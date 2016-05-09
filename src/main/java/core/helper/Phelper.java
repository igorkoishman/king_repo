package core.helper;

import core.model.Player;
import core.model.PlayerException;

public interface Phelper {


	public boolean isPlayerExistingInDb(String userName, String Password)throws RuntimeException;

	
	public void insertNewPlayer(Player player) throws PlayerException, RuntimeException;

}