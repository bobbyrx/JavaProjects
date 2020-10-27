package bg.sofia.uni.fmi.mjt.revolut.card;

import java.time.LocalDate;

public interface Card {
    String getType();
    LocalDate getExpirationDate();
    boolean checkPin(int pin);
    boolean isBlocked();
    void increaseTries();
    void resetTries();
    int getTries();
    void block();
}
