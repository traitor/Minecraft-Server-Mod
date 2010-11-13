
import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Iterator;
import net.minecraft.server.MinecraftServer;

/**
 * PluginLoader.java - Used to load plugins, toggle them, etc.
 * 
 * @author James
 */
public class PluginLoader {

    /**
     * Hook - Used for adding a listener to listen on specific hooks
     */
    public enum Hook {

        /**
         * Calls onLoginChecks
         */
        LOGINCHECK,
        /**
         * Calls onLogin
         */
        LOGIN,
        /**
         * Calls onChat
         */
        CHAT,
        /**
         * Calls onCommand
         */
        COMMAND,
        /**
         * Calls onConsoleCommand
         */
        SERVERCOMMAND,
        /**
         * Calls onBan
         */
        BAN,
        /**
         * Calls onIpBan
         */
        IPBAN,
        /**
         * Calls onKick
         */
        KICK,
        /**
         * Calls onBlockCreate
         */
        BLOCK_CREATED,
        /**
         * Calls onBlockDestroy
         */
        BLOCK_DESTROYED,
        /**
         * Calls onDisconnect
         */
        DISCONNECT,
        /**
         * Calls onPlayerMove
         */
        PLAYER_MOVE,
        /**
         * Calls onArmSwing
         */
        ARM_SWING,
        /**
         * Calls onComplexBlockChange
         */
        COMPLEX_BLOCK_CHANGE,
        /**
         * Calls onInventoryChange
         */
        INVENTORY_CHANGE,
        /**
         * Calls onCraftInventoryChange
         */
        CRAFTINVENTORY_CHANGE,
        /**
         * Calls onEquipmentChange
         */
        EQUIPMENT_CHANGE,
        /**
         * Calls onItemDrop
         */
        ITEM_DROP,
        /**
         * Calls onSendComplexBlock
         */
        COMPLEX_BLOCK_SEND,
        /**
         * Calls onTeleport
         */
        TELEPORT,
        /**
         * Calls onBlockBreak
         */
        BLOCK_BROKEN,
        /**
         * Calls onIgnite
         */
        IGNITE,
        /**
         * Calls onFlow
         */
        FLOW,
        /**
         * Calls onExplode
         */
        EXPLODE,
        /**
         * Unused.
         */
        NUM_HOOKS
    }
    private static final Logger log = Logger.getLogger("Minecraft");
    private static final Object lock = new Object();
    private List<Plugin> plugins = new ArrayList<Plugin>();
    private List<List<PluginRegisteredListener>> listeners = new ArrayList<List<PluginRegisteredListener>>();
    private HashMap<String, PluginInterface> customListeners = new HashMap<String, PluginInterface>();
    private Server server;
    private PropertiesFile properties;

    /**
     * Creates a plugin loader
     * 
     * @param server
     *            server to use
     */
    public PluginLoader(MinecraftServer server) {
        properties = new PropertiesFile("server.properties");
        this.server = new Server(server);

        for (int h = 0; h < Hook.NUM_HOOKS.ordinal(); ++h) {
            listeners.add(new ArrayList<PluginRegisteredListener>());
        }
    }

    /**
     * Loads all plugins.
     */
    public void loadPlugins() {
        String[] classes = properties.getString("plugins", "").split(",");
        for (String sclass : classes) {
            if (sclass.equals("")) {
                continue;
            }
            loadPlugin(sclass.trim());
        }
    }

    /**
     * Loads the specified plugin
     * 
     * @param fileName
     *            file name of plugin to load
     */
    public void loadPlugin(String fileName) {
        if (getPlugin(fileName) != null) {
            return; // Already exists.
        }
        load(fileName);
    }

    /**
     * Reloads the specified plugin
     * 
     * @param fileName
     *            file name of plugin to reload
     */
    public void reloadPlugin(String fileName) {
        /* Not sure exactly how much of this is necessary */
        Plugin toNull = getPlugin(fileName);
        if (toNull != null) {
            if (toNull.isEnabled()) {
                toNull.disable();
            }
        }
        synchronized (lock) {
            plugins.remove(toNull);
            for (List<PluginRegisteredListener> regListeners : listeners) {
                Iterator<PluginRegisteredListener> iter = regListeners.iterator();
                while (iter.hasNext()) {
                    if (iter.next().getPlugin() == toNull) {
                        iter.remove();
                    }
                }
            }
        }
        toNull = null;

        load(fileName);
    }

