
import java.net.SocketAddress;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class id extends ej
        implements ef {

    public static Logger a = Logger.getLogger("Minecraft");
    public bb b;
    public boolean c = false;
    private MinecraftServer d;
    private ea e;
    private int f = 0;
    private double g;
    private double h;
    private double i;
    private boolean j = true;
    private gp k = null;
    private List<String> onlyOneUseKits = new ArrayList<String>();

    public id(MinecraftServer paramMinecraftServer, bb parambb, ea paramea) {
        this.d = paramMinecraftServer;
        this.b = parambb;
        parambb.a(this);
        this.e = paramea;
        paramea.a = this;
    }

    public void a() {
        this.b.a();
        if (this.f++ % 20 == 0) {
            this.b.a(new iz());
        }
    }

    public void c(String paramString) {
        this.b.a(new io(paramString));
        this.b.c();
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(gf paramgf) {
        double d1;
        if (!this.j) {
            d1 = paramgf.b - this.h;
            if ((paramgf.a == this.g) && (d1 * d1 < 0.01D) && (paramgf.c == this.i)) {
                this.j = true;
            }
        }
        if (this.j) {
            this.g = this.e.l;
            this.h = this.e.m;
            this.i = this.e.n;

            d1 = this.e.l;
            double d2 = this.e.m;
            double d3 = this.e.n;

            float f1 = this.e.r;
            float f2 = this.e.s;

            if (paramgf.h) {
                d1 = paramgf.a;
                d2 = paramgf.b;
                d3 = paramgf.c;
                double d4 = paramgf.d - paramgf.b;
                if ((d4 > 1.65D) || (d4 < 0.1D)) {
                    c("Illegal stance");
                    a.warning(this.e.aq + " had an illegal stance: " + d4);
                }
                this.e.ai = paramgf.d;
            }
            if (paramgf.i) {
                f1 = paramgf.e;
                f2 = paramgf.f;
            }

            this.e.i();
            this.e.M = 0.0F;
            this.e.b(this.g, this.h, this.i, f1, f2);

            double d4 = d1 - this.e.l;
            double d5 = d2 - this.e.m;
            double d6 = d3 - this.e.n;

            float f3 = 0.0625F;
            int m = this.d.e.a(this.e, this.e.v.b().e(f3, f3, f3)).size() == 0 ? 1 : 0;

            this.e.c(d4, d5, d6);
            d4 = d1 - this.e.l;
            d5 = d2 - this.e.m;
            if ((d5 > -0.5D) || (d5 < 0.5D)) {
                d5 = 0.0D;
            }
            d6 = d3 - this.e.n;
            double d7 = d4 * d4 + d5 * d5 + d6 * d6;
            int n = 0;
            if (d7 > 0.0625D) {
                n = 1;
                a.warning(this.e.aq + " moved wrongly!");
            }
            this.e.b(d1, d2, d3, f1, f2);

            int i1 = this.d.e.a(this.e, this.e.v.b().e(f3, f3, f3)).size() == 0 ? 1 : 0;
            if ((m != 0) && ((n != 0) || (i1 == 0))) {
                a(this.g, this.h, this.i, f1, f2);
                return;
            }

            this.e.w = paramgf.g;
            this.d.f.b(this.e);
        }
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        this.j = false;
        this.g = paramDouble1;
        this.h = paramDouble2;
        this.i = paramDouble3;
        this.e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        this.e.a.b(new dq(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }

    //Destroy function
    public void a(hd paramhd) {
        this.e.aj.a[this.e.aj.d] = this.k;
        boolean bool = this.d.e.z = (this.d.f.g(this.e.aq) || etc.getInstance().isAdmin(e));
        int m = 0;
        if (paramhd.e == 0) {
            m = 1;
        }
        if (paramhd.e == 1) {
            m = 1;
        }

        if (m != 0) {
            double d1 = this.e.m;
            this.e.m = this.e.ai;
            fr localfr = this.e.a(4.0D, 1.0F);
            this.e.m = d1;
            if (localfr == null) {
                return;
            }
            if ((localfr.b != paramhd.a) || (localfr.c != paramhd.b) || (localfr.d != paramhd.c) || (localfr.e != paramhd.d)) {
                return;
            }
        }
        int n = paramhd.a;
        int i1 = paramhd.b;
        int i2 = paramhd.c;
        int i3 = paramhd.d;
        int i4 = (int) gj.e(n - this.d.e.n);
        int i5 = (int) gj.e(i2 - this.d.e.p);
        if (i4 > i5) {
            i5 = i4;
        }
        if (paramhd.e == 0) {
            if (!etc.getInstance().canBuild(e)) {
                return;
            }
            if (i5 > etc.getInstance().spawnProtectionSize || bool) {
                if (!(Boolean)etc.getInstance().getLoader().callHook(PluginLoader.HOOKS.BLOCK_DESTROYED, new Object[] {e, etc.getServer().getBlockAt(n, i1, i2)}))
                    this.e.ad.a(n, i1, i2);
            }
        } else if (paramhd.e == 2) {
            this.e.ad.a();
        } else if (paramhd.e == 1) {
            if (!etc.getInstance().canBuild(e)) {
                return;
            }
            if (i5 > etc.getInstance().spawnProtectionSize || bool) {
                if (!(Boolean)etc.getInstance().getLoader().callHook(PluginLoader.HOOKS.BLOCK_DESTROYED, new Object[] {e, etc.getServer().getBlockAt(n, i1, i2)}))
                    this.e.ad.a(n, i1, i2, i3);
            }
        } else if (paramhd.e == 3) {
            double d2 = this.e.l - (n + 0.5D);
            double d3 = this.e.m - (i1 + 0.5D);
            double d4 = this.e.n - (i2 + 0.5D);
            double d5 = d2 * d2 + d3 * d3 + d4 * d4;
            if (d5 < 256.0D) {
                this.e.a.b(new et(n, i1, i2, this.d.e));
            }
        }
        this.d.e.z = false;
    }

    //Build function
    public void a(fe paramfe) {
        if (!etc.getInstance().canBuild(e)) {
            return;
        }
        boolean bool = this.d.e.z = (this.d.f.g(this.e.aq) || etc.getInstance().isAdmin(e));
        int m = paramfe.b;
        int n = paramfe.c;
        int i1 = paramfe.d;
        int i2 = paramfe.e;
        int i3 = (int) gj.e(m - this.d.e.n);
        int i4 = (int) gj.e(i1 - this.d.e.p);
        if (i3 > i4) {
            i4 = i3;
        }
        if (i4 > etc.getInstance().spawnProtectionSize || bool) {
            gp localgp = paramfe.a >= 0 ? new gp(paramfe.a) : null;

            Block blockPlaced = new Block(localgp != null ? localgp.c : paramfe.a, m, n, i1);
            if (paramfe.e == 0)
                blockPlaced.setY(blockPlaced.getY() - 1);
            else if(paramfe.e == 1)
                blockPlaced.setY(blockPlaced.getY() + 1);
            else if(paramfe.e == 2)
                blockPlaced.setZ(blockPlaced.getZ() - 1);
            else if(paramfe.e == 3)
                blockPlaced.setZ(blockPlaced.getZ() + 1);
            else if(paramfe.e == 4)
                blockPlaced.setX(blockPlaced.getX() - 1);
            else if(paramfe.e == 5)
                blockPlaced.setX(blockPlaced.getX() + 1);
            Block blockClicked = new Block(etc.getServer().getBlockIdAt(m, n, i1), m, n, i1);

            if (!(Boolean)etc.getInstance().getLoader().callHook(PluginLoader.HOOKS.BLOCK_CREATED, new Object[] {e, blockPlaced, blockClicked, paramfe.a})) {
                if (localgp != null) {
                    if (!etc.getInstance().isOnItemBlacklist(localgp.c) || bool) {
                        this.e.ad.a(this.e, this.d.e, localgp, m, n, i1, i2);
                    }
                } else {
                    // is this right?
                    this.e.ad.a(this.e, this.d.e, localgp, m, n, i1, i2);
                }
            }
        }
        this.e.a.b(new et(m, n, i1, this.d.e));
        this.d.e.z = false;
    }

    public void a(String paramString) {
        a.info(this.e.aq + " lost connection: " + paramString);
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(hp paramhp) {
        a.warning(getClass() + " wasn't prepared to deal with a " + paramhp.getClass());
        c("Protocol error, unexpected packet");
    }

    public void b(hp paramhp) {
        this.b.a(paramhp);
    }

    public void a(fv paramfv) {
        int m = paramfv.b;
        this.e.aj.d = (this.e.aj.a.length - 1);
        if (m == 0) {
            this.k = null;
        } else {
            this.k = new gp(m);
        }
        this.e.aj.a[this.e.aj.d] = this.k;
        this.d.k.a(this.e, new fv(this.e.c, m));
    }

    public void a(k paramk) {
        double d1 = paramk.b / 32.0D;
        double d2 = paramk.c / 32.0D;
        double d3 = paramk.d / 32.0D;
        fn localfn = new fn(this.d.e, d1, d2, d3, new gp(paramk.h, paramk.i));
        localfn.o = (paramk.e / 128.0D);
        localfn.p = (paramk.f / 128.0D);
        localfn.q = (paramk.g / 128.0D);
        localfn.ad = 10;
        this.d.e.a(localfn);
    }

    public void a(ba paramba) {
        String str = paramba.a;
        if (str.length() > 100) {
            b("Chat message too long");
            return;
        }
        str = str.trim();
        for (int k = 0; k < str.length(); ++k) {
            if (" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~¦ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»".indexOf(str.charAt(k)) < 0) {
                b("Illegal characters in chat");
                return;
            }
        }

        if (str.startsWith("/")) {
            d(str);
        } else {
            if (etc.getInstance().isMuted(e)) {
                msg(Colors.Rose + "You are currently muted.");
                return;
            }
            if ((Boolean)etc.getInstance().getLoader().callHook(PluginLoader.HOOKS.CHAT, new Object[]{e, str}))
                return;

            String message = "<" + etc.getInstance().getUserColor(e.aq) + this.e.aq + Colors.White + "> " + str;
            a.log(Level.INFO, "<" + e.aq + "> " + str);
            this.d.f.a(new ba(message));
        }
    }

    private ea match(String name) {
        ea player = null;
        boolean found = false;
        if (("`" + this.d.f.c().toUpperCase() + "`").split(name.toUpperCase()).length == 2) {
            for (int i = 0; i < this.d.f.b.size() && !found; ++i) {
                ea localea = (ea) this.d.f.b.get(i);
                if (("`" + localea.aq.toUpperCase() + "`").split(name.toUpperCase()).length == 2) {
                    player = localea;
                    found = true;
                }
            }
        } else if (("`" + this.d.f.c() + "`").split(name).length > 2) {
            // Too many partial matches.
            for (int i = 0; i < this.d.f.b.size() && !found; ++i) {
                ea localea = (ea) this.d.f.b.get(i);
                if (localea.aq.equalsIgnoreCase(name)) {
                    player = localea;
                    found = true;
                }
            }
        }
        return player;
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

    /**
     * Returns true if this player has control over the other player
     * @param player
     * @return
     */
    public boolean hasControlOver(ea player) {
        boolean isInGroup = false;

        if (etc.getInstance().getUser(player.aq) != null) {
            for (String str : etc.getInstance().getUser(player.aq).Groups) {
                if (etc.getInstance().isUserInGroup(e, str)) {
                    isInGroup = true;
                }
            }
        } else {
            return true;
        }

        return isInGroup;
    }

    /**
     * Sends a message to the player
     * @param msg
     */
    public void msg(String msg) {
        b(new ba(msg));
    }

    private void d(String paramString) {
        try {
            if (etc.getInstance().isLogging()) {
                a.info("Command used by " + e.aq + " " + paramString);
            }
            String[] split = paramString.split(" ");
            if ((Boolean)etc.getInstance().getLoader().callHook(PluginLoader.HOOKS.COMMAND, new Object[]{e, split})) {
                return; //No need to go on, commands were parsed.
            }
            if (!etc.getInstance().canUseCommand(e.aq, split[0]) && !split[0].startsWith("/#")) {
                msg(Colors.Rose + "Unknown command.");
                return;
            }
            if (split[0].equalsIgnoreCase("/help")) {
                //Meh, not the greatest way, but not the worst either.
                List<String> availableCommands = new ArrayList<String>();
                for (Entry<String, String> entry : etc.getInstance().commands.entrySet()) {
                    if (etc.getInstance().canUseCommand(e.aq, entry.getKey())) {
                        if (entry.getKey().equals("/kit") && !etc.getInstance().getDataSource().hasKits()) {
                            continue;
                        }
                        if (entry.getKey().equals("/listwarps") && !etc.getInstance().getDataSource().hasWarps()) {
                            continue;
                        }

                        availableCommands.add(entry.getKey() + " " + entry.getValue());
                    }
                }

                msg(Colors.Blue + "Available commands (Page " + (split.length == 2 ? split[1] : "1") + " of " + (int) Math.ceil((double) availableCommands.size() / (double) 7) + ") [] = required <> = optional:");
                if (split.length == 2) {
                    try {
                        int amount = Integer.parseInt(split[1]);

                        if (amount > 0) {
                            amount = (amount - 1) * 7;
                        } else {
                            amount = 0;
                        }

                        for (int i = amount; i < amount + 7; i++) {
                            if (availableCommands.size() > i) {
                                msg(Colors.Rose + availableCommands.get(i));
                            }
                        }
                    } catch (NumberFormatException ex) {
                        msg(Colors.Rose + "Not a valid page number.");
                    }
                } else {
                    for (int i = 0; i < 7; i++) {
                        if (availableCommands.size() > i) {
                            msg(Colors.Rose + availableCommands.get(i));
                        }
                    }
                }
            } else if (split[0].equalsIgnoreCase("/reload")) {
                etc.getInstance().load();
                etc.getInstance().loadData();
                a.info("Reloaded config");
                msg("Successfuly reloaded config");
            } else if ((split[0].equalsIgnoreCase("/modify") || split[0].equalsIgnoreCase("/mp"))) {
                if (split.length < 4) {
                    msg(Colors.Rose + "Usage is: /modify [player] [key] [value]");
                    msg(Colors.Rose + "Keys:");
                    msg(Colors.Rose + "prefix: only the letter the color represents");
                    msg(Colors.Rose + "commands: list seperated by comma");
                    msg(Colors.Rose + "groups: list seperated by comma");
                    msg(Colors.Rose + "ignoresrestrictions: true or false");
                    msg(Colors.Rose + "admin: true or false");
                    msg(Colors.Rose + "modworld: true or false");
                    return;
                }

                ea player = match(split[1]);

                if (player == null) {
                    msg(Colors.Rose + "Player does not exist.");
                    return;
                }

                String key = split[2];
                String value = split[3];
                User user = etc.getInstance().getUser(split[1]);
                boolean newUser = false;

                if (user == null) {
                    if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                        msg(Colors.Rose + "When adding a new user, set their group(s) first.");
                        return;
                    }
                    msg(Colors.Rose + "Adding new user.");
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
                    user.Prefix = value;
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
                    etc.getInstance().getDataSource().addUser(user);
                } else {
                    etc.getInstance().getDataSource().modifyUser(user);
                }
                msg(Colors.Rose + "Modified user.");
                a.info("Modifed user " + split[1] + ". " + key + " => " + value + " by " + e.aq);
            } else if (split[0].equalsIgnoreCase("/whitelist")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "whitelist [operation (toggle, add or remove)] <player>");
                    return;
                }

                if (split[1].equalsIgnoreCase("toggle")) {
                    msg(Colors.Rose + (etc.getInstance().toggleWhitelist() ? "Whitelist enabled" : "Whitelist disabled"));
                } else if(split.length == 3) {
                    if (split[1].equalsIgnoreCase("add")) {
                        etc.getInstance().getDataSource().addToWhitelist(split[2]);
                        msg(Colors.Rose + split[2] + " added to whitelist");
                    } else if (split[1].equalsIgnoreCase("remove")) {
                        etc.getInstance().getDataSource().removeFromWhitelist(split[2]);
                        msg(Colors.Rose + split[2] + " removed from whitelist");
                    } else {
                        msg(Colors.Rose + "Invalid operation.");
                    }
                } else {
                    msg(Colors.Rose + "Invalid operation.");
                }
            } else if (split[0].equalsIgnoreCase("/reservelist")) {
                if (split.length != 3) {
                    msg(Colors.Rose + "reservelist [operation (add or remove)] [player]");
                    return;
                }

                if (split[1].equalsIgnoreCase("add")) {
                    etc.getInstance().getDataSource().addToReserveList(split[2]);
                    msg(Colors.Rose + split[2] + " added to reservelist");
                } else if (split[1].equalsIgnoreCase("remove")) {
                    etc.getInstance().getDataSource().removeFromReserveList(split[2]);
                    msg(Colors.Rose + split[2] + " removed from reservelist");
                } else {
                    msg(Colors.Rose + "Invalid operation.");
                }
            } else if (split[0].equalsIgnoreCase("/mute")) {
                if (split.length != 2) {
                    msg(Colors.Rose + "Correct usage is: /mute [player]");
                    return;
                }

                ea player = match(split[1]);

                if (player != null) {
                    if (etc.getInstance().toggleMute(player)) {
                        msg(Colors.Rose + "player was muted");
                    } else {
                        msg(Colors.Rose + "player was unmuted");
                    }
                } else {
                    msg(Colors.Rose + "Can't find player " + split[1]);
                }
            } else if ((split[0].equalsIgnoreCase("/msg") || split[0].equalsIgnoreCase("/tell")) || split[0].equalsIgnoreCase("/m")) {
                if (split.length < 3) {
                    msg(Colors.Rose + "Correct usage is: /msg [player] [message]");
                    return;
                }
                if (etc.getInstance().isMuted(e)) {
                    msg(Colors.Rose + "You are currently muted.");
                    return;
                }

                ea player = match(split[1]);

                if (player != null) {
                    if (player.aq.equals(e.aq)) {
                        msg(Colors.Rose + "You can't message yourself!");
                        return;
                    }
                    String prefix = etc.getInstance().getUserColor(e.aq);

                    player.a.msg("(MSG) " + prefix + "<" + e.aq + "> " + Colors.White + combineSplit(2, split, " "));
                    msg("(MSG) " + prefix + "<" + e.aq + "> " + Colors.White + combineSplit(2, split, " "));
                } else {
                    msg(Colors.Rose + "Couldn't find player " + split[1]);
                }
            } else if (split[0].equalsIgnoreCase("/kit") && etc.getInstance().getDataSource().hasKits()) {
                if (split.length != 2 && split.length != 3) {
                    msg(Colors.Rose + "Available kits" + Colors.White + ": " + etc.getInstance().getDataSource().getKitNames(e.aq));
                    return;
                }

                ea toGive = e;
                if (split.length > 2 && etc.getInstance().canIgnoreRestrictions(e)) {
                    toGive = match(split[1]);
                }

                Kit kit = etc.getInstance().getDataSource().getKit(split[1]);
                if (toGive != null) {
                    if (kit != null) {
                        if (!etc.getInstance().isUserInGroup(e, kit.Group) && !kit.Group.equals("")) {
                            msg(Colors.Rose + "That kit does not exist.");
                        } else if (onlyOneUseKits.contains(kit.Name)) {
                            msg(Colors.Rose + "You can only get this kit once per login.");
                        } else if (MinecraftServer.b.containsKey(this.e.aq + " " + kit.Name)) {
                            msg(Colors.Rose + "You can't get this kit again for a while.");
                        } else {
                            if (!etc.getInstance().canIgnoreRestrictions(e)) {
                                if (kit.Delay >= 0) {
                                    MinecraftServer.b.put(this.e.aq + " " + kit.Name, kit.Delay);
                                } else {
                                    onlyOneUseKits.add(kit.Name);
                                }
                            }

                            a.info(this.e.aq + " got a kit!");
                            toGive.a.msg(Colors.Rose + "Enjoy this kit!");
                            for (Map.Entry<String, Integer> entry : kit.IDs.entrySet()) {
                                try {
                                    int itemId = 0;
                                    try {
                                        itemId = Integer.parseInt(entry.getKey());
                                    } catch (NumberFormatException n) {
                                        itemId = etc.getInstance().getDataSource().getItem(entry.getKey());
                                    }

                                    int temp = kit.IDs.get(entry.getKey());
                                    do {
                                        if (temp - 64 >= 64) {
                                            toGive.a(new gp(itemId, 64));
                                        } else {
                                            toGive.a(new gp(itemId, temp));
                                        }
                                        temp -= 64;
                                    } while (temp >= 64);
                                } catch (Exception e1) {
                                    a.info("Got an exception while giving out a kit (Kit name \"" + kit.Name + "\"). Are you sure all the Ids are numbers?");
                                    msg(Colors.Rose + "The server encountered a problem while giving the kit :(");
                                }
                            }
                        }
                    } else {
                        msg(Colors.Rose + "That kit does not exist.");
                    }
                } else {
                    msg(Colors.Rose + "That user does not exist.");
                }
            } else if (split[0].equalsIgnoreCase("/tp")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /tp [player]");
                    return;
                }

                ea player = match(split[1]);

                if (this.e.aq.equalsIgnoreCase(split[1])) {
                    msg(Colors.Rose + "You're already here!");
                    return;
                }

                if (player != null) {
                    a.info(this.e.aq + " teleported to " + player.aq);
                    a(player.l, player.m, player.n, player.r, player.s);
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if ((split[0].equalsIgnoreCase("/tphere") || split[0].equalsIgnoreCase("/s"))) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /tphere [player]");
                    return;
                }

                ea player = match(split[1]);

                if (this.e.aq.equalsIgnoreCase(split[1])) {
                    msg(Colors.Rose + "Wow look at that! You teleported yourself to yourself!");
                    return;
                }

                if (player != null) {
                    a.info(this.e.aq + " teleported " + player.aq + " to their self.");
                    player.a.a(e.l, e.m, e.n, e.r, e.s);
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/playerlist") || split[0].equalsIgnoreCase("/who")) {
                msg(Colors.Rose + "Player list (" + d.f.b.size() + "/" + etc.getInstance().playerLimit + "): " + Colors.White + d.f.c());
            } else if (split[0].equalsIgnoreCase("/item") || split[0].equalsIgnoreCase("/i") || split[0].equalsIgnoreCase("/give")) {
                if (split.length < 2) {
                    if (etc.getInstance().canIgnoreRestrictions(e)) {
                        msg(Colors.Rose + "Correct usage is: /item [itemid] <amount> <player> (optional)");
                    } else {
                        msg(Colors.Rose + "Correct usage is: /item [itemid] <amount>");
                    }
                    return;
                }

                ea toGive = e;
                if (split.length == 4 && etc.getInstance().canIgnoreRestrictions(e)) {
                    toGive = match(split[3]);
                }

                if (toGive != null) {
                    try {
                        int i2 = 0;
                        try {
                            i2 = Integer.parseInt(split[1]);
                        } catch (NumberFormatException n) {
                            i2 = etc.getInstance().getDataSource().getItem(split[1]);
                        }
                        int i3 = 1;
                        if (split.length > 2) {
                            i3 = Integer.parseInt(split[2]);
                        }

                        String i2str = Integer.toString(i2);
                        if (i3 == -1 && etc.getInstance().isAdmin(e)) {
                            i3 = 255;
                        } else if (i3 <= 0) {
                            i3 = 1;
                        }
                        if (i3 > 64 && !etc.getInstance().canIgnoreRestrictions(this.e)) {
                            i3 = 64;
                        }

                        boolean allowedItem = false;
                        if (!etc.getInstance().allowedItems[0].equals("") && (!etc.getInstance().canIgnoreRestrictions(this.e))) {
                            for (String str : etc.getInstance().allowedItems) {
                                if (i2str.equals(str)) {
                                    allowedItem = true;
                                }
                            }
                        } else {
                            allowedItem = true;
                        }
                        if (!etc.getInstance().disallowedItems[0].equals("") && !etc.getInstance().canIgnoreRestrictions(this.e)) {
                            for (String str : etc.getInstance().disallowedItems) {
                                if (i2str.equals(str)) {
                                    allowedItem = false;
                                }
                            }
                        }
                        if (i2 < ez.c.length) {
                            if (ez.c[i2] != null && (allowedItem || etc.getInstance().canIgnoreRestrictions(this.e))) {
                                a.log(Level.INFO, "Giving " + toGive.aq + " some " + i2);
                                if (i3 == 255) {
                                    toGive.a(new gp(i2, 255));
                                } else {
                                    int temp = i3;

                                    do {
                                        if (temp - 64 >= 64) {
                                            toGive.a(new gp(i2, 64));
                                        } else {
                                            toGive.a(new gp(i2, temp));
                                        }
                                        temp -= 64;
                                    } while (temp >= 64);
                                }

                                if (toGive == this.e) {
                                    msg(Colors.Rose + "There you go c:");
                                } else {
                                    msg(Colors.Rose + "Gift given! :D");
                                    toGive.a.msg(Colors.Rose + "Enjoy your gift! :D");
                                }
                            } else if ((!allowedItem) && (ez.c[i2] != null) && !etc.getInstance().canIgnoreRestrictions(this.e)) {
                                msg(Colors.Rose + "You are not allowed to spawn that item.");
                            } else {
                                msg(Colors.Rose + "No item with ID " + split[1]);
                            }
                        } else {
                            msg(Colors.Rose + "No item with ID " + split[1]);
                        }
                    } catch (NumberFormatException localNumberFormatException) {
                        msg(Colors.Rose + "Improper ID and/or amount.");
                    }
                } else {
                    msg(Colors.Rose + "Can't find user " + split[3]);
                }
            } else if (split[0].equalsIgnoreCase("/tempban")) {
                // /tempban MINUTES HOURS DAYS
                if (split.length == 1) {
                    //TODO;
                    return;
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
            } else if (split[0].equalsIgnoreCase("/banlist")) {
                byte type = 0;
                if (split.length == 2) {
                    if (split[1].equalsIgnoreCase("ips")) {
                        type = 1;
                    }
                }
                if (type == 0) { //Regular user bans
                    msg(Colors.Blue + "Ban list:" + Colors.White + " " + d.f.getBans());
                } else { //IP bans
                    msg(Colors.Blue + "IP Ban list:" + Colors.White + " " + d.f.getBans());
                }
            } else if (split[0].equalsIgnoreCase("/banip")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /banip [player] <reason> (optional) NOTE: this permabans IPs.");
                    return;
                }

                ea player = match(split[1]);

                if (player != null) {
                    if (!hasControlOver(player)) {
                        msg(Colors.Rose + "You can't ban that user.");
                        return;
                    }

                    // adds player to ban list
                    this.d.f.c(player.a.b.b().toString().split(":")[0].substring(1));

                    etc.getInstance().getLoader().callHook(PluginLoader.HOOKS.IPBAN, new Object[]{e, split.length > 2 ? split[1] : ""});

                    a.log(Level.INFO, "IP Banning " + player.aq + " (IP: " + player.a.b.b().toString() + ")");
                    msg(Colors.Rose + "IP Banning " + player.aq + " (IP: " + player.a.b.b().toString() + ")");

                    if (split.length > 2) {
                        player.a.c("IP Banned by " + e.aq + ": " + combineSplit(2, split, " "));
                    } else {
                        player.a.c("IP Banned by " + e.aq + ".");
                    }
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/ban")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /ban [player] <reason> (optional)");
                    return;
                }

                ea player = match(split[1]);

                if (player != null) {
                    if (!hasControlOver(player)) {
                        msg(Colors.Rose + "You can't ban that user.");
                        return;
                    }

                    // adds player to ban list
                    this.d.f.a(player.aq);

                    etc.getInstance().getLoader().callHook(PluginLoader.HOOKS.BAN, new Object[]{e, split.length > 2 ? split[1] : ""});

                    if (split.length > 2) {
                        player.a.c("Banned by " + e.aq + ": " + combineSplit(2, split, " "));
                    } else {
                        player.a.c("Banned by " + e.aq + ".");
                    }
                    a.log(Level.INFO, "Banning " + player.aq);
                    msg(Colors.Rose + "Banning " + player.aq);
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/unban")) {
                if (split.length != 2) {
                    msg(Colors.Rose + "Correct usage is: /unban [player]");
                    return;
                }
                this.d.f.b(split[1]);
                msg(Colors.Rose + "Unbanned " + split[1]);
            } else if (split[0].equalsIgnoreCase("/unbanip")) {
                if (split.length != 2) {
                    msg(Colors.Rose + "Correct usage is: /unbanip [ip]");
                    return;
                }
                this.d.f.d(split[1]);
                msg(Colors.Rose + "Unbanned " + split[1]);
            } else if (split[0].equalsIgnoreCase("/kick")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /kick [player] <reason> (optional)");
                    return;
                }

                ea player = match(split[1]);

                if (player != null) {
                    if (!hasControlOver(player)) {
                        msg(Colors.Rose + "You can't kick that user.");
                        return;
                    }

                    etc.getInstance().getLoader().callHook(PluginLoader.HOOKS.KICK, new Object[]{e, split.length > 2 ? split[1] : ""});

                    if (split.length > 2) {
                        player.a.c("Kicked by " + e.aq + ": " + combineSplit(2, split, " "));
                    } else {
                        player.a.c("Kicked by " + e.aq + ".");
                    }
                    a.log(Level.INFO, "Kicking " + player.aq);
                    msg(Colors.Rose + "Kicking " + player.aq);
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/me")) {
                if (etc.getInstance().isMuted(e)) {
                    msg(Colors.Rose + "You are currently muted.");
                    return;
                }
                if (split.length == 1)
                    return;
                String prefix = etc.getInstance().getUserColor(e.aq);
                String paramString2 = "* " + prefix + this.e.aq + Colors.White + " " + paramString.substring(paramString.indexOf(" ")).trim();
                a.info("* " + this.e.aq + " " + paramString.substring(paramString.indexOf(" ")).trim());
                this.d.f.a(new ba(paramString2));
            } else if (split[0].equalsIgnoreCase("/sethome")) {
                // player.k, player.l, player.m
                // x, y, z
                Location loc = new Location();
                loc.x = e.l;
                loc.y = e.m;
                loc.z = e.n;
                loc.rotX = e.r;
                loc.rotY = e.s;
                Warp home = new Warp();
                home.Location = loc;
                home.Group = ""; //no group neccessary, lol.
                home.Name = e.aq;
                etc.getInstance().changeHome(home);
                msg(Colors.Rose + "Your home has been set.");
            } else if (split[0].equalsIgnoreCase("/spawn")) {
                int m = this.d.e.d(this.d.e.n, this.d.e.p);
                a(this.d.e.n + 0.5D, m + 1.5D, this.d.e.p + 0.5D, 0.0F, 0.0F);
            } else if (split[0].equalsIgnoreCase("/setspawn")) {
                this.d.e.n = (int) Math.ceil(e.l);
                //Too lazy to actually update this considering it's not even used anyways.
                //this.d.e.n = (int) Math.ceil(e.m); //Not that the Y axis really matters since it tries to get the highest point iirc.
                this.d.e.p = (int) Math.ceil(e.n);
                a.info("Spawn position changed.");
                msg(Colors.Rose + "You have set the spawn to your current position.");
            } else if (split[0].equalsIgnoreCase("/home")) {
                Warp home = null;
                if (split.length > 1 && etc.getInstance().isAdmin(e)) {
                    home = etc.getInstance().getDataSource().getHome(split[1]);
                } else {
                    home = etc.getInstance().getDataSource().getHome(e.aq);
                }

                if (home != null) {
                    a(home.Location.x, home.Location.y, home.Location.z, home.Location.rotX, home.Location.rotY);
                } else if (split.length > 1 && etc.getInstance().isAdmin(e)) {
                    msg(Colors.Rose + "That player home does not exist");
                } else {
                    int m = this.d.e.d(this.d.e.n, this.d.e.p);
                    a(this.d.e.n + 0.5D, m + 1.5D, this.d.e.p + 0.5D, 0.0F, 0.0F);
                }
            } else if (split[0].equalsIgnoreCase("/warp")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /warp [warpname]");
                    return;
                }
                ea toWarp = e;
                Warp warp = null;
                if (split.length == 3 && etc.getInstance().canIgnoreRestrictions(e)) {
                    warp = etc.getInstance().getDataSource().getWarp(split[1]);
                    toWarp = match(split[2]);
                } else {
                    warp = etc.getInstance().getDataSource().getWarp(split[1]);
                }
                if (toWarp != null) {
                    if (warp != null) {
                        if (!etc.getInstance().isUserInGroup(e, warp.Group) && !warp.Group.equals("")) {
                            msg(Colors.Rose + "Warp not found.");
                        } else {
                            toWarp.a.a(warp.Location.x, warp.Location.y, warp.Location.z, warp.Location.rotX, warp.Location.rotY);
                            toWarp.a.msg(Colors.Rose + "Woosh!");
                        }
                    } else {
                        msg(Colors.Rose + "Warp not found");
                    }
                } else {
                    msg(Colors.Rose + "Player not found.");
                }
            } else if (split[0].equalsIgnoreCase("/listwarps") && etc.getInstance().getDataSource().hasWarps()) {
                if (split.length != 2 && split.length != 3) {
                    msg(Colors.Rose + "Available warps: " + Colors.White + etc.getInstance().getDataSource().getWarpNames(e.aq));
                    return;
                }
            } else if (split[0].equalsIgnoreCase("/setwarp")) {
                if (split.length < 2) {
                    if (etc.getInstance().canIgnoreRestrictions(e)) {
                        msg(Colors.Rose + "Correct usage is: /setwarp [warpname] [group]");
                    } else {
                        msg(Colors.Rose + "Correct usage is: /setwarp [warpname]");
                    }
                    return;
                }
                Location loc = new Location();
                loc.x = e.l;
                loc.y = e.m;
                loc.z = e.n;
                loc.rotX = e.r;
                loc.rotY = e.s;
                Warp warp = new Warp();
                warp.Name = split[1];
                warp.Location = loc;
                if (split.length == 3) {
                    warp.Group = split[2];
                } else {
                    warp.Group = "";
                }
                etc.getInstance().setWarp(warp);
                msg(Colors.Rose + "Created warp point " + split[1] + ".");
            } else if (split[0].equalsIgnoreCase("/removewarp")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /removewarp [warpname]");
                    return;
                }
                Warp warp = etc.getInstance().getDataSource().getWarp(split[1]);
                if (warp != null) {
                    etc.getInstance().getDataSource().removeWarp(warp);
                    msg(Colors.Blue + "Warp removed.");
                } else {
                    msg(Colors.Rose + "That warp does not exist");
                }
            } else if (split[0].equalsIgnoreCase("/lighter")) {
                if (MinecraftServer.b.containsKey(this.e.aq + " lighter")) {
                    a.info(this.e.aq + " failed to iron!");
                    msg(Colors.Rose + "You can't create another lighter again so soon");
                } else {
                    if (!etc.getInstance().canIgnoreRestrictions(e)) {
                        MinecraftServer.b.put(this.e.aq + " lighter", Integer.valueOf(6000));
                    }
                    a.info(this.e.aq + " created a lighter!");
                    this.e.a(new gp(259, 1));
                }
            } else if ((paramString.startsWith("/#")) && (this.d.f.g(this.e.aq))) {
                String str = paramString.substring(2);
                a.info(this.e.aq + " issued server command: " + str);
                this.d.a(str, this);
            } else if (split[0].equalsIgnoreCase("/time")) {
                if (split.length != 2) {
                    msg(Colors.Rose + "Correct usage is: /time [time|day|night]");
                    return;
                }

                if (split[1].equalsIgnoreCase("day")) {
                    this.d.e.c = 0;
                } else if (split[1].equalsIgnoreCase("night")) {
                    this.d.e.c = 13000;
                } else {
                    try {
                        this.d.e.c = Long.parseLong(split[1]);
                    } catch (NumberFormatException e) {
                        msg(Colors.Rose + "Please enter numbers, not letters.");
                    }
                }
            } else if (split[0].equalsIgnoreCase("/getpos")) {
                msg("Pos X: " + e.l + " Y: " + e.m + " Z " + e.n);
                msg("Rotation X: " + e.r + " Y: " + e.s);

                double degreeRotation = ((e.r - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }
                msg("Compass: " + etc.getCompassPointForDirection(degreeRotation) + " (" + (Math.round(degreeRotation * 10) / 10.0) + ")");
            } else if (split[0].equalsIgnoreCase("/listplugins")) {
                msg(Colors.Rose + "Plugins" + Colors.White + ": " + etc.getInstance().getLoader().getPluginList());
            } else if (split[0].equalsIgnoreCase("/enableplugin")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /enableplugin [plugin]");
                    return;
                }

                etc.getInstance().getLoader().enablePlugin(split[1]);
                msg(Colors.Rose + "Plugin enabled.");
            } else if (split[0].equalsIgnoreCase("/disableplugin")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /enableplugin [plugin]");
                    return;
                }

                etc.getInstance().getLoader().disablePlugin(split[1]);
                msg(Colors.Rose + "Plugin disabled.");
            } else if (split[0].equalsIgnoreCase("/compass")) {
                double degreeRotation = ((e.r - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }

                msg(Colors.Rose + "Compass: " + etc.getCompassPointForDirection(degreeRotation));
            } else if (split[0].equalsIgnoreCase("/motd")) {
                for (String str : etc.getInstance().motd) {
                    msg(str);
                }
            } else {
                a.info(this.e.aq + " tried command " + paramString);
                msg(Colors.Rose + "Unknown command");
            }
        } catch (Exception ex) {
            a.log(Level.SEVERE, "Exception in command handler (Report this to hey0 unless you did something dumb like enter letters as numbers):", ex);
            if (etc.getInstance().isAdmin(e)) {
                msg(Colors.Rose + "Exception occured. Check the server for more info.");
            }
        }
    }

    public void a(o paramo) {
        if (paramo.b == 1) {
            this.e.z();
        }
    }

    public void a(io paramio) {
        this.b.a("Quitting");
    }

    public int b() {
        return this.b.d();
    }

    public void b(String paramString) {
        b(new ba("§7" + paramString));
    }

    public String c() {
        return this.e.aq;
    }

    public void a(r paramr) {
        if (paramr.a == -1) {
            this.e.aj.a = paramr.b;
        }
        if (paramr.a == -2) {
            this.e.aj.c = paramr.b;
        }
        if (paramr.a == -3) {
            this.e.aj.b = paramr.b;
        }
    }

    public void d() {
        this.b.a(new r(-1, this.e.aj.a));
        this.b.a(new r(-2, this.e.aj.c));
        this.b.a(new r(-3, this.e.aj.b));
    }

    public void a(ib paramib) {
        if (!etc.getInstance().canBuild(e))
            return;

        as localas = this.d.e.k(paramib.a, paramib.b, paramib.c);
        if (localas != null) {
            localas.a(paramib.e);
            localas.c();
        }
    }
}
