
public class Variable implements Expression {
	String name;
	double value;
	boolean isSet;
	
  Variable(String name)
  {
	  this.name = name;
	  this.isSet = false;
  }
  public void setValue(double value)
  {
	  
	  this.value = value;
	  this.isSet= true;
  }
  public int getType()
  {
	  return Expression.VARIABLE_NODE;
  }
  public double  getValue()
  {
	  if(isSet)
		  return value;
	  else
		  throw new RuntimeException("Not initialized");
  }
}
