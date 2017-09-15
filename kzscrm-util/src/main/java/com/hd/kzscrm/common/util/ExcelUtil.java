package com.hd.kzscrm.common.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

public class ExcelUtil {
	private static int sheetSize=65535;
	private static int sheetNum=1;
	public static <T>  void  writeXls(HttpServletResponse response,String workName,LinkedHashMap<String,String> map,List<T> data,Class clazz) throws Exception{
		//判断数据是否为空
	    if(data==null || data.size()==0){
	    	throw new Exception("导出数据为空！");
	    }
	    //设置sheet的个数
	    if(data.size()>sheetSize){
	    	if(data.size()%sheetSize==0){
	    		sheetNum=data.size()/sheetSize;
	    	}else{
	    		sheetNum=data.size()/sheetSize+1;
	    	}
	    }
	    HSSFWorkbook  wb = new HSSFWorkbook();//创建工作薄
	    //判断创建sheet的个数
	    for(int num=0;num<sheetNum;num++){
	    	int size=sheetSize*num;
	    	HSSFSheet sheet=wb.createSheet("sheet"+(num+1));//设置当前sheet的名字
	    	
	    	//每一个sheet首行添加标题
	    	if(map==null || map.size()>0){
	    		HSSFRow row=sheet.createRow(0);
	    		Set<String> set = map.keySet();
	    		int c=0;
	    		for(String str:set){
	    			HSSFCell cell=row.createCell(c);
		    	  	cell.setCellValue(map.get(str));
		    	  	c++;
	    		}
	    	}
	    	int dataSize=sheetSize;
	    	if(sheetNum==1){
	    		dataSize=data.size();
	    	}else{
	    		if(num==sheetNum-1){
	    			dataSize=data.size()-size;
	    		}
	    	}
    		//添加内容
	    	for(int r=1;r<=dataSize;r++){
	    		HSSFRow row=sheet.createRow(r);
	    		Set<String> set = map.keySet();
	    		int c=0;
    			T t = data.get(size+r-1);
    			Class clazz1 = t.getClass();
	    		for(String str:set){
	    			HSSFCell cell=row.createCell(c);
	    			Field filed=clazz1.getDeclaredField(str);
	    			filed.setAccessible(true);
	    			isType(cell,filed.get(t));
		    	  	c++;
	    		}
    		}
	    }
	    //文件名加上当前的时间戳
	    String currentTimestamp = DateUtil.getCurrentTimestamp();
	    String newWorkName = workName+currentTimestamp;
	    outExcel(response,wb,newWorkName);
	}
	
	//excel 添加数据
	public static void isType(HSSFCell cell,Object param){
		if(param==null){
			cell.setCellValue("");
		}else if(param instanceof Integer){
			cell.setCellValue(Integer.valueOf(param.toString()));
		}else if(param instanceof Date){
			SimpleDateFormat sdf=new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");
			cell.setCellValue(sdf.format(param));
		}else{
			cell.setCellValue(param.toString());
		}
	}
	
	//输出excel
	public static void outExcel(HttpServletResponse response,HSSFWorkbook wb,String name) throws IOException{
		   OutputStream output=response.getOutputStream();
		   response.reset();  
		   response.setHeader("Content-disposition", "attachment; filename="+ new String(name.getBytes("gb2312"), "ISO-8859-1") +".xls");  
		   response.setContentType("application/msexcel");          
		   wb.write(output);  
		   output.close();  

	}
	
	
	//excel 导入功能
	public static List<Map<String,Object>> insertExcel(HttpServletRequest request,String file) throws Exception{
		 MultipartHttpServletRequest multiRequest=(MultipartHttpServletRequest)request;
		 MultipartFile multipartFile=multiRequest.getFile(file);
		 String fileName=multipartFile.getOriginalFilename();
		 InputStream is=multipartFile.getInputStream();
		 String pix=fileName.substring(fileName.lastIndexOf("."),fileName.length());
		 if(pix.equalsIgnoreCase(".xls")){
			 return inserXls(is);
		 }else if(pix.equalsIgnoreCase(".xlsx")){
			 return inserXlsx(is);
		 }else{
			 throw new RuntimeException("文件格式错误,只允许excel！");
		 }
	} 
	
