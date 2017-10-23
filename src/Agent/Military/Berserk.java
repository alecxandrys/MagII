package Agent.Military;

public class Berserk extends Military {
    public Berserk(String name) {
        super(20, 10,3, name,4);
    }

    @Override
    protected void death() {
        super.death();

    }
}
