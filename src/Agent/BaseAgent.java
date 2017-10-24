package Agent;

import FX.UnitController;
import Util.Map;
import Util.Path;

import java.util.ArrayList;

public abstract class BaseAgent{
	private Integer health;
	private Integer energy;
	private Integer speed;
	private String name;
	private Boolean isAlive;

	private int isRed;

	protected char literal;

	private Integer xCoordinate;
	private Integer yCoordinate;

	abstract public String activity();

	protected void death(){
		this.isAlive=false;
		if( getIsRed()==1 ){UnitController.redTeam.remove(this);}
		else {UnitController.blueTeam.remove(this);}
	}

	public BaseAgent(Integer health,Integer energy,Integer speed,String name,int isRed){
		this.health=health;
		this.energy=energy;
		this.speed=speed;
		this.name=name;
		this.isRed=isRed;
		this.isAlive=true;
	}

	public Integer getEnergy(){
		return energy;
	}

	public void setEnergy(Integer energy){
		this.energy=energy;
	}

	public void allocateDamage(int damage){
		this.health-=damage;
		if( this.health<=0 ){
			death();
		}
	}

	public String getName(){
		return name;
	}

	public void setCoordinate(Integer x,Integer y){
		this.xCoordinate=x;
		this.yCoordinate=y;
	}

	public Integer[] getCoordinate(){
		Integer[] coordinate=new Integer[2];
		coordinate[0]=this.xCoordinate;
		coordinate[1]=this.yCoordinate;
		return coordinate;
	}

	public Boolean getAlive(){
		return isAlive;
	}

	public Integer getSpeed(){
		return speed;
	}

	public void setSpeed(Integer speed){
		this.speed=speed;
	}

	public char getLiteral(){
		return literal;
	}

	protected Boolean moveTo(int targetX,int targetY){
		ArrayList< int[] > path=Path.getSingle().getPath(xCoordinate,yCoordinate,targetX,targetY);
		int curPos;
		if (path.size()>this.speed)
		{
			curPos=this.speed-1;//скорость с 1, индекс с 0
		}
		else if (path.size()==0) {return false;}
		else {curPos=path.size()-1;}
		while( curPos>=0 ){
			if( !Map.placed[path.get(curPos)[0]][path.get(curPos)[1]] ){
				changeState(path.get(curPos)[0],path.get(curPos)[1]);
				return true;
			}
			curPos--;
		}
		return false;
	}

	private void changeState(int targetX,int targetY){
		Map.placed[xCoordinate][yCoordinate]=false;
		Map.placed[targetX][targetY]=true;
		setCoordinate(targetX,targetY);
	}

	public int getIsRed(){
		return isRed;
	}
}
