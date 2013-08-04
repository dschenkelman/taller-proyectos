package socialtoilet.android.model;

public class ToiletTrait implements IToiletTrait
{
	private int id;
	private String description;
	private boolean value;
	
	@Override
	public int getId() { return id; }
	@Override
	public String getDescription() { return description; }
	@Override
	public boolean hasDescription() { return value; }
	@Override
	public void setHasDescription(boolean has) { value = has; }
	
	
}
