package socialtoilet.android.services.get;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.google.gson.Gson;

import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.IToiletTrait;
import socialtoilet.android.model.ToiletTrait;
import socialtoilet.android.service.api.APIService;

public class RetrieveToiletTraitsService extends GetService implements
		IRetrieveToiletTraitsService
{
	private IRetrieveToiletTraitsServiceDelegate delegate;
	
	public RetrieveToiletTraitsService()
	{
		performingRequest = false;
	}
	
	@Override
	public void retrieveToiletTraitService(
			IRetrieveToiletTraitsServiceDelegate delegate, IToilet toilet)
	{
		if(performingRequest || null == delegate || null == toilet)
		{
			return;
		}
		performingRequest = true;
		this.delegate = delegate;
		execute(APIService.getInstance().getRetrieveToiletTraitsURL(toilet.getID().toString()));
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
                    		IToiletTrait trait = new Gson().fromJson(jsonToiletTrait.toString(), ToiletTrait.class);
                    		if(null != trait)
                    		{
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
