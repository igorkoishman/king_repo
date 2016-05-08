package core.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import core.model.Player;

@Controller
public class LoginController {

	
	@RequestMapping(value = "/login", method = RequestMethod.GET) 
	protected @ResponseBody Player login(@RequestParam(required = false, value = "userName") String userName,
			@RequestParam(required = false, value = "password") String password) {
		
		return new Player(userName, password);
	}
  
	
	
}
