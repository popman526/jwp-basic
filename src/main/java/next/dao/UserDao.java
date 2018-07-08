package next.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.PreparedStatementSetter;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
    public void insert(User user) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    	jdbcTemplate.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

    public void update(User user) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "UPDATE USERS SET userId = ?, password = ?, name = ?, email = ? where userId = ?";
    	jdbcTemplate.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    }

    public List<User> findAll() {
		RowMapper<User> rm = new RowMapper<User>() {
			
			@Override
			public List<User> mapRow(ResultSet rs) throws SQLException {
				List<User> list = new ArrayList<>();
				User user = null;
				while (rs.next()) {
				    user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
				            rs.getString("email"));
				    list.add(user);
				}
				return list;
			}
		};
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		String sql = "SELECT userId, password, name, email FROM USERS";
		return jdbcTemplate.query(sql, rm);
		
    }

    public User findByUserId(String userId) {
		RowMapper<User> rm = new RowMapper<User>() {
			
			@Override
			public List<User> mapRow(ResultSet rs) throws SQLException {
				List<User> list = new ArrayList<>();
				User user = null;
				while (rs.next()) {
				    user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
				            rs.getString("email"));
				    list.add(user);
				}
				return list;
			}
		};
		
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	String sql = "SELECT userId, password, name, email FROM USERS WHERE userId=?";
		return (User)jdbcTemplate.queryForObject(sql, rm, userId);
    }
}
