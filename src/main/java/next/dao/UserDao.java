package next.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import core.jdbc.JdbcTemplate;
import core.jdbc.RowMapper;
import next.model.User;

public class UserDao {
    public void insert(User user) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	
    	String sql = createQueryForInsert();
    	jdbcTemplate.executeUpdate(sql, user.getUserId(), user.getPassword(), user.getName(), user.getEmail());
    }

	private String createQueryForInsert() {
		return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
	}

    public void update(User user) {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
    	String sql = createQueryForUpdate();
    	jdbcTemplate.executeUpdate(sql, user.getPassword(), user.getName(), user.getEmail(), user.getUserId());
    	
    }

	private String createQueryForUpdate() {
		return "UPDATE USERS SET password=?, name=?, email=? WHERE userId=?";
	}

    public List<User> findAll() {
    	
    	RowMapper<User> rm = rs ->
		new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
	            rs.getString("email"));

		JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = createQueryForFindByAll();
		
		return (List<User>)jdbcTemplate.query(sql, rm);
    }

    private String createQueryForFindByAll() {
		return "SELECT userId, password, name, email FROM USERS";

	}

	public User findByUserId(String userId) {
		RowMapper<User> rm = rs ->
			new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
		            rs.getString("email"));
		
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = createQueryForFindById();
		return jdbcTemplate.excuteQuery(sql, rm, userId);
    }

	private String createQueryForFindById() {
		return "SELECT userId, password, name, email FROM USERS WHERE userid=?";
	}
}