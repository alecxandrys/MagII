package Util;

import Agent.BaseAgent;

public class Path{
	private static Path single=null;

	public static Path getSingle(){
		if( single==null ){
			single=new Path();
		}
		return single;
	}

	private Path(){}

	public Integer getDistance(BaseAgent a,BaseAgent b)
	{
		return this.getDistance(a.getCoordinate()[0],a.getCoordinate()[1],b.getCoordinate()[0],b.getCoordinate()[1]);
	}
	public Integer getDistance(int x1,int y1,int x2,int y2)
	{
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}
}
