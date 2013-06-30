package com.example.appsample.services;

public interface IRetreiveToiletsService
{
	
	void retrieveNearBathrooms(double latitude, double longitude, IRetreiveToiletsServiceDelegate delegate);

}
