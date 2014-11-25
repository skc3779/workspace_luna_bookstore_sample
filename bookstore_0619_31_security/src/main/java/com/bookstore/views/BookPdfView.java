package com.bookstore.views;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bookstore.contants.PropertyNames;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.BaseFont;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class BookPdfView extends AbstractIText5PdfView {

	private void setFileNameToResponse(HttpServletRequest request,
			HttpServletResponse response, String fileName) {
		String userAgent = request.getHeader("User-Agent");
		if (userAgent.indexOf("MSIE 5.5") >= 0) {
			response.setContentType("doesn/matter");
			response.setHeader("Content-Disposition", "filename=\"" + fileName
					+ "\"");
		} else {
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ fileName + "\"");
		}
	}

	private String createFileName() {
		SimpleDateFormat fileFormat = new SimpleDateFormat("yyyyMMdd_HHmmss");
		return new StringBuilder("pdf").append("-")
				.append(fileFormat.format(new Date())).append(".pdf")
				.toString();
	}

	@Override
	protected void buildPdfMetadata(Map<String, Object> model,
			Document document, HttpServletRequest request) throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	protected void buildPdfDocument(Map<String, Object> model,
			Document document, PdfWriter writer, HttpServletRequest request,
			HttpServletResponse response) throws Exception {

		String fileName = createFileName();
		setFileNameToResponse(request, response, fileName);

//		BaseFont nGothic = BaseFont.createFont("C:/Windows/Fonts/NanumGothic.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//		BaseFont nGothicBold = BaseFont.createFont("C:/Windows/Fonts/NanumGothicBold.ttf", BaseFont.IDENTITY_H, BaseFont.NOT_EMBEDDED);
//		Font font = new Font(nGothicBold, 20);
		
		Font fGothicBold = FontFactory.getFont("C:/Windows/Fonts/NanumGothicBold.ttf", BaseFont.IDENTITY_H,
							BaseFont.NOT_EMBEDDED, 20);
		Font fGothic = FontFactory.getFont("C:/Windows/Fonts/NanumGothic.ttf", BaseFont.IDENTITY_H,
							BaseFont.NOT_EMBEDDED, 12);
		
//		Chapter chapter = new Chapter(new Paragraph("Book List, 북 리스트", fGothicBold), 1);
//		chapter.add(new Paragraph("내용입니다.", nGothic));
//		chapter.add(new Paragraph("내용입니다.", nGothic));
//		chapter.add(new Paragraph("내용입니다.", nGothic));
//		chapter.add(new Paragraph("내용입니다.", nGothic));
		
		/* iText in Action Books 참고 */

		// 부문
		Chunk chunk = new Chunk("Book List, 북 리스트", fGothicBold);
		chunk.setBackground(BaseColor.RED, 1f, 0.5f, 1f, 1.5f);
		chunk.setTextRise(6);
		document.add(chunk);
		document.add(new Phrase(" "));
		
		// 제목 등록	
		
		
    	// a table with three columns
        PdfPTable table = new PdfPTable(4);
        
        // properties
        table.setTotalWidth(new float[]{80,40,80,40});
        //table.setLockedWidth(true);
        
        // the cell object        
        PdfPCell cell;
        // we add a cell with colspan 4
        cell = new PdfPCell(new Phrase("테이블", fGothic));
        cell.setColspan(4);
        table.addCell(cell);        

        // 제목
        table.addCell(new Phrase("제목", fGothic));
        table.addCell(new Phrase("저자", fGothic));
        table.addCell(new Phrase("설명", fGothic));
        table.addCell(new Phrase("출판일", fGothic));

		@SuppressWarnings("unchecked")
		ArrayList<Map<String, Object>> books = (ArrayList<Map<String, Object>>) model.get("books");
		
		// 내용
		for(Map<String, Object> book : books){
			table.addCell(new Phrase((String) book.get(PropertyNames.Name), fGothic));
			table.addCell(new Phrase((String) book.get(PropertyNames.Author), fGothic));
			table.addCell(new Phrase((String) book.get(PropertyNames.Comment), fGothic));
			table.addCell(new Phrase((String) book.get(PropertyNames.PublishDate), fGothic));
		}
        
		document.add(table);
	}

}
