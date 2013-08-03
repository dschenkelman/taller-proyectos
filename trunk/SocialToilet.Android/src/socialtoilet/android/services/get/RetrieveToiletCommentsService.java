package socialtoilet.android.services.get;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import socialtoilet.android.model.Comment;
import socialtoilet.android.model.IComment;
import socialtoilet.android.service.api.APIService;

import com.google.gson.Gson;

public class RetrieveToiletCommentsService extends GetService implements
		IRetrieveToiletCommentsService
{
	private IRetrieveToiletCommentsServiceDelegate delegate;
	
	public RetrieveToiletCommentsService()
	{
		performingRequest = false;
	}
	
	@Override
	public void retrieveToiletComments(UUID toiletId,
			IRetrieveToiletCommentsServiceDelegate delegate)
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
		execute(APIService.getInstance().getRetrieveToiletCommentsURL(toiletId.toString()));
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        Collection<IComment> comments = new ArrayList<IComment>();
        if( null != result )
        {
            try
            {
            	JSONArray jsonComments = new JSONArray(result);
            	for(int i = 0; i < jsonComments.length(); i++)
            	{
            		JSONObject jsonToilet = jsonComments.getJSONObject(i);
            		IComment comment = new Gson().fromJson(jsonToilet.toString(), Comment.class);
            		if( null != comment )
            		{
            			comments.add(comment);
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
        	delegate.retrieveToiletCommentsFinishWithError(this, emptyResponseErrorType);
        }
        delegate.retrieveToiletCommentsFinish(this, comments);
        performingRequest = false;
        delegate = null;
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.retrieveToiletCommentsFinishWithError(this, ioResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.retrieveToiletCommentsFinishWithError(this, clientProtocolResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
}
