import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class jh extends fb implements ex {
    public static Logger a = Logger.getLogger("Minecraft");
    public bi b;
    public boolean c = false;
    private MinecraftServer d;
    private es e;
    private int f = 0;
    private double g;
    private double h;
    private double i;
    private boolean j = true;
    private hm k = null;

    public jh(MinecraftServer paramMinecraftServer, bi parambi, es parames) {
        d = paramMinecraftServer;
        b = parambi;
        parambi.a(this);
        e = parames;
        parames.a = this;
    }

    /**
     * Returns the item in player's hand
     * 
     * @return
     */
    public int getItemInHand() {
        if (k != null) { // Check to see if we are even holding anything
            return k.c;
        }
        return -1;
    }

    public Player getPlayer() {
        return e.getPlayer();
    }

    public void a() {
        b.a();
        if (f++ % 20 == 0) {
            b.a(new ki());
        }
    }

    public void c(String paramString) {
        // hMod: Should we add this too here?
        // etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, new Object[]{e});
        b.a(new jv(paramString));
        b.c();
        d.f.a(new bh("§e" + e.at + " left the game."));
        d.f.c(e);
        c = true;
    }

    @Override
    public void a(hc paramhc) {
        if (!j) {
            double d1 = paramhc.b - h;
            if ((paramhc.a == g) && (d1 * d1 < 0.01D) && (paramhc.c == i)) {
                j = true;
            }
        }
        // hMod: Notice player movement
        if ((int) Math.floor(g) != (int) Math.floor(getPlayer().getX()) || (int) Math.floor(h) != (int) Math.floor(getPlayer().getY()) || (int) Math.floor(i) != (int) Math.floor(getPlayer().getZ())) {
            Location from = new Location();
            from.x = (int) Math.floor(g);
            from.y = (int) Math.floor(h);
            from.z = (int) Math.floor(i);
            from.rotX = getPlayer().getRotation();
            from.rotY = getPlayer().getPitch();

            Location to = new Location();
            to.x = (int) Math.floor(e.p);
            to.y = (int) Math.floor(e.q);
            to.z = (int) Math.floor(e.r);
            to.rotX = getPlayer().getRotation();
            to.rotY = getPlayer().getPitch();

            etc.getLoader().callHook(PluginLoader.Hook.PLAYER_MOVE, new Object[]{e, from, to});
        }
        if (j) {
            if (e.k != null) {
                float f1 = e.v;
                float f2 = e.w;
                e.k.A();
                double d3 = e.p;
                double d4 = e.q;
                double d5 = e.r;
                double d6 = 0.0D;
                double d7 = 0.0D;
                if (paramhc.i) {
                    f1 = paramhc.e;
                    f2 = paramhc.f;
                }
                if ((paramhc.h) && (paramhc.b == -999.0D) && (paramhc.d == -999.0D)) {
                    d6 = paramhc.a;
                    d7 = paramhc.c;
                }

                e.A = paramhc.g;
                e.k();
                e.c(d6, 0.0D, d7);
                e.b(d3, d4, d5, f1, f2);
                e.s = d6;
                e.u = d7;
                d.e.b(e.k, true);
                e.k.A();
                d.f.b(e);
                g = e.p;
                h = e.q;
                i = e.r;
                d.e.f(e);

                return;
            }

            double d2 = e.q;
            g = e.p;
            h = e.q;
            i = e.r;

            double d3 = e.p;
            double d4 = e.q;
            double d5 = e.r;

            float f3 = e.v;
            float f4 = e.w;

            if ((paramhc.h) && (paramhc.b == -999.0D) && (paramhc.d == -999.0D)) {
                paramhc.h = false;
            }

            if (paramhc.h) {
                d3 = paramhc.a;
                d4 = paramhc.b;
                d5 = paramhc.c;
                double d7 = paramhc.d - paramhc.b;
                if ((d7 > 1.65D) || (d7 < 0.1D)) {
                    c("Illegal stance");
                    a.warning(e.at + " had an illegal stance: " + d7);
                }
                e.ak = paramhc.d;
            }
            if (paramhc.i) {
                f3 = paramhc.e;
                f4 = paramhc.f;
            }

            e.k();
            e.R = 0.0F;
            e.b(g, h, i, f3, f4);

            double d7 = d3 - e.p;
            double d8 = d4 - e.q;
            double d9 = d5 - e.r;

            float f5 = 0.0625F;
            int m = d.e.a(e, e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;

            e.c(d7, d8, d9);
            d7 = d3 - e.p;
            d8 = d4 - e.q;
            if ((d8 > -0.5D) || (d8 < 0.5D)) {
                d8 = 0.0D;
            }
            d9 = d5 - e.r;
            double d10 = d7 * d7 + d8 * d8 + d9 * d9;
            int n = 0;
            if (d10 > 0.0625D) {
                n = 1;
                a.warning(e.at + " moved wrongly!");
                System.out.println("Got position " + d3 + ", " + d4 + ", " + d5);
                System.out.println("Expected " + e.p + ", " + e.q + ", " + e.r);
            }
            e.b(d3, d4, d5, f3, f4);

            int i1 = d.e.a(e, e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;
            if ((m != 0) && ((n != 0) || (i1 == 0))) {
                a(g, h, i, f3, f4);
                return;
            }

            e.A = paramhc.g;
            d.f.b(e);
            e.b(e.q - d2, paramhc.g);
        }
    }

    public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
        // hMod: Teleportation hook
        Location from = new Location();
        from.x = paramDouble1;
        from.y = paramDouble2;
        from.z = paramDouble3;
        from.rotX = paramFloat1;
        from.rotY = paramFloat2;
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.TELEPORT, new Object[]{e, e.getPlayer().getLocation(), from})) {
            return;
        }

        j = false;
        g = paramDouble1;
        h = paramDouble2;
        i = paramDouble3;
        e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        e.a.b(new eg(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }

    // hMod: Store x/y/z
    int x, y, z, type;

    // Destroy function
    @Override
    public void a(id paramid) {
        e.am.a[e.am.d] = k;
        boolean bool = d.e.B = d.f.g(e.at) || getPlayer().isAdmin();
        int m = 0;
        if (paramid.e == 0) {
            m = 1;
        }
        if (paramid.e == 1) {
            m = 1;
        }

        int n = paramid.a;
        int i1 = paramid.b;
        int i2 = paramid.c;
        if (m != 0) {
            double d1 = e.p - (n + 0.5D);
            double d2 = e.q - (i1 + 0.5D);
            double d4 = e.r - (i2 + 0.5D);
            double d6 = d1 * d1 + d2 * d2 + d4 * d4;
            if (d6 > 36.0D) {
                return;
            }

            double d8 = e.q;
            e.q = e.ak;

            e.q = d8;
        }

        int i3 = paramid.d;
        int i4 = (int) hg.e(n - d.e.m);
        int i5 = (int) hg.e(i2 - d.e.o);
        if (i4 > i5) {
            i5 = i4;
        }
        if (paramid.e == 0) {
            // hMod: Start digging
            // No buildrights
            if (!getPlayer().canBuild()) {
                return;
            }
            if ((i5 > etc.getInstance().getSpawnProtectionSize()) || (bool)) {
                Block block = etc.getServer().getBlockAt(n, i1, i2);
                block.setStatus(0); // Started digging
                x = block.getX();
                y = block.getY();
                z = block.getZ();
                type = block.getType();
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block})) {
                    this.e.c.a(n, i1, i2);
                }
            }
        } else if (paramid.e == 2) {
            // hMod: Stop digging
            Block block = etc.getServer().getBlockAt(n, i1, i2);
            block.setStatus(2); // Stopped digging
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});
            e.c.a();
        } else if (paramid.e == 1) {
            // hMod: Continue digging
            if (!getPlayer().canBuild()) {
                return;
            }

            if (i5 > etc.getInstance().getSpawnProtectionSize() || (bool)) {
                Block block = etc.getServer().getBlockAt(n, i1, i2);
                block.setStatus(1); // Digging
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block})) {
                    this.e.c.a(n, i1, i2, i3);
                }
            }
        } else if (paramid.e == 3) {
            // hMod: Break block
            Block block = new Block(type, x, y, z);
            block.setStatus(3);
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});

            double d3 = e.p - (n + 0.5D);
            double d5 = e.q - (i1 + 0.5D);
            double d7 = e.r - (i2 + 0.5D);
            double d9 = d3 * d3 + d5 * d5 + d7 * d7;
            if (d9 < 256.0D) {
                e.a.b(new fm(n, i1, i2, d.e));
            }
        }
        d.e.B = false;
    }

    @Override
    public void a(ga paramga) {
        boolean bool = d.e.B = (d.f.g(getPlayer().getName()) || getPlayer().isAdmin());
        if (paramga.e == 255) {
            hm localhm1 = paramga.a >= 0 ? new hm(paramga.a) : null;
            e.c.a(e, d.e, localhm1);
        } else {
            int m = paramga.b;
            int n = paramga.c;
            int i1 = paramga.d;
            int i2 = paramga.e;
            int i3 = (int) hg.e(m - d.e.m);
            int i4 = (int) hg.e(i1 - d.e.o);
            if (i3 > i4) {
                i4 = i3;
            }
            // hMod: Store block data to call BLOCK_CREATED
            Block blockClicked = new Block(etc.getServer().getBlockIdAt(m, n, i1), m, n, i1);
            blockClicked.setFaceClicked(Block.Face.fromId(paramga.e));

            // hMod: call BLOCK_RIGHTCLICKED
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, new Object[]{(e).getPlayer(), blockClicked, new Item(new hm(paramga.a))});

            // hMod: call original BLOCK_CREATED
            Block blockPlaced = new Block(paramga.a, m, n, i1);
            if (paramga.e == 0) {
                blockPlaced.setY(blockPlaced.getY() - 1);
            } else if (paramga.e == 1) {
                blockPlaced.setY(blockPlaced.getY() + 1);
            } else if (paramga.e == 2) {
                blockPlaced.setZ(blockPlaced.getZ() - 1);
            } else if (paramga.e == 3) {
                blockPlaced.setZ(blockPlaced.getZ() + 1);
            } else if (paramga.e == 4) {
                blockPlaced.setX(blockPlaced.getX() - 1);
            } else if (paramga.e == 5) {
                blockPlaced.setX(blockPlaced.getX() + 1);
            }
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_CREATED, new Object[]{e, blockPlaced, blockClicked, paramga.a});

            // hMod: If we were building inside spawn, bail! (unless ops/admin)
            if ((i4 > etc.getInstance().getSpawnProtectionSize() || bool) && getPlayer().canBuild()) {
                hm localhm = paramga.a >= 0 ? new hm(paramga.a) : null;
                this.e.c.a(this.e, this.d.e, localhm, m, n, i1, i2);
            } else {
                // hMod: No point sending the client to update the blocks, you weren't allowed to place!
                this.d.e.B = false;
                return;
            }

            // hMod: these are the 'block changed' packets for the client.
            e.a.b(new fm(m, n, i1, d.e));

            if (i2 == 0) {
                n--;
            }
            if (i2 == 1) {
                n++;
            }
            if (i2 == 2) {
                i1--;
            }
            if (i2 == 3) {
                i1++;
            }
            if (i2 == 4) {
                m--;
            }
            if (i2 == 5) {
                m++;
            }

            e.a.b(new fm(m, n, i1, d.e));
        }
        d.e.B = false;
    }

    @Override
    public void a(String paramString) {
        // hMod: disconnect!
        etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, new Object[]{e});
        a.info(e.at + " lost connection: " + paramString);
        d.f.a(new bh("§e" + e.at + " left the game."));
        d.f.c(e);
        c = true;
    }

    @Override
    public void a(is paramis) {
        a.warning(getClass() + " wasn't prepared to deal with a " + paramis.getClass());
        c("Protocol error, unexpected packet");
    }

    public void b(is paramis) {
        b.a(paramis);
    }

    @Override
    public void a(gs paramgs) {
        int m = paramgs.b;
        e.am.d = (e.am.a.length - 1);
        if (m == 0) {
            k = null;
        } else {
            k = new hm(m);
        }
        e.am.a[e.am.d] = k;
        d.k.a(e, new gs(e.g, m));
    }

    @Override
    public void a(l paraml) {
        double d1 = paraml.b / 32.0D;
        double d2 = paraml.c / 32.0D;
        double d3 = paraml.d / 32.0D;
        // hMod: allow item drops
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_DROP, new Object[]{e, new Item(paraml.h, paraml.i)})) {

            gk localgk = new gk(d.e, d1, d2, d3, new hm(paraml.h, paraml.i));
            localgk.s = (paraml.e / 128.0D);
            localgk.t = (paraml.f / 128.0D);
            localgk.u = (paraml.g / 128.0D);
            localgk.c = 10;
            d.e.a(localgk);
        }
    }

    @Override
    public void a(bh parambh) {
        // hMod: redirect chathandling to player class
        String str = parambh.a;
        getPlayer().chat(str);
    }

    /**
     * Sends a message to the player
     * 
     * @param msg
     */
    public void msg(String msg) {
        b(new bh(msg));
    }

    private void d(String paramString) {
        // hMod: chat already handles the call to command();.
        // getPlayer().command(paramString);
    }

    @Override
    public void a(q paramq) {
        if (paramq.b == 1) {
            // hMod: Swing teh arm!
            etc.getLoader().callHook(PluginLoader.Hook.ARM_SWING, new Object[]{e});
            e.F();
        } else if (paramq.b == 104) {
            e.al = true;
        } else if (paramq.b == 105) {
            e.al = false;
        }
    }

    @Override
    public void a(jv paramjv) {
        b.a("Quitting");
    }

    public int b() {
        return b.d();
    }

    @Override
    public void b(String paramString) {
        b(new bh("§7" + paramString));
    }

    @Override
    public String c() {
        return e.at;
    }

    @Override
    public void a(u paramu) {
        // hMod: Inventory handling; no inventory if you don't have buildrights
        if (!getPlayer().canBuild()) {
            getPlayer().getInventory().clearContents();
            getPlayer().getCraftingTable().clearContents();
            getPlayer().getEquipment().clearContents();
            getPlayer().getInventory().updateInventory();
            return;
        }
        if (paramu.a == -1) {
            // hMod: Inventory
            hm[] temp = this.e.am.a;
            this.e.am.a = paramu.b;
            this.e.am.a[this.e.am.d] = k; // hMod: Preserve item in hand
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_CHANGE, new Object[]{e})) {
                this.e.am.a = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
        if (paramu.a == -2) {
            // hMod: Equipment
            hm[] temp = this.e.am.c;
            this.e.am.c = paramu.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EQUIPMENT_CHANGE, new Object[]{e})) {
                this.e.am.c = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
        if (paramu.a == -3) {
            // hMod: Craft inventory
            hm[] temp = this.e.am.b;
            this.e.am.b = paramu.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.CRAFTINVENTORY_CHANGE, new Object[]{e})) {
                this.e.am.b = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
    }

    public void d() {
        b.a(new u(-1, e.am.a));
        b.a(new u(-2, e.am.c));
        b.a(new u(-3, e.am.b));
    }

    @Override
    public void a(jg paramjg) {
        if (paramjg.e.d("x") != paramjg.a) {
            return;
        }
        if (paramjg.e.d("y") != paramjg.b) {
            return;
        }
        if (paramjg.e.d("z") != paramjg.c) {
            return;
        }

        ay localay = d.e.k(paramjg.a, paramjg.b, paramjg.c);
        if (localay != null) {
            if (localay instanceof ib) {
                // hMod: Chest
                ib chest = (ib) localay;
                hm[] temp = chest.getContents();
                localay.a(paramjg.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Chest(chest)}) || !e.getPlayer().canBuild()) {
                    chest.setContents(temp);
                }
            } else if (localay instanceof du) {
                // hMod: Furnace
                du furnace = (du) localay;
                hm[] temp = furnace.getContents();
                localay.a(paramjg.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Furnace(furnace)}) || !e.getPlayer().canBuild()) {
                    furnace.setContents(temp);
                }
            } else if (localay instanceof jm) {
                // hMod: Sign
                jm sign = (jm) localay;
                String[] temp = sign.e;
                localay.a(paramjg.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Sign(sign)}) || !e.getPlayer().canBuild()) {
                    sign.e = temp;
                }
            }
            // try {
            // localay.a(paramjg.e);
            // } catch (Exception localException) {
            // }
            localay.c();
        }
    }

    @Override
    public void a(a parama) {
        dy localdy = d.e.a(parama.b);
        e.am.a[e.am.d] = k;

        if ((localdy != null) && (e.i(localdy))) {
            if (parama.c == 0) {
                e.g(localdy);
            } else if (parama.c == 1) {
                e.h(localdy);
            }
        }
    }

    @Override
    public void a(az paramaz) {
        if (e.aR > 0) {
            return;
        }

        e = d.f.d(e);
    }
}
