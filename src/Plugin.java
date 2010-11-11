
/**
 * Plugin.java - Extend this to create your own plugins.
 * 
 * @author James
 */
public abstract class Plugin {

    private String name = "";
    private boolean enabled = true;
    private boolean usesListeners;

    /**
     * Enables the plugin
     */
    public abstract void enable();

    /**
     * Disables the plugin
     */
    public abstract void disable();

    /**
     * Returns true if this plugin is enabled
     * 
     * @return
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Toggles whether or not this plugin is enabled
     * 
     * @return
     */
    public boolean toggleEnabled() {
        enabled = !enabled;
        return enabled;
    }

    /**
     * Sets the name of this plugin
     * 
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the name of this plugin
     * 
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * Plugin is loaded and may now register hooks
     */
    public void initialize() {
    }
}
