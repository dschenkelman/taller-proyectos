package socialtoilet.android.services;

import socialtoilet.android.model.IToilet;

public interface IAddToiletService
{
	void addToilet(IToilet toilet, IAddToiletServiceDelegate delegate);
}
