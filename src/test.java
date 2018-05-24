
public class test {

	public static void main (String args[])
	{
		Tokeniser tokenizer = new Tokeniser();
		  tokenizer.add("sin|cos|exp|tan|ln|sqrt|asin|acos|atan|log|log2", 4); 
		  tokenizer.add("\\(", 5); 
		  tokenizer.add("\\)", 6); 
		  tokenizer.add("[+-]", 1); 
		  tokenizer.add("[*/]", 2); 
		  tokenizer.add("\\^", 3); 
		  tokenizer.add("[0-9]+",7); 
		  tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); 
		  tokenizer.tokenise("x*(x^2)");
		  DiffParser p = new DiffParser();
		  Differntial diff = p.Parse(tokenizer.getToken());
		  System.out.println(diff.getDifferntial());
		  
	}
	
}
