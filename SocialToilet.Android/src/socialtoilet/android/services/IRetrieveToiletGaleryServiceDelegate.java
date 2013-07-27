package socialtoilet.android.services;

import java.util.Collection;

import socialtoilet.android.model.IToiletPicture;

public interface IRetrieveToiletGaleryServiceDelegate
{
	void retrieveToiletGaleryServiceFinish(
			IRetrieveToiletGaleryService service, Collection<IToiletPicture> pictures);
	void retrieveToiletGaleryServiceFinishWithError(
			IRetrieveToiletGaleryService service, String errorCode);
}
