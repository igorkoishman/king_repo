package core.clients;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import core.model.Player;

public class PlayerMapper implements RowMapper<Player> {
	public Player mapRow(ResultSet rs, int rowNum) throws SQLException {
		Player player = new Player();
		player.setUsername(rs.getString("user_name"));
		player.setPassword(rs.getString("password"));
		player.setRegistrationDate(rs.getDate("registration_date"));
		player.setLastEnterence(rs.getDate("last_enterence"));
		return player;
	}
}