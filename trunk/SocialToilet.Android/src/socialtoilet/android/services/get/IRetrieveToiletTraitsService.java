package socialtoilet.android.services.get;

import socialtoilet.android.model.IToilet;

public interface IRetrieveToiletTraitsService
{
	void retrieveToiletTraitService(IRetrieveToiletTraitsServiceDelegate delegate,
			IToilet toilet);
}
