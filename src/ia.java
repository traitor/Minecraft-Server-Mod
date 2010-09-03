/* Used for sending packets, parsing chat, etc. */

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class ia extends eh implements ed {
    public static final Logger a = Logger.getLogger("Minecraft");
    public ba b;
    public boolean c = false;
    private List<String> onlyOneUseKits = new ArrayList<String>();
    private MinecraftServer d;
    private dy e;
    private int f = 0;
    private double g;
    private double h;
    private double i;
    private boolean j = true;

    public ia(MinecraftServer paramMinecraftServer, ba paramba, dy paramdy) {
        this.d = paramMinecraftServer;
        this.b = paramba;
        paramba.a(this);
        this.e = paramdy;
        paramdy.a = this;
    }

    public void a() {
        this.b.a();
        if (this.f++ % 20 == 0) {
            this.b.a(new iw());
        }
    }

    public void c(String paramString) {
        this.b.a(new il(paramString));
        this.b.c();
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(gd paramgd) {
        double d1;
        if (!this.j) {
            d1 = paramgd.b - this.h;
            if ((paramgd.a == this.g) && (d1 * d1 < 0.01D) && (paramgd.c == this.i)) {
                this.j = true;
            }
        }
        if (this.j) {
            this.g = this.e.k;
            this.h = this.e.l;
            this.i = this.e.m;

            d1 = this.e.k;
            double d2 = this.e.l;
            double d3 = this.e.m;

            float f1 = this.e.q;
            float f2 = this.e.r;

            if (paramgd.h) {
                d1 = paramgd.a;
                d2 = paramgd.b;
                d3 = paramgd.c;
                double d4 = paramgd.d - paramgd.b;
                if ((d4 > 1.65D) || (d4 < 0.1D)) {
                    c("Illegal stance");
                    a.log(Level.WARNING, e.ap + " had an illegal stance: " + d4);
                }
                this.e.ah = paramgd.d;
            }
            if (paramgd.i) {
                f1 = paramgd.e;
                f2 = paramgd.f;
            }

            this.e.i();
            this.e.L = 0.0F;
            this.e.b(this.g, this.h, this.i, f1, f2);

            double d4 = d1 - this.e.k;
            double d5 = d2 - this.e.l;
            double d6 = d3 - this.e.m;

            float f3 = 0.0625F;
            int k = this.d.e.a(this.e, this.e.u.b().e(f3, f3, f3)).isEmpty() ? 1 : 0;

            this.e.c(d4, d5, d6);
            d4 = d1 - this.e.k;
            d5 = d2 - this.e.l;
            if ((d5 > -0.5D) || (d5 < 0.5D)) {
                d5 = 0.0D;
            }
            d6 = d3 - this.e.m;
            double d7 = d4 * d4 + d5 * d5 + d6 * d6;
            int l = 0;
            if (d7 > 0.0625D) {
                l = 1;
                a.log(Level.WARNING, this.e.ap + " moved wrongly!");
            }
            this.e.b(d1, d2, d3, f1, f2);

            int i1 = this.d.e.a(this.e, this.e.u.b().e(f3, f3, f3)).isEmpty() ? 1 : 0;
            if ((k != 0) && (((l != 0) || (i1 == 0)))) {
                a(this.g, this.h, this.i, f1, f2);
                return;
            }

            this.e.v = paramgd.g;
            this.d.f.b(this.e);
        }
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        this.j = false;
        this.g = paramDouble1;
        this.h = paramDouble2;
        this.i = paramDouble3;
        this.e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        this.e.a.b(new _do(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }

    public void a(hb paramhb) {
        if (!etc.getInstance().canBuild(e)) {
            return;
        }

        boolean bool = this.d.f.g(this.e.ap) || etc.getInstance().isAdmin(e);
        this.d.e.x = true;

        int k = 0;
        if (paramhb.e == 0) {
            k = 1;
        }
        if (paramhb.e == 1) {
            k = 1;
        }

        if (k != 0) {
            double d1 = this.e.l;
            this.e.l = this.e.ah;
            fp localfp = this.e.a(4.0D, 1.0F);
            this.e.l = d1;
            if (localfp == null) {
                return;
            }
            if ((localfp.b != paramhb.a) || (localfp.c != paramhb.b) || (localfp.d != paramhb.c) || (localfp.e != paramhb.d)) {
                return;
            }
        }
        int l = paramhb.a;
        int i1 = paramhb.b;
        int i2 = paramhb.c;
        int i3 = paramhb.d;
        int i4 = (int) gh.e(l - this.d.e.m);
        int i5 = (int) gh.e(i2 - this.d.e.o);
        if (i4 > i5) {
            i5 = i4;
        }
        if (paramhb.e == 0) {
            if (i5 > etc.getInstance().spawnProtectionSize || bool) {
                this.e.ac.a(l, i1, i2);
            }
        } else if (paramhb.e == 2) {
            this.e.ac.a();
        } else if (paramhb.e == 1) {
            if (i5 > etc.getInstance().spawnProtectionSize || bool) {
                this.e.ac.a(l, i1, i2, i3);
            }
        } else if (paramhb.e == 3) {
            double d2 = this.e.k - (l + 0.5D);
            double d3 = this.e.l - (i1 + 0.5D);
            double d4 = this.e.m - (i2 + 0.5D);
            double d5 = d2 * d2 + d3 * d3 + d4 * d4;
            if (d5 < 256.0D) {
                this.e.a.b(new er(l, i1, i2, this.d.e));
            }
        }
        this.d.e.x = false;
    }

    public void a(fc paramfc) {
        if (!etc.getInstance().canBuild(e)) {
            return;
        }

        boolean bool = this.d.f.g(this.e.ap) || etc.getInstance().isAdmin(e);
        this.d.e.x = true;

        int k = paramfc.b; //x
        int l = paramfc.c; //y (height)
        int i1 = paramfc.d; //z
        int i2 = paramfc.e; //1-5?
        int i3 = (int) gh.e(k - this.d.e.m);
        int i4 = (int) gh.e(i1 - this.d.e.o);
        if (i3 > i4) {
            i4 = i3;
        }

        if (i4 > etc.getInstance().spawnProtectionSize || bool) {
            gn localgn = (paramfc.a >= 0) ? new gn(paramfc.a) : null;

            if (localgn != null) {
                if (!etc.getInstance().isOnItemBlacklist(localgn.c) || bool) {
                    this.e.ac.a(this.e, this.d.e, localgn, k, l, i1, i2);
                }
            } else {
                // is this right?
                this.e.ac.a(this.e, this.d.e, null, k, l, i1, i2);
            }
        }
        this.e.a.b(new er(k, l, i1, this.d.e));
        this.d.e.x = false;
    }

    public void a(String paramString) {
        a.log(Level.INFO, e.ap + " lost connection: " + paramString);
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(hn paramhn) {
        a.log(Level.WARNING, super.getClass() + " wasn't prepared to deal with a " + paramhn.getClass());
        c("Protocol error, unexpected packet");
    }

    public void b(hn paramhn) {
        this.b.a(paramhn);
    }

    public void a(ft paramft) {
        int k = paramft.b;
        if (k == 0) {
            this.e.ai.a[this.e.ai.d] = null;
        } else {
            this.e.ai.a[this.e.ai.d] = new gn(k);
        }
        this.d.k.a(this.e, new ft(this.e.c, k));
    }

    public void a(k paramk) {
        double d1 = paramk.b / 32.0D;
        double d2 = paramk.c / 32.0D;
        double d3 = paramk.d / 32.0D;
        fl localfl = new fl(this.d.e, d1, d2, d3, new gn(paramk.h, paramk.i));
        localfl.n = (paramk.e / 128.0D);
        localfl.o = (paramk.f / 128.0D);
        localfl.p = (paramk.g / 128.0D);
        localfl.ac = 10;
        this.d.e.a(localfl);
    }

    public void a(az paramaz) {
        String str = paramaz.a;
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

            String message = "<" + etc.getInstance().getUserColor(e.ap) + this.e.ap + Colors.White + "> " + str;
            a.log(Level.INFO, "<" + e.ap + "> " + str);
            this.d.f.a(new az(message));
        }
    }

    private dy match(String name) {
        dy player = null;
        boolean found = false;
        if (("`" + this.d.f.c().toUpperCase() + "`").split(name.toUpperCase()).length == 2) {
            for (int i = 0; i < this.d.f.b.size() && !found; ++i) {
                dy localdy = (dy) this.d.f.b.get(i);
                if (("`" + localdy.ap.toUpperCase() + "`").split(name.toUpperCase()).length == 2) {
                    player = localdy;
                    found = true;
                }
            }
        } else if (("`" + this.d.f.c() + "`").split(name).length > 2) {
            // Too many partial matches.
            for (int i = 0; i < this.d.f.b.size() && !found; ++i) {
                dy localdy = (dy) this.d.f.b.get(i);
                if (localdy.ap.equalsIgnoreCase(name)) {
                    player = localdy;
                    found = true;
                }
            }
        }
        return player;
    }

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

    public boolean hasControlOver(dy player) {
        boolean isInGroup = false;

        if (etc.getInstance().getUser(player.ap) != null) {
            for (String str : etc.getInstance().getUser(player.ap).Groups) {
                if (etc.getInstance().isUserInGroup(e, str)) {
                    isInGroup = true;
                }
            }
        } else {
            return true;
        }

        return isInGroup;
    }

    @SuppressWarnings("LoggerStringConcat")
    private void d(String paramString) {
        try {
            String[] split = paramString.split(" ");
            if (!etc.getInstance().canUseCommand(e.ap, split[0]) && !split[0].startsWith("/#")) {
                msg(Colors.Rose + "Unknown command.");
                return;
            }
            if (split[0].equalsIgnoreCase("/help")) {
                //Meh, not the greatest way, but not the worst either.
                List<String> availableCommands = new ArrayList<String>();
                for (Entry<String, String> entry : etc.getInstance().commands.entrySet()) {
                    if (etc.getInstance().canUseCommand(e.ap, entry.getKey())) {
                        if (entry.getKey().equals("/kit") && !etc.getInstance().hasKits()) {
                            continue;
                        }

                        availableCommands.add(entry.getKey() + " " + entry.getValue());
                    }
                }

                msg(Colors.Blue + "Available commands (Page " + (split.length == 2 ? split[1] : "1") + " of " + (int) Math.ceil((double) availableCommands.size() / (double) 7) + "):");
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

                dy player = match(split[1]);

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
                a.info("Modifed user " + split[1] + ". " + key + " => " + value + " by " + e.ap);
            } else if (split[0].equalsIgnoreCase("/whitelist")) {
                if (split.length != 3) {
                    msg(Colors.Rose + "whitelist [operation (add or remove)] [player]");
                    return;
                }

                if (split[1].equalsIgnoreCase("add")) {
                    etc.getInstance().getDataSource().addToWhitelist(split[2]);
                    msg(Colors.Rose + split[2] + " added to whitelist");
                } else if (split[1].equalsIgnoreCase("remove")) {
                    etc.getInstance().getDataSource().removeFromWhitelist(split[2]);
                    msg(Colors.Rose + split[2] + " removed from whitelist");
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

                dy player = match(split[1]);

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

                dy player = match(split[1]);

                if (player != null) {
                    if (player.ap.equals(e.ap)) {
                        msg(Colors.Rose + "You can't message yourself!");
                        return;
                    }
                    String prefix = etc.getInstance().getUserColor(e.ap);

                    player.a.msg("(MSG) " + prefix + "<" + e.ap + "> " + Colors.White + combineSplit(2, split, " "));
                    msg("(MSG) " + prefix + "<" + e.ap + "> " + Colors.White + combineSplit(2, split, " "));
                } else {
                    msg(Colors.Rose + "Couldn't find player " + split[1]);
                }
            } else if (split[0].equalsIgnoreCase("/kit") && etc.getInstance().hasKits()) {
                if (split.length != 2 && split.length != 3) {
                    msg(Colors.Rose + "Available kits: " + etc.getInstance().getKitNames(e));
                    return;
                }

                dy toGive = e;
                if (split.length > 2 && etc.getInstance().canIgnoreRestrictions(e)) {
                    toGive = match(split[1]);
                }

                Kit kit = etc.getInstance().getKit(split[1]);
                if (toGive != null) {
                    if (kit != null) {
                        if (!etc.getInstance().isUserInGroup(e, kit.Group) && !kit.Group.equals("")) {
                            msg(Colors.Rose + "That kit does not exist.");
                        } else if (onlyOneUseKits.contains(kit.Name)) {
                            msg(Colors.Rose + "You can only get this kit once per login.");
                        } else if (MinecraftServer.b.containsKey(this.e.ap + " " + kit.Name)) {
                            msg(Colors.Rose + "You can't get this kit again for a while.");
                        } else {
                            if (!etc.getInstance().canIgnoreRestrictions(e)) {
                                if (kit.Delay >= 0) {
                                    MinecraftServer.b.put(this.e.ap + " " + kit.Name, kit.Delay);
                                } else {
                                    onlyOneUseKits.add(kit.Name);
                                }
                            }

                            a.info(this.e.ap + " got a kit!");
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
                                            toGive.a(new gn(itemId, 64));
                                        } else {
                                            toGive.a(new gn(itemId, temp));
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

                dy player = match(split[1]);

                if (this.e.ap.equalsIgnoreCase(split[1])) {
                    msg(Colors.Rose + "You're already here!");
                    return;
                }

                if (player != null) {
                    a.info(this.e.ap + " teleported to " + player.ap);
                    a(player.k, player.l, player.m, player.q, player.r);
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if ((split[0].equalsIgnoreCase("/tphere") || split[0].equalsIgnoreCase("/s"))) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /tphere [player]");
                    return;
                }

                dy player = match(split[1]);

                if (this.e.ap.equalsIgnoreCase(split[1])) {
                    msg(Colors.Rose + "Wow look at that! You teleported yourself to yourself!");
                    return;
                }

                if (player != null) {
                    a.info(this.e.ap + " teleported " + player.ap + " to their self.");
                    player.a.a(e.k, e.l, e.m, e.q, e.r);
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

                dy toGive = e;
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
                        if (i3 <= 0) {
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
                        if (i2 < ex.c.length) {
                            if (ex.c[i2] != null && (allowedItem || etc.getInstance().canIgnoreRestrictions(this.e))) {
                                a.log(Level.INFO, "Giving " + toGive.ap + " some " + i2);
                                int temp = i3;

                                do {
                                    if (temp - 64 >= 64) {
                                        toGive.a(new gn(i2, 64));
                                    } else {
                                        toGive.a(new gn(i2, temp));
                                    }
                                    temp -= 64;
                                } while (temp >= 64);

                                if (toGive == this.e) {
                                    msg(Colors.Rose + "There you go c:");
                                } else {
                                    msg(Colors.Rose + "Gift given! :D");
                                    toGive.a.msg(Colors.Rose + "Enjoy your gift! :D");
                                }
                            } else if ((!allowedItem) && (ex.c[i2] != null) && !etc.getInstance().canIgnoreRestrictions(this.e)) {
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

            } else if (split[0].equalsIgnoreCase("/banip")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /banip [player] <reason> (optional) NOTE: this permabans IPs.");
                    return;
                }

                dy player = match(split[1]);

                if (player != null) {
                    if (!hasControlOver(player)) {
                        msg(Colors.Rose + "You can't ban that user.");
                        return;
                    }

                    // adds player to ban list
                    this.d.f.c(player.a.b.b().toString());

                    a.log(Level.INFO, "IP Banning " + player.ap + " (IP: " + player.a.b.b().toString() + ")");
                    msg(Colors.Rose + "IP Banning " + player.ap + " (IP: " + player.a.b.b().toString() + ")");

                    if (split.length > 2) {
                        player.a.c("IP Banned by " + e.ap + ": " + combineSplit(2, split, " "));
                    } else {
                        player.a.c("IP Banned by " + e.ap + ".");
                    }
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/ban")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /ban [player] <reason> (optional)");
                    return;
                }

                dy player = match(split[1]);

                if (player != null) {
                    if (!hasControlOver(player)) {
                        msg(Colors.Rose + "You can't ban that user.");
                        return;
                    }

                    // adds player to ban list
                    this.d.f.a(player.ap);

                    if (split.length > 2) {
                        player.a.c("Banned by " + e.ap + ": " + combineSplit(2, split, " "));
                    } else {
                        player.a.c("Banned by " + e.ap + ".");
                    }
                    a.log(Level.INFO, "Banning " + player.ap);
                    msg(Colors.Rose + "Banning " + player.ap);
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

                dy player = match(split[1]);

                if (player != null) {
                    if (!hasControlOver(player)) {
                        msg(Colors.Rose + "You can't kick that user.");
                        return;
                    }

                    if (split.length > 2) {
                        player.a.c("Kicked by " + e.ap + ": " + combineSplit(2, split, " "));
                    } else {
                        player.a.c("Kicked by " + e.ap + ".");
                    }
                    a.log(Level.INFO, "Kicking " + player.ap);
                    msg(Colors.Rose + "Kicking " + player.ap);
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/me")) {
                if (etc.getInstance().isMuted(e)) {
                    msg(Colors.Rose + "You are currently muted.");
                    return;
                }
                String prefix = etc.getInstance().getUserColor(e.ap);
                String paramString2 = "* " + prefix + this.e.ap + Colors.White + " " + paramString.substring(paramString.indexOf(" ")).trim();
                a.info("* " + this.e.ap + " " + paramString.substring(paramString.indexOf(" ")).trim());
                this.d.f.a(new az(paramString2));
            } else if (split[0].equalsIgnoreCase("/sethome")) {
                // player.k, player.l, player.m
                // x, y, z
                Location loc = new Location();
                loc.x = e.k;
                loc.y = e.l;
                loc.z = e.m;
                loc.rotX = e.q;
                loc.rotY = e.r;
                etc.getInstance().changeHome(e.ap, loc);
                msg(Colors.Rose + "Your home has been set.");
            } else if (split[0].equalsIgnoreCase("/spawn")) {
                int k = this.d.e.d(this.d.e.m, this.d.e.o);
                a(this.d.e.m + 0.5D, k + 1.5D, this.d.e.o + 0.5D, 0.0F, 0.0F);
            } else if (split[0].equalsIgnoreCase("/setspawn")) {
                this.d.e.m = (int) Math.ceil(e.k);
                this.d.e.n = (int) Math.ceil(e.l); //Not that the Y axis really matters since it tries to get the highest point iirc.
                this.d.e.o = (int) Math.ceil(e.m);
                a.info("Spawn position changed.");
                msg(Colors.Rose + "You have set the spawn to your current position.");
            } else if (split[0].equalsIgnoreCase("/home")) {
                a.info(this.e.ap + " returned home");
                Location loc = etc.getInstance().getDataSource().getHome(e.ap);
                if (loc != null) {
                    a(loc.x, loc.y, loc.z, loc.rotX, loc.rotY);
                } else {
                    int k = this.d.e.d(this.d.e.m, this.d.e.o);
                    a(this.d.e.m + 0.5D, k + 1.5D, this.d.e.o + 0.5D, 0.0F, 0.0F);
                }
            } else if (split[0].equalsIgnoreCase("/warp")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /warp [warpname]");
                    return;
                }
                dy toWarp = e;
                Location loc = null;
                if (split.length == 3 && etc.getInstance().canIgnoreRestrictions(e)) {
                    loc = etc.getInstance().getDataSource().getWarp(split[1]);
                    toWarp = match(split[2]);
                } else {
                    loc = etc.getInstance().getDataSource().getWarp(split[1]);
                }
                if (toWarp != null) {
                    if (loc != null) {
                        toWarp.a.a(loc.x, loc.y, loc.z, loc.rotX, loc.rotY);
                        toWarp.a.msg(Colors.Rose + "Woosh!");
                    } else {
                        msg(Colors.Rose + "Warp not found");
                    }
                } else {
                    msg(Colors.Rose + "Player not found.");
                }
            } else if (split[0].equalsIgnoreCase("/setwarp")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /setwarp [warpname]");
                    return;
                }
                Location loc = new Location();
                loc.x = e.k;
                loc.y = e.l;
                loc.z = e.m;
                loc.rotX = e.q;
                loc.rotY = e.r;
                etc.getInstance().setWarp(split[1], loc);
                msg(Colors.Rose + "Created warp point " + split[1] + ".");
            } else if (split[0].equalsIgnoreCase("/lighter")) {
                if (MinecraftServer.b.containsKey(this.e.ap + " lighter")) {
                    a.info(this.e.ap + " failed to iron!");
                    msg(Colors.Rose + "You can't create another lighter again so soon");
                } else {
                    if (!etc.getInstance().canIgnoreRestrictions(e)) {
                        MinecraftServer.b.put(this.e.ap + " lighter", Integer.valueOf(6000));
                    }
                    a.info(this.e.ap + " created a lighter!");
                    this.e.a(new gn(ex.g, 1));
                }
            } else if ((paramString.startsWith("/#")) && (this.d.f.g(this.e.ap))) {
                String str = paramString.substring(2);
                a.info(this.e.ap + " issued server command: " + str);
                this.d.a(str, this);
            } else if (split[0].equalsIgnoreCase("/time")) {
                if (split.length != 2) {
                    msg(Colors.Rose + "Correct usage is: /time [time|day|night]");
                    return;
                }

                if (split[1].equalsIgnoreCase("day")) {
                    this.d.e.c = 0; //morning.
                } else if (split[1].equalsIgnoreCase("night")) {
                    this.d.e.c = 500000; //approx value for midnight basically
                } else {
                    try {
                        this.d.e.c = Long.parseLong(split[1]);
                    } catch (NumberFormatException e) {
                        msg(Colors.Rose + "Please enter numbers, not letters.");
                    }
                }
            } else if (split[0].equalsIgnoreCase("/getpos")) {
                msg("Pos X: " + e.k + " Y: " + e.l + " Z " + e.m);
                msg("Rotation X: " + e.q + " Y: " + e.r);

                double degreeRotation = ((e.q - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }
                msg("Compass: " + etc.getCompassPointForDirection(degreeRotation) + " (" + (Math.round(degreeRotation * 10) / 10.0) + ")");
            } else if (split[0].equalsIgnoreCase("/compass")) {
                double degreeRotation = ((e.q - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }

                msg(Colors.Rose + "Compass: " + etc.getCompassPointForDirection(degreeRotation));
            } else if (split[0].equalsIgnoreCase("/motd")) {
                for (String str : etc.getInstance().motd) {
                    msg(str);
                }
            } else {
                a.info(this.e.ap + " tried command " + paramString);
                msg(Colors.Rose + "Unknown command");
            }
        } catch (Exception ex) {
            a.log(Level.SEVERE, "Exception in command handler (Report this to hey0 unless you did something dumb like enter letters as numbers):", ex);
            if (etc.getInstance().isAdmin(e)) {
                msg(Colors.Rose + "Exception occured. Check the server for more info.");
            }
        }
    }

    public void msg(String msg) {
        b(new az(msg));
    }

    public void a(o paramo) {
        if (paramo.b == 1) {
            this.e.y();
        }
    }

    public void a(il paramil) {
        this.b.a("Quitting");
    }

    public int b() {
        return this.b.d();
    }

    public void b(String paramString) {
        b(new az(Colors.LightGray + paramString));
    }

    public String c() {
        return this.e.ap;
    }
}
