package socialtoilet.android.services;

import java.util.UUID;

public interface IRetrieveToiletCommentsService
{
	void retrieveToiletComments(UUID toiletId, IRetrieveToiletCommentsServiceDelegate delegate);
}
