package testPackage;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReadWrite {

	public static void main(String[] args) throws IOException {
		readingExcel("./inputFiles/Students.xlsx");
		writeInExcel("./inputFiles/Students.xlsx", "Passed", 0, 1, 2);
	}
	
	public static void readingExcel(String file) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(file);
		Workbook workbook = null;
		if (file.endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(fileInputStream);
		}else if(file.endsWith(".xls")) {
			workbook = new HSSFWorkbook(fileInputStream);
		}
		Sheet sheet = workbook.getSheetAt(0);
		
		for (int i = 1; i <= sheet.getLastRowNum(); i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < row.getLastCellNum(); j++) {
				Cell cell = row.getCell(j);
				System.out.print(cell + " ");
			}
			System.out.println();
		}
		workbook.close();
		fileInputStream.close();
	}
	
	public static void writeInExcel(String path, String value, int sheetNumber, int rowNum, int cellNum) throws IOException {
		FileInputStream fileInputStream = new FileInputStream(path);
		Workbook workbook = null;
		if (path.endsWith(".xlsx")) {
			workbook = new XSSFWorkbook(fileInputStream);
		}else if(path.endsWith(".xls")) {
			workbook = new HSSFWorkbook(fileInputStream);
		}
		Sheet sheet = workbook.getSheetAt(sheetNumber);
		Row row = sheet.getRow(rowNum);
		Cell cell = row.getCell(cellNum);
		cell.setCellValue(value);
		
		FileOutputStream fileOutputStream = new FileOutputStream(path);
		workbook.write(fileOutputStream);
		workbook.close();
		fileOutputStream.close();
		fileInputStream.close();
	}
}
