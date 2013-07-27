package socialtoilet.android.model;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collection;
import java.util.Locale;
import java.util.TimeZone;

public class Comment implements IComment
{

	private String title;
	private String message;
	private String user;
	private long epoch;
	private Collection<String> likeUsers;

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

	@Override
	public String getDate()
	{    
		DateFormat objFormatter = new SimpleDateFormat("dd-MM-yyyy");
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

}
