import java.util.ArrayList;

public class AddSubtract implements Expression {

	ArrayList<Term> terms;
	
	AddSubtract(Expression exp , boolean positive)
	{
		this.terms =  new ArrayList<Term>();
		this.terms.add(new Term(exp,positive));
		
	}
	
	public void add(Expression exp , boolean positive)
	{
		this.terms.add(new Term(exp,positive));
	}
	
	public double getValue()
	{
		double t = 0;
		for(Term te : terms)
		{
			if(te.positive)
				t = t+ te.exp.getValue();
			else
				t= t-te.exp.getValue();
		}
		return t;
	}
	public int getType()
	{
		return Expression.ADDITION_NODE;
	}
	
}
