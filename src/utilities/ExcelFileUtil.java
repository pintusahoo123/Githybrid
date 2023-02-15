package utilities;

import java.awt.image.IndexColorModel;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputFilter.Status;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCellStyle;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelFileUtil {
	XSSFWorkbook wb;
	// constructor for reading excel file path
	public ExcelFileUtil (String Readexcel) throws Throwable
	{
		FileInputStream fi = new FileInputStream(Readexcel);
		wb = new XSSFWorkbook(fi);
	}
	// method for counting no. of rows        // whenever we want no of rows calls rowCount
	public int rowCount (String Sheetname)
	{
		return wb.getSheet(Sheetname).getLastRowNum();
	}
	// method for getting cell data            // whenever we want cell data call getCellData
	public String getCellData (String Sheetname,int row,int column)
	{
		String data ="";
		if(wb.getSheet(Sheetname).getRow(row).getCell(column).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
			int celldata = (int) wb.getSheet(Sheetname).getRow(row).getCell(column).getNumericCellValue();
			data = String.valueOf(celldata);
		}
		else
		{
			data = wb.getSheet(Sheetname).getRow(row).getCell(column).getStringCellValue();
		}
		return data;
	}
	// method for writing
	public void setCellData(String Sheetname,int row,int column,String status,String writeExcel) throws Throwable
	{
		// get sheet from workbook
		XSSFSheet ws = wb.getSheet(Sheetname);
		// get row from sheet
		XSSFRow rowNum = ws.getRow(row);
		// create cell in a row
		XSSFCell cell = rowNum.createCell(column);
		// write status
		cell.setCellValue(status);
		if(status.equalsIgnoreCase("pass"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.GREEN.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if (status.equalsIgnoreCase("fail"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.RED.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		else if (status.equalsIgnoreCase("blocked"))
		{
			XSSFCellStyle style = wb.createCellStyle();
			XSSFFont font = wb.createFont();
			font.setColor(IndexedColors.BLUE.getIndex());
			font.setBold(true);
			font.setBoldweight(XSSFFont.BOLDWEIGHT_BOLD);
			style.setFont(font);
			rowNum.getCell(column).setCellStyle(style);
		}
		FileOutputStream fo = new FileOutputStream(writeExcel);
		wb.write(fo);
	}

	/*public static void main(String[] args) throws Throwable {
		ExcelFileUtil xl = new ExcelFileUtil("D:/sampleexcel.xlsx");
		int rc = xl.rowCount("empdata");
		System.out.println(rc);
		for (int i=1; i<=rc; i++)
		{
			String fname = xl.getCellData("empdata",i, 0);
			String lname = xl.getCellData("empdata", i, 1);
			String empid = xl.getCellData("empdata", i, 2);
			System.out.println(fname+"   "+lname+"    "+empid);
			//xl.setCellData("empdata", i, 3, "pass", "D:/resultsexcel.xlsx");
			//xl.setCellData("empdata", i, 3, "fail", "D:/resultsexcel.xlsx");
			xl.setCellData("empdata", i, 3, "blocked", "D:/resultsexcel.xlsx");
		}
		*/

	}


