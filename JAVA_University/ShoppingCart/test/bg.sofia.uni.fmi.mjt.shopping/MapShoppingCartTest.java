package bg.sofia.uni.fmi.mjt.shopping;

import bg.sofia.uni.fmi.mjt.shopping.database.DataBase;
import bg.sofia.uni.fmi.mjt.shopping.item.Apple;
import bg.sofia.uni.fmi.mjt.shopping.item.Chocolate;
import bg.sofia.uni.fmi.mjt.shopping.item.Item;
import org.junit.Test;

import java.util.HashSet;
import java.util.Iterator;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;


public class MapShoppingCartTest {

    private final Item apple = new Apple("id_234");
    private final Item chocolate = new Chocolate("id_212");
    private final Item newChocolate = new Chocolate("id_200");
    private final ProductInfo appleInfo = new ProductInfo("Apple", "Red", 1.10);
    private final ProductInfo chocolateInfo = new ProductInfo("Chocolate", "Milk", 2.20);
    private final ProductInfo newChocolateInfo = new ProductInfo("New Chocolate", "Black", 2.70);

    @Test(expected = IllegalArgumentException.class)
    public void testIfRemoveItemIsNull() {
        MapShoppingCart newMap = new MapShoppingCart(null);
        newMap.removeItem(null);
    }

    @Test
    public void testIfRemoved() {
        MapShoppingCart newMap = new MapShoppingCart(null);
        newMap.addItem(apple);
        newMap.removeItem(apple);
        HashSet<Item> newHash = new HashSet<>(newMap.getSortedItems());
        assertFalse("Does not remove item", newHash.contains(apple));
    }

    @Test
    public void testIfRemovedOne() {
        MapShoppingCart newMap = new MapShoppingCart(null);
        newMap.addItem(apple);
        newMap.addItem(apple);
        newMap.removeItem(apple);
        assertEquals("Does not remove item", 1, newMap.getItemCount(apple));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAddItemIsNull() {
        MapShoppingCart newMap = new MapShoppingCart(null);
        newMap.addItem(null);
    }

    @Test
    public void testIfAdd() {
        MapShoppingCart newMap = new MapShoppingCart(null);
        newMap.addItem(apple);
        HashSet<Item> newHash = new HashSet<>(newMap.getSortedItems());
        assertTrue("Does not add item", newHash.contains(apple));
    }

    @Test(expected = ItemNotFoundException.class)
    public void testIfItemNotFoundWhenRemove() {
        MapShoppingCart newMap = new MapShoppingCart(null);
        newMap.removeItem(apple);
    }

    @Test
    public void testIfGetUniqueItems() {
        MapShoppingCart newMap = new MapShoppingCart(null);
        newMap.addItem(apple);
        newMap.addItem(apple);
        assertEquals("The returned collection should have unique items!",
                new HashSet<Item>(newMap.getUniqueItems()).size(),
                newMap.getUniqueItems().size());
    }

    @Test
    public void testSortedMethodHasUniqueItems() {
        MapShoppingCart newMap = new MapShoppingCart(null);
        newMap.addItem(apple);
        newMap.addItem(apple);
        assertEquals("The returned collection should have unique items!",
                new HashSet<>(newMap.getSortedItems()).size(),
                newMap.getSortedItems().size());
    }

    @Test
    public void testIfSortsCollectionOfItems() {
        MapShoppingCart newMap = new MapShoppingCart(null);
        newMap.addItem(apple);
        newMap.addItem(newChocolate);
        newMap.addItem(newChocolate);
        newMap.addItem(chocolate);
        newMap.addItem(newChocolate);
        newMap.addItem(newChocolate);
        newMap.addItem(chocolate);
        newMap.addItem(chocolate);
        newMap.addItem(apple);
        boolean isSorted = true;
        Iterator<Item> itemIterator = newMap.getSortedItems().iterator();
        if (!apple.getId().equals(itemIterator.next().getId())) {
            isSorted = false;
        }
        if (!chocolate.getId().equals(itemIterator.next().getId())) {
            isSorted = false;
        }
        if (!newChocolate.getId().equals(itemIterator.next().getId())) {
            isSorted = false;
        }
        assertTrue("It is not sorted by number of items!", isSorted);
    }

    @Test
    public void testIfGetTotal() {
        DataBase dataBase = new DataBase();
        dataBase.addInfo("id_234", appleInfo);
        dataBase.addInfo("id_212", chocolateInfo);
        dataBase.addInfo("id_200", newChocolateInfo);
        MapShoppingCart newMap = new MapShoppingCart(dataBase);
        newMap.addItem(apple);
        newMap.addItem(apple);
        newMap.addItem(chocolate);
        newMap.addItem(newChocolate);
        newMap.addItem(newChocolate);
        assertEquals("There is a problem with getTotal method in ListShoppingCart",
                9.80, newMap.getTotal(), 0);
    }
}
