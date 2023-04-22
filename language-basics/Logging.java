import java.util.logging.Level;
import java.util.logging.Logger;

public class Logging {
    private static final Logger myLogger = Logger.getLogger("ru.meneshev.myapp"); // получение регистратора

    public static void main(String[] args) {
        myLogger.entering("language-basics.Logging", "main");
        //Logger.getGlobal().setLevel(Level.OFF); // запрещает протоколировать
        //Logger.getGlobal().info("Open menu item selected"); // глобальный регистратор
        myLogger.exiting("language-basics.Logging", "main");

        try {
            throw new Exception();
        } catch (Exception e) {
            myLogger.log(Level.WARNING, "Fail to do something", e);
            // принять средства для исправления ошибки...
        }

    }
}
