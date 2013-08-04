package socialtoilet.android.services.post;

import socialtoilet.android.model.IToilet;

public interface IAddToiletServiceDelegate
{
	void addToiletFinish(IAddToiletService service, IToilet toilet);
	void addToiletFinishWithError(IAddToiletService service, String errorCode);
}
