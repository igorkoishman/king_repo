package com.koishman.stocks.service;

import com.google.common.collect.Lists;
import com.koishman.stocks.model.sotck.Stock;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
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
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;

@Component
public class StockWriterService {

	private ExcelWriter<Stock> excelWriter;
	@Autowired
	private StockContext stockContext;

	private static final List<String> headerColumns = Lists.newArrayList("Symbol", "Company Name", "Price", "Time");

	@PostConstruct
	public void init() {
		this.excelWriter = new ExcelWriter<>(headerColumns);
	}

	@Scheduled(initialDelay = 10000, fixedDelay = 600000)
	public void run() {
		System.out.println("write to file");
		Map<String, ConcurrentLinkedQueue<Stock>> queue = stockContext.getQueue();
		for (Map.Entry<String, ConcurrentLinkedQueue<Stock>> entry : queue.entrySet()) {
			List<Stock> stocks = Lists.newArrayList();
			while (entry.getValue().size() > 0) {
				stocks.add(entry.getValue().poll());
			}
			try {
				write(stocks, entry.getKey());
			} catch (IOException e) {
				e.printStackTrace();
			} catch (InvalidFormatException e) {
				e.printStackTrace();
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
		File file = new File(directoryName + "/" + date + ".xlsx");
		FileOutputStream outs = createFile(file);
		Sheet sheet;
		Workbook workbook;
		if (file.exists() && file.length() != 0) {
			workbook = WorkbookFactory.create(file);
			sheet = workbook.getSheet(day);
			if (sheet == null) {
				excelWriter.createSheet(workbook, day);
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

			row.createCell(2).setCellValue(batchStock.getValue());
			row.createCell(3).setCellValue(batchStock.getTime());
		}
	}

	private FileOutputStream createFile(File file) throws FileNotFoundException {
		return new FileOutputStream(file, true);
	}
}
