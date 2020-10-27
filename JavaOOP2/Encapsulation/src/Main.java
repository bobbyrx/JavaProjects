public class Main {
    public static void main(String[] args) {
        Player player=new Player("Tim McDonald",54,"Sword");
        player.print();
        System.out.println();
        Admin admin=new Admin("Borislav",90,"Warrior");
        admin.print();
        Admin admin1=new Admin();
        admin1.print();
        admin1=admin.copy();
        admin1.setLevel(100);
        admin1.print();
        admin.print();
        admin1.changeHealthPlayer(player);
    }
}
