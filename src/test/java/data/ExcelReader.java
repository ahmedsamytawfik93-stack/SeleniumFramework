package data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelReader {
	static FileInputStream stream = null;
	
	public FileInputStream getFileInputStream() {
		String filePath = System.getProperty("user.dir") + "\\src\\test\\java\\data\\UserData.xlsx";
		File srcFile = new File(filePath);
		
		try {
			stream = new FileInputStream(srcFile);
		} catch (FileNotFoundException e) {
			System.out.println("Error occurred: " + e.getMessage());
			System.exit(0);
		}
		
		return stream;
	}
	
	public Object[][] getExcelData() throws IOException {
		stream = getFileInputStream();
		
		XSSFWorkbook wb = new XSSFWorkbook(stream);
		XSSFSheet sheet = wb.getSheetAt(0);
			
		int totalNumberOfRows = sheet.getLastRowNum()+1;
		int totalNumberOfCols = 4;
			
		String[][] arrayExcelData = new String[totalNumberOfRows][totalNumberOfCols];
			
		for(int i=0; i < totalNumberOfRows; i++) {
			for(int j=0; j < totalNumberOfCols; j++) {
				XSSFRow row = sheet.getRow(i);
				arrayExcelData[i][j] = row.getCell(j).toString();
			}
		}
		wb.close();
		return arrayExcelData;
	}
}
