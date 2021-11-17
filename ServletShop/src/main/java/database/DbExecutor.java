package database;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class DbExecutor implements DataBaseDAOInterface {
	public static final String SELECT_USER = "SELECT name FROM users WHERE login=? AND password=?";
	public static final String INSERT_USER = "INSERT INTO users (name, login, password, gender, location, comment) VALUES(?,?,?,?,?,?)";

	public String checkLogin(String login, String password) {
		String name = null;
		Connection conn = DbConnector.getInstance().getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(SELECT_USER);
			ps.setString(1, login);
			ps.setString(2, encrypt(password));
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				name = rs.getString(1);
			}
		} catch (SQLException e) {

		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {

			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {

			}
		}
		return name;
	}

	public void addUser(String name, String login, String password, String gender, String location, String comment) {
		Connection conn = DbConnector.getInstance().getConnection();
		PreparedStatement ps = null;
		try {
			ps = conn.prepareStatement(INSERT_USER);
			ps.setString(1, name);
			ps.setString(2, login);
			ps.setString(3, encrypt(password));
			ps.setString(4, gender);
			ps.setString(5, location);
			ps.setString(6, comment);
			ps.executeUpdate();
		} catch (SQLException e) {
		} finally {
			try {
				if (ps != null) {
					ps.close();
				}
			} catch (SQLException ex) {

			}
			try {
				if (conn != null) {
					conn.close();
				}
			} catch (SQLException ex) {

			}
		}
	}

	@Override
	public String encrypt(String text) {
		MessageDigest md = null;
		String result = null;
		if (text != null) {
			try {
				md = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			md.update(text.getBytes(StandardCharsets.UTF_8));
			result = String.format("%064x", new BigInteger(1, md.digest()));
		}
		return result;
	}
}
