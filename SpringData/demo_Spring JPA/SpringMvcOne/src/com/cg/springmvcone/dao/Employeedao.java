package com.cg.springmvcone.dao;

import java.util.List;

import com.cg.springmvcone.dto.Employee;

public interface Employeedao {
	public void addEmployee(Employee emp);
	public List<Employee> showAllEmployee();
	public List<Employee> searchEmployee(Employee emp);
}
