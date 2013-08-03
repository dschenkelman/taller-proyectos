package socialtoilet.android.services.get;

import java.util.UUID;

public interface IRetrieveToiletService
{
	
	
	void retrieveToilet(UUID toiletId, IRetrieveToiletServiceDelegate detailToiletActivity);

}
