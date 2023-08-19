package permissions;

import javax.swing.*;
import java.awt.*;

public class PermissionTest {
    public static void main(String[] args) {
        System.setProperty("java.security.policy", "permissions/PermissionTest.policy");
        System.setSecurityManager(new SecurityManager());
        EventQueue.invokeLater(() -> {
            var frame = new PermissionTestFrame();
            frame.setTitle("PermissionTest");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setVisible(true);
        });
    }
}

class PermissionTestFrame extends JFrame {
    private JTextField textField;
    private WordCheckTextArea textArea;
    private static final int TEXT_ROWS = 20;
    private static final int TEXT_COLUMNS = 60;

    public PermissionTestFrame() {
        textField = new JTextField(20);
        var panel = new JPanel();
        panel.add(textField);
        var openButton = new JButton("Insert");
        panel.add(openButton);
        openButton.addActionListener(e -> insertWords(textField.getText()));
        add(panel, BorderLayout.NORTH);
        textArea = new WordCheckTextArea();
        textArea.setRows(TEXT_ROWS);
        textArea.setColumns(TEXT_COLUMNS);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        pack();
    }

    public void insertWords(String words) {
        try {
            textArea.append(words + "\n");
        } catch (SecurityException ex) {
            JOptionPane.showMessageDialog(this, "I am sorry, but I cannot do that.");
            ex.printStackTrace();
        }
    }


}
class WordCheckTextArea extends JTextArea {
    public void append(String text) {
        var p = new WordCheckPermission(text, "insert");
        SecurityManager manager = System.getSecurityManager();
        if (manager != null) manager.checkPermission(p);
        super.append(text);
    }
}