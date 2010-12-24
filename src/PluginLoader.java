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
         * Calls onItemDrop
         */
        ITEM_DROP,
        /**
         * Calls onItemPickUp
         */
        ITEM_PICK_UP,
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
         * Calls onMobSpawn
         */
        MOB_SPAWN,
        /**
         * Calls onDamage
         */
        DAMAGE,
        /**
         * Calls onHealthChange
         */
        HEALTH_CHANGE,
        /**
         * Calls onRedstoneChange
         */
        REDSTONE_CHANGE,
        /**
         * Calls onBlockPhysics
         */
        BLOCK_PHYSICS,
        /**
         * Calls onVehicleCreate
         */
        VEHICLE_CREATE,
        /**
         * Calls onVehicleUpdate
         */
        VEHICLE_UPDATE,
        /**
         * Calls onVehicleDamage
         */
        VEHICLE_DAMAGE,
        /**
         * Calls onVehicleCollision
         */
        VEHICLE_COLLISION,
        /**
         * Calls onVehicleDestroyed
         */
        VEHICLE_DESTROYED,
        /**
         * Calls onVehicleEntered
         */
        VEHICLE_ENTERED,
        /**
         * Calls onVehiclePositionChange
         */
        VEHICLE_POSITIONCHANGE,
        /**
         * Calls onItemUse
         */
        ITEM_USE,
        /**
         * Calls onBlockPlace
         */
        BLOCK_PLACE,
        /**
         * Calls onBlockRightClicked
         */
        BLOCK_RIGHTCLICKED,
        /**
         * Calls onLiquidDestroy
         */
        LIQUID_DESTROY,
        /**
         * Calls onAttack
         */
        ATTACK,
        /**
         * Calls onOpenInventory
         */
        OPEN_INVENTORY,
        /**
         * Calls onSignShow
         */
        SIGN_SHOW,
        /**
         * Calls onSignChange
         */
        SIGN_CHANGE,
        /**
         * Unused.
         */
        NUM_HOOKS
    }
    
    /**
     * HookResult - Used where returning a boolean isn't enough.
     */
    public enum HookResult {
        /**
         * Prevent the action
         */
        PREVENT_ACTION,
        /**
         * Allow the action
         */
        ALLOW_ACTION,
        /**
         * Do whatever it would normally do, continue processing
         */
        DEFAULT_ACTION
    }
    
    public enum DamageType {
        /*
         * Creeper explosion
         */
        CREEPER_EXPLOSION,
        /*
         * Damage dealt by another entity
         */
        ENTITY,
        /*
         * Damage caused by explosion
         */
        EXPLOSION,
        /*
         * Damage caused from falling (fall distance - 3.0)
         */
        FALL,
        /*
         * Damage caused by fire (1)
         */
        FIRE,
        /*
         * Low periodic damage caused by burning (1)
         */
        FIRE_TICK,
        /*
         * Damage caused from lava (4)
         */
        LAVA,
        /*
         * Damage caused from drowning (2)
         */
        WATER,
        /*
         * Damaged caused by cactus (1)
         */
        CACTUS
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
     * @param server server to use
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
     * @param fileName file name of plugin to load
     * @return if the operation was successful
     */
    public Boolean loadPlugin(String fileName) {
        if (getPlugin(fileName) != null) {
            return false; // Already exists.
        }
        return load(fileName);
    }

    /**
     * Reloads the specified plugin
     * @param fileName file name of plugin to reload
     * @return if the operation was successful
     */
    public Boolean reloadPlugin(String fileName) {
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

        return load(fileName);
    }

    private Boolean load(String fileName) {
        try {
            File file = new File("plugins/" + fileName + ".jar");
            if (!file.exists()) {
                log.log(Level.SEVERE, "Failed to find plugin file: plugins/" + fileName + ".jar. Please ensure the file exists");
                return false;
            }
            URLClassLoader child = null;
            try {
                child = new MyClassLoader(new URL[]{file.toURI().toURL()}, Thread.currentThread().getContextClassLoader());
            } catch (MalformedURLException ex) {
                log.log(Level.SEVERE, "Exception while loading class", ex);
                return false;
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
            return false;
        }
        return true;
    }

    /**
     * Returns the specified plugin
     * @param name name of plugin
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
     * @param name name of plugin to enable
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
                return loadPlugin(name);
            } else {
                return false;
            }
        }
        return true;
    }

    /**
     * Disables specified plugin
     * @param name name of the plugin to disable
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
     * @return server
     */
    public Server getServer() {
        return server;
    }

    /**
     * Calls a plugin hook.
     * @param h Hook to call
     * @param parameters Parameters of call
     * @return Object returned by call
     */
    public Object callHook(Hook h, Object... parameters) {
        Object toRet = false;

        if (h == Hook.REDSTONE_CHANGE) {
            toRet = (Integer) parameters[2];
        } else if (h == Hook.LIQUID_DESTROY) {
            toRet = HookResult.DEFAULT_ACTION;
        }

        synchronized (lock) {
            PluginListener listener = null;
            try {
                List<PluginRegisteredListener> registeredListeners = listeners.get(h.ordinal());

                for (PluginRegisteredListener regListener : registeredListeners) {
                    if (!regListener.getPlugin().isEnabled()) {
                        continue;
                    }

                    listener = regListener.getListener();

                    try {
                        switch (h) {
                            case LOGINCHECK:
                                String result = listener.onLoginChecks((String) parameters[0]);
                                if (result != null) {
                                    toRet = result;
                                }
                                break;
                            case LOGIN:
                                listener.onLogin((Player) parameters[0]);
                                break;
                            case DISCONNECT:
                                listener.onDisconnect((Player) parameters[0]);
                                break;
                            case CHAT:
                                if (listener.onChat((Player) parameters[0], (String) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case COMMAND:
                                if (listener.onCommand((Player) parameters[0], (String[]) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case SERVERCOMMAND:
                                if (listener.onConsoleCommand((String[]) parameters[0])) {
                                    toRet = true;
                                }
                                break;
                            case BAN:
                                listener.onBan((Player) parameters[0], (Player) parameters[1], (String) parameters[2]);
                                break;
                            case IPBAN:
                                listener.onIpBan((Player) parameters[0], (Player) parameters[1], (String) parameters[2]);
                                break;
                            case KICK:
                                listener.onKick((Player) parameters[0], (Player) parameters[1], (String) parameters[2]);
                                break;
                            case BLOCK_CREATED:
                                if (listener.onBlockCreate((Player) parameters[0], (Block) parameters[1], (Block) parameters[2], (Integer) parameters[3])) {
                                    toRet = true;
                                }
                                break;
                            case BLOCK_DESTROYED:
                                if (listener.onBlockDestroy((Player) parameters[0], (Block) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case PLAYER_MOVE:
                                listener.onPlayerMove((Player) parameters[0], (Location) parameters[1], (Location) parameters[2]);
                                break;
                            case ARM_SWING:
                                listener.onArmSwing((Player) parameters[0]);
                                break;
                            case ITEM_DROP:
                                if (listener.onItemDrop((Player) parameters[0], (Item) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case ITEM_PICK_UP:
                                if (listener.onItemPickUp((Player) parameters[0], (Item) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case TELEPORT:
                                if (listener.onTeleport((Player) parameters[0], (Location) parameters[1], (Location) parameters[2])) {
                                    toRet = true;
                                }
                                break;
                            case BLOCK_BROKEN:
                                if (listener.onBlockBreak((Player) parameters[0], (Block) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case FLOW:
                                if (listener.onFlow((Block) parameters[0], (Block) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case IGNITE:
                                if (listener.onIgnite((Block) parameters[0], (parameters[1] == null ? null : (Player) parameters[1]))) {
                                    toRet = true;
                                }
                                break;
                            case EXPLODE:
                                if (listener.onExplode((Block) parameters[0])) {
                                    toRet = true;
                                }
                                break;
                            case MOB_SPAWN:
                                if (listener.onMobSpawn((Mob) parameters[0])) {
                                    toRet = true;
                                }
                                break;
                            case DAMAGE:
                                if (listener.onDamage((DamageType) parameters[0], (BaseEntity) parameters[1], (BaseEntity) parameters[2], (Integer) parameters[3])) {
                                    toRet = true;
                                }
                                break;
                            case HEALTH_CHANGE:
                                if (listener.onHealthChange((Player) parameters[0], (Integer) parameters[1], (Integer) parameters[2])) {
                                    toRet = true;
                                }
                                break;
                            case REDSTONE_CHANGE:
                                toRet = listener.onRedstoneChange((Block) parameters[0], (Integer) parameters[1], (Integer) toRet);
                                break;
                            case BLOCK_PHYSICS:
                                if (listener.onBlockPhysics((Block) parameters[0], (Boolean) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case VEHICLE_CREATE:
                                listener.onVehicleCreate((BaseVehicle) parameters[0]);
                                break;
                            case VEHICLE_UPDATE:
                                listener.onVehicleUpdate((BaseVehicle) parameters[0]);
                                break;
                            case VEHICLE_DAMAGE:
                                if (listener.onVehicleDamage((BaseVehicle) parameters[0], (BaseEntity) parameters[1], (Integer) parameters[2])) {
                                    toRet = true;
                                }
                                break;
                            case VEHICLE_COLLISION:
                                if (listener.onVehicleCollision((BaseVehicle) parameters[0], (BaseEntity) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case VEHICLE_DESTROYED:
                                listener.onVehicleDestroyed((BaseVehicle) parameters[0]);
                                break;
                            case VEHICLE_ENTERED:
                                listener.onVehicleEnter((BaseVehicle) parameters[0], (HumanEntity) parameters[1]);
                                break;
                            case VEHICLE_POSITIONCHANGE:
                                listener.onVehiclePositionChange((BaseVehicle) parameters[0], (Integer) parameters[1], (Integer) parameters[2], (Integer) parameters[3]);
                                break;
                            case ITEM_USE:
                                if (listener.onItemUse((Player) parameters[0], (Block) parameters[1], (Block) parameters[2], (Item) parameters[3])) {
                                    toRet = true;
                                }
                                break;
                            case BLOCK_RIGHTCLICKED:
                                listener.onBlockRightClicked((Player) parameters[0], (Block) parameters[1], (Item) parameters[2]);
                                break;
                            case BLOCK_PLACE:
                                if (listener.onBlockPlace((Player) parameters[0], (Block) parameters[1], (Block) parameters[2], (Item) parameters[3])) {
                                    toRet = true;
                                }
                                break;
                            case LIQUID_DESTROY:
                                HookResult ret = listener.onLiquidDestroy((HookResult) toRet, (Integer) parameters[0], (Block) parameters[1]);
                                if (ret != HookResult.DEFAULT_ACTION && (HookResult) toRet == HookResult.DEFAULT_ACTION) {
                                    toRet = ret;
                                }
                                break;
                            case ATTACK:
                                if (listener.onAttack((LivingEntity) parameters[0], (LivingEntity) parameters[1], (Integer) parameters[2])) {
                                    toRet = true;
                                }
                                break;
                            case OPEN_INVENTORY:
                                if (listener.onOpenInventory((Player) parameters[0], (Inventory) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                            case SIGN_SHOW:
                                listener.onSignShow((Player) parameters[0], (Sign) parameters[1]);
                                break;
                            case SIGN_CHANGE:
                                if (listener.onSignChange((Player) parameters[0], (Sign) parameters[1])) {
                                    toRet = true;
                                }
                                break;
                        }
                    } catch (UnsupportedOperationException ex) {
                    }
                }
            } catch (Exception ex) {
                String listenerString = listener == null ? "null(unknown listener)" : listener.getClass().toString();
                log.log(Level.SEVERE, "Exception while calling plugin function in '" + listenerString + "' while calling hook: '" + h.toString() + "'.", ex);
            } catch (Throwable ex) { // The 'exception' thrown is so severe it's
                // not even an exception!
                log.log(Level.SEVERE, "Throwable while calling plugin (Outdated?)", ex);
            }
        }

        return toRet;
    }

    /**
     * Calls a custom hook
     * @param name name of hook
     * @param parameters parameters for the hook
     * @return object returned by call
     */
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
     * @param hook The hook to call on
     * @param listener The listener to use when calling
     * @param plugin The plugin of this listener
     * @param priorityEnum The priority of this listener
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

    /**
     * Adds a custom listener
     * @param listener listener to add
     */
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
     * @param reg listener to remove
     */
    public void removeListener(PluginRegisteredListener reg) {
        List<PluginRegisteredListener> regListeners = listeners.get(reg.getHook().ordinal());
        synchronized (lock) {
            regListeners.remove(reg);
        }
    }

    /**
     * Removes a custom listener
     * @param name name of listener
     */
    public void removeCustomListener(String name) {
        synchronized (lock) {
            customListeners.remove(name);
        }
    }
}
