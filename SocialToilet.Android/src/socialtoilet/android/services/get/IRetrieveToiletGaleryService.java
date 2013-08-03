package socialtoilet.android.services.get;

import java.util.UUID;

public interface IRetrieveToiletGaleryService
{
	void retrieveToiletGalery(UUID toiletId, IRetrieveToiletGaleryServiceDelegate delegate);
}
