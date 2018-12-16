package posts.model;

import  org.springframework.http.HttpMethod;
import posts.controller.User;

public class UserRequestDTO {


	private HttpMethod verb;
	private String url;
	private User user;
	private String data;

	public UserRequestDTO(HttpMethod verb, String url, User user, String data) {
		this.verb = verb;
		this.url = url;
		this.user = user;
		this.data = data;
	}

	public HttpMethod getVerb() {
		return verb;
	}

	public void setVerb(HttpMethod verb) {
		this.verb = verb;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}
}
