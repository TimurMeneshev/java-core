import java.util.Date;

public class Formatting {
    public static void main(String[] args) {
        double x = 1858774.0 / 21;
        System.out.println(x);
        System.out.printf("%4.1f", x);
        String name = "Jack";
        System.out.printf("\nHello, %s. Your balance is %,.2f\n", name, x);

        String format = String.format("\nHello, %s. Your balance is %,.2f\n", name, x);

        System.out.printf("%tc\n", new Date());
        System.out.printf("%1$s %2$tR %2$td.%2$tm.%2$ty", "Due date: ", new Date()); //использование индексов для аргументов
    }
}
