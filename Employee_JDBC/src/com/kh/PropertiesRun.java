package com.kh;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesRun {

	public static void main(String[] args) {
		
		Properties prop = new Properties();
		prop.setProperty("A", "B");
		
		try {
			// prop.store(new FileOutputStream("driver.properties"), "setting for DBMS");
			prop.storeToXML(new FileOutputStream("employee-mapper.xml"), "EMPLOYEE SQL");

		} catch (IOException e) {
			e.printStackTrace();
		}
		

	}

}
