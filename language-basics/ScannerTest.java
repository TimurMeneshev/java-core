import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Path;
import java.util.Scanner;

public class ScannerTest {
    public static void main(String[] args) {
        /*Scanner scanner = new Scanner(System.in);
        System.out.println("Enter name: ");
        String name = scanner.nextLine();
        System.out.println("Enter age: ");
        int age = scanner.nextInt();
        StringBuilder sb = new StringBuilder();
        sb.append("Hello, ")
                .append(name)
                .append(". Next year, you'll be ")
                .append(age + 1);
        System.out.println(sb);*/

        File file = new File("//Users//timurmenesev//Desktop//files_for_java//file_with_text.rtf");
        System.out.println(file.exists());

        try (Scanner scannerFile = new Scanner(
                Path.of("//Users//timurmenesev//Desktop//files_for_java//file_with_text.rtf"),
                Charset.forName("CP1251"));
             PrintWriter writer = new PrintWriter(
                     "//Users//timurmenesev//Desktop//files_for_java//file_with_text.rtf",
                     Charset.forName("CP1251"))) {
            writer.write("Some english text\n");
            writer.write("Текст на русском\n");
            writer.flush();
            while (scannerFile.hasNext()) {
                System.out.println(scannerFile.nextLine());
            }
        } catch (IOException e) {
            System.out.println("Шляпа с файлом");
        }
    }
}
