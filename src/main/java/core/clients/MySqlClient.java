package core.clients;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcOperations;

public class MySqlClient {

	@Autowired
	@Qualifier("jdbcTemplate")
	private JdbcOperations jdbcTemplate;
	
}
