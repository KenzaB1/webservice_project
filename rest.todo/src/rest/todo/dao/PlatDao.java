package rest.todo.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import rest.todo.model.PlatModel;
import rest.todo.model.RestoModel;

public enum PlatDao {
	instance;

	private Map<Integer, PlatModel> contentProvider = new HashMap<>();

	private PlatDao() {

		try {

			Connection conn = new ConnectionDB().getDBConnection();
			Statement stmt = conn.createStatement();

			String QUERY = "SELECT * FROM ws_resto.meal";

			ResultSet rs = stmt.executeQuery(QUERY);
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("ID: " + rs.getString("ID"));

				PlatModel meal = new PlatModel(rs.getInt("ID"), rs.getString("NAME"), rs.getDouble("PRICE"));

				contentProvider.put(rs.getInt("ID"), meal);

			}
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}

		/*
		 * PlatModel plats = new PlatModel("1", "Pizza",40); contentProvider.put("1",
		 * plats);
		 * 
		 * plats = new PlatModel("2", "Soupe",20); contentProvider.put("2", plats);
		 * 
		 * plats = new PlatModel("3", "Salade",10); contentProvider.put("3", plats);
		 */

	}

	public int put(PlatModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "INSERT INTO ws_resto.meal( `Name`, `PRICE`)" + " VALUES ( '" + model.getNAME() + "', '"
				+ model.getPRICE() + "') ";
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

	public int post(PlatModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "UPDATE ws_resto.meal " + " set ID='" + model.getID() + "', " + "NAME='" + model.getNAME() + "',"
				+ " PRICE='" + model.getPRICE() + "'" + " WHERE ID='" + model.getID() + "' ";
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
		String QUERY = "DELETE FROM ws_resto.meal WHERE ID='" + ID + "' ";
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

	public Map<Integer, PlatModel> getModel() {
		return contentProvider;
	}

}