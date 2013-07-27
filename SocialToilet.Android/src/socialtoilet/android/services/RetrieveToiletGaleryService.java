package socialtoilet.android.services;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import socialtoilet.android.model.IToiletPicture;
import socialtoilet.android.model.ToiletPictures;
import socialtoilet.android.service.api.APIService;

import com.google.gson.Gson;


public class RetrieveToiletGaleryService extends GetService implements
		IRetrieveToiletGaleryService
{
	private IRetrieveToiletGaleryServiceDelegate delegate;
	
	public RetrieveToiletGaleryService()
	{
		performingRequest = false;
	}
	
	@Override
	public void retrieveToiletGalery(UUID toiletId,
			IRetrieveToiletGaleryServiceDelegate delegate)
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
		execute(APIService.getInstance().getRetrieveToiletGaleryURL(toiletId.toString()));
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        Collection<IToiletPicture> pictures = new ArrayList<IToiletPicture>();
        if( null != result )
        {
            try
            {
            	JSONArray jsonPictures = new JSONArray(result);
            	for(int i = 0; i < jsonPictures.length(); i++)
            	{
            		JSONObject jsonToilet = jsonPictures.getJSONObject(i);
            		IToiletPicture picture = new Gson().fromJson(jsonToilet.toString(), ToiletPictures.class);
            		if( null != picture )
            		{
            			pictures.add(picture);
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
        	delegate.retrieveToiletGaleryServiceFinishWithError(this, emptyResponseErrorType);
        }
        delegate.retrieveToiletGaleryServiceFinish(this, pictures);
        performingRequest = false;
        delegate = null;
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.retrieveToiletGaleryServiceFinishWithError(this, ioResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.retrieveToiletGaleryServiceFinishWithError(this, clientProtocolResponseErrorType);
        performingRequest = false;
        delegate = null;
	}

}
