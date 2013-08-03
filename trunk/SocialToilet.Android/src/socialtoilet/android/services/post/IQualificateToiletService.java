package socialtoilet.android.services.post;

import socialtoilet.android.model.IToilet;

public interface IQualificateToiletService
{
	void qualificateToiletService(IToilet toilet, int userCalification, IQualificateToiletServiceDelegate delegate);
}
