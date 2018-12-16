package posts.controller;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class UserRequestValidator implements RequestValidator<UserRequest> {

	@Override
	public ValidationStatus validate(List<UserRequest> userRequest) {

		return ValidationStatus.successValidation();
	}
}
