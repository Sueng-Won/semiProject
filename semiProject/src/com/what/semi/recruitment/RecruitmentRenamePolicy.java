package com.what.semi.recruitment;

import java.io.File;
import java.sql.Date;
import java.text.SimpleDateFormat;

import com.oreilly.servlet.multipart.FileRenamePolicy;

public class RecruitmentRenamePolicy implements FileRenamePolicy {

	@Override
	public File rename(File oldFile) {
		long time = System.currentTimeMillis();
		Date date = new Date(time);
		SimpleDateFormat sdf = new SimpleDateFormat("YYYYMMDDhhmmss");
		int random = (int)(Math.random()*10000);
		
		//xxxx.jpg -> 새로운 이름jpg
		String name = oldFile.getName();
		String fileName = "";
		String ext = "";
		int pointIndex = name.lastIndexOf(".");
		
		if(pointIndex!=-1){
			//마지막 인덱스 포함x
			fileName = name.substring(0,pointIndex);
			//해당 인덱스 글자 표함여부
			ext = name.substring(pointIndex);
		}else{
			fileName = name;
		}
		
		//새로운 파일 이름 정의
		String newFileName = sdf.format(date) + random + ext;
		File newFile = new File(oldFile.getParent(),newFileName);
		return newFile;
	}

}
