package com.bookstore.controllers;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;


@Controller
@RequestMapping(value = "/upload")
@PropertySource("classpath:/com/bookstore/config/spring.properties")
public class MultiUploadController {
	
	private final static Logger log = LoggerFactory.getLogger(MultiUploadController.class);
	
	@Autowired
	private Environment env;
	
	@Autowired
	private CommonsMultipartResolver multipartRolver;
	
	@RequestMapping(value = "/multiupload", method = RequestMethod.GET)
	public String getUploadForm(){
		return "/multiupload";
	}
	
	@RequestMapping(value = "/multiuploadresult", method=RequestMethod.POST)
	public String uploadCompleted(HttpServletRequest request, HttpServletResponse response, Model model) {

		//MultipartRequestWrapper requestWrapper = new MultipartRequestWrapper(request);
		MultipartHttpServletRequest multipart = (MultipartHttpServletRequest)request;		
		
		List<ParamValue> list = new ArrayList<ParamValue>();
		
		for (Enumeration<?> e = multipart.getParameterNames(); e.hasMoreElements();) {
			String name = (String)e.nextElement();
			String value = multipart.getParameter(name);
			list.add(new ParamValue(name,value));
		}
		
		Iterator<?> files = multipart.getFileNames();
		while(files.hasNext()) {
			
			String name = (String)files.next();
			MultipartFile file = multipart.getFile(name);

			String fileName = file.getOriginalFilename();
			String filePath = env.getProperty("upload.filepath2") + fileName;
			
			try {
				
				byte[] bytes = file.getBytes();	
				File outFileName = new File(filePath);
				if(outFileName.exists()) {
					outFileName.deleteOnExit();
				}
				FileOutputStream fileoutputStream = new FileOutputStream(outFileName);
				fileoutputStream.write(bytes);
				fileoutputStream.close();
			} catch(IOException ie) {
				System.err.println("File writing error! ");
			}
			
			list.add(new ParamValue(name, fileName));
		}

		model.addAttribute("params", list);
		
		return "/multiuploadresult";		
	}
	
	public class ParamValue {
		public ParamValue(String name, String value) {
			this.name = name;
			this.value = value;
		}
		private String name;
		private String value;
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getValue() {
			return value;
		}
		public void setValue(String value) {
			this.value = value;
		}
	}
}
