package com.capgemini.pawjdbc.bean;

public class CustomerDetails {
	private String username;
	private String password;
	private String customerName;
	private String phoneNo;
	private int age;
	private String gender;
	private String address;
	private String aadharNo;
	
	
	
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getCustomerName() {
		return customerName;
	}
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	public String getPhoneNo() {
		return phoneNo;
	}
	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAadharNo() {
		return aadharNo;
	}
	public void setAadharNo(String aadharNo) {
		this.aadharNo = aadharNo;
	}
	
	
	
	@Override
	public String toString() {
		return "CustomerDetails [username=" + username + ", password=" + password + ", customerName=" + customerName
				+ ", phoneNo=" + phoneNo + ", age=" + age + ", gender=" + gender + ", address=" + address
				+ ", aadharNo=" + aadharNo  + "]";
	}
	
}
	
	
