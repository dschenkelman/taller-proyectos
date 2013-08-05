package socialtoilet.android.services.get;

import java.util.Collection;

import socialtoilet.android.model.IToiletTrait;

public interface IRetrieveTraitsServiceDelegate
{
	void retrieveTraitsServiceFinish(
			IRetrieveTraitsService service, Collection<IToiletTrait> traits);
	void retrieveTraitsServiceFinishWithError(
			IRetrieveTraitsService service, int errorCode);
}
