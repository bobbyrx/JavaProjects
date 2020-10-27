package bg.sofia.uni.fmi.mjt.revolut.account;

public abstract class Account {
    private double amount;
    private String IBAN;

    public Account(String IBAN){
        this(IBAN,0);
    }

    public Account(String IBAN,double amount) {
        this.amount = amount;
        this.IBAN = IBAN;
    }

    public abstract String getCurrency();

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIBAN(){
        return this.IBAN;
    }
}
