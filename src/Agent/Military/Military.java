package Agent.Military;

import Agent.BaseAgent;

public class Military extends BaseAgent {
    int attackRange=1;
    private int damage=2;
    public Military(Integer health, Integer energy,  Integer speed, String name, Integer damage) {
        super(health, energy, speed, name);
        this.damage=damage;
    }
    public Military(Integer health, Integer energy,  Integer speed, String name) {
        super(health, energy, speed, name);
    }
    public Military(String name) {
        super(20, 5, 2, name);
    }
    @Override
    public void activity() {

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
