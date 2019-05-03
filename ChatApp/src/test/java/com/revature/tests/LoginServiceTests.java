package com.revature.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import org.junit.Test;
import org.mockito.Mockito;

import com.revature.beans.Credentials;
import com.revature.beans.User;
import com.revature.daos.LoginDao;
import com.revature.services.LoginService;
import com.revature.util.HttpException;

public class LoginServiceTests {

	LoginDao mockLoginDao = Mockito.mock(LoginDao.class);
	LoginService loginService = new LoginService(mockLoginDao);
	
	@Test
	public void unprocessableCredentials() throws Exception {
		Credentials nullUsernameCredentials = new Credentials(null, "password");
		Credentials nullPasswordCredentials = new Credentials("username", null);
		Credentials nullCredentials = new Credentials(null, null);
		
		HttpException expected = null;
		try {
			loginService.login(nullUsernameCredentials);
		} catch (HttpException e) {
			expected = e;
		}
		
		assertNotNull("HttpException should be thrown when a null password is provided", expected);
		assertEquals("HttpException with status 422 should be issued when null password is provided", 422, expected.getStatus());
		
		expected = null;
		try {
			loginService.login(nullPasswordCredentials);
		} catch (HttpException e) {
			expected = e;
		}
		
		assertNotNull("HttpException should be thrown when a null username and password is provided", expected);
		assertEquals("HttpException with status 422 should be issued when null username and password is provided", 422, expected.getStatus());
		
		expected = null;
		try {
			loginService.login(nullCredentials);
		} catch (HttpException e) {
			expected = e;
		}
		
		assertNotNull("HttpException should be thrown when a null username is provided", expected);
		assertEquals("HttpException with status 422 should be issued when null username is provided", 422, expected.getStatus());
		
	}
	
	@Test
	public void badCredentials() throws Exception {
		User user = new User(1, "test-username", "test-password");
		Credentials badCredentials = new Credentials("test-username", "bad-password");
		
		// mock loginMethod to always return the previously defined user
		Mockito.when(mockLoginDao.getPasswordByUsername(badCredentials.getUsername()))
				.thenReturn(user);
		
		HttpException expected = null;
		
		try {
			loginService.login(badCredentials);
		} catch(HttpException e) {
			expected = e;
		}
		
		assertNotNull("HttpException should be thrown when passwords don't match.", expected);
		assertEquals("When passwords don't match, status code should be 400", 400, expected.getStatus());
		
	}

	@Test
	public void loginSuccessful() throws Exception {
		User user = new User(1, "test-username", "test-password");
		Credentials goodCredentials = new Credentials("test-username", "test-password");
		
		// mock loginMethod to always return the previously defined user
		Mockito.when(mockLoginDao.getPasswordByUsername(goodCredentials.getUsername()))
				.thenReturn(user);
		
		int id = loginService.login(goodCredentials);
		
		assertEquals("Id returned from method should equal id of user returned by mock dao", user.getId(), id);
	}
}
