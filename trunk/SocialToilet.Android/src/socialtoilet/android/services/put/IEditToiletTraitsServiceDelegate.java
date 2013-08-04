package socialtoilet.android.services.put;

public interface IEditToiletTraitsServiceDelegate
{
	void editToiletTraitsServiceFinish(IEditToiletTraitsService service);
	void editToiletTraitsServiceFinishWithError(
			IEditToiletTraitsService service, int errorCode);
}
