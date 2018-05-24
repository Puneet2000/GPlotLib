
public class ExpoDiff implements Differntial{

	private Differntial base;
	  private Differntial exponent;

	  public ExpoDiff(Differntial base, Differntial exponent) {
	    this.base = base;
	    this.exponent = exponent;
	  }
	@Override
	public String getFunction() {
		return '('+ base.getFunction()+'^'+exponent.getFunction()+')';
	}

	@Override
	public String getDifferntial() {
		if(base.getType() ==Differntial.CONSTANT_NODE && exponent.getType()!=Differntial.CONSTANT_NODE)
		return "(("+ base.getFunction()+'^'+exponent.getFunction()+")*ln("+base.getFunction()+")*"+exponent.getDifferntial()+")";
		else if(base.getType() !=Differntial.CONSTANT_NODE && exponent.getType()==Differntial.CONSTANT_NODE)
		{   
			double c = Double.parseDouble(exponent.getFunction());
			
			return "(" + exponent.getFunction()+"*("+base.getFunction()+"^"+Double.toString(c-1)+")*("+ base.getDifferntial()+"))";
		}
		else if(base.getType() ==Differntial.CONSTANT_NODE && exponent.getType()==Differntial.CONSTANT_NODE)
		{
			return "0";
		}
		else
		{
			return "1";
		}
	}

	
	@Override
	public int getType() {
		return Differntial.EXPONENTIATION_NODE; 
	}
	

}
