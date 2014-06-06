import java.util.*;
import java.awt.Color;
import java.lang.Math;

public class node {

	public double x_coordinate;
	public double y_coordinate;
	public Color randomColor;
	int name;
	LinkedList <Integer> list = new LinkedList<Integer>();
	public node(int i)
	{
		name = i;
		x_coordinate = (int)(800*Math.random());
		y_coordinate = (int)(500*Math.random());
		Random rand = new Random();
		float r = rand.nextFloat();
		float g = rand.nextFloat();
		float b = rand.nextFloat();
		randomColor = new Color(r, g, b);
		
	}
	
}
