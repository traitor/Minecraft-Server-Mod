
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

/**
 * PluginLoader.java - Used to load plugins, toggle them, etc.
 * @author James
 */
public class PluginLoader {

    public enum Hook {
        LOGINCHECK,
        LOGIN,
        CHAT,
        COMMAND,
        SERVERCOMMAND,
        BAN,
        IPBAN,
        KICK,
        BLOCK_CREATED,
        BLOCK_DESTROYED,
        DISCONNECT,
        PLAYER_MOVE,
        ARM_SWING,
        COMPLEX_BLOCK_CHANGE,
        INVENTORY_CHANGE,
        COMPLEX_BLOCK_SEND,
    }
    private static final Logger log = Logger.getLogger("Minecraft");
    private static final Object lock = new Object();
    private List<Plugin> plugins = new ArrayList<Plugin>();
    private Server server;
    private PropertiesFile properties;

    /**
     * Creates a plugin loader
     * @param server
     */
    public PluginLoader(MinecraftServer server) {
        properties = new PropertiesFile("server.properties");
        this.server = new Server(server);
    }

    /**
     * Loads all plugins.
     */
    public void loadPlugins() {
        String[] classes = properties.getString("plugins", "").split(",");
        for (String sclass : classes) {
            if (sclass.equals(""))
                continue;
            loadPlugin(sclass);
        }
    }

    /**
     * Loads the specified plugin
     * @param fileName
     */
    public void loadPlugin(String fileName) {
        if (getPlugin(fileName) != null)
            return; //Already exists.
        load(fileName);
    }

    /**
     * Reloads the specified plugin
     * @param fileName
     */
    public void reloadPlugin(String fileName) {
        /* Not sure exactly how much of this is necessary */
        Plugin toNull = getPlugin(fileName);
        if (toNull != null)
            if (toNull.isEnabled())
                toNull.disable();
        plugins.remove(toNull);
        toNull = null;

        load(fileName);
    }

    private void load(String fileName) {
        try {
            File file = new File("plugins/" + fileName + ".jar");
            URLClassLoader child = null;
            try {
                child = new MyClassLoader(new URL[]{file.toURL()}, this.getClass().getClassLoader());
            } catch (MalformedURLException ex) {
                log.log(Level.SEVERE, "Exception while loading class", ex);
            }
            Class c = Class.forName(fileName, true, child);

            try {
                Plugin plugin = (Plugin) c.newInstance();
                plugin.setName(fileName);
                plugin.enable();
                synchronized (lock) {
                    plugins.add(plugin);
                }
            } catch (InstantiationException ex) {
                log.log(Level.SEVERE, "Exception while loading plugin", ex);
            } catch (IllegalAccessException ex) {
                log.log(Level.SEVERE, "Exception while loading plugin", ex);
            }
        } catch (ClassNotFoundException ex) {
            log.log(Level.SEVERE, "Exception while loading plugin", ex);
        }
    }

    /**
     * Returns the specified plugin
     * @param name
     * @return
     */
    public Plugin getPlugin(String name) {
        synchronized (lock) {
            for (Plugin plugin : plugins) {
                if (plugin.getName().equalsIgnoreCase(name)) {
                    return plugin;
                }
            }
        }
        return null;
    }

    /**
     * Returns a string list of plugins
     * @return
     */
    public String getPluginList() {
        StringBuilder sb = new StringBuilder();
        synchronized (lock) {
            for (Plugin plugin : plugins) {
                sb.append(plugin.getName());
                sb.append(" ");
                sb.append(plugin.isEnabled() ? "(E)" : "(D)");
                sb.append(",");
            }
        }
        String str = sb.toString();
        if (str.length() > 1)
            return str.substring(0, str.length() - 1);
        else
            return "Empty";
    }

    /**
     * Enables the specified plugin (Or adds and enables it)
     * @param name
     * @return
     */
    public boolean enablePlugin(String name) {
        Plugin plugin = getPlugin(name);
        if (plugin != null) {
            if (!plugin.isEnabled()) {
                plugin.toggleEnabled();
                plugin.enable();
            }
        } else { //New plugin, perhaps?
            File file = new File("plugins/" + name + ".jar");
            if (file.exists())
                loadPlugin(name);
            else
                return false;
        }
        return true;
    }