    private void load(String fileName) {
        try {
            File file = new File("plugins/" + fileName + ".jar");
            if (!file.exists()) {
                log.log(Level.SEVERE, "Failed to find plugin file: plugins/" + fileName + ".jar. Please ensure the file exists");
                return;
            }
            URLClassLoader child = null;
            try {
                child = new MyClassLoader(new URL[]{file.toURI().toURL()}, Thread.currentThread().getContextClassLoader());
            } catch (MalformedURLException ex) {
                log.log(Level.SEVERE, "Exception while loading class", ex);
            }
            Class c = child.loadClass(fileName);

            Plugin plugin = (Plugin) c.newInstance();
            plugin.setName(fileName);
            plugin.enable();
            synchronized (lock) {
                plugins.add(plugin);
                plugin.initialize();
            }
        } catch (Throwable ex) {
            log.log(Level.SEVERE, "Exception while loading plugin", ex);
        }
    }

    /**
     * Returns the specified plugin
     * 
     * @param name
     *            name of plugin
     * @return plugin
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
     * 
     * @return String of plugins
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
        if (str.length() > 1) {
            return str.substring(0, str.length() - 1);
        } else {
            return "Empty";
        }
    }

    /**
     * Enables the specified plugin (Or adds and enables it)
     * 
     * @param name
     *            name of plugin to enable
     * @return whether or not this plugin was enabled
     */
    public boolean enablePlugin(String name) {
        Plugin plugin = getPlugin(name);
        if (plugin != null) {
            if (!plugin.isEnabled()) {
                plugin.toggleEnabled();
                plugin.enable();
            }
        } else { // New plugin, perhaps?
            File file = new File("plugins/" + name + ".jar");
            if (file.exists()) {
                loadPlugin(name);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Disables specified plugin
     * 
     * @param name
     *            name of the plugin to disable
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
     * 
     * @return server
     */
    public Server getServer() {
        return server;
    }

    /**
     * Calls a plugin hook.
     * 
     * @param h
     *            Hook to call
     * @param parameters
     *            Parameters of call
     * @return Object returned by call
     */
    public Object callHook(Hook h, Object[] parameters) {
        Object toRet = false;
        synchronized (lock) {
            try {
                List<PluginRegisteredListener> registeredListeners = listeners.get(h.ordinal());

                for (PluginRegisteredListener regListener : registeredListeners) {
                    if (!regListener.getPlugin().isEnabled()) {
                        continue;
                    }

                    PluginListener listener = regListener.getListener();

                    try {
                        switch (h) {
                            case LOGINCHECK:
                                String result = listener.onLoginChecks((String) parameters[0]);
                                if (result != null) {
                                    toRet = result;
                                }
                                break;
                            case LOGIN:
                                listener.onLogin(((ep) parameters[0]).getPlayer());
                                break;
                            case DISCONNECT:
                                listener.onDisconnect(((ep) parameters[0]).getPlayer());
                                break;
                            case CHAT:
                                if (listener.onChat(((ep) parameters[0]).getPlayer(), (String) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case COMMAND:
                                if (listener.onCommand(((ep) parameters[0]).getPlayer(), (String[]) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case SERVERCOMMAND:
                                if (listener.onConsoleCommand((String[]) parameters[0])) {
                                    toRet = true;
                                }
                                break;
                            case BAN:
                                listener.onBan(((ep) parameters[0]).getPlayer(), ((ep) parameters[1]).getPlayer(), (String) parameters[2]);
                                break;
                            case IPBAN:
                                listener.onIpBan(((ep) parameters[0]).getPlayer(), ((ep) parameters[1]).getPlayer(), (String) parameters[2]);
                                break;
                            case KICK:
                                listener.onKick(((ep) parameters[0]).getPlayer(), ((ep) parameters[1]).getPlayer(), (String) parameters[2]);
                                break;
                            case BLOCK_CREATED:
                                if (listener.onBlockCreate(((ep) parameters[0]).getPlayer(), (Block) parameters[1], (Block) parameters[2], (Integer) parameters[3])) {
                                    toRet = true;
                                }
                                break;
                            case BLOCK_DESTROYED:
                                if (listener.onBlockDestroy(((ep) parameters[0]).getPlayer(), (Block) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case PLAYER_MOVE:
                                listener.onPlayerMove(((ep) parameters[0]).getPlayer(), (Location) parameters[1], (Location) parameters[2]);
                                break;
                            case ARM_SWING:
                                listener.onArmSwing(((ep) parameters[0]).getPlayer());
                                break;
                            case INVENTORY_CHANGE:
                                if (listener.onInventoryChange(((ep) parameters[0]).getPlayer())) {
                                    toRet = true;
                                }
                                break;
                            case CRAFTINVENTORY_CHANGE:
                                if (listener.onCraftInventoryChange(((ep) parameters[0]).getPlayer())) {
                                    toRet = true;
                                }
                                break;
                            case EQUIPMENT_CHANGE:
                                if (listener.onEquipmentChange(((ep) parameters[0]).getPlayer())) {
                                    toRet = true;
                                }
                                break;
                            case ITEM_DROP:
                                if (listener.onItemDrop(((ep) parameters[0]).getPlayer(), (Item) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case COMPLEX_BLOCK_CHANGE:
                                if (listener.onComplexBlockChange(((ep) parameters[0]).getPlayer(), (ComplexBlock) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case COMPLEX_BLOCK_SEND:
                                if (listener.onSendComplexBlock(((ep) parameters[0]).getPlayer(), (ComplexBlock) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case TELEPORT:
                                if (listener.onTeleport(((ep) parameters[0]).getPlayer(), (Location) parameters[1], (Location) parameters[2])) {
                                    toRet = true;
                                }
                                break;
                            case BLOCK_BROKEN:
                                if (listener.onBlockBreak(((ep) parameters[0]).getPlayer(), (Block) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case FLOW:
                                if (listener.onFlow((Block) parameters[0])) {
                                    toRet = true;
                                }
                                break;
                            case IGNITE:
                                if (listener.onIgnite((Block) parameters[0], (parameters[1] == null ? null : ((ep) parameters[1]).getPlayer()))) {
                                    toRet = true;
                                }
                                break;
                            case EXPLODE:
                                if (listener.onExplode((Block) parameters[0])) {
                                    toRet = true;
                                }
                                break;
                        }
                    } catch (UnsupportedOperationException ex) {
                    }
                }
            } catch (Exception ex) {
                log.log(Level.SEVERE, "Exception while calling plugin function", ex);
            } catch (Throwable ex) { // The 'exception' thrown is so severe it's
                // not even an exception!
                log.log(Level.SEVERE, "Throwable while calling plugin (Outdated?)", ex);
            }
        }

        return toRet;
    }

    public Object callCustomHook(String name, Object[] parameters) {
        Object toRet = false;
        synchronized (lock) {
            try {
                PluginInterface listener = customListeners.get(name);

                if (listener == null) {
                    log.log(Level.SEVERE, "Cannot find custom hook: " + name);
                    return false;
                }

                String msg = listener.checkParameters(parameters);
                if (msg != null) {
                    log.log(Level.SEVERE, msg);
                    return false;
                }

                toRet = listener.run(parameters);
            } catch (Exception ex) {
                log.log(Level.SEVERE, "Exception while calling custom plugin function", ex);
            }
        }
        return toRet;
    }

    /**
     * Calls a plugin hook.
     * 
     * @param hook
     *            The hook to call on
     * @param listener
     *            The listener to use when calling
     * @param plugin
     *            The plugin of this listener
     * @param priorityEnum
     *            The priority of this listener
     * @return PluginRegisteredListener
     */
    public PluginRegisteredListener addListener(Hook hook, PluginListener listener, Plugin plugin, PluginListener.Priority priorityEnum) {
        int priority = priorityEnum.ordinal();
        PluginRegisteredListener reg = new PluginRegisteredListener(hook, listener, plugin, priority);

        synchronized (lock) {
            List<PluginRegisteredListener> regListeners = listeners.get(hook.ordinal());

            int pos = 0;
            for (PluginRegisteredListener other : regListeners) {
                if (other.getPriority() < priority) {
                    break;
                }
                ++pos;
            }

            regListeners.add(pos, reg);
        }

        return reg;
    }

    public void addCustomListener(PluginInterface listener) {
        synchronized (lock) {
            if (customListeners.get(listener.getName()) != null) {
                log.log(Level.SEVERE, "Replacing existing listener: " + listener.getName());
            }
            customListeners.put(listener.getName(), listener);
            log.info("Registered custom hook: " + listener.getName());
        }
    }

    /**
     * Removes the specified listener from the list of listeners
     * 
     * @param reg
     *            listener to remove
     */
    public void removeListener(PluginRegisteredListener reg) {
        List<PluginRegisteredListener> regListeners = listeners.get(reg.getHook().ordinal());
        synchronized (lock) {
            regListeners.remove(reg);
        }
    }

    public void removeCustomListener(String name) {
        synchronized (lock) {
            customListeners.remove(name);
        }
    }
}
