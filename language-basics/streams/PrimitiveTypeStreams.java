package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class PrimitiveTypeStreams {
    public static void show(String title, IntStream stream) {
       final int SIZE = 10;
       int[] fisrstElements = stream.limit(SIZE + 1).toArray();
        System.out.println(title + ": ");
        for (int i = 0; i < fisrstElements.length; i++) {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(fisrstElements[i]);
            else System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        IntStream is1 = IntStream.generate(() -> (int)(Math.random() * 100));
        show("is1", is1);
        IntStream is2 = IntStream.range(5, 10);
        show("is2", is2);
        IntStream is3 = IntStream.rangeClosed(5, 10);
        show("is3", is3);

        Path path = Path.of("/Users/timurmenesev/Downloads/corejava/gutenberg/alice30.txt");
        var contents = Files.readString(path);
        Stream<String> words = Stream.of(contents.split("\\PL+"));

        IntStream is4 = words.mapToInt(String::length);
        show("is4", is4);

        String sentence = "\uD835\uDD46 is the set of octonions.";
        System.out.println(sentence);
        IntStream codes = sentence.codePoints();
        System.out.println(codes.mapToObj(c -> "%X".formatted(c)).collect(Collectors.joining()));

        Stream<Integer> integers = IntStream.range(0, 100).boxed();
        IntStream is5 = integers.mapToInt(Integer::intValue);
        show("is5", is5);
    }
}
