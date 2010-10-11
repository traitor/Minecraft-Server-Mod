/**
 * PluginRegisteredListener - Stores the registered listeners
 * @author Maine
 */
public class PluginRegisteredListener {

    private PluginLoader.Hook hook;
    private PluginListener listener;
    private Plugin plugin;
    private int priority;

    public PluginRegisteredListener(PluginLoader.Hook h, PluginListener l, Plugin p, int pri) {
        hook = h;
        listener = l;
        plugin = p;
        priority = pri;
    }

    public PluginLoader.Hook getHook() {
        return hook;
    }

    public PluginListener getListener() {
        return listener;
    }

    public Plugin getPlugin() {
        return plugin;
    }

    public int GetPriority() {
        return priority;
    }
}
