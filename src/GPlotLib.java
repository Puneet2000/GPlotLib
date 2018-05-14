
public class GPlotLib {

	public static void main(String args[])
	{
		Tokeniser tokenizer = new Tokeniser();
		  tokenizer.add("sin|cos|exp|ln|sqrt", 1); 
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
	}
}
