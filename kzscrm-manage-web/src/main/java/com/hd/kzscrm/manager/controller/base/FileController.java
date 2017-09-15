package com.hd.kzscrm.manager.controller.base;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.collections.CollectionUtils;
import org.csource.common.MyException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hd.kzscrm.common.fastdfs.FastdfsUtil;
import com.hd.kzscrm.common.fastdfs.FileUtil;
import com.hd.kzscrm.common.model.RespModel;
import com.hd.kzscrm.common.util.CommUtil;
import com.hd.kzscrm.common.util.StringUtil;
import com.hd.kzscrm.manager.controller.BaseController;
/**
 * 
* @ClassName: FileController 
* @Description: 文件操作控制器 
* @author xiongchangjing 
* @date 2017年3月11日 下午1:43:48 
*
 */
@Controller
@RequestMapping("/file")
public class FileController extends BaseController {
	private static final Logger LOGGER = LoggerFactory.getLogger(FileController.class);
	
	@Value("${img.size.type}")
	private String[] sizeTypeList;
	
	/**
	 * 
	* @Title: upload 
	* @Description: 批量文件上传
	* @author xiongchangjing 
	* @date 2017年3月11日 下午2:10:42  
	* @param @param files
	* @param @return    设定文件 
	* @return RespModel    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/upload_files", method = RequestMethod.POST)
	@ResponseBody
	public RespModel upload(@RequestParam MultipartFile[] files) {
		List<String> paths = new ArrayList<String>();
		for (MultipartFile file : files) {
			 //上传文件 返回路径
            String path="";
			if (file.isEmpty()) {
				logger.info("文件未上传");
			} else {
				logger.info("文件长度: " + file.getSize());
				logger.info("文件类型: " + file.getContentType());
				logger.info("文件名称: " + file.getName());
				logger.info("文件原名: " + file.getOriginalFilename());
				logger.info("========================================");
				try {
					byte[] b = file.getBytes();
					String dirPath = "/opt/temp";
					if (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) {
						dirPath = "E:\\temp";
					}
					String filePath = FileUtil.uploadFilebySpring(file, dirPath);
					List<String[]> sizeList = new ArrayList<String[]>();
					sizeList.add(new String[] { "80", "80" });
					sizeList.add(new String[]{"200","200"});
					sizeList.add(new String[]{"600","600"});
					List<String> pathList = FileUtil.createResizeImage(filePath, sizeList, true);			
					path = FastdfsUtil.getInstance().upload_file1(filePath);
					for (int i = 0; i < pathList.size(); i++) {
						FastdfsUtil.getInstance().upload_file1(path, pathList.get(i), ("_"+sizeList.get(i)[0]+"x"+sizeList.get(i)[1]));
					}
				} catch (IOException e) {
					e.printStackTrace();
				} catch (MyException e) {
					e.printStackTrace();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
                logger.info("文件路径:"+path);
                paths.add(path);
			}

		}
		RespModel model = new RespModel(0, "操作成功", paths);
		return model;
	}

	
	/**
	 * 
	* @Title: upload 
	* @Description: 单个文件上传
	* @author xiongchangjing 
	* @date 2017年3月11日 下午2:12:52  
	* @param @param file
	* @param @return    设定文件 
	* @return RespModel    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	@ResponseBody
	public RespModel upload(@RequestParam MultipartFile file) {
		//上传文件 返回路径
		String path = "";
		logger.info("文件长度: " + file.getSize());
		logger.info("文件类型: " + file.getContentType());
		logger.info("文件名称: " + file.getName());
		logger.info("文件原名: " + file.getOriginalFilename());
		logger.info("========================================");
		try {
			byte[] b = file.getBytes();
			String dirPath = "/opt/temp";
			if (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) {
				dirPath = "E:\\temp";
			}
			String filePath = FileUtil.uploadFilebySpring(file, dirPath);
			List<String[]> sizeList = new ArrayList<String[]>();
			sizeList.add(new String[]{"80","80"});
			sizeList.add(new String[]{"200","200"});
			sizeList.add(new String[]{"600","600"});
			List<String> pathList = FileUtil.createResizeImage(filePath, sizeList, true);			
			path = FastdfsUtil.getInstance().upload_file1(filePath);
			for (int i = 0; i < pathList.size(); i++) {
				FastdfsUtil.getInstance().upload_file1(path, pathList.get(i), ("_"+sizeList.get(i)[0]+"x"+sizeList.get(i)[1]));
			}
			
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          
		RespModel model = new RespModel(0, "操作成功", path);
		return model;
	}

	
	/**
	 * 
	* @Title: upload_Resize_file 
	* @Description: 单个文件裁剪上传
	* @author xiongchangjing 
	* @date 2017年3月11日 下午2:12:52  
	* @param @param file
	* @param @return    设定文件 
	* @return RespModel    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/upload_Resize_file", method = RequestMethod.POST)
	@ResponseBody
	public RespModel upload_Resize_file(HttpServletRequest request,@RequestParam MultipartFile file) {
		//上传文件 返回路径
		String path = "";
		RespModel model = new RespModel();
		try {
			//图片实际宽高
			String scaleWidthString = request.getParameter("sw");
			String scaleHeightString = request.getParameter("sh");
			if(StringUtil.isEmpty(scaleWidthString) || StringUtil.isEmpty(scaleHeightString)){
				model = new RespModel(RespModel.RespCode.PARAM_EXCEPTION.getCode(), "图片实际宽高异常");
				return model;
			}
			int swIndex = scaleWidthString.indexOf("px");
			Integer sw = Integer.parseInt(scaleWidthString.substring(0, swIndex));
			int shIndex = scaleHeightString.indexOf("px");
			Integer sh = Integer.parseInt(scaleHeightString.substring(0, shIndex));
			
			//截取坐标
			Double x = CommUtil.parseDouble(request.getParameter("x"));
			Double y = CommUtil.parseDouble(request.getParameter("y"));
			Double w = CommUtil.parseDouble(request.getParameter("w"));
			Double h = CommUtil.parseDouble(request.getParameter("h"));
			//判断截取坐标
			if(w<=0 || h<=0){
				model = new RespModel(RespModel.RespCode.PARAM_EXCEPTION.getCode(), "截取坐标获取异常");
				return model;
			}
			
			String dirPath = "/opt/temp";
			if (System.getProperty("os.name").toLowerCase().indexOf("windows") != -1) {
				dirPath = "c:\\temp";
			}

			//处理原图
			String filePath = FileUtil.uploadFilebySpring(file, dirPath);

			FileUtil.imgCut(filePath, x, y, w, h, sw, sh);
			
			List<String[]> sizeList = new ArrayList<String[]>();
			if(sizeTypeList!=null &&sizeTypeList.length>0){
				for(String str : sizeTypeList){
					if(StringUtil.isNotEmpty(str) && str.indexOf("x")>-1){
						sizeList.add(str.split("x"));
					}
				}
			}
			if(CollectionUtils.isEmpty(sizeList)){
				sizeList.add(new String[] { "80", "80" });
				sizeList.add(new String[] { "200", "200" });
				sizeList.add(new String[] { "600", "600" });
			}
			List<String> pathList = FileUtil.createResizeImage(filePath, sizeList, true);
			path = FastdfsUtil.getInstance().upload_file1(filePath);
			for (int i = 0; i < pathList.size(); i++) {
				FastdfsUtil.getInstance().upload_file1(path, pathList.get(i),("_" + sizeList.get(i)[0] + "x" + sizeList.get(i)[1]));
			}
			model = new RespModel(RespModel.RespCode.SUCCESS.getCode(), "操作成功", path);
		} catch (Exception e) {
			e.printStackTrace();
			model = new RespModel(RespModel.RespCode.SYS_EXCEPTION.getCode(), "操作失败");
		}
		
		return model;
	}
	
	
	
	/**
	 * 
	* @Title: upload 
	* @Description: 单个文件上传
	* @author suchangsong
	* @date 2017年04月15日 下午10:05:23  
	* @param @param file
	* @param @return    设定文件 
	* @return RespModel    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	@ResponseBody
	public RespModel uploadFile(@RequestParam MultipartFile file) {
		//上传文件 返回路径
		String path = "";
		logger.info("文件长度: " + file.getSize());
		logger.info("文件类型: " + file.getContentType());
		logger.info("文件名称: " + file.getName());
		logger.info("文件原名: " + file.getOriginalFilename());
		logger.info("========================================");
		try {
			byte[] b = file.getBytes();
			path = FastdfsUtil.getInstance().upload(b,file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RespModel model = new RespModel(0, "操作成功", path);
		System.out.println("上传完毕！");
		return model;
	}
	

	/**
	 * 
	* @Title: upload 
	* @Description: 单个文件上传
	* @author suchangsong
	* @date 2017年04月15日 下午10:05:23  
	* @param @param file
	* @param @return    设定文件 
	* @return RespModel    返回类型 
	* @throws
	 */
	@RequestMapping(value = "/uploadAppFile", method = RequestMethod.POST)
	@ResponseBody
	public RespModel uploadAppFile(@RequestParam MultipartFile file) {
		//上传文件 返回路径
		String path = "";
		logger.info("文件长度: " + file.getSize());
		logger.info("文件类型: " + file.getContentType());
		logger.info("文件名称: " + file.getName());
		logger.info("文件原名: " + file.getOriginalFilename());
		logger.info("========================================");
		try {
			byte[] b = file.getBytes();
			path = FastdfsUtil.getInstance().upload(b,file.getOriginalFilename());
		} catch (IOException e) {
			e.printStackTrace();
		} catch (MyException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		RespModel model = new RespModel(0, "操作成功", path);
		System.out.println("上传完毕！");
		return model;
	}
}
