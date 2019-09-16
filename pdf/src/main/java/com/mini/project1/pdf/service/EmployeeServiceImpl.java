package com.mini.project1.pdf.service;

import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import com.mini.project1.pdf.model.Employee;
import com.mini.project1.pdf.repository.EmployeeRepository;

@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
	
	@Autowired private EmployeeRepository employeeRepository;
	
	@Override
	public List<Employee>getAllEmployees(){
		
		return (List<Employee>) employeeRepository.findAll();
	}
	
	@Override
	public boolean createPdf(List<Employee> employees, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
		
		Document document = new Document(PageSize.A4,15,15,45, 30);
		
		try {
			
			String filePath = context.getRealPath("/resources/reports");
			File file = new File(filePath);
			
			boolean exists = new File(filePath).exists();
			if(!exists) {
				new File(filePath).mkdirs();
				
			}
			
			PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"employees"+".pdf"));
			document.open();
			
			Font mainFont = FontFactory.getFont("Arial",10,BaseColor.BLACK);
			
			Paragraph paragraph = new Paragraph("All employees sdfgasdf www.google.com",mainFont);
			
			paragraph.setAlignment(Element.ALIGN_CENTER);
			paragraph.setIndentationLeft(50);
			paragraph.setIndentationRight(50);
			paragraph.setSpacingAfter(10);
			document.add(paragraph);
			
			
			PdfPTable table = new PdfPTable(4);
				
			table.setWidthPercentage(100);
			table.setSpacingBefore(10f);
			table.setSpacingAfter(10);
			
			Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			Font tableBody = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
			
			
			float[] columnWidths = {2f,2f,2f,2f};
			table.setWidths(columnWidths);
			
			PdfPCell firstName = new PdfPCell(new Paragraph("First Name",tableHeader));
			
			firstName.setBorderColor(BaseColor.BLACK);
			firstName.setPaddingLeft(10);
			firstName.setHorizontalAlignment(Element.ALIGN_CENTER);
			firstName.setVerticalAlignment(Element.ALIGN_CENTER);
			firstName.setBackgroundColor(BaseColor.GRAY);
			firstName.setExtraParagraphSpace(5f);
			table.addCell(firstName);
			
			
			
			
			PdfPCell lastName = new PdfPCell(new Paragraph("Last Name",tableHeader));
			
			lastName.setBorderColor(BaseColor.BLACK);
			lastName.setPaddingLeft(10);
			lastName.setHorizontalAlignment(Element.ALIGN_CENTER);
			lastName.setVerticalAlignment(Element.ALIGN_CENTER);
			lastName.setBackgroundColor(BaseColor.GRAY);
			lastName.setExtraParagraphSpace(5f);
			table.addCell(lastName);
			
			
			
			
			
			
			PdfPCell email = new PdfPCell(new Paragraph("Email",tableHeader));
			
			email.setBorderColor(BaseColor.BLACK);
			email.setPaddingLeft(10);
			email.setHorizontalAlignment(Element.ALIGN_CENTER);
			email.setVerticalAlignment(Element.ALIGN_CENTER);
			email.setBackgroundColor(BaseColor.GRAY);
			email.setExtraParagraphSpace(5f);
			table.addCell(email);
			
			
		PdfPCell phoneNumber = new PdfPCell(new Paragraph("Phone Number",tableHeader));
			
			phoneNumber.setBorderColor(BaseColor.BLACK);
			phoneNumber.setPaddingLeft(10);
			phoneNumber.setHorizontalAlignment(Element.ALIGN_CENTER);
			phoneNumber.setVerticalAlignment(Element.ALIGN_CENTER);
			phoneNumber.setBackgroundColor(BaseColor.GRAY);
			phoneNumber.setExtraParagraphSpace(5f);
			table.addCell(phoneNumber);
			
			
			
			for(Employee employee:employees) {
				
				
				PdfPCell firstNameValue = new PdfPCell(new Paragraph(employee.getFirstName(),tableBody));
				
				firstNameValue.setBorderColor(BaseColor.BLACK);
				firstNameValue.setPaddingLeft(10);
				firstNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				firstNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
				firstNameValue.setBackgroundColor(BaseColor.WHITE);
				firstNameValue.setExtraParagraphSpace(5f);
				table.addCell(firstNameValue);
				

				PdfPCell lastNameValue = new PdfPCell(new Paragraph(employee.getLastName(),tableBody));
				
				lastNameValue.setBorderColor(BaseColor.BLACK);
				lastNameValue.setPaddingLeft(10);
				lastNameValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				lastNameValue.setVerticalAlignment(Element.ALIGN_CENTER);
				lastNameValue.setBackgroundColor(BaseColor.WHITE);
				lastNameValue.setExtraParagraphSpace(5f);
				table.addCell(lastNameValue);
				

				PdfPCell emailValue = new PdfPCell(new Paragraph(employee.getEmail(),tableBody));
				
				emailValue.setBorderColor(BaseColor.BLACK);
				emailValue.setPaddingLeft(10);
				emailValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				emailValue.setVerticalAlignment(Element.ALIGN_CENTER);
				emailValue.setBackgroundColor(BaseColor.WHITE);
				emailValue.setExtraParagraphSpace(5f);
				table.addCell(emailValue);
				
				PdfPCell phoneNumberValue = new PdfPCell(new Paragraph(employee.getPhoneNumber(),tableBody));
				
				phoneNumberValue.setBorderColor(BaseColor.BLACK);
				phoneNumberValue.setPaddingLeft(10);
				phoneNumberValue.setHorizontalAlignment(Element.ALIGN_CENTER);
				phoneNumberValue.setVerticalAlignment(Element.ALIGN_CENTER);
				phoneNumberValue.setBackgroundColor(BaseColor.WHITE);
				phoneNumberValue.setExtraParagraphSpace(5f);
				table.addCell(phoneNumberValue);
				
			}
			
			document.add(table);
			document.close();
			writer.close();
			return true;	
			
		}catch(Exception e) {
			
			
		}
		
		
		return false;
	}

	@Override
	public boolean createExcel(List<Employee> employees, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		
		
		String filePath = context.getRealPath("/resources/reports");
		File file = new File(filePath);
		boolean exists = new File(filePath).exists();
		if(!exists) {
			new File(filePath).mkdirs();
			
		}
		
		try {
			FileOutputStream outputStream = new FileOutputStream(file+"/"+"employees"+".xls");
			HSSFWorkbook workbook = new HSSFWorkbook();
			HSSFSheet workSheet = workbook.createSheet("Employees");
			workSheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle = workbook.createCellStyle();
			
			headerCellStyle.setFillBackgroundColor(HSSFColor.WHITE.index);
			//headerCellStyle.setFillBackgroundColor(HSSFColor.BLUE.index);
			
			headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			//headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
			
			HSSFRow headerRow = workSheet.createRow(0);
			
			HSSFCell firstName = headerRow.createCell(0);
			firstName.setCellValue("First Name");
			firstName.setCellStyle(headerCellStyle);
			
			HSSFCell lastName = headerRow.createCell(1);
			lastName.setCellValue("Last Name");
			lastName.setCellStyle(headerCellStyle);
			
			HSSFCell email = headerRow.createCell(2);
			email.setCellValue("Email");
			email.setCellStyle(headerCellStyle);
			
			HSSFCell phoneNumber = headerRow.createCell(3);
			phoneNumber.setCellValue("Phone Number");
			phoneNumber.setCellStyle(headerCellStyle);
			
			
			
			//create values
			
			
			int i=1;
			
			for(Employee employee: employees) {
				
				HSSFRow bodyRow = workSheet.createRow(i);
				
				HSSFCellStyle bodyCellStyle = workbook.createCellStyle();
				bodyCellStyle.setFillForegroundColor(HSSFColor.WHITE.index);
				
				HSSFCell firstNameValue = bodyRow.createCell(0);
				firstNameValue.setCellValue(employee.getFirstName());
				firstNameValue.setCellStyle(bodyCellStyle);
				
				HSSFCell lastNameValue = bodyRow.createCell(1);
				lastNameValue.setCellValue(employee.getLastName());
				lastNameValue.setCellStyle(bodyCellStyle);
				
				HSSFCell emailValue = bodyRow.createCell(2);
				emailValue.setCellValue(employee.getEmail());
				emailValue.setCellStyle(bodyCellStyle);
				
				HSSFCell phoneNumberValue = bodyRow.createCell(3);
				phoneNumberValue.setCellValue(employee.getPhoneNumber());
				phoneNumberValue.setCellStyle(bodyCellStyle);
				
				i++;
			
			}
			workbook.write(outputStream);
			outputStream.flush();
			outputStream.close();
			return true;
			
			
			
			
		}catch(Exception e) {
			
			return false;
			
		}
		
		
		
	}

}
