package posts.controller;

import java.util.List;

public interface RequestValidator<API_OBJECT> {

	ValidationStatus validate(List<API_OBJECT> apiObjects);

}
