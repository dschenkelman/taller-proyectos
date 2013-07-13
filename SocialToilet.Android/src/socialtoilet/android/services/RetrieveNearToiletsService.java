package socialtoilet.android.services;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

import socialtoilet.android.model.IToilet;

public class RetrieveNearToiletsService extends AsyncTask<String, String, String> implements IRetrieveNearToiletsService
{

	private boolean performingRequest;
	private IRetrieveNearToiletsServiceDelegate delegate;
	
	public RetrieveNearToiletsService()
	{
		performingRequest = false;
	}

	@Override
	public void retrieveNearToilets(double latitude, double longitude,
			int distanceInMeters, IRetrieveNearToiletsServiceDelegate delegate)
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
		execute("http://192.168.1.35:8080/api/toilets/near?lat="+latitude+"&long="+longitude+"&radiusInMeters="+distanceInMeters);
	}

	@Override
	protected String doInBackground(String... uri)
	{
        HttpClient httpclient = new DefaultHttpClient();
        HttpResponse response;
        String responseString = null;
        try {
            response = httpclient.execute(new HttpGet(uri[0]));
            StatusLine statusLine = response.getStatusLine();
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
            //TODO Handle problems..
            performingRequest = false;
        }
        catch (IOException e)
        {
            //TODO Handle problems..
            performingRequest = false;
        }
        return responseString;
	}
	
    @Override
    protected void onPostExecute(String result) 
    {
        super.onPostExecute(result);
        //Do anything with response..
       
        Collection<IToilet> toilets = new ArrayList<IToilet>();
        
        delegate.retrieveNearToiletsFinish(this, toilets);
        performingRequest = false;
        delegate = null;
    }

}
