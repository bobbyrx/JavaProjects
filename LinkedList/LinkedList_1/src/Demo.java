import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;

public class Demo {
    public static void main(String[] args) {
        LinkedList<String> placesToVisit=new LinkedList<String>();
        addInOrder(placesToVisit,"Sydney");
        addInOrder(placesToVisit,"Melbourne");
        addInOrder(placesToVisit,"Brisbane");
        addInOrder(placesToVisit,"Perth");
        addInOrder(placesToVisit,"Canberra");
        addInOrder(placesToVisit,"Adelaide");
        addInOrder(placesToVisit,"Darwin");

        printList(placesToVisit);

        addInOrder(placesToVisit,"Aaaa");
        printList(placesToVisit);

        placesToVisit.remove(4);
        printList(placesToVisit);
    }
    private static void printList(LinkedList<String> linkedList) {
        Iterator<String>i=linkedList.iterator();
        while(i.hasNext()) {
            System.out.println("Now visiting "+i.next());
        }
        System.out.println("############################");
    }
    private static boolean addInOrder(LinkedList<String> linkedList,String newCity) {
        ListIterator<String>stringListIterator=linkedList.listIterator();
        while(stringListIterator.hasNext()) {
            int comparison=stringListIterator.next().compareTo(newCity);
            if(comparison>0) {
                //add here
                stringListIterator.previous();
                stringListIterator.add(newCity);
                return true;
            }
            else if(comparison<0) {
                //move to next city
                continue;
            }
            else {
                return false;
            }
        }
        stringListIterator.add(newCity);
        return true;
    }

}
