import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;

import net.minecraft.server.MinecraftServer;
/**
 * Player.java - Interface for eo so mods don't have to update often.
 * 
 * @author James
 */
public class Player extends HumanEntity {
    private static final Logger log = Logger.getLogger("Minecraft");
    private static final ChatCommands chatCommands = ChatCommands.getInstance();
    private int id = -1;
    private String prefix = "";
    private String[] commands = new String[]{""};
    private ArrayList<String> groups = new ArrayList<String>();
    private String[] ips = new String[]{""};
    private boolean ignoreRestrictions = false;
    private boolean admin = false;
    private boolean canModifyWorld = false;
    private boolean muted = false;
    private Inventory inventory, craftingTable, equipment;
    private List<String> onlyOneUseKits = new ArrayList<String>();

    /**
     * Creates an empty player. Add the player by calling {@link #setUser(es)}
     */
    public Player() {
    }
    
    /**
     * Returns the entity we're wrapping.
     * @return
     */
    public et getEntity() {
        return (et)entity;
    }
    
    /**
     * Returns if the player is still connected
     * @return
     */
    public boolean isConnected() {
        return !getEntity().a.c;
    }

    /**
     * Kicks player with the specified reason
     * 
     * @param reason
     */
    public void kick(String reason) {
        getEntity().a.c(reason);
    }

    /**
     * Sends a message to the player
     * 
     * @param message
     */
    public void sendMessage(String message) {
        getEntity().a.msg(message);
    }

    /**
     * Gives an item to the player
     * 
     * @param item
     */
    public void giveItem(Item item) {
        giveItem(item.getItemId(), item.getAmount());
    }

    public void giveKit(Kit kit) {
        giveKitTo(this, kit);
    }
    
    /**
     * This player gives a kit to the target player.
     * 
     * @param item
     */
    public void giveKitTo(Player target, Kit kit) {
        if (target == null) {
            sendMessage(Colors.Rose + "That user does not exist.");
            return;
        }
        
        if (kit == null) {
            sendMessage(Colors.Rose + "That kit does not exist.");
            return;
        }
        
        if (!isInGroup(kit.Group) && !kit.Group.equals("")) {
            sendMessage(Colors.Rose + "That kit does not exist.");
        } else if (hasUsedKit(kit)) {
            sendMessage(Colors.Rose + "You can only get this kit once per login.");
        } else if (MinecraftServer.b.containsKey(getName() + " " + kit.Name)) {
            sendMessage(Colors.Rose + "You can't get this kit again for a while.");
        } else {
            if (!canIgnoreRestrictions()) {
                if (kit.Delay >= 0) {
                    MinecraftServer.b.put(getName() + " " + kit.Name, kit.Delay);
                } else {
                    flagKitAsUsed(kit);
                }
            }

            log.info(target.getName() + " got a kit!");
            target.sendMessage(Colors.Rose + "Enjoy this kit!");
            for (Map.Entry<String, Integer> entry : kit.IDs.entrySet()) {
                try {
                    int itemId = 0;
                    try {
                        itemId = Integer.parseInt(entry.getKey());
                    } catch (NumberFormatException n) {
                        itemId = etc.getDataSource().getItem(entry.getKey());
                    }

                    target.giveItem(itemId, kit.IDs.get(entry.getKey()));
                } catch (Exception e1) {
                    log.info("Got an exception while giving out a kit (Kit name \"" + kit.Name + "\"). Are you sure all the Ids are numbers?");
                    sendMessage(Colors.Rose + "The server encountered a problem while giving the kit :(");
                }
            }
        }
    }
    
    
    /**
     * Makes player send message.
     * 
     * @param message
     */
    public void chat(String message) {
        message = message.trim();
        
        if(ChatParser.isMessageTooLong(message)) {
            kick("Chat message too long");
            return;
        }
        
        Matcher badChars = ChatParser.scanForBadCharacters(message);
        if (badChars != null) {
            kick("Illegal characters '" + badChars.group() + "' hex: " + Integer.toHexString(message.charAt(badChars.start())) + " in chat");
            return;
        }

        if (ChatParser.isCommand(message)) {
            command(message);
        } else {
            if (isMuted()) {
                sendMessage(Colors.Rose + "You are currently muted.");
                return;
            }
            
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.CHAT, new Object[]{this, message})) {
                return;
            }

