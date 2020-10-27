import java.util.ArrayList;

public class Dungeon implements ISave<Monster,Dungeon> {
    private ArrayList<Monster> monstersInDungeon;

    public Dungeon() {
        monstersInDungeon=new ArrayList<Monster>();
    }

    @Override
    public void copyWholeFile(Dungeon dungeon) {
        if(!dungeon.getFromFile().isEmpty())dungeon.getFromFile().clear();
        for(int i=0;i<this.getFromFile().size();i++){
            dungeon.getFromFile().add(this.getFromFile().get(i).copyMonster());
        }
        System.out.println("Coping dungeon complete!");
    }

    @Override
    public void printScreen() {
        System.out.println("Printing information in dungeon:");
        if(monstersInDungeon.isEmpty()){
            System.out.println("Empty!");
            return;
        }
        System.out.println("#############################");
        for(int i=0;i<monstersInDungeon.size();i++){
            System.out.println((i+1)+". "+monstersInDungeon.get(i).toString());
        }
        System.out.println("#############################");
    }

    @Override
    public ArrayList<Monster> getFromFile() {
        return monstersInDungeon;
    }

    @Override
    public void copyFromFile(Monster monster) {
        this.monstersInDungeon.add(monster);
    }
}
