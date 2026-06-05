package tests;

import org.testng.annotations.Test;

import java.util.*;

public class LambdaExample {

    @Test(description = "LambdaExample", groups = "LambdaExample")
    public void dataTableExample(){
        //fnDataTable();
        //fnMapObject();
        usingContains();
    }
    public void fnLambdaExample(){
    String[] s = {"1","2","3"};

    Arrays.stream(s)
            .filter(a->a.equalsIgnoreCase("2"))
            .map(a->Integer.valueOf(a))
            .forEach(a-> System.out.println(a));

    String[][] s1= {{"1","2","3"},{"1","2","3"}};
    List<String> headers = new ArrayList<>();
    headers.add("ID");
    headers.add("Username");
    headers.add("Password");

    List<Map<String,String>> dataTable = new ArrayList<>();
    List<String> rows = new ArrayList<>();
    rows.add("TC01");

        dataTable = rows.stream()
            .map(a->{
                Map<String,String> row = new LinkedHashMap<>();
                row.put(headers.get(0),a);
                row.put(headers.get(1),"User1");
                row.put(headers.get(2),"Pass1");
                return row;
            }).toList();

        System.out.println(dataTable);
    }
    public void fnDataTable(){
        Map<String, Map<String, String>> datatable = new LinkedHashMap<>();
        Map<String, String> header = new LinkedHashMap<>();

        Map<String, String> values = new LinkedHashMap<>();
        values.put("Username","User1");
        values.put("Password","Pass1");
        datatable.put("TC01", values);

        values = new LinkedHashMap<>();
        values.put("Username","User2");
        values.put("Password","Pass2");
        datatable.put("TC02", values);
        System.out.println(datatable.get("TC01").get("Username"));

    }

    public void fnMapObject(){
        Map<String, LoginData> dataTable = new LinkedHashMap<>();

       dataTable.put("TC01",new LoginData(true,"TC01","User1","Pass1"));
       dataTable.put("TC02",new LoginData(true,"TC02","User2","Pass2"));
       dataTable.put("TC03",new LoginData(true,"TC03","User3","Pass3"));
       System.out.println(dataTable.get("TC02").Username());

       dataTable.values().stream()
               .forEach(value-> System.out.println(value.Username()));
    }

    public record LoginData(
            Boolean execution,
            String testCaseID,
            String Username,
            String Password
    ){}

    public void usingContains(){
        List<Map<String, String>> dataTable = new ArrayList<>();
        Map<String,String> row = new LinkedHashMap<>();
        row.put("ID","TC01");
        row.put("Username","User1");
        row.put("Password","Pass1");
        dataTable.add(row);

        row = new LinkedHashMap<>();
        row.put("ID","TC02");
        row.put("Username","User2");
        row.put("Password","Pass2");
        dataTable.add(row);

        System.out.println(dataTable.get(1).get("Username"));

        System.out.println("-------------------");

       dataTable.forEach(row1-> {
           row1.entrySet().forEach(value-> System.out.println(value.getValue()));
       });
    }
}
