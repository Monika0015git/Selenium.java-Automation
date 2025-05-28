package excel;

import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelUtils {
	public Sheet sheet;
	// String path = System.getProperty("user.dir")+"/excelData/userData.xlsx";

	public ExcelUtils(String filePath, String sheetName) throws IOException {
		FileInputStream fis = new FileInputStream(filePath);
		Workbook workbook = new XSSFWorkbook(fis);
		sheet = workbook.getSheet(sheetName);
		fis.close();
	}

	public Object[][] getData() {
		int rowCount = sheet.getPhysicalNumberOfRows();
		int colCount = sheet.getRow(0).getLastCellNum();

		Object[][] data = new Object[rowCount - 1][colCount];

		for (int i = 1; i < rowCount; i++) {
			Row row = sheet.getRow(i);
			for (int j = 0; j < colCount; j++) {
				Cell cell = row.getCell(j);
				data[i - 1][j] = getCellValue(cell);
			}
		}
		return data;
	}

	private String getCellValue(Cell cell) {
		if (cell == null) {
			return "";
		}
		try {
			return cell.getStringCellValue();
		} catch (IllegalStateException e) {
			return String.valueOf(cell.getNumericCellValue());
		}
	}

	public static Object[][] getCardDetailsByEmail(String excelPath, String sheetName, String email) {
		Object[][] data = null;

		try {
			FileInputStream fis = new FileInputStream(excelPath);
			Workbook workbook = new XSSFWorkbook(fis);
			Sheet sheet = workbook.getSheet(sheetName);

			for (int i = 0; i <= sheet.getLastRowNum(); i++) {
				Row row = sheet.getRow(i);

				// Check if the email in column 0 matches
				if (row != null && row.getCell(0) != null) {
					String emailInRow = row.getCell(0).getStringCellValue();

					if (emailInRow.equalsIgnoreCase(email)) {
						String cardName = row.getCell(1).getStringCellValue();
						String cardNumber = row.getCell(2).getStringCellValue();
						String cvc = row.getCell(3).getStringCellValue();
						String expMonth = row.getCell(4).getStringCellValue();
						String expYear = row.getCell(5).getStringCellValue();

						data = new Object[][] { { cardName, cardNumber, cvc, expMonth, expYear } };
						break; // stop after finding the match
					}
				}
			}

			workbook.close();
			fis.close();
		} catch (Exception e) {
			e.printStackTrace();
		}

		// If no match found, return empty data to skip the test
		if (data == null) {
			return new Object[0][];
		}

		return data;
	}

	public String[] getRowByName(String name) {
		int totalRows = sheet.getPhysicalNumberOfRows();
		int totalCols = sheet.getRow(0).getLastCellNum();

		for (int i = 1; i < totalRows; i++) {
			Row row = sheet.getRow(i);
			Cell nameCell = row.getCell(0);

			if (nameCell != null && name.equalsIgnoreCase(nameCell.toString())) {
				String[] values = new String[totalCols];
				for (int j = 0; j < totalCols; j++) {
					Cell cell = row.getCell(j);
					if (cell == null) {
						values[j] = "";
					} else {
						try {
							values[j] = cell.getStringCellValue();
						} catch (IllegalStateException e) {
							values[j] = String.valueOf(cell.getNumericCellValue());
						}
					}
				}
				return values;
			}
		}
		return null;
	}
}
