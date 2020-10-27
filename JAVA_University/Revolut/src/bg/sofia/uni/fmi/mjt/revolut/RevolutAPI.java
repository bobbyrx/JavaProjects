package bg.sofia.uni.fmi.mjt.revolut;

import bg.sofia.uni.fmi.mjt.revolut.account.Account;
import bg.sofia.uni.fmi.mjt.revolut.card.Card;

public interface RevolutAPI {
    boolean pay(Card card, int pin, double amount, String currency);
    boolean payOnline(Card card,int pin,double amount,String currency,String shopURL);
    boolean addMoney(Account account, double amount);
    boolean transferMoney(Account from,Account to,double amount);
    double getTotalAmount();
}
