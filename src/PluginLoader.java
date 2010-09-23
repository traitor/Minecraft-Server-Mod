
import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class PluginLoader {

    public enum HOOKS {

        LOGINCHECK,
        LOGIN,
        CHAT,
        COMMAND,
        BAN,
        IPBAN,
        KICK,
        BLOCK_CREATED,
        BLOCK_DESTROYED,
    }
    private static final Logger log = Logger.getLogger("Minecraft");
    private List<Plugin> plugins = new ArrayList<Plugin>();
    private Server server;
    private cq properties;

    public PluginLoader(MinecraftServer server) {
        properties = new cq(new File("server.properties"));
        this.server = new Server(server);
    }

    public void load() {
        String[] classes = properties.getString("plugins", "").split(",");
        for (String sclass : classes) {
            if (sclass.equals(""))
                continue;
            try {
                File myJar = new File("plugins/" + sclass + ".jar");
                URLClassLoader child = null;
                try {
                    child = new URLClassLoader(new URL[]{myJar.toURL()}, this.getClass().getClassLoader());
                } catch (MalformedURLException ex) {
                    log.log(Level.SEVERE, "Exception while loading class", ex);
                }
                Class c = Class.forName(sclass, true, child);

                try {
                    Plugin plugin = (Plugin) c.newInstance();
                    plugin.enable();
                    plugins.add(plugin);
                } catch (InstantiationException ex) {
                    log.log(Level.SEVERE, "Exception while loading class", ex);
                } catch (IllegalAccessException ex) {
                    log.log(Level.SEVERE, "Exception while loading class", ex);
                }
            } catch (ClassNotFoundException ex) {
                log.log(Level.SEVERE, "Exception while loading class", ex);
            }
        }
    }

    public Server getServer() {
        return server;
    }

    public Object callHook(HOOKS h, Object[] parameters) {
        Object toRet = false;
        for (Plugin plugin : plugins) {
            try {
                switch (h) {
                    case LOGINCHECK:
                        String result = (String)plugin.onLoginChecks((String) parameters[0]);
                        if (result != null)
                            toRet = result;
                        break;
                    case LOGIN:
                        plugin.onLogin(new Player((ea) parameters[0]));
                        break;
                    case CHAT:
                        plugin.onChat(new Player((ea) parameters[0]), (String)parameters[1]);
                        break;
                    case COMMAND:
                        if (plugin.onCommand(new Player((ea) parameters[0]), (String[])parameters[1]))
                            toRet = true;
                        break;
                    case BAN:
                        plugin.onBan(new Player((ea) parameters[0]), (String)parameters[1]);
                        break;
                    case IPBAN:
                        plugin.onIpBan(new Player((ea) parameters[0]), (String)parameters[1]);
                        break;
                    case KICK:
                        plugin.onKick(new Player((ea) parameters[0]), (String)parameters[1]);
                        break;
                    case BLOCK_CREATED:
                        if (plugin.onBlockCreate(new Player((ea) parameters[0]), (Block)parameters[1]))
                            toRet = true;
                        break;
                    case BLOCK_DESTROYED:
                        if (plugin.onBlockDestroy(new Player((ea) parameters[0]), (Block)parameters[1]))
                            toRet = true;
                        break;
                }
            } catch (UnsupportedOperationException ex) {
            }
        }

        return toRet;
    }
}
