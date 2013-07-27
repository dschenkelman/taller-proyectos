package socialtoilet.android.services;

import socialtoilet.android.model.IToilet;

public interface ICalificateToiletService
{
	void calificateToiletService(IToilet toilet, int userCalification, ICalificateToiletServiceDelegate delegate);
}
