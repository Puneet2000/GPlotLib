
public class FuncDiff implements Differntial{

	public static final int SIN = 1;
	  public static final int COS = 2;
	  public static final int TAN = 3;

	  public static final int ASIN = 4;
	  public static final int ACOS = 5;
	  public static final int ATAN = 6;

	  public static final int SQRT = 7;
	  public static final int EXP = 8;

	  public static final int LN = 9;
	  public static final int LOG = 10;
	  public static final int LOG2 = 11;

	  private int function;
	  private Differntial argument;

	  public FuncDiff(int function,
	                                Differntial argument) {
	    super();
	    this.function = function;
	    this.argument = argument;
	  }

	  public int getType() {
	    return Differntial.FUNCTION_NODE;
	  }

	  public String getDifferntial() {
		  String s= argument.getDifferntial();
		  if(s!="0") {
		  if(s=="1")
			  s= "";
		  else s ="*"+s;
	    switch (function) {
	      case SIN:  return "(cos("+argument.getFunction()+")"+s+")";
	      case COS:  return "(-sin("+argument.getFunction()+")"+s+")";
	      case TAN:  return "((sec("+argument.getFunction()+")^2)"+s+")";
	      case ASIN: return "(1/sqrt(1-"+argument.getFunction()+"^2)"+s+")";
	      case ACOS: return "(-1/sqrt(1-"+argument.getFunction()+"^2)"+s+")";
	      case ATAN: return "(1/(1+"+argument.getFunction()+"^2)"+s+")";
	      case SQRT: return "(1/sqrt("+argument.getFunction()+")"+s+")";
	      case EXP:  return "(exp("+argument.getFunction()+")"+s+")";
	      case LN:   return "(1/("+argument.getFunction()+")"+s+")";
	      case LOG:  return "(1/("+argument.getFunction()+")"+s+")";
	      case LOG2: return "(1/("+argument.getFunction()+")"+s+")";
	    }
	    throw new RuntimeException("Invalid function id "+function+"!");
		  }
		  else
			  return "0";
	  }

	  public static int stringToFunction(String str) {
	    if (str.equals("sin")) return Function.SIN;
	    if (str.equals("cos")) return Function.COS;
	    if (str.equals("tan")) return Function.TAN;

	    if (str.equals("asin")) return Function.ASIN;
	    if (str.equals("acos")) return Function.ACOS;
	    if (str.equals("atan")) return Function.ATAN;

	    if (str.equals("sqrt")) return Function.SQRT;
	    if (str.equals("exp")) return Function.EXP;

	    if (str.equals("ln")) return Function.LN;
	    if (str.equals("log"))return Function.LOG;
	    if (str.equals("log2")) return Function.LOG2;

	    throw new  RuntimeException("Unexpected Function "+str+" found!");
	  }
	  public String IntToFunction() {
		  switch (function) {
	      case SIN:  return "sin";
	      case COS:  return "cos";
	      case TAN:  return "tan";
	      case ASIN: return "asin";
	      case ACOS: return "acos";
	      case ATAN: return "atan";
	      case SQRT: return "sqrt";
	      case EXP:  return "exp";
	      case LN:   return "ln";
	      case LOG:  return "log";
	      case LOG2: return "log2";
	    }
	    throw new RuntimeException("Invalid function id "+function+"!");
		  }

	  public static String getAllFunctions() {
	    return "sin|cos|tan|asin|acos|atan|sqrt|exp|ln|log|log2";
	  }

	@Override
	public String getFunction() {
		return IntToFunction() +"("+argument.getFunction()+")";
	}


	

}
