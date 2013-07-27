package socialtoilet.android.model;

import java.util.Collection;

public interface IComment
{
	String getTitle();
	String getMessage();
	String getUser();
	String getDate();
	Collection<String> getLikeUsers();
}
