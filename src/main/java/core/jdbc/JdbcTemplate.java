package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class JdbcTemplate {
	public void executeUpdate(String sql, PreparedStatementSetter pss) throws DataAccessException {
        try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)){
			pss.setValues(pstmt);
			pstmt.executeUpdate();
        } catch (SQLException e) {
        	throw new DataAccessException();
        }
	}
	
	public void executeUpdate(String sql, Object... parameters) {
		executeUpdate(sql, createPreparedStatementSetter(parameters));
	}

	public <T> T excuteQuery(String sql, RowMapper<T> rm, PreparedStatementSetter pss) {
		
		List<T> list = query(sql, rm, pss);
		if (list.isEmpty()) {
			return null;
		}
		return list.get(0);
		
	}
	public <T> T excuteQuery(String sql, RowMapper<T> rm, Object... parameters) {
		return excuteQuery(sql, rm, createPreparedStatementSetter(parameters));
		
    }
	
	private PreparedStatementSetter createPreparedStatementSetter(Object... parameters) {
		PreparedStatementSetter pss = new PreparedStatementSetter() {
			@Override
			public void setValues(PreparedStatement pstmt) throws SQLException {
				for (int i = 0; i < parameters.length; i++) {
					pstmt.setObject(i+1, parameters[i]);
				}
			}
		};
		return pss;
	}
	
	public <T> List<T> query(String sql, RowMapper<T> rm, PreparedStatementSetter pss) throws DataAccessException {
		try (Connection con = ConnectionManager.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()){
			pss.setValues(pstmt);
			List<T> result = new ArrayList<>();
			while (rs.next()) {
				result.add(rm.mapRow(rs));
			}
			return result;
		} catch (SQLException e) {
			throw new DataAccessException();
		}
	}
	
	public <T> List<T> query(String sql, RowMapper<T> rm, Object... parameters) {
		return query(sql, rm, createPreparedStatementSetter(parameters));
	}
}
