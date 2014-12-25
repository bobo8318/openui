package org.hao.dao;
/**
 * 
 */

import javax.annotation.Resource;
import javax.sql.DataSource;

import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.stereotype.Repository;
import org.mybatis.spring.SqlSessionTemplate;
@SuppressWarnings("deprecation")
@Repository("baseDao")
public class BaseDao {
	
	
	@Resource(name="readSqlSession")
	protected SqlSessionTemplate readSqlSession;
	
	@Resource(name="writerSqlSession")
	protected SqlSessionTemplate writerSqlSession;

	protected DataSource readDataSource;
	protected DataSource writeDataSource;
	protected SimpleJdbcTemplate readJdbcTemplate;
	protected SimpleJdbcTemplate writeJdbcTemplate;
	
	public SqlSessionTemplate getReadSqlSession() {
		return readSqlSession;
	}
	public void setReadSqlSession(SqlSessionTemplate readSqlSession) {
		this.readSqlSession = readSqlSession;
	}
	public SqlSessionTemplate getWriterSqlSession() {
		return writerSqlSession;
	}
	public void setWriterSqlSession(SqlSessionTemplate writerSqlSession) {
		this.writerSqlSession = writerSqlSession;
	}
	
	
	
	
	/*Connection cn = null;
	PreparedStatement pst = null;
	Statement st = null;
	ResultSet rs  = null;
	CallableStatement callableStatement = null;
	
	
	public DataSource getReadDataSource() {
		return readDataSource;
	}
	@Resource(name="readDataSource")
	public void setReadDataSource(DataSource readDataSource) {
		this.readDataSource = readDataSource;
		 this.readJdbcTemplate = new SimpleJdbcTemplate(readDataSource);
	}

	public DataSource getWriteDataSource() {
		return writeDataSource;
	}
	@Resource(name="writeDataSource")
	public void setWriteDataSource(DataSource writeDataSource) {
		this.writeDataSource = writeDataSource;
		this.writeJdbcTemplate = new SimpleJdbcTemplate(writeDataSource);
	}

	public SimpleJdbcTemplate getReadJdbcTemplate() {
		return readJdbcTemplate;
	}

	public void setReadJdbcTemplate(SimpleJdbcTemplate readJdbcTemplate) {
		this.readJdbcTemplate = readJdbcTemplate;
	}

	public SimpleJdbcTemplate getWriteJdbcTemplate() {
		return writeJdbcTemplate;
	}

	public void setWriteJdbcTemplate(SimpleJdbcTemplate writeJdbcTemplate) {
		this.writeJdbcTemplate = writeJdbcTemplate;
	}

	public Connection getConnection(Parameter.DATA_SOURCE datasource){
		return DataSourceManager.getConnection(datasource);//0 mysql 1 mssql
	}
	
	public Connection getConnection(){
		return DataSourceManager.getConnection(Parameter.DATA_SOURCE.LOCAL);//0 mysql 1 mssql
	}
	public void closeConnection(){
		DataSourceManager.closeConnection(rs, pst, st, cn);
	}
	public void closeCallConnection(){
		DataSourceManager.closeConnection(rs, null, callableStatement, cn);
	}*/
	
}