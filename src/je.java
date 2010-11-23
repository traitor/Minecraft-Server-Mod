import java.util.logging.Logger;

import net.minecraft.server.MinecraftServer;

public class je extends ey implements eu {

    public static Logger a = Logger.getLogger("Minecraft");
    public bh b;
    public boolean c = false;
    private MinecraftServer d;
    private ep e;
    private int f = 0;
    private double g;
    private double h;
    private double i;
    private boolean j = true;
    private hj k = null;

    public je(MinecraftServer paramMinecraftServer, bh parambh, ep paramep) {
        this.d = paramMinecraftServer;
        this.b = parambh;
        parambh.a(this);
        this.e = paramep;
        paramep.a = this;
    }

    /**
     * Returns the item in player's hand
     * 
     * @return
     */
    public int getItemInHand() {
        if (k != null) // Check to see if we are even holding anything
        {
            return k.c;
        }
        return -1;
    }

    public Player getPlayer() {
        return e.getPlayer();
    }

    public void a() {
        this.b.a();
        if (this.f++ % 20 == 0) {
            this.b.a(new ke());
        }
    }

    public void c(String paramString) {
        this.b.a(new jr(paramString));
        this.b.c();
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(gz paramgz) {
        if (!this.j) {
            double d1 = paramgz.b - this.h;
            if ((paramgz.a == this.g) && (d1 * d1 < 0.01D) && (paramgz.c == this.i)) {
                this.j = true;
            }
        }
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
        if (this.j) {
            if (this.e.k != null) {
                float f1 = this.e.v;
                float f2 = this.e.w;
                this.e.k.z();
                double d3 = this.e.p;
                double d4 = this.e.q;
                double d5 = this.e.r;
                double d6 = 0.0D;
                double d7 = 0.0D;
                if (paramgz.i) {
                    f1 = paramgz.e;
                    f2 = paramgz.f;
                }
                if ((paramgz.h) && (paramgz.b == -999.0D) && (paramgz.d == -999.0D)) {
                    d6 = paramgz.a;
                    d7 = paramgz.c;
                }

                this.e.k();
                this.e.b(d3, d4, d5, f1, f2);
                this.e.s = d6;
                this.e.u = d7;
                this.e.k.b_();

                return;
            }

            this.g = this.e.p;
            this.h = this.e.q;
            this.i = this.e.r;

            double d2 = this.e.p;
            double d3 = this.e.q;
            double d4 = this.e.r;

            float f3 = this.e.v;
            float f4 = this.e.w;

            if ((paramgz.h) && (paramgz.b == -999.0D) && (paramgz.d == -999.0D)) {
                paramgz.h = false;
            }

            if (paramgz.h) {
                d2 = paramgz.a;
                d3 = paramgz.b;
                d4 = paramgz.c;
                double d6 = paramgz.d - paramgz.b;
                if ((d6 > 1.65D) || (d6 < 0.1D)) {
                    c("Illegal stance");
                    a.warning(this.e.ar + " had an illegal stance: " + d6);
                }
                this.e.aj = paramgz.d;
            }
            if (paramgz.i) {
                f3 = paramgz.e;
                f4 = paramgz.f;
            }

            this.e.k();
            this.e.Q = 0.0F;
            this.e.b(this.g, this.h, this.i, f3, f4);

            double d6 = d2 - this.e.p;
            double d7 = d3 - this.e.q;
            double d8 = d4 - this.e.r;

            float f5 = 0.0625F;
            int m = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;

            this.e.c(d6, d7, d8);
            d6 = d2 - this.e.p;
            d7 = d3 - this.e.q;
            if ((d7 > -0.5D) || (d7 < 0.5D)) {
                d7 = 0.0D;
            }
            d8 = d4 - this.e.r;
            double d9 = d6 * d6 + d7 * d7 + d8 * d8;
            int n = 0;
            if (d9 > 0.0625D) {
                n = 1;
                a.warning(this.e.ar + " moved wrongly!");
            }
            this.e.b(d2, d3, d4, f3, f4);

            int i1 = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;
            if ((m != 0) && ((n != 0) || (i1 == 0))) {
                a(this.g, this.h, this.i, f3, f4);
                return;
            }

            this.e.A = paramgz.g;
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
        this.e.a.b(new ed(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }
    int x, y, z, type;

    // Destroy function
    public void a(hz paramhz) {
        this.e.ak.a[this.e.ak.d] = this.k;
        boolean bool = this.d.e.B = (this.d.f.g(this.e.ar) || getPlayer().isAdmin());
        int m = 0;
        if (paramhz.e == 0) {
            m = 1;
        }
        if (paramhz.e == 1) {
            m = 1;
        }

        if (m != 0) {
            double d1 = this.e.q;
            this.e.q = this.e.aj;
            gm localgm = this.e.a(4.0D, 1.0F);
            this.e.q = d1;
            if (localgm == null) {
                return;
            }
            if ((localgm.b != paramhz.a) || (localgm.c != paramhz.b) || (localgm.d != paramhz.c) || (localgm.e != paramhz.d)) {
                return;
            }
        }
        int n = paramhz.a;
        int i1 = paramhz.b;
        int i2 = paramhz.c;
        int i3 = paramhz.d;
        int i4 = (int) hd.e(n - this.d.e.m);
        int i5 = (int) hd.e(i2 - this.d.e.o);
        if (i4 > i5) {
            i5 = i4;
        }
        if (paramhz.e == 0) {
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

        } else if (paramhz.e == 2) {
            Block block = etc.getServer().getBlockAt(n, i1, i2);
            block.setStatus(2); // Stopped digging
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});

            this.e.c.a();
        } else if (paramhz.e == 1) {
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
        } else if (paramhz.e == 3) {
            Block block = new Block(type, x, y, z);
            block.setStatus(3);
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});

            double d2 = this.e.p - (n + 0.5D);
            double d3 = this.e.q - (i1 + 0.5D);
            double d4 = this.e.r - (i2 + 0.5D);
            double d5 = d2 * d2 + d3 * d3 + d4 * d4;
            if (d5 < 256.0D) {
                this.e.a.b(new fj(n, i1, i2, this.d.e));
            }
        }
        this.d.e.B = false;
    }

