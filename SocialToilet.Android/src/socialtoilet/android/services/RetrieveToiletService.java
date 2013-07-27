package socialtoilet.android.services;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;

import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.Toilet;
import socialtoilet.android.service.api.APIService;

import com.google.gson.Gson;

public class RetrieveToiletService extends GetService implements IRetrieveToiletService
{
	private IRetrieveToiletServiceDelegate delegate;
	
	public RetrieveToiletService()
	{
		performingRequest = false;
	}
	
	@Override
	public void retrieveToilet(UUID toiletId,
			IRetrieveToiletServiceDelegate delegate)
	{
		if( performingRequest )
		{
			return;
		}
		if( null == delegate )
		{
			return;
		}
		performingRequest = true;
		this.delegate = delegate;
		execute(APIService.getInstance().getRetrieveToiletURL(toiletId.toString()));
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        //Do anything with response..
        IToilet toilet = null;
        if( null != result )
        {
        	toilet = new Gson().fromJson(result, Toilet.class);
        }
        else
        {
        	delegate.retrieveToiletFinishWithError(this, emptyResponseErrorType);
        }
        delegate.retrieveToiletFinish(this, toilet);
        performingRequest = false;
        delegate = null;
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.retrieveToiletFinishWithError(this, ioResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.retrieveToiletFinishWithError(this, clientProtocolResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
}
