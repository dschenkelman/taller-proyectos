package socialtoilet.android.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.Toilet;
import socialtoilet.android.service.api.APIService;

public class RetrieveNearToiletsService extends GetService implements IRetrieveNearToiletsService
{
	private IRetrieveNearToiletsServiceDelegate delegate;
	
	public RetrieveNearToiletsService()
	{
		performingRequest = false;
	}

	@Override
	public void retrieveNearToilets(double latitude, double longitude,
			int distanceInMeters, IRetrieveNearToiletsServiceDelegate delegate)
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
		execute(APIService.getInstance().getRetrieveNearToilets(latitude, longitude, distanceInMeters));
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        Collection<IToilet> toilets = new ArrayList<IToilet>();
        if( null != result )
        {
            try
            {
            	JSONArray jsonToilets = new JSONArray(result);
            	for(int i = 0; i < jsonToilets.length(); i++)
            	{
            		JSONObject jsonToilet = jsonToilets.getJSONObject(i);
            		IToilet toilet = new Gson().fromJson(jsonToilet.toString(), Toilet.class);
            		if( null != toilet )
            		{
            			toilets.add(toilet);
            		}
            	}
    		}
            catch (JSONException e)
    		{
    			e.printStackTrace();
    		}
        }
        else
        {
        	delegate.retreiveNearToiletsFinishWithError(this, emptyResponseErrorType);
        }
        delegate.retrieveNearToiletsFinish(this, toilets);
        performingRequest = false;
        delegate = null;
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.retreiveNearToiletsFinishWithError(this, ioResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.retreiveNearToiletsFinishWithError(this, clientProtocolResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
}
