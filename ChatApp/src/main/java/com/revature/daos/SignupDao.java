package com.revature.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.revature.beans.Credentials;
import com.revature.util.ConnectionUtil;

public class SignupDao {
	public void Signup(Credentials credentials) {
		try (Connection conn = ConnectionUtil.getConnection()) {
			String sql = "INSERT INTO chat_users (username, password) VALUES (?, ?)";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			// Assigning parameters
			ps.setString(1, credentials.getUsername());
			ps.setString(2, credentials.getPassword());
			
			ps.executeUpdate();
			
		} catch(SQLException e) {
			e.printStackTrace();
		}
	}
}
