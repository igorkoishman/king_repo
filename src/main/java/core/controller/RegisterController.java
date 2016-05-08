package core.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import core.model.Player;

@Controller
public class RegisterController {

	@RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody  ResponseEntity<?> deleteNodes(@RequestBody Player player) {
		if(player.getUserName().equals("igor")){
			return new ResponseEntity<>(new Player(), HttpStatus.BAD_REQUEST);
		}
		 return new ResponseEntity<>(player, HttpStatus.OK);
	}

}
