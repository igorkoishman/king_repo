package kings;

public class Player {

	private String userName;
	private String password;

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

}
