import java.awt.*;
import java.lang.Math;
import javax.swing.*;
import java.util.ListIterator;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class visualize extends JPanel implements ActionListener{
	
	public static graph G;
	private static final long serialVersionUID=100L;
	Timer tm = new Timer(1,this);
	public double c1 = 2;
	public double c2 = 100;
	public double c3 = 100;
	public double c4 = 2;
	
	public visualize()
	{
		
	}
	public visualize(graph g)
	{
		G = g;
		/*for(int i = 0;i<G.number_ver;i++)
		{
			System.out.println(G.adjacency_list[i].x_coordinate +" "+ G.adjacency_list[i].y_coordinate);
			if(!G.adjacency_list[i].list.isEmpty())
				System.out.println(G.adjacency_list[i].list.getFirst());
		}*/
				
	}
	public void paintComponent(Graphics gui)
	{
		super.paintComponent(gui);
		
		//Random rand = new Random();
		for(int i = 0;i<G.number_ver;i++)
		{
			/*float r = rand.nextFloat();
			float g = rand.nextFloat();
			float b = rand.nextFloat();
			Color randomColor = new Color(r, g, b);*/
			gui.setColor(G.adjacency_list[i].randomColor);
			gui.fillOval((int)G.adjacency_list[i].x_coordinate,(int) G.adjacency_list[i].y_coordinate,30,30);
			//System.out.println(G.adjacency_list[i].x_coordinate +" "+ G.adjacency_list[i].y_coordinate);
		}
		gui.setColor(Color.BLACK);
		
		Graphics2D gui2 = (Graphics2D)gui;
		gui2.setStroke(new BasicStroke(3));
		for(int i = 0;i<G.number_ver;i++)
		{
			ListIterator<Integer> it = G.adjacency_list[i].list.listIterator();
			while(it.hasNext())
			{
				int x = it.next();
				gui2.drawLine((int)G.adjacency_list[i].x_coordinate+15, (int) G.adjacency_list[i].y_coordinate+15, (int)G.adjacency_list[x].x_coordinate+15,(int)G.adjacency_list[x].y_coordinate+15);
				//gui.drawLine((int)G.adjacency_list[i].x_coordinate + 30, (int) G.adjacency_list[i].y_coordinate+15, 30,15);
				
			}
			
		}
		tm.start();
	}
	
	double norm(double x1,double x2,double y1,double y2)
	{
		double a = Math.pow((x2-x1),2);
		double b = Math.pow((y2-y1),2);
		//System.out.println("fhgdfh" +a+" "+b);
		double t = Math.pow( a+b ,0.5);
		//System.out.println(t);
		return(t);
	}
	
	void calculateForce()
	{
		for(int i = 0;i<G.number_ver;i++)
		{
			
			double Fx = 0;
			double Fy = 0;
			double dirx,diry;
			for(int j = 0;j<G.number_ver;j++)
			{
				if(i!=j){
				double x1 = G.adjacency_list[i].x_coordinate;
				double x2 = G.adjacency_list[j].x_coordinate;
				double y1 = G.adjacency_list[i].y_coordinate;
				double y2 = G.adjacency_list[j].y_coordinate;
				//System.out.println(x1+" "+x2+" "+y1+" "+y2);
				if(G.adjacency_matrix[i][j] == 1)//attraction
				{
					dirx = ( x2-x1 )/norm(x1,x2,y1,y2);
					diry = ( y2-y1 )/norm(x1,x2,y1,y2);
					
					double F = c1*Math.log(norm(x1,x2,y1,y2)/c2);
					//System.out.println(dirx +" "+ diry +" "+ F);
					Fx = Fx + F*dirx;
					Fy = Fy + F*diry;
				}
				else//repel
				{
					dirx = ( x1-x2 )/norm(x1,x2,y1,y2);
					diry = ( y1-y2 )/norm(x1,x2,y1,y2);
					
					double F = c3/Math.pow(norm(x1,x2,y1,y2),2);
					
					Fx = Fx + F*dirx;
					Fy = Fy + F*diry;
					System.out.println(Fx +" " +Fy);
				}
			
			//change x and y coordinate of i
			
			G.adjacency_list[i].x_coordinate += Fx*c4;
			G.adjacency_list[i].y_coordinate += Fy*c4;
			}}
			
		}
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		calculateForce();
		repaint();
		
	}

}