    public void a(fx paramfx) {
        boolean bool = this.d.e.B = (this.d.f.g(getPlayer().getName()) || getPlayer().isAdmin());
        if (paramfx.e == 255) {
            hj localhj1 = paramfx.a >= 0 ? new hj(paramfx.a) : null;
            this.e.c.a(this.e, this.d.e, localhj1);
        } else {
            int m = paramfx.b;
            int n = paramfx.c;
            int i1 = paramfx.d;
            int i2 = paramfx.e;
            int i3 = (int) hd.e(m - this.d.e.m);
            int i4 = (int) hd.e(i1 - this.d.e.o);
            if (i3 > i4) {
                i4 = i3;
            }
            if ((i4 > etc.getInstance().getSpawnProtectionSize()) || (bool)) {
                hj localhj = paramfx.a >= 0 ? new hj(paramfx.a) : null;

                Block blockPlaced = new Block(localhj != null ? localhj.c : paramfx.a, m, n, i1);
                if (paramfx.e == 0) {
                    blockPlaced.setY(blockPlaced.getY() - 1);
                } else if (paramfx.e == 1) {
                    blockPlaced.setY(blockPlaced.getY() + 1);
                } else if (paramfx.e == 2) {
                    blockPlaced.setZ(blockPlaced.getZ() - 1);
                } else if (paramfx.e == 3) {
                    blockPlaced.setZ(blockPlaced.getZ() + 1);
                } else if (paramfx.e == 4) {
                    blockPlaced.setX(blockPlaced.getX() - 1);
                } else if (paramfx.e == 5) {
                    blockPlaced.setX(blockPlaced.getX() + 1);
                }
                Block blockClicked = new Block(etc.getServer().getBlockIdAt(m, n, i1), m, n, i1);
                blockClicked.setFaceClicked(Block.Face.fromId(paramfx.e));

                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_CREATED, new Object[]{e, blockPlaced, blockClicked, paramfx.a}) && getPlayer().canBuild()) {
                    if (localhj != null) {
                        if (!etc.getInstance().isOnItemBlacklist(localhj.c) || bool) {
                            this.e.c.a(this.e, this.d.e, localhj, m, n, i1, i2);
                        }
                    } // is this right ?
                    else {
                        this.e.c.a(this.e, this.d.e, localhj, m, n, i1, i2);
                    }
                }
            }

            this.e.a.b(new fj(m, n, i1, this.d.e));

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

