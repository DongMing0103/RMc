package com.hd.kzscrm.common.fastdfs;

/**
 * 
* @ClassName: Picture 
* @Description: 图片对象 
* @author xiongchangjing 
* @date 2017年3月11日 下午2:18:48 
*
 */
public class Picture {
	/**
	 * 相对路径
	 */
	private String path;
	
	
	/**
	 * 远程绝对路径
	 */
	private String  remoteUrl;

	
	

	public Picture(String path, String remoteUrl) {
		super();
		this.path = path;
		this.remoteUrl = remoteUrl;
	}


	public String getPath() {
		return path;
	}


	public void setPath(String path) {
		this.path = path;
	}


	public String getRemoteUrl() {
		return remoteUrl;
	}


	public void setRemoteUrl(String remoteUrl) {
		this.remoteUrl = remoteUrl;
	}
	
	
	
}
