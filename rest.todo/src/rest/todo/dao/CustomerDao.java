package rest.todo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import rest.todo.model.CustomerModel;



public enum CustomerDao {
    instance;

    private Map<Integer, CustomerModel> contentProvider = new HashMap<>();

    private CustomerDao() {
    	
    	try {
     		 
 		     Connection conn= new ConnectionDB().getDBConnection();
	         Statement stmt = conn.createStatement();
	         
	         String QUERY = "SELECT * FROM ws_resto.customer";
	         
	         ResultSet rs = stmt.executeQuery(QUERY);
	         // Extract data from result set
	         while (rs.next()) {
	            // Retrieve by column name
	            System.out.print("ID: " + rs.getString("ID"));
	            
	            CustomerModel customer = new CustomerModel(rs.getInt("ID"),rs.getString("NAME"),
	            		rs.getString("ADDRESS"), rs.getString("PHONENB"));
	            
	            contentProvider.put(rs.getInt("ID"), customer);           
	         }
	      } catch (SQLException e) {
	    	  e.getMessage();
	         e.printStackTrace();
	      } 

      /*  CustomerModel customer = new CustomerModel("1", "Alex","43 RUE BEAUNIER	PARIS","20113355");
        contentProvider.put("1", customer);
        
        customer = new CustomerModel("2", "Marie"," 20 RUE ORTOLAN	PARIS","90888777");
        contentProvider.put("2", customer);
        
        customer = new CustomerModel("3", "John","8, RUE SAINT-SAUEUR","50444666");
        contentProvider.put("3", customer);*/
        

    }
    public Map<Integer, CustomerModel> getModel(){
        return contentProvider;
    }
    
    public int put(CustomerModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "INSERT INTO ws_resto.customer ( `Name`, `Address`, `PhoneNb`)" 
		+ " VALUES ( '" + model.getNAME() + "', '"+ model.getADDRESS() + "', '"+ model.getPHONENB() + "') ";
		try {

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(QUERY);
			return (1);
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
			return (0);
		} finally {
		}

	}

	public int post(CustomerModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "UPDATE ws_resto.customer " + " set ID='" + model.getID() + "', " + "Name='" + model.getNAME()
				+ "'," + " Address='" + model.getADDRESS() + "'" + " PhoneNb='" + model.getPHONENB() + "'" + " WHERE ID='" + model.getID() + "' ";
		try {

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(QUERY);
			return (1);
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
			return (0);
		} finally {
		}

	}

	public int delete(int ID) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "DELETE FROM ws_resto.customer WHERE ID='" + ID + "' ";
		try {

			Statement stmt = conn.createStatement();
			stmt.executeUpdate(QUERY);
			return (1);
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
			return (0);
		} finally {
		}

	}

}