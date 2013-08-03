package socialtoilet.android.services.put;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import com.google.gson.Gson;

import socialtoilet.android.model.IToilet;
import socialtoilet.android.model.UserQualificationPostBody;
import socialtoilet.android.service.api.APIService;
import socialtoilet.android.utils.Settings;

public class EditQualificationToiletService extends PutService implements
		IEditQualificationToiletService
{
	private UserQualificationPostBody postBodyObject;
	private IEditQualificationToiletServiceDelegate delegate;
	
	public EditQualificationToiletService()
	{
		performingRequest = false;
	}
	
	@Override
	public void editQualificationToiletService(IToilet toilet,
			int userQualification,
			IEditQualificationToiletServiceDelegate delegate)
	{
		if( performingRequest || null == delegate || null == toilet )
			return;
		performingRequest = true;
		this.delegate = delegate;
		postBodyObject = new UserQualificationPostBody(
				Settings.getInstance().getUserId(), userQualification);
		execute(APIService.getInstance().getEditCalificationToiletPutURL(toilet.getID().toString()));
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        
        if(performingRequest)
        {
        	if(statusLine.getStatusCode() >= 400 || statusLine.getStatusCode() < 200)
        	{
            	delegate.editQualificationToiletFinishWithError(this, statusLine.getStatusCode());
        	}
        	else
        	{
                delegate.editQualificationToiletFinish(this, (int)postBodyObject.getQualification());
        	}
            performingRequest = false;
            delegate = null;
        }
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.editQualificationToiletFinishWithError(this, statusCode);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.editQualificationToiletFinishWithError(this, -1);
        performingRequest = false;
        delegate = null;
	}

	@Override
	protected String generatePutBodyObject()
	{
		String jsonString = new Gson().toJson(postBodyObject, UserQualificationPostBody.class);
		return jsonString;
	}
}
