package utils;

import lombok.Data;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ExcelUtils {

    Workbook workbook;
    Sheet sheet;

    public ExcelUtils(String workbookPath, String sheetName) {
        try{
            FileInputStream fs = new FileInputStream(workbookPath);
            workbook = new XSSFWorkbook(fs);
            sheet = workbook.getSheet(sheetName);
        }
        catch (Exception e){
            ExtentManager.test().fail("Failed to open Excel");
        }
    }

    public int getRowCount(){
        return sheet.getLastRowNum();
    }

    public int getColumnCount(){
        return sheet.getRow(0).getLastCellNum();
    }

    public String getCellValue(int i, int j){
        DataFormatter formatter = new DataFormatter();
        Cell cell = sheet.getRow(i).getCell(j);
        return formatter.formatCellValue(cell);
    }

    @DataProvider
    public Object[][] getExcelData(ITestContext context){
        List<List<String>> dataTable = new ArrayList<>();
        List<String> cellData = null;
        String excelPath = context.getCurrentXmlTest().getParameter("excelFilePath");
        String sheetName = context.getCurrentXmlTest().getParameter("sheetName");
        ExcelUtils excel = new ExcelUtils(excelPath,sheetName);
        int rowCount = excel.getRowCount();
        int colCount = excel.getColumnCount();
        for(int i=0; i<rowCount;i++){
            cellData = new ArrayList<>();
            for(int j=0;j<colCount;j++){
                cellData.add(getCellValue(i,j));
            }
            dataTable.add(new ArrayList<>(cellData));
        }

        Object[][] data = new Object[rowCount][colCount];

        for (int i = 0; i<rowCount; i++) {
            for (int j =0; j<colCount;j++){
                data[i][j] = dataTable.get(i).get(j);
            }
        }
        return data;
    }
}
