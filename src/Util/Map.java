package Util;

public class Map{
	static public boolean[][] placed;
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
