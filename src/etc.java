/* Just a general storage for all my crap */

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.server.*;

/**
 * etc.java - My catch-all class for a bunch of shit. If there's something you
 * need it's probably in here.
 * @author James
 */
public class etc {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static volatile etc instance;
    private static MinecraftServer server;
    private ArrayList<String> muted = new ArrayList<String>();
    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    /**
     *
     */
    public String usersLoc = "users.txt", kitsLoc = "kits.txt", homeLoc = "homes.txt", warpLoc = "warps.txt", itemLoc = "items.txt", groupLoc = "groups.txt", commandsLoc = "commands.txt";
    /**
     *
     */
    /**
     *
     */
    public String whitelistLoc = "whitelist.txt", reservelistLoc = "reservelist.txt";
    /**
     *
     */
    public String whitelistMessage = "Not on whitelist.";
    /**
     *
     */
    public String[] allowedItems = null;
    /**
     *
     */
    public String[] disallowedItems = null;
    /**
     *
     */
    public String[] itemSpawnBlacklist = null;
    /**
     *
     */
    public String[] motd = null;
    /**
     *
     */
    public boolean saveHomes = true;
    /**
     *
     */
    public boolean firstLoad = true;
    /**
     *
     */
    public boolean whitelistEnabled = false;
    /**
     *
     */
    public int playerLimit = 20;
    /**
     *
     */
    public int spawnProtectionSize = 16;
    /**
     *
     */
    public long sleepTime = 30000;
    /**
     *
     */
    public long saveInterval = 1800000;
    /**
     *
     */
    public LinkedHashMap<String, String> commands = new LinkedHashMap<String, String>();
    private String dataSourceType;
    private ReloadThread reloadThread;
    private SaveAllThread saveThread;
    private DataSource dataSource;
    private PropertiesFile properties;
    private PluginLoader loader;
    private boolean logging = false;

    private etc() {
        commands.put("/help", "[Page] - Shows a list of commands. 7 per page.");
        commands.put("/playerlist", "- Shows a list of players");
        commands.put("/reload", "- Reloads config");
        commands.put("/listbans", "<IP or bans> - Gives a list of bans");
        commands.put("/banip", "[Player] <Reason> - Bans the player's IP");
        commands.put("/unbanip", "[IP] - Unbans the IP");
        commands.put("/ban", "[Player] <Reason> - Bans the player");
        commands.put("/unban", "[Player] - Unbans the player");
        commands.put("/mute", "[Player] - Toggles mute on player.");
        commands.put("/tp", "[Player] - Teleports to player. Credits to Zet from SA");
        commands.put("/tphere", "[Player] - Teleports the player to you");
        commands.put("/kick", "[Player] <Reason> - Kicks player");
        commands.put("/item", "[ID] [Amount] <Player> - Gives items");
        commands.put("/kit", "[Kit] - Gives a kit. To get a list of kits type /kit");
        commands.put("/listwarps", "- Gives a list of available warps");
        commands.put("/home", "- Teleports you home");
        commands.put("/sethome", "- Sets your home");
        commands.put("/setspawn", "- Sets the spawn point to your position.");
        commands.put("/me", "[Message] - * hey0 says hi!");
        commands.put("/msg", "[Player] [Message] - Sends a message to player");
        commands.put("/spawn", "- Teleports you to spawn");
        commands.put("/warp", "[Warp] - Warps to the specified warp.");
        commands.put("/setwarp", "[Warp] - Sets the warp to your current position.");
        commands.put("/removewarp", "[Warp] - Removes the specified warp.");
        commands.put("/getpos", "- Displays your current position.");
        commands.put("/compass", "- Gives you a compass reading.");
        commands.put("/time", "[Time|day|night] - Changes time");
        commands.put("/lighter", "- Gives you a lighter for lighting furnaces");
        commands.put("/motd", "- Displays the MOTD");
        commands.put("/modify", "[player] [key] [value] - Type /modify for more info");
        commands.put("/whitelist", "[operation (add or remove)] [player]");
        commands.put("/reservelist", "[operation (add or remove)] [player]");

        load();
    }

