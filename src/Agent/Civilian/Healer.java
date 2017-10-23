package Agent.Civilian;

public class Healer extends Civilian {
    public Healer(String name) {
        super(15, 10,2, name);
        this.literal='H';
    }
    private int healRange=2;

    @Override
    public void activity() {

    }

    public int getHealRange() {
        return healRange;
    }
}
