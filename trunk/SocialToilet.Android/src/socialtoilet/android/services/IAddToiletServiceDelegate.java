package socialtoilet.android.services;

public interface IAddToiletServiceDelegate
{
	void addToiletFinish(IAddToiletService service);
	void addToiletFinishWithError(IAddToiletService service, String errorCode);
}
