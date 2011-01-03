/**
 * PluginListener.java - Extend this and register it to listen to specific
 * hooks.
 * 
 * @author Maine
 */
public class PluginListener {

    /**
     * Priority - Used for priority for plugin listeners
     */
    public enum Priority {

        /**
         * Highly critical for hooks that absolutely must occur before any
         * others. Use carefully.
         */
        CRITICAL,
        /**
         * May block/interrupt/undo the action, but prefer MEDIUM
         */
        HIGH,
        /**
         * Preferred priority for blocking/interrupting/undoing the action
         */
        MEDIUM,
        /**
         * Must not block/interrupt/undo the action
         */
        LOW
    }

    /**
     * Called when a player moves from one block to another
     * 
     * @param player
     *            player moving
     * @param from
     *            previous block location
     * @param to
     *            current block location
     */
    public void onPlayerMove(Player player, Location from, Location to) {
    }

    /**
     * Called when a player teleports from one location to another
     * 
     * @param player
     *            player moving
     * @param from
     *            previous block location
     * @param to
     *            current block location
     * @return false if you want the player to teleport.
     */
    public boolean onTeleport(Player player, Location from, Location to) {
        return false;
    }

    /**
     * Called during the early login process to check whether or not to kick the
     * player
     * 
     * @param user
     * @return kick reason. null if you don't want to kick the player.
     */
    public String onLoginChecks(String user) {
        return null;
    }

    /**
     * Called during the later login process
     * 
     * @param player
     */
    public void onLogin(Player player) {
    }

    /**
     * Called on player disconnect
     * 
     * @param player
     */
    public void onDisconnect(Player player) {
    }

    /**
     * Called when a player talks. If you return true the message won't be sent
     * out.
     * 
     * @param player
     * @param message
     * @return false if you want the message to be sent.
     */
    public boolean onChat(Player player, String message) {
        return false;
    }

    /**
     * Called before the command is parsed. Return true if you don't want the
     * command to be parsed.
     * 
     * @param player
     * @param split
     * @return false if you want the command to be parsed.
     */
    public boolean onCommand(Player player, String[] split) {
        return false;
    }

    /**
     * Called before the console command is parsed. Return true if you don't
     * want the server command to be parsed by the server.
     * 
     * @param split
     * @return false if you want the command to be parsed.
     */
    public boolean onConsoleCommand(String[] split) {
        return false;
    }

    /**
     * Called when a player is banned
     * 
     * @param mod
     *            moderator that's banning
     * @param player
     *            player being banned
     * @param reason
     *            reason for ban
     */
    public void onBan(Player mod, Player player, String reason) {
    }

    /**
     * Called when a player is IP banned
     * 
     * @param mod
     *            moderator that's banning
     * @param player
     *            player being banning
     * @param reason
     *            for IP ban
     */
    public void onIpBan(Player mod, Player player, String reason) {
    }

    /**
     * Called when a player is kicked
     * 
     * @param mod
     *            moderator that's kicking
     * @param player
     *            player being kicked
     * @param reason
     *            reason for kick
     */
    public void onKick(Player mod, Player player, String reason) {
    }

    /**
     * Called when someone presses right click aimed at a block.
     * You can intercept this to add your own right click actions
     * to different item types (see itemInHand)
     * 
     * @param player
     * @param blockPlaced
     * @param blockClicked
     * @param itemInHand
     * @return false if you want the action to go through
     * 
     * @deprecated use onBlockRightClick to get the information
     * @see #onBlockRightClicked(Player, Block, Item)
     * @see #onBlockPlace(Player, Block, Block, Item)
     * @see #onItemUse(Player, Block, Block, Item)
     */
    @Deprecated
    public boolean onBlockCreate(Player player, Block blockPlaced, Block blockClicked, int itemInHand) {
        return false;
    }

    /**
     * Called when a person left clicks a block.
     * 
     * @param player
     * @param block
     * @return
     */
    public boolean onBlockDestroy(Player player, Block block) {
        return false;
    }

    /**
     * Called when a person actually breaks the block.
     * 
     * @param player
     * @param block
     * @return
     */
    public boolean onBlockBreak(Player player, Block block) {
        return false;
    }

    /**
     * Called when a player swings their arm, aka left clicks (even if no block
     * is in front of them)
     * 
     * @param player
     *            player swinging
     */
    public void onArmSwing(Player player) {
    }

    /**
     * Called when a player drops an item.
     * 
     * @param player
     *            player who dropped the item
     * @param item
     *            item that was dropped
     * @return true if you don't want the dropped item to be spawned in the
     *         world
     */
    public boolean onItemDrop(Player player, Item item) {
        return false;
    }

    /**
     * Called when a player picks up an item.
     *
     * @param player
     *            player who picked up the item
     * @param item
     *            item that was picked up
     * @return true if you want to leave the item where it was
     */
    public boolean onItemPickUp(Player player, Item item) {
        return false;
    }

