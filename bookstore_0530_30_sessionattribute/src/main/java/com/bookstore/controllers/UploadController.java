package com.bookstore.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.bookstore.utils.UploadItem;

@Controller
@RequestMapping(value = "/upload")
@PropertySource("classpath:/com/bookstore/config/spring.properties")
public class UploadController {
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "index", method=RequestMethod.POST)
	public String uploadCompleted(UploadItem uploadItem, BindingResult result) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			return "tile.upload.error";
		}

		if (!uploadItem.getFileData().isEmpty()) {
			String filename = uploadItem.getFileData().getOriginalFilename();
			String imgExt = filename.substring(filename.lastIndexOf(".") + 1, filename.length());

			// upload 가능한 파일 타입 지정
			if (imgExt.equalsIgnoreCase("JPG")
					|| imgExt.equalsIgnoreCase("JPEG")
					|| imgExt.equalsIgnoreCase("GIF")
					|| imgExt.equalsIgnoreCase("PNG")) {
				byte[] bytes = uploadItem.getFileData().getBytes();
				try {
					System.err.println("FilePath : " + env.getProperty("upload.filepath2") + filename);
					File outFileName = new File(env.getProperty("upload.filepath2") + filename);					
					FileOutputStream fileoutputStream = new FileOutputStream(
							outFileName);
					fileoutputStream.write(bytes);
					fileoutputStream.close();
				} catch (IOException ie) {
					System.err.println("File writing error! ");
				}
				System.err.println("File upload success! ");
			} else {
				System.err.println("File type error! ");
			}
		}
		return "/uploaditem";
	}
	
	@RequestMapping(value = "/uploaditem", method = RequestMethod.GET)
	public String getUploadForm(){
		return "/uploaditem";
	}
	
}
