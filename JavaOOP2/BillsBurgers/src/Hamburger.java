public class Hamburger {
    private String name;
    private String rollType;
    private String meat;
    private Items[] items=new Items[4];
    private int numberOfAdditionalItems=0;
    private double price=9.99;
    private double additionalPrice=0;

    public double getAdditionalPrice() {
        return additionalPrice;
    }

    public Hamburger(String name, String rollType, String meat) {
        this.name = name;
        this.rollType = rollType;
        this.meat = meat;
    }

    public String getName() {
        return name;
    }

    public String getRollType() {
        return rollType;
    }

    public String getMeat() {
        return meat;
    }

    public void getAdditionItems(){
        System.out.println("Number of additional items: "+this.getNumberOfAdditionalItems());
        for(int i=0;i<numberOfAdditionalItems;i++)
        {
            System.out.println("Name of item: "+items[i].getClass().getSimpleName());
            System.out.println("Price of item: "+items[i].getPrice()+"$");
        }
    }

    public int getNumberOfAdditionalItems() {
        return numberOfAdditionalItems;
    }

    public double getPrice() {
        return price;
    }

    public void addAddition(Items items) {
        if (this.getNumberOfAdditionalItems() < 4) {
            System.out.println("Adding " + items.getClass().getSimpleName() + " to the burger");
            additionalPrice+=items.getPrice();
            this.items[numberOfAdditionalItems]=items;
            numberOfAdditionalItems++;
        }
        else System.out.println("You have added enough additional items");
    }

    public void getPriceOfWholeOrder(){
        System.out.println("Base burger: "+this.getPrice()+"$");
        this.getAdditionItems();
        int money=(int)((this.getPrice()+this.getAdditionalPrice())*100);
        System.out.println("The whole order: "+((double)money)/100+"$");
    }
    public void print(){
        System.out.println();
        System.out.println("Printing the whole information:");
        System.out.println("Name of the burger: "+this.getName());
        System.out.println("Type of roll: "+this.getRollType());
        System.out.println("Type of meat: "+this.getMeat());
        this.getPriceOfWholeOrder();
        System.out.println();
    }

}
