package com.shahnawaz.todo;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.shahnawaz.todo.entities.Todo;



public class ExportTodos {
	private XSSFWorkbook workbook;
	private XSSFSheet sheet;
	List<Todo> todos;
	public ExportTodos(List<Todo> todos) {
		super();
		this.todos = todos;
		workbook=new XSSFWorkbook();
		sheet=workbook.createSheet("Todos");
		writeHeader();
		
		
		
	}
	public void writeHeader() {
		Row row=sheet.createRow(1);
		
		Cell todocell=row.createCell(1);
		todocell.setCellValue("Todo");
		
		Cell completedcell=row.createCell(2);
		completedcell.setCellValue("Completed");
		
		Cell created_date=row.createCell(3);
		created_date.setCellValue("created_date");
		
		Cell updated_date=row.createCell(4);
		updated_date.setCellValue("updated_date");
		writeDataRow();
		
	}
	
	void writeDataRow(){
		int rowindex=2;
		CreationHelper creationhelper=workbook.getCreationHelper();
		
		for(Todo todo:todos) {
			int colindex=1;
			Row row=sheet.createRow(rowindex);
			
			Cell todocell=row.createCell(colindex++);
			todocell.setCellValue(todo.getTaskString());
			
			Cell completedcell=row.createCell(colindex++);
			completedcell.setCellValue(todo.getCompletedString());
			
			Cell created_date=row.createCell(colindex++);
			created_date.setCellValue(todo.getCreated_date());
			CellStyle style=workbook.createCellStyle();
			style.setDataFormat(creationhelper.createDataFormat().getFormat("dd-mm-yy hh:mm;ss"));
			created_date.setCellStyle(style);
			//created_date.setCellValue(todo.getCreated_date());
			
			Cell updated_date=row.createCell(colindex++);
			updated_date.setCellValue(todo.getUpdated_date());
			updated_date.setCellStyle(style);
			rowindex++;
			
		}
	}
	public void export(HttpServletResponse response) throws IOException {
		//public ByteArrayInputStream export(HttpServletResponse response) throws IOException {
			//ByteArrayOutputStream outputStream=new ByteArrayOutputStream();
		ServletOutputStream stream=response.getOutputStream();
		workbook.write(stream);
		//byte[] arr=outputStream.toByteArray();
		workbook.close();
		//outputStream.flush();
		//outputStream.close();
		//response.setContentType("application/vnd.ms-excel");
		//response.getWriter().write(10);
		
		stream.close();
		//return new ByteArrayInputStream(arr);
		
	}
	
}