	//导入xls格式
	public static List<Map<String,Object>> inserXls(InputStream is) throws IOException{
		 HSSFWorkbook wb=new HSSFWorkbook(is);
		 HSSFSheet sheet=wb.getSheetAt(0);//获取第一个sheet
		 int rowNum=sheet.getLastRowNum();
		 List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
		for(int i=1;i<=rowNum;i++){
			HSSFRow row=sheet.getRow(i);
			if(row==null){
				continue;
			}
			if(row.getLastCellNum()<=0){
				continue;
			}
			Map<String,Object> map=new HashMap<String,Object>();
			for(int n=0;n<row.getLastCellNum();n++){
				HSSFCell cell=row.getCell(n);
				if(cell != null){
					switch(cell.getCellType()){ 
						case HSSFCell.CELL_TYPE_NUMERIC://为number型
							map.put("num"+n,cell.getNumericCellValue());
							break;
						case HSSFCell.CELL_TYPE_STRING:	//为string型
							map.put("num"+n,cell.getStringCellValue());
							break;
						case HSSFCell.CELL_TYPE_BOOLEAN://为boolean型	
							map.put("num"+n,cell.getBooleanCellValue());
							break;
						case HSSFCell.CELL_TYPE_FORMULA:	//获取公式型
							map.put("num"+n,cell.getCellFormula());
							break;
						case HSSFCell.CELL_TYPE_BLANK://当为空值时
							map.put("num"+n,null);
							break;
						case HSSFCell.CELL_TYPE_ERROR://当发生错误的时候
							map.put("num"+n,null);
							break;
						default:
							map.put("num"+n,null);
							break;
					}
				}else{
					map.put("num"+n,null);
				}
			}
			mapList.add(map);
		}
		is.close();
		return mapList;
	}
	
	//导入xlsx格式
	public static List<Map<String,Object>> inserXlsx(InputStream is) throws IOException{
		 XSSFWorkbook wb=new XSSFWorkbook(is);
		 XSSFSheet sheet=wb.getSheetAt(0);//获取第一个sheet
		 int rowNum=sheet.getLastRowNum();
		 List<Map<String,Object>> mapList=new ArrayList<Map<String,Object>>();
		for(int i=1;i<=rowNum;i++){
			XSSFRow row=sheet.getRow(i);
			if(row==null){
				continue;
			}
			Map<String,Object> map=new HashMap<String,Object>();
			for(int n=0;n<row.getLastCellNum();n++){
				XSSFCell cell=row.getCell(n);
				if(cell!=null){
					switch(cell.getCellType()){
						case XSSFCell.CELL_TYPE_NUMERIC://为number型
							map.put("num"+n,cell.getNumericCellValue());
							break;
						case XSSFCell.CELL_TYPE_STRING:	//为string型
							map.put("num"+n,cell.getStringCellValue());
							break;
						case XSSFCell.CELL_TYPE_BOOLEAN://为boolean型	
							map.put("num"+n,cell.getBooleanCellValue());
							break;
						case XSSFCell.CELL_TYPE_FORMULA:	//获取公式型
							map.put("num"+n,cell.getCellFormula());
							break;
						case XSSFCell.CELL_TYPE_BLANK://当为空值时
							map.put("num"+n,null);
							break;
						case XSSFCell.CELL_TYPE_ERROR://当发生错误的时候
							map.put("num"+n,null);
							break;
						default:
							map.put("num"+n,null);
							break;
					}
				}else{
					map.put("num"+n,"");
				}
			}
			mapList.add(map);
		}
		is.close();
		return mapList;
	}
	
}
