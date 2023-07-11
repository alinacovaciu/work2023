package com.ssn.practica.work.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCExample {

	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.35:1521:xe", "aln_test2",
				"aln_test2")) {
			if (conn != null) {
				System.out.println("Connection successful!");
			}

			Statement statement = conn.createStatement();
			ResultSet resultSet = statement.executeQuery("select * from vw_note_studenti");
			while (resultSet.next()) {
				System.out.println(//
						resultSet.getInt(1) + " " + //
								resultSet.getString(2) + " " + //
								resultSet.getDouble(3) + " " + //
								resultSet.getInt(4));
			}
			resultSet.close();

		}
	}

}
