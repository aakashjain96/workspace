package com.capgemini.pawjdbc.dao;




import java.util.List;
import java.util.Map;

import com.capgemini.pawjdbc.bean.AccountDetails;



public interface IPaymentAppDAO {

	public boolean loginAccount(String username, String password);
	public int createAccount(AccountDetails accountDetails);
	public double showBalance();
	public boolean deposit(double amount);
	public boolean withdraw(double amount);
	public boolean fundTransfer(int accNo,double amount);
	public String printTransaction();
	
}
