/* Save-alls every x minutes according to settings */

import net.minecraft.server.MinecraftServer;

public class SaveAllThread implements Runnable {
    private boolean running = false;
    private long saveInterval = 1800000L;
    private MinecraftServer server;

    public SaveAllThread(MinecraftServer paramMinecraftServer, long saveInterval) {
        this.server = paramMinecraftServer;
        this.saveInterval = saveInterval;
    }

    public void run() {
        while (this.running) {
            try {
                Thread.sleep(saveInterval);
            } catch (InterruptedException localInterruptedException) {
            }
            server.a("save-all", server);
        }
    }

    public void start() {
        this.running = true;
        new Thread(this).start();
    }

    public void stop() {
        this.running = false;
    }
}
