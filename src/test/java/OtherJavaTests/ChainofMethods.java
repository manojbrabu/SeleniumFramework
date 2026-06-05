package OtherJavaTests;

import com.google.gson.internal.bind.util.ISO8601Utils;

import java.util.*;

public class ChainofMethods {
    public static void main(String[] args){
    WebDriver driver = new WebDriver();
    //driver.manage().window().maximize();
    forEachExample();
    }

    //forEach

    public static void forEachExample(){
        List<Map<String,String>> dataTable = new ArrayList<>();
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

        //row.forEach((a,b)-> System.out.println(a+" "+b));

        for(Map<String,String> row1:dataTable) {
            for (Map.Entry<String, String> value1 : row1.entrySet()) {
                if (value1.getValue().equals("TC01")){
                    System.out.println(row1.get("ID"));
                    System.out.println(row1.get("Username"));
                    System.out.println(row1.get("Password"));
                }
            }
        }
        System.out.println("---------------------------");
        //Using For Each

        Map<String,String> expData = new LinkedHashMap<>();
        dataTable.stream()
                .forEach(mapEntry->{
                    mapEntry.forEach((k,v)->{
                        if(v.equals("TC02")){
                            System.out.println(mapEntry.get(k));
                            System.out.println(mapEntry.get("Username"));
                            System.out.println(mapEntry.get("Password"));
                        }
                       ;
                    });
                });

}

}
