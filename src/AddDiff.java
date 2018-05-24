import java.util.ArrayList;

public class AddDiff implements Differntial{
    ArrayList<Term2> terms;
	
	AddDiff(Differntial diff , boolean positive)
	{
		this.terms =  new ArrayList<Term2>();
		this.terms.add(new Term2(diff,positive));
		
	}
	
	public void add(Differntial diff , boolean positive)
	{
		this.terms.add(new Term2(diff,positive));
	}
	
	public String getDifferntial()
	{
		String d ="";
		for(Term2 te : terms)
		{  if(te.diff.getDifferntial()!="0") {
			if(te.positive)
				d = d + '+' + te.diff.getDifferntial();
			else
				d = d + '-' + te.diff.getDifferntial();
		}
		}
		return d;
	}
	public int getType()
	{
		return Differntial.ADDITION_NODE;
	}
	public String getFunction()
	{
		String f ="";
		for(Term2 te : terms)
		{
			if(te.positive)
				f = f + '+' + te.diff.getFunction();
			else
				f = f + '-' + te.diff.getFunction();
		}
		return f;
	}

}
