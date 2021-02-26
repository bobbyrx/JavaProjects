package bg.sofia.uni.fmi.mjt.restaurant;

public class Chef extends Thread {

    private final int id;
    private final Restaurant restaurant;
    private int countMealsCooked;

    public int chefId() {
        return this.id;
    }

    public Chef(int id, Restaurant restaurant) {
        this.id = id;
        this.restaurant = restaurant;
    }

    @Override
    public void run() {
        Order order;

        while ((order = this.restaurant.nextOrder()) != null) {
            try {
                System.out.println("Chef " + id + " is starting to cook "
                        + order.meal().getName() + " for Customer " + order.customer().getId());

                Thread.sleep(order.meal().getCookingTime());
                System.out.println("Chef " + id + " made "
                        + order.meal().getName() + " for Customer " + order.customer().getId());
                countMealsCooked++;
            } catch (InterruptedException e) {
                System.err.print("Unexpected exception was thrown: " + e.getMessage());
                e.printStackTrace();
            }
        }
    }

    /**
     * Returns the total number of meals that this chef has cooked.
     **/
    public int getTotalCookedMeals() {
        return countMealsCooked;
    }
}