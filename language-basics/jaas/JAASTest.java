package jaas;

import java.awt.*;

public class JAASTest {
    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            var frame = new JAASFrame();
            frame.setDefaultCloseOperation(JAASFrame.EXIT_ON_CLOSE);
            frame.setTitle("JAASTest");
            frame.setVisible(true);
        });
    }
}
