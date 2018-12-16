package posts.service;

import posts.model.UserRequestDTO;

import java.util.List;

public interface UserService {

	List<UserResponse> execute(List<UserRequestDTO> userRequestDTOs);

}
