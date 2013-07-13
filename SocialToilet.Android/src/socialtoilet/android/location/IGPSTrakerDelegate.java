package socialtoilet.android.location;

import android.location.Location;

public interface IGPSTrakerDelegate
{
	void locationChange(Location location);
}
