package socialtoilet.android.services.post;

import socialtoilet.android.model.LoginUser;

public interface IAuthService
{
	void authUser(IAuthServiceDelegate delegate, LoginUser user);
}
