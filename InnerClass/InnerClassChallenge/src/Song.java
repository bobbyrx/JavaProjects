public class Song {
    private String tittle;
    private double length;

    public Song(String tittle, double length) {
        this.tittle = tittle;
        this.length = length;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public double getLength() {
        return length;
    }

    public void setLength(double length) {
        this.length = length;
    }

    public void print(){
        System.out.println("Tittle of the song: "+this.getTittle());
        System.out.println("Length of the song: "+this.getLength());
    }

    public void copy(Song song){
        if(!song.getTittle().equals(this.getTittle())){
            this.setTittle(new String(song.getTittle()));
        }
        this.setLength(song.getLength());
    }
}
