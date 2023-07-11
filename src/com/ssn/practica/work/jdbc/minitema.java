package com.ssn.practica.work.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class minitema {
	public static void main(String[] args) throws ClassNotFoundException, SQLException {
		Class.forName("oracle.jdbc.driver.OracleDriver");

		try (Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@192.168.1.35:1521:xe", "aln_test2",
				"aln_test2")) {
			if (conn != null) {
				System.out.println("Connection successful");
			}

			PreparedStatement statement = conn.prepareStatement("insert into articol values (?, ?, ?)");
			statement.setInt(1, 1);
			statement.setString(2, "Andrei");
			statement.setInt(3, 22);

			statement.execute();
		}

	}

}
