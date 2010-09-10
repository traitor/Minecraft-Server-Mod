
import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class fk {

    public static Logger a = Logger.getLogger("Minecraft");

    public static void a() {
        gx localgx = new gx();

        a.setUseParentHandlers(false);

        ConsoleHandler localConsoleHandler = new ConsoleHandler();
        localConsoleHandler.setFormatter(localgx);
        a.addHandler(localConsoleHandler);

        //For parsers, or whatever.
        try {
            FileHandler localFileHandler = new FileHandler("server.log");
            localFileHandler.setFormatter(localgx);
            a.addHandler(localFileHandler);
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to log to server log", localException);
        }

        //Keep these files logged.
        File log = new File("logs");
        try {
            if (!log.exists()) {
                log.mkdir();
            }
            FileHandler localFileHandler = new FileHandler("logs/server_" + ((int) (System.currentTimeMillis() / 1000L)) + ".log");
            localFileHandler.setFormatter(localgx);
            a.addHandler(localFileHandler);
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to log to server log", localException);
        }
    }
}
