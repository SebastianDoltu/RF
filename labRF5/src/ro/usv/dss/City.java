package ro.usv.dss;

public class City {
	private double x;
	private double y;
	private String Name;
	private String County;

	public City() 
	{
		Name = "initialised";
		County =  "initialised";
	}
	
	public City(double x, double y, String Name, String County)
	{
		this.x = x;
		this.y = y;
		this.Name =  Name;
		this.County = County;
	}
	
	@Override
	public String toString()
	{
		return String.format("Orasul %s cu punctele %s si %s se afla in judetul %s", this.Name, this.x, this.y, this.County);
	}
	
	public void setName(String Name)
	{
		this.Name =  Name;
	}
	
	public String GetCounty()
	{
		return this.County;
	}
	
	public void setCounty(String County)
	{
		this.County =  County;
	}
	
	public double GetX()
	{
		return this.x;
	}
	
	public double GetY()
	{
		return this.y;
	}
}
