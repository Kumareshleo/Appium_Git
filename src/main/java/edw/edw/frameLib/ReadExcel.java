package edw.edw.frameLib;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import org.apache.poi.xssf.usermodel.XSSFCell;

import org.apache.poi.xssf.usermodel.XSSFRow;

import org.apache.poi.xssf.usermodel.XSSFSheet;

import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ReadExcel {

	private static XSSFSheet ExcelWSheet;

	private static XSSFWorkbook ExcelWBook;

	private static XSSFCell Cell;

	private static XSSFRow Row;

	// This method is to set the File path and to open the Excel file, Pass
	// Excel Path and Sheetname as Arguments to this method

	public static void setExcelFile(String fileName) {

		try {

			String pathDataFile = System.getProperty("user.dir") + "\\testData\\" + fileName + ".xlsx";

			// Open the Excel file

			FileInputStream ExcelFile = new FileInputStream(pathDataFile);

			// Access the required test data sheet

			ExcelWBook = new XSSFWorkbook(ExcelFile);

			ExcelWSheet = ExcelWBook.getSheet("Sheet1");
			ExcelFile.close();
		} catch (Exception e) {

			try {
				throw (e);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}

	}

	public void closeExcel() {

	}

	
	
	public static Object[][] getMultipleData() throws Exception
	{
		
		String[][] tabArray = null;

		   try {

			   int startRow = 1;

			   int startCol = 1;

			   int ci,cj;

			   int totalRows = ExcelWSheet.getLastRowNum();
			  
			   // you can write a function as well to get Column count

			   int totalCols = ExcelWSheet.getRow(0).getLastCellNum();
			 

			   tabArray=new String[totalCols-1][totalRows];

			   ci=0;

			   for (int i=startCol;i<totalCols;i++, ci++) {           	   

				  cj=0;

				   for (int j=startRow;j<=totalRows;j++, cj++){

					   tabArray[ci][cj]=getCellData(j,i);

						}

					}

				}

			catch (FileNotFoundException e){

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

				}

			catch (IOException e){

				System.out.println("Could not read the Excel sheet");

				e.printStackTrace();

				}

			return(tabArray);

			}
	// This method is to read the test data from the Excel cell, in this we are
	// passing parameters as Row num and Col num

	public static String getCellData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue().replaceAll("'", "");

			return CellData;

		} catch (Exception e) {

			return "";

		}

	}

	public static ArrayList<String> getData(int RowNum) throws Exception {

		ArrayList<String> data = new ArrayList<String>();
		String CellData;
		for (int start = 1; start < 30; start++) {
			try {

				Cell = ExcelWSheet.getRow(RowNum).getCell(start);

				CellData = Cell.getStringCellValue().replaceAll("'", "");

			}

			catch (Exception e) {
				CellData = "";

			}
			data.add(CellData);

		}

		return data;
	}

	public static String getCellDataWithoutReplacement(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		} catch (Exception e) {

			return "";

		}

	}

	public static String getCellQueryData(int RowNum, int ColNum) throws Exception {

		try {

			Cell = ExcelWSheet.getRow(RowNum).getCell(ColNum);

			String CellData = Cell.getStringCellValue();

			return CellData;

		} catch (Exception e) {

			return "";

		}

	}

	// This method is to write in the Excel cell, Row num and Col num are the
	// parameters

	public static void setCellData(String Result, int RowNum, int ColNum, String fileName) throws Exception {

		try {

			String pathDataFile = System.getProperty("user.dir") + "\\testData\\" + fileName + ".xlsx";

			Row = ExcelWSheet.getRow(RowNum);

			Cell = Row.getCell(ColNum, org.apache.poi.ss.usermodel.Row.RETURN_BLANK_AS_NULL);

			if (Cell == null) {

				Cell = Row.createCell(ColNum);

				Cell.setCellValue(Result);

			} else {

				Cell.setCellValue(Result);

			}

			// Constant variables Test Data path and Test Data file name

			FileOutputStream fileOut = new FileOutputStream(pathDataFile);

			ExcelWBook.write(fileOut);

			fileOut.flush();

			fileOut.close();

		} catch (Exception e) {

			throw (e);

		}

	}

}