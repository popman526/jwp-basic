package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import next.dao.UserDao;
import next.model.User;

public abstract class JdbcTemplate {
	public void insert(User user) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = getSql();
            pstmt = con.prepareStatement(sql);
            setParameter(user, pstmt);

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }
	
	public abstract void setParameter(User user, PreparedStatement pstmt) throws SQLException;

	public abstract String getSql() throws SQLException;

	public void update(User user) throws SQLException {
    	Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = getSql();
            pstmt = con.prepareStatement(sql);
            setParameter(user, pstmt);

            pstmt.executeUpdate();
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }

            if (con != null) {
                con.close();
            }
        }
    }

}
