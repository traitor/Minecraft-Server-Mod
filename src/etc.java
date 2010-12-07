import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

import net.minecraft.server.MinecraftServer;

/**
 * etc.java - My catch-all class for a bunch of shit. If there's something you
 * need it's probably in here.
 * 
 * @author James
 */
public class etc {

    private static final Logger log = Logger.getLogger("Minecraft");
    private static volatile etc instance;
    private static MinecraftServer server;
    private String usersLoc = "users.txt", kitsLoc = "kits.txt", homeLoc = "homes.txt", warpLoc = "warps.txt", itemLoc = "items.txt", groupLoc = "groups.txt";
    private String whitelistLoc = "whitelist.txt", reservelistLoc = "reservelist.txt";
    private String whitelistMessage = "Not on whitelist.";
    
    private Set<Integer> allowedItems = new HashSet<Integer>();
    private Set<Integer> disallowedItems = new HashSet<Integer>();
    private Set<Integer> itemSpawnBlacklist = new HashSet<Integer>();
    
    private String[] motd = null;
    private boolean saveHomes = true;
    private boolean firstLoad = true;
    private boolean whitelistEnabled = false;
    private int playerLimit = 20;
    private int spawnProtectionSize = 16;
    private LinkedHashMap<String, String> commands = new LinkedHashMap<String, String>();
    private String dataSourceType;
    private DataSource dataSource;
    private PropertiesFile properties;
    private PluginLoader loader;
    private boolean logging = false;
    private boolean enableHealth = true;
    private PluginLoader.HookResult autoHeal = PluginLoader.HookResult.DEFAULT_ACTION;
    private boolean showUnknownCommand = true;
    private String versionStr;
    private boolean tainted = true;
    private int version = 1; // Version is meant to be loaded from the file, this stays as 1.
    private String driver, username, password, db;

    private etc() {
        commands.put("/help", "[Page] - Shows a list of commands. 7 per page.");
        commands.put("/playerlist", "- Shows a list of players");
        commands.put("/reload", "- Reloads config");
        commands.put("/banlist", "<IP or bans> - Gives a list of bans");
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
        commands.put("/time", "[time|'day|night|check|raw'] (rawtime) - Changes or checks the time");
        commands.put("/lighter", "- Gives you a lighter for lighting furnaces");
        commands.put("/motd", "- Displays the MOTD");
        commands.put("/modify", "[player] [key] [value] - Type /modify for more info");
        commands.put("/whitelist", "[operation (add or remove)] [player]");
        commands.put("/reservelist", "[operation (add or remove)] [player]");
        commands.put("/enableplugin", "[plugin] - Enables plugin");
        commands.put("/disableplugin", "[plugin] - Disables plugin");
        commands.put("/listplugins", "- Lists all plugins");
        commands.put("/reloadplugin", "[plugin] - Reloads plugin");
        commands.put("/clearinventory", "- Clears your inventory");

        load();
    }

    private void loadIds(Collection<Integer> storage, String rawData) {
        for (String id : rawData.split(",")) {
            id = id.trim();
            if (id.length() > 0) {
                try {
                    storage.add(Integer.parseInt(id));
                } catch (NumberFormatException e) {
                    log.log(Level.SEVERE, "While parsing the config: '" + id + "' is not a number");
                }
            }
        }
    }

