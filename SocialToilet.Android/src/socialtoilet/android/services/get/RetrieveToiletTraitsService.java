package socialtoilet.android.services.get;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import socialtoilet.android.model.IToiletTrait;
import socialtoilet.android.model.ToiletTrait;
import socialtoilet.android.service.api.APIService;

import com.google.gson.Gson;

public class RetrieveToiletTraitsService extends GetService implements
		IRetrieveToiletTraitsService
{
	private IRetrieveToiletTraitsServiceDelegate delegate;
	
	public RetrieveToiletTraitsService()
	{
		performingRequest = false;
	}
	
	@Override
	public void retrieveToiletTraits(
			IRetrieveToiletTraitsServiceDelegate delegate)
	{
		if(performingRequest || null == delegate)
		{
			return;
		}
		performingRequest = true;
		this.delegate = delegate;
		execute(APIService.getInstance().getRetrieveToiletTraitsURL());
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        //Do anything with response..
        if(performingRequest)
        {
        	if(0 > statusLine.getStatusCode() || 400 <= statusLine.getStatusCode())
        	{
            	delegate.retrieveToiletTraitsServiceFinishWithError(this, statusLine.getStatusCode());
        	}
        	else
        	{
                Collection<IToiletTrait> traits = new ArrayList<IToiletTrait>();
                if( null != result )
                {
                    try
                    {
                    	JSONArray jsonToiletTraits = new JSONArray(result);
                    	for(int i = 0; i < jsonToiletTraits.length(); i++)
                    	{
                    		JSONObject jsonToiletTrait = jsonToiletTraits.getJSONObject(i);
                    		ToiletTrait trait = new Gson().fromJson(jsonToiletTrait.toString(), ToiletTrait.class);
                    		if(null != trait)
                    		{
                    			trait.correct();
                    			traits.add(trait);
                    		}
                    	}
                    	delegate.retrieveToiletTraitsServiceFinish(this, traits);
            		}
                    catch (JSONException e)
            		{
            			e.printStackTrace();
                    	delegate.retrieveToiletTraitsServiceFinishWithError(this, -100);
            		}
                }
                else
                {
                	delegate.retrieveToiletTraitsServiceFinishWithError(this, -100);
                }
        	}
            performingRequest = false;
            delegate = null;
        }
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.retrieveToiletTraitsServiceFinishWithError(this, statusCode);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.retrieveToiletTraitsServiceFinishWithError(this, statusLine.getStatusCode());
        performingRequest = false;
        delegate = null;
	}
}
