package mail;

import java.io.Console;
import java.io.IOException;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Properties;

public class MailTest {
    public static void main(String[] args) throws IOException {
        var props = new Properties();
        try (Reader in = Files.newBufferedReader(Path.of("mail", "mail.properties"), StandardCharsets.UTF_8)) {
            props.load(in);
        }
        List<String> lines = Files.readAllLines(Path.of(args[0]), StandardCharsets.UTF_8);

        String from = lines.get(0);
        String to = lines.get(1);
        String subject = lines.get(2);

        var builder = new StringBuilder();
        for (int i = 3; i < lines.size(); i++) {
            builder.append(lines.get(i));
            builder.append("\n");
        }

        Console console = System.console();
        var password = new String(console.readPassword("Password:"));

    }
}
