import java.util.LinkedList;

public class Parser {
  LinkedList<Token> tokens;
  Token lookahead;
  
  @SuppressWarnings("unchecked")
public void Parse(LinkedList<Token> tokens)
  {
	  this.tokens = (LinkedList<Token>)tokens.clone();
	  lookahead = this.tokens.getFirst();
	   expression();
	   
	   if(lookahead.token != Token.EPSILON)
	   {
		   System.out.println("Inavlid Input");
		   System.exit(0);
	   }
  }
  
  public void nextToken()
  {
	  tokens.pop();
	  if (tokens.isEmpty())
		  lookahead = new Token(Token.EPSILON ,"");
	  else
		  lookahead = tokens.getFirst();
  }
  
  public void expression()
  {
	  signedTerm();
	  recrSum();
  }
  public void signedTerm()
  {
	  if(lookahead.token == Token.PLUSMINUS)
	  {
		  nextToken();
		  term();
	  }
	  else
	  {
		  term();
	  }
  }
  public void recrSum()
  {
	  if (lookahead.token ==Token.PLUSMINUS)
	  {
		  nextToken();
		  term();
		  recrSum();
	  }
	  
  }
  
  public void term()
  {
	  factor();
	  recrTerm();
	  
  }
  public void factor()
  {
	argument();
	recrFactor();
  }
  public void recrTerm()
  {
	  if(lookahead.token == Token.MULTDIV)
	  {
		  nextToken();
		  signedFactor();
		  recrTerm();
	  }
  }
  public void signedFactor()
  {
	  if(lookahead.token == Token.PLUSMINUS)
	  {
		  nextToken();
		  factor();
	  }
	  else
	
		  factor();
	  }
  
  public void recrFactor()
  {
	  if (lookahead.token == Token.RAISED)
	    {
	     
	      nextToken();
	      signedFactor();
	    }
  }
  public void argument()
  {
	  if (lookahead.token == Token.FUNCTION)
	    {
	      
	      nextToken();
	      argument();
	    }
	    else if (lookahead.token == Token.OPEN_BRACKET)
	    {
	      nextToken();
	      expression();

	      if (lookahead.token != Token.CLOSE_BRACKET)
	      {
	    	  System.out.println("Invalid Input");
	    	  System.exit(0);
	      }

	      nextToken();
	    }
	    else
	    {
	     
	      value();
	    }
  }
  
  public void value() {
	  if (lookahead.token == Token.NUMBER)
	    {
	      nextToken();
	    }
	    else if (lookahead.token == Token.VARIABLE)
	    {
	      nextToken();
	    }
	    else
	    {
	       System.out.println("Invalid Expression");
	       System.exit(0);
	    }
  }
  
  }
  

