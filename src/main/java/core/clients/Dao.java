package core.clients;

import java.util.List;

public interface Dao<T> {
	  
	   public T GetObject(String string);
	   public List<T> GetObjects();
	   public void insertRow(String... valueMap);
	  
	  
	}
