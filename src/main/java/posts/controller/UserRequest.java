package posts.controller;

import org.springframework.web.bind.annotation.RequestMethod;

public class UserRequest {

	private String verb;
	private String url;
	private User user;
	private String data;

	public UserRequest(String verb, String url, User user, String data) {
		this.verb = verb;
		this.url = url;
		this.user = user;
		this.data = data;
	}

	public String getVerb() {
		return verb;
	}

	public void setVerb(String verb) {
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
