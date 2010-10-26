
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class id extends ej
        implements ef {

    public static final Logger a = Logger.getLogger("Minecraft");
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

    /**
     * Returns the item in player's hand
     * @return
     */
    public int getItemInHand() {
        if (k != null) //Check to see if we are even holding anything
        {
            return k.c;
        }
        return -1;
    }

    /**
     * Returns the player for this class
     * @return
     */
    public Player getPlayer() {
        return e.getPlayer();
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
        if ((int) Math.floor(g) != (int) Math.floor(e.l) || (int) Math.floor(h) != (int) Math.floor(e.m) || (int) Math.floor(i) != (int) Math.floor(e.n)) {
            Location from = new Location();
            from.x = (int) Math.floor(g);
            from.y = (int) Math.floor(h);
            from.z = (int) Math.floor(i);
            from.rotX = e.r;
            from.rotY = e.s;

            Location to = new Location();
            to.x = (int) Math.floor(e.l);
            to.y = (int) Math.floor(e.m);
            to.z = (int) Math.floor(e.n);
            to.rotX = e.r;
            to.rotY = e.s;

            etc.getLoader().callHook(PluginLoader.Hook.PLAYER_MOVE, new Object[]{e, from, to});
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
                    a.warning(getPlayer().getName() + " had an illegal stance: " + d4);
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
                a.warning(getPlayer().getName() + " moved wrongly!");
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
        Location from = new Location();
        from.x = paramDouble1;
        from.y = paramDouble2;
        from.z = paramDouble3;
        from.rotX = paramFloat1;
        from.rotY = paramFloat2;
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.TELEPORT, new Object[]{e, e.getPlayer().getLocation(), from})) {
            return;
        }
        this.j = false;
        this.g = paramDouble1;
        this.h = paramDouble2;
        this.i = paramDouble3;
        this.e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        this.e.a.b(new dq(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }
    int x, y, z, type;
    //Destroy function

    public void a(hd paramhd) {
        this.e.aj.a[this.e.aj.d] = this.k;
        boolean bool = this.d.e.z = (this.d.f.g(getPlayer().getName()) || getPlayer().isAdmin());
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
            if (!getPlayer().canBuild()) {
                return;
            }
            if (i5 > etc.getInstance().getSpawnProtectionSize() || bool) {
                Block block = etc.getServer().getBlockAt(n, i1, i2);
                block.setStatus(0); //Started digging
                x = block.getX();
                y = block.getY();
                z = block.getZ();
                type = block.getType();
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block})) {
                    this.e.ad.a(n, i1, i2);
                }
            }
        } else if (paramhd.e == 2) {
            Block block = etc.getServer().getBlockAt(n, i1, i2);
            block.setStatus(2); //Stopped digging
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});

            this.e.ad.a();
        } else if (paramhd.e == 1) {
            if (!getPlayer().canBuild()) {
                return;
            }
            if (i5 > etc.getInstance().getSpawnProtectionSize() || bool) {
                Block block = etc.getServer().getBlockAt(n, i1, i2);
                block.setStatus(1); //Digging
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block})) {
                    this.e.ad.a(n, i1, i2, i3);
                }
            }
        } else if (paramhd.e == 3) {
            Block block = new Block(type, x, y, z);
            block.setStatus(3);
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});

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
        if (!getPlayer().canBuild()) {
            return;
        }
        boolean bool = this.d.e.z = (this.d.f.g(getPlayer().getName()) || getPlayer().isAdmin());
        int m = paramfe.b;
        int n = paramfe.c;
        int i1 = paramfe.d;
        int i2 = paramfe.e;
        int i3 = (int) gj.e(m - this.d.e.n);
        int i4 = (int) gj.e(i1 - this.d.e.p);
        if (i3 > i4) {
            i4 = i3;
        }
        if (i4 > etc.getInstance().getSpawnProtectionSize() || bool) {
            gp localgp = paramfe.a >= 0 ? new gp(paramfe.a) : null;

            Block blockPlaced = new Block(localgp != null ? localgp.c : paramfe.a, m, n, i1);
            if (paramfe.e == 0) {
                blockPlaced.setY(blockPlaced.getY() - 1);
            } else if (paramfe.e == 1) {
                blockPlaced.setY(blockPlaced.getY() + 1);
            } else if (paramfe.e == 2) {
                blockPlaced.setZ(blockPlaced.getZ() - 1);
            } else if (paramfe.e == 3) {
                blockPlaced.setZ(blockPlaced.getZ() + 1);
            } else if (paramfe.e == 4) {
                blockPlaced.setX(blockPlaced.getX() - 1);
            } else if (paramfe.e == 5) {
                blockPlaced.setX(blockPlaced.getX() + 1);
            }
            Block blockClicked = new Block(etc.getServer().getBlockIdAt(m, n, i1), m, n, i1);
            blockClicked.setFaceClicked(Block.Face.fromId(paramfe.e));

            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_CREATED, new Object[]{e, blockPlaced, blockClicked, paramfe.a})) {
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
        etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, new Object[]{e});
        a.info(getPlayer().getName() + " lost connection: " + paramString);
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

    //Player changed item in hand
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
            if (getPlayer().isMuted()) {
                msg(Colors.Rose + "You are currently muted.");
                return;
            }
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.CHAT, new Object[]{e, str})) {
                return;
            }

            String message = "<" + getPlayer().getColor() + getPlayer().getName() + Colors.White + "> " + str;
            a.log(Level.INFO, "<" + getPlayer().getName() + "> " + str);
            this.d.f.a(new ba(message));
        }
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
                a.info("Command used by " + getPlayer().getName() + " " + paramString);
            }
            String[] split = paramString.split(" ");
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMMAND, new Object[]{e, split})) {
                return; //No need to go on, commands were parsed.
            }
            if (!getPlayer().canUseCommand(split[0]) && !split[0].startsWith("/#")) {
                if (etc.getInstance().showUnknownCommand()) {
                    msg(Colors.Rose + "Unknown command.");
                }
                return;
            }
            if (split[0].equalsIgnoreCase("/help")) {
                //Meh, not the greatest way, but not the worst either.
                List<String> availableCommands = new ArrayList<String>();
                for (Entry<String, String> entry : etc.getInstance().getCommands().entrySet()) {
                    if (getPlayer().canUseCommand(entry.getKey())) {
                        if (entry.getKey().equals("/kit") && !etc.getDataSource().hasKits()) {
                            continue;
                        }
                        if (entry.getKey().equals("/listwarps") && !etc.getDataSource().hasWarps()) {
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
                for (Player player : etc.getServer().getPlayerList()) {
                    player.getUser().reloadPlayer();
                }
                a.info("Reloaded config");
                msg("Successfuly reloaded config");
            } else if ((split[0].equalsIgnoreCase("/modify") || split[0].equalsIgnoreCase("/mp"))) {
                if (split.length > 2 && split[2].contains(":")) {
                    for (int i = 3; i < split.length; i++) {
                        if (!split[i].contains(":")) {
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
                    }

                    Player player = etc.getServer().matchPlayer(split[1]);

                    if (player == null) {
                        msg(Colors.Rose + "Player does not exist.");
                        return;
                    }

                    for (int i = 2; i < split.length; i++) {
                        if (split[i].split(":").length != 2) {
                            msg("This key:value pair is deformed... " + split[i]);
                            return;
                        }
                        String key = split[i].split(":")[0];
                        String value = split[i].split(":")[1];
                        boolean newUser = false;

                        if (!etc.getDataSource().doesPlayerExist(player.getName())) {
                            if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                                msg(Colors.Rose + "When adding a new user, set their group(s) first.");
                                return;
                            }
                            msg(Colors.Rose + "Adding new user.");
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
                        a.info("Modifed user " + split[1] + ". " + key + " => " + value + " by " + getPlayer().getName());
                    }
                    msg(Colors.Rose + "Modified user.");
                } else {
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

                    Player player = etc.getServer().matchPlayer(split[1]);

                    if (player == null) {
                        msg(Colors.Rose + "Player does not exist.");
                        return;
                    }

                    String key = split[2];
                    String value = split[3];
                    boolean newUser = false;

                    if (!etc.getDataSource().doesPlayerExist(player.getName())) {
                        if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                            msg(Colors.Rose + "When adding a new user, set their group(s) first.");
                            return;
                        }
                        msg(Colors.Rose + "Adding new user.");
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
                    msg(Colors.Rose + "Modified user.");
                    a.info("Modifed user " + split[1] + ". " + key + " => " + value + " by " + getPlayer().getName());
                }
            } else if (split[0].equalsIgnoreCase("/whitelist")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "whitelist [operation (toggle, add or remove)] <player>");
                    return;
                }

                if (split[1].equalsIgnoreCase("toggle")) {
                    msg(Colors.Rose + (etc.getInstance().toggleWhitelist() ? "Whitelist enabled" : "Whitelist disabled"));
                } else if (split.length == 3) {
                    if (split[1].equalsIgnoreCase("add")) {
                        etc.getDataSource().addToWhitelist(split[2]);
                        msg(Colors.Rose + split[2] + " added to whitelist");
                    } else if (split[1].equalsIgnoreCase("remove")) {
                        etc.getDataSource().removeFromWhitelist(split[2]);
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
                    etc.getDataSource().addToReserveList(split[2]);
                    msg(Colors.Rose + split[2] + " added to reservelist");
                } else if (split[1].equalsIgnoreCase("remove")) {
                    etc.getDataSource().removeFromReserveList(split[2]);
                    msg(Colors.Rose + split[2] + " removed from reservelist");
                } else {
                    msg(Colors.Rose + "Invalid operation.");
                }
            } else if (split[0].equalsIgnoreCase("/mute")) {
                if (split.length != 2) {
                    msg(Colors.Rose + "Correct usage is: /mute [player]");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (player.toggleMute()) {
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
                if (getPlayer().isMuted()) {
                    msg(Colors.Rose + "You are currently muted.");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (player.getName().equals(getPlayer().getName())) {
                        msg(Colors.Rose + "You can't message yourself!");
                        return;
                    }
                    String prefix = getPlayer().getColor();

                    player.sendMessage("(MSG) " + prefix + "<" + getPlayer().getName() + "> " + Colors.White + etc.combineSplit(2, split, " "));
                    msg("(MSG) " + prefix + "<" + getPlayer().getName() + "> " + Colors.White + etc.combineSplit(2, split, " "));
                } else {
                    msg(Colors.Rose + "Couldn't find player " + split[1]);
                }
            } else if (split[0].equalsIgnoreCase("/kit") && etc.getDataSource().hasKits()) {
                if (split.length != 2 && split.length != 3) {
                    msg(Colors.Rose + "Available kits" + Colors.White + ": " + etc.getDataSource().getKitNames(getPlayer()));
                    return;
                }

                Player toGive = getPlayer();
                if (split.length > 2 && getPlayer().canIgnoreRestrictions()) {
                    toGive = etc.getServer().matchPlayer(split[1]);
                }

                Kit kit = etc.getDataSource().getKit(split[1]);
                if (toGive != null) {
                    if (kit != null) {
                        if (!getPlayer().isInGroup(kit.Group) && !kit.Group.equals("")) {
                            msg(Colors.Rose + "That kit does not exist.");
                        } else if (onlyOneUseKits.contains(kit.Name)) {
                            msg(Colors.Rose + "You can only get this kit once per login.");
                        } else if (MinecraftServer.b.containsKey(getPlayer().getName() + " " + kit.Name)) {
                            msg(Colors.Rose + "You can't get this kit again for a while.");
                        } else {
                            if (!getPlayer().canIgnoreRestrictions()) {
                                if (kit.Delay >= 0) {
                                    MinecraftServer.b.put(getPlayer().getName() + " " + kit.Name, kit.Delay);
                                } else {
                                    onlyOneUseKits.add(kit.Name);
                                }
                            }

                            a.info(getPlayer().getName() + " got a kit!");
                            toGive.sendMessage(Colors.Rose + "Enjoy this kit!");
                            for (Map.Entry<String, Integer> entry : kit.IDs.entrySet()) {
                                try {
                                    int itemId = 0;
                                    try {
                                        itemId = Integer.parseInt(entry.getKey());
                                    } catch (NumberFormatException n) {
                                        itemId = etc.getDataSource().getItem(entry.getKey());
                                    }

                                    toGive.giveItem(itemId, kit.IDs.get(entry.getKey()));
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

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (getPlayer().getName().equalsIgnoreCase(player.getName())) {
                        msg(Colors.Rose + "You're already here!");
                        return;
                    }

                    a.info(getPlayer().getName() + " teleported to " + player.getName());
                    getPlayer().teleportTo(player);
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if ((split[0].equalsIgnoreCase("/tphere") || split[0].equalsIgnoreCase("/s"))) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /tphere [player]");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (getPlayer().getName().equalsIgnoreCase(player.getName())) {
                        msg(Colors.Rose + "Wow look at that! You teleported yourself to yourself!");
                        return;
                    }

                    a.info(getPlayer().getName() + " teleported " + player.getName() + " to their self.");
                    player.teleportTo(getPlayer());
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/playerlist") || split[0].equalsIgnoreCase("/who")) {
                msg(Colors.Rose + "Player list (" + d.f.b.size() + "/" + etc.getInstance().getPlayerLimit() + "): " + Colors.White + d.f.c());
            } else if (split[0].equalsIgnoreCase("/item") || split[0].equalsIgnoreCase("/i") || split[0].equalsIgnoreCase("/give")) {
                if (split.length < 2) {
                    if (getPlayer().canIgnoreRestrictions()) {
                        msg(Colors.Rose + "Correct usage is: /item [itemid] <amount> <player> (optional)");
                    } else {
                        msg(Colors.Rose + "Correct usage is: /item [itemid] <amount>");
                    }
                    return;
                }

                Player toGive = getPlayer();
                if (split.length == 4 && getPlayer().canIgnoreRestrictions()) {
                    toGive = etc.getServer().matchPlayer(split[3]);
                }

                if (toGive != null) {
                    try {
                        int itemId = 0;
                        try {
                            itemId = Integer.parseInt(split[1]);
                        } catch (NumberFormatException n) {
                            itemId = etc.getDataSource().getItem(split[1]);
                        }
                        int amount = 1;
                        if (split.length > 2) {
                            amount = Integer.parseInt(split[2]);
                        }

                        String itemIdstr = Integer.toString(itemId);
                        if (amount <= 0 && !getPlayer().isAdmin()) {
                            amount = 1;
                        }

                        if (amount > 64 && !getPlayer().canIgnoreRestrictions()) {
                            amount = 64;
                        }
                        if (amount > 1024) {
                            amount = 1024; //16 stacks worth. More than enough.
                        }
                        boolean allowedItem = false;
                        if (!etc.getInstance().getAllowedItems()[0].equals("") && (!getPlayer().canIgnoreRestrictions())) {
                            for (String str : etc.getInstance().getAllowedItems()) {
                                if (itemIdstr.equals(str)) {
                                    allowedItem = true;
                                }
                            }
                        } else {
                            allowedItem = true;
                        }
                        if (!etc.getInstance().getDisallowedItems()[0].equals("") && !getPlayer().canIgnoreRestrictions()) {
                            for (String str : etc.getInstance().getDisallowedItems()) {
                                if (itemIdstr.equals(str)) {
                                    allowedItem = false;
                                }
                            }
                        }
                        if (Item.isValidItem(itemId)) {
                            if (allowedItem || getPlayer().canIgnoreRestrictions()) {
                                a.log(Level.INFO, "Giving " + toGive.getName() + " some " + itemId);
                                toGive.giveItem(itemId, amount);

                                if (toGive.getName().equalsIgnoreCase(getPlayer().getName())) {
                                    msg(Colors.Rose + "There you go c:");
                                } else {
                                    msg(Colors.Rose + "Gift given! :D");
                                    toGive.sendMessage(Colors.Rose + "Enjoy your gift! :D");
                                }
                            } else if (!allowedItem && !getPlayer().canIgnoreRestrictions()) {
                                msg(Colors.Rose + "You are not allowed to spawn that item.");
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

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (!getPlayer().hasControlOver(player)) {
                        msg(Colors.Rose + "You can't ban that user.");
                        return;
                    }

                    // adds player to ban list
                    this.d.f.c(player.getIP());

                    etc.getLoader().callHook(PluginLoader.Hook.IPBAN, new Object[]{getPlayer().getUser(), player.getUser(), split.length >= 3 ? etc.combineSplit(2, split, " ") : ""});

                    a.log(Level.INFO, "IP Banning " + player.getName() + " (IP: " + player.getIP() + ")");
                    msg(Colors.Rose + "IP Banning " + player.getName() + " (IP: " + player.getIP() + ")");

                    if (split.length > 2) {
                        player.kick("IP Banned by " + getPlayer().getName() + ": " + etc.combineSplit(2, split, " "));
                    } else {
                        player.kick("IP Banned by " + getPlayer().getName() + ".");
                    }
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/ban")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /ban [player] <reason> (optional)");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (!getPlayer().hasControlOver(player)) {
                        msg(Colors.Rose + "You can't ban that user.");
                        return;
                    }

                    // adds player to ban list
                    this.d.f.a(player.getName());

                    etc.getLoader().callHook(PluginLoader.Hook.BAN, new Object[]{getPlayer().getUser(), player.getUser(), split.length >= 3 ? etc.combineSplit(2, split, " ") : ""});

                    if (split.length > 2) {
                        player.kick("Banned by " + getPlayer().getName() + ": " + etc.combineSplit(2, split, " "));
                    } else {
                        player.kick("Banned by " + getPlayer().getName() + ".");
                    }
                    a.log(Level.INFO, "Banning " + player.getName());
                    msg(Colors.Rose + "Banning " + player.getName());
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

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (!getPlayer().hasControlOver(player)) {
                        msg(Colors.Rose + "You can't kick that user.");
                        return;
                    }

                    etc.getLoader().callHook(PluginLoader.Hook.KICK, new Object[]{getPlayer().getUser(), player.getUser(), split.length >= 3 ? etc.combineSplit(2, split, " ") : ""});

                    if (split.length > 2) {
                        player.kick("Kicked by " + getPlayer().getName() + ": " + etc.combineSplit(2, split, " "));
                    } else {
                        player.kick("Kicked by " + getPlayer().getName() + ".");
                    }
                    a.log(Level.INFO, "Kicking " + player.getName());
                    msg(Colors.Rose + "Kicking " + player.getName());
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/me")) {
                if (getPlayer().isMuted()) {
                    msg(Colors.Rose + "You are currently muted.");
                    return;
                }
                if (split.length == 1) {
                    return;
                }
                String prefix = getPlayer().getColor();
                String paramString2 = "* " + prefix + getPlayer().getName() + Colors.White + " " + paramString.substring(paramString.indexOf(" ")).trim();
                a.info("* " + getPlayer().getName() + " " + paramString.substring(paramString.indexOf(" ")).trim());
                this.d.f.a(new ba(paramString2));
            } else if (split[0].equalsIgnoreCase("/sethome")) {
                // player.k, player.l, player.m
                // x, y, z
                Warp home = new Warp();
                home.Location = getPlayer().getLocation();
                home.Group = ""; //no group neccessary, lol.
                home.Name = getPlayer().getName();
                etc.getInstance().changeHome(home);
                msg(Colors.Rose + "Your home has been set.");
            } else if (split[0].equalsIgnoreCase("/spawn")) {
                int m = this.d.e.d(this.d.e.n, this.d.e.p);
                a(this.d.e.n + 0.5D, m + 1.5D, this.d.e.p + 0.5D, 0.0F, 0.0F);
            } else if (split[0].equalsIgnoreCase("/setspawn")) {
                this.d.e.n = (int) Math.ceil(getPlayer().getX());
                //Too lazy to actually update this considering it's not even used anyways.
                //this.d.e.n = (int) Math.ceil(e.m); //Not that the Y axis really matters since it tries to get the highest point iirc.
                this.d.e.p = (int) Math.ceil(getPlayer().getZ());
                a.info("Spawn position changed.");
                msg(Colors.Rose + "You have set the spawn to your current position.");
            } else if (split[0].equalsIgnoreCase("/home")) {
                Warp home = null;
                if (split.length > 1 && getPlayer().isAdmin()) {
                    home = etc.getDataSource().getHome(split[1]);
                } else {
                    home = etc.getDataSource().getHome(getPlayer().getName());
                }

                if (home != null) {
                    getPlayer().teleportTo(home.Location);
                } else if (split.length > 1 && getPlayer().isAdmin()) {
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
                Player toWarp = getPlayer();
                Warp warp = null;
                if (split.length == 3 && getPlayer().canIgnoreRestrictions()) {
                    warp = etc.getDataSource().getWarp(split[1]);
                    toWarp = etc.getServer().matchPlayer(split[2]);
                } else {
                    warp = etc.getDataSource().getWarp(split[1]);
                }
                if (toWarp != null) {
                    if (warp != null) {
                        if (!getPlayer().isInGroup(warp.Group) && !warp.Group.equals("")) {
                            msg(Colors.Rose + "Warp not found.");
                        } else {
                            toWarp.teleportTo(warp.Location);
                            toWarp.sendMessage(Colors.Rose + "Woosh!");
                        }
                    } else {
                        msg(Colors.Rose + "Warp not found");
                    }
                } else {
                    msg(Colors.Rose + "Player not found.");
                }
            } else if (split[0].equalsIgnoreCase("/listwarps") && etc.getDataSource().hasWarps()) {
                if (split.length != 2 && split.length != 3) {
                    msg(Colors.Rose + "Available warps: " + Colors.White + etc.getDataSource().getWarpNames(getPlayer()));
                    return;
                }
            } else if (split[0].equalsIgnoreCase("/setwarp")) {
                if (split.length < 2) {
                    if (getPlayer().canIgnoreRestrictions()) {
                        msg(Colors.Rose + "Correct usage is: /setwarp [warpname] [group]");
                    } else {
                        msg(Colors.Rose + "Correct usage is: /setwarp [warpname]");
                    }
                    return;
                }
                if (split[1].contains(":")) {
                    msg("You can't set a warp with \":\" in its name");
                    return;
                }
                Warp warp = new Warp();
                warp.Name = split[1];
                warp.Location = getPlayer().getLocation();
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
                Warp warp = etc.getDataSource().getWarp(split[1]);
                if (warp != null) {
                    etc.getDataSource().removeWarp(warp);
                    msg(Colors.Blue + "Warp removed.");
                } else {
                    msg(Colors.Rose + "That warp does not exist");
                }
            } else if (split[0].equalsIgnoreCase("/lighter")) {
                if (MinecraftServer.b.containsKey(getPlayer().getName() + " lighter")) {
                    a.info(getPlayer().getName() + " failed to iron!");
                    msg(Colors.Rose + "You can't create another lighter again so soon");
                } else {
                    if (!getPlayer().canIgnoreRestrictions()) {
                        MinecraftServer.b.put(getPlayer().getName() + " lighter", Integer.valueOf(6000));
                    }
                    a.info(getPlayer().getName() + " created a lighter!");
                    getPlayer().giveItem(259, 1);
                }
            } else if ((paramString.startsWith("/#")) && (this.d.f.g(getPlayer().getName()))) {
                String str = paramString.substring(2);
                a.info(getPlayer().getName() + " issued server command: " + str);
                this.d.a(str, this);
            } else if (split[0].equalsIgnoreCase("/time")) {
                if (split.length == 2) {
                    if (split[1].equalsIgnoreCase("day")) {
                        etc.getServer().setRelativeTime(0);
                    } else if (split[1].equalsIgnoreCase("night")) {
                        etc.getServer().setRelativeTime(13000);
                    } else if (split[1].equalsIgnoreCase("check")) {
                        msg(Colors.Rose + "The time is " + etc.getServer().getRelativeTime() + "! (RAW: " + etc.getServer().getTime() + ")");
                    } else {
                        try {
                            etc.getServer().setRelativeTime(Long.parseLong(split[1]));
                        } catch (NumberFormatException ex) {
                            msg(Colors.Rose + "Please enter numbers, not letters.");
                        }
                    }
                } else if (split.length == 3) {
                    if (split[1].equalsIgnoreCase("raw")) {
                        try {
                            etc.getServer().setTime(Long.parseLong(split[2]));
                        } catch (NumberFormatException ex) {
                            msg(Colors.Rose + "Please enter numbers, not letters.");
                        }
                    }
                } else {
                    msg(Colors.Rose + "Correct usage is: /time [time|'day|night|check|raw'] (rawtime)");
                    return;
                }
            } else if (split[0].equalsIgnoreCase("/getpos")) {
                Player p = getPlayer();
                msg("Pos X: " + p.getX() + " Y: " + p.getY() + " Z: " + p.getZ());
                msg("Rotation: " + p.getRotation() + " Pitch: " + p.getPitch());

                double degreeRotation = ((p.getRotation() - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }
                msg("Compass: " + etc.getCompassPointForDirection(degreeRotation) + " (" + (Math.round(degreeRotation * 10) / 10.0) + ")");
            } else if (split[0].equalsIgnoreCase("/listplugins")) {
                msg(Colors.Rose + "Plugins" + Colors.White + ": " + etc.getLoader().getPluginList());
            } else if (split[0].equalsIgnoreCase("/reloadplugin")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /reloadplugin [plugin]");
                    return;
                }

                etc.getLoader().reloadPlugin(split[1]);
                msg(Colors.Rose + "Plugin reloaded.");
            } else if (split[0].equalsIgnoreCase("/enableplugin")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /enableplugin [plugin]");
                    return;
                }

                etc.getLoader().enablePlugin(split[1]);
                msg(Colors.Rose + "Plugin enabled.");
            } else if (split[0].equalsIgnoreCase("/disableplugin")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /enableplugin [plugin]");
                    return;
                }

                etc.getLoader().disablePlugin(split[1]);
                msg(Colors.Rose + "Plugin disabled.");
            } else if (split[0].equalsIgnoreCase("/compass")) {
                double degreeRotation = ((getPlayer().getRotation() - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }

                msg(Colors.Rose + "Compass: " + etc.getCompassPointForDirection(degreeRotation));
            } else if (split[0].equalsIgnoreCase("/motd")) {
                for (String str : etc.getInstance().getMotd()) {
                    msg(str);
                }
            } else if (split[0].equalsIgnoreCase("/spawnmob")) {
                if (split.length == 1) {
                    msg(Colors.Rose + "Correct usage is: /spawnmob [name] <amount>");
                    return;
                }
                if (!Mob.isValid(split[1])) {
                    msg(Colors.Rose + "Invalid mob. Name has to start with a capital like so: Pig");
                    return;
                }

                if (split.length == 2) {
                    Mob mob = new Mob(split[1], getPlayer().getLocation());
                    mob.spawn();
                }
                
                if (split.length == 3) {
                		try {
                				int mobnumber = Integer.parseInt(split[2]);
                				for (int i = 0; i < mobnumber; i++) {
                						mob.spawn();
                				}
                		} catch (NumberFormatException nfe) {
                				if(!Mob.isValid(split[2])) {
                						msg(Colors.Rose + "Invalid mob name or number of mobs.");
                						msg(Colors.Rose + "Mob names have to start with a capital like so: Pig");
                				} else {
                						Mob mob = new Mob(split[1], getPlayer().getLocation());
                						mob.spawn(new Mob(split[2]));
                				}
                		}
                } else if (split.length == 4) {
                		try {
                				int mobnumber = Integer.parseInt(split[3]);
                				if(!Mob.isValid(split[2])) {
                						msg(Colors.Rose + "Invalid rider. Name has to start with a capital like so: Pig");
                				} else {
                						for (int i = 0; i < mobnumber; i++) {
                								Mob mob = new Mob(split[1], getPlayer().getLocation());
                								mob.spawn(new Mob(split[2]));
                						}
                				}
                		} catch (NumberFormatException nfe) {
                				msg(Colors.Rose + "Invalid number of mobs.");
                		}
                }
            } else if (split[0].equalsIgnoreCase("/clearinventory")) {
                Player target = getPlayer();
                if (split.length >= 2 && getPlayer().isAdmin()) {
                    target = etc.getServer().matchPlayer(split[1]);
                }
                if (target != null) {
                    Inventory inv = target.getInventory();
                    inv.clearContents();
                    inv = target.getCraftingTable();
                    inv.clearContents();
                    inv = target.getEquipment();
                    inv.clearContents();
                    inv.updateInventory();
                    if (!target.getName().equals(getPlayer().getName())) {
                        msg(Colors.Rose + "Cleared " + target.getName() + "'s inventory.");
                    }
                } else {
                    msg(Colors.Rose + "Target not found");
                }
            } else if (split[0].equalsIgnoreCase("/version")) {
                msg(Colors.Gold + "Hey0 Server Mod Build " + etc.getInstance().getVersion());
            } else {
                a.info(getPlayer().getName() + " tried command " + paramString);
                if (etc.getInstance().showUnknownCommand()) {
                    msg(Colors.Rose + "Unknown command");
                }
            }
        } catch (Throwable ex) { //Might as well try and catch big exceptions before the server crashes from a stack overflow or something
            a.log(Level.SEVERE, "Exception in command handler (Report this to hey0 unless you did something dumb like enter letters as numbers):", ex);
            if (getPlayer().isAdmin()) {
                msg(Colors.Rose + "Exception occured. Check the server for more info.");
            }
        }
    }

    //Arm animation
    public void a(o paramo) {
        if (paramo.b == 1) {
            etc.getLoader().callHook(PluginLoader.Hook.ARM_SWING, new Object[]{e});
            this.e.z();
        }
    }

    //Quitting (packet)
    public void a(io paramio) {
        this.b.a("Quitting");
    }

    public int b() {
        return this.b.d();
    }

    public void b(String paramString) {
        b(new ba("§7" + paramString));
    }

    //get name? lol
    public String c() {
        return getPlayer().getName();
    }

    //Update our inventory
    public void a(r paramr) {
        if (!getPlayer().canBuild()) {
            return;
        }

        if (paramr.a == -1) {
            gp[] temp = this.e.aj.a;
            this.e.aj.a = paramr.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_CHANGE, new Object[]{e})) {
                this.e.aj.a = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
        if (paramr.a == -2) {
            this.e.aj.c = paramr.b;
        }
        if (paramr.a == -3) {
            this.e.aj.b = paramr.b;
        }
    }

    //Send inventory
    public void d() {
        this.b.a(new r(-1, this.e.aj.a));
        this.b.a(new r(-2, this.e.aj.c));
        this.b.a(new r(-3, this.e.aj.b));
    }

    //Change object data (Chests, signs, furnaces, etc.)
    public void a(ib paramib) {
        if (!getPlayer().canBuild()) {
            return;
        }

        as localas = this.d.e.k(paramib.a, paramib.b, paramib.c);
        if (localas != null) {
            if (localas instanceof hb) { //Chest
                hb chest = (hb) localas;
                gp[] temp = chest.getContents();
                localas.a(paramib.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Chest(chest)})) {
                    chest.setContents(temp);
                }
            } else if (localas instanceof df) { //Furnace
                df furnace = (df) localas;
                gp[] temp = furnace.getContents();
                localas.a(paramib.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Furnace(furnace)})) {
                    furnace.setContents(temp);
                }
            } else if (localas instanceof ig) { //Sign
                ig sign = (ig) localas;
                String[] temp = sign.e;
                localas.a(paramib.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Sign(sign)})) {
                    sign.e = temp;
                }
            }
            localas.c();
        }
    }
}
