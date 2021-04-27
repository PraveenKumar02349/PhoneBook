package com.angler.phonebook.Utils;

import java.util.Arrays;
import java.util.List;
import java.util.regex.Pattern;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;


public class Utils {
	public static void log(String message) {
		Logger logger = LoggerFactory.getLogger(Utils.class);
		logger.info(message);
		logger.info("----------------------------");
	}

	public static <T> T jsonToPojo(String data, T className) {
		T generalObject = (T) new Gson().fromJson(data, className.getClass());
		return generalObject;
	}
	
	public static <T> List<T> stringToListParser(String data, Class<T[]> tClass) {
		T[] arr = new Gson().fromJson(data, tClass);
		return Arrays.asList(arr);
	}

	public static <T> String listToStringParser(List<T> list) {
		return new Gson().toJson(list);
	}

	public static <T> String pojoToStringParser(T data) {
		return new Gson().toJson(data);
	}

	public static boolean isNull(String data) {
		if (data == null)
			return true;
		return false;
	}

	public static boolean isNullEmpty(String data) {
		if (isNull(data))
			return true;
		if (data.isEmpty())
			return true;
		return false;
	}

	public static boolean isValidEmail(String email) {
		String regex = "^[\\w-_\\.+]*[\\w-_\\.]\\@([\\w]+\\.)+[\\w]+[\\w]$";
		Pattern pat = Pattern.compile(regex);
		if (isNullEmpty(email))
			return false;
		return pat.matcher(email).matches();
	}
	
	public static String getConcatString(Object[] arr, String delimeter) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : arr)
			sb.append(obj.toString()).append(delimeter);
		return sb.substring(0, sb.length() - delimeter.length());
	}

	public static String getWhereString(Object[] arr, String delimeter) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : arr)
			sb.append(obj.toString()).append((arr.length == 1) ? delimeter : (delimeter + "AND "));
		return sb.substring(0, sb.length() - ((arr.length == 1) ? "" : "AND ").length());
	}
	
	public static String getUpdateString(Object[] arr, String delimeter) {
		StringBuilder sb = new StringBuilder();
		for (Object obj : arr)
			sb.append(obj.toString()).append((arr.length == 1) ? delimeter : (delimeter + ", "));
		return sb.substring(0, sb.length() - ((arr.length == 1) ? "" : ", ").length());
	}
}
