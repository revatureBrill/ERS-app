package com.revature.services;

import com.revature.beans.Credentials;
import com.revature.daos.SignupDao;

public class SignupService {

	private SignupDao signupDao = new SignupDao();
	
	public boolean signup(Credentials credentials) {
		// Ensure that username meets username standards
		
		// Ensure that password meets password standards
		
		// Check if username already exists

		return signupDao.Signup(credentials);
		
		
	}

}
