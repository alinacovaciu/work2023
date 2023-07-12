package com.ssn.practica.work.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class minitema {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.35:1521:xe", "aln_test2",
				"aln_test2")) {
			if (conn != null) {
				System.out.println("Connection successful");
			}

			insertArticol(conn, 404, "iaurt");
			insertMagazin(conn, 402, "mega");
			insertArticolMagazin(conn, 404, 402, 3);
			String query = "SELECT a.nume, m.nume AS nume_magazin, p.pret " +

					"FROM articol a " +

					"JOIN articol_magazin p ON a.id = p.id_articol " +

					"JOIN magazin m ON p.id_magazin = m.id " +

					"WHERE p.pret = (SELECT MIN(pret) FROM articol_magazin WHERE id_articol = p.id_articol) " +

					"ORDER BY a.nume";

			PreparedStatement ps = conn.prepareStatement(query);
			ResultSet rs = ps.executeQuery();

			while (rs.next()) {
				String numeArt = rs.getString("nume");
				String numeMag = rs.getString("nume");
				Float pret = rs.getFloat("pret");
				System.out.println("articol" + numeArt + "," + "magazin" + numeMag + "," + "pret" + pret);
			}
		}

	}

	public static void insertArticol(Connection conn, int id, String nume) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into articol values (?, ?)");
		statement.setInt(1, id);
		statement.setString(2, nume);
		statement.execute();

	}

	public static void insertMagazin(Connection conn, int id, String nume) throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into magazin values (?, ?)");
		statement.setInt(1, id);
		statement.setString(2, nume);
		statement.execute();
	}

	public static void insertArticolMagazin(Connection conn, int articolid, int magazinid, int pret)
			throws SQLException {
		PreparedStatement statement = conn.prepareStatement("insert into articol_magazin values (?, ?, ?)");
		statement.setInt(1, articolid);
		statement.setInt(2, magazinid);
		statement.setInt(3, pret);
		statement.execute();

	}

}