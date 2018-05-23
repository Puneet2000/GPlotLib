
public class Vardiff implements Differntial{

	String name;
	
  Vardiff(String name )
  {
	  this.name = name;
	
  }
	@Override
	public String getFunction() {
		return name;
	}

	@Override
	public String getDifferntial() {
		return "1";
	}

	@Override
	public int getType() {
		return Differntial.VARIABLE_NODE;
	}
	

}
