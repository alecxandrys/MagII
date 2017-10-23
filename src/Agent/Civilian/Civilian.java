package Agent.Civilian;

import Agent.BaseAgent;

public class Civilian extends BaseAgent {

    public Civilian(String name) {
        super(10, 5, 2, name);
    }

    public Civilian(Integer health,Integer energy,Integer speed,String name) {
        super(health, energy, speed, name);
    }

    private void grow() {

    }

    @Override
    public void activity() {

    }

}
