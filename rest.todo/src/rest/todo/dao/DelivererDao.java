package rest.todo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import rest.todo.model.DelivererModel;
import rest.todo.model.DelivererModel;

public enum DelivererDao {
	instance;

	private Map<Integer, DelivererModel> contentProvider = new HashMap<>();

	private DelivererDao() {

		try {

			Connection conn = new ConnectionDB().getDBConnection();
			Statement stmt = conn.createStatement();

			String QUERY = "SELECT * FROM ws_resto.deliverer";

			ResultSet rs = stmt.executeQuery(QUERY);
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("ID: " + rs.getString("ID"));

				DelivererModel deliverer = new DelivererModel(rs.getInt("ID"), rs.getString("NAME"),
						rs.getString("PHONENB"));

				contentProvider.put(rs.getInt("ID"), deliverer);

			}
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}

		/*
		 * DelivererModel deliverer = new DelivererModel("1", "Max","20999888");
		 * contentProvider.put("1", deliverer);
		 * 
		 * deliverer = new DelivererModel("2", "Julie","90444777");
		 * contentProvider.put("2", deliverer);
		 * 
		 * deliverer = new DelivererModel("3", "John","50333222");
		 * contentProvider.put("3", deliverer);
		 */

	}

	public Map<Integer, DelivererModel> getModel() {
		return contentProvider;
	}

	public int put(DelivererModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "INSERT INTO ws_resto.deliverer ( `Name`, `PhoneNb`) " + " VALUES ( '" + model.getNAME() + "', '"
				+ model.getPHONENB() + "') ";
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

	public int post(DelivererModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "UPDATE ws_resto.deliverer " + " set ID='" + model.getID() + "', " + "Name='" + model.getNAME()
				+ "'," + " PhoneNb='" + model.getPHONENB() + "'" + " WHERE ID='" + model.getID() + "' ";
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
		String QUERY = "DELETE FROM ws_resto.deliverer WHERE ID='" + ID + "' ";
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