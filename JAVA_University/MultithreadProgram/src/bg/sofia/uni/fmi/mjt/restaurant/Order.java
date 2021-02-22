package bg.sofia.uni.fmi.mjt.restaurant;

import bg.sofia.uni.fmi.mjt.restaurant.customer.AbstractCustomer;

public record Order(Meal meal, AbstractCustomer customer) {
}
