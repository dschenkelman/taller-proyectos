package socialtoilet.android.services;

import socialtoilet.android.model.LoginUser;

public interface IAuthService
{
	void authUser(IAuthServiceDelegate delegate, LoginUser user);
}
