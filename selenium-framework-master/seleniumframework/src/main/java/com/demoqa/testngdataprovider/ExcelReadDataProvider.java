package com.demoqa.testngdataprovider;

import java.util.Iterator;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.demoqa.exceldtos.EmployeeLogin;

public class ExcelReadDataProvider implements Iterator<Object[]> {
	Logger logger = LogManager.getLogger(ExcelReadDataProvider.class);
	private int rowIndex = 2;
	private final ExcelReader excelRead;
	private final String sheetName;

	public ExcelReadDataProvider(String fileName, String sheetName) {
		excelRead = new ExcelReader(fileName);
		this.sheetName = sheetName;
	}

	@Override
	public boolean hasNext() {
		logger.debug("row index" + rowIndex);
		logger.debug("number of rows in" + excelRead.getRowCount(sheetName));
		return (rowIndex <= excelRead.getRowCount(sheetName));
	}

	@Override
	public Object[] next() {
		List<String> lst = excelRead.getRowData(sheetName, rowIndex);
		EmployeeLogin emp = new EmployeeLogin();
		if (lst.size() > 0) {
			emp.setEmployeeLogin(lst.get(0));
			emp.setEmployeedPassword(lst.get(1));
			emp.setIsValidUser(lst.get(2));
		}
		rowIndex++;
		return new Object[] { emp };
	}

}
