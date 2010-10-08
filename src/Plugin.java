
/**
 * Plugin.java - Extend this to create your own plugins.
 * @author James
 */
public abstract class Plugin {
    private String name = "";
    private boolean enabled = true;

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
     * @return
     */
    public boolean isEnabled() { return enabled; }

    /**
     * Toggles whether or not this plugin is enabled
     * @return
     */
    public boolean toggleEnabled() { enabled = !enabled; return enabled; }

    /**
     * Sets the name of this plugin
     * @param name
     */
    public void setName(String name) { this.name = name; }

    /**
     * Returns the name of this plugin
     * @return name
     */
    public String getName() { return name; }

    /**
     * Called when a player moves from one block to another
     * @param player player moving
     * @param from previous block location
     * @param to current block location
     */
    public void onPlayerMove(Player player, Location from, Location to) { }

    /**
     * Called during the early login process to check whether or not to kick the
     *  player
     * @param user
     * @return kick reason. null if you don't want to kick the player.
     */
    public String onLoginChecks(String user) { return null; }

    /**
     * Called during the later login process
     * @param player
     */
    public void onLogin(Player player) { }

    /**
     * Called on player disconnect
     * @param player
     */
    public void onDisconnect(Player player) { }

    /**
     * Called when a player talks. If you return true the message won't be sent
     * out.
     * @param player
     * @param message
     * @return false if you want the message to be sent.
     */
    public boolean onChat(Player player, String message) { return false; }
    
    /**
     * Called before the command is parsed. Return true if you don't want the
     * command to be parsed.
     * @param player
     * @param split
     * @return false if you want the command to be parsed.
     */
    public boolean onCommand(Player player, String[] split) { return false; }

    /**
     * Called before the server command is parsed. Return true if you don't want
     * the server command to be parsed by the server.
     * @param split
     * @return false if you want the command to be parsed.
     */
    public boolean onServerCommand(String[] split) { return false; }
    
    /**
     * Called when a player is banned
     * @param player
     * @param reason
     */
    public void onBan(Player player, String reason) { }

    /**
     * Called when a player is IP banned
     * @param player
     * @param reason
     */
    public void onIpBan(Player player, String reason) { }

    /**
     * Called when a player is kicked
     * @param player
     * @param reason
     */
    public void onKick(Player player, String reason) { }

    /**
     * Called when someone presses right click. If they right clicked with a
     * block you can return true to cancel that. You can intercept this to add
     * your own right click actions to different item types (see itemInHand)
     * @param player
     * @param blockPlaced
     * @param blockClicked
     * @param itemInHand
     * @return false if you want the action to go through
     */
    public boolean onBlockCreate(Player player, Block blockPlaced, Block blockClicked, int itemInHand) { return false; }

    /**
     * Called when a person left clicks a block.
     * @param player
     * @param block
     * @return
     */
    public boolean onBlockDestroy(Player player, Block block) { return false; }

    /**
     * Called when a player swings their arm, aka left clicks (even if no block is in front of them)
     * @param player player swinging
     */
    public void onArmSwing(Player player) { }

}
