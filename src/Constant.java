
public class Constant implements Expression {

	private double value;
	public Constant(double value)
	{
		this.value = value;
	}
	
	public void Constant(String value)
	{
		this.value = Double.valueOf(value);
	}
	public int getType()
	{
		return Expression.CONSTANT_NODE;
	}
	public double getValue()
	{
		return value;
	}
}
