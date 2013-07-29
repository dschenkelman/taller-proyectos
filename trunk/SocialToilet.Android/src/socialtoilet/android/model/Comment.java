package socialtoilet.android.model;


import android.annotation.SuppressLint;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.TimeZone;

public class Comment implements IComment
{

	private String title;
	private String message;
	private String user;
	private long epoch;
	private Collection<String> likeUsers;

	public Comment()
	{
		likeUsers = new ArrayList<String>();
	}
	
	@Override
	public String getTitle()
	{
		return title;
	}

	@Override
	public String getMessage()
	{
		return message;
	}

	@Override
	public String getUser()
	{
		return user;
	}

	@SuppressLint("SimpleDateFormat")
	@Override
	public String getDate()
	{    
		DateFormat objFormatter = new SimpleDateFormat("hh:mm'hs' dd/MM/yyyy");
	    objFormatter.setTimeZone(TimeZone.getDefault());

	    Calendar objCalendar = Calendar.getInstance(TimeZone.getDefault());
	    objCalendar.setTimeInMillis(epoch);
	    String result = objFormatter.format(objCalendar.getTime());
	    objCalendar.clear();
	    return result; 
	}

	@Override
	public Collection<String> getLikeUsers()
	{
		return likeUsers;
	}

	public void setUser(String user)
	{
		this.user = user;
	}

	public void setEpoch(int epoch)
	{
		this.epoch = epoch;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public void setMessage(String message)
	{
		this.message = message;
	}

}
