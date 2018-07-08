package core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface RowMapper<T> {

	List<T> mapRow(ResultSet rs) throws SQLException;

}
