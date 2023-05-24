package streams;

import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Iterator;
import java.util.List;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.regex.Pattern;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;

public class CreatingStreams {
    public static <T> void show(String title, Stream<T> stream) {
        final int SIZE = 10;
        List<T> firstElements = stream.limit(SIZE+1).toList();
        System.out.println(title + ": ");
        for (int i = 0; i < firstElements.size(); i++) {
            if (i > 0) System.out.print(", ");
            if (i < SIZE) System.out.print(firstElements.get(i));
            else System.out.print("...");
        }
        System.out.println();
    }

    public static void main(String[] args) throws IOException {
        Path path = Path.of("/Users/timurmenesev/Downloads/curl_request_log.txt");
        var contents = Files.readString(path);

        //создание стрима из массива
        Stream<String> words = Stream.of(contents.split("\\PL+"));
        show("words", words);

        //создание стрима из массива
        Stream<String> song = Stream.of("sad", "but", "true");
        show("song", song);

        //создание пустого стрима
        Stream<String> silence = Stream.empty();
        show("silence", silence);

        //создание бесконечного стрима строк
        Stream<String> echos = Stream.generate(() -> "Echo");
        show("echos", echos);

        //создание бесконечного стрима случайных чисел
        Stream<Double> randoms = Stream.generate(Math::random);
        show("randoms", randoms);

        //создание бесконечного стрима с последовательностью + 1
        Stream<BigInteger> integers = Stream.iterate(BigInteger.ONE, n -> n.add(BigInteger.ONE));
        show("integers", integers);

        Stream<String> greetings = "Hello\nGuten Tag\nBonjour".lines();
        show("greetings", greetings);

        Stream<String> wordsAnotherWay = Pattern.compile("\\PL+")
                .splitAsStream(contents);
        show("wordsAnotherWay", wordsAnotherWay);

        try (Stream<String> lines = Files.lines(path)) {
            show("lines", lines);
        }

        //преобразование в стрим НЕ коллекции
        Iterable<Path> iterable = FileSystems.getDefault().getRootDirectories();
        Stream<Path> rootDirectories = StreamSupport.stream(iterable.spliterator(), false);
        show("rootDirectories", rootDirectories);

        //направление результатов итератора в стрим
        Iterator<Path> iterator = Path.of("/Users/timurmenesev/Downloads").iterator();
        Stream<Path> pathComponents = StreamSupport.stream(
                Spliterators.spliteratorUnknownSize(iterator, Spliterator.ORDERED), false);
        show("pathComponents", pathComponents);
    }
}
