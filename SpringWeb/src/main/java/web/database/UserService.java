package web.database;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementSetter;
import org.springframework.stereotype.Service;

import web.database.mapper.UserRowMapper;
import web.model.User;

@Service
public class UserService implements UserServiceInterface{
	private static final String SELECT_USER = "SELECT name FROM users WHERE login=? AND password=?";
	private static final String INSERT_USER = "INSERT INTO users (name, login, password, gender, location, comment) VALUES(?,?,?,?,?,?)";
	private static final Logger LOGGER = Logger.getLogger(UserService.class.getName());

	private final JdbcTemplate jdbcTemplate;
	
	public UserService(JdbcTemplate jdbcTemplate) {
		super();
		this.jdbcTemplate = jdbcTemplate;
	}
	
	public String checkAuthorization(String login, String password) {
		String user;
		LOGGER.info("Starting query");
		try {
		user = jdbcTemplate.queryForObject(SELECT_USER, new Object[] {login, encrypt(password)}, String.class);
		} catch(EmptyResultDataAccessException e) {
			return null;
		}
		LOGGER.info("Query success");
		return user;
	}
	
	
	public void addUser(String name, String login, String password, String gender, String location, String comment) {
		LOGGER.info("Starting query");
		jdbcTemplate.update(INSERT_USER, name, login, encrypt(password), gender, location, comment);
		LOGGER.info("Query success");
	}
	
	public String encrypt(String text) {
		MessageDigest md = null;
		String result = null;
		if (text != null) {
			try {
				md = MessageDigest.getInstance("SHA-256");
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}
			md.update(text.getBytes(StandardCharsets.UTF_8));
			result = String.format("%064x", new BigInteger(1, md.digest()));
		}
		return result;
	}
	

}
