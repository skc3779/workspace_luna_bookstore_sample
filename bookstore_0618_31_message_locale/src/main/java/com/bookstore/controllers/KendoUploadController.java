package com.bookstore.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.bookstore.utils.UploadForm;

@Controller
@RequestMapping(value = "/upload")
@PropertySource("classpath:/spring.properties")
public class KendoUploadController {
	
	private static Log log = LogFactory.getLog(KendoUploadController.class);
	
	@Autowired
	private Environment env;
	
	@RequestMapping(value = "/kendo/uploadform", method = RequestMethod.GET)
	public String getKendoUpload(){
		return "/kendo/uploadform";
	}

	@RequestMapping(value = "/kendo/uploadform", method=RequestMethod.POST)
	public String getKendoUploadSave(@ModelAttribute("uploadForm") UploadForm uploadForm, 
				BindingResult result, Model model) {
		if (result.hasErrors()) {
			for (ObjectError error : result.getAllErrors()) {
				System.err.println("Error: " + error.getCode() + " - " + error.getDefaultMessage());
			}
			return "tile.upload.error";
		}

		if (!uploadForm.getFiles().isEmpty()) {
			List<MultipartFile> files = uploadForm.getFiles();
			List<String> fileNames = new ArrayList<String>();
			
			for(MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				String filePath = env.getProperty("upload.filepath2") + fileName;
				fileNames.add(fileName);
				log.info("FilePath : " + filePath);
				try {				
					byte[] bytes = file.getBytes();	
					File outFileName = new File(filePath);
					FileOutputStream fileoutputStream = new FileOutputStream(outFileName);
					fileoutputStream.write(bytes);
					fileoutputStream.close();
				} catch(IOException ie) {
					System.err.println("File writing error! ");
				}
			}
			
			model.addAttribute("files", fileNames);
		}
		return "/kendo/uploadresult";
	}
	
	
	@RequestMapping(value = "/kendo/async/uploadform", method = RequestMethod.GET)
	public String getKendoAsyncUpload(){
		return "/kendo/async/uploadform";
	}
	
	@RequestMapping(value = "/kendo/async/save", method = RequestMethod.POST)
	public @ResponseBody String getKendoAsyncSave(@RequestParam List<MultipartFile> files) {		
	
		if (!files.isEmpty()) {
			List<String> fileNames = new ArrayList<String>();			
			for(MultipartFile file : files) {
				String fileName = file.getOriginalFilename();
				String filePath = env.getProperty("upload.filepath2") + fileName;
				fileNames.add(fileName);
				log.info("FilePath : " + filePath);
				try {				
					byte[] bytes = file.getBytes();	
					File outFileName = new File(filePath);
					FileOutputStream fileoutputStream = new FileOutputStream(outFileName);
					fileoutputStream.write(bytes);
					fileoutputStream.close();
				} catch(IOException ie) {
					System.err.println("File writing error! ");
				}
			}
		}
		return "";
	}	

	@RequestMapping(value = "/kendo/async/remove", method = RequestMethod.POST)
	public @ResponseBody String getKendoAsyncRemove(@RequestParam String[] fileNames) {
		
		if(fileNames.length > 0) {		
			for(String fileName : fileNames) {
				String filePath = env.getProperty("upload.filepath2") + fileName;
				log.info("FilePath : " + filePath);
				File file = new File(filePath);
				if(file.isFile()) {
					file.delete();
				}				
			}
		}
		return "";
	}
	
}
