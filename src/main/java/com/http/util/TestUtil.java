package com.http.util;

import java.text.ParseException;

public abstract class TestUtil {

	public static void main(String[] args) {
		CalendarTool calendarTool = new CalendarTool();
		try {
			CalendarTool.getBeforeMinutes1("s ", 5);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
