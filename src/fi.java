/* Creates the .log file */

import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class fi {
    public static final Logger a = Logger.getLogger("Minecraft");

    public static void a() {
        gv localgv = new gv();

        a.setUseParentHandlers(false);

        ConsoleHandler localConsoleHandler = new ConsoleHandler();
        localConsoleHandler.setFormatter(localgv);
        a.addHandler(localConsoleHandler);

        //For parsers, or whatever.
        try {
            FileHandler localFileHandler = new FileHandler("server.log");
            localFileHandler.setFormatter(localgv);
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
            localFileHandler.setFormatter(localgv);
            a.addHandler(localFileHandler);
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to log to server log", localException);
        }
    }
}
