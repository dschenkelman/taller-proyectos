package socialtoilet.android.services.mocks;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
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
import socialtoilet.android.services.IRetrieveNearToiletsService;
import socialtoilet.android.services.IRetrieveNearToiletsServiceDelegate;

public class MockRetrieveNearToiletsService extends AsyncTask<String, String, String> implements IRetrieveNearToiletsService
{

	private boolean performingRequest;
	private IRetrieveNearToiletsServiceDelegate delegate;
	
	public MockRetrieveNearToiletsService()
	{
		performingRequest = false;
	}

	@Override
	public void retrieveNearToilets(double latitude, double longitude,
			IRetrieveNearToiletsServiceDelegate delegate)
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
		doInBackground("http://192.168.56.1:2496/api/toilets/near?lat=-34.61955023847969&long=-58.43277096748352&radiusInMeters=8000");
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
       
        Collection<IToilet> toilets = null;
        
        delegate.retrieveNearToiletsFinish(this, toilets);
        performingRequest = false;
        delegate = null;
    }
}
