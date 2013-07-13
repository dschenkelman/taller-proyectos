package socialtoilet.android.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.message.BasicNameValuePair;

import socialtoilet.android.model.IToilet;

public class AddToiletService extends PostService implements IAddToiletService
{
	
	private IToilet postBodyObject;
	private IAddToiletServiceDelegate delegate;
	
	public AddToiletService()
	{
		performingRequest = false;
	}
	
	@Override
	public void addToilet(IToilet toilet, IAddToiletServiceDelegate delegate)
	{
		if( performingRequest || null == delegate || null == toilet )
			return;
		postBodyObject = toilet;
		performingRequest = true;
		this.delegate = delegate;
		execute("");
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
	protected List<? extends NameValuePair> generatePostBody()
	{
		// TODO 
		postBodyObject = new IToilet() {
			
			@Override
			public String getMapTitle() {
				return "el pozo";
			}
			
			@Override
			public String getMapSnippet() {
				return "a cagar se a dicho";
			}
			
			@Override
			public double getLongitude() {
				return -58.50;
			}
			
			@Override
			public double getLatitude() {
				return -34.70;
			}
			
			@Override
			public int getRanking() {
				return 5;
			}
			
			@Override
			public UUID getID() {
				return UUID.randomUUID();
			}
			
			@Override
			public String getDescription() {
				return "Alto cagadero";
			}
			
			@Override
			public String getAddress() {
				return "acá";
			}
		};
		
		List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
        nameValuePairs.add(new BasicNameValuePair("address", postBodyObject.getAddress()));
        nameValuePairs.add(new BasicNameValuePair("description", postBodyObject.getDescription()));
        nameValuePairs.add(new NameValuePair()
        {
			
			@Override
			public String getValue()
			{
				return "{latitude : "+ postBodyObject.getLatitude() +", longitude : "+ postBodyObject.getLongitude() +" }";
			}
			
			@Override
			public String getName()
			{
				return "location";
			}
		});
        
		return nameValuePairs;
	}

}
