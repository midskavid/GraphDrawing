import java.awt.Color;
import java.io.*;
import javax.swing.JFrame;


public class graph {
	public static int number_ver;
	public static int number_edges;
	public node[] adjacency_list;
	public int[][] adjacency_matrix;
	
	graph()
	{
		adjacency_list = new node[number_ver];
		
		adjacency_matrix = new int[number_ver][];
		for(int i = 0;i<number_ver;i++)
		{
			adjacency_matrix[i] = new int[number_ver];	
		}
		/*
		for(int i = 0;i<number_ver;i++)
		{
			for(int j = 0;j<number_ver;j++)
			{
				System.out.print(adjacency_matrix[i][j]);
			}
			System.out.println();
		}*/
	}
	
	public static void main(String args[])throws IOException
	{
		
		
		BufferedReader buf = new BufferedReader(new InputStreamReader(System.in));
		System.out.println("Enter the number of vertices in the Graph : ");
		number_ver = Integer.parseInt(buf.readLine());
		//System.out.println(number_ver);
		
		graph G = new graph();
		
		//node[] objects = new node[number_ver];
		
		for(int i = 0;i<number_ver;i++)
		{
			G.adjacency_list[i] = new node(i);
		}
		
		System.out.println("Enter the number of edges : ");
		number_edges = Integer.parseInt(buf.readLine());
		
		for(int i = 0;i<number_edges;i++)
		{
			String[] aa = buf.readLine().split(" ");
			int st = Integer.parseInt(aa[0]);
			int dst = Integer.parseInt(aa[1]);
			
			G.adjacency_list[st].list.add(dst);
			G.adjacency_matrix[st][dst] = 1; 
			G.adjacency_matrix[dst][st] = 1;//for force calculation
			
		}
		/*
		for(int i = 0;i<number_ver;i++)
		{
			System.out.println(G.adjacency_list[i].x_coordinate +" "+ G.adjacency_list[i].y_coordinate);
			if(!G.adjacency_list[i].list.isEmpty())
				System.out.println(G.adjacency_list[i].list.getFirst());
		}
		*/
		visualize Graph = new visualize(G);

		JFrame jf = new JFrame();
		jf.setTitle("Force Graph algorithm");
		jf.setSize(1080,900);
		jf.setVisible(true);
		jf.setBackground(Color.WHITE);
		//t.paintComponents(null);
		jf.add(Graph);
		jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
	
}




