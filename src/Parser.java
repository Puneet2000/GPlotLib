import java.util.LinkedList;

public class Parser {
  LinkedList<Token> tokens;
  Token lookahead;
  double value;
  Parser (double value){
	  this.value = value;
  }
public Expression Parse(LinkedList<Token> tokens)
  {  System.out.println("Inside PArse");
	  this.tokens = (LinkedList<Token>)tokens.clone();
	  lookahead = this.tokens.getFirst();
	   Expression exp =expression();
	   System.out.println(exp.getValue());
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
  
  public Expression expression()
  {
	  System.out.println("Inside Expression");
	  Expression exp =signedTerm();
	  return recrSum(exp);
  }
  public Expression signedTerm()
  {  System.out.println("Inside signedTerm");
	  if(lookahead.token == Token.PLUSMINUS)
	  {
		  boolean positive = lookahead.sequence.equals("+");
		    nextToken();
		    Expression t = term();
		    if (positive)
		      return t;
		    else {
		    	AddSubtract p = new AddSubtract(t,positive);
	        
	            return p;
		      
		      }
	  }
	  else
	  {
		  return term();
	  }
  }
  public Expression recrSum(Expression exp)
  {         System.out.println("Inside recrSum");

		  if (lookahead.token == Token.PLUSMINUS) {
			    AddSubtract sum;
			    
			    if (exp.getType() == Expression.ADDITION_NODE)
			      sum = (AddSubtract)exp;
			    else
			      {sum = new AddSubtract(exp,true);
			    
			      }
		

			    boolean positive = lookahead.sequence.equals("+");
			    nextToken();
			    Expression t = term();
			    sum.add(t, positive);
		  return recrSum(sum);
	  }
	  return exp;
  }
  
  public Expression term()
  { System.out.println("Inside term");
	  Expression exp =factor();
	  return  recrTerm(exp);
	  
  }
  public Expression factor()
  { System.out.println("Inside factor");
	Expression expr =argument();
	return recrFactor(expr);
  }
  public Expression recrTerm(Expression expression)
  {   System.out.println("Inside recrTerm");
	  if(lookahead.token == Token.MULTDIV)
	  {
		  MultDivide prod;

		    if (expression.getType() == Expression.MULTIPLICATION_NODE)
		      prod = (MultDivide)expression;
		    else
		    { prod = new MultDivide(expression,true);}

		    boolean positive = lookahead.sequence.equals("*");
		    nextToken();
		    Expression f = signedFactor();
		    prod.add(f, positive);
		  return recrTerm(prod);
	  }
	  return expression;
  }
  public Expression signedFactor()
  {    System.out.println("Inside signedFactor");
	  if(lookahead.token == Token.PLUSMINUS)
	  {
		  boolean positive = lookahead.sequence.equals("+");
		    nextToken();
		    Expression t = factor();
		    if (positive)
		      return t;
		    else
		      {return new AddSubtract(t, false);
		      }
	  }
	  else
	
		  return factor();
	  }
  
  public Expression recrFactor(Expression exp)
  {   System.out.println("Inside recrfactor");
	  if (lookahead.token == Token.RAISED)
	    {
	     
	      nextToken();
	      Expression exponent = signedFactor();
	      return new Exponentiation(exp, exponent);
	      
	    }
	  return exp;
  }
  public Expression argument()
  {    System.out.println("Inside argument");
	  if (lookahead.token == Token.FUNCTION)
	    {
		  int function = Function.stringToFunction(lookahead.sequence);;
	      nextToken();
	      Expression expr =argument();
	      return new Function(function, expr);
	    }
	    else if (lookahead.token == Token.OPEN_BRACKET)
	    {
	      nextToken();
	      Expression expr =expression();
	   

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
  
  public Expression value() {
	  System.out.println("Inside value");
	  if (lookahead.token == Token.NUMBER)
	    {
		  Expression expr = new Constant(lookahead.sequence);
	      nextToken();
	      return expr;
	    }
	    else if (lookahead.token == Token.VARIABLE)
	    {
	    	Expression expr = new Variable(lookahead.sequence,value);
	    	
	      nextToken();
	      return expr;
	    }
	    else
	    {
	       System.out.println(lookahead.sequence);
	       System.out.println("Invalid Expression");
	       System.exit(0);
	    }
	  return null;
  }
  
  }
  

