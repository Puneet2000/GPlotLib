import java.awt.*;
import java.util.*;
import javax.swing.*;


public class PaintPanel extends JPanel {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ArrayList<Double> pointsx ,pointsy;
	public PaintPanel(ArrayList<Double> pointsx,ArrayList<Double> pointsy) {

	this.pointsx = pointsx;
	this.pointsy = pointsy;

	}

	protected void paintComponent(Graphics g) {

	super.paintComponent(g);


	for(int i=0;i<=1010;i=i+20)
	g.drawLine(0, i, 1000, i);
	for(int i=0;i<=1000;i=i+20)
	g.drawLine(i, 0, i, 1000);
	Graphics2D g2D = (Graphics2D)g;
	g2D.setStroke(new BasicStroke(3F));
	g.drawLine(500,0,500,1000);
	g.drawLine(0,500,1000,500);
	g2D.setStroke(new BasicStroke(3F));
	g2D.setColor(Color.RED);
	for(int i=0;i<pointsx.size()-1;i++)
	{
		
		Double x1=pointsx.get(i),x2=pointsx.get(i+1),y1=pointsy.get(i),y2=pointsy.get(i+1);
	   g.drawLine(scalex(x1),scaley(y1),scalex(x2),scaley(y2));


	}


	}
	public int scalex(double x)
	{
		
			return  (int)(500 + x*20);
		
	}
	public int scaley(double y)
	{
		return (int)( 500 -y*20);
	}



}
