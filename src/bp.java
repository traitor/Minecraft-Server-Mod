/* This is used so we can set the minecraft server for etc + load everything on server start */

import net.minecraft.server.MinecraftServer;

public class bp extends Thread {
    public bp(MinecraftServer paramMinecraftServer) {
        etc.getInstance().loadData();
        etc.getInstance().startThreads(paramMinecraftServer);
        etc.getInstance().getLoader();
        etc.setServer(paramMinecraftServer);
        setDaemon(true);
        start();
    }

    public void run() {
        try {
            Thread.sleep(2147483647L);
        } catch (InterruptedException localInterruptedException) {
        }
    }
}
