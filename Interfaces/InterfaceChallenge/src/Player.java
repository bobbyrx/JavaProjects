public class Player {
    private String name;
    private int level;
    private Class playerClass;

    public Player(String name, int level, Class playerClass) {
        this.name = name;
        this.level = level;
        this.playerClass = playerClass;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Class getPlayerClass() {
        return playerClass;
    }

    public Player copyPlayer(){
        return new Player(this.getName(),this.getLevel(),this.getPlayerClass());
    }

    @Override
    public String toString() {
        return "Name -> "+this.getName()+" :: Level -> "+this.getLevel()+" :: Class -> "+this.getPlayerClass();
    }
}
