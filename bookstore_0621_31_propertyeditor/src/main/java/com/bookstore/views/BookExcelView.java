package com.bookstore.views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.document.AbstractJExcelView;

import com.bookstore.contants.PropertyNames;

@Component
public class BookExcelView extends AbstractJExcelView {

	@Override
	protected void buildExcelDocument(Map<String, Object> model,
			WritableWorkbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		String fileName = createFileName();
		setFileNameToResponse(request, response, fileName);
		
		WritableSheet sheet = workbook.createSheet("책 리스트", 0);
		sheet.addCell(new Label(0,0,"제목"));
		sheet.addCell(new Label(1,0,"저자"));
		sheet.addCell(new Label(2,0,"설명"));
		sheet.addCell(new Label(3,0,"출판일"));
		
		@SuppressWarnings("unchecked")
		ArrayList<Map<String, Object>> books = (ArrayList<Map<String, Object>>) model.get("books");
		
		int row=1;
		for(Map<String, Object> book : books){
			sheet.addCell(new Label(0, row, (String) book.get(PropertyNames.Name)));
			sheet.addCell(new Label(1, row, (String) book.get(PropertyNames.Author)));
			sheet.addCell(new Label(2, row, (String) book.get(PropertyNames.Comment)));
			sheet.addCell(new Label(3, row, (String) book.get(PropertyNames.PublishDate)));
			
			row++;
		}
		
	}
	
	private String createFileName() {
		SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd");
		return new StringBuilder("booklist")
				.append("-").append(fileFormat.format(new Date()))
				.append(".xls").toString();
	}
	
	private void setFileNameToResponse(HttpServletRequest request, HttpServletResponse response,
			String fileName) {
		String userAgent = request.getHeader("User-Agent");
		if(userAgent.indexOf("MISE 5.5") >= 0) {
			response.setContentType("doesn/matter");
			response.setHeader("Content-Disposition", "filename=\"" + fileName + "\"");			
		} else {
			response.setHeader("Content-Disposition", "attachment; filename=\"" + fileName + "\"");
		}
	}

}
