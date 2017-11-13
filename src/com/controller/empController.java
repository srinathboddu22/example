package com.controller;


import java.util.List;
import java.util.Scanner;

import com.dao.empDaoImpl;
import com.model.emp;


public class empController {

	static Scanner sc = new Scanner(System.in);
	private static com.dao.empDao empDao = new empDaoImpl();

	public static void main(String[] args) {

		System.out.println("Welcome to whishworks");
		empLogin();

	}

	public static void empLogin() {
		System.out.println("Please login here");
		System.out.println("Enter Username: ");
		String user = sc.next();
		System.out.println("Enter Password: ");
		String pass = sc.next();
		if (user.equals("admin") && pass.equals("pass")) {
			listView();
		} else {
			empLogin();
		}
	}

	public static void listView() {
		
		System.out.println("Enter The operation you need to perform");
		System.out.println(
				"1.Add an Employee \n2.Update current Employee Salary \n3.List all Employees \n4.Delete a Particular Employee\n");
		int i = sc.nextInt();

		switch (i) {
		case 1:
			addEmployee();
			break;
		case 2:
			updateEmployeeSal();
			break;
		case 3:
			listOfEmployees();
			break;
		case 4:
			deleteEmployee();
			break;
		default:
			System.out.println("Invalid Operation, Please choose correct option:");
			listView();
		}
	}

	public static void addEmployee() {
		System.out.println("Enter EmpId: ");
		int eno = sc.nextInt();
		System.out.println("Enter EmpName: ");
		String ename = sc.next();
		System.out.println("Enter EmpSalary: ");
		Double esal = sc.nextDouble();
        emp emp = new emp();
		emp.setEmpId(eno);
		emp.setEmpName(ename);
		emp.setEmpSal(esal);
		if (empDao.createEmp(emp) != 0) {
			System.out.println("Successflly Created");
			listView();
		} else {
			System.out.println("Sorry, try this once again");
			addEmployee();
		}
	}

	public static void updateEmployeeSal() {
		System.out.println("Enter empId:");
		int eno = sc.nextInt();
		System.out.println("Enter empSal: ");
		Double esal = sc.nextDouble();

		emp emp = new emp();
		emp.setEmpId(eno);
		emp.setEmpName("");
		emp.setEmpSal(eno);
		if (empDao.updateEmp(emp) != 0) {
			System.out.println("Successfully Updated");
			listView();
		} else {
			System.out.println("Sorry, try this once again");
			updateEmployeeSal();
		}
	}

	public static void listOfEmployees() {

		List<emp> list = empDao.getemp();
		for (emp emp : list) {
			System.out.println(emp);
		}
		listView();
	}

	public static void deleteEmployee() {
		System.out.println("Enter eno:");
		int eno = sc.nextInt();

		emp emp = new emp();
		emp.setEmpId(eno);

		if (empDao.destroyEmp(eno) != 0) {
			System.out.println("Succesfully Deleted"); 
			listView();
		} else {
			System.out.println("Sorry, try this once again");
			updateEmployeeSal();
		}
	}
}
