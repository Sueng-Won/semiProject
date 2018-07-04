package com.what.semi.common.file;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class MyRenamePolicy implements FileRenamePolicy {
	@Override
	public File rename(File oldFile) {
		//long time = System.currentTimeMillis();
		Date date = new Date();
		SimpleDateFormat sdf 
				= new SimpleDateFormat("yyyyMMddhhmmss");
		
		int random = (int)(Math.random()*10000);
		
		//XXXX.jpg -> 새로운이름.jpg
		
		String name = oldFile.getName();
		String fileName = "";
		String ext = "";
		int pointIndex = name.lastIndexOf(".");
		
		//기존 파일에 대한 이름
		if(-1 != pointIndex){
			//마지막 인덱스 포함 X
			fileName = name.substring(0, pointIndex);
			//해당 인덱스 글자 포함 O
			ext = name.substring(pointIndex);
		}else{
			fileName = name;
		}
		
		//새로운 파일 이름 정의
		String newFileName = sdf.format(date) + random + ext;
		File newFile = new File(oldFile.getParent(), newFileName);
		
		return newFile;
	}
}












