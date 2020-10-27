import java.util.ArrayList;

public interface ISave<Genetic,Gen> {
    ArrayList<Genetic> getFromFile();
    void copyWholeFile(Gen gen);
    void copyFromFile(Genetic genetic);
    void printScreen();
}
