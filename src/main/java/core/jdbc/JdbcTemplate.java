package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public abstract class JdbcTemplate {
	
	public void executeUpdate(String sql) throws SQLException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			setParameter(pstmt);
			
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
	
	public List<User> query(String sql) throws SQLException {
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
		List<?> result = query(sql);
		System.out.println(result);
		if (result.isEmpty()) {
			return null;
		}
		
		return result.get(0);
    }

	public abstract void setParameter(PreparedStatement pstmt) throws SQLException;

	public abstract List<User> mpaRow(ResultSet rs) throws SQLException;


}
