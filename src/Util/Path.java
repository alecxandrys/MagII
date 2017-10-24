package Util;

import Agent.BaseAgent;

import java.util.ArrayList;

public class Path{
	private static Path single=null;

	public static Path getSingle(){
		if( single==null ){
			single=new Path();
		}
		return single;
	}

	private Path(){}

	public Integer getDistance(BaseAgent a,BaseAgent b){
		return this.getDistance(a.getCoordinate()[0],a.getCoordinate()[1],b.getCoordinate()[0],b.getCoordinate()[1]);
	}

	public Integer getDistance(int x1,int y1,int x2,int y2){
		return Math.abs(x1-x2)+Math.abs(y1-y2);
	}

	public ArrayList< int[] > getPath(int x1,int y1,int x2,int y2){
		Integer distance=getDistance(x1,y1,x2,y2);
		ArrayList< int[] > path=new ArrayList<>(distance);
		int cur_x=x1, cur_y=y2;

		while( cur_x!=x2&cur_y!=y2 ){
			if( cur_x!=x2 ){
				if( cur_x>x2 ){cur_x--;}else{cur_x++;}
				path.add(new int[]{cur_x,cur_y});
			}
			if( cur_y!=y2 ){
				if( cur_y>y2 ){cur_y--;}else{cur_y++;}
				path.add(new int[]{cur_x,cur_y});
			}
		}
		path.remove(path.size()-1);//удалить последнюю точку
		return path;
	}
}
