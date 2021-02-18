package com.zoopla.util;

public final class Constants {
	
	private final static String RESOURCEPATH=System.getProperty("user.dir");
	private final static String CONFIGPATH=getResourcepath()+"/src/test/resources/config.properties";
	private final static String EXCELPATH=getResourcepath()+"/src/test/resources/ExcelData/OrangeHRM.xlsx";
	private final static int EXPLICITWAIT=50;
	
	public static String getConfigpath() {
		return CONFIGPATH;
	}

	public static int getExplicitwait() {
		return EXPLICITWAIT;
	}
	
	public static String getResourcepath() {
		return RESOURCEPATH;
	}

	public static String getExcelpath() {
		return EXCELPATH;
	}

}
