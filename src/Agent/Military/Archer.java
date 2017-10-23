package Agent.Military;

public class Archer extends Military {
    public Archer(String name) {
        super(20, 5,2, name);
        this.attackRange=4;//change attack range
	    this.literal='A';
    }

}
