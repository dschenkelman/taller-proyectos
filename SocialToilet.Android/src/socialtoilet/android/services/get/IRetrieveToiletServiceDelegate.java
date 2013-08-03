package socialtoilet.android.services.get;

import socialtoilet.android.model.IToilet;

public interface IRetrieveToiletServiceDelegate
{
	void retrieveToiletFinish(IRetrieveToiletService service, IToilet toilet);
	void retrieveToiletFinishWithError(IRetrieveToiletService service, String errorCode);
}
