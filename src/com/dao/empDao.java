package com.dao;

import java.util.List;

import com.model.emp;

public interface empDao {
	
	   public int createEmp(emp e);
	   public int updateEmp(emp e);
	   public int deleteEmp(int empId);
	   public List getemp();
	   public emp getemp(int empId);
	public int destroyEmp(int eno);
}
