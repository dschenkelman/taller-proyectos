package socialtoilet.android.services;

import socialtoilet.android.model.IToilet;

public interface IRetrieveToiletServiceDelegate
{
	void retrieveToiletFinish(IRetrieveToiletService service, IToilet toilet);
	void retrieveToiletFinishWithError(IRetrieveToiletService service, String errorCode);
}
