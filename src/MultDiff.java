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
		{  if(te.diff.getDifferntial()!="0") {
			if(te.diff.getDifferntial()!="1"){
			d = d + te.diff.getDifferntial();}
			int count=0;
			for(Term2 t : terms)
			{
				if(t!=te) {
					if(t.diff.getDifferntial()!="1") {
					
					d= d + "*" +t.diff.getFunction();
					
					
					}
				}
			}
			d = d+ "+";
		}}
		d.replaceAll("[+-]*", "*");
		d = d.substring(0, d.length() - 1);
		if(d.charAt(0)=='*')
			d= d.substring(1,d.length());
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
