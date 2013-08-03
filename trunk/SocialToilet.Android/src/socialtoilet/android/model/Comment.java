package socialtoilet.android.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import android.text.format.Time;

public class Comment implements IComment
{

	private UUID userId;
	private String content;
	private String userName;
	private String postedOn;
	private Collection<String> likeUsers;
	
	public Comment()
	{
		likeUsers = new ArrayList<String>();
	}

	@Override
	public UUID getUserId()
	{
		return userId;
	}

	@Override
	public String getUser()
	{
		return userName;
	}

	@Override
	public String getContent()
	{
		return content;
	}

	@Override
	public String getDate()
	{    
	    return postedOn; 
	}

	@Override
	public Collection<String> getLikeUsers()
	{
		return likeUsers;
	}

	public void setUser(UUID userId, String user)
	{
		this.userId = userId;
		this.userName = user;
	}

	public void setContent(String content)
	{
		this.content = content;
	}

	public void stapTime()
	{
		Time time = new Time();
		time.setToNow();
		postedOn = time.format("%Y-%m-%d") + " " + time.format("%H:%M");
	}

}
