import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class ServerConsoleCommands {
    private static final Logger                      log      = Logger.getLogger("Minecraft");
    private static ServerConsoleCommands             instance;
    private final LinkedHashMap<String, BaseCommand> commands = new LinkedHashMap<String, BaseCommand>();

    private ServerConsoleCommands() {
        add("reload", reload);
        add("listplugins", listplugins);
        add("enableplugin", enableplugin);
        add("reloadplugin", reloadplugin);
        add("disableplugin", disableplugin);
        add("modify", modify);
        add("mp", modify);
        add("reservelist", reservelist);
        add("whitelist", whitelist);
        add("version", version);
    }

    /**
     * Add a command to the server list.
     * 
     * @param name
     * @param cmd
     */
    public void add(String name, BaseCommand cmd) {
        if (name != null && cmd != null)
            commands.put(name, cmd);
    }

    /**
     * Remove a command from the server list.
     * 
     * @param name
     */
    public void remove(String name) {
        if (name != null) {
            etc.getInstance().removeCommand(name);
            commands.remove(name);
        }
    }

    /**
     * Performs a lookup for a command of the given name and executes it if
     * found. Returns false if command not found.
     * 
     * @param command
     * @param player
     * @param parameters
     * @return
     */
    public static boolean parseServerConsoleCommand(MessageReceiver caller, String command, String[] args) {
        if (instance == null)
            instance = new ServerConsoleCommands();

        BaseCommand cmd = instance.getCommand(command);
        if (cmd != null) {
            cmd.parseCommand(caller, args);
            // Inform caller a matching command was found.
            return true;
        }
        return false;
    }

    public BaseCommand getCommand(String command) {
        return commands.get(command);
    }

    public static final BaseCommand reload        = new BaseCommand("- Reloads hMod") {
                                                      @Override
                                                      void execute(MessageReceiver caller, String[] parameters) {
                                                          etc.getInstance().load();
                                                          etc.getInstance().loadData();
                                                          for (Player p : etc.getServer().getPlayerList())
                                                              p.getUser().reloadPlayer();
                                                          log.info("hMod reloaded by " + caller.getName());
                                                          caller.notify("Successfully reloaded config");
                                                      }
                                                  };

    public static final BaseCommand modify        = new BaseCommand("[player] [key] [value] - Type /modify for more info", "Overriden onBadSyntax", 3) {

                                                      @Override
                                                      void execute(MessageReceiver caller, String[] parameters) {
                                                          if (parameters.length > 2 && parameters[2].contains(":")) {
                                                              for (int i = 3; i < parameters.length; i++)
                                                                  if (!parameters[i].contains(":")) {
                                                                      onBadSyntax(caller, null);
                                                                      return;
                                                                  }

                                                              Player player = etc.getServer().matchPlayer(parameters[1]);

                                                              if (player == null) {
                                                                  caller.notify("Player does not exist.");
                                                                  return;
                                                              }

                                                              for (int i = 2; i < parameters.length; i++) {
                                                                  if (parameters[i].split(":").length != 2) {
                                                                      caller.notify("This key:value pair is deformed... " + parameters[i]);
                                                                      return;
                                                                  }
                                                                  String key = parameters[i].split(":")[0];
                                                                  String value = parameters[i].split(":")[1];
                                                                  boolean newUser = false;

                                                                  if (!etc.getDataSource().doesPlayerExist(player.getName())) {
                                                                      if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                                                                          caller.notify("When adding a new user, set their group(s) first.");
                                                                          return;
                                                                      }
                                                                      caller.notify("Adding new user.");
                                                                      newUser = true;
                                                                      player.setCanModifyWorld(true);
                                                                  }

                                                                  updatePlayerValues(player, key, value);
                                                                  saveChanges(player, newUser);

                                                                  log.info("Modifed user " + parameters[1] + ". " + key + " => " + value + " by " + caller.getName());
                                                              }
                                                              caller.notify("Modified user.");
                                                          } else {
                                                              if (parameters.length < 4) {
                                                                  onBadSyntax(caller, null);
                                                                  return;
                                                              }

                                                              Player player = etc.getServer().matchPlayer(parameters[1]);

                                                              if (player == null) {
                                                                  caller.notify("Player does not exist.");
                                                                  return;
                                                              }

                                                              String key = parameters[2];
                                                              String value = parameters[3];
                                                              boolean newUser = false;

                                                              if (!etc.getDataSource().doesPlayerExist(player.getName())) {
                                                                  if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                                                                      caller.notify("When adding a new user, set their group(s) first.");
                                                                      return;
                                                                  }
                                                                  caller.notify("Adding new user.");
                                                                  newUser = true;
                                                              }

                                                              updatePlayerValues(player, key, value);
                                                              saveChanges(player, newUser);

                                                              caller.notify("Modified user.");
                                                              // Send to server
                                                              // log too,
                                                              // regardless of
                                                              // caller.
                                                              log.info("Modifed user " + parameters[1] + ". " + key + " => " + value + " by " + caller.getName());
                                                          }
                                                      }

                                                      private void saveChanges(Player player, boolean newUser) {
                                                          if (newUser)
                                                              etc.getDataSource().addPlayer(player);
                                                          else
                                                              etc.getDataSource().modifyPlayer(player);
                                                      }

                                                      private void updatePlayerValues(Player player, String key, String value) {
                                                          if (key.equalsIgnoreCase("prefix") || key.equalsIgnoreCase("p"))
                                                              player.setPrefix(value);
                                                          else if (key.equalsIgnoreCase("commands") || key.equalsIgnoreCase("c"))
                                                              player.setCommands(value.split(","));
                                                          else if (key.equalsIgnoreCase("groups") || key.equalsIgnoreCase("g"))
                                                              player.setGroups(value.split(","));
                                                          else if (key.equalsIgnoreCase("ignoresrestrictions") || key.equalsIgnoreCase("ir"))
                                                              player.setIgnoreRestrictions(value.equalsIgnoreCase("true") || value.equals("1"));
                                                          else if (key.equalsIgnoreCase("admin") || key.equalsIgnoreCase("a"))
                                                              player.setAdmin(value.equalsIgnoreCase("true") || value.equals("1"));
                                                          else if (key.equalsIgnoreCase("modworld") || key.equalsIgnoreCase("mw"))
                                                              player.setCanModifyWorld(value.equalsIgnoreCase("true") || value.equals("1"));
                                                      }

                                                      @Override
                                                      public void onBadSyntax(MessageReceiver caller, String[] params) {
                                                          caller.notify("Usage is: /modify [player] [key] [value]");
                                                          caller.notify("Keys:");
                                                          caller.notify("prefix: only the letter the color represents");
                                                          caller.notify("commands: list seperated by comma");
                                                          caller.notify("groups: list seperated by comma");
                                                          caller.notify("ignoresrestrictions: true or false");
                                                          caller.notify("admin: true or false");
                                                          caller.notify("modworld: true or false");
                                                      }
                                                  };

    public final static BaseCommand whitelist     = new BaseCommand("[operation (add or remove)] [player]", "whitelist [operation (toggle, add or remove)] <player>", 2) {
                                                      @Override
                                                      void execute(MessageReceiver caller, String[] parameters) {
                                                          if (parameters[1].equalsIgnoreCase("toggle"))
                                                              caller.notify((etc.getInstance().toggleWhitelist() ? "Whitelist enabled" : "Whitelist disabled"));
                                                          else if (parameters.length == 3) {
                                                              if (parameters[1].equalsIgnoreCase("add")) {
                                                                  etc.getDataSource().addToWhitelist(parameters[2]);
                                                                  caller.notify(parameters[2] + " added to whitelist");
                                                              } else if (parameters[1].equalsIgnoreCase("remove")) {
                                                                  etc.getDataSource().removeFromWhitelist(parameters[2]);
                                                                  caller.notify(parameters[2] + " removed from whitelist");
                                                              } else
                                                                  caller.notify("Invalid operation.");
                                                          } else
                                                              caller.notify("Invalid operation.");
                                                      }
                                                  };

    public final static BaseCommand reservelist   = new BaseCommand("[operation (add or remove)] [player]", "reservelist [operation (add or remove)] [player]", 3, 3) {

                                                      @Override
                                                      void execute(MessageReceiver caller, String[] parameters) {
                                                          if (parameters[1].equalsIgnoreCase("add")) {
                                                              etc.getDataSource().addToReserveList(parameters[2]);
                                                              caller.notify(parameters[2] + " added to reservelist");
                                                          } else if (parameters[1].equalsIgnoreCase("remove")) {
                                                              etc.getDataSource().removeFromReserveList(parameters[2]);
                                                              caller.notify(parameters[2] + " removed from reservelist");
                                                          } else
                                                              caller.notify("Invalid operation.");
                                                      }
                                                  };

    public final static BaseCommand listplugins   = new BaseCommand("- Lists all plugins") {
                                                      @Override
                                                      void execute(MessageReceiver caller, String[] parameters) {
                                                          caller.notify("Plugins" + Colors.White + ": " + etc.getLoader().getPluginList());
                                                      }
                                                  };

    public final static BaseCommand reloadplugin  = new BaseCommand("[plugin] - Reloads plugin", "Correct usage is: /reloadplugin [plugin]", 2) {
                                                      @Override
                                                      void execute(MessageReceiver caller, String[] parameters) {
                                                          if (etc.getLoader().reloadPlugin(parameters[1]))
                                                              caller.notify("Plugin reloaded.");
                                                          else
                                                              caller.notify("Unable to reload plugin. Check capitalization and/or server logfile.");
                                                      }
                                                  };

    public final static BaseCommand enableplugin  = new BaseCommand("[plugin] - Enables plugin", "Correct usage is: /enableplugin [plugin]", 2) {
                                                      @Override
                                                      void execute(MessageReceiver caller, String[] parameters) {
                                                          if (etc.getLoader().enablePlugin(parameters[1]))
                                                              caller.notify("Plugin enabled.");
                                                          else
                                                              caller.notify("Unable to enable plugin. Check capitalization and/or server logfile.");
                                                      }
                                                  };

    public final static BaseCommand disableplugin = new BaseCommand("[plugin] - Disables plugin", "Correct usage is: /disableplugin [plugin]", 2) {
                                                      @Override
                                                      void execute(MessageReceiver caller, String[] parameters) {
                                                          etc.getLoader().disablePlugin(parameters[1]);
                                                          caller.notify("Plugin disabled.");
                                                      }
                                                  };

    public final static BaseCommand version       = new BaseCommand("- Displays the server version") {
                                                      @Override
                                                      void execute(MessageReceiver caller, String[] parameters) {
                                                          if (!etc.getInstance().getTainted())
                                                              caller.notify(Colors.Gold + "Hey0 Server Mod Build " + etc.getInstance().getVersion());
                                                          else
                                                              caller.notify(Colors.Gold + "Unofficial hMod Build " + etc.getInstance().getVersionStr());
                                                      }
                                                  };
}
