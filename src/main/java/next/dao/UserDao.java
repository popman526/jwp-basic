package next.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import core.jdbc.ConnectionManager;
import core.jdbc.JdbcTemplate;
import next.model.User;

public class UserDao {
    public void insert(User user) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			
			@Override
			public void setParameter(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
			}
			
			@Override
			public String getSql() {
				return "INSERT INTO USERS VALUES (?, ?, ?, ?)";
			}
		};
    	jdbcTemplate.executeUpdate();
    }

    public void setInsertParameter(PreparedStatement pstmt) throws SQLException {
	}

	public String getInsertSql() {
		String sql = "INSERT INTO USERS VALUES (?, ?, ?, ?)";
		return sql;
	}

    public void update(User user) throws SQLException {
    	JdbcTemplate jdbcTemplate = new JdbcTemplate() {
			
			@Override
			public void setParameter(PreparedStatement pstmt) throws SQLException {
				pstmt.setString(1, user.getUserId());
				pstmt.setString(2, user.getPassword());
				pstmt.setString(3, user.getName());
				pstmt.setString(4, user.getEmail());
				pstmt.setString(5, user.getUserId());
			}
			
			@Override
			public String getSql() throws SQLException {
				return "UPDATE USERS SET userId = ?, password = ?, name = ?, email = ? where userId = ?";
			}
		};
    	jdbcTemplate.executeUpdate();
    }

	public void setUpdateParameter(User user, PreparedStatement pstmt) throws SQLException {
	}

	public String getUpdateSql() {
		String sql = "UPDATE USERS SET userId = ?, password = ?, name = ?, email = ? where userId = ?";
		return sql;
	}

    public List<User> findAll() throws SQLException {
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS";
            pstmt = con.prepareStatement(sql);

            rs = pstmt.executeQuery();
            
            List<User> list = new ArrayList<>();
            User user = null;
            while (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
                list.add(user);
            }

            return list;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }

    public User findByUserId(String userId) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            pstmt.setString(1, userId);

            rs = pstmt.executeQuery();

            User user = null;
            if (rs.next()) {
                user = new User(rs.getString("userId"), rs.getString("password"), rs.getString("name"),
                        rs.getString("email"));
            }

            return user;
        } finally {
            if (rs != null) {
                rs.close();
            }
            if (pstmt != null) {
                pstmt.close();
            }
            if (con != null) {
                con.close();
            }
        }
    }
}
