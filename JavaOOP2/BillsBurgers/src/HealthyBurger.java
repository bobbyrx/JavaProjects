public class HealthyBurger extends Hamburger {
    private double additionalPrice=0;
    private Items[] items=new Items[6];
    private int numberOfAdditionalItems=0;
    private double price=14.99;

    @Override
    public double getAdditionalPrice() {
        return additionalPrice;
    }

    @Override
    public int getNumberOfAdditionalItems() {
        return numberOfAdditionalItems;
    }

    @Override
    public double getPrice() {
        return price;
    }

    public HealthyBurger(String name, String meat) {
        super(name,"Brown rye bread", meat);
    }

    @Override
    public void getAdditionItems() {
        System.out.println("Number of additional items: "+this.getNumberOfAdditionalItems());
        for(int i=0;i<numberOfAdditionalItems;i++)
        {
            System.out.println("Name of item: "+items[i].getClass().getSimpleName());
            System.out.println("Price of item: "+items[i].getPrice()+"$");
        }
    }

    @Override
    public void print() {
        super.print();
    }

    @Override
    public void addAddition(Items items) {
        if (this.getNumberOfAdditionalItems() < 6) {
            System.out.println("Adding " + items.getClass().getSimpleName() + " to the burger");
            additionalPrice+=items.getPrice();
            this.items[numberOfAdditionalItems]=items;
            numberOfAdditionalItems++;
        }
        else System.out.println("You have added enough additional items");
    }
}
