package speiger.src.api.util.config;

public class EntityCounter
{
	int ID;
	
	public EntityCounter()
	{
		ID = 1;
	}
	
	public EntityCounter(int i)
	{
		ID = i;
	}

	public int getCurrentID()
	{
		return ID;
	}
	
	public void updateToNextID()
	{
		ID++;
	}
	
}
