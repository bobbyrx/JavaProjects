import javax.swing.*;
import java.util.Scanner;

public class Main {
    public static Scanner scanner=new Scanner(System.in);
    public static ISave guild=new Guild();
    public static ISave dungeon=new Dungeon();
    public static void main(String[] args) {
        ISave guildNew=new Guild();
        ISave dungeonNew=new Dungeon();
        Menu(guildNew,dungeonNew);
        scanner.close();
    }
    public static void Menu(ISave guildNew,ISave dungeonNew){
        boolean ifExit=false;
        while(!ifExit){
            System.out.print("Input your option: ");
            int option=scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 0:
                    printMenu();
                    break;
                case 1:
                    readGuild();
                    break;
                case 2:
                    readDungeon();
                    break;
                case 3:
                    guild.copyWholeFile(guildNew);
                    break;
                case 4:
                    dungeon.copyWholeFile(dungeonNew);
                    break;
                case 5:
                    printSecondMenu();
                    System.out.print("Input your second option: ");
                    int secondOptionGuild=scanner.nextInt();
                    scanner.nextLine();
                    switch (secondOptionGuild){
                        case 1:
                            printGuildOrDungeon(guildNew);
                            break;
                        case 2:
                            printGuildOrDungeon(guild);
                            break;
                        default:
                            System.out.println("Wrong second input!");
                            System.out.println("Canceling action option 5!");
                            break;
                    }
                    break;
                case 6:
                    printSecondMenu();
                    System.out.print("Input your second option: ");
                    int secondOptionDungeon=scanner.nextInt();
                    scanner.nextLine();
                    switch (secondOptionDungeon){
                        case 1:
                            printGuildOrDungeon(dungeonNew);
                            break;
                        case 2:
                            printGuildOrDungeon(dungeon);
                            break;
                        default:
                            System.out.println("Wrong second input!");
                            System.out.println("Canceling option 6!");
                            break;
                    }
                    break;
                case 7:
                    System.out.print("Do you want to exit?(yes/no): ");
                    String answer=scanner.nextLine();
                    if(answer.toLowerCase().equals("yes")){
                        ifExit=true;
                        System.out.println("Exiting!");
                    }
                    break;
                default:
                    System.out.println("Wrong input!");
            }
        }
    }
    public static void printSecondMenu(){
        System.out.println("1 -> New place");
        System.out.println("2 -> Old place");
    }
    public static void printMenu(){
        System.out.println("0 -> Print menu");
        System.out.println("1 -> Add to guild");
        System.out.println("2 -> Add to dungeon");
        System.out.println("3 -> Copy information to new guild");
        System.out.println("4 -> Copy information to new dungeon");
        System.out.println("5 -> Print on screen guild");
        System.out.println("6 -> Print on screen dungeon");
        System.out.println("7 -> Exit");
    }
    public static void printGuildOrDungeon(ISave file){
        file.printScreen();
    }
    public static void readGuild(){
        System.out.print("Input player name: ");
        String name=scanner.nextLine();
        System.out.print("Input what level is the player: ");
        int level=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Input what class is the player: ");
        String playerClassString=scanner.nextLine();
        Class playerClassEnum;
        switch (playerClassString.toLowerCase()){
            case "mage":
                playerClassEnum=Class.Mage;
                break;
            case "warrior":
                playerClassEnum=Class.Warrior;
                break;
            case "hunter":
                playerClassEnum=Class.Hunter;
                break;
            case "druid" :
                playerClassEnum=Class.Druid;
                break;
            case "warlock":
                playerClassEnum=Class.Warlock;
                break;
            case "paladin":
                playerClassEnum=Class.Paladin;
                break;
            case "shaman":
                playerClassEnum=Class.Shaman;
                break;
            case "rogue":
                playerClassEnum=Class.Rouge;
                break;
            case "priest":
                playerClassEnum=Class.Priest;
                break;
            default:
                playerClassEnum=Class.Default;
                break;
        }
        guild.copyFromFile(new Player(name,level,playerClassEnum));
        System.out.println("Added new player in guild!");
    }
    public static void readDungeon(){
        System.out.print("Input monster name: ");
        String name=scanner.nextLine();
        System.out.print("Input what level is the monster: ");
        int level=scanner.nextInt();
        scanner.nextLine();
        System.out.print("Input what type is the monster: ");
        String monsterTypeString=scanner.nextLine();
        Type monsterTypeEnum;
        switch (monsterTypeString.toLowerCase()){
            case "undead":
                monsterTypeEnum=Type.Undead;
                break;
            case "demon":
                monsterTypeEnum=Type.Demon;
                break;
            case "beast":
                monsterTypeEnum=Type.Beast;
                break;
            default:
                monsterTypeEnum=Type.None;
                break;
        }
        dungeon.copyFromFile(new Monster(name,level,monsterTypeEnum));
        System.out.println("Added new monster in dungeon!");
    }

}
