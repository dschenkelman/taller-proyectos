package socialtoilet.android.model;

import java.util.UUID;

public class UserQualificationPostBody
{
	UUID userId;
	float qualification;
	
	public UserQualificationPostBody(UUID userId, float qualification)
	{
		this.userId = userId;
		this.qualification = qualification;
	}

	public float getQualification()
	{
		return qualification;
	}
}
