package bg.sofia.uni.fmi.mjt.restaurant;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

public class MJTDiningPlace implements Restaurant {

    private AtomicInteger idOfChefs = new AtomicInteger(0);

    private List<Order> notYetDoneOrders;
    private int countOrders;
    private final List<Chef> chefsInRestaurant;
    private AtomicBoolean isClosed = new AtomicBoolean(false);

    public MJTDiningPlace(int numberOfChefs) throws IllegalArgumentException {
        if (numberOfChefs <= 0) {
            throw new IllegalArgumentException("Should be a positive number!");
        }
        chefsInRestaurant = new ArrayList<>();
        for (int i = 0; i < numberOfChefs; i++) {
            chefsInRestaurant.add(new Chef(idOfChefs.getAndIncrement(), this));
        }
        for (Chef newChef : chefsInRestaurant) {
            newChef.start();
        }
        notYetDoneOrders = new LinkedList<>();
    }

    @Override
    public void submitOrder(Order order){
        if (!isClosed.get()) {
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
    public Order nextOrder() {
        try {
            if (notYetDoneOrders.size()!=0) {
                Order newOrder =
                        new Order(notYetDoneOrders.get(0).meal(), notYetDoneOrders.get(0).customer());
                notYetDoneOrders.remove(0);
                countOrders--;
                return newOrder;
            }
        } catch (NullPointerException ignored) {
            return null;
        }
        return null;
    }

    @Override
    public int getOrdersCount() {
        return countOrders;
    }

    @Override
    public Chef[] getChefs() {
        Chef[] listOfChefs = new Chef[chefsInRestaurant.size()];
        for (int i = 0; i < chefsInRestaurant.size(); i++) {
            listOfChefs[i] = chefsInRestaurant.get(i);
        }
        return listOfChefs;
    }

    @Override
    public void close() {
        synchronized (this) {
            isClosed.set(true);
        }
        for (Chef newChef : chefsInRestaurant) {
            try {
               //synchronized (this){
               //    this.notifyAll();
               //}
                newChef.terminate();
                newChef.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

}
