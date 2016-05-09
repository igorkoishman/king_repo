package core.clients;

import java.util.List;

import core.model.Player;

public interface PlayerDaointerface {
	  
	   public Player getPlayer(String string) throws RuntimeException;
	   public List<Player> getAllPlayers() throws RuntimeException;
	   public void insertPlayer(Player player) throws RuntimeException;
	   public void updatePlayer(Player player);
	  
	  
	}
