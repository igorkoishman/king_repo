package com.koishman.stocks.service;

import com.google.common.collect.Lists;
import com.koishman.stocks.model.sotck.Stock;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingDeque;

@Component
public class StockWriterService {

	private ExcelWriter<Stock> excelWriter;
	@Autowired
	private StockContext stockContext;

	private static final List<String> headerColumns = Lists.newArrayList("Symbol", "Company Name", "Price", "Volume", "Time");

	@PostConstruct
	public void init() {
		this.excelWriter = new ExcelWriter<>(headerColumns);
	}

	@Scheduled(initialDelay = 10000, fixedDelay = 30000)
	public void run() {
		Set<String> symbols = stockContext.getSymbols();
		if (!symbols.isEmpty()) {
			System.out.println("write to file");
			for (String symbol : symbols) {
				List<Stock> stocks = Lists.newArrayList();
				BlockingDeque<Stock> symbolStockData = stockContext.getSymbolStockData(symbol);
				while (!symbolStockData.isEmpty()) {
					stocks.add(symbolStockData.poll());
				}
				try {
					write(stocks, symbol);
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InvalidFormatException e) {
					e.printStackTrace();
				}

			}

		}

	}

	public void write(List<Stock> stockList, String symbol) throws IOException, InvalidFormatException {
		String directoryName = symbol;
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-yyyy");
		LocalDate localDate = LocalDate.now();
		String date = localDate.format(formatter);
		File directory = new File(directoryName);
		if (!directory.exists()) {
			directory.mkdir();
		}
		DateTimeFormatter formatterDate = DateTimeFormatter.ofPattern("dd");
		String day = localDate.format(formatterDate);
		File file = new File(directoryName + "/" + symbol + "_" + date + ".xlsx");
		FileOutputStream outs = createFile(file);
		Sheet sheet;
		Workbook workbook;
		if (file.exists() && file.length() != 0) {
			workbook = WorkbookFactory.create(file);
			sheet = workbook.getSheet(day);
			if (sheet == null) {
				sheet = excelWriter.createSheet(workbook, day);
			}
		} else {
			workbook = new XSSFWorkbook();
			sheet = excelWriter.createSheet(workbook, day);
		}

		boolean today = excelWriter.writeToFile(workbook, sheet, outs, this::createStockRow, stockList);
		System.out.println(today);
		workbook.close();
	}

	private void createStockRow(Sheet sheet, List<Stock> batchStocks) {
		int lastRowNum = sheet.getLastRowNum() + 1;
		for (Stock batchStock : batchStocks) {
			Row row = sheet.createRow(lastRowNum++);
			row.createCell(0).setCellValue(batchStock.getSymbol());
			row.createCell(1).setCellValue(batchStock.getCompanyName());
			Cell cell = row.createCell(2);
			cell.setCellType(CellType.NUMERIC);
			cell.setCellValue(batchStock.getValue());
			Cell cellVolume = row.createCell(3);
			cellVolume.setCellType(CellType.NUMERIC);
			cellVolume.setCellValue(batchStock.getVolume());
			row.createCell(4).setCellValue(batchStock.getTime());
		}
	}

	private FileOutputStream createFile(File file) throws FileNotFoundException {
		return new FileOutputStream(file, true);
	}
}
