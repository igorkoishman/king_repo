package core.utils;

public class StringUtils {

	public static boolean isNull(String string) {
		return string == null ? true : false;
	}

	public static boolean isEmpty(String string) {
		return string.length() == 0 ? true : false;
	}
}
