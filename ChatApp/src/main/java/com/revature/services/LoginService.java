package com.revature.services;

import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.daos.LoginDao;
import com.revature.util.HttpException;

public class LoginService {
	LoginDao loginDao = new LoginDao();

	public int login(Credentials credentials) {
		
		if(credentials.getPassword() == null || credentials.getUsername() == null) {
			throw new HttpException(422);
		}
		
		User user = loginDao.getPasswordByUsername(credentials.getUsername());
		
		if (!user.getPassword().equals(credentials.getPassword())) {
			throw new HttpException(400);
		}
		
		return user.getId();
	}

	public LoginService(LoginDao loginDao) {
		super();
		this.loginDao = loginDao;
	}

	public LoginService() {
		super();
		this.loginDao = new LoginDao();
	}
}
