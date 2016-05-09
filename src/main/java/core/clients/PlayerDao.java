package core.clients;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import core.model.Player;
@Transactional
@Component
public class PlayerDao implements PlayerDaointerface {

	@Autowired
	private JdbcOperations jdbcOperations;

	@Override
	public Player getPlayer(String userName) throws RuntimeException {
		String sql = "SELECT * FROM players WHERE user_name = ?";
		return jdbcOperations.queryForObject(sql, new Object[] { userName }, new PlayerMapper());
	}

	@Override
	public List<Player> getAllPlayers() throws RuntimeException {
		String sql = "SELECT * FROM players";
		return jdbcOperations.query(sql, new PlayerMapper());
	}

	@Override
	public void insertPlayer(Player player) throws RuntimeException {
		 String sql = "insert into players (user_name, password,registration_date,last_enterence) values (?,?,?,?)";
		 try {
			 jdbcOperations.update(sql,player.getUserName(),player.getPassword(),player.getRegistrationDate(),player.getLastEnterence());
		} catch (DataAccessException e) {
			throw new RuntimeException(e.getMessage());
		}
		
	}
	
	
	@Override
	public void updatePlayer(Player player) {
		String sql="UPDATE players SET last_enterence = ? WHERE user_name = ?";
				jdbcOperations.update(sql, player.getLastEnterence(),player.getUserName());
		
	}

}
