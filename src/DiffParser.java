import java.util.LinkedList;

public class DiffParser {
	LinkedList<Token> tokens;
	  Token lookahead;
	
	public Differntial Parse(LinkedList<Token> tokens)
	  {  System.out.println("Inside PArse");
		  this.tokens = (LinkedList<Token>)tokens.clone();
		  lookahead = this.tokens.getFirst();
		   Differntial exp =expression();
		
		   if(lookahead.token != Token.EPSILON)
		   {
			   System.out.println("Inavlid Input 1 : " + lookahead.sequence);
			   System.exit(0);
		   }
		   return exp;
	  }
	  
	  public void nextToken()
	  {  System.out.println("Inside nextToken");
		  tokens.pop();
		  if (tokens.isEmpty())
			  lookahead = new Token(Token.EPSILON ,"");
		  else
			  lookahead = tokens.getFirst();
	  }
	  
	  public Differntial expression()
	  {
		  System.out.println("Inside Differntial");
		  Differntial exp =signedTerm();
		  return recrSum(exp);
	  }
	  public Differntial signedTerm()
	  {  System.out.println("Inside signedTerm");
		  if(lookahead.token == Token.PLUSMINUS)
		  {
			  boolean positive = lookahead.sequence.equals("+");
			    nextToken();
			    Differntial t = term();
			    if (positive)
			      return t;
			    else {
			    	AddDiff p = new AddDiff(t,true);
		        
		            return p;
			      
			      }
		  }
		  else
		  {
			  return term();
		  }
	  }
	  public Differntial recrSum(Differntial exp)
	  {         System.out.println("Inside recrSum");

			  if (lookahead.token == Token.PLUSMINUS) {
				    AddDiff sum;
				    
				    if (exp.getType() == Differntial.ADDITION_NODE)
				      sum = (AddDiff)exp;
				    else
				      {sum = new AddDiff(exp,true);
				    
				      }
			

				    boolean positive = lookahead.sequence.equals("+");
				    nextToken();
				    Differntial t = term();
				    sum.add(t, positive);
			  return recrSum(sum);
		  }
		  return exp;
	  }
	  
	  public Differntial term()
	  { System.out.println("Inside term");
		  Differntial exp =factor();
		  return  recrTerm(exp);
		  
	  }
	  public Differntial factor()
	  { System.out.println("Inside factor");
		Differntial expr =argument();
		return recrFactor(expr);
	  }
	  public Differntial recrTerm(Differntial expression)
	  {   System.out.println("Inside recrTerm");
		  if(lookahead.token == Token.MULTDIV)
		  {
			  MultDiff prod;

			    if (expression.getType() == Differntial.MULTIPLICATION_NODE)
			      prod = (MultDiff)expression;
			    else
			    { prod = new MultDiff(expression,true);}

			    boolean positive = lookahead.sequence.equals("*");
			    nextToken();
			    Differntial f = signedFactor();
			    prod.add(f, positive);
			  return recrTerm(prod);
		  }
		  return expression;
	  }
	  public Differntial signedFactor()
	  {    System.out.println("Inside signedFactor");
		  if(lookahead.token == Token.PLUSMINUS)
		  {
			  boolean positive = lookahead.sequence.equals("+");
			    nextToken();
			    Differntial t = factor();
			    if (positive)
			      return t;
			    else
			      {return new AddDiff(t, false);
			      }
		  }
		  else
		
			  return factor();
		  }
	  
	  public Differntial recrFactor(Differntial exp)
	  {   System.out.println("Inside recrfactor");
		  if (lookahead.token == Token.RAISED)
		    {
		     
		      nextToken();
		      Differntial exponent = signedFactor();
		      return new ExpoDiff(exp, exponent);
		      
		    }
		  return exp;
	  }
	  public Differntial argument()
	  {    System.out.println("Inside argument");
		  if (lookahead.token == Token.FUNCTION)
		    {
			  int function = FuncDiff.stringToFunction(lookahead.sequence);;
		      nextToken();
		      Differntial expr =argument();
		      return new FuncDiff(function, expr);
		    }
		    else if (lookahead.token == Token.OPEN_BRACKET)
		    {
		      nextToken();
		      Differntial expr =expression();
		   

		      if (lookahead.token != Token.CLOSE_BRACKET)
		      {
		    	  System.out.println("Invalid Input 2");
		    	  System.exit(0);
		      }

		      nextToken();
		      return expr; 
		    }
		    else
		    {
		     
		      return value();
		    }
	  }
	  
	  public Differntial value() {
		  System.out.println("Inside value");
		  if (lookahead.token == Token.NUMBER)
		    {
			  Differntial expr = new ConsDiff(lookahead.sequence);
		      nextToken();
		      return expr;
		    }
		    else if (lookahead.token == Token.VARIABLE)
		    {
		    	Differntial expr = new Vardiff(lookahead.sequence);
		    	
		      nextToken();
		      return expr;
		    }
		    else
		    {
		       System.out.println(lookahead.sequence);
		       System.out.println("Invalid Differntial");
		       System.exit(0);
		    }
		  return null;
	  }

}