    /**
     * Called when either a lava block or a lighter tryes to light something on fire.
     * block status depends on the light source:
     * 1 = lava.
     * 2 = lighter (flint + steel).
     * 3 = spread (dynamic spreading of fire).
     * @param block block that the fire wants to spawn in.
     * @param player player
     * @return true if you dont want the fire to ignite.
     */
    public boolean onIgnite(Block block, Player player) {
        return false;
    }

    /**
     * Called when a dynamite block or a creeper is triggerd.
     * block status depends on explosive compound:
     * 1 = dynamite.
     * 2 = creeper.
     * @param block
     *          dynamite block/creeper location block.
     *
     * @return true if you dont the block to explode.
     */
    public boolean onExplode(Block block) {
        return false;
    }

    /**
     * Called when fluid wants to flow to a certain block.
     * (10 & 11 for lava and 8 & 9 for water)
     * 
     * @param blockFrom
     *              the block where the fluid came from.
     *              (blocktype = fluid type)
     * @param blockTo
     *              the block where fluid wants to flow to.
     *
     *
     * @return true if you dont want the substance to flow.
     */
    public boolean onFlow(Block blockFrom, Block blockTo) {
        return false;
    }

    /**
     * @param mob Mob attempting to spawn.
     * @return true if you dont want mob to spawn.
     */
    public boolean onMobSpawn(Mob mob) {
        return false;
    }

    /**
     * Called when a living object is attacked.
     * tip:
     * Use isMob() and isPlayer() and getPlayer().
     * 
     * @param type 
     *          type of damage dealt.
     * @param attacker
     *          object that is attacking.
     * @param defender
     *          object that is defending.
     * @param amount
     *          amount of damage dealt.
     * 
     * @return
     */
    public boolean onDamage(PluginLoader.DamageType type, BaseEntity attacker, BaseEntity defender, int amount) {
        return false;
    }

    /**
     * Called when a players health changes.
     * @param player
     *              the player which health is changed.
     * @param oldValue
     *              old lives value
     * @param newValue
     *              new lives value
     * @return
     *      return true to stop the change.
     */
    public boolean onHealthChange(Player player, int oldValue, int newValue) {
        return false;
    }

    /**
     * Called whenever a redstone source (wire, switch, torch) changes its
     * current.
     *
     * Standard values for wires are 0 for no current, and 14 for a strong current.
     * Default behaviour for redstone wire is to lower the current by one every
     * block.
     *
     * For other blocks which provide a source of redstone current, the current
     * value will be 1 or 0 for on and off respectively.
     *
     * @param block
     * @param oldLevel the old current
     * @param newLevel the new current
     * @return the new current to use (newLevel to leave as-is)
     */
    public int onRedstoneChange(Block block, int oldLevel, int newLevel) {
        return newLevel;
    }

    /**
     * Called when the game is checking the physics for a certain block.
     * This method is called frequently whenever a nearby block is changed,
     * or if the block has just been placed.
     * Currently the only supported blocks are sand, gravel and portals.
     *
     * @param block Block which requires special physics
     * @param placed True if block was just placed
     * @return true if you do want to stop the default physics for this block
     */
    public boolean onBlockPhysics(Block block, boolean placed) {
        return false;
    }

    /**
     * Called when you place a vehicle.
     * 
     * @param vehicle the vehicle placed
     */
    public void onVehicleCreate(BaseVehicle vehicle) {

    }

    /**
     * Called when vehicle receives damage
     * 
     * @param vehicle
     * @param attacker entity that dealt the damage
     * @param damage
     * @return false to set damage
     */
    public boolean onVehicleDamage(BaseVehicle vehicle, BaseEntity attacker, int damage) {
        return false;
    }

    /**
     * Called when a vehicle changes speed
     * 
     * @param vehicle the vehicle
     */
    public void onVehicleUpdate(BaseVehicle vehicle) {

    }

    /**
     * Called when a collision occurs with a vehicle and an entity.
     * 
     * @param vehicle the vehicle
     * @param collisioner
     * @return false to ignore damage
     */
    public Boolean onVehicleCollision(BaseVehicle vehicle, BaseEntity collisioner) {
        return false;
    }

    /**
     * Called when a vehicle is destroyed
     * 
     * @param vehicle the vehicle
     */
    public void onVehicleDestroyed(BaseVehicle vehicle) {

    }

    /**
     * Called when a player enter or leaves a vehicle
     * 
     * @param vehicle the vehicle
     * @param player the player
     */
    public void onVehicleEnter(BaseVehicle vehicle, HumanEntity player) {

    }
    /**
     * Called when a vehicle changes block
     * @param vehicle the vehicle
     * @param x coordinate x
     * @param y coordinate y
     * @param z coordinate z
     */
    public void onVehiclePositionChange(BaseVehicle vehicle, int x, int y, int z) {

    }

