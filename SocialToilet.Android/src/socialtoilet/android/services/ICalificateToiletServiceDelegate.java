package socialtoilet.android.services;

public interface ICalificateToiletServiceDelegate
{
	void calificateToiletFinish(ICalificateToiletService service);
	void calificateToiletFinishWithError(ICalificateToiletService service, String errorCode);
}
