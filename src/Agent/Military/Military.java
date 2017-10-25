package Agent.Military;

import Agent.BaseAgent;
import Util.Path;
import Util.PotentionalMap;

public class Military extends BaseAgent{
	int attackRange=1;
	private int damage=2;

	public Military(Integer health,Integer energy,Integer speed,String name,Integer damage,int isRed){
		super(health,energy,speed,name,isRed);
		this.damage=damage;
	}

	public Military(Integer health,Integer energy,Integer speed,String name,int isRed){
		super(health,energy,speed,name,isRed);
	}

	public Military(String name,int isRed){
		super(10,5,2,name,isRed);
		this.literal='M';
	}

	@Override
	public String activity(){
		StringBuilder log=new StringBuilder();
		Integer[] savePoint=PotentionalMap.getSingle().getBestCoordinate(this.getIsRed());
		int distance=Path.getSingle().getDistance(getCoordinate()[0],getCoordinate()[1],savePoint[0],savePoint[1]);
		BaseAgent enemy=Path.getSingle().getClosestEnemy(this);
		if( Path.getSingle().getDistance(this,enemy)<=getAttackRange() ){
			enemy.allocateDamage(getDamage());
			log.append(getName()).append(" атаковал:\n").append(enemy.getName()).append("\n");
			if(!enemy.getAlive()) {log.append("Цель убита\n");}
		}else if( distance>getSpeed() ){
			moveTo(savePoint[0],savePoint[1]);
		}else{
			moveTo(enemy.getCoordinate()[0],enemy.getCoordinate()[1]);
		}
		return log.toString();
	}

	public int getDamage(){
		return this.damage;
	}

	public int getAttackRange(){
		return this.attackRange;
	}

	public void setDamage(int damage){
		this.damage=damage;
	}
}
