package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public class JdbcTemplate {
	
	public void executeUpdate(String sql, PreparedStatementSetter pss) throws DataAccessException {
		Connection con = null;
		PreparedStatement pstmt = null;
		try {
			con = ConnectionManager.getConnection();
			pstmt = con.prepareStatement(sql);
			pss.setParameter(pstmt);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		} finally {
			if (pstmt != null) {
				try {
					pstmt.close();
				} catch (SQLException e) {
					throw new DataAccessException(e);
				}
			}
			
			if (con != null) {
				try {
					con.close();
				} catch (SQLException e) {
					throw new DataAccessException(e);
				}
			}
		}
	}
	
	public List<User> query(String sql, PreparedStatementSetter pss, RowMapper rm) throws DataAccessException {
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
        } catch (SQLException e) {
        	throw new DataAccessException(e);
		} finally {
            if (rs != null) {
                try {
					rs.close();
				} catch (SQLException e) {
					throw new DataAccessException(e);
				}
            }
            if (pstmt != null) {
                try {
					pstmt.close();
				} catch (SQLException e) {
					throw new DataAccessException(e);
				}
            }
            if (con != null) {
                try {
					con.close();
				} catch (SQLException e) {
					throw new DataAccessException(e);
				}
            }
        }
    }
	
	public Object queryForObject(String sql, PreparedStatementSetter pss, RowMapper rm) throws DataAccessException {
		List<?> result = query(sql, pss, rm);
		System.out.println(result);
		if (result.isEmpty()) {
			return null;
		}
		
		return result.get(0);
    }


}
