package com.seleniumpractice.filereader;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.NumberToTextConverter;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader
{
	String filePath = "";
	String workSheetName = "";
	String testName = "";
	
	public ExcelReader(String excelPath, String excelSheetName, String testname)
	{
		this.filePath = excelPath;
		this.workSheetName = excelSheetName;
		this.testName = testname;
	}
	
	public List<HashMap<String, String>> getExcelData()
	{	
		List<HashMap<String, String>> listMap = new ArrayList<HashMap<String, String>>();
		HashMap<String, String> data = null;
		ArrayList<String> ColNames = new ArrayList<String>();
		FileInputStream file = null;
		XSSFWorkbook workbook = null;
		try {
			file = new FileInputStream(filePath);
			workbook = new XSSFWorkbook(file);
		} catch (Exception e1) {
			e1.printStackTrace();
		}
		int sheetNo = workbook.getNumberOfSheets();
		for(int i=0; i<sheetNo; i++)
		{
			if(workbook.getSheetName(i).equalsIgnoreCase(workSheetName))
			{
				XSSFSheet sheetName = workbook.getSheetAt(i);
				//Identifying testcases column
				Iterator<Row> rows = sheetName.iterator();
				Row firstRow = rows.next();
				Iterator<Cell> cell = firstRow.cellIterator();
				int columnCount=0;
				int columnNo = 0;
				while(cell.hasNext())
				{
					Cell value = cell.next();
					if(value.getStringCellValue().equalsIgnoreCase("TestCase"))
					{
						columnNo=columnCount;
					}
					//To get Column names in arraylist
					if(value.getCellType() ==CellType.STRING)
					{
						ColNames.add(columnCount, value.getStringCellValue());
					}
					else if(value.getCellType()==CellType.NUMERIC)
					{
						String cellData = NumberToTextConverter.toText(value.getNumericCellValue());
						ColNames.add(columnCount, cellData);
					}
					columnCount++;
				}
				
				while (rows.hasNext())
				{
					int columnKey = 0;
					data =  new HashMap<String, String>();
					Row row = rows.next();
					Iterator<Cell> cellN = row.cellIterator();
					boolean testfoundFlag = false;
					while (cellN.hasNext())
					{
						Cell cellValue = cellN.next();
						if (testfoundFlag)
						{
							if (cellValue.getCellType() == CellType.STRING)
							{
								data.put(ColNames.get(columnKey), cellValue.getStringCellValue());
							}
							else if (cellValue.getCellType() == CellType.NUMERIC)
							{
								data.put(ColNames.get(columnKey), NumberToTextConverter.toText(cellValue.getNumericCellValue()));
							}
						}
						
						if (ColNames.get(columnKey).equals("TestCase"))
						{
							if (cellValue.getStringCellValue().equals(testName))
							{
								data.put(ColNames.get(columnKey), testName);
								testfoundFlag = true;
							}
							else
							{
								break;
							}
						}
						
						columnKey++;
					}
					
					if (data.size() > 0)
					{
						listMap.add(data);
					}
				}
			}
		}
		//System.out.println(listMap);
	return listMap;
  }
	
	public static void main(String[] args) {
		ExcelReader reader = new ExcelReader("E:\\NK\\Selenium Projects\\TestNGFramework\\testdata\\excel\\testdata.xlsx", "STAG_LoginTest", "Verify that the user details are displayed");
		List<HashMap<String, String>> excelData = reader.getExcelData();
		for (HashMap<String, String> map : excelData)
		{
			for (Entry<String, String> entry : map.entrySet())
			{
				System.out.println("Key: [" + entry.getKey() + "] and Value: [" + entry.getValue() + "]");
			}
		}
	}
}
