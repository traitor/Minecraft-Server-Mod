import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Date;

/**
 * AdminPlugin.java - Basic admin command plugin, ported from hey0's code
 * @author James
 * Ported by chrisinajar
 */
public class AdminPlugin extends Plugin
{
    public static final Logger a = Logger.getLogger("Minecraft");

	public void enable()
	{
	}

	public void disable()
	{
	}

	public static String combineSplit(int startIndex, String[] string, String seperator) {
		StringBuilder builder = new StringBuilder();
		for (int i = startIndex; i < string.length; i++)
			builder.append(string[i] + seperator);
		builder.deleteCharAt(builder.length() - 1); // remove the extra
		// seperator
		return builder.toString();
	}
	
	public boolean onCommand(Player player, String[] split)
	{
        if (!player.canUseCommand(split[0]) && !split[0].startsWith("/#")) {
			player.sendMessage(Colors.Rose + "Unknown command.");
            return false;
		}
		if ((split[0].startsWith("/#")) && (player.isAdmin())) {
			String str = combineSplit(1, split, " ");
			a.info(player.getName() + " issued server command: " + str);
			etc.getServer().useConsoleCommand(str, player);
        } else if (split[0].equalsIgnoreCase("/mute")) {
            if (split.length != 2) {
                player.sendMessage(Colors.Rose + "Correct usage is: /mute [player]");
                return true;
            }

            Player p = etc.getServer().matchPlayer(split[1]);
            if (p != null) {
                if (p.toggleMute()) {
                    player.sendMessage(Colors.Rose + "player was muted");
                } else {
                    player.sendMessage(Colors.Rose + "player was unmuted");
                }
            } else {
                player.sendMessage(Colors.Rose + "Can't find player " + split[1]);
            }
            } else if (split[0].equalsIgnoreCase("/tempban")) {
                // /tempban MINUTES HOURS DAYS
                if (split.length == 1) {
                    //TODO;
                    return false;
                }
                int minutes = 0, hours = 0, days = 0;
                if (split.length >= 2) {
                    minutes = Integer.parseInt(split[1]);
                }
                if (split.length >= 3) {
                    hours = Integer.parseInt(split[2]);
                }
                if (split.length >= 4) {
                    days = Integer.parseInt(split[3]);
                }
                Date date = new Date();
                //date.
            } else if (split[0].equalsIgnoreCase("/banip")) {
                if (split.length < 2) {
                    player.sendMessage(Colors.Rose + "Correct usage is: /banip [player] <reason> (optional) NOTE: this permabans IPs.");
                    return true;
                }

                Player p = etc.getServer().matchPlayer(split[1]);

                if (p != null) {
                    if (!player.hasControlOver(p)) {
                        player.sendMessage(Colors.Rose + "You can't ban that user.");
                        return true;
                    }

                    // adds player to ban list
                    etc.getServer().banIp(p, split.length > 2 ? split[1] : "");

                    a.log(Level.INFO, "IP Banning " + p.getName() + " (IP: " + p.getIP() + ")");
                    player.sendMessage(Colors.Rose + "IP Banning " + p.getName() + " (IP: " + p.getIP() + ")");

                    if (split.length > 2) {
                        p.kick("IP Banned by " + player.getName() + ": " + etc.combineSplit(2, split, " "));
                    } else {
                        p.kick("IP Banned by " + player.getName() + ".");
                    }
                } else {
                    player.sendMessage(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/banlist")) {
                byte type = 0;
                if (split.length == 2) {
                    if (split[1].equalsIgnoreCase("ips")) {
                        type = 1;
                    }
                }
                if (type == 0) { //Regular user bans
                    player.sendMessage(Colors.Blue + "Ban list:" + Colors.White + " " + etc.getServer().getBans());
                } else { //IP bans
                    player.sendMessage(Colors.Blue + "IP Ban list:" + Colors.White + " " + etc.getServer().getBans());
                }
            } else if (split[0].equalsIgnoreCase("/ban")) {
                if (split.length < 2) {
                    player.sendMessage(Colors.Rose + "Correct usage is: /ban [player] <reason> (optional)");
                    return true;
                }

                Player p = etc.getServer().matchPlayer(split[1]);

                if (p != null) {
                    if (!player.hasControlOver(p)) {
                        player.sendMessage(Colors.Rose + "You can't ban that user.");
                        return true;
                    }

                    // adds player to ban list
                    etc.getServer().ban(p, split.length > 2 ? split[1] : "");

                    if (split.length > 2) {
                        p.kick("Banned by " + player.getName() + ": " + etc.combineSplit(2, split, " "));
                    } else {
                        p.kick("Banned by " + player.getName() + ".");
                    }
                    a.log(Level.INFO, "Banning " + p.getName());
                    player.sendMessage(Colors.Rose + "Banning " + p.getName());
                } else {
                    player.sendMessage(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/unban")) {
                if (split.length != 2) {
                    player.sendMessage(Colors.Rose + "Correct usage is: /unban [player]");
                    return true;
                }
                etc.getServer().unban(split[1]);
                player.sendMessage(Colors.Rose + "Unbanned " + split[1]);
            } else if (split[0].equalsIgnoreCase("/unbanip")) {
                if (split.length != 2) {
                    player.sendMessage(Colors.Rose + "Correct usage is: /unbanip [ip]");
                    return true;
                }
                etc.getServer().unbanIp(split[1]);
                player.sendMessage(Colors.Rose + "Unbanned " + split[1]);
            } else if (split[0].equalsIgnoreCase("/kick")) {
                if (split.length < 2) {
                    player.sendMessage(Colors.Rose + "Correct usage is: /kick [player] <reason> (optional)");
                    return true;
                }

                Player p = etc.getServer().matchPlayer(split[1]);

                if (p != null) {
                    if (!player.hasControlOver(p)) {
                        player.sendMessage(Colors.Rose + "You can't kick that user.");
                        return true;
                    }

                    etc.getInstance().getLoader().callHook(PluginLoader.Hook.KICK, new Object[]{p.getUser(), split.length > 2 ? split[1] : ""});

                    if (split.length > 2) {
                        p.kick("Kicked by " + player.getName() + ": " + etc.combineSplit(2, split, " "));
                    } else {
                        p.kick("Kicked by " + player.getName() + ".");
                    }
                    a.log(Level.INFO, "Kicking " + p.getName());
                    player.sendMessage(Colors.Rose + "Kicking " + p.getName());
                } else {
                    player.sendMessage(Colors.Rose + "Can't find user " + split[1] + ".");
                }
		} else {
			return false;
		}
		return true;
	}
}
