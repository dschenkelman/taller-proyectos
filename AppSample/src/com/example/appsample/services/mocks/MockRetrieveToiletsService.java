package com.example.appsample.services.mocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import com.example.appsample.model.IToilet;
import com.example.appsample.services.IRetrieveToiletsService;
import com.example.appsample.services.IRetrieveToiletsServiceDelegate;

public class MockRetrieveToiletsService implements IRetrieveToiletsService
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
			@Override
			public UUID getID()
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b38");
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
			@Override
			public UUID getID()
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b37");
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
			@Override
			public UUID getID()
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b36");
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
			@Override
			public UUID getID()
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b35");
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
			@Override
			public UUID getID()
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b34");
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
			@Override
			public UUID getID()
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b33");
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
			@Override
			public UUID getID()
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b32");
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
			@Override
			public UUID getID()
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b31");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b30");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b29");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b28");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b27");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b26");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b25");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b24");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b23");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b22");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b21");
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
			@Override
			public UUID getID() 
			{
				return UUID.fromString("44e128a5-ac7a-4c9a-be4c-224b6bf81b20");
			}
		});
	}

	@Override
	public void retrieveNearToilet(double latitude, double longitude,
			IRetrieveToiletsServiceDelegate delegate)
	{
		if( null == delegate )
		{
			return;
		}
		delegate.retrieveNearToiletsFinish(this, bathrooms);
	}

	@Override
	public void retrieveToilet(UUID toiletId, IRetrieveToiletsServiceDelegate delegate)
	{
		if( null == delegate )
		{
			return;
		}
		for( IToilet toilet : bathrooms )
		{
			if( 0 == toilet.getID().compareTo(toiletId) )
			{
				delegate.retrieveToiletFinish(this, toilet);
			}
		}
	}
}
