package posts.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import posts.controller.apimodel.Response;
import posts.model.UserRequestDTO;
import posts.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("batch")
public class UserController {

	private static Logger logger = LoggerFactory.getLogger(UserController.class);
	private UserService userService;
	private UserRequestValidator userRequestValidator;

	@Autowired
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@Autowired
	public void setUserRequestValidator(UserRequestValidator userRequestValidator) {
		this.userRequestValidator = userRequestValidator;
	}

	@Autowired
	public void setVoteService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(method = RequestMethod.POST, produces = "application/json", consumes = "application/json")
	protected @ResponseBody
	ResponseEntity<?> addVote(@RequestBody List<UserRequest> userRequests) {
		logger.info("user request started");
		ValidationStatus validate = userRequestValidator.validate(userRequests);
		if (validate.status.equals("FAILED")) {
			return new ResponseEntity<>(new Response(validate.getErrorMessages().get(0)), HttpStatus.BAD_REQUEST);
		}

		return new ResponseEntity(userService.execute(convertToUserDTO(userRequests)), HttpStatus.OK);
	}

	private List<UserRequestDTO> convertToUserDTO(List<UserRequest> userRequests) {
		List<UserRequestDTO> userRequestDTOS = new ArrayList<>();
		for (UserRequest request : userRequests) {
			userRequestDTOS.add(convertToUserDTO(request));

		}
		return userRequestDTOS;
	}

	private UserRequestDTO convertToUserDTO(UserRequest userRequest) {
		HttpMethod httpMethod = HttpMethod.valueOf(userRequest.getVerb());
		return new UserRequestDTO(httpMethod, userRequest.getUrl(), userRequest.getUser(), userRequest.getData());
	}

}