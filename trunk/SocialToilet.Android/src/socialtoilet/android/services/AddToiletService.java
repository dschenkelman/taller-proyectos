package socialtoilet.android.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;

import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.Toilet;

public class AddToiletService extends GetService implements IAddToiletService
{
	
	private IAddToiletServiceDelegate delegate;
	
	public AddToiletService()
	{
		performingRequest = false;
	}
	
	@Override
	public void addToilet(IToilet toilet, IAddToiletServiceDelegate delegate)
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
		execute("http://192.168.1.250:8080/api/");
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

}