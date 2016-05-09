package core.model;

import java.util.Date;

public class Player {

	private String userName;
	private String password;
	private Date registrationDate;
	private Date lastEnterence;

	public Player() {
		// TODO Auto-generated constructor stub
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Player(String userName, String password) {
		super();
		this.userName = userName;
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
		return "Player [userName=" + userName + ", password=" + password + ", registrationDate=" + registrationDate
				+ ", lastEnterence=" + lastEnterence + "]";
	}

	public Date getLastEnterence() {
		return lastEnterence;
	}

	public void setLastEnterence(Date lastEnterence) {
		this.lastEnterence = lastEnterence;
	}

}
