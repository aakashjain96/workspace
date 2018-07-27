package com.capgemini.pawjdbc.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.capgemini.pawjdbc.bean.AccountDetails;
import com.capgemini.pawjdbc.util.JdbcUtil;
import com.mysql.jdbc.PreparedStatement;

public class PaymentAppDAO implements IPaymentAppDAO {

	public static AccountDetails accountDetails;
	public static Map<String, AccountDetails> map = new HashMap<String, AccountDetails>();
	public static Connection conn=JdbcUtil.getConnection();
	static String aadharNo;
	static String recieverAadharNo;

	int transactionId = (int) ((Math.random() * 123) + 999);

	public boolean loginAccount(String username, String password) {
		
		try {
			Statement stat=conn.createStatement();
		String selectQuery ="select customerdetails.username,customerdetails.password,customerdetails.aadharno from customerdetails,accountdetails where customerdetails.aadharno=accountdetails.aadharno";
		
		ResultSet rs=stat.executeQuery(selectQuery);
		while (rs.next()) {
		String uname=rs.getString(1);
		String upass=rs.getString(2);
		 aadharNo=rs.getString(3);
			if (uname.equals(username) && upass.equals(password)) {
				
				return true;
			}
		}
		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; 
		
		
	}

	public int createAccount(AccountDetails accountDetails) {
		
		String insertQuery1 ="insert INTO ACCOUNTDETAILS values(?,?,?)";
		String insertQuery ="insert INTO CUSTOMERDETAILS values(?,?,?,?,?,?,?,?)";
		String insertQuery2="insert INTO PASSBOOK values(?,?)";
		
		 try {
			 java.sql.PreparedStatement pstat1= conn.prepareStatement(insertQuery1);
			 pstat1.setLong(1, accountDetails.getAccountNumber());
			 pstat1.setDouble(2, accountDetails.getBalance());
			 pstat1.setString(3, accountDetails.getCustomerDetails().getAadharNo());
					
			 java.sql.PreparedStatement pstat= conn.prepareStatement(insertQuery);
			 pstat.setString(1, accountDetails.getCustomerDetails().getUsername());
			 pstat.setString(2, accountDetails.getCustomerDetails().getPassword());
			 pstat.setString(3, accountDetails.getCustomerDetails().getCustomerName());
			 pstat.setString(4, accountDetails.getCustomerDetails().getPhoneNo());
			 pstat.setInt(5, accountDetails.getCustomerDetails().getAge());
			 pstat.setString(6, accountDetails.getCustomerDetails().getGender());
			 pstat.setString(7, accountDetails.getCustomerDetails().getAddress());
			 pstat.setString(8, accountDetails.getCustomerDetails().getAadharNo());
			 
			 
			 java.sql.PreparedStatement pstat2= conn.prepareStatement(insertQuery2);
			 pstat2.setString(1, "Transactions");
			 pstat2.setString(2, accountDetails.getCustomerDetails().getAadharNo());
			 
			
			 pstat.executeUpdate();
			 pstat1.executeUpdate();
			 pstat2.executeUpdate();
			 
			 
			 
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 1;
	}

	public double showBalance() {
		
		try {
			Statement stat=conn.createStatement();
			String selectQuery ="select accountdetails.balance from accountdetails where aadharNo='"+aadharNo+"' ";
			ResultSet rs=stat.executeQuery(selectQuery);
			while (rs.next()) {
				double bal=rs.getDouble(1);
				return bal;
			}} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
		
		
	}

	public boolean deposit(double amount) {
		
		String updateQuery ="update accountdetails set balance=balance+? where aadharno=?";
		 try {
			java.sql.PreparedStatement pstat= conn.prepareStatement(updateQuery);
			pstat.setDouble(1, amount);
			pstat.setString(2, aadharNo);
			pstat.executeUpdate();
		
			String dep=transactionId +"  Amount of "+amount+" is deposited";
			transactionAdd(dep);
			
			
		} catch (SQLException e) {
			System.err.println("amount not deposited");
			return false;
		}
		return true;
	}

	public boolean withdraw(double amount) {
		
		Double bal = null;
		 try {
			 Statement stat=conn.createStatement();
			 String selectQuery = "select balance from accountDetails where aadharno='"+aadharNo+"' ";
			 ResultSet rs=stat.executeQuery(selectQuery);
			while (rs.next()) {
			 bal=rs.getDouble(1);
				}
		 	}
		 catch (SQLException e) {
				System.err.println("invalid aadhar no.");
			
			}
		if(bal>=amount) {
		String updateQuery ="update accountdetails set balance=balance-? where aadharno=?";
		
		 try {
			java.sql.PreparedStatement pstat= conn.prepareStatement(updateQuery);
			pstat.setDouble(1, amount);
			pstat.setString(2, aadharNo);
			pstat.executeUpdate();
			
			String with=transactionId +"  Amount of "+amount+" is withdrawn";
			transactionAdd(with);
	
		} catch (SQLException e) {
			System.err.println("amount not withdrawn");
		
		}
		return true;}
	else {
		return false;
	}
		
		
	}

	public boolean fundTransfer(int accountNo, double amount) {
		
		Double bal = null;
		 try {
			 Statement stat=conn.createStatement();
			 String selectQuery = "select balance from accountDetails where aadharno='"+aadharNo+"' ";
			 ResultSet rs=stat.executeQuery(selectQuery);
			while (rs.next()) {
			 bal=rs.getDouble(1);
				}
		 	}
		 catch (SQLException e) {
				System.err.println("Fund Not Transferred");
			
			}
		if(bal>=amount) {
		try {		
		String updateQuery1 ="update accountdetails set balance=balance+? where accountno=?";
				
		java.sql.PreparedStatement pstat1= conn.prepareStatement(updateQuery1);
		pstat1.setDouble(1, amount);
		pstat1.setLong(2, accountNo);
		pstat1.executeUpdate();		
		
		Statement stat=conn.createStatement();
		String selectQuery ="select accountdetails.aadharno from accountdetails where accountno='"+accountNo+"' ";
		ResultSet rs=stat.executeQuery(selectQuery);
		while (rs.next()) {
			recieverAadharNo=rs.getString(1);
		}
			String transfer1=transactionId +"  Amount of "+amount+"is transferred";
			String recieverupdateQuerytransaction ="update passbook set transaction=CONCAT(transaction,?) where aadhartransaction=?";
			java.sql.PreparedStatement pstattransfer;
			try {
			
				pstattransfer = conn.prepareStatement(recieverupdateQuerytransaction);
				pstattransfer.setString(1,"\n"+ transfer1);
				pstattransfer.setString(2, recieverAadharNo);
				pstattransfer.executeUpdate();
				
			
			} catch (SQLException e) {
			System.err.println("passbook not updated");
			}
			
			
		
		String updateQuery2 ="update accountdetails set balance=balance-? where aadharno=?";
		
		java.sql.PreparedStatement pstat2= conn.prepareStatement(updateQuery2);
		pstat2.setDouble(1, amount);
		pstat2.setString(2, aadharNo);
		pstat2.executeUpdate();		
		String transfer=transactionId +"  Amount of "+amount+" is transferred";
		transactionAdd(transfer);
		return true;	
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
		else {
			return false;
		}
	}
	public String printTransaction() {
		try {
			Statement stat=conn.createStatement();
			String selectQuery ="select transaction from passbook where aadhartransaction='"+aadharNo+"' ";
			ResultSet rs=stat.executeQuery(selectQuery);
			while (rs.next()) {
				String bal=rs.getString(1);
				return bal;
			}} catch (SQLException e) {
			System.err.println("passbook not updated");
			e.printStackTrace();
		}
		return null;

	}
	
	public void transactionAdd(String s)
	{
	
		String updateQuerytransaction ="update passbook set transaction=CONCAT(transaction,?) where aadhartransaction=?";
		java.sql.PreparedStatement pstat1;
		try {
		
			pstat1 = conn.prepareStatement(updateQuerytransaction);
			pstat1.setString(1,"\n"+ s);
			pstat1.setString(2, aadharNo);
			pstat1.executeUpdate();
			
		
		} catch (SQLException e) {
			System.err.println("passbook not updated");
		}
	
	}
	
}
