package tests;

import com.google.gson.internal.bind.util.ISO8601Utils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;
import java.io.FileInputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class ReadExcel {
    Workbook excel;
    Sheet sheet;
    Row row;
    Cell col;
    List<Map<String,String>> dataTable;
    @Test(description= "ReadExcel", groups="ReadExcel")
    public void ReadExcelTest(){
        String excelPath = System.getProperty("user.dir")+"/resources/TestData.xlsx";
        excelPath = Paths.get(System.getProperty("user.dir"),"src","main","resources","TestData.xlsx").toString();
        System.out.println(excelPath);
        openExcel(excelPath, "Data");
        //getValue();
        System.out.println(getRowCount());
        System.out.println(getColumnCount());
        loadExcelDataintoDataTable();
    }

    public void openExcel(String excelPath, String sheetName){
        FileInputStream file;
        try{
        file = new FileInputStream(excelPath);
        excel = new XSSFWorkbook(file);
            System.out.println("ExcelAdded");
        sheet = excel.getSheet(sheetName);
            System.out.println("SheetAssigned");
        }
        catch (Exception e){
            System.out.println(e.toString());
        }
    }
    public void loadExcelDataintoDataTable(){
        dataTable = new ArrayList<>();
        Map<String,String> data;
        for(int row = 1;row<getRowCount();row++){
            data = new LinkedHashMap<>();
            for(int col=0;col<getColumnCount();col++){
                //System.out.println(sheet.getRow(row).getCell(col));
                data.put(sheet.getRow(0).getCell(col).toString(),sheet.getRow(row).getCell(col).toString());
            }
            dataTable.add(data);
        }
        System.out.println("----------------");
        //System.out.println(dataTable);
       /* for(Map<String,String> data1 : dataTable){
            if(data1.containsKey("Username")&&data1.containsValue("TC01")){
                System.out.println(data1.get("Username"));
            }
        }*/
        dataTable
                .forEach(row->{
                    row.keySet().forEach(key->{
                     if(row.get(key).equals("TC01")){
                         System.out.println(row.get("Username"));
                     }
                    });
                });
    }
    public int getRowCount(){
        return sheet.getPhysicalNumberOfRows();
    }
    public int getColumnCount(){
        return sheet.getRow(0).getLastCellNum();
    }
    public void getValue(){
        System.out.println(sheet.getRow(1).getCell(1));
    }
}
