package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class JdbcTemplate {
	
	public void executeUpdate(String sql, Object... parameters) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			for (int i = 0; i < parameters.length; i++) {
				pstmt.setObject(i + 1, parameters[i]);
			}
			
			pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new DataAccessException(e);
		}
	}
	
	public <T> List<T> query(String sql, RowMapper<T> rm, Object... parameters) throws DataAccessException {
        ResultSet rs = null;
        try (Connection con = ConnectionManager.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
        		){
        	for (int i = 0; i < parameters.length; i++) {
				pstmt.setObject(i + 1, parameters[i]);
			}
            rs = pstmt.executeQuery();
            
            List<T> list = rm.mapRow(rs);

            return list;
        } catch (SQLException e) {
        	throw new DataAccessException(e);
        }
    }
	
	public <T> T queryForObject(String sql, RowMapper<T> rm, Object... parameters) throws DataAccessException {
		List<T> result = query(sql, rm, parameters);
		System.out.println(result);
		if (result.isEmpty()) {
			return null;
		}
		
		return result.get(0);
    }


}
