
public class Exponentiation implements Expression{
	 private Expression base;
	  private Expression exponent;

	  public Exponentiation(Expression base, 
	                                      Expression exponent) {
	    this.base = base;
	    this.exponent = exponent;
	  }

	  public int getType() {
	    return Expression.EXPONENTIATION_NODE;
	  }

	  public double getValue() {
	    return Math.pow(base.getValue(), exponent.getValue());
	  }
}
