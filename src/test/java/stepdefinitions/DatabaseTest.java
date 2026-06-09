package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import utils.DatabaseManager;

import java.util.List;
import java.util.Map;

public class DatabaseTest {

    private List<Map<String,Object>> dataTable =  null;
    @Given("Connect database using connection string {string}")
    public void openConnection(String connectionString){
        DatabaseManager.setConnection(connectionString);
    }

    @When("Run given query {string}")
    public void executeQuery(String query){
        dataTable = DatabaseManager.getTableData("Users","ID","3");
    }

    @Then("Print table details in console")
    public void displayResult(){
        System.out.println(dataTable.get(0).get("ID"));
        System.out.println(dataTable.get(0).get("Username"));
        System.out.println(dataTable.get(0).get("Password"));
    }
}
