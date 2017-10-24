package Agent.Military;

public class Archer extends Military {
    public Archer(String name,int isRed) {
        super(20, 5,2, name,isRed);
        this.attackRange=4;//change attack range
	    this.literal='A';
    }

}
