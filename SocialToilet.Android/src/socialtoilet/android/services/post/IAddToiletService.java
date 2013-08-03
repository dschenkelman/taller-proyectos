package socialtoilet.android.services.post;

import socialtoilet.android.model.Toilet;

public interface IAddToiletService
{
	void addToilet(Toilet toilet, IAddToiletServiceDelegate delegate);
}
