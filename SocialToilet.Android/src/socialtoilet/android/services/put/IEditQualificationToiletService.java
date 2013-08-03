package socialtoilet.android.services.put;

import socialtoilet.android.model.IToilet;

public interface IEditQualificationToiletService
{

	void editQualificationToiletService(IToilet toilet, int userCalification,
			IEditQualificationToiletServiceDelegate delegate);

}
