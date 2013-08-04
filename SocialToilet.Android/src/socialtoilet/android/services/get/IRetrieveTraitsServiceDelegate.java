package socialtoilet.android.services.get;

import java.util.Collection;

import socialtoilet.android.model.IToiletTrait;

public interface IRetrieveTraitsServiceDelegate
{
	void retrieveToiletTraitsServiceFinish(
			IRetrieveTraitsService service, Collection<IToiletTrait> traits);
	void retrieveToiletTraitsServiceFinishWithError(
			IRetrieveTraitsService service, int errorCode);
}
