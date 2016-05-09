package core.clients;

import java.sql.SQLException;
import java.util.List;

public interface Dao<T> {
	  
	   public T GetObject(String string) throws SQLException;
	   public List<T> GetObjects() throws SQLException;
	   public void insertRow(String... valueMap) throws RuntimeException;
	  
	  
	}
