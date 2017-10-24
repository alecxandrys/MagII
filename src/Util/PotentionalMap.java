package Util;

import Agent.BaseAgent;
import Agent.Military.Military;
import FX.Main;
import FX.UnitController;

public class PotentionalMap{
	private static PotentionalMap single=null;

	public static PotentionalMap getSingle(){
		if( single==null ){
			single=new PotentionalMap();
		}
		return single;
	}
	private PotentionalMap(){}

	private float[][] result=new float[Main.xSize][Main.ySize];

	/**
	 * Получаем наилучшие координаты, к которым стремимся
	 * @param isRed 1 если красные, -1 если синие для правильного учета потенциала
	 * @return координата с наибольшим (лучшим) потенциалом
	 */
	public Integer[] getBestCoordinate(int isRed)
	{
		getMap(isRed);
		int a=0,b=0;
		float max=-1000;
		for (int i=0;i<Main.xSize;i++)
		{
			for(int j=0;j<Main.ySize;j++)
			{
				if (result[i][j]>max)
				{
					a=i;
					b=j;
					max=result[i][j];
				}
			}
		}
		Integer[] coordinate=new Integer[2];
		coordinate[0]=a;
		coordinate[1]=b;
		return coordinate;

	}
	private void getMap(int isRed){
		for( int i=0; i<Main.xSize; i++ ){
			for( int j=0; j<Main.ySize; j++ ){
				result[i][j]=0;
			}
		}
		UnitController.redTeam.forEach((elem)->count(elem,isRed));
		UnitController.blueTeam.forEach((elem)->count(elem,isRed));
	}
	private void count(BaseAgent elem,int isRed)
	{
		if( elem.getLiteral()=='M'||elem.getLiteral()=='B'||elem.getLiteral()=='A' ){
			Military element=(Military) elem;
			for( int i=0; i<Main.xSize; i++ ){
				for( int j=0; j<Main.ySize; j++ ){
					int distance=Path.getSingle().getDistance(element.getCoordinate()[0],element.getCoordinate()[1],i,j);
					int range=element.getAttackRange()+element.getSpeed();
						distance++;
						result[i][j]=result[i][j]+(isRed)*((float)range/(float)distance);
				}
			}
		}
	}
}
