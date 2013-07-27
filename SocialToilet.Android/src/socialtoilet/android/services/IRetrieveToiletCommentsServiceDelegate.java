package socialtoilet.android.services;

import java.util.Collection;

import socialtoilet.android.model.IComment;

public interface IRetrieveToiletCommentsServiceDelegate
{
	void retrieveToiletCommentsFinish(IRetrieveToiletCommentsService service, Collection<IComment> comments);
	void retrieveToiletCommentsFinishWithError(IRetrieveToiletCommentsService service, String errorCode);
}
