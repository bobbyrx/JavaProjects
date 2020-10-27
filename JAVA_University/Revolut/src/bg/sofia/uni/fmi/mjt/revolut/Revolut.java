package bg.sofia.uni.fmi.mjt.revolut;

import bg.sofia.uni.fmi.mjt.revolut.account.Account;
import bg.sofia.uni.fmi.mjt.revolut.card.Card;
import bg.sofia.uni.fmi.mjt.revolut.card.VirtualOneTimeCard;

import java.time.LocalDate;
import java.util.Arrays;

public class Revolut implements RevolutAPI{
    private Account[] accounts;
    private Card[] cards;

    public Revolut(Account[] accounts, Card[] cards) {
        this.accounts= Arrays.copyOf(accounts,accounts.length);
        this.cards=Arrays.copyOf(cards,cards.length);
    }

    @Override
    public boolean pay(Card card, int pin, double amount, String currency) {
        boolean isInRevolut=false;
        for(Card newCard:this.cards){
            if(newCard.equals(card)){
                isInRevolut=true;
                break;
            }
        }
        LocalDate nowDate=LocalDate.now();
        if(card.getType().equals("PHYSICAL")&&
                !card.isBlocked()&&
                isInRevolut&&
                card.getExpirationDate().compareTo(nowDate)>=0) {

            if (card.checkPin(pin)) {
                double minAmount = Double.MAX_VALUE;
                int index = -1;
                for (Account newAccount : accounts) {

                    if (newAccount.getCurrency().equals(currency) &&
                            minAmount > newAccount.getAmount() &&
                            newAccount.getAmount() >= amount) {

                        minAmount = newAccount.getAmount();
                        index = Arrays.asList(accounts).indexOf(newAccount);
                    }

                }
                if (index==-1) return false;
                else {
                    accounts[index].setAmount(minAmount - amount);
                    cards[Arrays.asList(cards).indexOf(card)].resetTries();
                    return true;
                }
            }
            else {
                int index=Arrays.asList(cards).indexOf(card);
                if(cards[index].getTries()<=3)cards[index].increaseTries();
                return false;
            }
        }
        else {
            return false;
        }
    }

    @Override
    public boolean payOnline(Card card, int pin, double amount, String currency, String shopURL) {
        boolean isInRevolut=false;
        for(Card newCard:this.cards){
            if(newCard.equals(card)){
                isInRevolut=true;
                break;
            }
        }
        LocalDate nowDate=LocalDate.now();
        if(!shopURL.contains(".biz")&&
                !card.isBlocked()&&
                isInRevolut&&
                card.getExpirationDate().compareTo(nowDate)>=0) {

            if (card.checkPin(pin)) {
                double minAmount = Double.MAX_VALUE;
                int index = -1;
                for (Account newAccount : accounts) {

                    if (newAccount.getCurrency().equals(currency) &&
                            minAmount > newAccount.getAmount() &&
                            newAccount.getAmount() >= amount) {

                        minAmount = newAccount.getAmount();
                        index = Arrays.asList(accounts).indexOf(newAccount);
                    }

                }
                if (index==-1) return false;
                else {
                    accounts[index].setAmount(minAmount - amount);
                    int cardIndex=Arrays.asList(cards).indexOf(card);
                    cards[cardIndex].resetTries();
                    if(card instanceof VirtualOneTimeCard){
                        cards[cardIndex].block();
                    }
                    return true;
                }
            }
            else {
                int index=Arrays.asList(cards).indexOf(card);
                if(cards[index].getTries()<=3)cards[index].increaseTries();
                return false;
            }
        }
        else return false;
    }

    @Override
    public boolean addMoney(Account account, double amount) {
        boolean isInRevolut=false;
        for(Account newAccount:accounts){
            if(newAccount.equals(account)){
                isInRevolut=true;
                break;
            }
        }
        if(isInRevolut){
            int index=Arrays.asList(accounts).indexOf(account);
            accounts[index].setAmount(accounts[index].getAmount()+amount);
            return true;
        }
        else return false;
    }

    @Override
    public boolean transferMoney(Account from, Account to, double amount) {
        boolean isAccountFromInRevolut=false;
        boolean isAccountToInRevolut=false;
        for(Account newAcount:accounts){
            if(newAcount.equals(from)){
                isAccountFromInRevolut=true;
                if(isAccountToInRevolut)break;
            }
            if(newAcount.equals(to)){
                isAccountToInRevolut=true;
                if(isAccountFromInRevolut)break;
            }
        }
        final double INFLECTION= 1.95583d;
        if(isAccountFromInRevolut&&isAccountToInRevolut&&!from.getIBAN().equals(to.getIBAN())){
            double newAmount;
            if(from.getAmount()-amount<0)return false;

            if(from.getCurrency().equals(to.getCurrency())){
                from.setAmount(from.getAmount()-amount);
                to.setAmount(to.getAmount()+amount);
            }
            else if(from.getCurrency().equals("BGN")&&to.getCurrency().equals("EUR")){
                newAmount=amount/INFLECTION;
                from.setAmount(from.getAmount()-amount);
                to.setAmount(to.getAmount()+newAmount);
            }
            else{
                newAmount=amount*INFLECTION;
                from.setAmount(from.getAmount()-amount);
                to.setAmount(to.getAmount()+newAmount);
            }
            return true;
        }
        else return false;
    }

    @Override
    public double getTotalAmount() {
        final double INFLECTION= 1.95583d;
        double countAmount=0;
        for(Account newAccount:accounts){
            double newAmount=newAccount.getAmount();
            if(newAccount.getCurrency().equals("EUR")){
                newAmount*=INFLECTION;
            }
            countAmount+=newAmount;
        }
        return countAmount;
    }

}
