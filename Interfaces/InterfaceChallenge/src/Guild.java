import java.util.ArrayList;

public class Guild implements ISave<Player,Guild> {
    private ArrayList<Player>playersOfGuild;

    public Guild(ArrayList<Player> playersOfGuild) {
        this.playersOfGuild = playersOfGuild;
    }

    @Override
    public void copyWholeFile(Guild guild) {
        if(!guild.getFromFile().isEmpty())guild.getFromFile().clear();
        for(int i=0;i<this.getFromFile().size();i++){
            guild.getFromFile().add(this.getFromFile().get(i).copyPlayer());
        }
        System.out.println("Coping guild complete!");
    }

    public Guild() {
        playersOfGuild=new ArrayList<Player>();
    }

    @Override
    public void printScreen() {
        System.out.println("Printing information in guild:");
        if(playersOfGuild.isEmpty()){
            System.out.println("Empty!");
            return;
        }
        System.out.println("#############################");
        for(int i=0;i<playersOfGuild.size();i++){
            System.out.println((i+1)+". "+playersOfGuild.get(i).toString());
        }
        System.out.println("#############################");
    }

    @Override
    public ArrayList<Player> getFromFile() {
      return playersOfGuild;
    }

    @Override
    public void copyFromFile(Player player) {
        playersOfGuild.add(player);
    }
}
