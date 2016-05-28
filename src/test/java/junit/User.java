package junit;

public class User{
	private User user;
	private String userName;
	public User(User user,String userName) {
		this.user=user;
		this.userName=userName;
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
}