            String chat = "<" + getColor() + getName() + Colors.White + "> " + message;
            log.log(Level.INFO, "<" + getName() + "> " + message);
            etc.getServer().messageAll(chat);
        }
    }
    
    public List<String> getAllowedCommands() {
        List<String> allowedCommandTooltips = new ArrayList<String>();
        
        for (Entry<String, BaseCommand> entry : chatCommands.getCommands().entrySet()) {
            if (canUseCommand(entry.getKey())) {
                if (entry.getKey().equals("kit") && !etc.getDataSource().hasKits()) {
                    continue;
                }
                if (entry.getKey().equals("listwarps") && !etc.getDataSource().hasWarps()) {
                    continue;
                }

                allowedCommandTooltips.add(entry.getKey() + " " + entry.getValue().tooltip);
            }
        }
        
        return allowedCommandTooltips;
    }

    public void flagKitAsUsed(Kit k) {
        onlyOneUseKits.add(k.Name);
    }
    
    public boolean hasUsedKit(Kit k) {
        return onlyOneUseKits.contains(k.Name);
    }
    
    public Player findPlayer(String name) {
        Player target = etc.getServer().matchPlayer(name);

        if (target == null) {
            sendMessage(Colors.Rose + "Player does not exist.");
        }
        
        return target;
    }
    
    /**
     * Makes player use command.
     * 
     * @param command
     * 
     */
    public void command(String message) {
        try {
            if (etc.getInstance().isLogging()) {
                log.info("Command used by " + getName() + " " + message);
            }
            
            if (!ChatParser.isCommand(message)) {
                return;
            }
            
            sendMessage("Executing: " + message);            
            String[] parameters = message.split(" ");
            
            // Remove slash and set command to lowercase.
            String command = parameters[0].substring(1);
            command = command.toLowerCase();
            
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMMAND, new Object[]{this, parameters})) {
                return; // No need to continue, commands were parsed.
            }
            
            boolean hasAccess = canUseCommand(command);
            if (!hasAccess && !command.startsWith("#")) {
                if (etc.getInstance().showUnknownCommand()) {
                    sendMessage(Colors.Rose + "Unknown command.");
                }
                return;
            }

            if (hasAccess && chatCommands.parseCommand(command, this, parameters)) {
                // Command parsed successfully
                return;
            }

            if (command.contentEquals("me")) {
                if (isMuted()) {
                    sendMessage(Colors.Rose + "You are currently muted.");
                    return;
                }
                if (parameters.length == 1) {
                    return;
                }
                String paramString2 = "* " + getColor() + getName() + Colors.White + " " + command.substring(command.indexOf(" ")).trim();
                log.info("* " + getName() + " " + command.substring(command.indexOf(" ")).trim());
                etc.getServer().messageAll(paramString2);
            } else if (command.contentEquals("sethome")) {
                // player.k, player.l, player.m
                // x, y, z
                Warp home = new Warp();
                home.Location = getLocation();
                home.Group = ""; // no group neccessary, lol.
                home.Name = getName();
                etc.getInstance().changeHome(home);
                sendMessage(Colors.Rose + "Your home has been set.");
            } else if (command.contentEquals("spawn")) {
                teleportTo(etc.getServer().getSpawnLocation());
            } else if (command.contentEquals("setspawn")) {
                etc.getMCServer().e.m = (int) Math.ceil(getX());
                etc.getMCServer().e.o = (int) Math.ceil(getZ());
                // Too lazy to actually update this considering it's not even
                // used anyways.
                // this.d.e.n = (int) Math.ceil(e.m); //Not that the Y axis
                // really matters since it tries to get the highest point iirc.

                log.info("Spawn position changed.");
                sendMessage(Colors.Rose + "You have set the spawn to your current position.");
            } else if (command.contentEquals("home")) {
                Warp home = null;
                if (parameters.length > 1 && isAdmin()) {
                    home = etc.getDataSource().getHome(parameters[1]);
                } else {
                    home = etc.getDataSource().getHome(getName());
                }

                if (home != null) {
                    teleportTo(home.Location);
                } else if (parameters.length > 1 && isAdmin()) {
                    sendMessage(Colors.Rose + "That player home does not exist");
                } else {
                    teleportTo(etc.getServer().getSpawnLocation());
                }
            } else if (command.contentEquals("warp")) {
                if (parameters.length < 2) {
                    sendMessage(Colors.Rose + "Correct usage is: /warp [warpname]");
                    return;
                }
                Player toWarp = this;
                Warp warp = null;
                if (parameters.length == 3 && canIgnoreRestrictions()) {
                    warp = etc.getDataSource().getWarp(parameters[1]);
                    toWarp = etc.getServer().matchPlayer(parameters[2]);
                } else {
                    warp = etc.getDataSource().getWarp(parameters[1]);
                }
                if (toWarp != null) {
                    if (warp != null) {
                        if (!isInGroup(warp.Group) && !warp.Group.equals("")) {
                            sendMessage(Colors.Rose + "Warp not found.");
                        } else {
                            toWarp.teleportTo(warp.Location);
                            toWarp.sendMessage(Colors.Rose + "Woosh!");
                        }
                    } else {
                        sendMessage(Colors.Rose + "Warp not found");
                    }
                } else {
                    sendMessage(Colors.Rose + "Player not found.");
                }
            } else if (command.contentEquals("listwarps") && etc.getDataSource().hasWarps()) {
                if (parameters.length != 2 && parameters.length != 3) {
                    sendMessage(Colors.Rose + "Available warps: " + Colors.White + etc.getDataSource().getWarpNames(this));
                    return;
                }
            } else if (command.contentEquals("setwarp")) {
                if (parameters.length < 2) {
                    if (canIgnoreRestrictions()) {
                        sendMessage(Colors.Rose + "Correct usage is: /setwarp [warpname] [group]");
                    } else {
                        sendMessage(Colors.Rose + "Correct usage is: /setwarp [warpname]");
                    }
                    return;
                }
                if (parameters[1].contains(":")) {
                    sendMessage("You can't set a warp with \":\" in its name");
                    return;
                }
                Warp warp = new Warp();
                warp.Name = parameters[1];
                warp.Location = getLocation();
                if (parameters.length == 3) {
                    warp.Group = parameters[2];
                } else {
                    warp.Group = "";
                }
                etc.getInstance().setWarp(warp);
                sendMessage(Colors.Rose + "Created warp point " + parameters[1] + ".");
            } else if (command.contentEquals("removewarp")) {
                if (parameters.length < 2) {
                    sendMessage(Colors.Rose + "Correct usage is: /removewarp [warpname]");
                    return;
                }
                Warp warp = etc.getDataSource().getWarp(parameters[1]);
                if (warp != null) {
                    etc.getDataSource().removeWarp(warp);
                    sendMessage(Colors.Blue + "Warp removed.");
                } else {
                    sendMessage(Colors.Rose + "That warp does not exist");
                }
            } else if (command.contentEquals("lighter")) {
                if (MinecraftServer.b.containsKey(getName() + " lighter")) {
                    log.info(getName() + " failed to iron!");
                    sendMessage(Colors.Rose + "You can't create another lighter again so soon");
                } else {
                    if (!canIgnoreRestrictions()) {
                        MinecraftServer.b.put(getName() + " lighter", Integer.valueOf(6000));
                    }
                    log.info(getName() + " created a lighter!");
                    giveItem(259, 1);
                }
            } else if ((command.startsWith("/#")) && (etc.getMCServer().f.g(getName()))) {
                String str = command.substring(2);
                log.info(getName() + " issued server command: " + str);
                etc.getMCServer().a(str, getEntity().a);
            } else if (command.contentEquals("time")) {
                if (parameters.length == 2) {
                    if (parameters[1].equalsIgnoreCase("day")) {
                        etc.getServer().setRelativeTime(0);
                    } else if (parameters[1].equalsIgnoreCase("night")) {
                        etc.getServer().setRelativeTime(13000);
                    } else if (parameters[1].equalsIgnoreCase("check")) {
                        sendMessage(Colors.Rose + "The time is " + etc.getServer().getRelativeTime() + "! (RAW: " + etc.getServer().getTime() + ")");
                    } else {
                        try {
                            etc.getServer().setRelativeTime(Long.parseLong(parameters[1]));
                        } catch (NumberFormatException ex) {
                            sendMessage(Colors.Rose + "Please enter numbers, not letters.");
                        }
                    }
                } else if (parameters.length == 3) {
                    if (parameters[1].equalsIgnoreCase("raw")) {
                        try {
                            etc.getServer().setTime(Long.parseLong(parameters[2]));
                        } catch (NumberFormatException ex) {
                            sendMessage(Colors.Rose + "Please enter numbers, not letters.");
                        }
                    }
                } else {
                    sendMessage(Colors.Rose + "Correct usage is: /time [time|'day|night|check|raw'] (rawtime)");
                    return;
                }
            } else if (command.contentEquals("getpos")) {
                sendMessage("Pos X: " + getX() + " Y: " + getY() + " Z: " + getZ());
                sendMessage("Rotation: " + getRotation() + " Pitch: " + getPitch());

                double degreeRotation = ((getRotation() - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }
                sendMessage("Compass: " + etc.getCompassPointForDirection(degreeRotation) + " (" + (Math.round(degreeRotation * 10) / 10.0) + ")");
            } else if (command.contentEquals("listplugins")) {
                sendMessage(Colors.Rose + "Plugins" + Colors.White + ": " + etc.getLoader().getPluginList());
            } else if (command.contentEquals("reloadplugin")) {
                if (parameters.length < 2) {
                    sendMessage(Colors.Rose + "Correct usage is: /reloadplugin [plugin]");
                    return;
                }

                etc.getLoader().reloadPlugin(parameters[1]);
                sendMessage(Colors.Rose + "Plugin reloaded.");
            } else if (command.contentEquals("enableplugin")) {
                if (parameters.length < 2) {
                    sendMessage(Colors.Rose + "Correct usage is: /enableplugin [plugin]");
                    return;
                }

                etc.getLoader().enablePlugin(parameters[1]);
                sendMessage(Colors.Rose + "Plugin enabled.");
            } else if (command.contentEquals("disableplugin")) {
                if (parameters.length < 2) {
                    sendMessage(Colors.Rose + "Correct usage is: /disableplugin [plugin]");
                    return;
                }

                etc.getLoader().disablePlugin(parameters[1]);
                sendMessage(Colors.Rose + "Plugin disabled.");
            } else if (command.contentEquals("compass")) {
                double degreeRotation = ((getRotation() - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }

                sendMessage(Colors.Rose + "Compass: " + etc.getCompassPointForDirection(degreeRotation));
            } else if (command.contentEquals("motd")) {
                for (String str : etc.getInstance().getMotd()) {
                    sendMessage(str);
                }
            } else if (command.contentEquals("spawnmob")) {
                if (parameters.length == 1) {
                    sendMessage(Colors.Rose + "Correct usage is: /spawnmob [name] <amount>");
                    return;
                }
                if (!Mob.isValid(parameters[1])) {
                    sendMessage(Colors.Rose + "Invalid mob. Name has to start with a capital like so: Pig");
                    return;
                }

                if (parameters.length == 2) {
                    Mob mob = new Mob(parameters[1], getLocation());
                    mob.spawn();
                } else if (parameters.length == 3) {
                    try {
                        int mobnumber = Integer.parseInt(parameters[2]);
                        for (int i = 0; i < mobnumber; i++) {
                            Mob mob = new Mob(parameters[1], getLocation());
                            mob.spawn();
                        }
                    } catch (NumberFormatException nfe) {
                        if (!Mob.isValid(parameters[2])) {
                            sendMessage(Colors.Rose + "Invalid mob name or number of mobs.");
                            sendMessage(Colors.Rose + "Mob names have to start with a capital like so: Pig");
                        } else {
                            Mob mob = new Mob(parameters[1], getLocation());
                            mob.spawn(new Mob(parameters[2]));
                        }
                    }
                } else if (parameters.length == 4) {
                    try {
                        int mobnumber = Integer.parseInt(parameters[3]);
                        if (!Mob.isValid(parameters[2])) {
                            sendMessage(Colors.Rose + "Invalid rider. Name has to start with a capital like so: Pig");
                        } else {
                            for (int i = 0; i < mobnumber; i++) {
                                Mob mob = new Mob(parameters[1], getLocation());
                                mob.spawn(new Mob(parameters[2]));
                            }
                        }
                    } catch (NumberFormatException nfe) {
                        sendMessage(Colors.Rose + "Invalid number of mobs.");
                    }
                }
            } else if (command.contentEquals("clearinventory")) {
                Player target = this;
                if (parameters.length >= 2 && isAdmin()) {
                    target = etc.getServer().matchPlayer(parameters[1]);
                }
                if (target != null) {
                    Inventory inv = target.getInventory();
                    inv.clearContents();
                    inv = target.getCraftingTable();
                    inv.clearContents();
                    inv = target.getEquipment();
                    inv.clearContents();
                    inv.updateInventory();
                    if (!target.getName().equals(getName())) {
                        sendMessage(Colors.Rose + "Cleared " + target.getName() + "'s inventory.");
                    }
                } else {
                    sendMessage(Colors.Rose + "Target not found");
                }
            } else if (parameters[0].equals("/mspawn")) {
                if (parameters.length != 2) {
                    sendMessage(Colors.Rose + "You must specify what to change the mob spawner to.");
                    return;
                }
                if (!Mob.isValid(parameters[1])) {
                    sendMessage(Colors.Rose + "Invalid mob specified.");
                    return;
                }

                HitBlox hb = new HitBlox(this);
                Block block = hb.getTargetBlock();
                if (block.getType() == 52) { // mob spawner
                    MobSpawner ms = (MobSpawner) etc.getServer().getComplexBlock(block.getX(), block.getY(), block.getZ());
                    if (ms != null)
                        ms.setSpawn(parameters[1]);
                } else {
                    sendMessage(Colors.Rose + "You are not targeting a mob spawner.");
                }
            } else if (command.contentEquals("version")) {
                if (!etc.getInstance().getTainted())
                    sendMessage(Colors.Gold + "Hey0 Server Mod Build " + etc.getInstance().getVersion());
                else {
                    sendMessage(Colors.Gold + "Unofficial hMod Build " + etc.getInstance().getVersionStr());
                }
            } else {
                log.info(getName() + " tried command " + command);
                if (etc.getInstance().showUnknownCommand()) {
                    sendMessage(Colors.Rose + "Unknown command");
                }
            }
        } catch (Throwable ex) { // Might as well try and catch big exceptions
            // before the server crashes from a stack
            // overflow or something
            log.log(Level.SEVERE, "Exception in command handler (Report this to hey0 unless you did something dumb like enter letters as numbers):", ex);
            if (isAdmin()) {
                sendMessage(Colors.Rose + "Exception occured. Check the server for more info.");
            }
        }
    }

    /**
     * Gives an item to the player
     * 
     * @param itemId
     * @param amount
     */
    public void giveItem(int itemId, int amount) {
        inventory.giveItem(itemId, amount);
        inventory.updateInventory();
    }

    /**
     * Gives the player this item by dropping it in front of them
     * 
     * @param item
     */
    public void giveItemDrop(Item item) {
        giveItemDrop(item.getItemId(), item.getAmount());
    }

    /**
     * Gives the player this item by dropping it in front of them
     * 
     * @param itemId
     * @param amount
     */
    public void giveItemDrop(int itemId, int amount) {
        et player = getEntity();
        if (amount == -1) {
            player.a(new hn(itemId, 255));
        } else {
            int temp = amount;
            do {
                if (temp - 64 >= 64) {
                    player.a(new hn(itemId, 64));
                } else {
                    player.a(new hn(itemId, temp));
                }
                temp -= 64;
            } while (temp > 0);
        }
    }

    /**
     * Returns true if this player can use the specified command
     * 
     * @param command
     * @return
     */
    public boolean canUseCommand(String command) {
        command = "/" + command;
        
        for (String str : commands) {
            if (str.equalsIgnoreCase(command)) {
                return true;
            }
        }

        for (String str : groups) {
            Group g = etc.getDataSource().getGroup(str);
            if (g != null) {
                if (recursiveUseCommand(g, command)) {
                    return true;
                }
            }
        }

        if (hasNoGroups()) {
            Group def = etc.getInstance().getDefaultGroup();
            if (def != null) {
                if (recursiveUseCommand(def, command)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean recursiveUseCommand(Group g, String command) {
        for (String str : g.Commands) {
            if (str.equalsIgnoreCase(command) || str.equals("*")) {
                return true;
            }
        }

        if (g.InheritedGroups != null) {
            for (String str : g.InheritedGroups) {
                Group g2 = etc.getDataSource().getGroup(str);
                if (g2 != null) {
                    if (recursiveUseCommand(g2, command)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if this player is in the specified group
     * 
     * @param group
     * @return
     */
    public boolean isInGroup(String group) {
        if (group != null) {
            if (etc.getInstance().getDefaultGroup() != null) {
                if (group.equalsIgnoreCase(etc.getInstance().getDefaultGroup().Name)) {
                    return true;
                }
            }
        }
        for (String str : groups) {
            if (recursiveUserInGroup(etc.getDataSource().getGroup(str), group)) {
                return true;
            }
        }
        return false;
    }

    private boolean recursiveUserInGroup(Group g, String group) {
        if (g == null || group == null) {
            return false;
        }

        if (g.Name.equalsIgnoreCase(group)) {
            return true;
        }

        if (g.InheritedGroups != null) {
            for (String str : g.InheritedGroups) {
                if (g.Name.equalsIgnoreCase(str)) {
                    return true;
                }

                Group g2 = etc.getDataSource().getGroup(str);
                if (g2 != null) {
                    if (recursiveUserInGroup(g2, group)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if this player has control over the other player
     * 
     * @param player
     * @return true if player has control
     */
    public boolean hasControlOver(Player player) {
        boolean isInGroup = false;

        if (player.hasNoGroups()) {
            return true;
        }
        for (String str : player.getGroups()) {
            if (isInGroup(str)) {
                isInGroup = true;
            } else {
                continue;
            }
            break;
        }

        return isInGroup;
    }

    /**
     * Returns the player's current location
     * 
     * @return
     */
    public Location getLocation() {
        Location loc = new Location();
        loc.x = getX();
        loc.y = getY();
        loc.z = getZ();
        loc.rotX = getRotation();
        loc.rotY = getPitch();
        return loc;
    }

    /**
     * Returns the IP of this player
     * 
     * @return
     */
    public String getIP() {
        return getEntity().a.b.b().toString().split(":")[0].substring(1);
    }

    /**
     * Returns true if this player is an admin.
     * 
     * @return
     */
    public boolean isAdmin() {
        if (admin) {
            return true;
        }

        for (String str : groups) {
            Group group = etc.getDataSource().getGroup(str);
            if (group != null) {
                if (group.Administrator) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Don't use this! Use isAdmin
     * 
     * @return
     */
    public boolean getAdmin() {
        return admin;
    }

    /**
     * Sets whether or not this player is an administrator
     * 
     * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Returns false if this player can not modify terrain, edit chests, etc.
     * 
     * @return
     */
    public boolean canBuild() {
        if (canModifyWorld) {
            return true;
        }

        for (String str : groups) {
            Group group = etc.getDataSource().getGroup(str);
            if (group != null) {
                if (group.CanModifyWorld) {
                    return true;
                }
            }
        }

        if (hasNoGroups()) {
            if (etc.getInstance().getDefaultGroup().CanModifyWorld) {
                return true;
            }
        }

        return false;
    }

    /**
     * Don't use this, use canBuild()
     * 
     * @return
     */
    public boolean canModifyWorld() {
        return canModifyWorld;
    }

    /**
     * Sets whether or not this player can modify the world terrain
     * 
     * @param canModifyWorld
     */
    public void setCanModifyWorld(boolean canModifyWorld) {
        this.canModifyWorld = canModifyWorld;
    }

    /**
     * Set allowed commands
     * 
     * @return
     */
    public String[] getCommands() {
        return commands;
    }

    /**
     * Sets this player's allowed commands
     * 
     * @param commands
     */
    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    /**
     * Returns this player's groups
     * 
     * @return
     */
    public String[] getGroups() {
        String[] strGroups = new String[groups.size()];
        groups.toArray(strGroups);
        return strGroups;
    }

    /**
     * Sets this player's groups
     * 
     * @param groups
     */
    public void setGroups(String[] groups) {
        this.groups.clear();
        for (String s : groups) {
            if (s.length() > 0) {
                this.groups.add(s);
            }
        }
    }

    /**
     * Adds the player to the specified group
     * 
     * @param group group to add player to
     */
    public void addGroup(String group) {
        this.groups.add(group);
    }

    /**
     * Removes specified group from list of groups
     * @param group group to remove
     */
    public void removeGroup(String group) {
        this.groups.remove(group);
    }

    /**
     * Returns the sql ID.
     * 
     * @return
     */
    public int getSqlId() {
        return id;
    }

    /**
     * Sets the sql ID. Don't touch this.
     * 
     * @param id
     */
    public void setSqlId(int id) {
        this.id = id;
    }

    /**
     * If the user can ignore restrictions this will return true. Things like
     * item amounts and such are unlimited, etc.
     * 
     * @return
     */
    public boolean canIgnoreRestrictions() {
        if (admin || ignoreRestrictions) {
            return true;
        }

        for (String str : groups) {
            Group group = etc.getDataSource().getGroup(str);
            if (group != null) {
                if (group.Administrator || group.IgnoreRestrictions) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Don't use. Use canIgnoreRestrictions()
     * 
     * @return
     */
    public boolean ignoreRestrictions() {
        return ignoreRestrictions;
    }

    /**
     * Sets ignore restrictions
     * 
     * @param ignoreRestrictions
     */
    public void setIgnoreRestrictions(boolean ignoreRestrictions) {
        this.ignoreRestrictions = ignoreRestrictions;
    }

    /**
     * Returns allowed IPs
     * 
     * @return
     */
    public String[] getIps() {
        return ips;
    }

    /**
     * Sets allowed IPs
     * 
     * @param ips
     */
    public void setIps(String[] ips) {
        this.ips = ips;
    }

    /**
     * Returns the correct color/prefix for this player
     * 
     * @return
     */
    public String getColor() {
        if (prefix != null) {
            if (!prefix.equals("")) {
                return "§" + prefix;
            }
        }
        if (groups.size() > 0) {
            Group group = etc.getDataSource().getGroup(groups.get(0));
            if (group != null) {
                return "§" + group.Prefix;
            }
        }
        Group def = etc.getInstance().getDefaultGroup();
        return def != null ? "§" + def.Prefix : "";
    }

    /**
     * Returns the prefix. NOTE: Don't use this, use getColor() instead.
     * 
     * @return
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the prefix
     * 
     * @param prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets the actual user class.
     * 
     * @return
     */
    public et getUser() {
        return getEntity();
    }

    /**
     * Sets the user. Don't use this.
     * 
     * @param er
     */
    public void setUser(et player) {
        this.entity = player;
        this.inventory = new Inventory(this, Inventory.Type.Inventory);
        this.craftingTable = new Inventory(this, Inventory.Type.CraftingTable);
        this.equipment = new Inventory(this, Inventory.Type.Equipment);
    }

    public void teleportTo(double x, double y, double z, float rotation, float pitch) {
        et player = getEntity();
        
        // If player is in vehicle - eject them before they are teleported.
        if (player.k != null) {
            player.e(player.k);
        }
        player.a.a(x, y, z, rotation, pitch);
    }

    /**
     * Returns true if the player is muted
     * 
     * @return
     */
    public boolean isMuted() {
        return muted;
    }

    /**
     * Toggles mute
     * 
     * @return
     */
    public boolean toggleMute() {
        muted = !muted;
        return muted;
    }

    /**
     * Checks to see if this player is in any groups
     * 
     * @return true if this player is in any group
     */
    public boolean hasNoGroups() {
        if (groups.isEmpty()) {
            return true;
        }
        if (groups.size() == 1) {
            return groups.get(0).equals("");
        }
        return false;
    }

    /**
     * Returns item id in player's hand
     * 
     * @return
     */
    public int getItemInHand() {
        return getEntity().a.getItemInHand();
    }

    /**
     * Returns this player's inventory
     * 
     * @return inventory
     */
    public Inventory getInventory() {
        return inventory;
    }

    /**
     * Returns this player's crafting table (2x2)
     * 
     * @return inventory
     */
    public Inventory getCraftingTable() {
        return craftingTable;
    }

    /**
     * Returns this player's equipment
     * 
     * @return inventory
     */
    public Inventory getEquipment() {
        return equipment;
    }

    /**
     * Returns a String representation of this Player
     * 
     * @return String representation of this Player
     */
    @Override
    public String toString() {
        return String.format("Player[id=%d, name=%s]", id, getName());
    }

    /**
     * Tests the given object to see if it equals this object
     * 
     * @param obj the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Player other = (Player) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    /**
     * Returns a unique hashcode for this Player
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 71 * hash + this.id;
        return hash;
    }
}
