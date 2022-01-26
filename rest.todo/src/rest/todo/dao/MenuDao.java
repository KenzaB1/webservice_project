package rest.todo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import rest.todo.model.MenuModel;

public enum MenuDao {
	instance;

	private Map<Integer, MenuModel> contentProvider = new HashMap<>();

	private MenuDao() {

		try {

			Connection conn = new ConnectionDB().getDBConnection();
			Statement stmt = conn.createStatement();

			String QUERY = "SELECT * FROM ws_resto.menu";

			ResultSet rs = stmt.executeQuery(QUERY);
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("ID: " + rs.getString("ID"));

				MenuModel menu = new MenuModel(rs.getInt("ID"), rs.getString("RESTOID"), rs.getString("MEALID"));

				contentProvider.put(rs.getInt("ID"), menu);

			}
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}

		/*
		 * MenuModel menu = new MenuModel("1", "1","2"); contentProvider.put("1", menu);
		 * 
		 * menu = new MenuModel("2", "2","3"); contentProvider.put("2", menu);
		 * 
		 * menu = new MenuModel("3", "1","2"); contentProvider.put("3", menu);
		 */

	}

	public Map<Integer, MenuModel> getModel() {
		return contentProvider;
	}

	public int put(MenuModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "INSERT INTO ws_resto.menu (`RestoID`, `MealID`)" + " VALUES ( '" + model.getRESTOID() + "', '"
				+ model.getMEALID() + "') ";
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

	public int post(MenuModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "UPDATE ws_resto.menu " + " set ID='" + model.getID() + "', " + "RestoID='" + model.getRESTOID()
				+ "'," + " MealID='" + model.getMEALID() + "'" + " WHERE ID='" + model.getID() + "' ";
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
		String QUERY = "DELETE FROM ws_resto.menu WHERE ID='" + ID + "' ";
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