    /**
     * Loads or reloads the mod
     */
    public final void load() {
        if (properties == null) {
            properties = new PropertiesFile("server.properties");
        } else {
            properties.load();
        }

        try {
            dataSourceType = properties.getString("data-source", "flatfile");

            allowedItems = properties.getString("alloweditems", "").split(",");
            disallowedItems = properties.getString("disalloweditems", "").split(",");
            itemSpawnBlacklist = properties.getString("itemspawnblacklist", "").split(",");
            motd = properties.getString("motd", "Type /help for a list of commands.").split("@");
            playerLimit = properties.getInt("max-players", 20);
            saveHomes = properties.getBoolean("save-homes", true);
            whitelistEnabled = properties.getBoolean("whitelist", false);
            whitelistMessage = properties.getString("whitelist-message", "Not on whitelist.");
            if (dataSourceType.equalsIgnoreCase("flatfile")) {
                usersLoc = properties.getString("admintxtlocation", "users.txt");
                kitsLoc = properties.getString("kitstxtlocation", "kits.txt");
                homeLoc = properties.getString("homelocation", "homes.txt");
                warpLoc = properties.getString("warplocation", "warps.txt");
                itemLoc = properties.getString("itemstxtlocation", "items.txt");
                groupLoc = properties.getString("group-txt-location", "groups.txt");
                whitelistLoc = properties.getString("whitelist-txt-location", "whitelist.txt");
                reservelistLoc = properties.getString("reservelist-txt-location", "reservelist.txt");
            }
            spawnProtectionSize = properties.getInt("spawn-protection-size", 16);
            sleepTime = properties.getLong("reload-interval", 30000);
            saveInterval = properties.getLong("save-interval", 1800000);
            logging = properties.getBoolean("logging", false);
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception while reading from server.properties", e);
            // Just in case...
            disallowedItems = new String[]{""};
            allowedItems = new String[]{""};
            itemSpawnBlacklist = new String[]{""};
            motd = new String[]{"Type /help for a list of commands."};
        }
    }

    /**
     * Loads or reloads the data source
     */
    public void loadData() {
        if (dataSourceType.equalsIgnoreCase("flatfile") && dataSource == null) {
            dataSource = new FlatFileSource();
        } else if (dataSourceType.equalsIgnoreCase("mysql") && dataSource == null) {
            dataSource = new MySQLSource();
        }

        dataSource.initialize();
    }

    /**
     * Returns the instance
     * @return
     */
    public static etc getInstance() {
        if (instance == null) {
            instance = new etc();
        }

        return instance;
    }

    /**
     * Sets the server to be used.
     * @param s
     */
    public static void setServer(MinecraftServer s) {
        server = s;
    }

    /**
     * Returns the minecraft server
     * @return
     */
    public static MinecraftServer getMCServer() {
        return server;
    }

    /**
     * Returns the minecraft server interface
     * @return
     */
    public static Server getServer() {
        return getInstance().getLoader().getServer();
    }

    /**
     * Returns the plugin loader
     * @return
     */
    public PluginLoader getLoader() {
        if (loader == null) {
            loader = new PluginLoader(server);
            loader.load();
        }

        return loader;
    }

    /**
     * Starts the save and reload thread
     * @param paramMinecraftServer
     */
    public void startThreads(MinecraftServer paramMinecraftServer) {
        if (saveInterval > 0 && saveThread == null) {
            saveThread = new SaveAllThread(paramMinecraftServer, saveInterval);
            saveThread.start();
        }

        if (sleepTime > 0 && reloadThread == null) {
            reloadThread = new ReloadThread(sleepTime);
            reloadThread.start();
        }
    }

    /**
     * Checks to see if specified user is in the specified group
     * @param player
     * @param group
     * @return
     */
    public boolean isUserInGroup(Player player, String group) {
        return isUserInGroup(player.getName(), group);
    }

    /**
     * Checks to see if specified user is in the specified group
     * @param e
     * @param group
     * @return
     */
    public boolean isUserInGroup(ea e, String group) {
        return isUserInGroup(e.aq, group);
    }