            this.e.a.b(new fj(m, n, i1, this.d.e));
        }
        this.d.e.B = false;
    }

    public void a(String paramString) {
        etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, new Object[]{e});
        a.info(this.e.ar + " lost connection: " + paramString);
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(io paramio) {
        a.warning(getClass() + " wasn't prepared to deal with a " + paramio.getClass());
        c("Protocol error, unexpected packet");
    }

    public void b(io paramio) {
        this.b.a(paramio);
    }

    public void a(gp paramgp) {
        int m = paramgp.b;
        this.e.ak.d = (this.e.ak.a.length - 1);
        if (m == 0) {
            this.k = null;
        } else {
            this.k = new hj(m);
        }
        this.e.ak.a[this.e.ak.d] = this.k;
        this.d.k.a(this.e, new gp(this.e.g, m));
    }

    public void a(l paraml) {
        double d1 = paraml.b / 32.0D;
        double d2 = paraml.c / 32.0D;
        double d3 = paraml.d / 32.0D;
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_DROP, new Object[]{e, new Item(paraml.h, paraml.i)})) {
            gh localgh = new gh(this.d.e, d1, d2, d3, new hj(paraml.h, paraml.i));
            localgh.s = (paraml.e / 128.0D);
            localgh.t = (paraml.f / 128.0D);
            localgh.u = (paraml.g / 128.0D);
            localgh.c = 10;
            this.d.e.a(localgh);
        }
    }

    public void a(bg parambg) {
        String str = parambg.a;
        getPlayer().chat(str);
    }

    /**
     * Sends a message to the player
     * 
     * @param msg
     */
    public void msg(String msg) {
        b(new bg(msg));
    }

    private void d(String paramString) {
       //chat already handles the call to command();.
       //getPlayer().command(paramString);
    }

    public void a(q paramq) {
        if (paramq.b == 1) {
            etc.getLoader().callHook(PluginLoader.Hook.ARM_SWING, new Object[]{e});
            this.e.E();
        }
    }

    public void a(jr paramjr) {
        this.b.a("Quitting");
    }

    public int b() {
        return this.b.d();
    }

    public void b(String paramString) {
        b(new bg("§7" + paramString));
    }

    public String c() {
        return this.e.ar;
    }

    public void a(u paramu) {
        if (!getPlayer().canBuild()) {
            getPlayer().getInventory().clearContents();
            getPlayer().getCraftingTable().clearContents();
            getPlayer().getEquipment().clearContents();
            getPlayer().getInventory().updateInventory();
            return;
        }

        if (paramu.a == -1) {
            hj[] temp = this.e.ak.a;
            this.e.ak.a = paramu.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_CHANGE, new Object[]{e})) {
                this.e.ak.a = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
        if (paramu.a == -2) {
            hj[] temp = this.e.ak.c;
            this.e.ak.c = paramu.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EQUIPMENT_CHANGE, new Object[]{e})) {
                this.e.ak.c = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
        if (paramu.a == -3) {
            hj[] temp = this.e.ak.b;
            this.e.ak.b = paramu.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.CRAFTINVENTORY_CHANGE, new Object[]{e})) {
                this.e.ak.b = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
    }

    public void d() {
        this.b.a(new u(-1, this.e.ak.a));
        this.b.a(new u(-2, this.e.ak.c));
        this.b.a(new u(-3, this.e.ak.b));
    }

    public void a(jc paramjc) {
        if (paramjc.e.d("x") != paramjc.a) {
            return;
        }
        if (paramjc.e.d("y") != paramjc.b) {
            return;
        }
        if (paramjc.e.d("z") != paramjc.c) {
            return;
        }

        ay localay = this.d.e.k(paramjc.a, paramjc.b, paramjc.c);
        if (localay != null) {
            if (localay instanceof hx) { // Chest
                hx chest = (hx) localay;
                hj[] temp = chest.getContents();
                localay.a(paramjc.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Chest(chest)}) || !e.getPlayer().canBuild()) {
                    chest.setContents(temp);
                }
            } else if (localay instanceof ds) { // Furnace
                ds furnace = (ds) localay;
                hj[] temp = furnace.getContents();
                localay.a(paramjc.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Furnace(furnace)}) || !e.getPlayer().canBuild()) {
                    furnace.setContents(temp);
                }
            } else if (localay instanceof ji) { // Sign
                ji sign = (ji) localay;
                String[] temp = sign.e;
                localay.a(paramjc.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Sign(sign)}) || !e.getPlayer().canBuild()) {
                    sign.e = temp;
                }
            }
            /*
             * try { localay.a(paramjc.e); } catch (Exception localException) {
             * }
             */
            localay.c();
        }
    }

    public void a(a parama) {
        dw localdw = this.d.e.a(parama.b);
        if ((localdw != null) && (this.e.g(localdw))) {
            localdw.a(this.e);
        }
    }
}
