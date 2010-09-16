
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
    private cq properties;

    public PluginLoader() {
        properties = new cq(new File("server.properties"));
    }

    public void load() {
        String[] classes = properties.getString("plugins", "").split(",");
        for (String sclass : classes) {
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
                    plugin.initialize();
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

    public boolean callHook(HOOKS h, Object[] parameters) {
        boolean toRet = false;
        for (Plugin plugin : plugins) {
            try {
                switch (h) {
                    case LOGINCHECK:
                        if (!plugin.onLoginChecks((String) parameters[0]))
                            toRet = true;
                        break;
                    case LOGIN:
                        plugin.onLogin((ea) parameters[0]);
                        break;
                    case CHAT:
                        plugin.onChat((ea) parameters[0], (String)parameters[1]);
                        break;
                    case COMMAND:
                        if (plugin.onCommand((ea) parameters[0], (String[])parameters[1]))
                            toRet = true;
                        break;
                    case BAN:
                        plugin.onBan((ea) parameters[0], (String)parameters[1]);
                        break;
                    case IPBAN:
                        plugin.onIpBan((ea) parameters[0], (String)parameters[1]);
                        break;
                    case KICK:
                        plugin.onKick((ea) parameters[0], (String)parameters[1]);
                        break;
                    case BLOCK_CREATED:
                        //Nothing yet.
                        break;
                    case BLOCK_DESTROYED:
                        //Nothing yet.
                        break;
                }
            } catch (UnsupportedOperationException ex) {
            }
        }

        return toRet;
    }
}
