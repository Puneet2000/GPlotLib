import java.util.ArrayList;

public class MultDiff implements Differntial{
ArrayList<Term2> terms;
	
	MultDiff(Differntial diff , boolean positive)
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
		String d="";
		for(Term2 te : terms)
		{  int flag=0;
			if(te.diff.getDifferntial() !="0")
		{  
			if(te.diff.getDifferntial()!="1")
			d = d + te.diff.getDifferntial();
			else flag=1;
		
			for(Term2 t : terms)
			{
				if(t!=te) {
			        if(flag==0)
					d= d + "*" +t.diff.getFunction();
			        else {
			        	d =d + t.diff.getFunction();
			        	flag=0;
			        }
				
				}
			}
			d = d+ "+";
		}
		}
	
		d = d.substring(0, d.length() - 1);
	
		return d;
	}
	public int getType()
	{
		return Differntial.MULTIPLICATION_NODE;
	}
	
	public String getFunction()
	{
		String f="";
		for(Term2 te : terms)
		{
			if(te.positive)
				f=f+'*'+te.diff.getFunction();
			else
				f=f+'/'+te.diff.getFunction();
		}
		return f;
		
	}

}
