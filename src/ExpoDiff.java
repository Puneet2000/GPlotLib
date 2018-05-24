
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
		String s = (exponent.getDifferntial()!="1"? "*"+exponent.getDifferntial() :"");
		String b = (base.getDifferntial()!="1"? "(*"+base.getDifferntial()+")" :"");
		if(base.getType() ==Differntial.CONSTANT_NODE && exponent.getType()!=Differntial.CONSTANT_NODE)
		return "(("+ base.getFunction()+'^'+exponent.getFunction()+")*ln("+base.getFunction()+")"+s+")";
		else if(base.getType() !=Differntial.CONSTANT_NODE && exponent.getType()==Differntial.CONSTANT_NODE)
		{   
			double c = Double.parseDouble(exponent.getFunction());
			String k = ((Double.toString(c-1)!="1")? "^"+Double.toString(c-1):"");
			return "(" + exponent.getFunction()+"*("+base.getFunction()+k+")"+ b+")";
		}
		else if(base.getType() ==Differntial.CONSTANT_NODE && exponent.getType()==Differntial.CONSTANT_NODE)
		{
			return "0";
		}
		else
		{
			return "(("+base.getFunction()+"^"+exponent.getFunction()+")*(("+ exponent.getFunction()+"/"+base.getFunction()+")*"+base.getDifferntial()+")+(ln("+base.getFunction()+")*"+exponent.getDifferntial()+"))";
		}
	}

	
	@Override
	public int getType() {
		return Differntial.EXPONENTIATION_NODE; 
	}
	

}
