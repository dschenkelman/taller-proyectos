package socialtoilet.android.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class GetService extends AsyncTask<String, String, String>{

	public static String emptyResponseErrorType = "kEmptyResponseError";
	public static String clientProtocolResponseErrorType = "kClientProtocolResponseError";
	public static String ioResponseErrorType = "kIOResponseError";
	
	protected boolean performingRequest;
	
	@Override
	protected String doInBackground(String... uri)
	{
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        StatusLine statusLine = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            statusLine = response.getStatusLine();
            if(statusLine.getStatusCode() == HttpStatus.SC_OK)
            {
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                response.getEntity().writeTo(out);
                out.close();
                responseString = out.toString();
            }
            else
            {
                //Closes the connection.
                response.getEntity().getContent().close();
                throw new IOException(statusLine.getReasonPhrase());
            }
        }
        catch (ClientProtocolException e)
        {
            handleClientProtocolException(e);
        }
        catch (IOException e)
        {
        	handleStatusCodeNotOk(e, statusLine.getStatusCode());
        }
        return responseString;
	}

	protected void handleStatusCodeNotOk(IOException e, int statusCode)
	{
        performingRequest = false;
	}

	protected void handleClientProtocolException(ClientProtocolException e)
	{
        performingRequest = false;
	}
}
