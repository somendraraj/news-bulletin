package com.he.newsb.common;

import java.util.Calendar;
import java.util.Date;

public class DateUtil {

	public static Date getFormatedDate(long milliSeconds) {
		// DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Calendar calendar = Calendar.getInstance();
		calendar.setTimeInMillis(milliSeconds);
		return calendar.getTime();
	}

}
