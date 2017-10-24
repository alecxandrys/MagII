package Agent.Civilian;

import Agent.BaseAgent;
import Agent.Military.Archer;
import Agent.Military.Berserk;
import Agent.Military.Military;
import FX.UnitController;
import Util.Index;
import Util.Map;
import Util.PotentionalMap;

import java.util.Objects;

public class Civilian extends BaseAgent{

	public Civilian(String name,int isRed){
		super(10,5,2,name,isRed);
		this.literal='C';
	}

	public Civilian(Integer health,Integer energy,Integer speed,String name,int isRed){
		super(health,energy,speed,name,isRed);
	}

	private String grow(){
		StringBuilder log=new StringBuilder("");
		int type=(int) (Math.random()*5);
		BaseAgent newbi=null;
		switch( type ){
			case 0:{
				log.append("Blue Civilian #").append(Index.bt0);
				newbi=new Civilian(log.toString(),getIsRed());
				Index.bt0++;
				break;
			}
			case 1:{
				log.append("Blue Healer #").append(Index.bt1);
				newbi=new Healer(log.toString(),getIsRed());
				Index.bt1++;
				break;
			}
			case 2:{
				log.append("Blue Military #").append(Index.bt2);
				newbi=new Military(log.toString(),getIsRed());
				Index.bt2++;
				break;
			}
			case 3:{
				log.append("Blue Berserk #").append(Index.bt3);
				newbi=new Berserk(log.toString(),getIsRed());
				Index.bt3++;
				break;
			}
			case 4:{
				log.append("Blue Archer #").append(Index.bt4);
				newbi=new Archer(log.toString(),getIsRed());
				Index.bt4++;
				break;
			}
			default:{break;}
		}
		for( int i=0; i<Map.shifts.length; i++ ){
			if (!Map.placed[getCoordinate()[0]+Map.shifts[i][0]][getCoordinate()[1]+Map.shifts[i][1]])
			{
				newbi.setCoordinate(getCoordinate()[0]+Map.shifts[i][0],getCoordinate()[1]+Map.shifts[i][1]);
				Map.placed[getCoordinate()[0]+Map.shifts[i][0]][getCoordinate()[1]+Map.shifts[i][1]]=true;
				if (getIsRed()==1)
				{
					UnitController.redTeam.add(newbi);
				}
				else
				{
					UnitController.blueTeam.add(newbi);
				}
				setEnergy(getEnergy()-10);
				return log.toString();
			}
		}
		return "";
	}

	@Override
	public String activity(){
		StringBuilder log=new StringBuilder();
		Integer[] target=PotentionalMap.getSingle().getBestCoordinate(this.getIsRed());
		Boolean move=this.moveTo(target[0],target[1]);
		setEnergy(getEnergy()+1);
		if( !move ){setEnergy(getEnergy()+2);}
		if( getEnergy()>=10 ){
			String result=grow();
			if( !Objects.equals(result,"") ){
				log.append(getName()).append(" порождает нового участника ").append(result).append("\n");
			}
		}
		return log.toString();
	}

}
