package socialtoilet.android.services;

import socialtoilet.android.model.LoginUser;

public interface authServiceDelegate
{
	void authServiceDelegateFinish(IAuthService service, 
			LoginUser user);
	void authServiceDelegateFinishWithError(
			IAuthService service, String errorCode);
}
