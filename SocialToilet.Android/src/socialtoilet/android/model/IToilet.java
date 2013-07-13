package socialtoilet.android.model;

import java.util.UUID;

public interface IToilet extends IMapMarked
{

	int getRanking();
	String getDescription();
	String getAddress();
	UUID getID();
	
}
