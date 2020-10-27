public class DeluxeHamburger  extends Hamburger{
    private double additionalPrice=0;
    private Items[] items=new Items[2];
    private int numberOfAdditionalItems=0;
    private double price=17.99;

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

    public DeluxeHamburger(String name, String rollType, String meat) {
        super(name, rollType, meat);
        Chips chips=new Chips();
        Drinks drinks=new Drinks();
        this.addAddition(chips);
        this.addAddition(drinks);
    }


    @Override
    public void getAdditionItems() {
        System.out.println("Number of added items: "+this.getNumberOfAdditionalItems());
        for(int i=0;i<numberOfAdditionalItems;i++)
        {
            System.out.println("Name of item: "+items[i].getClass().getSimpleName());
        }
    }

    @Override
    public void getPriceOfWholeOrder() {
        System.out.println("Base burger: "+this.getPrice()+"$");
        this.getAdditionItems();
        System.out.println("The whole order: "+this.getPrice()+"$");
    }

    @Override
    public void print() {
        super.print();
    }

    @Override
    public void addAddition(Items items) {
        if (this.getNumberOfAdditionalItems() < 2) {
            additionalPrice+=items.getPrice();
            this.items[numberOfAdditionalItems]=items;
            numberOfAdditionalItems++;
        }
        else System.out.println("You can't have more additional items with this burger");
    }
}
