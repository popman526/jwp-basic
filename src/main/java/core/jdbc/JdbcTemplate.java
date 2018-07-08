package core.jdbc;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import next.model.User;

public abstract class JdbcTemplate {
	public void executeUpdate() throws SQLException {
        Connection con = null;
        PreparedStatement pstmt = null;
        try {
            con = ConnectionManager.getConnection();
            String sql = getSql();
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
	
	public abstract void setParameter(PreparedStatement pstmt) throws SQLException;

	public abstract String getSql() throws SQLException;

}
