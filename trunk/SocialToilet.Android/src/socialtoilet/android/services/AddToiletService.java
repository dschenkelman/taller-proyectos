package socialtoilet.android.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;

import socialtoilet.android.model.Toilet;
import socialtoilet.android.service.api.APIService;

public class AddToiletService extends PostService implements IAddToiletService
{
	
	private Toilet postBodyObject;
	private IAddToiletServiceDelegate delegate;
	
	public AddToiletService()
	{
		performingRequest = false;
	}
	
	@Override
	public void addToilet(Toilet toilet, IAddToiletServiceDelegate delegate)
	{
		if( performingRequest || null == delegate || null == toilet )
			return;
		postBodyObject = toilet;
		performingRequest = true;
		this.delegate = delegate;
		execute(APIService.getInstance().getAddToiletPostURL());
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        delegate.addToiletFinish(this);
        performingRequest = false;
        delegate = null;
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.addToiletFinishWithError(this, ioResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.addToiletFinishWithError(this, clientProtocolResponseErrorType);
        performingRequest = false;
        delegate = null;
	}

	@Override
	protected String generatePostBodyObject()
	{
		String jsonString = new Gson().toJson(postBodyObject, Toilet.class);
		
		return jsonString;
	}

}
