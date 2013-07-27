package socialtoilet.android.services;

import java.io.IOException;
import java.util.UUID;

import org.apache.http.client.ClientProtocolException;

import socialtoilet.android.model.IToilet;
import socialtoilet.android.service.api.APIService;

public class CalificateToiletService extends PostService implements ICalificateToiletService
{
	private UUID toiletId;
	private int userCalification;
	
	private ICalificateToiletServiceDelegate delegate;
	
	public CalificateToiletService()
	{
		performingRequest = false;
	}
	
	@Override
	public void calificateToiletService(IToilet toilet, int userCalification,
			ICalificateToiletServiceDelegate delegate)
	{
		if( performingRequest || null == delegate || null == toilet )
			return;
		toiletId = toilet.getID();
		this.userCalification = userCalification;
		performingRequest = true;
		this.delegate = delegate;
		execute(APIService.getInstance().getCalificateToiletPostURL());
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        delegate.calificateToiletFinish(this);
        performingRequest = false;
        delegate = null;
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.calificateToiletFinishWithError(this, ioResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.calificateToiletFinishWithError(this, clientProtocolResponseErrorType);
        performingRequest = false;
        delegate = null;
	}

	@Override
	protected String generatePostBodyObject()
	{
		String jsonString = "";
		// TODO generate post body with userCalification and toiletId
		return jsonString;
	}

}
