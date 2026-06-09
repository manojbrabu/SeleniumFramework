@database
  Feature: Database Connection Test
    Scenario: Verify user is able to connect and display table data

      Given Connect database using connection string "jdbc:sqlite:D:/Work/DB/MyDatabase.db"
      When Run given query "select * from Users;"
      Then Print table details in console
