package Agent.Civilian;

public class Medic extends Civilian {
    public Medic(String name) {
        super(15, 10,2, name);
    }
    private int healRange=2;

    @Override
    public void activity() {

    }

    public int getHealRange() {
        return healRange;
    }
}
