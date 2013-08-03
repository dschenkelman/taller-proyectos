package socialtoilet.android.model;

import java.util.UUID;

public interface IToilet extends IMapMarked
{
	UUID getID();
	String getDescription();
	String getAddress();

	float getRanking();
	int getUserCalification();
	int getUserCalificationsCount();
	void setUserCalification(int calification);
	void revertUserCalification();
	void setRating(IRating rating);
	
	boolean canBeUsedWithoutConsumption();
	boolean hasWater();
	boolean hasToiletPaper();
	boolean hasSoap();
	boolean hasMirror();
	boolean doToiletDoorCloses();
	boolean hasGotLadiesItemsOnSale();
	boolean hasGotCondomsOnSale();
	boolean isAptForHandicapped();
	boolean hasBabyRoom();
}
