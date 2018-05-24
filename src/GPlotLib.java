import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

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
		  JFrame jf = new JFrame("Menu");
		  jf.setSize(200,100);
		  jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		  JPanel jp = new JPanel();
		  JButton Plotter = new JButton ("Plotter");
		
		  JButton Calculator = new JButton("Calculator");
		  JButton derivative = new JButton("Find Derivative");
		  jp.add(Plotter);
		  jp.add(Calculator);
		  jp.add(derivative);
		  jf.add(jp);
		  Plotter.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String function=JOptionPane.showInputDialog(jf,"Enter your function ( eg : sin(x)  ) : y = ");      
				  function = function.replaceAll("\\s","");
				  if(function!=null && function.length()>0 ) {
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
			  } 
			} );
		  
		  Calculator.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String function=JOptionPane.showInputDialog("Enter your Expression");      
				  function = function.replaceAll("\\s","");
				  if(function!=null && function.length()>0) {
				  System.out.println(function); 
				  tokenizer.tokenise(function);
				  Parser parser= new Parser(0);
					 
				  Expression expression = parser.Parse(tokenizer.getToken());
				  JOptionPane.showMessageDialog(jf, expression.getValue(), "Answer is : ",1);
				  
			   
			  } 
			  }
			} );
		  
		  derivative.addActionListener(new ActionListener() { 
			  public void actionPerformed(ActionEvent e) { 
				  String function=JOptionPane.showInputDialog("Enter your Expression");      
				  function = function.replaceAll("\\s","");
				  if(function!=null && function.length()>0) {
				  System.out.println(function); 
				  tokenizer.tokenise(function);
				  DiffParser parser= new DiffParser();
					 
				  Differntial expression = parser.Parse(tokenizer.getToken());
				  JOptionPane.showMessageDialog(jf, expression.getDifferntial(), "The Derivative of function is : ",1);
				  
			   
			  } 
			  }
			} );
		  jf.setVisible(true);
		  
		 
		  
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
