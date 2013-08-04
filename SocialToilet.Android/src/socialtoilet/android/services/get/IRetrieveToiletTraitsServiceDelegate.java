package socialtoilet.android.services.get;

import java.util.Collection;

import socialtoilet.android.model.IToiletTrait;

public interface IRetrieveToiletTraitsServiceDelegate
{
	void retrieveToiletTraitsServiceFinish(
			IRetrieveToiletTraitsService service, Collection<IToiletTrait> traits);
	void retrieveToiletTraitsServiceFinishWithError(
			IRetrieveToiletTraitsService service, int errorCode);
}
