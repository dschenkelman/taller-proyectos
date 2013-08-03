package socialtoilet.android.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import socialtoilet.android.service.api.APIService;

public class RetrieveToiletUserQualificationService extends GetService implements
		IRetrieveToiletUserQualificationService
{
	private IRetrieveToiletUserQualificationServiceDelegate delegate;
	
	public RetrieveToiletUserQualificationService()
	{
		performingRequest = false;
	}
	
	@Override
	public void retrieveToiletUserCalification(
			IRetrieveToiletUserQualificationServiceDelegate delegate, String toiletId)
	{
		if(performingRequest || null == delegate)
		{
			return;
		}
		performingRequest = true;
		this.delegate = delegate;
		execute(APIService.getInstance().getRetrieveToiletUserQualificationURL(toiletId));
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
                delegate.retrieveToiletUserQualificationServiceFinish(this,
                		Double.parseDouble(result));
            }
            else
            {
            	delegate.retrieveToiletUserQualificationServiceFinishWithError(this, 10000);
            }
            performingRequest = false;
            delegate = null;
        }
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.retrieveToiletUserQualificationServiceFinishWithError(this, statusCode);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.retrieveToiletUserQualificationServiceFinishWithError(this, 10000);
        performingRequest = false;
        delegate = null;
	}
}
