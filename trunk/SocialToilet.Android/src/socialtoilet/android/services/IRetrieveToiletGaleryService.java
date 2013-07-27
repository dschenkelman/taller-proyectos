package socialtoilet.android.services;

import java.util.UUID;

public interface IRetrieveToiletGaleryService
{
	void retrieveToiletGalery(UUID toiletId, IRetrieveToiletGaleryServiceDelegate delegate);
}
