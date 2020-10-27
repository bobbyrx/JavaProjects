import java.util.ArrayList;

public class Album {
    private String name;
    private ArrayList<Song> albumList;

    public Album(String name, ArrayList<Song> albumList) {
        this.name = name;
        this.albumList = albumList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Song> getAlbumList() {
        return albumList;
    }

    public void setAlbumList(ArrayList<Song> albumList) {
        this.albumList = albumList;
    }

    public void print(){
        System.out.println("Name of the album: "+this.getName());
        for (Song song : albumList) {
            song.print();
        }
    }
}
