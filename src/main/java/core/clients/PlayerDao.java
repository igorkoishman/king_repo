package core.clients;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.mysql.jdbc.exceptions.MySQLDataException;

import core.model.Player;

@Component
public class PlayerDao implements Dao<Player> {

	@Autowired
	private JdbcOperations jdbcOperations;

	@Override
	public Player GetObject(String userName) throws SQLException{
		String sql = "SELECT * FROM players WHERE userName = ?";
		return jdbcOperations.queryForObject(sql, new Object[] { userName }, new PlayerMapper());
	}

	@Override
	public List<Player> GetObjects()throws SQLException{
		String sql = "SELECT * FROM players";
		return jdbcOperations.query(sql, new PlayerMapper());
	}

	@Override
	public void insertRow(String... valueMap) throws RuntimeException{
		 String sql = "insert into players (user_name, password,registration_date,last_enterence) values (?,?,?,?)";
		 try {
			 jdbcOperations.update(sql,valueMap);
		} catch (DataAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
	
	}

}
