package socialtoilet.android.model;

import java.util.UUID;

public class LoginUser
{
	private UUID userId;
	private String user;
	private String password;

	public LoginUser(String user, String password)
	{
		this.user = user;
		this.password = password;
	}

	public UUID getUserId() { return userId; }
	public void setUserId(UUID userId) { this.userId = userId; }

	public String getUser() { return user; }
	public void setUser(String user) { this.user = user; }

	public String getPassword() { return password; }
	public void setPassword(String password) { this.password = password; }
}
