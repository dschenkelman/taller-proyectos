package socialtoilet.android.services.get;

import java.util.UUID;

public interface IRetrieveToiletCommentsService
{
	void retrieveToiletComments(UUID toiletId, IRetrieveToiletCommentsServiceDelegate delegate);
}