    /**
     * Loads or reloads the mod
     */
    public final void load() {
        if (properties == null) {
            properties = new PropertiesFile("server.properties");
        } else {
            try {
                properties.load();
            } catch (IOException e) {
                log.log(Level.SEVERE, "Exception while reading from server.properties", e);
            }
        }

        try {
            dataSourceType = properties.getString("data-source", "flatfile");

            loadIds( allowedItems, properties.getString("alloweditems", ""));
            loadIds( disallowedItems, properties.getString("disalloweditems", ""));
            loadIds( itemSpawnBlacklist, properties.getString("itemspawnblacklist", ""));
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
            } else {
                PropertiesFile sql = new PropertiesFile("mysql.properties");
                driver = sql.getString("driver", "com.mysql.jdbc.Driver");
                username = sql.getString("user", "root");
                password = sql.getString("pass", "root");
                db = sql.getString("db", "jdbc:mysql://localhost:3306/minecraft");
            }
            spawnProtectionSize = properties.getInt("spawn-protection-size", 16);
            logging = properties.getBoolean("logging", false);
            enableHealth = properties.getBoolean("enable-health", true);

            String autoHealString = properties.getString("auto-heal", "default");
            if (autoHealString.equalsIgnoreCase("true")) {
                autoHeal = PluginLoader.HookResult.ALLOW_ACTION;
            } else if (autoHealString.equalsIgnoreCase("false")) {
                autoHeal = PluginLoader.HookResult.PREVENT_ACTION;
            }

            showUnknownCommand = properties.getBoolean("show-unknown-command", true);
            URL url = this.getClass().getResource("/version.txt");
            if (url != null) {
                InputStreamReader ins = new InputStreamReader(url.openStream());
                BufferedReader bufferedReader = new BufferedReader(ins);
                String versionParam = bufferedReader.readLine();
                if (versionParam.startsWith("git-")) { // recommended version.txt for git builds: git-<gituser>-<shorttag>
                    // example: git-sk89q-591c662cf4afc8e3e09a
                    version = -1;
                    versionStr = versionParam;
                    tainted = true;
                } else {
                    version = Integer.parseInt(versionParam);
                    versionStr = Integer.toString(version); // and back to a string.
                    tainted = false; // looks official. We hope.
                }
            } else {
                // I'm a tainted build, probably.
                version = -1;
                versionStr = "Undefined version";
                tainted = true;
                // If any mods check the version.. #@!$
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception while reading from server.properties", e);
            // Just in case...
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
     * 
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
     * 
     * @param s
     */
    public static void setServer(MinecraftServer s) {
        server = s;
    }

    /**
     * Returns the minecraft server
     * 
     * @return
     */
    public static MinecraftServer getMCServer() {
        return server;
    }

    /**
     * Returns the data source
     * 
     * @return
     */
    public static DataSource getDataSource() {
        return etc.getInstance().getSource();
    }

    /**
     * Returns the minecraft server interface
     * 
     * @return
     */
    public static Server getServer() {
        return etc.getLoader().getServer();
    }

    /**
     * Returns the plugin loader
     * 
     * @return
     */
    public static PluginLoader getLoader() {
        if (instance.loader == null) {
            instance.loader = new PluginLoader(server);
            instance.loader.loadPlugins();
        }

        return instance.loader;
    }

    /**
     * Returns the default group
     * 
     * @return default group
     */
    public Group getDefaultGroup() {
        Group group = dataSource.getDefaultGroup();
        if (group == null) {
            log.log(Level.SEVERE, "No default group! Expect lots of errors!");
        }
        return group;
    }

    /**
     * Adds or modifies the home.
     * 
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
     * 
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
     * 
     * @param id
     * @return
     */
    public boolean isOnItemBlacklist(int id) {
        return itemSpawnBlacklist.contains(id);
    }

    /**
     * Returns the data source
     * 
     * @return
     */
    public DataSource getSource() {
        return dataSource;
    }

    /**
     * Returns true if we're logging commands and such
     * 
     * @return
     */
    public boolean isLogging() {
        return logging;
    }

    /**
     * Returns true if we want health to be enabled.
     *
     * @return
     */
    public boolean isHealthEnabled() {
        return enableHealth;
    }
    

    /**
     * Returns the status of auto-heal.
     *
     * @return
     */
    public PluginLoader.HookResult autoHeal() {
        return autoHeal;
    }

    /**
     * Adds command to the /help list
     * 
     * @param command
     * @param description
     */
    public void addCommand(String command, String description) {
        commands.put(command, description);
    }

    /**
     * Removes command from /help list
     * 
     * @param command
     */
    public void removeCommand(String command) {
        commands.remove(command);
    }

    /**
     * Toggles the whitelist (doesn't persist)
     * 
     * @return
     */
    public boolean toggleWhitelist() {
        whitelistEnabled = !whitelistEnabled;
        return whitelistEnabled;
    }
    
    /**
     * Callback object for notifications sent by executed ServerCommands.
     * so that they appear in server log.
     */
    private MessageReceiver serverConsole = new MessageReceiver() {
        @Override
        public String getName() {
            return "<Server>";
        }

        @Override
        public void notify(String message) {
            // Strip the colors.
            message = message.replaceAll("§[a-f0-9]", "");
            if (message != null)
                log.info(message);
        }
        
    };

    /**
     * Parses a console command
     * 
     * @param command
     * @param server
     * @return
     */
    public boolean parseConsoleCommand(String command, MinecraftServer server) {
        if (getMCServer() == null) {
            setServer(server);
        }
        String[] split = command.split(" ");
        if ((Boolean) getLoader().callHook(PluginLoader.Hook.SERVERCOMMAND, new Object[]{split})) {
            return true;
        }
        if (split.length == 0) {
            return false;
        }

        boolean dontParseRegular = true;
        if (split[0].equalsIgnoreCase("save-all")) {
            dontParseRegular = false;
            getServer().saveInventories();
        } else if (split[0].equalsIgnoreCase("help") || split[0].equalsIgnoreCase("mod-help")) {
            if (split[0].equalsIgnoreCase("help")) {
                dontParseRegular = false;
            }
            log.info("Server mod help:");
            log.info("help          Displays this mod's and server's help");
            log.info("mod-help      Displays this mod's help");
            log.info("version       Displays the server version");
            log.info("reload        Reloads the config");
            log.info("modify        Type modify for more info");
            log.info("whitelist     Type whitelist for more info");
            log.info("reservelist   Type reservelist for more info");
            log.info("listplugins   Lists all plugins");
            log.info("enableplugin  Enables a plugin");
            log.info("disableplugin Disables a plugin");
            log.info("reloadplugin  Reloads a plugin");
        } else {
            dontParseRegular = ServerConsoleCommands.parseServerConsoleCommand(serverConsole, split[0], split);
        }
        
        return dontParseRegular;
    }

    /**
     * Returns compass direction according to your rotation
     * 
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

    /**
     * Combines the string array into a string at the specified start with the
     * separator separating each string.
     * 
     * @param startIndex
     * @param string
     * @param seperator
     * @return combined string
     */
    public static String combineSplit(int startIndex, String[] string, String seperator) {
        StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i < string.length; i++) {
            builder.append(string[i]);
            builder.append(seperator);
        }
        builder.deleteCharAt(builder.length() - seperator.length()); // remove
        // the
        // extra
        // seperator
        return builder.toString();
    }

    /**
     * Returns a list of allowed items for /item
     * 
     * @return list of allowed items
     */
    public Set<Integer> getAllowedItems() {
        return allowedItems;
    }

    /**
     * Returns the list of commands
     * 
     * @return
     */
    public LinkedHashMap<String, String> getCommands() {
        return commands;
    }

    /**
     * Returns a list of disallowed items for /item
     * 
     * @return
     */
    public Set<Integer> getDisallowedItems() {
        return disallowedItems;
    }

    /**
     * Returns the location of groups.txt
     * 
     * @return
     */
    public String getGroupLocation() {
        return groupLoc;
    }

    /**
     * Returns the location of homes.txt
     * 
     * @return
     */
    public String getHomeLocation() {
        return homeLoc;
    }

    /**
     * Returns the location of items.txt
     * 
     * @return
     */
    public String getItemLocation() {
        return itemLoc;
    }

    /**
     * Returns list of banned blocks
     * 
     * @return
     */
    public Set<Integer> getItemSpawnBlacklist() {
        return itemSpawnBlacklist;
    }

    /**
     * Returns the location of kits.txt
     * 
     * @return
     */
    public String getKitsLocation() {
        return kitsLoc;
    }

    /**
     * Returns the MOTD.
     * 
     * @return
     */
    public String[] getMotd() {
        return motd;
    }

    /**
     * Returns the player limit
     * 
     * @return
     */
    public int getPlayerLimit() {
        return playerLimit;
    }

    /**
     * Returns the location of reservelist.txt
     * 
     * @return
     */
    public String getReservelistLocation() {
        return reservelistLoc;
    }

    /**
     * Returns true if the server is saving homes
     * 
     * @return true if server can save homes
     */
    public boolean canSaveHomes() {
        return saveHomes;
    }

    /**
     * Returns the spawn protection size
     * 
     * @return
     */
    public int getSpawnProtectionSize() {
        return spawnProtectionSize;
    }

    /**
     * Returns the location of users.txt
     * 
     * @return
     */
    public String getUsersLocation() {
        return usersLoc;
    }

    /**
     * Returns the location of warps.txt
     * 
     * @return
     */
    public String getWarpLocation() {
        return warpLoc;
    }

    /**
     * Returns true if the whitelist is enabled
     * 
     * @return
     */
    public boolean isWhitelistEnabled() {
        return whitelistEnabled;
    }

    /**
     * Returns the location of whitelist.txt
     * 
     * @return
     */
    public String getWhitelistLocation() {
        return whitelistLoc;
    }

    /**
     * Returns the message the kick will show if a player isn't on the whitelist
     * 
     * @return
     */
    public String getWhitelistMessage() {
        return whitelistMessage;
    }

    /**
     * Sets the list of allowed items
     * 
     * @param allowedItems
     */
    public void setAllowedItems(int[] allowedItems) {
        this.allowedItems.clear();
        // this.allowedItems.addAll(Arrays.asList(allowedItems)); <-- if only java was smart >.>
        for (int item: allowedItems) {
            this.allowedItems.add(item);
        }
    }

    /**
     * Sets the list of disallowed items
     * 
     * @param disallowedItems
     */
    public void setDisallowedItems(int[] disallowedItems) {
        this.disallowedItems.clear();
        // this.allowedItems.addAll(Arrays.asList(allowedItems)); <-- if only java was smart >.>
        for (int item: disallowedItems) {
            this.disallowedItems.add(item);
        }
    }

    /**
     * Sets the location of groups.txt
     * 
     * @param groupLoc
     */
    public void setGroupLocation(String groupLoc) {
        this.groupLoc = groupLoc;
    }

    /**
     * Sets the location of homes.txt
     * 
     * @param homeLoc
     */
    public void setHomeLocation(String homeLoc) {
        this.homeLoc = homeLoc;
    }

    /**
     * Sets the location of items.txt
     * 
     * @param itemLoc
     */
    public void setItemLocation(String itemLoc) {
        this.itemLoc = itemLoc;
    }

    /**
     * Sets the item spawn blacklist
     * 
     * @param itemSpawnBlacklist
     */
    public void setItemSpawnBlacklist(int[] itemSpawnBlacklist) {
        this.itemSpawnBlacklist.clear();
        // this.allowedItems.addAll(Arrays.asList(allowedItems)); <-- if only java was smart >.>
        for (int item: itemSpawnBlacklist) {
            this.itemSpawnBlacklist.add(item);
        }
    }

    /**
     * Sets the location of kits.txt
     * 
     * @param kitsLoc
     */
    public void setKitsLocation(String kitsLoc) {
        this.kitsLoc = kitsLoc;
    }

    /**
     * If set to true the server will log all commands used.
     * 
     * @param logging
     */
    public void setLogging(boolean logging) {
        this.logging = logging;
    }

    /**
     * Set the MOTD
     * 
     * @param motd
     */
    public void setMotd(String[] motd) {
        this.motd = motd;
    }

    /**
     * Set the player limit
     * 
     * @param playerLimit
     */
    public void setPlayerLimit(int playerLimit) {
        this.playerLimit = playerLimit;
    }

    /**
     * Set the location of reservelist.txt
     * 
     * @param reservelistLoc
     */
    public void setReservelistLocation(String reservelistLoc) {
        this.reservelistLoc = reservelistLoc;
    }

    /**
     * If true the server will save homes. If false homes won't be saved and
     * will be wiped the next server restart.
     * 
     * @param saveHomes
     */
    public void setSaveHomes(boolean saveHomes) {
        this.saveHomes = saveHomes;
    }

    /**
     * Set the spawn protection size (def: 16)
     * 
     * @param spawnProtectionSize
     */
    public void setSpawnProtectionSize(int spawnProtectionSize) {
        this.spawnProtectionSize = spawnProtectionSize;
    }

    /**
     * Sets the location of users.txt
     * 
     * @param usersLoc
     */
    public void setUsersLocation(String usersLoc) {
        this.usersLoc = usersLoc;
    }

    /**
     * Sets the location of warps.txt
     * 
     * @param warpLoc
     */
    public void setWarpLocation(String warpLoc) {
        this.warpLoc = warpLoc;
    }

    /**
     * If true the whitelist is enabled
     * 
     * @param whitelistEnabled
     */
    public void setWhitelistEnabled(boolean whitelistEnabled) {
        this.whitelistEnabled = whitelistEnabled;
    }

    /**
     * Sets the location of whitelist.txt
     * 
     * @param whitelistLoc
     */
    public void setWhitelistLocation(String whitelistLoc) {
        this.whitelistLoc = whitelistLoc;
    }

    /**
     * Sets the whitelist message to show when it kicks someone
     * 
     * @param whitelistMessage
     */
    public void setWhitelistMessage(String whitelistMessage) {
        this.whitelistMessage = whitelistMessage;
    }

    /**
     * Returns true if "Unknown command" is shown to a player when they enter an
     * unknown command (For wrappers and such)
     * 
     * @return show unknown command
     */
    public boolean showUnknownCommand() {
        return showUnknownCommand;
    }

    /**
     * Sets whether or not to show "Unknown command" to players.
     * 
     * @param showUnknownCommand
     *            whether or not to show it
     */
    public void setShowUnknownCommand(boolean showUnknownCommand) {
        this.showUnknownCommand = showUnknownCommand;
    }

    /**
     * Return the current build of the mod
     * 
     * @return build/version
     */
    public int getVersion() {
        return version;
    }

    /**
     * Return whether this build is "tainted"
     *
     * @return tainted
     */
    public boolean getTainted() {
        return tainted;
    }

    /**
     * Return the specified string version of the build
     *
     * @return build/version
     */
    public String getVersionStr() {
        return versionStr;
    }

    private Connection _getSQLConnection() {
        try {
            return DriverManager.getConnection(db + "?autoReconnect=true&user=" + username + "&password=" + password);
        } catch (SQLException ex) {
            log.log(Level.SEVERE, "Unable to retreive connection", ex);
        }
        return null;
    }

    /**
     * Returns a SQL connection
     * 
     * @return sql connection
     */
    public static Connection getSQLConnection() {
        return getInstance()._getSQLConnection();
    }
}
