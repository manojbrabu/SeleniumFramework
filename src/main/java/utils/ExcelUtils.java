package utils;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

public class ExcelUtils {

    private final Workbook workbook;
    private final Sheet sheet;

    public ExcelUtils(String workbookPath, String sheetName) {
        try {
            FileInputStream fs = new FileInputStream(workbookPath);
            workbook = new XSSFWorkbook(fs);
            sheet = workbook.getSheet(sheetName);
        } catch (Exception e) {
            throw new IllegalStateException("Failed to open Excel: " + workbookPath, e);
        }
    }

    public int getRowCount() {
        return sheet.getLastRowNum();
    }

    public int getColumnCount() {
        return sheet.getRow(0).getLastCellNum();
    }

    public String getCellValue(int row, int column) {
        DataFormatter formatter = new DataFormatter();
        Cell cell = sheet.getRow(row).getCell(column);
        return formatter.formatCellValue(cell);
    }

    @DataProvider
    public Object[][] getExcelData(ITestContext context) {
        String excelPath = context.getCurrentXmlTest().getParameter("excelFilePath");
        String sheetName = context.getCurrentXmlTest().getParameter("sheetName");
        ExcelUtils excel = new ExcelUtils(excelPath, sheetName);

        int rowCount = excel.getRowCount();
        int colCount = excel.getColumnCount();
        List<List<String>> dataTable = new ArrayList<>();

        for (int i = 0; i < rowCount; i++) {
            List<String> cellData = new ArrayList<>();
            for (int j = 0; j < colCount; j++) {
                cellData.add(excel.getCellValue(i, j));
            }
            dataTable.add(cellData);
        }

        Object[][] data = new Object[rowCount][colCount];
        for (int i = 0; i < rowCount; i++) {
            for (int j = 0; j < colCount; j++) {
                data[i][j] = dataTable.get(i).get(j);
            }
        }
        return data;
    }
}
