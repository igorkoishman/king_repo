package core.controller;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import core.helper.PlayerHelper;
import core.model.Player;

@Controller
public class LoginController {

	@Autowired
	private PlayerHelper playerHelper;
	private static Logger logger = Logger.getLogger(RegisterController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody ResponseEntity<?> login(@RequestBody Player player) {
		logger.info("get into login method");

		if (!playerHelper.isPlayerExistingInDb(player.getUsername(), player.getPassword())) {
			logger.warn("the player is not valid");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
