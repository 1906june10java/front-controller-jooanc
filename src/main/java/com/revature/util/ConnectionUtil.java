package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/* Final utility class to obtain connections in a modular way */
public final class ConnectionUtil {

	private static Logger logger = Logger.getLogger(ConnectionUtil.class);

	/* Make Tomcat now which database driver to use */
	static {
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
		} catch (ClassNotFoundException e) {
			logger.warn("Exception thrown adding oracle driver.", e);
		}
	}

	/* Get connection to JDBC */
	public static Connection getConnection() throws SQLException {
		String url = "jdbc:oracle:thin:@mydbinstance.cd85qhigqy8i.us-east-2.rds.amazonaws.com:1521:ORCL";
		String username = "admin";
		String password = "p4ssw0rd";		

		return DriverManager.getConnection(url, username, password);
	}
	public static void main(String[] args) {
		try { ConnectionUtil.getConnection();
			logger.trace("Connection successful.");
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}

}
