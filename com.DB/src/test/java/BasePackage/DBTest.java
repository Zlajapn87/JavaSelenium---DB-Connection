package BasePackage;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;


public class DBTest {

	public static void main(String[] args) throws SQLException {
		
		
		
		final String URL = "jdbc:oracle:thin:@//10.245.254.12:1521/sugarcrmtest"; 
		
		Connection conn = null;
		
		// Object of Statement. It is used to create a Statement to execute the query
		Statement stmt = null;
		
		//Object of ResultSet => 'It maintains a cursor that points to the current row in the result set'
		 ResultSet resultSet = null;
		 try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		 
		 // Open a connection
		 conn = DriverManager.getConnection(URL, "SUGARCRM", "S2_Lrn#WKP");
		 
		 // Execute a query
		 stmt = conn.createStatement();
		 
		 resultSet = stmt.executeQuery("SELECT * FROM SUGARCRM.accounts a\r\n" + 
		 		"WHERE a.NAME like '%MIROSLAV%'");
		 
		 //Printing all the results, including the column names
		 ResultSetMetaData rsmd = resultSet.getMetaData();
		 int columnsNumber = rsmd.getColumnCount();
		 while (resultSet.next()) {
			    for (int i = 1; i <= columnsNumber; i++) {
			        String columnValue = resultSet.getString(i);
			        System.out.print(rsmd.getColumnName(i) +": " +  columnValue + ", "  );
			    }
			    //Next row
			    System.out.println("");
		}
		 
		 //Closing the connection		
		 if (resultSet != null) {
				 try {
					 resultSet.close();
				 } 
				 catch (Exception e) {
				 }
		 }
		 
		 if (conn != null) {
			 try {
				 conn.close();
			 } 
			 catch (Exception e) {
			 }
		 }	
	}		
}
