
public class GPlotLib {

	public static void main(String args[])
	{
		Tokeniser tokenizer = new Tokeniser();
		  tokenizer.add("sin|cos|exp|ln|sqrt|asin|acos|atan|log|log2", 1); 
		  tokenizer.add("\\(", 2); 
		  tokenizer.add("\\)", 3); 
		  tokenizer.add("[+-]", 4); 
		  tokenizer.add("[*/]", 5); 
		  tokenizer.add("\\^", 6); 
		  tokenizer.add("[0-9]+",7); 
		  tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); 
		  
		  try {
			  tokenizer.tokenise("sin(x)^2+cos(x)^2");

			  for (Token tok : tokenizer.getToken()) {
			    System.out.println("" + tok.token + " " + tok.sequence);
			  }
			}
			catch (Exception e) {
			  System.out.println(e.getMessage());
			}
		  AddSubtract innerSum =
				  new AddSubtract();
				innerSum.add(new Constant(1), true);
				innerSum.add(new Constant(3), true);

				Expression sqrt =
				  new Function(
				    Function.SQRT,
				    innerSum);

				Expression expo =
				  new Exponentiation(
				    new Constant(2),
				    new Constant(4));

				MultDivide prod =
				  new MultDivide();
				prod.add(new Constant(3), true);
				prod.add(expo, true);

				AddSubtract expression =
				  new AddSubtract();
				expression.add(prod, true);
				expression.add(sqrt, true);

				System.out.println("The result is "
				  + expression.getValue());
	}
}
