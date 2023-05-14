package basics;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.function.IntConsumer;


//closure - замыкание (блок кода вместе со значениями свободных переменных)
public class ClosureExample {
    public static void repeatMessage(String text, int delay) {
        ActionListener listener = event -> {
            System.out.println(text); // CLOSURE - используем свободную переменную (вне лямбды)
            Toolkit.getDefaultToolkit().beep();
        };
        new Timer(delay, listener).start();
    }

    public static void countDown(int start, int delay) {
        ActionListener listener = event -> {
          //start--; нельзя изменить захваченную переменную
            System.out.println(start);
        };
        new Timer(delay, listener).start();
    }

    //принимаем лямбду в кач-ве аргумента
    public static void repeat(int n, IntConsumer action) { //стандартный интерфейс для обработки int
        for(int i = 0; i < n; i++) action.accept(i);
    }

    public static void main(String[] args) {
        ClosureExample.repeat(10, i -> System.out.println("Countdown: " + (9 - i)));
    }
}
