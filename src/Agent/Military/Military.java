package Agent.Military;

import Agent.BaseAgent;
import FX.UnitController;
import Util.Path;
import Util.PotentionalMap;

public class Military extends BaseAgent {
    int attackRange=1;
    private int damage=2;
    public Military(Integer health, Integer energy,  Integer speed, String name, Integer damage,int isRed) {
        super(health, energy, speed, name, isRed);
        this.damage=damage;
    }
    public Military(Integer health, Integer energy,  Integer speed, String name,int isRed) {
        super(health, energy, speed, name, isRed);
    }
    public Military(String name,int isRed) {
        super(20, 5, 2, name, isRed);
        this.literal='M';
    }
    @Override
    public String activity() {
	    StringBuilder log=new StringBuilder();
	    Integer[] target=PotentionalMap.getSingle().getBestCoordinate(this.getIsRed());
	    int distance=Path.getSingle().getDistance(getCoordinate()[0],getCoordinate()[1],target[0],target[1]);
	    if (distance>getSpeed())
	    {
			moveTo(target[0],target[1]);
	    }
	    else
	    {
			int index=Path.getSingle().getClosestEnemy(this);
			BaseAgent enemy;
				if (getIsRed()==1){enemy=UnitController.blueTeam.get(index);}
				else{enemy=UnitController.redTeam.get(index);}
				moveTo(enemy.getCoordinate()[0],enemy.getCoordinate()[1]);
				enemy.allocateDamage(getDamage());
				log.append(getName()).append(" атаковал:\n").append(enemy.getName()).append("\n");
	    }
	    return log.toString();
    }

    public int getDamage()
    {
        return this.damage;
    }
    public int getAttackRange()
    {
        return this.attackRange;
    }
    public void setDamage(int damage)
    {
        this.damage=damage;
    }
}
