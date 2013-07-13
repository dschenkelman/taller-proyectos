package socialtoilet.android.services;

import java.io.IOException;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public abstract class PostService extends AsyncTask<String, String, String>
{
	public static String emptyResponseErrorType = "kEmptyResponseError";
	public static String clientProtocolResponseErrorType = "kClientProtocolResponseError";
	public static String ioResponseErrorType = "kIOResponseError";
	
	protected boolean performingRequest;

	@Override
	protected String doInBackground(String... uri)
	{
	    HttpClient httpclient = new DefaultHttpClient();
	    HttpPost httppost = new HttpPost("http://www.yoursite.com/script.php");
	    HttpResponse response = null;
        StatusLine statusLine = null;
	    try 
	    {
	        httppost.setEntity(new UrlEncodedFormEntity(generatePostBody()));
	        response = httpclient.execute(httppost);
            statusLine = response.getStatusLine();
	    }
	    catch (ClientProtocolException e)
	    {
            handleClientProtocolException(e);
        }
        catch (IOException e)
        {
        	handleStatusCodeNotOk(e, ((null == statusLine) ? -1 : statusLine.getStatusCode()));
	    }
		return response.toString();
	}
	
	abstract protected List<? extends NameValuePair> generatePostBody();

	protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
        performingRequest = false;
	}

	protected void handleClientProtocolException(ClientProtocolException e)
	{
        performingRequest = false;
	}
}
