package net.idealful.util;

import java.text.SimpleDateFormat;
import java.util.Date;

public class CommonUtil {
	public static final String CONTEXT_PATH = "/demoSyncAndAsync";

	/**
	 * <pre>
	 * Check if a certain string is null or empty.
	 * 
	 * Default : false
	 * </pre>
	 * 
	 * @param str
	 * @return boolean
	 */
	public boolean isNullOrEmpty(String str) {
		boolean isYn = false;

		if (str == null) {
			isYn = true;
		}

		if ("".equals(str)) {
			isYn = true;
		}

		return isYn;
	}

	/**
	 * <pre>
	 * Get date and time string by pattern parameter.
	 * 
	 * Default : yyyy-MM-dd HH:mm:ss
	 * </pre>
	 * 
	 * @param pattern
	 * @return String
	 */
	public String getDateTime(String pattern) {
		if (isNullOrEmpty(pattern)) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}

		return new SimpleDateFormat(pattern).format(new Date());
	}
}
