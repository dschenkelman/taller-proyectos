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

public class RetrieveNearToiletsService extends Service implements IRetrieveNearToiletsService
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
		execute("http://192.168.1.250:8080/api/toilets/near?lat="+latitude+"&long="+longitude+"&radiusInMeters="+distanceInMeters);
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        //Do anything with response..
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
	protected void handleIOException(IOException e)
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
