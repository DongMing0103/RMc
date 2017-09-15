package com.hd.kzscrm.common.fastdfs;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.awt.image.ImageFilter;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.imageio.ImageIO;

import org.springframework.web.multipart.MultipartFile;

import com.alibaba.dubbo.container.Main;
import com.hd.kzscrm.common.util.DateUtils;
import com.hd.kzscrm.common.util.StringUtil;


/**
 * 文件操作工具类
* @ClassName: FileUtil 
* @Description: TODO(这里用一句话描述这个类的作用) 
* @author xiongchangjing 
* @date 2017年3月18日 下午5:38:17 
*
 */
public class FileUtil {
	/**
	 * 
	* @Title: uploadFilebySpring 
	* @Description: TODO(使用Spring上传文件)
	* @author xiongchangjing 
	* @date 2017年3月18日 下午8:15:41  
	* @param @param multipartFile
	* @param @param uploadPath
	* @param @return
	* @param @throws IOException    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String uploadFilebySpring(MultipartFile multipartFile,
			String uploadPath) throws IOException {
			
		String dateTime= DateUtils.getFormatDate("yyyyMMddHHmmss");
		//生成某年目录名   再生成月目录
		/*String dateTimePath=dateTime.substring(0, 4)+"/"+dateTime.substring(4, 6);
		uploadPath+="/"+dateTimePath;*/
		File folder = new File(uploadPath);

		if (!folder.exists()) {
			folder.mkdirs();
		}
		 /**获取文件的后缀**/    
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));     
		String fileName = dateTime + "_"+ UUID.randomUUID().toString()+suffix;
		folder=new File(uploadPath + "/" + fileName);
        multipartFile.transferTo(folder);     
		fileName=uploadPath+"/"+fileName;
		return fileName;
	}
	
	/**
	 * 
	* @Title: getFileSavePath 
	* @Description: TODO(获取文件存储路径)
	* @author xiongchangjing 
	* @date 2017年3月20日 下午6:32:54  
	* @param @param multipartFile
	* @param @param dirPath
	* @param @return    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String getFileSavePath(MultipartFile multipartFile,String dirPath){
		String dateTime= DateUtils.getFormatDate("yyyyMMddHHmmss");
		//生成某年目录名   再生成月目录
		String dateTimePath=dateTime.substring(0, 4)+"/"+dateTime.substring(4, 6);
		dirPath+="/"+dateTimePath;
		File folder = new File(dirPath);

		if (!folder.exists()) {
			folder.mkdirs();
		}
		 /**获取文件的后缀**/    
        String suffix = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf("."));     
		String fileName = dateTime + "_"+ UUID.randomUUID().toString()+suffix;
		fileName=dirPath+"/"+fileName;
		return fileName;
	}
	/**
	 * 
	* @Title: createResizeImage 
	* @Description: TODO(批量创建压缩图)
	* @author xiongchangjing 
	* @date 2017年3月29日 下午9:08:43  
	* @param @param path  文件目录
	* @param @param list  宽高度list
	* @param @param sample 是否以缩放方式，而非缩略图方式
	* @param @return
	* @param @throws Exception    设定文件 
	* @return List<String>    返回类型 
	* @throws
	 */
	public static List<String>  createResizeImage(String path, List<String[]> list, boolean sample) throws Exception{
		List<String> desPathList = new ArrayList<>();
		for (int i = 0; i < list.size(); i++) {
			String[] str = list.get(i);
			String despath = resizeImage(path, StringUtil.parseInt(str[0]), StringUtil.parseInt(str[1]), sample);
			desPathList.add(despath);
		}
		return desPathList;
	}
	
	/**
	 * 
	* @Title: resizeImage 
	* @Description: TODO(这里用一句话描述这个方法的作用)
	* @author xiongchangjing 
	* @date 2017年3月29日 下午9:10:22  
	* @param @param path  文件目录
	* @param @param width  
	* @param @param height
	* @param @param sample 是否以缩放方式，而非缩略图方式
	* @param @return
	* @param @throws Exception    设定文件 
	* @return String    返回类型 
	* @throws
	 */
	public static String resizeImage(String path, int width, int height, boolean sample) throws Exception{
		System.out.println("resizeImage:"+path);
        String suffix = path.substring(path.lastIndexOf("."));     
        String sizesuffix = "_"+width+"x"+height;
        String desPath = path.replace(suffix, sizesuffix+suffix);
        Im4JavaUtils.resizeImage(path, desPath, width, height, sample);
		return desPath; 
	}
	
	/**
	 * 
	* @Title: deleteFile 
	* @Description: 删除文件
	* @author xiongchangjing 
	* @date 2017年3月18日 下午8:15:10  
	* @param @param sPath
	* @param @return    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
	public boolean deleteFile(String sPath) {  
	    boolean flag = false;  
	    File file = new File(sPath);  
	    // 路径为文件且不为空则进行删除  
	    if (file.isFile() && file.exists()) {  
	        file.delete();  
	        flag = true;  
	    }  
	    return flag;  
	}  
	
	/**
	 * 
	* @Title: imgCut 
	* @Description: 截图工具，根据截取的比例进行缩放裁剪
	* @author xiongchangjing 
	* @date 2017年3月30日 下午6:47:02  
    * @param path        图片路径
    * @param zoomX       缩放后的X坐标
    * @param zoomY       缩放后的Y坐标
    * @param zoomW       缩放后的截取宽度
    * @param zoomH       缩放后的截取高度
    * @param scaleWidth  缩放后图片的宽度
    * @param scaleHeight 缩放后的图片高度
	* @param @return
	* @param @throws Exception    设定文件 
	* @return boolean    返回类型 
	* @throws
	 */
    public static boolean imgCut(String path, Double zoomX, Double zoomY, Double zoomW,
    		Double zoomH, int scaleWidth, int scaleHeight) throws Exception {
        Image img;
        ImageFilter cropFilter;
        BufferedImage bi = ImageIO.read(new File(path));
        int fileWidth = bi.getWidth();
        int fileHeight = bi.getHeight();
        double scale = (double) fileWidth / (double) scaleWidth;

        double realX = zoomX * scale;
        double realY = zoomY * scale;
        double realW = zoomW * scale;
        double realH = zoomH * scale;

        if (fileWidth >= realW && fileHeight >= realH) {
            Image image = bi.getScaledInstance(fileWidth, fileHeight, Image.SCALE_DEFAULT);
            cropFilter = new CropImageFilter((int) realX, (int) realY, (int) realW, (int) realH);
            img = Toolkit.getDefaultToolkit().createImage(
                    new FilteredImageSource(image.getSource(), cropFilter));
            BufferedImage bufferedImage = new BufferedImage((int) realW, (int) realH, BufferedImage.TYPE_INT_RGB);
            Graphics g = bufferedImage.getGraphics();
            g.drawImage(img, 0, 0, null);
            g.dispose();
            //输出文件
            return ImageIO.write(bufferedImage, "JPEG", new File(path));
        } else {
            return true;
        }
    }
}
