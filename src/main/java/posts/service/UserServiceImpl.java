package posts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import posts.model.UserRequestDTO;

import java.util.ArrayList;
import java.util.List;

@Component
public class UserServiceImpl implements UserService {

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public List<UserResponse> execute(List<UserRequestDTO> userRequestDTOs) {
		List<UserResponse> userResponses = new ArrayList<>();
		for (UserRequestDTO userRequestDTO : userRequestDTOs) {
			UserResponse userResponse = executeRequest(userRequestDTO);
			if (HttpStatus.Series.SUCCESSFUL != HttpStatus.Series.valueOf(userResponse.getResult())) {
				userResponse = executeRequest(userRequestDTO);
			}
			userResponses.add(userResponse);
		}

		return userResponses;
	}

	private UserResponse executeRequest(UserRequestDTO userRequestDTO) {
		HttpHeaders headers = new HttpHeaders();
		HttpEntity<String> entity = new HttpEntity<>(userRequestDTO.getData(), headers);
		String targetUrl = userRequestDTO.getUrl().replace("{userId}", String.valueOf(userRequestDTO.getUser().getId()));
		try {
			ResponseEntity<ResponseFromUserService> response = restTemplate.exchange(targetUrl, userRequestDTO.getVerb(), entity,
					ResponseFromUserService.class);
			return new UserResponse(userRequestDTO.getUser().getId(), targetUrl, response.getStatusCode());
		} catch (Exception e) {
			return new UserResponse(userRequestDTO.getUser().getId(), targetUrl, HttpStatus.SERVICE_UNAVAILABLE);
		}

	}
}
