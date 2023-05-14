package collections;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeSetTest {
    public static void main(String[] args) {
        var parts = new TreeSet<Item>();
        parts.add(new Item("Toaster", 1234));
        parts.add(new Item("Widget", 4652));
        parts.add(new Item("Modem", 9912));

        //сортировка по номеру
        System.out.println(parts);

        //указываем поле, по которму сравнивать
        var sortByDescription = new TreeSet<Item>(Comparator.comparing(Item::getDescription));

        sortByDescription.addAll(parts);

        //сортировка по описанию
        System.out.println(sortByDescription);
    }
}
