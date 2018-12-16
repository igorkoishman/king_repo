package posts.service;

import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;

public class UserResponse {


	private int id;
	private String urlPerformed;
	private HttpStatus result;

	public UserResponse(int id, String urlPerformed, HttpStatus result) {
		this.id = id;
		this.urlPerformed = urlPerformed;
		this.result = result;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrlPerformed() {
		return urlPerformed;
	}

	public void setUrlPerformed(String urlPerformed) {
		this.urlPerformed = urlPerformed;
	}

	public HttpStatus getResult() {
		return result;
	}

	public void setResult(HttpStatus result) {
		this.result = result;
	}
}
