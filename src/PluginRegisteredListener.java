/**
 * PluginRegisteredListener - Stores the registered listeners
 * @author Maine
 */
public class PluginRegisteredListener {

    private PluginLoader.Hook hook;
    private PluginListener listener;
    private Plugin plugin;
    private int priority;

    /**
     * Creates a register listener class for calling later.
     * @param h The hook this registered listener is for
     * @param l The plugin listener itself
     * @param p The plugin itself
     * @param pri The priority of this listener
     */
    public PluginRegisteredListener(PluginLoader.Hook h, PluginListener l, Plugin p, int pri) {
        hook = h;
        listener = l;
        plugin = p;
        priority = pri;
    }

    /**
     * Returns the hook for this listener
     * @return hook
     */
    public PluginLoader.Hook getHook() {
        return hook;
    }

    /**
     * Returns the listener
     * @return listener
     */
    public PluginListener getListener() {
        return listener;
    }

    /**
     * Returns this listener's plugin
     * @return plugin
     */
    public Plugin getPlugin() {
        return plugin;
    }

    /**
     * Returns this listener's priority
     * @return priority
     */
    public int getPriority() {
        return priority;
    }
}
