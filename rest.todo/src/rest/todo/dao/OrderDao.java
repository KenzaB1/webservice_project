package rest.todo.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import rest.todo.model.OrderModel;
import rest.todo.model.RestoModel;

public enum OrderDao {
	instance;

	private Map<Integer, OrderModel> contentProvider = new HashMap<>();

	private OrderDao() {

		try {

			Connection conn = new ConnectionDB().getDBConnection();
			Statement stmt = conn.createStatement();

			String QUERY = "SELECT * FROM ws_resto.order";

			ResultSet rs = stmt.executeQuery(QUERY);
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("ID: " + rs.getString("ID"));

				OrderModel order = new OrderModel(rs.getInt("ID"), rs.getString("CUSTOMERID"),
						rs.getString("DELIVERERID"), rs.getDouble("TOTALPRICE"));

				contentProvider.put(rs.getInt("ID"), order);

			}
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}

		/*
		 * OrderModel order = new OrderModel("1", "1", "1","2");
		 * contentProvider.put("1", order);
		 * 
		 * order = new OrderModel("2","2", "2","3"); contentProvider.put("2", order);
		 * 
		 * order = new OrderModel("3","3", "1","2"); contentProvider.put("3", order);
		 */

	}

	public Map<Integer, OrderModel> getModel() {
		return contentProvider;
	}

	public int put(OrderModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "INSERT INTO ws_resto.order ( `CustomerID`, `DelivererID`, `TOTALPRICE`)" + " VALUES ( '"
				+ model.getCUSTOMERID() + "', '" + model.getDELIVERERID() + "'" + ", '" + model.getTOTALPRICE() + "') ";
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

	public int post(OrderModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "UPDATE ws_resto.order " + " set ID='" + model.getID() + "', " + "CustomerID='"
				+ model.getCUSTOMERID() + "'," + " DelivererID='" + model.getDELIVERERID() + "'" + ", TOTALPRICE='"
				+ model.getTOTALPRICE() + "'" + " WHERE ID='" + model.getID() + "' ";
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
		String QUERY = "DELETE FROM ws_resto.order WHERE ID='" + ID + "' ";
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