package com.cg.springmvcone.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cg.springmvcone.dao.Employeedao;
import com.cg.springmvcone.dto.Employee;
@Service("employeeservice")
@Transactional
public class EmployeeServiceImpl implements EmployeeService {
    @Autowired
	Employeedao employeedao;
	@Override
	public void addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		employeedao.addEmployee(emp);
		
	}
	@Override
	public List<Employee> showAllEmployee() {
		// TODO Auto-generated method stub
		return employeedao.showAllEmployee();
	}
	@Override
	public List<Employee> searchEmployee(Employee emp) {
		// TODO Auto-generated method stub
		return employeedao.searchEmployee(emp);
	}

}