    /**
     * Checks to see if specified user is in the specified group
     * @param name
     * @param group
     * @return
     */
    public boolean isUserInGroup(String name, String group) {
        if (group != null) {
            if (getDefaultGroup() != null) {
                if (group.equalsIgnoreCase(getDefaultGroup().Name)) {
                    return true;
                }
            } else {
                log.info("There's no default group.");
            }
        }
        User user = getUser(name);
        if (user != null) {
            for (String str : user.Groups) {
                if (recursiveUserInGroup(dataSource.getGroup(str), group)) {
                    return true;
                }
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

                Group g2 = dataSource.getGroup(str);
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
     * Returns user
     * @param name
     * @return user
     */
    public User getUser(String name) {
        return dataSource.getUser(name);
    }

    /**
     * Returns the default group
     * @return default group
     */
    public Group getDefaultGroup() {
        Group group = dataSource.getDefaultGroup();
        if (group == null)
            log.log(Level.SEVERE, "No default group! Expect lots of errors!");
        return group;
    }

    /**
     * The user's color or prefix
     * @param name
     * @return color/prefix
     */
    public String getUserColor(String name) {
        User user = getUser(name);
        if (user != null) {
            if (user.Prefix != null) {
                if (!user.Prefix.equals("")) {
                    return "§" + user.Prefix;
                }
            }

            Group group = dataSource.getGroup(user.Groups[0]);
            if (group != null) {
                return "§" + group.Prefix;
            }
        }
        Group def = getDefaultGroup();
        return def != null ? "§" + def.Prefix : "";
    }

    /**
     * Returns true if the player can use the specified command
     * @param name
     * @param command
     * @return
     */
    public boolean canUseCommand(String name, String command) {
        User user = getUser(name);
        //holy motherfuck
        if (user != null) {
            for (String str : user.Commands) {
                if (str.equalsIgnoreCase(command)) {
                    return true;
                }
            }

            for (String str : user.Groups) {
                Group g = dataSource.getGroup(str);
                if (g != null) {
                    if (recursiveUseCommand(g, command)) {
                        return true;
                    }
                }
            }
        }

        Group def = getDefaultGroup();
        if (def != null) {
            if (recursiveUseCommand(def, command)) {
                return true;
            }
        } else {
            log.info("No default group.");
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
                Group g2 = dataSource.getGroup(str);
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
     * Returns true if the player's an administrator
     * @param player
     * @return
     */
    public boolean isAdmin(ea player) {
        return isAdmin(player.aq);
    }

    /**
     * Returns true if the player's an administrator
     * @param player
     * @return
     */
    public boolean isAdmin(String player) {
        User user = getUser(player);
        if (user == null) {
            return false;
        }
        if (user.Administrator) {
            return true;
        }

        for (String str : user.Groups) {
            Group group = dataSource.getGroup(str);
            if (group != null) {
                if (group.Administrator) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns true if the player can ignore restrictions
     * @param player
     * @return
     */
    public boolean canIgnoreRestrictions(ea player) {
        return canIgnoreRestrictions(player.aq);

    }

    /**
     * Returns true if the player can ignore restrictions
     * @param player
     * @return
     */
    public boolean canIgnoreRestrictions(String player) {
        User user = getUser(player);
        if (user == null) {
            return false;
        }
        if (user.Administrator || user.IgnoreRestrictions) {
            return true;
        }

        for (String str : user.Groups) {
            Group group = dataSource.getGroup(str);
            if (group != null) {
                if (group.Administrator || group.IgnoreRestrictions) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Returns false if the player can not build and can not modify chests or
     * furnaces
     * @param player
     * @return
     */
    public boolean canBuild(ea player) {
        User user = getUser(player.aq);

        if (user == null) {
            return getDefaultGroup().CanModifyWorld;
        }

        if (!user.CanModifyWorld) {
            return false;
        }

        for (String str : user.Groups) {
            Group group = dataSource.getGroup(str);
            if (group != null) {
                if (!group.CanModifyWorld) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Returns true if the player is muted
     * @param e
     * @return
     */
    public boolean isMuted(ea e) {
        return muted.contains(e.aq);
    }

    /**
     * Toggles mute for specified player
     * @param e
     * @return
     */
    public boolean toggleMute(ea e) {
        if (muted.contains(e.aq)) {
            muted.remove(e.aq);
        } else {
            muted.add(e.aq);
        }
        return muted.contains(e.aq);
    }

    /**
     * Adds or modifies the home.
     * @param home
     */
    public void changeHome(Warp home) {
        if (dataSource.getHome(home.Name) == null) {
            dataSource.addHome(home);
        } else {
            dataSource.changeHome(home);
        }
    }

    /**
     * Adds or modifies the warp
     * @param warp
     */
    public void setWarp(Warp warp) {
        if (dataSource.getWarp(warp.Name) == null) {
            dataSource.addWarp(warp);
        } else {
            dataSource.changeWarp(warp);
        }
    }

    /**
     * Returns true if the item is on the blacklist
     * @param id
     * @return
     */
    public boolean isOnItemBlacklist(int id) {
        for (String str : itemSpawnBlacklist) {
            if (Integer.toString(id).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the data source
     * @return
     */
    public DataSource getDataSource() {
        return dataSource;
    }

    /**
     * Returns true if we're logging commands and such
     * @return
     */
    public boolean isLogging() {
        return logging;
    }

    /**
     * Adds command to the /help list
     * @param command
     * @param description
     */
    public void addCommand(String command, String description) {
        commands.put(command, description);
    }

    /**
     * Removes command from /help list
     * @param command
     */
    public void removeCommand(String command) {
        commands.remove(command);
    }

    /**
     * Toggles the whitelist (doesn't persist)
     * @return
     */
    public boolean toggleWhitelist() {
        whitelistEnabled = !whitelistEnabled;
        return whitelistEnabled;
    }

    /**
     * Parses a console command
     * @param command
     * @param server
     * @return
     */
    public boolean parseConsoleCommand(String command, MinecraftServer server) {
        String[] split = command.split(" ");
        boolean dontParseRegular = true;
        if (split[0].equalsIgnoreCase("help") || split[0].equalsIgnoreCase("mod-help")) {
            if (split[0].equalsIgnoreCase("help")) {
                dontParseRegular = false;
            }
            log.info("Server mod help:");
            log.info("help          Displays this mod's and server's help");
            log.info("mod-help      Displays this mod's help");
            log.info("reload        Reloads the config");
            log.info("modify        Type modify for more info");
            log.info("whitelist     Type whitelist for more info");
            log.info("reservelist   Type reservelist for more info");
        } else if (split[0].equalsIgnoreCase("reload")) {
            load();
            loadData();
            log.info("Reloaded mod");
        } else if (split[0].equalsIgnoreCase("modify")) {
            if (split.length < 4) {
                log.info("Usage is: /modify [player] [key] [value]");
                log.info("Keys:");
                log.info("prefix: only the letter the color represents");
                log.info("commands: list seperated by comma");
                log.info("groups: list seperated by comma");
                log.info("ignoresrestrictions: true or false");
                log.info("admin: true or false");
                log.info("modworld: true or false");
                return true;
            }

            ea player = match(split[1], server);

            if (player == null) {
                log.info("Player does not exist.");
                return true;
            }

            String key = split[2];
            String value = split[3];
            User user = etc.getInstance().getUser(split[1]);
            boolean newUser = false;

            if (user == null) {
                if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                    log.info("When adding a new user, set their group(s) first.");
                    return true;
                }
                log.info("Adding new user.");
                newUser = true;
                user = new User();
                user.Name = split[1];
                user.Administrator = false;
                user.CanModifyWorld = true;
                user.IgnoreRestrictions = false;
                user.Commands = new String[]{""};
                user.Prefix = "";
            }

            if (key.equalsIgnoreCase("prefix") || key.equalsIgnoreCase("p")) {
                user.Prefix = "§" + value;
            } else if (key.equalsIgnoreCase("commands") || key.equalsIgnoreCase("c")) {
                user.Commands = value.split(",");
            } else if (key.equalsIgnoreCase("groups") || key.equalsIgnoreCase("g")) {
                user.Groups = value.split(",");
            } else if (key.equalsIgnoreCase("ignoresrestrictions") || key.equalsIgnoreCase("ir")) {
                user.IgnoreRestrictions = value.equalsIgnoreCase("true") || value.equals("1");
            } else if (key.equalsIgnoreCase("admin") || key.equalsIgnoreCase("a")) {
                user.Administrator = value.equalsIgnoreCase("true") || value.equals("1");
            } else if (key.equalsIgnoreCase("modworld") || key.equalsIgnoreCase("mw")) {
                user.CanModifyWorld = value.equalsIgnoreCase("true") || value.equals("1");
            }

            if (newUser) {
                dataSource.addUser(user);
            } else {
                dataSource.modifyUser(user);
            }
            log.info("Modified user.");
        } else if (split[0].equalsIgnoreCase("whitelist")) {
            if (split.length < 2) {
                log.info("whitelist [operation (toggle, add or remove)] [player]");
                return true;
            }

            if (split[1].equalsIgnoreCase("toggle")) {
                log.info(toggleWhitelist() ? "Whitelist enabled" : "Whitelist disabled");
            } else if(split.length == 3) {
                if (split[1].equalsIgnoreCase("add")) {
                    dataSource.addToWhitelist(split[2]);
                    log.info(split[2] + " added to whitelist");
                } else if (split[1].equalsIgnoreCase("remove")) {
                    dataSource.removeFromWhitelist(split[2]);
                    log.info(split[2] + " removed from whitelist");
                } else {
                    log.info("Invalid operation.");
                }
            } else {
                log.info("Invalid operation.");
            }
        } else if (split[0].equalsIgnoreCase("reservelist")) {
            if (split.length != 3) {
                log.info("reservelist [operation (add or remove)] [player]");
                return true;
            }

            if (split[1].equalsIgnoreCase("add")) {
                dataSource.addToReserveList(split[2]);
                log.info(split[2] + " added to reservelist");
            } else if (split[1].equalsIgnoreCase("remove")) {
                dataSource.removeFromReserveList(split[2]);
                log.info(split[2] + " removed from reservelist");
            } else {
                log.info("Invalid operation.");
            }
        } else if (split[0].equalsIgnoreCase("plugin")) {
            if (split.length < 2 || split[1] == "help") {
                log.info("usage: plugin [command] ([option], ...)");
                log.info("Commands:");
                log.info("load [name]         Loads named plugin");
                log.info("unload [name]       Unloads named plugin");
                log.info("reload [name]       Reloads named plugin");
                log.info("list                List currently loaded plugins");
                return true;
            }
            if (split[1].equalsIgnoreCase("load")) {
                if (split.length < 3 || split[2] == "help") {
                    log.info("usage: plugin load [name]");
                    log.info("Loads the named plugin, assuming it can be found.");
                    return true;
                }
                getLoader().loadPlugin(split[2]);
            } else if (split[1].equalsIgnoreCase("unload")) {
                if (split.length < 3 || split[2] == "help") {
                    log.info("usage: plugin unload [name]");
                    log.info("Unloads the named plugin, assuming it is currently loaded.");
                    log.info("NOTE: This currently doesn't work right, but will in the very near future!");
                    return true;
                }
                log.info("WARNING: This currently doesn't work right, but will in the very near future!");
                getLoader().disablePlugin(split[2]);
            } else if (split[1].equalsIgnoreCase("reload")) {
                if (split.length < 3 || split[2] == "help") {
                    log.info("usage: plugin reload [name]");
                    log.info("Reloads the named plugin, loading it if it is not already in.");
                    log.info("NOTE: This currently doesn't work right, but will in the very near future!");
                    return true;
                }
                log.info("WARNING: This currently doesn't work right, but will in the very near future!");
                getLoader().loadPlugin(split[2]);
                getLoader().disablePlugin(split[2]);
                getLoader().enablePlugin(split[2]);
            } else if (split[1].equalsIgnoreCase("list")) {
                log.info("Plugins: " + getLoader().getPluginList());
            } else {
                log.info("Unknown option, try plugin help");
            }
        } else {
            dontParseRegular = false;
        }
        return dontParseRegular;
    }

    private static ea match(String name, MinecraftServer d) {
        ea player = null;
        boolean found = false;
        if (("`" + d.f.c().toUpperCase() + "`").split(name.toUpperCase()).length == 2) {
            for (int i = 0; i < d.f.b.size() && !found; ++i) {
                ea localea = (ea) d.f.b.get(i);
                if (("`" + localea.aq.toUpperCase() + "`").split(name.toUpperCase()).length == 2) {
                    player = localea;
                    found = true;
                }
            }
        } else if (("`" + d.f.c() + "`").split(name).length > 2) {
            // Too many partial matches.
            for (int i = 0; i < d.f.b.size() && !found; ++i) {
                ea localea = (ea) d.f.b.get(i);
                if (localea.aq.equalsIgnoreCase(name)) {
                    player = localea;
                    found = true;
                }
            }
        }
        return player;
    }

    /**
     * Returns compass direction according to your rotation
     * @param degrees
     * @return
     */
    public static String getCompassPointForDirection(double degrees) {
        if (0 <= degrees && degrees < 22.5) {
            return "N";
        } else if (22.5 <= degrees && degrees < 67.5) {
            return "NE";
        } else if (67.5 <= degrees && degrees < 112.5) {
            return "E";
        } else if (112.5 <= degrees && degrees < 157.5) {
            return "SE";
        } else if (157.5 <= degrees && degrees < 202.5) {
            return "S";
        } else if (202.5 <= degrees && degrees < 247.5) {
            return "SW";
        } else if (247.5 <= degrees && degrees < 292.5) {
            return "W";
        } else if (292.5 <= degrees && degrees < 337.5) {
            return "NW";
        } else if (337.5 <= degrees && degrees < 360.0) {
            return "N";
        } else {
            return "ERR";
        }
    }
}
