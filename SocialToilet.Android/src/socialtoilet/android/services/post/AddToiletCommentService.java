package socialtoilet.android.services.post;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import android.util.Log;

import com.google.gson.Gson;

import socialtoilet.android.model.Comment;
import socialtoilet.android.service.api.APIService;

public class AddToiletCommentService extends PostService implements
		IAddToiletCommentService
		{

	private Comment postBodyObject;
	private IAddToiletCommentServiceDelegate delegate;
	
	public AddToiletCommentService()
	{
		performingRequest = false;
	}
	
	@Override
	public void addToiletComment(IAddToiletCommentServiceDelegate delegate, 
			String toiletId, Comment comment)
	{
		if(performingRequest || null == delegate || null == comment || 0 == toiletId.length())
			return;
		postBodyObject = comment;
		performingRequest = true;
		this.delegate = delegate;
		execute(APIService.getInstance().getAddToiletCommentPostURL(toiletId));
	}

    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        if(performingRequest)
        {
            if(200 > statusLine.getStatusCode()|| statusLine.getStatusCode() >= 400)
            {
            	delegate.addToiletCommentFinishWithError(this, statusLine.getStatusCode() + "");
            }
            else
            {
            	postBodyObject.stapTime();
                delegate.addToiletCommentFinish(this, postBodyObject);
            }
            performingRequest = false;
            delegate = null;
        }
    }

    @Override
    protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
    	delegate.addToiletCommentFinishWithError(this, ioResponseErrorType);
        performingRequest = false;
        delegate = null;
	}
    
    @Override
	protected void handleClientProtocolException(ClientProtocolException e)
	{
    	delegate.addToiletCommentFinishWithError(this, clientProtocolResponseErrorType);
        performingRequest = false;
        delegate = null;
	}

	@Override
	protected String generatePostBodyObject()
	{
		Log.d("Social Toiler", "addToiletComment.generatePostBodyObject()");
		String jsonString = new Gson().toJson(postBodyObject, Comment.class);
		return jsonString;
	}
}
