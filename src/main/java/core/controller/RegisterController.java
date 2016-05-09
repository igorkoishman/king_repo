package core.controller;

import static core.utils.StringUtils.isEmpty;
import static core.utils.StringUtils.isNull;

import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mysql.jdbc.exceptions.MySQLDataException;

import core.clients.PlayerDao;
import core.model.Player;
import core.utils.DateUtils;

@Controller
public class RegisterController {

	@Autowired
	
	private PlayerDao playerDao;
	private static Logger logger = Logger.getLogger(RegisterController.class);

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody ResponseEntity<?> deleteNodes(@RequestBody Player player) {
		logger.info("get into register method");
		if (isNull(player.getUserName()) || isNull(player.getPassword()) || isEmpty(player.getUserName())
				|| isEmpty(player.getPassword())) {
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		} else {
			String currentDate = DateUtils.getCurrentDateFormatdB();
		
				
					try {
						playerDao.insertRow(player.getUserName(),player.getPassword(),currentDate,currentDate);
					} catch (Exception e) {
						logger.error(e.getMessage());
					}
//			List<Player> getObjects = playerDao.GetObjects();
//			getObjects.forEach((arg0)->System.out.println(arg0));
			
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
