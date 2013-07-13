package socialtoilet.android.services;

import socialtoilet.android.model.IToilet;

public interface IRetrieveToiletServiceDelegate
{
	void retrieveToiletFinish(IRetrieveToiletService mockRetrieveToiletsService, IToilet toilet);
	void retrieveToiletFinishWithError(IRetrieveToiletService mockRetrieveToiletsService, int errorCode);
}
