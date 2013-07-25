package socialtoilet.android.add;

import android.util.Log;

import com.google.ads.Ad;
import com.google.ads.AdListener;
import com.google.ads.AdRequest.ErrorCode;

public class STAdListener implements AdListener
{

	@Override
	public void onDismissScreen(Ad arg0)
	{
		// TODO Auto-generated method stub
		Log.d("Social Toilet", "onDismissScreen");
	}

	@Override
	public void onFailedToReceiveAd(Ad arg0, ErrorCode errCode)
	{
		// TODO Auto-generated method stub
		Log.d("Social Toilet", "onFailedToReceiveAd\n" + errCode);

	}

	@Override
	public void onLeaveApplication(Ad arg0)
	{
		// TODO Auto-generated method stub
		Log.d("Social Toilet", "onLeaveApplication");
	}

	@Override
	public void onPresentScreen(Ad arg0)
	{
		// TODO Auto-generated method stub
		Log.d("Social Toilet", "onPresentScreen");
	}

	@Override
	public void onReceiveAd(Ad arg0)
	{
		// TODO Auto-generated method stub
		Log.d("Social Toilet", "onReceiveAd");
	}

}
