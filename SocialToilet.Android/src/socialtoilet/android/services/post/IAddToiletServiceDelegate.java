package socialtoilet.android.services.post;

public interface IAddToiletServiceDelegate
{
	void addToiletFinish(IAddToiletService service);
	void addToiletFinishWithError(IAddToiletService service, String errorCode);
}
