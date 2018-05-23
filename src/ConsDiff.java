
public class ConsDiff implements Differntial{
	private double value;
	public ConsDiff(double value)
	{
		this.value = value;
	}
	
	public ConsDiff(String value)
	{
		this.value = Double.valueOf(value);
	}
	public int getType()
	{
		return Differntial.CONSTANT_NODE;
	}
	public String getDifferntial()
	{
		return "0";
	}
	public String getFunction()
	{
		return Double.toString(value); 
	}

	

}
