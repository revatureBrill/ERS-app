package com.revature.services;

import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.daos.LoginDao;
import com.revature.util.HttpException;

public class LoginService {
	LoginDao loginDao = new LoginDao();

	public int login(Credentials credentials) {
		// attempt to retrieve username/password from database
		User user = loginDao.getPasswordByUsername(credentials.getUsername());
		// compare them
		if (!user.getPassword().equals(credentials.getPassword())) {
			throw new HttpException(400);
		}
		return user.getId();
	}
}
