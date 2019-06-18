package ro.usv.dss;

public class Plant {
	private double sepalLength;
	private double sepalWidth;
	private double petalLength;
	private double petalWidth;
	private String plantType;

	public Plant() 
	{
		sepalLength = 0;
		sepalWidth = 0;
		petalLength = 0;
		petalWidth = 0;
		plantType = "";
	}
	
	public Plant(double sL, double sW, double pL, double pW, String pT)
	{
		this.sepalLength = sL;
		this.sepalWidth = sW;
		this.petalLength =  pL;
		this.petalWidth = pW;
		this.plantType = pT;
	}
	
	@Override
	public String toString()
	{
		return String.format("Planta cu dimensiunile sepalelor[%s, %s] , petalelor[%s, %s]"
				+ " este de tipul %s.", this.sepalLength, this.sepalWidth, this.petalLength, this.petalWidth, this.plantType);
	}
	
	public void setType(String type)
	{
		this.plantType =  type;
	}
	
	public String GetType()
	{
		return this.plantType;
	}	
	
	public double GetSepalWidth()
	{
		return this.sepalWidth;
	}
	
	public double GetSepalLength()
	{
		return this.sepalLength;
	}
	
	public double GetPetalWidth()
	{
		return this.petalWidth;
	}
	
	public double GetPetalLength()
	{
		return this.petalLength;
	}
}
