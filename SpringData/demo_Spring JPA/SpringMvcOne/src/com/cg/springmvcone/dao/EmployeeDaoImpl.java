package com.cg.springmvcone.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.cg.springmvcone.dto.Employee;
@Repository("employeedao")
public class EmployeeDaoImpl implements Employeedao {
    @PersistenceContext
	private EntityManager entitymanager;
	@Override
	public void addEmployee(Employee emp) {
		// TODO Auto-generated method stub
		
		entitymanager.persist(emp);
		entitymanager.flush();
		
	}
	@Override
	public List<Employee> showAllEmployee() {
		// TODO Auto-generated method stub
		Query query=entitymanager.createQuery("FROM Employee");
		return query.getResultList();
	}
	@Override
	public List<Employee> searchEmployee(Employee emp) {
		// TODO Auto-generated method stub
		Query queryOne=entitymanager.createQuery
				("FROM Employee where empId=:emp_id");
		queryOne.setParameter("emp_id",emp.getEmpId());
		List<Employee> empList=queryOne.getResultList();
		
		return empList;
	}

}
