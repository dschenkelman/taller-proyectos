package socialtoilet.android.model;

public class Rating implements IRating
{
    private float rating;
    private int votes;
    
	public Rating() { }
	@Override
	public float getRating() { return rating; }
	@Override
	public int getCalificationCount() { return votes; }
}
