package utils;

import javax.swing.plaf.nimbus.State;
import java.sql.*;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DatabaseManager {

    private static ThreadLocal<Connection> connection = new ThreadLocal<>();
    public static void setConnection(String conString) {

        //conString = "jdbc:sqlite:D:/Work/DB/MyDatabase.db";

        try{
            connection.set(DriverManager.getConnection(conString));
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static Connection getConnection(){return connection.get();
    }

    public static Statement prepareStatement(String query){
        Statement stmt;
        try{
            stmt=getConnection().prepareStatement(query);
            return stmt;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    public static List<Map<String,Object>> getTableData(String tableName, String colmnName, String value){
        PreparedStatement stmt;
        List<Map<String,Object>> dataTable = new ArrayList<>();
        Map<String,Object> row = new LinkedHashMap<>();
        String query = "Select * from "+tableName+" where "+colmnName+" = ?;";
        try{
            stmt=getConnection().prepareStatement(query);
            stmt.setString(1,value);
            ResultSet result = stmt.executeQuery();
            ResultSetMetaData resultSetMetaData = result.getMetaData();
            while(result.next()){
                for(int i=1;i<=resultSetMetaData.getColumnCount();i++){
                    row.put(resultSetMetaData.getColumnName(i),result.getObject(i));
                }
                dataTable.add(row);
            }
            return dataTable;
        }
        catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
    public static List<Map<String,Object>> executeQuery(String Query){
        //Query = "select * from Users;";
        ResultSet result;
        ResultSetMetaData metaData;
        int colCount= 0;
        List<Map<String,Object>> dataTable;
        Map<String,Object> rowData;
        try{
            result = getConnection().prepareStatement(Query).executeQuery();
            metaData = result.getMetaData();
            colCount  = metaData.getColumnCount();
            dataTable = new ArrayList<>();

            while(result.next()){
                rowData = new LinkedHashMap<>();
                for(int j=1;j<=colCount;j++){
                    rowData.put(metaData.getColumnName(j),result.getObject(j));
                }
                dataTable.add(rowData);
            }
        }
        catch(SQLException e){
            throw new RuntimeException(e);
        }
        closeConnection();
        return dataTable;
    }

    public static void closeConnection() {
        try {
            if (connection.get() != null) {
                connection.get().close();
                connection.remove();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
