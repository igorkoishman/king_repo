package core.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Player {
	private String username;
	private String password;
	@JsonIgnore
	private Date registrationDate;
	@JsonIgnore
	private Date lastEnterence;

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public Player(String username, String password) {
		this.username = username;
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Date getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(Date registrationDate) {
		this.registrationDate = registrationDate;
	}

	@Override
	public String toString() {
		return "Player [userName=" + username + ", password=" + password + ", registrationDate=" + registrationDate
				+ ", lastEnterence=" + lastEnterence + "]";
	}

	public Date getLastEnterence() {
		return lastEnterence;
	}

	public void setLastEnterence(Date lastEnterence) {
		this.lastEnterence = lastEnterence;
	}

}
