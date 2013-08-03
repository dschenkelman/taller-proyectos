package socialtoilet.android.services.get;

import java.util.Collection;

import socialtoilet.android.model.IToiletPicture;

public interface IRetrieveToiletGaleryServiceDelegate
{
	void retrieveToiletGaleryServiceFinish(
			IRetrieveToiletGaleryService service, Collection<IToiletPicture> pictures);
	void retrieveToiletGaleryServiceFinishWithError(
			IRetrieveToiletGaleryService service, String errorCode);
}
