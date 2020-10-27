import java.util.ArrayList;

public class Album {
    private String name;
    private SongList songList;

    private static class SongList {
        private ArrayList<Song> albumList;

        public SongList(ArrayList<Song> albumList) {
            this.albumList = albumList;
        }
        public  boolean ifExist(Song newSong){
            for (Song song : albumList) {
                if (song.getTittle().equals(newSong.getTittle())) {
                    return true;
                }
            }
            return false;
        }
        public boolean addSongToList(Song newSong){
            if(!ifExist(newSong)){
                albumList.add(newSong);
                return true;
            }
            else {
                System.out.println("This song ( "+newSong.getTittle()+" ) is already in the album list!");
                return false;
            }
        }
    }
    public Album(String name, ArrayList<Song> albumList) {
        this.name = name;
        this.songList=new SongList(albumList);
    }

    public Album(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Song> getAlbumList() {
        return this.songList.albumList;
    }

    public void setAlbumList(ArrayList<Song> albumList) {
        this.songList.albumList = albumList;
    }

    public void print(){
        System.out.println("Name of the album: "+this.getName());
        for (Song song : songList.albumList) {
            song.print();
        }
    }
    public boolean addSongToAlbum(Song newSong){
        if(this.songList.addSongToList(newSong))return true;
        else return false;
    }

}
