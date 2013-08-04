package socialtoilet.android.model;

public class ToiletTrait implements IToiletTrait
{
	private int id;
	private int traitId;
	private String description;
	private boolean isActive;
	
	@Override
	public int getId() { return id; }
	@Override
	public String getDescription() { return description; }
	@Override
	public boolean hasDescription() { return isActive; }
	@Override
	public void setHasDescription(boolean has) { isActive = has; }
	
	// TODO delete thius methods
	public void correct()
	{
		traitId = id;
	}
}
