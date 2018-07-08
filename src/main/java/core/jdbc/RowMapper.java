package core.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import next.model.User;

public interface RowMapper {

	List<User> mapRow(ResultSet rs) throws SQLException;

}
