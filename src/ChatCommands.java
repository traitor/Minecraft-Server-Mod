import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public final class ChatCommands {
    private static final Logger log = Logger.getLogger("Minecraft");
    private static ChatCommands instance;
    private LinkedHashMap<String, BaseCommand> commands = new LinkedHashMap<String, BaseCommand>();

    public static ChatCommands getInstance() {
        if (instance == null) {
            instance = new ChatCommands();
        }
        return instance;
    }

    public boolean parseCommand(String command, Player player, String[] parameters) {
        BaseCommand cmd = commands.get(command);
        if (cmd != null) {
            cmd.parseCommand(player, parameters);
            return true;
        }
        return false;
    }

    /** 
     * Returns a copy of the current command list. Use add() or remove() if you wish to modify it.
     * @return copy of command list.
     */
    public LinkedHashMap<String, BaseCommand> getCommands() {
        return new LinkedHashMap<String, BaseCommand>(commands);
    }

    public void add(String name, BaseCommand cmd) {
        commands.put(name, cmd);
    }

    public void remove(String name) {
        commands.remove(name);
    }

    // Add all standard commands to the list on creation.
    private ChatCommands() {
        commands.put("ban",         ban);
        commands.put("banlist",     banlist);
        commands.put("banip",       banip);
        commands.put("help",        help);
        commands.put("give",        give);
        commands.put("i",           give);
        commands.put("item",        give);
        commands.put("kick",        kick);
        commands.put("kit",         kit);
        commands.put("modify",      modify);
        commands.put("mp",          modify);
        commands.put("m",           msg);
        commands.put("msg",         msg);
        commands.put("tell",        msg);
        commands.put("tempban",     tempban);
        commands.put("mute",        mute);
        commands.put("reload",      reloadConfig);
        commands.put("reservelist", reservelist);
        commands.put("playerlist",  playerlist);
        commands.put("who",         playerlist);
        commands.put("tp",          tp);
        commands.put("s",           tphere);
        commands.put("tphere",      tphere);
        commands.put("unban",       unban);
        commands.put("unbanip",     unbanip);
        commands.put("whitelist",   whitelist);
    }

    private BaseCommand help = new BaseCommand("[Page] - Shows a list of commands. 7 per page.") {
        @Override        
        void execute(Player player, String[] parameters) {
            // Meh, not the greatest way, but not the worst either.
            List<String> allowedCommands = player.getAllowedCommands();
            player.sendMessage(Colors.Blue + "Available commands (Page " + (parameters.length == 2 ? parameters[1] : "1") + " of " + (int) Math.ceil((double) allowedCommands.size() / (double) 7) + ") [] = required <> = optional:");
            if (parameters.length == 2) {
                try {
                    int amount = Integer.parseInt(parameters[1]);
                    amount = (amount > 0) ? (amount - 1) * 7 : 0;

                    for (int i = amount; i < amount + 7; i++) {
                        if (allowedCommands.size() > i) {
                            player.sendMessage(Colors.Rose + allowedCommands.get(i));
                        }
                    }
                } catch (NumberFormatException ex) {
                    player.sendMessage(Colors.Rose + "Not a valid page number.");
                }
            } else {
                for (int i = 0; i < 7; i++) {
                    if (allowedCommands.size() > i) {
                        player.sendMessage(Colors.Rose + allowedCommands.get(i));
                    }
                }
            }
        }
    };

    private BaseCommand playerlist = new BaseCommand("- Shows a list of players") {
        @Override
        void execute(Player player, String[] parameters) {
            player.sendMessage(Colors.Rose + "Player list (" + etc.getMCServer().f.b.size() + "/" + etc.getInstance().getPlayerLimit() + "): " + Colors.White + etc.getMCServer().f.c());
        }
    };

    private BaseCommand reloadConfig = new BaseCommand("- Reloads config") {
        @Override
        void execute(Player player, String[] parameters) {
            etc.getInstance().load();
            etc.getInstance().loadData();
            for (Player p : etc.getServer().getPlayerList()) {
                p.getUser().reloadPlayer();
            }
            log.info("Reloaded config");
            player.sendMessage("Successfuly reloaded config");
        }
    };

    private BaseCommand modify = new BaseCommand("[player] [key] [value] - Type /modify for more info") {      
        public void showError(Player player) {
            player.sendMessage(Colors.Rose + "Usage is: /modify [player] [key] [value]");
            player.sendMessage(Colors.Rose + "Keys:");
            player.sendMessage(Colors.Rose + "prefix: only the letter the color represents");
            player.sendMessage(Colors.Rose + "commands: list seperated by comma");
            player.sendMessage(Colors.Rose + "groups: list seperated by comma");
            player.sendMessage(Colors.Rose + "ignoresrestrictions: true or false");
            player.sendMessage(Colors.Rose + "admin: true or false");
            player.sendMessage(Colors.Rose + "modworld: true or false");
        }
        @Override
        void execute(Player player, String[] parameters) {
            Player target = (parameters.length > 1) ? player.findPlayer(parameters[1]) : null;
            if (target == null) {
                return;
            }
            boolean newUser = !etc.getDataSource().doesPlayerExist(target.getName());

            if (parameters.length > 2 && parameters[2].contains(":")) {
                // New version of /modify, taking colon separated list of values.
                for (int i = 3; i < parameters.length; i++) {
                    if (!parameters[i].contains(":")) {
                        showError(player);
                        return;
                    }
                }
                String firstKey = parameters[2].split(":")[0];
                if (newUserNoGroup(player, target, firstKey, newUser)) {
                    return;
                }
                parseMany(player, target, parameters, newUser);
            } else {
                // Old version of /modify, taking only one key-value pair.
                if (parameters.length != 4) {
                    showError(player);
                    return;
                }
                String firstKey = parameters[2];
                if (newUserNoGroup(player, target, firstKey, newUser)) {
                    return;
                }
                parseOne(player, target, parameters[2], parameters[3], newUser);
                player.sendMessage(Colors.Rose + "Modified user.");
            }
        }
        
        private boolean newUserNoGroup(Player player, Player target, String firstKey, boolean newUser) {
            if (newUser) {
                if (!firstKey.equalsIgnoreCase("groups") && !firstKey.equalsIgnoreCase("g")) {
                    player.sendMessage(Colors.Rose + "When adding a new user, set their group(s) first.");
                    return false;
                }
                player.sendMessage(Colors.Rose + "Adding new user.");
                target.setCanModifyWorld(true);
            }
            return true;
        }

        private void parseMany(Player player, Player target, String[] parameters, boolean newUser) {                                
            for (int i = 2; i < parameters.length; i++) {
                if (parameters[i].split(":").length != 2) {
                    player.sendMessage("This key:value pair is deformed... " + parameters[i]);
                    return;
                }
                parseOne(player, target, parameters[i].split(":")[0], parameters[i].split(":")[1], newUser);
            }
            player.sendMessage(Colors.Rose + "Modified user.");
        }

        private void parseOne(Player player, Player target, String key, String value, boolean newUser) {
            setKeyOnTarget(target, key, value);
            if (newUser) {
                etc.getDataSource().addPlayer(target);
            } else {
                etc.getDataSource().modifyPlayer(target);
            }
            log.info("Modifed user " + target.getName() + ". " + key + " => " + value + " by " + player.getName());
        }

        private void setKeyOnTarget(Player target, String key, String value) {
            if (key.equalsIgnoreCase("prefix") || key.equalsIgnoreCase("p")) {
                target.setPrefix(value);
            } else if (key.equalsIgnoreCase("commands") || key.equalsIgnoreCase("c")) {
                target.setCommands(value.split(","));
            } else if (key.equalsIgnoreCase("groups") || key.equalsIgnoreCase("g")) {
                target.setGroups(value.split(","));
            } else if (key.equalsIgnoreCase("ignoresrestrictions") || key.equalsIgnoreCase("ir")) {
                target.setIgnoreRestrictions(value.equalsIgnoreCase("true") || value.equals("1"));
            } else if (key.equalsIgnoreCase("admin") || key.equalsIgnoreCase("a")) {
                target.setAdmin(value.equalsIgnoreCase("true") || value.equals("1"));
            } else if (key.equalsIgnoreCase("modworld") || key.equalsIgnoreCase("mw")) {
                target.setCanModifyWorld(value.equalsIgnoreCase("true") || value.equals("1"));
            }
        }
    };

    private BaseCommand whitelist = new BaseCommand("[operation (add or remove)] [player]", "whitelist [operation (toggle, add or remove)] <player>", 2) {
        @Override
        void execute(Player player, String[] parameters) {    
            if (parameters[1].equalsIgnoreCase("toggle")) {
                player.sendMessage(Colors.Rose + (etc.getInstance().toggleWhitelist() ? "Whitelist enabled" : "Whitelist disabled"));
            } else if (parameters.length == 3) {
                if (parameters[1].equalsIgnoreCase("add")) {
                    etc.getDataSource().addToWhitelist(parameters[2]);
                    player.sendMessage(Colors.Rose + parameters[2] + " added to whitelist");
                } else if (parameters[1].equalsIgnoreCase("remove")) {
                    etc.getDataSource().removeFromWhitelist(parameters[2]);
                    player.sendMessage(Colors.Rose + parameters[2] + " removed from whitelist");
                } else {
                    player.sendMessage(Colors.Rose + "Invalid operation.");
                }
            } else {
                player.sendMessage(Colors.Rose + "Invalid operation.");
            }
        }
    };

    private BaseCommand reservelist = new BaseCommand("[operation (add or remove)] [player]","reservelist [operation (add or remove)] [player]",3,3) {
        @Override
        void execute(Player player, String[] parameters) {
            if (parameters[1].equalsIgnoreCase("add")) {
                etc.getDataSource().addToReserveList(parameters[2]);
                player.sendMessage(Colors.Rose + parameters[2] + " added to reservelist");
            } else if (parameters[1].equalsIgnoreCase("remove")) {
                etc.getDataSource().removeFromReserveList(parameters[2]);
                player.sendMessage(Colors.Rose + parameters[2] + " removed from reservelist");
            } else {
                player.sendMessage(Colors.Rose + "Invalid operation.");
            }
        }

    };

    private BaseCommand mute = new BaseCommand("[Player] - Toggles mute on player.", "Correct usage is: /mute [player]", 2) {
        @Override
        void execute(Player player, String[] parameters) {
            Player target = player.findPlayer(parameters[1]);
            if (target == null) {
                return;
            }
            player.sendMessage(Colors.Rose + "player was " + ((target.toggleMute()) ? "muted" : "unmuted"));
        }
    };

    private BaseCommand msg = new BaseCommand("[Player] [Message] - Sends a message to player", "Correct usage is: /msg [player] [message]", 3) {
        void execute(Player player, String[] parameters) {
            if (player.isMuted()) {
                player.sendMessage(Colors.Rose + "You are currently muted.");
                return;
            }

            Player target = player.findPlayer(parameters[1]);
            if (target == null) {
                return;
            }
            
            if (target.getName().equals(player.getName())) {
                player.sendMessage(Colors.Rose + "You can't message yourself!");
                return;
            }

            target.sendMessage("(MSG) " + player.getColor() + "<" + player.getName() + "> " + Colors.White + etc.combineSplit(2, parameters, " "));
            player.sendMessage("(MSG) " + player.getColor() + "<" + player.getName() + "> " + Colors.White + etc.combineSplit(2, parameters, " "));
        }
    };

    private BaseCommand kit = new BaseCommand("[Kit] - Gives a kit. To get a list of kits type /kit") {
        void execute(Player player, String[] parameters) {
            if (!etc.getDataSource().hasKits()) {
                return;
            }

            if (parameters.length != 2 && parameters.length != 3) {
                player.sendMessage(Colors.Rose + "Available kits" + Colors.White + ": " + etc.getDataSource().getKitNames(player));
                return;
            }

            Player target = player;
            if (parameters.length > 2 && player.canIgnoreRestrictions()) {
                target = etc.getServer().matchPlayer(parameters[1]);
            }

            Kit kit = etc.getDataSource().getKit(parameters[1]);
            player.giveKitTo(target, kit);
        }
    };

    private BaseCommand tp = new BaseCommand("[Player] - Teleports to player. Credits to Zet from SA", "Correct usage is: /tp [player]", 2) {
        void execute(Player player, String[] parameters) {
            Player target = player.findPlayer(parameters[1]);
            if (target == null) {
                return;
            }
            
            if (player.getName().equalsIgnoreCase(player.getName())) {
                player.sendMessage(Colors.Rose + "You're already here!");
                return;
            }

            player.teleportTo(target);
            log.info(player.getName() + " teleported to " + player.getName());
        }
    };

    private BaseCommand tphere = new BaseCommand("[Player] - Teleports the player to you", "Correct usage is: /tphere [player]", 2) {
        void execute(Player player, String[] parameters) {
            Player target = player.findPlayer(parameters[1]);
            if (target == null) {
                return;
            }
               
            if (player.getName().equalsIgnoreCase(target.getName())) {
                player.sendMessage(Colors.Rose + "Wow look at that! You teleported yourself to yourself!");
                return;
            }
            
            target.teleportTo(player);
            log.info(player.getName() + " teleported " + target.getName() + " to their self.");
        }
    };
    
    private BaseCommand give = new BaseCommand("[ID] [Amount] <Player> - Gives items", "", 2) {
        @Override
        public void onError(Player player, String[] parameters) {
            player.sendMessage(Colors.Rose + "Correct usage is: /item [itemid] <amount>" + ((player.canIgnoreRestrictions()) ? " <player> (optional)":""));
        }
        
        void execute(Player player, String[] parameters) {
            Player target = player;
            if (parameters.length == 4 && player.canIgnoreRestrictions()) {
                target = player.findPlayer(parameters[3]);
                if (target == null) {
                    return;
                }
            }
            try {
                int itemId = 0;
                try {
                    itemId = Integer.parseInt(parameters[1]);
                } catch (NumberFormatException n) {
                    itemId = etc.getDataSource().getItem(parameters[1]);
                }
                int amount = 1;
                if (parameters.length > 2) {
                    amount = Integer.parseInt(parameters[2]);
                }
                String itemIdstr = Integer.toString(itemId);
                if (amount <= 0 && !player.isAdmin()) {
                    amount = 1;
                }
                if (amount > 64 && !player.canIgnoreRestrictions()) {
                    amount = 64;
                }
                if (amount > 1024) {
                    amount = 1024; // 16 stacks worth. More than enough.
                }
                boolean allowedItem = false;
                if (!etc.getInstance().getAllowedItems()[0].equals("") && (!player.canIgnoreRestrictions())) {
                    for (String str : etc.getInstance().getAllowedItems()) {
                        if (itemIdstr.equals(str)) {
                            allowedItem = true;
                        }
                    }
                } else {
                    allowedItem = true;
                }
                if (!etc.getInstance().getDisallowedItems()[0].equals("") && !player.canIgnoreRestrictions()) {
                    for (String str : etc.getInstance().getDisallowedItems()) {
                        if (itemIdstr.equals(str)) {
                            allowedItem = false;
                        }
                    }
                }
                if (Item.isValidItem(itemId)) {
                    if (allowedItem || player.canIgnoreRestrictions()) {
                        log.log(Level.INFO, "Giving " + target.getName() + " some " + itemId);
                        target.giveItem(itemId, amount);

                        if (target.getName().equalsIgnoreCase(player.getName())) {
                            player.sendMessage(Colors.Rose + "There you go c:");
                        } else {
                            player.sendMessage(Colors.Rose + "Gift given! :D");
                            target.sendMessage(Colors.Rose + "Enjoy your gift! :D");
                        }
                    } else if (!allowedItem && !player.canIgnoreRestrictions()) {
                        player.sendMessage(Colors.Rose + "You are not allowed to spawn that item.");
                    }
                } else {
                    player.sendMessage(Colors.Rose + "No item with ID " + parameters[1]);
                }
            } catch (NumberFormatException localNumberFormatException) {
                player.sendMessage(Colors.Rose + "Improper ID and/or amount.");
            }
        }
    };
    
    private BaseCommand tempban = new BaseCommand("[Player] <Reason> - Tempory Ban of player's IP", "", 2) {
        @Override        
        void execute(Player player, String[] parameters) {
            // /tempban MINUTES HOURS DAYS
            if (parameters.length == 1) {
                // TODO;
                return;
            }
            int minutes = 0, hours = 0, days = 0;
            if (parameters.length >= 2) {
                minutes = Integer.parseInt(parameters[1]);
            }
            if (parameters.length >= 3) {
                hours = Integer.parseInt(parameters[2]);
            }
            if (parameters.length >= 4) {
                days = Integer.parseInt(parameters[3]);
            }
            Date date = new Date();
            // date.
        }
    };
    private BaseCommand banlist = new BaseCommand("<IP or bans> - Gives a list of bans", "", 2) {
        @Override        
        void execute(Player player, String[] parameters) {
            byte type = 0;
            if (parameters.length == 2) {
                if (parameters[1].equalsIgnoreCase("ips")) {
                    type = 1;
                }
            }
            if (type == 0) { // Regular user bans
                player.sendMessage(Colors.Blue + "Ban list:" + Colors.White + " " + etc.getMCServer().f.getBans());
            } else { // IP bans
                player.sendMessage(Colors.Blue + "IP Ban list:" + Colors.White + " " + etc.getMCServer().f.getIpBans());
            }
        }
    };
    
    private BaseCommand banip = new BaseCommand("[Player] <Reason> - Bans the player's IP", "Correct usage is: /banip [player] <reason> (optional) NOTE: this permabans IPs.", 2) {
        @Override        
        void execute(Player player, String[] parameters) {
            Player target = player.findPlayer(parameters[1]);
            if (target == null) {
                return;
            }
            if (!player.hasControlOver(target)) {
                player.sendMessage(Colors.Rose + "You can't ban that user.");
                return;
            }
        
            // adds player to ban list
            etc.getMCServer().f.c(player.getIP());
            etc.getLoader().callHook(PluginLoader.Hook.IPBAN, new Object[]{this, player, parameters.length >= 3 ? etc.combineSplit(2, parameters, " ") : ""});
    
            log.log(Level.INFO, "IP Banning " + player.getName() + " (IP: " + player.getIP() + ")");
            player.sendMessage(Colors.Rose + "IP Banning " + player.getName() + " (IP: " + player.getIP() + ")");
    
            target.kick("IP Banned by " + player.getName() + ((parameters.length > 2) ? ": " + etc.combineSplit(2, parameters, " ") : "."));
        }
    };
    
    private BaseCommand ban = new BaseCommand("[Player] <Reason> - Bans the player", "Correct usage is: /ban [player] <reason> (optional)", 2) {
        @Override        
        void execute(Player player, String[] parameters) {  
            Player target = etc.getServer().matchPlayer(parameters[1]);
            
            if (target == null) {
                etc.getServer().ban(parameters[1]);
                log.log(Level.INFO, "Banning " + parameters[1]);
                player.sendMessage(Colors.Rose + "Banning " + parameters[1]);
                return;
            }
            if (!player.hasControlOver(target)) {
                player.sendMessage(Colors.Rose + "You can't ban that user.");
                return;
            }
    
            // Adds player to ban list.
            etc.getServer().ban(player.getName());
            etc.getLoader().callHook(PluginLoader.Hook.BAN, new Object[]{this, player, parameters.length >= 3 ? etc.combineSplit(2, parameters, " ") : ""});
    
            if (parameters.length > 2) {
                target.kick("Banned by " + player.getName() + ": " + etc.combineSplit(2, parameters, " "));
            } else {
                target.kick("Banned by " + player.getName() + ".");
            }

            log.log(Level.INFO, "Banning " + player.getName());
            player.sendMessage(Colors.Rose + "Banning " + player.getName());
        }
    };
    
    private BaseCommand unban = new BaseCommand("[Player] - Unbans the player", "Correct usage is: /unban [player]", 2,2) {
        @Override        
        void execute(Player player, String[] parameters) {
            etc.getServer().unban(parameters[1]);
            player.sendMessage(Colors.Rose + "Unbanned " + parameters[1]);
        }
    };
    
    private BaseCommand unbanip = new BaseCommand("[IP] - Unbans the IP", "Correct usage is: /unbanip [ip]", 2) {
        @Override        
        void execute(Player player, String[] parameters) {
            etc.getMCServer().f.d(parameters[1]);
            player.sendMessage(Colors.Rose + "Unbanned " + parameters[1]);
        }
    };

    private BaseCommand kick = new BaseCommand("[Player] <Reason> - Kicks player", "Correct usage is: /kick [player] <reason> (optional)", 2) {
        @Override        
        void execute(Player player, String[] parameters) {        
            Player target = player.findPlayer(parameters[1]);
            if (target == null) {
                return;
            }
            if (!player.hasControlOver(target)) {
                player.sendMessage(Colors.Rose + "You can't kick that user.");
                return;
            }
            etc.getLoader().callHook(PluginLoader.Hook.KICK, new Object[]{this, player, parameters.length >= 3 ? etc.combineSplit(2, parameters, " ") : ""});
            target.kick("Kicked by " + player.getName() + ((parameters.length > 2) ? ": " + etc.combineSplit(2, parameters, " ") : "."));
            log.log(Level.INFO, "Kicking " + target.getName());
            player.sendMessage(Colors.Rose + "Kicking " + target.getName());
        }
    };
}
