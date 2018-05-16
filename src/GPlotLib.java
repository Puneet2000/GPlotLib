import java.util.ArrayList;

import javax.swing.JFrame;

public class GPlotLib {
	public static ArrayList<Double> x , y;
	public static void main(String args[])
	{
		Tokeniser tokenizer = new Tokeniser();
		  tokenizer.add("sin|cos|exp|ln|sqrt|asin|acos|atan|log|log2", 1); 
		  tokenizer.add("\\(", 2); 
		  tokenizer.add("\\)", 3); 
		  tokenizer.add("[+-]", 4); 
		  tokenizer.add("[*/]", 5); 
		  tokenizer.add("\\^", 6); 
		  tokenizer.add("[0-9]+",7); 
		  tokenizer.add("[a-zA-Z][a-zA-Z0-9_]*", 8); 
		  x = new ArrayList<Double>();
		  y= new ArrayList<Double>();
		  int j=0;
		  double i=-25;
		  while(j<200)
		  {
			  x.add(i);
			  y.add(1/i);
			  i=i+0.25;
			  j++;
		  }
		  new GPlotLib().drawGUI();
		  
		  
	}
	public void drawGUI()
	
	{
		JFrame jfrm = new JFrame("Paint Demo");
		
		jfrm.setSize(1000, 1010);
	
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		PaintPanel pp = new PaintPanel(x,y);
		
		jfrm.add(pp);
	
		jfrm.setVisible(true);
	}
}
