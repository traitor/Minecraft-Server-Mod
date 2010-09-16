//Interface for the minecraft server
import java.util.ArrayList;
import java.util.List;
import net.minecraft.server.MinecraftServer;

public class Server {

    private MinecraftServer server;

    public Server(MinecraftServer server) {
        this.server = server;
    }

    public void useConsoleCommand(String command) {
        server.a(command, server);
    }

    public void setTimer(String uniqueString, int time) {
        MinecraftServer.b.put(uniqueString, time);
    }

    public boolean isTimerExpired(String uniqueString) {
        return MinecraftServer.b.containsKey(uniqueString);
    }

    public long getTime() {
        return server.e.c;
    }

    public void setTime(long time) {
        server.e.c = time;
    }

    public MinecraftServer getMCServer() {
        return server;
    }

    //Sort of shitty, but oh well.
    public List<Player> getPlayerList() {
        List<Player> toRet = new ArrayList<Player>();
        for (Object o : server.f.b)
            toRet.add(new Player((ea)o));
        return toRet;
    }
}
