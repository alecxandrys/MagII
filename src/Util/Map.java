package Util;

public class Map{
	static public boolean[][] placed;
	static public int[][] shifts={{1,0},{-1,0},{0,1},{0,-1}};
	public Map(Integer x,Integer y)
	{
		placed=new boolean[x][y];
		for (int i=0;i<x;i++)
		{
			for (int j=0;j<y;j++)
			{
				placed[i][j]=false;
			}
		}
	}
}
