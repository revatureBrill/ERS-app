package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.revature.beans.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.HttpException;

/**
 * LoginDao is responsible for performing persistance related tasks on a database for
 * Login related operations.
 * 
 * @author mitch
 */
public class LoginDao {

	/**
	 * Attempts to retrieve User instance from a provided username.
	 * @param username - Username as a string
	 * @return User instance
	 * @throws HttpException if user does not exist with status 400 and when 
	 * 		any SQLException occurs with status 500
	 */
	public User getPasswordByUsername(String username) {
		try(Connection conn = ConnectionUtil.getConnection()) {
			String sql = "SELECT id, password FROM chat_users WHERE username = ?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()) {
				String password = rs.getString("password");
				int id = rs.getInt("id");
				return new User(id, username, password);
			} else {
				throw new HttpException(400);
			}
			
		} catch(SQLException e) {
			e.printStackTrace();
			throw new HttpException(500);
		}
	}

}
