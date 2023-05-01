package logging;

import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.logging.*;
import javax.swing.*;

public class LoggingImageViewer {
    public static void main(String[] args) {
        // проверяем что нет класса/конфига для приложения
        if (System.getProperty("java.util.config.class") == null
                && System.getProperty("java.util.config.file") == null)
        {
            try {
                // создаем свой регистратор
                Logger.getLogger("ru.meneshev.myapp").setLevel(Level.ALL);

                // кол-во файлов в ротации?
                final int LOG_ROTATION_COUNT = 10;

                // создаем файловый обработчик
                var handler = new FileHandler("%h/LoggingImageViever.log",
                        0, LOG_ROTATION_COUNT);

                // добавляем обработчик в регистратор
                Logger.getLogger("ru.meneshev.myapp").addHandler(handler);
            } catch (IOException e) {
                Logger.getLogger("ru.meneshev.myapp").log(Level.SEVERE,
                        "Can't create log file handler", e);
            }
        }



        EventQueue.invokeLater(() -> {
                    Handler windowHandler = new WindowHandler();
                    windowHandler.setLevel(Level.ALL);
                    Logger.getLogger("ru.meneshev.myapp").addHandler(windowHandler);

                    JFrame frame = new ImageViewerFrame();
                    frame.setTitle("LoggingImageViewer");
                    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                    Logger.getLogger("ru.meneshev.myapp").fine("Showing frale");
                    frame.setVisible(true);
                }
        );
    }
}

class ImageViewerFrame extends JFrame {
    private static final int DEFAULT_WIDTH = 700;
    private static final int DEFAULT_HEIGHT = 800;

    private JLabel label;
    private static Logger logger = Logger.getLogger("ru.meneshev.myapp");

    public ImageViewerFrame() {
        logger.entering("ImageViewerFrame", "<init>");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);

        JMenu menu = new JMenu("File");
        menuBar.add(menu);

        JMenuItem openItem = new JMenuItem("Open");
        menu.add(openItem);
        openItem.addActionListener(new FileOpenListener());

        JMenuItem exitItem = new JMenuItem("Exit");
        menu.add(exitItem);
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logger.fine("Exiting");
                System.exit(0);
            }
        });

        label = new JLabel();
        add(label);
        logger.exiting("ImageVieverFrame", "<init>");
    }

    private class FileOpenListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            logger.entering("ImageVieverFrame.FileOpenListener",
                    "actionPerformed", e);

            JFileChooser chooser = new JFileChooser();
            chooser.setCurrentDirectory(new File("."));

            chooser.setFileFilter(new javax.swing.filechooser.FileFilter() {

                @Override
                public boolean accept(File f) {
                    return f.getName().toLowerCase().endsWith(".gif") || f.isDirectory();
                }

                @Override
                public String getDescription() {
                    return "GIF Images";
                }
            });

            int r = chooser.showOpenDialog(ImageViewerFrame.this);

            if(r == JFileChooser.APPROVE_OPTION) {
                String name = chooser.getSelectedFile().getPath();
                logger.log(Level.FINE, "Reading file (0)", name);
                label.setIcon(new ImageIcon(name));
            } else logger.fine("File open dialog canceled");
            logger.exiting("ImageVieverFrame.FileOpenListener",
                    "actionPerformed");
        }
    }
}

class WindowHandler extends StreamHandler {
    private JFrame frame;

    public WindowHandler() {
        frame = new JFrame();
        final JTextArea output = new JTextArea();
        output.setEditable(false);
        frame.setSize(500, 700);
        frame.add(new JScrollPane(output));
        frame.setFocusableWindowState(false);
        frame.setVisible(true);
        setOutputStream(new OutputStream() {
            @Override
            public void write(int b) throws IOException {
            }

            public void write(byte[] b, int off, int len) {
                output.append(new String(b, off, len));
            }
        });
    }

    public void publish(LogRecord record) {
        if (!frame.isVisible()) return;
        super.publish(record);
        flush();
    }
}


