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


public class ListShoppingCartTest {

    private final Item apple = new Apple("id_234");
    private final Item chocolate = new Chocolate("id_212");
    private final Item newChocolate = new Chocolate("id_200");
    private final ProductInfo appleInfo = new ProductInfo("Apple", "Red", 1.10);
    private final ProductInfo chocolateInfo = new ProductInfo("Chocolate", "Milk", 2.20);
    private final ProductInfo newChocolateInfo = new ProductInfo("New Chocolate", "Black", 2.70);

    @Test(expected = IllegalArgumentException.class)
    public void testIfRemoveItemIsNull() {
        ListShoppingCart newList = new ListShoppingCart(null);
        newList.removeItem(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIfAddItemIsNull() {
        ListShoppingCart newList = new ListShoppingCart(null);
        newList.addItem(null);
    }

    @Test
    public void testIfAddItem() {
        ListShoppingCart newList = new ListShoppingCart(null);
        newList.addItem(apple);
        HashSet<Item> newHash = new HashSet<>(newList.getSortedItems());
        assertTrue("Does not add item", newHash.contains(apple));
    }

    @Test
    public void testIfRemoved() {
        ListShoppingCart newList = new ListShoppingCart(null);
        newList.addItem(apple);
        newList.removeItem(apple);
        HashSet<Item> newHash = new HashSet<>(newList.getSortedItems());
        assertFalse("Does not remove item", newHash.contains(apple));
    }

    @Test(expected = ItemNotFoundException.class)
    public void testIfItemNotFoundWhenRemove() {
        ListShoppingCart newList = new ListShoppingCart(null);
        newList.removeItem(apple);
    }

    @Test
    public void testIfGetUniqueItems() {
        ListShoppingCart newList = new ListShoppingCart(null);
        newList.addItem(apple);
        newList.addItem(apple);
        assertEquals("The returned collection should have unique items!",
                new HashSet<Item>(newList.getUniqueItems()).size(),
                newList.getUniqueItems().size());
    }

    @Test
    public void testSortedMethodHasUniqueItems() {
        ListShoppingCart newList = new ListShoppingCart(null);
        newList.addItem(apple);
        newList.addItem(apple);
        assertEquals("The returned collection should have unique items!",
                new HashSet<>(newList.getSortedItems()).size(),
                newList.getSortedItems().size());
    }

    @Test
    public void testIfSortsCollectionOfItems() {
        ListShoppingCart newList = new ListShoppingCart(null);
        newList.addItem(apple);
        newList.addItem(newChocolate);
        newList.addItem(newChocolate);
        newList.addItem(chocolate);
        newList.addItem(newChocolate);
        newList.addItem(newChocolate);
        newList.addItem(chocolate);
        newList.addItem(chocolate);
        newList.addItem(apple);
        boolean isSorted = true;
        Iterator<Item> itemIterator = newList.getSortedItems().iterator();
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
        ListShoppingCart newList = new ListShoppingCart(dataBase);
        newList.addItem(apple);
        newList.addItem(apple);
        newList.addItem(chocolate);
        newList.addItem(newChocolate);
        newList.addItem(newChocolate);
        assertEquals("There is a problem with getTotal method in ListShoppingCart",
                9.80, newList.getTotal(), 0);
    }
}
