
import java.util.LinkedList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Tokeniser {
	LinkedList <TokenInfo> tokenInfos ;
    LinkedList<Token> tokens;
	 public Tokeniser() {
		 tokenInfos = new LinkedList<TokenInfo>();
		 tokens = new LinkedList<Token>();
	 }
	
	private class TokenInfo {
	    public final Pattern regex;
	    public final int token;

	    public TokenInfo(Pattern regex, int token) {
	      super();
	      this.regex = regex;
	      this.token = token;
	    }
	  }
	
	
	public void add(String regex , int tokenId)
	{
		tokenInfos.add(new TokenInfo(Pattern.compile("^("+regex+")"),tokenId));
		
	}
	public void tokenise(String str)
	{
		String s = new String(str);
		tokens.clear();
		while(!s.equals(""))
		{
			  boolean match = false;
			for (TokenInfo info : tokenInfos) {
			      Matcher m = info.regex.matcher(s);
			      if (m.find()) {
			       match = true;

			        String tok = m.group().trim();
			        tokens.add(new Token(info.token, tok));

			        s = m.replaceFirst("");
			        break;
			      }
			     
			    	        
		}
		
			 if (!match) { System.out.println("Invalid syntax"); System.exit(0);;}
	}

	}
	public LinkedList<Token> getToken()
	{
		return tokens;
	}

	}
