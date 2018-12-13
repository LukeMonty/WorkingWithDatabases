package workingWithDatabases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCAttempt {

	static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	static final String DB_URL = "jdbc:mysql://localhost/sakila";

	static final String USER = "root";
	static final String PASSWORD = "lukemonty";

	public void accessDB() {
		Connection conn = null;
		Statement stmt = null;

		try {

			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Inserting values..");
			stmt = conn.createStatement();

			String sql = "insert into actor(first_name, last_name) values ('Luke', 'MONTY')";
			stmt.executeUpdate(sql);
			System.out.println("Insert complete");

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}

		}

		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Getting records from table...");
			stmt = conn.createStatement();

			String sql2 = "SELECT * FROM actor";
			ResultSet rs = stmt.executeQuery(sql2);
			while (rs.next()) {
				int id = rs.getInt("actor_id");
				String name = rs.getString("first_name");
				String lastName = rs.getString("last_name");
				String timeStamp = rs.getString("last_update");
				System.out.println("ID: " + id + ", name: " + name + ", last name: " + lastName + ", LastUpdated: " + timeStamp);
			}
			rs.close();

		} catch (SQLException sqle) {
			sqle.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Updating ID 201 in actors");
			stmt = conn.createStatement();
			
			String sql3 = "UPDATE actor SET first_name = 'Bob' WHERE actor_id = 201";
			
			stmt.executeUpdate(sql3);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
		try {
			conn = DriverManager.getConnection(DB_URL, USER, PASSWORD);
			System.out.println("Delete ID 203");
			stmt = conn.createStatement();
			String sql4 = "DELETE FROM actor WHERE actor_id in (203,204)";
			stmt.executeUpdate(sql4);
			
		} catch (SQLException sqle) {
			sqle.printStackTrace();
		}
		finally {
			try {
				if (stmt != null)
					conn.close();
			} catch (SQLException se) {
			}
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException se) {
				se.printStackTrace();
			}
		}
	}
}
