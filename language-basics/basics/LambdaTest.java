package basics;

import javax.swing.*;
import java.util.Arrays;
import java.util.Date;


public class LambdaTest {
    public static void main(String[] args) {
        String[] planets = new String[] {
                "Mercury", "Venus", "Earth",
                "Mars", "Jupiter", "Saturn",
                "Uranus", "Neptune"
        };
        System.out.println(Arrays.toString(planets));

        System.out.println("Dictionary order:");
        Arrays.sort(planets);
        System.out.println(Arrays.toString(planets));

        System.out.println("Sorted by length:");
        Arrays.sort(planets,
                (first, second) -> // в данном случае компилятор может определить типы, поэтому их не указываем
                        first.length() - second.length());
        System.out.println(Arrays.toString(planets));

        Timer t = new Timer(1000, event -> //если 1 аргумент, то можно без "()"
                System.out.println("The time is " +
                        new Date()));
        t.start();

        JOptionPane.showMessageDialog(null,
                "Quit program?");
        System.exit(0);
    }
}
