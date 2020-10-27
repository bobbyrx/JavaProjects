package bg.sofia.uni.fmi.mjt.revolut.card;

import java.time.LocalDate;

public class VirtualOneTimeCard implements Card {
    private int tries=0;
    private String number;
    private int pin;
    private LocalDate expirationDate;
    private boolean blocked=false;

    public VirtualOneTimeCard() {
        this(null,0,LocalDate.now());
    }

    public VirtualOneTimeCard(String number, int pin, LocalDate expirationDate) {
        this.number = number;
        this.pin = pin;
        this.expirationDate = expirationDate;
    }

    public String getNumber() {
        return number;
    }

    public int getPin() {
        return pin;
    }

    @Override
    public String getType() {
        return "VIRTUALONETIME";
    }

    @Override
    public LocalDate getExpirationDate() {
        return this.expirationDate;
    }

    @Override
    public boolean checkPin(int pin) {
        return this.pin==pin;
    }

    @Override
    public boolean isBlocked() {
        return this.blocked;
    }

    @Override
    public void block() {
        this.blocked=true;
    }

    @Override
    public void increaseTries() {
        if(tries<3)tries++;
        if(tries==3){
            this.block();
        }
    }

    @Override
    public void resetTries() {
        tries=0;
    }

    @Override
    public int getTries() {
        return tries;
    }
}
