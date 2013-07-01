package com.example.appsample.services;

import java.util.UUID;

public interface IRetrieveToiletsService
{
	
	void retrieveNearToilet(double latitude, double longitude, IRetrieveToiletsServiceDelegate delegate);
	void retrieveToilet(UUID toiletId, IRetrieveToiletsServiceDelegate detailToiletActivity);

}
