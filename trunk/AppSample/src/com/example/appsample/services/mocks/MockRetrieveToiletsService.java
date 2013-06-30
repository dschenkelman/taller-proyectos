package com.example.appsample.services.mocks;

import java.util.ArrayList;
import java.util.Collection;

import com.example.appsample.model.IToilet;
import com.example.appsample.services.IRetreiveToiletsService;
import com.example.appsample.services.IRetreiveToiletsServiceDelegate;

public class MockRetrieveToiletsService implements IRetreiveToiletsService
{

	private Collection<IToilet> bathrooms;
	
	public MockRetrieveToiletsService()
	{
		bathrooms = new ArrayList<IToilet>();
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 1;
			}
			@Override
			public double getLongitude()
			{
				return -58.652569;
			}
			@Override
			public double getLatitude()
			{
				return -34.469759;
			}
			@Override
			public String getMapTitle()
			{
				return "La Sol";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Todo cagado.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 3;
			}
			@Override
			public double getLongitude()
			{
				return -58.645509;
			}
			@Override
			public double getLatitude()
			{
				return -34.465991;
			}
			@Override
			public String getMapTitle()
			{
				return "La Aspro";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Aveces lo cierran pero es limpio.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 2;
			}
			@Override
			public double getLongitude()
			{
				return -58.651067;
			}
			@Override
			public double getLatitude()
			{
				return -34.469458;
			}
			@Override
			public String getMapTitle()
			{
				return "Mi Boliche";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Tenes que comprar algo para usarlo.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 1;
			}
			@Override
			public double getLongitude()
			{
				return -58.663362;
			}
			@Override
			public double getLatitude()
			{
				return -34.476371;
			}
			@Override
			public String getMapTitle()
			{
				return "197 y Pana";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ".";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 3;
			}
			@Override
			public double getLongitude()
			{
				return -58.663941;
			}
			@Override
			public double getLatitude()
			{
				return -34.476008;
			}
			@Override
			public String getMapTitle()
			{
				return "Hospital El Talar";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Hacerse el boludo acá.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 1;
			}
			@Override
			public double getLongitude()
			{
				return -58.661313;
			}
			@Override
			public double getLatitude()
			{
				return -34.475026;
			}
			@Override
			public String getMapTitle()
			{
				return "Bar-ato";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". El ambiente es muy feo.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 2;
			}
			@Override
			public double getLongitude()
			{
				return -58.617045;
			}
			@Override
			public double getLatitude()
			{
				return -34.486161;
			}
			@Override
			public String getMapTitle()
			{
				return "Mc Don Torcuato";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Típico McDonald's.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 1;
			}
			@Override
			public double getLongitude()
			{
				return -58.614492;
			}
			@Override
			public double getLatitude()
			{
				return -34.484693;
			}
			@Override
			public String getMapTitle()
			{
				return "YPF Don Torcuato";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Nunca quieren darte la llave.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 3;
			}
			@Override
			public double getLongitude()
			{
				return -58.555644;
			}
			@Override
			public double getLatitude()
			{
				return -34.497294;
			}
			@Override
			public String getMapTitle()
			{
				return "McDonald's Marquez";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Típico McDonald's.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 2;
			}
			@Override
			public double getLongitude()
			{
				return -58.546954;
			}
			@Override
			public double getLatitude()
			{
				return -34.500963;
			}
			@Override
			public String getMapTitle()
			{
				return "YPF Thames";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". No tiene espejo.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 3;
			}
			@Override
			public double getLongitude()
			{
				return -58.523426;
			}
			@Override
			public double getLatitude()
			{
				return -34.514733;
			}
			@Override
			public String getMapTitle()
			{
				return "Norcenter";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Lleno de baños.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 1;
			}
			@Override
			public double getLongitude()
			{
				return -58.511774;
			}
			@Override
			public double getLatitude()
			{
				return -34.523896;
			}
			@Override
			public String getMapTitle()
			{
				return "YPF Ugarte";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Muy buen baño.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 2;
			}
			@Override
			public double getLongitude()
			{
				return -58.456939;
			}
			@Override
			public double getLatitude()
			{
				return -34.54729;
			}
			@Override
			public String getMapTitle()
			{
				return "McDonald's Obras";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Te dan ganas de comer después.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 3;
			}
			@Override
			public double getLongitude()
			{
				return -58.457476;
			}
			@Override
			public double getLatitude()
			{
				return -34.547069;
			}
			@Override
			public String getMapTitle()
			{
				return "ESO Obras";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Inodoro muy calentito y confortable.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 3;
			}
			@Override
			public double getLongitude()
			{
				return -58.377095;
			}
			@Override
			public double getLatitude()
			{
				return -34.613799;
			}
			@Override
			public String getMapTitle()
			{
				return "UGI's Piedras";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Si esta clausurado te comes alta pizza y fue.";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 1;
			}
			@Override
			public double getLongitude()
			{
				return -58.369676;
			}
			@Override
			public double getLatitude()
			{
				return -34.617896;
			}
			@Override
			public String getMapTitle()
			{
				return "McDonald's FIUBA";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ".";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 2;
			}
			@Override
			public double getLongitude()
			{
				return -58.369606;
			}
			@Override
			public double getLatitude()
			{
				return -34.618086;
			}
			@Override
			public String getMapTitle()
			{
				return "ESO FIUBA";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ".";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 2;
			}
			@Override
			public double getLongitude()
			{
				return -58.367874;
			}
			@Override
			public double getLatitude()
			{
				return -34.617317;
			}
			@Override
			public String getMapTitle()
			{
				return "FIUBA - Mujeres";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Todos los pisos";
			}
		});
		bathrooms.add( new IToilet()
		{
			@Override
			public int getRanking()
			{
				return 1;
			}
			@Override
			public double getLongitude()
			{
				return -58.367809;
			}
			@Override
			public double getLatitude()
			{
				return -34.617702;
			}
			@Override
			public String getMapTitle()
			{
				return "FIUBA - Hombres";
			}
			@Override
			public String getMapSnippet()
			{
				return "Ranking: " + getRanking() + ". Todos los pisos";
			}
		});
	}

	@Override
	public void retrieveNearBathrooms(double latitude, double longitude,
			IRetreiveToiletsServiceDelegate delegate)
	{
		delegate.retreiveNearToiletsFinish(this, bathrooms);
	}
}
