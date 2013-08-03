package socialtoilet.android.services;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;

import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.LoginUser;
import socialtoilet.android.model.UserQualificationPostBody;
import socialtoilet.android.service.api.APIService;
import socialtoilet.android.utils.Settings;

public class QualificateToiletService extends PostService implements IQualificateToiletService
{
	
	private UserQualificationPostBody postBodyObject;
	private IQualificateToiletServiceDelegate delegate;
	
	public QualificateToiletService()
	{
		performingRequest = false;
	}
	
	@Override
	public void qualificateToiletService(IToilet toilet, int userQualification,
			IQualificateToiletServiceDelegate delegate)
	{
		if( performingRequest || null == delegate || null == toilet )
			return;
		performingRequest = true;
		this.delegate = delegate;
		postBodyObject = new UserQualificationPostBody(
				Settings.getInstance().getUserId(), userQualification);
		execute(APIService.getInstance().getCalificateToiletPostURL(toilet.getID().toString()));
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        
        if(performingRequest)
        {
        	if(statusLine.getStatusCode() >= 400 || statusLine.getStatusCode() < 200)
        	{
            	delegate.calificateToiletFinishWithError(this, statusLine.getStatusCode());
        	}
        	else
        	{
                delegate.calificateToiletFinish(this);
        	}
            performingRequest = false;
            delegate = null;
        }
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.calificateToiletFinishWithError(this, statusCode);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.calificateToiletFinishWithError(this, -1);
        performingRequest = false;
        delegate = null;
	}

	@Override
	protected String generatePostBodyObject()
	{
		String jsonString = new Gson().toJson(postBodyObject, UserQualificationPostBody.class);
		return jsonString;
	}
}
