package socialtoilet.android.services.put;

import java.util.Collection;

import socialtoilet.android.model.IToiletTrait;

public interface IEditToiletTraitsService
{

	void editToiletTraits(IEditToiletTraitsServiceDelegate delegate,
			Collection<IToiletTrait> traits, String toiletId);

}
