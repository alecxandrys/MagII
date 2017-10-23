package Agent;

public abstract class BaseAgent {
    private Integer health;
    private Integer energy;
    private Integer speed;
    private String name;
    private Boolean isAlive;

    protected char literal;

    private Integer xCoordinate;
    private Integer yCoordinate;

    abstract public void activity();

    protected void death() {
        this.isAlive=false;
    }

    public BaseAgent(Integer health, Integer energy, Integer speed, String name) {
        this.health = health;
        this.energy = energy;
        this.speed = speed;
        this.name = name;
        this.isAlive=true;
    }

    public Integer getEnergy() {
        return energy;
    }

    public void setEnergy(Integer energy) {
        this.energy = energy;
    }

    public Integer getHealth() {
        return health;
    }

    public void setHealth(Integer health) {
        this.health = health;
    }

    public String getName() {
        return name;
    }

    public void setCoordinate (Integer x,Integer y)
    {
        this.xCoordinate=x;
        this.yCoordinate=y;
    }
    public Integer[] getCoordinate()
    {
        Integer[] coordinate=new Integer[2];
        coordinate[0]=this.xCoordinate;
        coordinate[1]=this.yCoordinate;
        return coordinate;
    }

    public Boolean getAlive() {
        return isAlive;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public char getLiteral()
    {
    	return literal;
    }
}