    /**
     * Disables specified plugin
     * @param name
     */
    public void disablePlugin(String name) {
        Plugin plugin = getPlugin(name);
        if (plugin != null) {
            if (plugin.isEnabled()) {
                plugin.toggleEnabled();
                plugin.disable();
            }
        }
    }

    /**
     * Returns the server
     * @return
     */
    public Server getServer() {
        return server;
    }

    /**
     * Calls a plugin hook.
     * @param h
     * @param parameters
     * @return
     */
    public Object callHook(Hook h, Object[] parameters) {
        Object toRet = false;
        synchronized (lock) {
            try {
                for (Plugin plugin : plugins) {
                    if (!plugin.isEnabled())
                        continue;
                    
                    try {
                        switch (h) {
                            case LOGINCHECK:
                                String result = plugin.onLoginChecks((String) parameters[0]);
                                if (result != null)
                                    toRet = result;
                                break;
                            case LOGIN:
                                plugin.onLogin(((ea) parameters[0]).getPlayer());
                                break;
                            case DISCONNECT:
                                plugin.onDisconnect(((ea) parameters[0]).getPlayer());
                                break;
                            case CHAT:
                                if (plugin.onChat(((ea) parameters[0]).getPlayer(), (String)parameters[1]))
                                    toRet = true;
                                break;
                            case COMMAND:
                                if (plugin.onCommand(((ea) parameters[0]).getPlayer(), (String[])parameters[1]))
                                    toRet = true;
                                break;
                             case SERVERCOMMAND:
                                if (plugin.onServerCommand((String[])parameters[0]))
                                    toRet = true;
                                break;
                            case BAN:
                                plugin.onBan(((ea) parameters[0]).getPlayer(), (String)parameters[1]);
                                break;
                            case IPBAN:
                                plugin.onIpBan(((ea) parameters[0]).getPlayer(), (String)parameters[1]);
                                break;
                            case KICK:
                                plugin.onKick(((ea) parameters[0]).getPlayer(), (String)parameters[1]);
                                break;
                            case BLOCK_CREATED:
                                if (plugin.onBlockCreate(((ea) parameters[0]).getPlayer(), (Block)parameters[1], (Block)parameters[2], (Integer)parameters[3]))
                                    toRet = true;
                                break;
                            case BLOCK_DESTROYED:
                                if (plugin.onBlockDestroy(((ea) parameters[0]).getPlayer(), (Block)parameters[1]))
                                    toRet = true;
                                break;
                            case PLAYER_MOVE:
                                plugin.onPlayerMove(((ea) parameters[0]).getPlayer(), (Location)parameters[1], (Location)parameters[2]);
                                break;
                            case ARM_SWING:
                                plugin.onArmSwing(((ea) parameters[0]).getPlayer());
                                break;
                            case INVENTORY_CHANGE:
                                if (plugin.onInventoryChange(((ea) parameters[0]).getPlayer()))
                                    toRet = true;
                                break;
                            case COMPLEX_BLOCK_CHANGE:
                                if (plugin.onComplexBlockChange(((ea) parameters[0]).getPlayer(), (ComplexBlock) parameters[1]))
                                    toRet = true;
                                break;
                            case COMPLEX_BLOCK_SEND:
                                if (plugin.onSendComplexBlock(((ea) parameters[0]).getPlayer(), (ComplexBlock) parameters[1]))
                                    toRet = true;
                                break;
                        }
                    } catch (UnsupportedOperationException ex) {
                    }
                }
            } catch (Exception ex) {
                log.log(Level.SEVERE, "Exception while calling plugin function", ex);
            } catch (Throwable ex) { //The 'exception' thrown is so severe it's not even an exception!
                log.log(Level.SEVERE, "Throwable while calling plugin (Outdated?)", ex);
            }
        }

        return toRet;
    }
}
