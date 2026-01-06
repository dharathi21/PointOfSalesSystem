package com.wipro.pos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DButil {
	public static Connection con;
	public static Connection getConnection() {
	try {
		if(con==null) {
		Class.forName("oracle.jdbc.driver.OracleDriver");
		String username="dharathi";
		String password="dharathi123";
		String url="jdbc:oracle:thin:@//localhost:1521/XE";
		con=DriverManager.getConnection(url,username,password);
		System.out.print("successfull");}
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}catch(SQLException s) {
		s.printStackTrace();
	}
	return con;
	}
}
