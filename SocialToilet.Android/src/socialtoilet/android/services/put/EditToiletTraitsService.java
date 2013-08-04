package socialtoilet.android.services.put;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import socialtoilet.android.model.IToiletTrait;
import socialtoilet.android.service.api.APIService;

public class EditToiletTraitsService extends PutService 
	implements IEditToiletTraitsService
{
	private Collection<IToiletTrait> postBodyObject;
	private IEditToiletTraitsServiceDelegate delegate;
	
	public EditToiletTraitsService()
	{
		performingRequest = false;
	}
	
	@Override
	public void editToiletTraits(IEditToiletTraitsServiceDelegate delegate,
			Collection<IToiletTrait> traits, String toiletId)
	{
		if( performingRequest || null == delegate || null == traits )
			return;
		performingRequest = true;
		this.delegate = delegate;
		postBodyObject = traits;
		execute(APIService.getInstance().getEditToiletTraitsPutURL(toiletId));
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        
        if(performingRequest)
        {
        	if(statusLine.getStatusCode() >= 400 || statusLine.getStatusCode() < 200)
        	{
            	delegate.editToiletTraitsServiceFinishWithError(this, statusLine.getStatusCode());
        	}
        	else
        	{
                delegate.editToiletTraitsServiceFinish(this);
        	}
            performingRequest = false;
            delegate = null;
        }
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.editToiletTraitsServiceFinishWithError(this, statusCode);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.editToiletTraitsServiceFinishWithError(this, -1);
        performingRequest = false;
        delegate = null;
	}

	@Override
	protected String generatePutBodyObject()
	{
		Type listOfTestObject = new TypeToken<Collection<IToiletTrait>>(){}.getType();
		String jsonString = new Gson().toJson(postBodyObject, listOfTestObject);
		
		return jsonString;
	}
}
