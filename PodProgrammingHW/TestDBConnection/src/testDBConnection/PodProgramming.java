package testDBConnection;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Savepoint;
import java.sql.Statement;
import java.util.Scanner;

//I know, there is an issue with the Modify Date Column in the table. 
//Was not able to figure it out. At least we can see savepoint a and rollback working.

public class PodProgramming {
    public static void main(String[] args) {
        // JDBC connection URL for connecting to the database
        final String DATABASE_URL = "jdbc:sqlserver://localhost;" +
                                    "databaseName=PREMIERECO;integratedSecurity=true;encrypt=true;TrustServerCertificate=true";
        
        Connection dbConnection;

        try {
            // Now connecting to the db
            dbConnection = DriverManager.getConnection(DATABASE_URL);
            dbConnection.setAutoCommit(false);
            System.out.println("connected successfully");

            // Calling a method to display all tables in the database
            displayAllTables(dbConnection);
            
        } catch (SQLException sqlE) {
            // Handling SQL exceptions
            System.out.println("encountered a problem " + sqlE.getMessage());
        }
    }

    public static void displayAllTables(Connection dbConnection) {
        // This method displays all tables in the database
        try {
            // Retrieving database metadata
            DatabaseMetaData dbMetaD = dbConnection.getMetaData();
            String[] types = { "TABLE" };
            ResultSet tables = dbMetaD.getTables(null, null, "%", types);
            while (tables.next()) {
                
            	// Getting the table name and schema
                String tableName = tables.getString("TABLE_NAME");
                String tableSchema = tables.getString("TABLE_SCHEM");
                
                // Excluding the system tables
                if (!tableSchema.equalsIgnoreCase("sys") && !tableSchema.equalsIgnoreCase("information_schema")) {
                    
                	// Constructing SQL query to select all records from the table
                    String querySql = "Select * From " + tableName;
                    
                    // Preparing and executing the SQL statement
                    PreparedStatement stmnt = dbConnection.prepareStatement(querySql);
                    ResultSet results = stmnt.executeQuery();
                    
                    // Retrieving metadata for the result set
                    ResultSetMetaData resultMD = results.getMetaData();
                    
                    // Printing table name and column names/types
                    printTables(results, resultMD, tableName);
                    
                    // Closing resources
                    stmnt.close();
                    results.close();
                    // Initiating a transaction
                    transaction(dbConnection);
                }
            }
        } catch (SQLException sqlE) {
            // Handling SQL exceptions
            sqlE.printStackTrace();
        }
    }

    private static void printTables(ResultSet results, ResultSetMetaData resultMD, String tableName) {
        // Now printing the table name and column names/types
        try {
            
        	// Printing table name
            System.out.println(tableName);
            
            // Printing column names and types
            for (int i = 1; i <= resultMD.getColumnCount(); i++) {
                System.out.println(resultMD.getColumnName(i) + " " + resultMD.getColumnTypeName(i));
                System.out.println();
            }
            
            // Printing table data
            while (results.next()) {
                for (int i = 1; i < resultMD.getColumnCount(); i++) {
                    System.out.println(results.getObject(i) + "\t");
                }
            }
        } catch (Exception E) {
            E.printStackTrace();
        }
    }

    private static void transaction(Connection dbConnection) {
        //Lets handle a transaction
        Scanner scanner = new Scanner(System.in);

        // Now getting user input for the transaction
        System.out.println("Existing Customers Only: And No Validation: Please Proceed With Caution:");
        System.out.println("\nPlease enter the part number: ");
        String partNumber = scanner.nextLine();
        double price = getPrice(dbConnection, partNumber);
        System.out.println("\nHow many pieces would you like: ");
        int quantity = scanner.nextInt();
        System.out.println("\nWhat is your customer ID: (ONLY EXISTING CUSTOMERS)");
        int customerID = scanner.nextInt();

        Savepoint saveP = null;

        try {
            // Setting a savepoint before executing queries
            saveP = dbConnection.setSavepoint("Now before the queries...");
            
            // Inserting data into the Orders table
            String insertOrders = "Insert Into Orders (ORDER_DATE, CUST_NUM) Values (?,?)";
            PreparedStatement insertOStmnt = dbConnection.prepareStatement(insertOrders, Statement.RETURN_GENERATED_KEYS);
            Date currentDate = new java.sql.Date(System.currentTimeMillis());
            insertOStmnt.setDate(1, currentDate);
            insertOStmnt.setInt(2, customerID);
            insertOStmnt.executeUpdate();
            int orderNum = getOrderNumber(insertOStmnt);
            
            // Inserting data into the Order_Line table
            String insertOLine = "Insert Into Order_Line (ORDER_NUM, Part_Num, QTY_ORDERED, QUOTED_PRICE, Modify_Date) values(?,?,?,?,?)";
            PreparedStatement insertOLS = dbConnection.prepareStatement(insertOLine);
            insertOLS.setInt(1, orderNum);
            insertOLS.setString(2, partNumber);
            insertOLS.setInt(3, quantity);
            insertOLS.setDouble(4, price);
            insertOLS.setDate(5, currentDate);
            insertOLS.executeUpdate();
            insertOLS.close();
            
            // Now updating the Part table
            String updateInventory = "Update Part Set Units_On_Hand = Units_On_Hand - ? Where Part_Num = ? ";
            PreparedStatement updateStmnt = dbConnection.prepareStatement(updateInventory);
            updateStmnt.setInt(1, quantity);
            updateStmnt.setString(2, partNumber);
            updateStmnt.executeUpdate();
            updateStmnt.close();
            
            // Now committing the transaction
            dbConnection.commit();
            System.out.println("Transaction saved successfully!");
            scanner.close();
            
        } catch (Exception E) {
            // Now handling exceptions and rolling back to the savepoint
            E.printStackTrace();
            try {
                dbConnection.rollback(saveP);
                System.out.println("Rolling back to the savepoint.");
            } catch (SQLException sqlE) {
            }
        }
    }

    
    private static double getPrice(Connection dbConnection, String partNumber) {
    	// This method gets the price of a part
        double price = 0.0;
        String priceQuery = "Select Price From Part Where Part_Num = ?";
        try {
            PreparedStatement priceStmnt = dbConnection.prepareStatement(priceQuery);
            priceStmnt.setString(1, partNumber);
            ResultSet priceResult = priceStmnt.executeQuery();
            if (priceResult.next()) {
                price = priceResult.getDouble("Price");
            }
            priceResult.close();
            priceStmnt.close();
        } catch (Exception E) {
        	E.printStackTrace();
        } finally {
            return price;
        }
    }

    private static int getOrderNumber(PreparedStatement insertOStmnt) {
        // This method gets the order number
        int orderNumber = 0;
        try {
            ResultSet generateKey = insertOStmnt.getGeneratedKeys();
            if (generateKey.next()) {
                orderNumber = generateKey.getInt(1);
            }
            generateKey.close();
        } catch (Exception E) {
        	E.printStackTrace();
        }
        return orderNumber;
    }
}
