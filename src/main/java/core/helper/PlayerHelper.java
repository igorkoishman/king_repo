package core.helper;

import static core.utils.StringUtils.isEmpty;
import static core.utils.StringUtils.isNull;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import core.clients.PlayerDaointerface;
import core.model.Player;
import core.model.PlayerException;
import core.utils.DateUtils;

@Component
public class PlayerHelper implements Phelper {
	@Autowired
	private PlayerDaointerface playerDao;

	private boolean isPlayerValidForRegistration(Player player) {
		if (isNull(player.getUserName()) || isNull(player.getPassword()) || isEmpty(player.getUserName())
				|| isEmpty(player.getPassword())) {
			return false;
		}
		return true;
	}

	@Override
	public boolean isPlayerExistingInDb(String userName, String Password) throws RuntimeException {
		Player player = playerDao.getPlayer(userName);
		if (player.getPassword().equals(Password)) {
			player.setLastEnterence(DateUtils.getCurrentDate());
			playerDao.updatePlayer(player);
			return true;
		}
		return false;
	}

	@Override
	public void insertNewPlayer(Player player) throws PlayerException, RuntimeException {
		Date currentDate = DateUtils.getCurrentDate();
		player.setLastEnterence(currentDate);
		player.setRegistrationDate(currentDate);
		if (!isPlayerValidForRegistration(player)) {
			throw new PlayerException("the data is not valid for registration");
		} else {
			playerDao.insertPlayer(player);
		}
	}

}
