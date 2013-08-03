package socialtoilet.android.model;

import java.util.UUID;

public class UserQualificationPostBody
{
	UUID userId;
	double rating;
	
	public UserQualificationPostBody(UUID userId, double rating)
	{
		this.userId = userId;
		this.rating = rating;
	}

	public double getQualification()
	{
		return rating;
	}
}
