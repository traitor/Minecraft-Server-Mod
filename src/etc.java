/* Just a general storage for all my crap */

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
    public String usersLoc = "users.txt", kitsLoc = "kits.txt", homeLoc = "homes.txt", warpLoc = "warps.txt", itemLoc = "items.txt", groupLoc = "groups.txt", commandsLoc = "commands.txt";
    public String whitelistLoc = "whitelist.txt", reservelistLoc = "reservelist.txt";
    public String whitelistMessage = "Not on whitelist.";
    public String[] allowedItems = null;
    public String[] disallowedItems = null;
    public String[] itemSpawnBlacklist = null;
    public String[] motd = null;
    public boolean saveHomes = true;
    public boolean firstLoad = true;
    public boolean whitelistEnabled = false;
    public int playerLimit = 20;
    public int spawnProtectionSize = 16;
    public LinkedHashMap<String, String> commands = new LinkedHashMap<String, String>();
    private String dataSourceType;
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
        commands.put("/log.info", "[Player] [Message] - Sends a message to player");
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
        commands.put("/enableplugin", "[plugin] - Enables plugin");
        commands.put("/disableplugin", "[plugin] - Disables plugin");
        commands.put("/listplugins", "- Lists all plugins");
        commands.put("/reloadplugin", "[plugin] - Reloads plugin");

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
     * Returns the data source
     * @return
     */
    public static DataSource getDataSource() {
        return etc.getInstance().getSource();
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
            loader.loadPlugins();
        }

        return loader;
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
    public DataSource getSource() {
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

            Player player = getServer().matchPlayer(split[1]);

            if (player == null) {
                log.info("Player does not exist.");
                return true;
            }

            String key = split[2];
            String value = split[3];
            boolean newUser = false;

            if (!etc.getDataSource().doesPlayerExist(player.getName())) {
                if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                    log.info("When adding a new user, set their group(s) first.");
                    return true;
                }
                log.info("Adding new user.");
                newUser = true;
            }

            if (key.equalsIgnoreCase("prefix") || key.equalsIgnoreCase("p")) {
                player.setPrefix(value);
            } else if (key.equalsIgnoreCase("commands") || key.equalsIgnoreCase("c")) {
                player.setCommands(value.split(","));
            } else if (key.equalsIgnoreCase("groups") || key.equalsIgnoreCase("g")) {
                player.setGroups(value.split(","));
            } else if (key.equalsIgnoreCase("ignoresrestrictions") || key.equalsIgnoreCase("ir")) {
                player.setIgnoreRestrictions(value.equalsIgnoreCase("true") || value.equals("1"));
            } else if (key.equalsIgnoreCase("admin") || key.equalsIgnoreCase("a")) {
                player.setAdmin(value.equalsIgnoreCase("true") || value.equals("1"));
            } else if (key.equalsIgnoreCase("modworld") || key.equalsIgnoreCase("mw")) {
                player.setCanModifyWorld(value.equalsIgnoreCase("true") || value.equals("1"));
            }

            if (newUser) {
                etc.getDataSource().addPlayer(player);
            } else {
                etc.getDataSource().modifyPlayer(player);
            }
            log.info("Modifed user " + split[1] + ". " + key + " => " + value);
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
        } else {
            dontParseRegular = false;
        }
        return dontParseRegular;
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

    /**
     * Combines the string array into a string at the specified start with the
     * separator separating each string.
     * @param startIndex
     * @param string
     * @param seperator
     * @return
     */
    public static String combineSplit(int startIndex, String[] string, String seperator) {
        StringBuilder builder = new StringBuilder();
        for (int i = startIndex; i < string.length; i++) {
            builder.append(string[i]);
            builder.append(seperator);
        }
        builder.deleteCharAt(builder.length() - seperator.length()); // remove the extra
        // seperator
        return builder.toString();
    }
}
