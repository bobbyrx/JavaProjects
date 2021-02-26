package bg.sofia.uni.fmi.mjt.restaurant;

import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class MJTDiningPlace implements Restaurant {

    private List<Order> notYetDoneOrders;
    private int countOrders;
    private final Chef[] chefsInRestaurant;
    private boolean isClosed;

    public MJTDiningPlace(int numberOfChefs) throws IllegalArgumentException {
        if (numberOfChefs <= 0) {
            throw new IllegalArgumentException("Should be a positive number!");
        }
        notYetDoneOrders = new LinkedList<>();
        chefsInRestaurant = new Chef[numberOfChefs];
        for (int i = 0; i < numberOfChefs; i++) {
            chefsInRestaurant[i] = new Chef(i, this);
        }
        for (int i = 0; i < numberOfChefs; ++i) {
            chefsInRestaurant[i].start();
        }
    }

    @Override
    public synchronized void submitOrder(Order order) {
        if (!isClosed) {

            notYetDoneOrders.add(order);
            countOrders++;

            Comparator<Order> compareByTimeToCook =
                    (Order p1, Order p2) -> compareTo(p2.meal().getCookingTime(), p1.meal().getCookingTime());

            Comparator<Order> compareByVip =
                    (Order p1, Order p2) -> compareTo(p2.customer().hasVipCard(), p1.customer().hasVipCard());

            Comparator<Order> compare =
                    compareByVip.thenComparing(compareByTimeToCook);

            notYetDoneOrders = notYetDoneOrders.stream()
                    .sorted(compare).collect(Collectors.toList());

            System.out.println("Customer " + order.customer().getId() + " orders " + order.meal().getName());

            this.notifyAll();

        } else {
            System.out.println("Sorry, the restaurant is closing sir!");
        }
    }

    public int compareTo(int num1, int num2) {
        return num1 - num2;
    }

    public int compareTo(boolean bool1, boolean bool2) {
        int num1;
        int num2;
        if (bool1) {
            num1 = 1;
        } else {
            num1 = 0;
        }
        if (bool2) {
            num2 = 1;
        } else {
            num2 = 0;
        }
        return num1 - num2;
    }

    @Override
    public synchronized Order nextOrder() {
        while (notYetDoneOrders.isEmpty() && !isClosed) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        if (notYetDoneOrders.isEmpty()) {
            return null;
        } else {
            Order order = notYetDoneOrders.get(0);
            notYetDoneOrders.remove(0);
            return order;
        }
    }

    @Override
    public int getOrdersCount() {
        return countOrders;
    }

    @Override
    public Chef[] getChefs() {
        return chefsInRestaurant;
    }

    @Override
    public synchronized void close() {
        this.notifyAll();
        isClosed = true;
    }

}
