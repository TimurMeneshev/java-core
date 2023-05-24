package optional;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

public class OptionalTest {
    public static Optional<Double> inverse(Double x) {
        return  x == 0 ? Optional.empty() : Optional.of(1 / x);
    }

    public static Optional<Double> squareRoot(Double x) {
        return x < 0 ? Optional.empty() : Optional.of(Math.sqrt(x));
    }

    public static void main(String[] args) throws IOException {
        var contents = Files.readString(Path.of("/Users/timurmenesev/Downloads/curl_request_log.txt"));
        List<String> wordList = List.of(contents.split("\\PL+"));

        Optional<String> optionalValue = wordList.stream()
                .filter(s -> s.contains("a"))
                .findFirst();

        System.out.println(optionalValue.orElse("No word contains a"));

        Optional<String> optionalString = Optional.empty();
        String result = optionalString.orElse("N/A");
        System.out.println("result: " + result);

        result = optionalString.orElseGet(() -> Locale.getDefault().getDisplayName());
        System.out.println("result: " + result);

        try {
            result = optionalString.orElseThrow(IllegalStateException::new);
            System.out.println(result);
        } catch (Throwable t) {
            t.printStackTrace();
        }

        optionalValue = wordList.stream()
                .filter(s -> s.contains("a"))
                .findFirst();
        optionalValue.ifPresent(s -> System.out.println(s + " contains a"));

        var results = new HashSet<String>();
        optionalValue.ifPresent(results::add);
        Optional<Boolean> added = optionalValue.map(results::add);
        System.out.println(added);

        System.out.println(inverse(4.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(-1.0).flatMap(OptionalTest::squareRoot));
        System.out.println(inverse(0.0).flatMap(OptionalTest::squareRoot));

        Optional<Double> result2 = Optional.of(-4.0).flatMap(OptionalTest::inverse).flatMap(OptionalTest::squareRoot);
        System.out.println(result2);
    }
}
