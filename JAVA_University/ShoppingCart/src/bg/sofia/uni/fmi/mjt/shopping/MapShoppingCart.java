package bg.sofia.uni.fmi.mjt.shopping;

import bg.sofia.uni.fmi.mjt.shopping.item.Item;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class MapShoppingCart implements ShoppingCart {

    private final Map<Item, Integer> items;
    private final ProductCatalog catalog;

    public MapShoppingCart(ProductCatalog catalog) {
        this.catalog = catalog;
        this.items = new LinkedHashMap<>();
    }

    public int getItemCount(Item item) {
        return items.get(item);
    }

    public Collection<Item> getUniqueItems() {
        List<Item> i = new ArrayList<>();
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            i.add(entry.getKey());
        }
        return i;
    }

    @Override
    public void addItem(Item item) {
        if (item != null) {
            if (items.get(item) != null) {
                items.put(item, items.get(item) + 1);
            } else {
                items.put(item, 1);
            }
        } else {
            throw new IllegalArgumentException("Item is null!");
        }
    }

    @Override
    public void removeItem(Item item) {
        if (item == null) {
            throw new IllegalArgumentException("Item is null!");
        }
        if (!items.containsKey(item)) {
            throw new ItemNotFoundException("Item not found!");
        }

        Integer occurrences = items.get(item);
        if (occurrences - 1 == 0) {
            items.remove(item);
        } else {
            items.put(item, occurrences - 1);
        }
    }

    @Override
    public double getTotal() {
        double total = 0;
        for (Map.Entry<Item, Integer> entry : items.entrySet()) {
            ProductInfo info = catalog.getProductInfo(entry.getKey().getId());
            total += info.price() * entry.getValue();
        }
        return total;
    }

    @Override
    public Collection<Item> getSortedItems() {
        List<Item> sortedItems = new ArrayList<>(items.keySet());
        sortedItems.sort(new Comparator<Item>() {
            @Override
            public int compare(Item item1, Item item2) {
                return items.get(item1).compareTo(items.get(item2));
            }
        });
        return sortedItems;
    }
}
