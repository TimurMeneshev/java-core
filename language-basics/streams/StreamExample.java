package streams;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

public class StreamExample {
    public static void main(String[] args) throws IOException {
        var logString = Files.readString(Path.of("/Users/timurmenesev/Downloads/curl_request_log.txt"));
        List<String> lWords = List.of(logString.split("\\PL+"));
        //фильтрация стрима
        Stream<String> words = lWords.stream()
                .filter(w -> w.contains("a"));
        //words.forEach(System.out::println);

        //преобразование стрима
        lWords.stream().map(String::toUpperCase).forEach(System.out::println);

        lWords.stream().map(s -> s.concat(s)).forEach(System.out::println);
    }
}
