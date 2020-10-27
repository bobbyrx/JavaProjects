public class Monster {
    private String name;
    private int level;
    private Type monsterType;

    public Monster(String name, int level, Type monsterType) {
        this.name = name;
        this.level = level;
        this.monsterType = monsterType;
    }

    public String getName() {
        return name;
    }

    public int getLevel() {
        return level;
    }

    public Type getMonsterType() {
        return monsterType;
    }

    public Monster copyMonster(){
        return new Monster(this.getName(),this.getLevel(),this.getMonsterType());
    }

    @Override
    public String toString() {
        return "Name -> "+this.getName()+" :: Level -> "+this.getLevel()+" :: Type -> "+this.getMonsterType();
    }
}
