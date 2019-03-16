package com.koishman.stocks.service;

import com.google.common.collect.Lists;
import com.koishman.stocks.model.sotck.Stock;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class ExcelWriter<T> {

	private static List<String> headerColumns = Lists.newArrayList("Symbol", "Company Name", "Price", "Time");
	private static List<Stock> employees = new ArrayList<>();

	// Initializing employees data to insert into the excel file
	static {
		employees.add(new Stock("Rajeev Singh", "rajeev@example.com", "23.56", "asdad"));

		employees.add(new Stock("Thomas cook", "thomas@example.com", "23.56", "asdad"));

		employees.add(new Stock("Steve Maiden", "steve@example.com", "23.56", "asdad"));
	}

	public static void main(String[] args) throws IOException, org.apache.poi.openxml4j.exceptions.InvalidFormatException {
		ExcelWriter<Stock> employeeExcelWriter = new ExcelWriter<>();
		// Create a Workbook
		File file = new File("stock.xlsx");
		Workbook workbook;
		if (file.exists()) {
			workbook = WorkbookFactory.create(file);
		} else {
			workbook = new XSSFWorkbook();
		}
		FileOutputStream outs = employeeExcelWriter.createFile(file);
		Sheet sheet = employeeExcelWriter.createSheet(workbook, "12", headerColumns);
		Sheet sheet2 = employeeExcelWriter.createSheet(workbook, "13", headerColumns);
		boolean today = employeeExcelWriter.writeToFile(workbook, sheet, outs, employeeExcelWriter::createEmployee, employees);
		System.out.println(today);
		outs = employeeExcelWriter.createFile(file);
		boolean today2 = employeeExcelWriter.writeToFile(workbook, sheet2, outs, employeeExcelWriter::createEmployee, employees);
		System.out.println(today2);
		outs = employeeExcelWriter.createFile(file);
		today = employeeExcelWriter.writeToFile(workbook, sheet, outs, employeeExcelWriter::createEmployee, employees);
		System.out.println(today);
		outs = employeeExcelWriter.createFile(file);
		today = employeeExcelWriter.writeToFile(workbook, sheet2, outs, employeeExcelWriter::createEmployee, employees);
		System.out.println(today2);
		workbook.close();
		return;
	}

	private boolean writeToFile(Workbook workbook, Sheet sheet, FileOutputStream outs, RowCreator<T> rowCreator, List<T> list) throws IOException {
		rowCreator.createRows(sheet, list);
		for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
			sheet.autoSizeColumn(i);
		}
		workbook.write(outs);
		outs.close();
		return true;
	}

	private FileOutputStream createFile(File file) throws FileNotFoundException {
		return new FileOutputStream(file, true);
	}

	private Sheet createSheet(Workbook workbook, String sheetName, List<String> headerColumns) {
		Sheet sheet = workbook.createSheet(sheetName);

		Font headerFont = workbook.createFont();
		headerFont.setBold(true);
		headerFont.setFontHeightInPoints((short) 14);
		headerFont.setColor(IndexedColors.RED.getIndex());

		CellStyle headerCellStyle = workbook.createCellStyle();
		headerCellStyle.setFont(headerFont);

		Row headerRow = sheet.createRow(0);

		for (int i = 0; i < headerColumns.size(); i++) {
			Cell cell = headerRow.createCell(i);
			cell.setCellValue(headerColumns.get(i));
			cell.setCellStyle(headerCellStyle);
		}
		return sheet;
	}

	private void createEmployee(Sheet sheet, List<Stock> batchStocks) {
		int lastRowNum = sheet.getLastRowNum() + 1;
		for (Stock batchStock : batchStocks) {
			Row row = sheet.createRow(lastRowNum++);

			row.createCell(0).setCellValue(batchStock.getSymbol());

			row.createCell(1).setCellValue(batchStock.getCompanyName());

			row.createCell(2).setCellValue(batchStock.getValue());
			row.createCell(3).setCellValue(batchStock.getTime());
		}
	}
}

@FunctionalInterface
interface RowCreator<T> {

	void createRows(Sheet sheet, List<T> objects);
}