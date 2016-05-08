package core.utils;


import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DateUtils {
	
	private static final Logger logger = LoggerFactory.getLogger(DateUtils.class);
	
	public static String dateFormatMySqlDB = "yyyy-MM-dd HH:mm:ss";
	public static String dateFormatMySqlDBEqual = "yyyy-MM-dd";
	public static String dateForFilter = "MMddyyyy";
	public static String dateForJson = "yyyyMMddHHmm";
	public static String dateFormatToES = "yyyy-MM-dd'T'HH:mm:ss.SSSX";
	public static String oracleFormat = "dd/MM/yy";
	public static String dateFormatSyncBatch = "ddMMyyyyy HH:mm:ss";
	
	/**
	 * Add Calendar.* and return a string with the format you need what to add
	 * for example :Calendar.DAY_OF_MONTH
	 * 
	 * @param format
	 * @param monthToSubstract
	 * @param whatToAdd
	 * @return
	 */
	public static String getDateSubstract(String format, int timeToAdd, int whatToAdd) {
		// "yyyy-MM-dd"
		SimpleDateFormat sdfFrom = new SimpleDateFormat(format);
		Calendar currentTime = Calendar.getInstance();
		currentTime.add(whatToAdd, timeToAdd);
		return sdfFrom.format(currentTime.getTime());
	}
	
	public static String getDateSubstractForElastic(String format, int timeToAdd, int whatToAdd) {
		// "yyyy-MM-dd"
		SimpleDateFormat sdfFrom = new SimpleDateFormat(format);
		Calendar currentTime = Calendar.getInstance();
		currentTime.add(whatToAdd, timeToAdd);
		currentTime.add(Calendar.HOUR_OF_DAY, -3);
		return sdfFrom.format(currentTime.getTime());
	}
	
	public static String getCurrentDateFormatdB() {
		// "yyyy-MM-dd"
		SimpleDateFormat sdfFrom = new SimpleDateFormat(dateFormatMySqlDB);
		Calendar currentTime = Calendar.getInstance();
		return sdfFrom.format(currentTime.getTime());
	}
	
	public static String converDateToDbFormat(Date date) {
		// "yyyy-MM-dd"
		SimpleDateFormat sdfFrom = new SimpleDateFormat(dateFormatMySqlDB);
		return sdfFrom.format(date.getTime());
	}
	
	public static Date getDateSubstract(int timeToAdd, int whatToAdd) {
		Calendar currentTime = Calendar.getInstance();
		currentTime.add(whatToAdd, timeToAdd);
		return currentTime.getTime();
	}
	
	/** 
	 * @param format
	 * @return current date
	 */
	public static long getDateAsLong(Date date) {
		return date.getTime();
	}
	
	/**
	 * @param format
	 * @return current date
	 */
	public static String getCurrentDate(String format) {
		SimpleDateFormat sdfFrom = new SimpleDateFormat(format);
		Calendar currentTime = Calendar.getInstance();
		return (sdfFrom.format(currentTime.getTime()));
	}
	
	public static Date getCurrentDate() {
		Calendar currentTime = Calendar.getInstance();
		return currentTime.getTime();
	}
	
	public static Date getDateFromString(String format, String dateStr) {
		
		DateFormat formatter = new SimpleDateFormat(format);
		Date date = null;
		try {
			date = (Date) formatter.parse(dateStr);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		return date;
	}
	
	// public static String getDate(Date date, String dateFormat) {
	// DateFormat formatter = new SimpleDateFormat(dateFormat);
	// return formatter.format(date);
	// }
	public static String getDate(Date date, String dateFormat) {
		DateFormat formatter = new SimpleDateFormat(dateFormat);
		return formatter.format(date);
	}
	
	public static boolean isEqualGreater(Date dateBefore, Date dateAfter) {
		int com = dateBefore.compareTo(dateAfter);
		
		if (com <= 0) {
			return true;
		}
		return false;
	}
	
	public static boolean isBiggerDate(Date dateBefore, Date dateAfter) {
		int com = dateBefore.compareTo(dateAfter);
		
		if (com < 0) {
			return true;
		}
		return false;
	}
	
	/**
	 * return max date from array of date if there is no date will throw
	 * exception
	 * 
	 * @param date array of date
	 * @return max date
	 * @throws Exception if array date is null
	 */
	public static Date getLastDate(ArrayList<Date> date) throws Exception {
		Date maxDate = date.get(0);
		if (maxDate == null) {
			throw new Exception("[ERROR] Date is null please chech log");
		}
		for (int i = 0; i < date.size(); i++) {
			if (isBiggerDate(maxDate, date.get(i))) {
				maxDate = date.get(i);
			}
		}
		return maxDate;
	}
	
	/**
	 * 
	 * @param dateBefore
	 * @param dateAfter
	 * @return true if equal
	 */
	public static boolean isEqualDate(Date dateBefore, Date dateAfter) {
		logger.info("run method isEqualDate");
		String dateBeforeStr = getDate(dateBefore, dateFormatMySqlDB);
		String dateAfterStr = getDate(dateAfter, dateFormatMySqlDB);
		System.out.println(dateBefore);
		System.out.println(dateAfter);
		logger.info("date before = " + dateAfterStr);
		logger.info("date after = " + dateAfterStr);
		int com = dateBeforeStr.compareTo(dateAfterStr);
		if (com == 0) {
			return true;
		}
		return false;
	}
}
