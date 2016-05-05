package kings;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginController {

	@RequestMapping(value = "/login", method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
	protected @ResponseBody Player login(@RequestBody Map<String, String> paramsMap) {
		return new Player(paramsMap.get("userName"), paramsMap.get("password"));
	}
  
	
	
}
