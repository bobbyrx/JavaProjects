import com.sun.security.jgss.GSSUtil;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.Scanner;

public class Main {
    public static Scanner scanner=new Scanner(System.in);
    public static ArrayList<Album>albums=new ArrayList<Album>();
    public static LinkedList<Song> playList=new LinkedList<Song>();
    public static ListIterator<Song> listIterator;
    public static boolean isForward;
    public static void main(String[] args) {
        ArrayList<Song>rock=new ArrayList<Song>();
        ArrayList<Song>chalga=new ArrayList<Song>();
        ArrayList<Song>asianMusic=new ArrayList<Song>();

        chalga.add(new Song("POP FOLK",4.12));
        chalga.add(new Song("POP",3.59));

        rock.add(new Song("Metal",12.30));
        rock.add(new Song("Classic rock",6.32));

        asianMusic.add(new Song("K-POP",2.37));
        asianMusic.add(new Song("Anime opening",1.29));

        albums.add(new Album("MyPOP",chalga));
        albums.add(new Album("MyRock",rock));
        albums.add(new Album("MyJam",asianMusic));

        boolean isQuit=false;

        while(!isQuit){
            System.out.print("Input your option: ");
            int option=scanner.nextInt();
            scanner.nextLine();
            switch (option){
                case 0:
                    printMenu();
                    break;
                case 1:
                    skipForward();
                    break;
                case 2:
                    skipBackward();
                    break;
                case 3:
                    replayCurrentSong();
                    break;
                case 4:
                    removeCurrentSong();
                    break;
                case 5:
                    addToPlayList();
                    break;
                case 6:
                    printPlayList();
                    break;
                case 7:
                    printAlbums();
                    break;
                case 8:
                    if(askToQuit().toLowerCase().equals("yes")) {
                        isQuit = true;
                        System.out.println("Exiting!");
                    }
                        break;
                default:
                    System.out.println("Invalid number!");
                    break;
            }
        }
        scanner.close();
    }
    public static String askToQuit(){
        System.out.print("Do you want to exit?:(yes/no) ");
        return scanner.nextLine();
    }
    public static void addToPlayList() {
        System.out.print("Input new song: ");
        String nameOfSong=scanner.nextLine();
        Song song=new Song("",-1);
        boolean isInAnyAlbum=false;
        for (Album album : albums) {
            for (int i = 0; i < album.getAlbumList().size(); i++) {
                String name = new String(album.getAlbumList().get(i).getTittle());
                if (nameOfSong.toLowerCase().equals(name.toLowerCase())) {
                    isInAnyAlbum = true;
                    song.copy(album.getAlbumList().get(i));
                    break;
                }
            }
            if (isInAnyAlbum) break;
        }
        if(!isInAnyAlbum) {
            System.out.println("There is no song with name ("+nameOfSong+") in your Albums!");
            return;
        }
        playList.add(song);
        System.out.println("New song ("+nameOfSong+") added!");
        listIterator=playList.listIterator();
        isForward=false;
    }

    public static void printPlayList(){
        System.out.println("Player music list:");
        if(playList.isEmpty()){
            System.out.println("Empty!");
            return;
        }
        for (Song song : playList) {
            song.print();
        }
    }

    public static void printAlbums(){
        System.out.println("Printing available albums:");
        for (Album album : albums) {
            album.print();
            System.out.println("######################");
        }
    }

    public static void skipForward(){
        if(playList.isEmpty()){
            System.out.println("No songs in play list to skip forward!");
            return;
        }
        if(!isForward){
            if (listIterator.hasNext()) {
                listIterator.next();
            }
        }
        if(listIterator.hasNext()){
            System.out.println("Now playing:");
            listIterator.next().print();
        }
        else System.out.println("End of the play list!");
        if(!isForward)isForward=true;
    }

    public static void skipBackward(){
        if(playList.isEmpty()){
            System.out.println("No songs in play list to skip backward!");
            return;
        }
        if(isForward){
            if (listIterator.hasPrevious()) {
                listIterator.previous();
            }
        }
        if(listIterator.hasPrevious()){
            System.out.println("Now playing:");
            listIterator.previous().print();
        }
        else System.out.println("Beginning of the play list!");
        if(isForward)isForward=false;
    }

    public static void replayCurrentSong(){
        if(playList.isEmpty()){
            System.out.println("No song to replay!");
            return;
        }
        System.out.println("Replaying:");
        if(isForward) {
            listIterator.previous();
            listIterator.next().print();
        }
        else {
            listIterator.next();
            listIterator.previous().print();
        }
    }

    public static void removeCurrentSong(){
        if(playList.isEmpty()){
            System.out.println("No songs to remove!");
            return;
        }
        System.out.println("Removing current song!");
        if(!isForward){
            listIterator.next();
        }
        else{
            listIterator.previous();
        }
        listIterator.remove();
    }

    public static void printMenu(){
        System.out.println("0 -> Print menu");
        System.out.println("1 -> Skip forward");
        System.out.println("2 -> Skip backward");
        System.out.println("3 -> Replay song");
        System.out.println("4 -> Remove current song");
        System.out.println("5 -> Add existing song from your albums");
        System.out.println("6 -> Show your play list");
        System.out.println("7 -> Show your albums");
        System.out.println("8 -> Quit");
    }
}
