package com.user.frame;

import java.io.FileOutputStream;

import org.springframework.web.multipart.MultipartFile;

public class Util {
public static void saveFile(MultipartFile mf, String admindir, String userdir) {
		
		byte [] data;
		String imgname = mf.getOriginalFilename();
		try {
			data = mf.getBytes();
			FileOutputStream adminfo = 
					new FileOutputStream(admindir+imgname);
			adminfo.write(data);
			adminfo.close();
			System.out.println("저장완료");
			
			FileOutputStream userfo = 
					new FileOutputStream(userdir+imgname);
			userfo.write(data);
			userfo.close();
			System.out.println("저장완료");
		}catch(Exception e) {
			System.out.println("저장실패");
		}
		
	}
}
