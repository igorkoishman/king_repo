package core.controller;

import java.util.Map;

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

@Controller
public class LoginController {

	@Autowired
	private PlayerHelper playerHelper;
	private static Logger logger = Logger.getLogger(RegisterController.class);

	@RequestMapping(value = "/login", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody ResponseEntity<?> deleteNodes(@RequestBody Map<String, String> player) {
		logger.info("get into login method");

		if (!playerHelper.isPlayerExistingInDb(player.get("userName"), player.get("password"))) {
			logger.warn("the player is not valid");
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
