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
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameter(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
		};
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
		
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
    	jdbcTemplate.executeUpdate(sql, pss);
    }

    public void update(User user) {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
    		
    		@Override
    		public void setParameter(PreparedStatement pstmt) throws SQLException {
    			pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getUserId());
    		}
    	};
    	JdbcTemplate jdbcTemplate = new JdbcTemplate();
		String sql = "UPDATE USERS SET userId = ?, password = ?, name = ?, email = ? where userId = ?";
    	jdbcTemplate.executeUpdate(sql, pss);
    }

    public List<User> findAll() {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameter(PreparedStatement pstmt) throws SQLException {
				// TODO Auto-generated method stub
				
			}
		};
		RowMapper rm = new RowMapper() {
			
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
		return jdbcTemplate.query(sql, pss, rm);
		
    }

    public User findByUserId(String userId) {
    	PreparedStatementSetter pss = new PreparedStatementSetter() {
			
			@Override
			public void setParameter(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, userId);
			}
		};
		RowMapper rm = new RowMapper() {
			
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
		return (User)jdbcTemplate.queryForObject(sql, pss, rm);
    }
}
