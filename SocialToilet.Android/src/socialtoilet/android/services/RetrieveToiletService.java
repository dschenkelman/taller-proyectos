package socialtoilet.android.services;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;

import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.Toilet;

import com.google.gson.Gson;

public class RetrieveToiletService extends Service implements IRetrieveToiletService {

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
		execute("");
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
	protected void handleIOException(IOException e)
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
