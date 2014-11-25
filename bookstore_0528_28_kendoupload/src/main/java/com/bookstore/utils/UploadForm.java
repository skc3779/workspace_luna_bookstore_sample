package com.bookstore.utils;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class UploadForm {
	
	private String name;	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	private List<MultipartFile> files;
	public List<MultipartFile> getFiles() {
		return files;
	}
	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}
}
