package Agent.Civilian;

public class Healer extends Civilian {
    public Healer(String name,int isRed) {
        super(15, 10,2, name,isRed);
        this.literal='H';
    }
    private int healRange=2;

    @Override
    public String activity() {
	    StringBuilder log=new StringBuilder();

	    return log.toString();
    }

    public int getHealRange() {
        return healRange;
    }
}
