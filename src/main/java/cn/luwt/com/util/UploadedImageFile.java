package cn.luwt.com.util;

import org.springframework.web.multipart.MultipartFile;

public class UploadedImageFile {

	MultipartFile image;//�ϴ�
	
	public MultipartFile getImage(){
		return image;
	}
	
	public void setImage(MultipartFile image){
		this.image=image;
	}
}
