package socialtoilet.android.services.factories.mocks;

import java.util.ArrayList;
import java.util.Collection;
import java.util.UUID;

import socialtoilet.android.location.GPSTracker;
import socialtoilet.android.model.IRating;
import socialtoilet.android.model.IToilet;

public class ModelMockFactory
{
	private static ModelMockFactory instance;

	private Collection<IToilet> toilets;
	
	public static ModelMockFactory getIntance()
	{
		if(null == instance)
		{
			instance = new ModelMockFactory();
		}
		return instance;
	}
	
	private ModelMockFactory()
	{
		toilets = new ArrayList<IToilet>();
		toilets.add(new IToilet()
			{
				public String getMapTitle() { return "La Roma"; }
				public String getMapSnippet() { return "También venden pizza.";}
				public double getLongitude() { return GPSTracker.getInstance().getLongitude() + 0.001; }
				public double getLatitude() { return GPSTracker.getInstance().getLatitude() - 0.002; }
				public float getRanking() { return 2.5f; }
				public UUID getID() { return UUID.randomUUID(); }
				public String getDescription() { return "Es un buen baño"; }
				public String getAddress() { return "Av. Yrigoyen 1565, El Talar"; }
				public int getUserCalification() { return 0; }
				public int getUserCalificationsCount() { return 25; }
				public void setUserCalification(int calification) { }
				public void revertUserCalification() { }
				public void setRating(IRating rating) { }
				public boolean canBeUsedWithoutConsumption() { return false; }
				public boolean hasWater() { return false; }
				public boolean hasToiletPaper() { return false; }
				public boolean hasSoap() { return false; }
				public boolean hasMirror() { return false; }
				public boolean doToiletDoorCloses() { return false; }
				public boolean hasGotLadiesItemsOnSale() { return false; }
				public boolean hasGotCondomsOnSale() { return false; }
				public boolean isAptForHandicapped() { return false; }
				public boolean hasBabyRoom() { return false; }
				public void userCalificationRetrieved( int calification) { }
			});
		toilets.add(new IToilet()
			{
				public String getMapTitle() { return "Eso"; }
				public String getMapSnippet() { return "Siempre cerrado.";}
				public double getLongitude() { return GPSTracker.getInstance().getLongitude() + 0.003; }
				public double getLatitude() { return GPSTracker.getInstance().getLatitude() - 0.005; }
				public float getRanking() { return 2.5f; }
				public UUID getID() { return UUID.randomUUID(); }
				public String getDescription() { return "Esta re cuidado"; }
				public String getAddress() { return "Av. Yrigoyen 1355, El Talar"; }
				public int getUserCalification() { return 2; }
				public int getUserCalificationsCount() { return 31; }
				public void setUserCalification(int calification) { }
				public void revertUserCalification() { }
				public void setRating(IRating rating) { }
				public boolean canBeUsedWithoutConsumption() { return true; }
				public boolean hasWater() { return true; }
				public boolean hasToiletPaper() { return false; }
				public boolean hasSoap() { return false; }
				public boolean hasMirror() { return false; }
				public boolean doToiletDoorCloses() { return false; }
				public boolean hasGotLadiesItemsOnSale() { return false; }
				public boolean hasGotCondomsOnSale() { return false; }
				public boolean isAptForHandicapped() { return false; }
				public boolean hasBabyRoom() { return false; }
				public void userCalificationRetrieved( int calification) { }
			});
	}

	public Collection<IToilet> getNearToilets()
	{
		return toilets;
	}

	public void addToilet(IToilet toilet)
	{
		toilets.add(toilet);
	}

	public IToilet getToilet(UUID toiletId)
	{
		for(IToilet toilet : toilets)
		{
			if(toilet.getID().toString().equalsIgnoreCase(toiletId.toString()))
			{
				return toilet;
			}
		}
		return null;
	}
	
}
