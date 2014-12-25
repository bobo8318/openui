package org.hao.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.hao.common.Parameter;

public class DataSourceManager {
	/**
	 * 获取链接
	 * 普通的方式活动数据库连接。
	 * @return
	 */
	public static Connection  getConnection(Parameter.DATA_SOURCE datasource){
		
		//System.out.println("---get database connection---:"+Config.getInstance().getDataSource("ds_mysql").getConnection());
		Connection conn = null;
				return conn;
	}
	
	/**
	 * 关闭链接对象
	 * @param rs
	 * @param st
	 * @param cn
	 */
	public static void closeConnection(ResultSet rs, PreparedStatement pst, Statement st,Connection cn){
		try {
			if(rs!=null){
				rs.close();
			}
			if(pst!=null){
				pst.close();
			}
			if(st!=null){
				st.close();
			}
			if(cn!=null&&!cn.isClosed()){
				cn.close();
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
