package com.capgemini.pawjdbc.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import com.capgemini.pawjdbc.bean.AccountDetails;
import com.capgemini.pawjdbc.bean.CustomerDetails;
import com.capgemini.pawjdbc.exception.NoLoginIdFound;
import com.capgemini.pawjdbc.service.PaymentAppService;
import com.capgemini.pawjdbc.service.PaymentAppValidation;




public class PaymentAppClient {



	public static void main(String[] args) {
		
		
		
		
		
		int choice1=-1;
		while(choice1!=3) {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		try {
		System.out.println(".....................................................");
		System.out.println("............PAYMENT WALLET APPLICATION...............");
		System.out.println(".....................................................");
		System.out.println("..                                                 ..");
		System.out.println("..              1.SignUp                           ..");
		System.out.println("..              2.Login                            ..");
		System.out.println("..              3.Exit                             ..");
		System.out.println("..                                                 ..");
		System.out.println(".....................................................");
		System.out.println(".....................................................");
		System.out.println("                                                     ");
		
		 choice1 = Integer.parseInt(br.readLine());
		switch (choice1) {
		case 1:
			createAccount();
		break;
			
		case 2:
			
			try {
			boolean b= loginAccount();
if(b) {
			int choice = -1;
			while (choice != 6) {

				System.out.println("                                                     ");
				System.out.println(".....................................................");
				System.out.println("............PAYMENT WALLET APPLICATION...............");
				System.out.println(".....................................................");
				System.out.println("..                                                 ..");
				System.out.println("..              1.Show Balance                     ..");
				System.out.println("..              2.Deposit                          ..");
				System.out.println("..              3.Withdraw                         ..");
				System.out.println("..              4.Fund Transfer                    ..");
				System.out.println("..              5.Print Transaction                ..");
				System.out.println("..              6.Exit                             ..");
				System.out.println("..                                                 ..");
				System.out.println(".....................................................");
				System.out.println(".....................................................");
				System.out.println("                                                     ");
				
				
				
				
					choice = Integer.parseInt(br.readLine());
			
			

			switch (choice) {
			case 1:
				showBalance();
				break;
			case 2:
				deposit();
				break;
			case 3:
				withdraw();
				break;			
			case 4:
				fundTransfer();
				break;			
			case 5:
				printTransaction();
				break;			
			case 6:
				System.out.println("Thank you");
			
				break;	
					
						
			default:
				System.err.println("Enter a valid choice");
				break;
			}
			
		}
}else {
	try {
		throw new NoLoginIdFound();
	} catch (NoLoginIdFound e) {
		System.err.println("enter valid login details");
	}
}} catch (NumberFormatException e) {
	System.err.println("Enter valid details");
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
		case 3:
			System.out.println("Thank you");
			break;
		default:
			System.err.println("Enter a valid choice");
			break;
		
			}} catch (NumberFormatException e) {
				System.err.println("Enter valid details");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		}
		
		}
	
	
	
	
	

	
	static boolean b=false;
	public static boolean loginAccount()
	{
		 PaymentAppService service = new PaymentAppService();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		System.out.println("Enter Your Username");
		String username;
		try {
			username = br.readLine();
		
		System.out.println("Enter Your Password");
		String password = br.readLine();
		
		
		 b= service.loginAccount(username, password);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(b)
		{
			return true;
		}
		else
		{
			System.err.println("enter valid username and password");
		return false;
		}
	
		
	}
	
	
	public static void createAccount()
	{
		 AccountDetails accountDetails = new AccountDetails();
		 CustomerDetails customerDetails=new CustomerDetails();
	
		try {

			 PaymentAppService service = new PaymentAppService();
	
			 PaymentAppValidation validate = new PaymentAppValidation();
		
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			long accountNumber = (long) ((Math.random() * 1234) + 99999);
			System.out.println("Enter your Name: ");
			String customerName = br.readLine();
			System.out.println("Enter your 10 digit PhoneNo: ");
			String phoneNo = br.readLine();
			System.out.println("Enter your Age: ");
			int age = Integer.parseInt(br.readLine());
			System.out.println("Enter your Gender: ");
			String gender = br.readLine();
			System.out.println("Enter your Address");
			String address = br.readLine();
			System.out.println("Enter your 12 digit AadharNo: ");
			String aadharNo = br.readLine();
			System.out.println("Enter your opening balance: ");
			double balance =Double.parseDouble( br.readLine());
			System.out.println("Enter your username: ");
			String username = br.readLine();
			System.out.println("Enter your password: ");
			String password = br.readLine();
		
			

			
			
			
			boolean isValidPhoneNo = validate.validatePhoneNo(phoneNo);
			boolean isValidAadharNo = validate.validateAadharNo(aadharNo);
			boolean isValidGender = validate.validateGender(gender);
			

			if (isValidPhoneNo && isValidAadharNo && isValidGender) {
				
				accountDetails.setAccountNumber(accountNumber);
				customerDetails.setCustomerName(customerName);
				customerDetails.setPhoneNo(phoneNo);
				customerDetails.setAge(age);
				customerDetails.setGender(gender);
				customerDetails.setAddress(address);
				customerDetails.setAadharNo(aadharNo);
				accountDetails.setBalance(balance);
				customerDetails.setUsername(username);
				customerDetails.setPassword(password);
				accountDetails.setCustomerDetails(customerDetails);
			
				
				int worked = service.createAccount(accountDetails);
				if (worked == 1) {
					System.out.println("Account created with Account no.  " + accountNumber);
					System.out.println("Your UserName is: "+username);
					System.out.println("Your Password is :"+password);
					
				}
				else {
					System.out.println("Account not created");
				}
			}
			else
			{
				System.out.println("Account not created,enter valid Details");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	public static void showBalance()
	{
		 PaymentAppService service = new PaymentAppService();
		double bal= service.showBalance();
		System.out.println("Your wallet balance is: "+bal);
	
	}
	
	public static void deposit() {
		 PaymentAppService service = new PaymentAppService();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter amount to deposit");
		try {
			double amount=Double.parseDouble(br.readLine());
			boolean isDepositAmount = service.deposit(amount);
			if(isDepositAmount)
			{
				System.out.println("Amount is deposited in your wallet");
			
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public static void withdraw()
	{
		PaymentAppService service = new PaymentAppService();
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the amount you want to withdraw");
		
		try {
			double amount=Double.parseDouble(br.readLine());
			boolean isWithdrawAmount = service.withdraw(amount);
			if(isWithdrawAmount)
			{
				System.out.println("Amount is debited from your wallet");
			}
			else
			{
				System.err.println("balance is low");
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public static void fundTransfer()
	{
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		System.out.println(" Enter the Account Number you want to transfer the amount");
		int accountNo;
		try {
			accountNo = Integer.parseInt(br.readLine());
		
		System.out.println("Enter Amount to Transfer");
		double amount = Double.parseDouble(br.readLine());
		
		PaymentAppService service = new PaymentAppService();
		b= service.fundTransfer(accountNo,amount);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(b)
		{
			System.out.println("Fund is Successfully Transfered");
		}
		else
		{
			System.err.println("balance is low");
		}
		
	}
	
	
	public static void printTransaction()
	{
		PaymentAppService service = new PaymentAppService();
		String transaction=service.printTransaction();
	
		
				System.out.println(transaction);
			
	
	
		
	}
	
	
	}
	

