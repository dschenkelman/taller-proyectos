package com.example.appsample.services;

import java.util.Collection;

import com.example.appsample.model.IToilet;

public interface IRetrieveToiletsServiceDelegate
{
	void retrieveNearToiletsFinish(IRetrieveToiletsService service, Collection<IToilet> nearBathrooms);
	void retreiveNearToiletsFinishWithError(IRetrieveToiletsService service, int errorCode);
	
	void retrieveToiletFinish(IRetrieveToiletsService mockRetrieveToiletsService, IToilet toilet);
	void retrieveToiletFinishWithError(IRetrieveToiletsService mockRetrieveToiletsService, int errorCode);
}
