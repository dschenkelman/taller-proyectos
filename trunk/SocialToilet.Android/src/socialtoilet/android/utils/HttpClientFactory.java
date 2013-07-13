package socialtoilet.android.utils;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.Credentials;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.DefaultHttpClient;

import android.util.Log;

public class HttpClientFactory
{
	public static HttpClient createClient()
	{
		DefaultHttpClient client = new DefaultHttpClient();
		CredentialsProvider provider = new BasicCredentialsProvider();
		Credentials credentials = new UsernamePasswordCredentials(
				Settings.getInstance().getUser(),
				Settings.getInstance().getPassword());
		Log.w("Puto", "***********" + credentials.getUserPrincipal() + "  <>  " + credentials.getPassword());
		provider.setCredentials(new AuthScope(AuthScope.ANY_HOST, AuthScope.ANY_PORT), credentials);
		client.setCredentialsProvider(provider);
		return client;
	}
}
