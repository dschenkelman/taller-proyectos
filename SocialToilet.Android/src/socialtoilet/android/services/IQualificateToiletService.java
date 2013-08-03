package socialtoilet.android.services;

import socialtoilet.android.model.IToilet;

public interface IQualificateToiletService
{
	void qualificateToiletService(IToilet toilet, int userCalification, IQualificateToiletServiceDelegate delegate);
}
