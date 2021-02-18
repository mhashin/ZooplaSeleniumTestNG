package com.zoopla.util;

import java.io.FileInputStream;
import java.util.Properties;

public final class ReadPropertyFile {
	
	public static String get(String key) {
		
		String propertyValue=null;
		Properties prop=new Properties();
		try {
			FileInputStream fis=new FileInputStream(Constants.getConfigpath());
			prop.load(fis);
			propertyValue=prop.getProperty(key);
			if(propertyValue==null) {
				throw new Exception("Property named "+propertyValue+ "not found");
			}
			} catch (Exception e) {
				e.printStackTrace();
			}
		return propertyValue;
			
		} 
}
