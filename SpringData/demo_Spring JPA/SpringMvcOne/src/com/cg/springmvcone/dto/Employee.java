package com.cg.springmvcone.dto;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Table(name="EMPLOYEONE")
public class Employee {
	@Id
	@Column(name="emp_id")
	@NotNull(message="Id field will not be empty")
	Integer empId;
	@Column(name="emp_name")
	@NotEmpty(message="Name should not empty")
	String empName;
	@Column(name="emp_qul")
	String empQualification;
	public Integer getEmpId() {
		return empId;
	}
	public void setEmpId(Integer empId) {
		this.empId = empId;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public String getEmpQualification() {
		return empQualification;
	}
	public void setEmpQualification(String empQualification) {
		this.empQualification = empQualification;
	}
	@Override
	public String toString() {
		return "Employee [empId=" + empId + ", empName=" + empName
				+ ", empQualification=" + empQualification + "]";
	}
	
	

}
