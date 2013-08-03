package socialtoilet.android.services;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

import com.google.gson.Gson;

import socialtoilet.android.model.LoginUser;
import socialtoilet.android.service.api.APIService;

public class AuthService extends PostService implements IAuthService
{
	private LoginUser postBodyObject;
	private IAuthServiceDelegate delegate;
	
	public AuthService()
	{
		performingRequest = false;
	}
	
	@Override
	public void authUser(IAuthServiceDelegate delegate, LoginUser user)
	{
		if(performingRequest || null == user)
			return;
		postBodyObject = user;
		performingRequest = true;
		this.delegate = delegate;
		execute(APIService.getInstance().getAuthPostURL());
	}

    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        
        if(performingRequest)
        {
        	if(statusLine.getStatusCode() >= 400)
        	{
            	delegate.authServiceDelegateFinishWithError(this, statusLine.getStatusCode() + "");
        	}
        	else
        	{
                postBodyObject.setUserId(UUID.fromString(result.substring(1, result.length() - 2)));
                delegate.authServiceDelegateFinish(this, postBodyObject);
        	}
            performingRequest = false;
            delegate = null;
        }
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.authServiceDelegateFinishWithError(this, ioResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.authServiceDelegateFinishWithError(this, clientProtocolResponseErrorType);
        performingRequest = false;
        delegate = null;
	}

	@Override
	protected String generatePostBodyObject()
	{
		String jsonString = new Gson().toJson(postBodyObject, LoginUser.class);
		return jsonString;
	}

}
