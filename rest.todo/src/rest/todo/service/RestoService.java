/**
 * 
 */
package rest.todo.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;

import rest.todo.dao.ConnectionDB;
import rest.todo.model.RestoModel;

/**
 * @author Ines
 *
 */
public class RestoService {

	private Map<Integer, RestoModel> contentProvider = new HashMap<>();

	private RestoService() {

		getModels();

		/*
		 * RestoModel resto = new RestoModel("1",
		 * "BEAUNIER","43 RUE BEAUNIER	PARIS","E",""); contentProvider.put("1", resto);
		 * 
		 * resto = new RestoModel("2", "MOUFFETARD"," 20 RUE ORTOLAN	PARIS","E","");
		 * contentProvider.put("2", resto);
		 * 
		 * resto = new RestoModel("3", "SAINT-SAUVEUR","8, RUE SAINT-SAUEUR","E","");
		 * contentProvider.put("3", resto);
		 */

	}

	public Map<Integer, RestoModel> getModel() {
		return contentProvider;
	}

	public void getModels() {
		try {

			Connection conn = new ConnectionDB().getDBConnection();
			Statement stmt = conn.createStatement();

			String QUERY = "SELECT * FROM ws_resto.restaurant";

			ResultSet rs = stmt.executeQuery(QUERY);
			// Extract data from result set
			while (rs.next()) {
				// Retrieve by column name
				System.out.print("ID: " + rs.getString("ID"));

				RestoModel resto = new RestoModel(rs.getInt("ID"), rs.getString("NAME"), rs.getString("ADDRESS"),
						rs.getString("TYPE"), rs.getString("DESCRIPTION"));

				contentProvider.put(rs.getInt("ID"), resto);

			}
		} catch (SQLException e) {
			e.getMessage();
			e.printStackTrace();
		}
	}

	public int put(RestoModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "INSERT INTO ws_resto.restaurant(`Name`, `Address`, `Type`, `Description`)" + " VALUES ( '"
				+ model.getNAME() + "', '" + model.getADDRESS() + "'" + ", '" + model.getTYPE() + "', '"
				+ model.getDESCRIPTION() + "') ";
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

	public int post(RestoModel model) {

		Connection conn = new ConnectionDB().getDBConnection();
		String QUERY = "UPDATE ws_resto.restaurant " + " set ID='" + model.getID() + "', " + "NAME='" + model.getNAME()
				+ "'," + " ADDRESS='" + model.getADDRESS() + "'" + ", TYPE='" + model.getTYPE() + "',"
				+ "DESCRIPTION= '" + model.getDESCRIPTION() + "' WHERE ID='" + model.getID() + "' ";
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
		String QUERY = "DELETE FROM ws_resto.restaurant WHERE ID='" + ID + "' ";
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
