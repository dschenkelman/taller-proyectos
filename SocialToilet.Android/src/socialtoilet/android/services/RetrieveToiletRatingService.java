package socialtoilet.android.services;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import socialtoilet.android.model.Rating;
import socialtoilet.android.service.api.APIService;

import com.google.gson.Gson;

public class RetrieveToiletRatingService extends GetService implements
		IRetrieveToiletRatingService
{

	private IRetrieveToiletRatingServiceDelegate delegate;
	
	public RetrieveToiletRatingService()
	{
		performingRequest = false;
	}
	
	@Override
	public void retrieveToiletRating(String toiletId,
			IRetrieveToiletRatingServiceDelegate delegate)
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
		execute(APIService.getInstance().getRetrieveToiletRatingURL(toiletId));
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        //Do anything with response..
        Rating rating = null;
        if( null != result )
        {
        	rating = new Gson().fromJson(result, Rating.class);
        }
        else
        {
        	delegate.retrieveToiletRatingServiceFinishWithError(this, emptyResponseErrorType);
        }
        delegate.retrieveToiletRatingServiceFinish(this, rating);
        performingRequest = false;
        delegate = null;
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.retrieveToiletRatingServiceFinishWithError(this, ioResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.retrieveToiletRatingServiceFinishWithError(this, clientProtocolResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
}
