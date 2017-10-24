package Agent.Military;

import Agent.BaseAgent;

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
