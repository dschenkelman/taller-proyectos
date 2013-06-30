package com.example.appsample.services;

import java.util.Collection;

import com.example.appsample.model.IToilet;

public interface IRetreiveToiletsServiceDelegate
{
	void retreiveNearToiletsFinish(IRetreiveToiletsService service, Collection<IToilet> nearBathrooms);
	void retreiveNearToiletsFinishWithError(IRetreiveToiletsService service, int errorCode);
}
