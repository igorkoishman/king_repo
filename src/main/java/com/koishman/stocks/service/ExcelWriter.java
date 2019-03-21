package com.koishman.stocks.service;

import org.apache.poi.ss.usermodel.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class ExcelWriter<T> {

	private List<String> headerColumns;

	public ExcelWriter(List<String> headerColumns) {
		this.headerColumns = headerColumns;
	}

	public boolean writeToFile(Workbook workbook, Sheet sheet, FileOutputStream outs, RowCreator<T> rowCreator, List<T> list) throws IOException {
		rowCreator.createRows(sheet, list);
		for (int i = 0; i < sheet.getRow(0).getLastCellNum(); i++) {
			sheet.autoSizeColumn(i);
		}
		workbook.write(outs);
		outs.close();
		return true;
	}

	public Sheet createSheet(Workbook workbook, String sheetName) {
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

}

@FunctionalInterface
interface RowCreator<T> {

	void createRows(Sheet sheet, List<T> objects);
}