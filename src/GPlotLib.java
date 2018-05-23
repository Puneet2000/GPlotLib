import java.util.ArrayList;
import java.util.LinkedList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class GPlotLib {
	public static ArrayList<Double> x , y;
	public static void main(String args[])
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
		  x = new ArrayList<Double>();
		  y= new ArrayList<Double>();
		  String function=JOptionPane.showInputDialog("Enter your function ( eg : sin(x)) : y = ");      
		  function = function.replaceAll("\\s","");
		  System.out.println(function);
		  int j=0;
		  double i=-25;
		
		 
		  
		  tokenizer.tokenise(function);
		  while (j<200) {
			  x.add(i);
			  Parser parser= new Parser(i);
			 
		    Expression expression = parser.Parse(tokenizer.getToken());
		    y.add(expression.getValue());
		    System.out.println("The value of the expression is "+expression.getValue());
			  i=i+0.25;
			  j++;
		   
		  }
		  new GPlotLib().drawGUI();
		 
		  
	}
	public void drawGUI()
	
	{
		JFrame jfrm = new JFrame("GPlotLib");
		
		jfrm.setSize(1000, 1010);
	
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
		PaintPanel pp = new PaintPanel(x,y);
		
		jfrm.add(pp);
	
		jfrm.setVisible(true);
	}
}
