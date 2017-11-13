package com.jdbc;

import java.sql.Connection;
import java.sql.SQLException;

public interface empJdbcDao {
	public Connection getConnection();
	public boolean destroy();
}
