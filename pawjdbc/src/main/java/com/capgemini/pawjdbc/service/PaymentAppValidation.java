package com.capgemini.pawjdbc.service;

public class PaymentAppValidation {

	public boolean validatePhoneNo(String phoneNo) {
		if (phoneNo.length() == 10 && phoneNo!=null) {
			return true;
		} else {
			System.err.println("Enter Valid Phone No.");
			return false;
		}
	}

	public boolean validateAadharNo(String aadharNo) {
		if (aadharNo.length() == 12 && aadharNo!=null) {
			return true;
		} else {
			System.err.println("Enter Valid Aadhar No.");
			return false;
		}

	}

	public boolean validateGender(String gender) {
		if ((gender.equalsIgnoreCase("male") || gender.equalsIgnoreCase("female") || gender.equalsIgnoreCase("others"))&& gender!=null) {
			return true;
		} else {
			System.err.println("Enter Valid Gender");
			return false;
		}
	}
	
}
