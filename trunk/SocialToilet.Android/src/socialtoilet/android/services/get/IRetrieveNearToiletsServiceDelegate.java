package socialtoilet.android.services.get;

import java.util.Collection;

import socialtoilet.android.model.IToilet;

public interface IRetrieveNearToiletsServiceDelegate
{
	void retrieveNearToiletsFinish(IRetrieveNearToiletsService service, Collection<IToilet> nearToilets);
	void retreiveNearToiletsFinishWithError(IRetrieveNearToiletsService service, String errorCode);
}
