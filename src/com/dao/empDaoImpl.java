package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.jdbc.empJdbcDao;
import com.jdbc.empJdbcDaoImpl;
import com.model.emp;



public class empDaoImpl implements empDao {
private static Connection connection = null;

	static {
		empJdbcDao empjdbcDao = new empJdbcDaoImpl();
		connection = empjdbcDao.getConnection();
		System.out.println(connection);
	}

	public int createEmp(emp emp) {
		int rows = 0;
		try {
			System.out.println(emp);
			String query = "Insert into Employee values(?,?,?)";
			PreparedStatement pstmt = empDaoImpl.connection.prepareStatement(query);
			pstmt.setInt(1, emp.getEmpId());
			pstmt.setString(2, emp.getEmpName());
			pstmt.setDouble(3, emp.getEmpSal());
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	@Override
	public int updateEmp(emp emp) {
		int rows = 0;
		try {
			String query = "update Employee set ename=? , esal=? where eno=?";
			PreparedStatement pstmt =empDaoImpl.connection.prepareStatement(query);
			pstmt.setInt(3, emp.getEmpId());
			pstmt.setString(1, emp.getEmpName());
			pstmt.setDouble(2, emp.getEmpSal());
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}

	public int deleteEmp(int eno) {
		int rows = 0;
		try {
			String query = "delete from Employee where eno=?";
			PreparedStatement pstmt =empDaoImpl.connection.prepareStatement(query);
			pstmt.setInt(1, eno);
			rows = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return rows;
	}
	public List getemp() {
		List<emp> list = new ArrayList<emp>();
		try {
			String query = "select * from Employee";
			PreparedStatement pstmt = empDaoImpl.connection.prepareStatement(query);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next()) {
				emp emp = new emp();
				emp.setEmpId(rs.getInt("eno"));
				emp.setEmpName(rs.getString("ename"));
				emp.setEmpSal(rs.getDouble("esal"));
				list.add(emp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	

	public emp getemp(int eno) {
		emp emp = new emp();
		try {
			String query = "select * from Employee where eno = ?";
			PreparedStatement pstmt = empDaoImpl.connection.prepareStatement(query);
			pstmt.setInt(1, eno);
			ResultSet rs = pstmt.executeQuery();
			emp.setEmpId(rs.getInt("eno"));
			emp.setEmpName(rs.getString("ename"));
			emp.setEmpSal(rs.getDouble("esal"));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return emp;
	}

	@Override
	public int destroyEmp(int eno) {
		// TODO Auto-generated method stub
		return 0;
	}


}