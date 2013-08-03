package socialtoilet.android.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import socialtoilet.android.service.api.APIService;

public class RetrieveToiletUserCalificationService extends GetService implements
		IRetrieveToiletUserCalificationService
{
	private IRetrieveToiletUserCalificationServiceDelegate delegate;
	
	public RetrieveToiletUserCalificationService()
	{
		performingRequest = false;
	}
	
	@Override
	public void retrieveToiletUserCalification(
			IRetrieveToiletUserCalificationServiceDelegate delegate, String toiletId)
	{
		if(performingRequest || null == delegate)
		{
			return;
		}
		performingRequest = true;
		this.delegate = delegate;
		execute(APIService.getInstance().getRetrieveToiletUserCalificationURL(toiletId));
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        //Do anything with response..
        if(performingRequest)
        {
            if( null != result )
            {
                delegate.retrieveToiletUserCalificationServiceFinish(this,
                		Integer.parseInt(result.substring(1, result.length() - 2)));
            }
            else
            {
            	delegate.retrieveToiletUserCalificationServiceFinishWithError(this, 10000);
            }
            performingRequest = false;
            delegate = null;
        }
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.retrieveToiletUserCalificationServiceFinishWithError(this, statusCode);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.retrieveToiletUserCalificationServiceFinishWithError(this, 10000);
        performingRequest = false;
        delegate = null;
	}
}
