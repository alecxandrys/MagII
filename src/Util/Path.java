package Util;

import Agent.BaseAgent;
import FX.UnitController;

import java.util.ArrayList;
import java.util.concurrent.CopyOnWriteArrayList;

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
		while( distance>0 ){
			if( x1!=x2 ){
				if( x1>x2 ){x1--;}else{x1++;}
				path.add(new int[]{x1,y1});
				distance--;
			}
			if (distance<=0) break;
			if( y1!=y2 ){
				if( y1>y2 ){y1--;}else{y1++;}
				path.add(new int[]{x1,y1});
				distance--;
			}
		}
		//path.remove(path.size()-1);//удалить последнюю точку
		return path;
	}

	public BaseAgent getClosestEnemy(BaseAgent agent){
		CopyOnWriteArrayList< BaseAgent > list;
		if( agent.getIsRed()==1 ){
			list=UnitController.blueTeam;
		}else{list=UnitController.redTeam;}
		int cur_index=0, distance=10000, min_index=0;
		for( BaseAgent elem : list ){
			int range=Path.getSingle().getDistance(agent,elem);
			if( range<distance ){
				min_index=cur_index;
				distance=range;
			}
			cur_index++;
		}
		return list.get(min_index);
	}
}

