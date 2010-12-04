import java.io.File;
import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class gi {
    public static Logger a = Logger.getLogger("Minecraft");

    public static void a() {
        hx localhx = new hx();

        a.setUseParentHandlers(false);

        ConsoleHandler localConsoleHandler = new ConsoleHandler();
        localConsoleHandler.setFormatter(localhx);
        a.addHandler(localConsoleHandler);
        try {
            FileHandler localFileHandler = new FileHandler("server.log", true);
            localFileHandler.setFormatter(localhx);
            a.addHandler(localFileHandler);
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to log to server.log", localException);
        }
        // hMod: Keep the serveroutput logs.
        File log = new File("logs");
        try {
            if (!log.exists()) {
                log.mkdir();
            }
            FileHandler localFileHandler = new FileHandler("logs/server_" + ((int) (System.currentTimeMillis() / 1000L)) + ".log");
            localFileHandler.setFormatter(localhx);
            a.addHandler(localFileHandler);
        } catch (Exception localException) {
            a.log(Level.WARNING, "Failed to log to server log", localException);
        }
    }
}
