/**
 * Plugin.java - Extend this to create your own plugins.
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
     * Plugin is loaded and may now register hooks
     */
    public void initialize() { }

    /**
     * Returns whether or not this plugin uses listeners
     * @return uses listeners
     */
    public boolean getUsesListeners() { return usesListeners; }
    
    /**
     * Sets uses listeners to true.
     */
    public void setUsesListeners() { usesListeners = true; }

     /**
     * Called when a player moves from one block to another
     * @param player player moving
     * @param from previous block location
     * @param to current block location
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public void onPlayerMove(Player player, Location from, Location to) { }

    /**
     * Called during the early login process to check whether or not to kick the
     *  player
     * @param user
     * @return kick reason. null if you don't want to kick the player.
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public String onLoginChecks(String user) { return null; }

    /**
     * Called during the later login process
     * @param player
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public void onLogin(Player player) { }

    /**
     * Called on player disconnect
     * @param player
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public void onDisconnect(Player player) { }

    /**
     * Called when a player talks. If you return true the message won't be sent
     * out.
     * @param player
     * @param message
     * @return false if you want the message to be sent.
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public boolean onChat(Player player, String message) { return false; }

    /**
     * Called before the command is parsed. Return true if you don't want the
     * command to be parsed.
     * @param player
     * @param split
     * @return false if you want the command to be parsed.
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public boolean onCommand(Player player, String[] split) { return false; }

    /**
     * Called before the server command is parsed. Return true if you don't want
     * the server command to be parsed by the server.
     * @param split
     * @return false if you want the command to be parsed.
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public boolean onServerCommand(String[] split) { return false; }

    /**
     * Called when a player is banned
     * @param player
     * @param reason
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public void onBan(Player player, String reason) { }

    /**
     * Called when a player is IP banned
     * @param player
     * @param reason
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public void onIpBan(Player player, String reason) { }

    /**
     * Called when a player is kicked
     * @param player
     * @param reason
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public void onKick(Player player, String reason) { }

    /**
     * Called when someone presses right click. If they right clicked with a
     * block you can return true to cancel that. You can intercept this to add
     * your own right click actions to different item types (see itemInHand)
     * @param player
     * @param blockPlaced
     * @param blockClicked
     * @param itemInHand
     * @return false if you want the action to go through
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public boolean onBlockCreate(Player player, Block blockPlaced, Block blockClicked, int itemInHand) { return false; }

    /**
     * Called when a person left clicks a block.
     * @param player
     * @param block
     * @return
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public boolean onBlockDestroy(Player player, Block block) { return false; }

    /**
     * Called when a player swings their arm, aka left clicks (even if no block is in front of them)
     * @param player player swinging
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public void onArmSwing(Player player) { }

    /**
     * Called when a player's inventory is modified.
     * @param player player who's inventory was modified
     * @return true if you want any changes to be reverted
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public boolean onInventoryChange(Player player) { return false; }

    /**
     * Called when either a sign, chest or furnace is changed.
     * @param player player who changed it
     * @param block complex block that changed
     * @return true if you want any changes to be reverted
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public boolean onComplexBlockChange(Player player, ComplexBlock block) { return false; }

    /**
     * Called when either a sign, chest or furnace is sent to a
     * player
     * @param player player who the block is being sent to
     * @param block complex block that's being sent
     * @return true if you want the chest, furnace or sign to be
     * empty
     * @deprecated This method is being removed.
     *    Use PluginListener and register hooks during initialize()
     */
    @Deprecated public boolean onSendComplexBlock(Player player, ComplexBlock block) { return false; }
}
