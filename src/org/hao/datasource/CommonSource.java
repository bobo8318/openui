package org.hao.datasource;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonSource {
	private Connection connection;
	private String dbDriver = "com.mysql.jdbc.Driver";
	private String dbUrl = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_openui";
	private String dbUser = "w5k2l1wyx0";          
	private String dbPassword = "m501llll2w5y2y10ik052ljzz2y354x231i5lz5l"; 
	
	public Connection createConnection() throws Exception{
		try {
		Class.forName(this.dbDriver);
		} catch (ClassNotFoundException e) {
		e.printStackTrace();
		throw e;
		}

		try {
		this.connection = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
		} catch (SQLException e) {
		e.printStackTrace();
		throw e;
		}
			return this.connection;
		
		}



}
