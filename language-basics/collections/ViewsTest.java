package collections;

import java.util.*;

import static java.util.Map.entry;

public class ViewsTest {
    public static void main(String[] args) {
        //small collections
        List<String> names = List.of("Peter", "Paul", "Mary");
        Set<Integer> numbers = Set.of(2, 3, 5);
        Map<String, Integer> scores = Map.of("Peter", 2, "Paul", 3, "Mary", 5);
        Map<String, Integer> scoresEntries = Map.ofEntries(
                entry("Peter", 2),
                entry("Paul", 3),
                entry("Mary", 5));


        //unmodifiable views
        List<String> unmodNames = Collections.unmodifiableList(names);
        //unmodNames.add("John"); //UnsupportedOperationException

        //subranges
        List<String> subListNames = names.subList(1, 3); //from 2 to 3

        //checked views
        var strings = new ArrayList<String>();
        List<String> safeStrings = Collections.checkedList(strings, String.class);
        //ArrayList rawList = (ArrayList) safeStrings; //ClassCastException
        //rawList.add(new Date());

        //synchronized views
        var map = Collections.synchronizedMap(new HashMap<String, Integer>(scores));

        System.out.println(
                "small collections: " + "\n" + names +
                        "\n" + numbers + "\n" + scores +
                        "\n" + scoresEntries + "\n" +
                        "unmodifiable views: " + "\n" + unmodNames + "\n" +
                        "subranges: " + "\n" + subListNames
        );

    }
}
