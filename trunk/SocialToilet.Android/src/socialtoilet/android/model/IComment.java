package socialtoilet.android.model;

import java.util.Collection;
import java.util.UUID;

public interface IComment
{
	UUID getUserId();
	String getUser();
	String getContent();
	String getDate();
	Collection<String> getLikeUsers();
}
