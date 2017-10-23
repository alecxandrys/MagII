package Agent.Military;

public class Berserk extends Military {
    public Berserk(String name) {
        super(20, 10,3, name,4);
        this.literal='B';
    }

    @Override
    protected void death() {
        super.death();

    }
}
