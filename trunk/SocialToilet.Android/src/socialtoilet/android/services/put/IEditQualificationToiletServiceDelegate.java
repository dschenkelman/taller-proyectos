package socialtoilet.android.services.put;

public interface IEditQualificationToiletServiceDelegate
{
	void editQualificationToiletFinish(IEditQualificationToiletService service,
			int newQualification);
	void editQualificationToiletFinishWithError(
			IEditQualificationToiletService service, int errorCode);
}