    /**
     * Called when a player uses an item (rightclick with item in hand)
     * @param player the player
     * @param blockPlaced where a block would end up when the item was a bucket
     * @param blockClicked
     * @param item the item being used (in hand)
     * @return true to prevent using the item.
     */
    public boolean onItemUse(Player player, Block blockPlaced, Block blockClicked, Item item) {
        return false;
    }

    /**
     * Called when someone places a block. Return true to prevent the placement.
     * 
     * @param player
     * @param blockPlaced
     * @param blockClicked
     * @param itemInHand
     * @return true if you want to undo the block placement
     */
    public boolean onBlockPlace(Player player, Block blockPlaced, Block blockClicked, Item itemInHand) {
        return false;
    }

    /**
     * Called when someone presses right click aimed at a block.
     * You can intercept this to add your own right click actions
     * to different item types (see itemInHand)
     * 
     * @param player
     * @param blockClicked
     * @param itemInHand
     */
    public void onBlockRightClicked(Player player, Block blockClicked, Item item) {

    }

    /**
     * Called when water or lava tries to populate a block, you can prevent
     * crushing of torches, railways, flowers etc. You can alternatively allow
     * to let normally solid blocks be crushed.
     * 
     * @param currentState the current tristate, once it's set to a non DEFAULT_ACTION it is final.
     * @param liquidBlock the type of the attacking block
     * @param targetBlock the block to be destroyed
     * @return final after a non DEFAULT_ACTION
     */
    public PluginLoader.HookResult onLiquidDestroy( PluginLoader.HookResult currentState, int liquidBlockId, Block targetBlock )  {
        return PluginLoader.HookResult.DEFAULT_ACTION;
    }

    /**
     * Called when an entity (attacker) tries to hurt a player (defender).
     * Returning 'true' prevents all damage, returning 'false' lets the game handle it.
     * Remember that the damage will be lessened by the amount of {@link LivingEntity#getLastDamage()}
     * the defender has.
     * 
     * @param attacker the giver
     * @param defender the taker
     * @param amount of damage the entity tries to do
     * @return
     */
    public boolean onAttack(LivingEntity attacker, LivingEntity defender, Integer amount) {
        return false;
    }

    /**
     * Called when a player attempts to open an inventory; whether it's a
     * workbench, a chest or their own player inventory
     *
     * @param player user who attempted to open the inventory
     * @param inventory the inventory that they are attempting to open
     * @return
     */
    public boolean onOpenInventory(Player player, Inventory inventory) {
        return false;
    }

    /**
     * Called when a sign is shown to a player, most often when they come into
     * range of a sign.
     * 
     * @param player Player who this sign is being shown to
     * @param sign Sign which is being shown to the player
     */
    public void onSignShow(Player player, Sign sign) {
    }

    /**
     * Called when a sign is changed by a player (Usually, when they first place it)
     * 
     * @param player Player who changed the sign
     * @param sign Sign which had changed
     * @return true if you wish to cancel this change
     */
    public boolean onSignChange(Player player, Sign sign) {
        return false;
    }
	
    /**
     * Called when a leaf block is about to decay.
     *
     * @param block The leaf block about to decay
     * @return true if you wish to stop the block from decaying
     */
    public boolean onLeafDecay(Block block) {
        return false;
    }
	
	/**
     * Called when the player places an inventory item, or adds amount to an existing inventory slot.
     *
     * @param player Player who placed their inventory item
     * @param inventory The inventory that had an item placed
     * @param placedItem The item (and amount) that was placed.
     * @param oldSlot The slot that the item was placed in.
     * @return true if you want the placement to be undone; false otherwise
     */
    public boolean onInventoryPlaceItem(Player player, Inventory inventory, Item placedItem, Integer slot) {
        return false;
    }

    /**
     * Called when the player takes an inventory item, whether they selected half or the whole slot.
     *
     * @param player Player who took their inventory item
     * @param inventory The inventory that had an item taken
     * @param takenItem The item (and amount) that was taken
     * @param oldSlot The slot that the item was taken from.
     * @return true if you want the taking to be undone; false otherwise
     */
    public boolean onInventoryTakeItem(Player player, Inventory inventory, Item takenItem, Integer slot) {
        return false;
    }

    /**
     * Called when a slot's contents are swapped with the contents of the cursor.
     *
     * @param player Player who swapped their inventory item
     * @param inventory The inventory that had an item swapped
     * @param slot The slot that had items swapped
     * @param oldItemInSlot The old item in the slot, which is now on the cursor
     * @param newItemInSlot The new item in the slot, which was on the cursor
     * @return true if you want the swap to be undone; false otherwise
     */
    public boolean onInventoryCursorSwap(Player player, Inventory inventory, Integer slot, Item oldItemInSlot, Item newItemInSlot) {
        return false;
    }
}
