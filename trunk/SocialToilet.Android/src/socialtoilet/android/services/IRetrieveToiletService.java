package socialtoilet.android.services;

import java.util.UUID;

public interface IRetrieveToiletService
{
	
	
	void retrieveToilet(UUID toiletId, IRetrieveToiletServiceDelegate detailToiletActivity);

}
