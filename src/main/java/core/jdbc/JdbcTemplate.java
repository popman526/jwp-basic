package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public class JdbcTemplate {
	
	public void executeUpdate(String sql, PreparedStatementSetter pss) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			pss.setParameter(pstmt);
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public List<User> query(String sql, PreparedStatementSetter pss, RowMapper rm) throws DataAccessException {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
        		){
            pss.setParameter(pstmt);
            rs = pstmt.executeQuery();
            
            List<User> list = rm.mapRow(rs);

            return list;
        } catch (SQLException e) {
        	throw new DataAccessException(e);
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
