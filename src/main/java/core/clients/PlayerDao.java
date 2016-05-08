package core.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;

import core.model.Player;

@Component
public class PlayerDao implements Dao<Player> {

	@Autowired
	private JdbcOperations jdbcOperations;

	@Override
	public Player GetObject(String userName) {
		String sql = "SELECT * FROM players WHERE userName = ?";
		return jdbcOperations.queryForObject(sql, new Object[] { userName }, new PlayerMapper());
	}

	@Override
	public List<Player> GetObjects(){
		String sql = "SELECT * FROM players";
		return jdbcOperations.query(sql, new PlayerMapper());
	}

	@Override
	public void insertRow(String... valueMap) {
		 String sql = "insert into players (user_name, password,registration_date,last_enterence) values (?,?,?,?)";
		 jdbcOperations.update(sql,valueMap);
	}

}
