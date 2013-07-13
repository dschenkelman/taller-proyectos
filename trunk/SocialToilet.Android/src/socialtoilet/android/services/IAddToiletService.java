package socialtoilet.android.services;

import socialtoilet.android.model.Toilet;

public interface IAddToiletService
{
	void addToilet(Toilet toilet, IAddToiletServiceDelegate delegate);
}
