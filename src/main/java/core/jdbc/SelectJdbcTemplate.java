package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import next.model.User;

public abstract class SelectJdbcTemplate {
	
	public List<User> list(String sql) throws SQLException {
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            setParameter(pstmt);
            rs = pstmt.executeQuery();
            
            List<User> list = mpaRow(rs);

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
	
	public Object queryForObject(String sql) throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
//            String sql = "SELECT userId, password, name, email FROM USERS WHERE userid=?";
            pstmt = con.prepareStatement(sql);
            setParameter(pstmt);

            rs = pstmt.executeQuery();

            Object user = row(rs);

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

	public abstract Object row(ResultSet rs) throws SQLException;
	
	public abstract void setParameter(PreparedStatement pstmt) throws SQLException;

	public abstract List<User> mpaRow(ResultSet rs) throws SQLException;

}
