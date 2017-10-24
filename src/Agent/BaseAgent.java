package Agent;

import Util.Map;
import Util.Path;

import java.util.ArrayList;

public abstract class BaseAgent {
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

    protected void death() {
        this.isAlive=false;
    }

    public BaseAgent(Integer health,Integer energy,Integer speed,String name,int isRed) {
        this.health = health;
        this.energy = energy;
        this.speed = speed;
        this.name = name;
        this.isRed=isRed;
        this.isAlive=true;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setCoordinate (Integer x,Integer y)
    {
        this.xCoordinate=x;
        this.yCoordinate=y;
    }
    public Integer[] getCoordinate()
    {
        Integer[] coordinate=new Integer[2];
        coordinate[0]=this.xCoordinate;
        coordinate[1]=this.yCoordinate;
        return coordinate;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public char getLiteral()
    {
    	return literal;
    }
    protected Boolean moveTo(int targetX,int targetY)
    {
	    ArrayList<int[]> path=Path.getSingle().getPath(xCoordinate,yCoordinate,targetX,targetY);
		int curPos=this.speed-1;//скорость с 1, индекс с 0
		while (curPos>=0)
		{
			if (!Map.placed[path.get(curPos)[0]][path.get(curPos)[1]])
			{
				changeState(path.get(curPos)[0],path.get(curPos)[1]);
				return true;
			}
		}
		return false;
    }
    private void changeState(int targetX,int targetY)
    {
	    Map.placed[xCoordinate][yCoordinate]=false;
	    Map.placed[targetX][targetY]=true;
	    setCoordinate(targetX,targetY);
    }

	public int getIsRed(){
		return isRed;
	}
}
