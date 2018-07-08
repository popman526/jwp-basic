package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public class JdbcTemplate {
	
	public void executeUpdate(String sql, PreparedStatementSetter pss) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pss.setParameter(pstmt);
			
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
	
	public List<User> query(String sql, PreparedStatementSetter pss, RowMapper rm) throws SQLException {
    	Connection con = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            con = ConnectionManager.getConnection();
            pstmt = con.prepareStatement(sql);
            pss.setParameter(pstmt);
            rs = pstmt.executeQuery();
            
            List<User> list = rm.mapRow(rs);

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
	
	public Object queryForObject(String sql, PreparedStatementSetter pss, RowMapper rm) throws SQLException {
		List<?> result = query(sql, pss, rm);
		System.out.println(result);
		if (result.isEmpty()) {
			return null;
		}
		
		return result.get(0);
    }


}
