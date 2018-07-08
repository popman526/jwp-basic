package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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
	
	public <T> List<T> query(String sql, PreparedStatementSetter pss, RowMapper<T> rm) throws DataAccessException {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
        		){
            pss.setParameter(pstmt);
            rs = pstmt.executeQuery();
            
            List<T> list = rm.mapRow(rs);

            return list;
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }
    }
	
	public <T> T queryForObject(String sql, PreparedStatementSetter pss, RowMapper<T> rm) throws DataAccessException {
		List<T> result = query(sql, pss, rm);
		System.out.println(result);
		if (result.isEmpty()) {
			return null;
		}
		
		return result.get(0);
    }


}
