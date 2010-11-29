
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class jg extends fa implements ew {

    public static Logger a = Logger.getLogger("Minecraft");
    public bi b;
    public boolean c = false;
    private MinecraftServer d;
    private er e;
    private int f = 0;
    private double g;
    private double h;
    private double i;
    private boolean j = true;
    private hl k = null;

    public jg(MinecraftServer paramMinecraftServer, bi parambi, er paramer) {
        this.d = paramMinecraftServer;
        this.b = parambi;
        parambi.a(this);
        this.e = paramer;
        paramer.a = this;
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
        this.b.a();
        if (this.f++ % 20 == 0)
            this.b.a(new kh());
    }

    public void c(String paramString) {
        // hMod: Should we add this too here?
        // etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, new Object[]{e});
        this.b.a(new ju(paramString));
        this.b.c();
        this.d.f.a(new bh("§e" + this.e.as + " left the game."));
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(hb paramhb) {
        if (!this.j) {
            double d1 = paramhb.b - this.h;
            if ((paramhb.a == this.g) && (d1 * d1 < 0.01D) && (paramhb.c == this.i)) {
                this.j = true;
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
        if (this.j) {
            if (this.e.k != null) {
                float f1 = this.e.v;
                float f2 = this.e.w;
                this.e.k.A();
                double d3 = this.e.p;
                double d4 = this.e.q;
                double d5 = this.e.r;
                double d6 = 0.0D;
                double d7 = 0.0D;
                if (paramhb.i) {
                    f1 = paramhb.e;
                    f2 = paramhb.f;
                }
                if ((paramhb.h) && (paramhb.b == -999.0D) && (paramhb.d == -999.0D)) {
                    d6 = paramhb.a;
                    d7 = paramhb.c;
                }

                this.e.A = paramhb.g;
                this.e.k();
                this.e.b(d3, d4, d5, f1, f2);
                this.e.s = d6;
                this.e.u = d7;
                this.e.k.b_();

                this.d.f.b(this.e);
                return;
            }

            double d2 = this.e.q;
            this.g = this.e.p;
            this.h = this.e.q;
            this.i = this.e.r;

            double d3 = this.e.p;
            double d4 = this.e.q;
            double d5 = this.e.r;

            float f3 = this.e.v;
            float f4 = this.e.w;

            if ((paramhb.h) && (paramhb.b == -999.0D) && (paramhb.d == -999.0D)) {
                paramhb.h = false;
            }

            if (paramhb.h) {
                d3 = paramhb.a;
                d4 = paramhb.b;
                d5 = paramhb.c;
                double d7 = paramhb.d - paramhb.b;
                if ((d7 > 1.65D) || (d7 < 0.1D)) {
                    c("Illegal stance");
                    a.warning(this.e.as + " had an illegal stance: " + d7);
                }
                this.e.ak = paramhb.d;
            }
            if (paramhb.i) {
                f3 = paramhb.e;
                f4 = paramhb.f;
            }

            this.e.k();
            this.e.R = 0.0F;
            this.e.b(this.g, this.h, this.i, f3, f4);

            double d7 = d3 - this.e.p;
            double d8 = d4 - this.e.q;
            double d9 = d5 - this.e.r;

            float f5 = 0.0625F;
            int m = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;

            this.e.c(d7, d8, d9);
            d7 = d3 - this.e.p;
            d8 = d4 - this.e.q;
            if ((d8 > -0.5D) || (d8 < 0.5D)) {
                d8 = 0.0D;
            }
            d9 = d5 - this.e.r;
            double d10 = d7 * d7 + d8 * d8 + d9 * d9;
            int n = 0;
            if (d10 > 0.0625D) {
                n = 1;
                a.warning(this.e.as + " moved wrongly!");
                System.out.println("Got position " + d3 + ", " + d4 + ", " + d5);
                System.out.println("Expected " + this.e.p + ", " + this.e.q + ", " + this.e.r);
            }
            this.e.b(d3, d4, d5, f3, f4);

            int i1 = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;
            if ((m != 0) && ((n != 0) || (i1 == 0))) {
                a(this.g, this.h, this.i, f3, f4);
                return;
            }

            this.e.A = paramhb.g;
            this.d.f.b(this.e);
            this.e.b(this.e.q - d2, paramhb.g);
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

        this.j = false;
        this.g = paramDouble1;
        this.h = paramDouble2;
        this.i = paramDouble3;
        this.e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        this.e.a.b(new ef(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2,
                false));
    }
    // hMod: Store x/y/z
    int x, y, z, type;

    // Destroy function
    public void a(ic paramic) {
        this.e.al.a[this.e.al.d] = this.k;
        // hMod: Allow admins
        boolean bool = this.d.e.B = (this.d.f.g(this.e.as) || getPlayer().isAdmin());
        int m = 0;
        if (paramic.e == 0)
            m = 1;
        if (paramic.e == 1)
            m = 1;

        int n = paramic.a;
        int i1 = paramic.b;
        int i2 = paramic.c;
        if (m != 0) {
            double d1 = this.e.p - (n + 0.5D);
            double d2 = this.e.q - (i1 + 0.5D);
            double d4 = this.e.r - (i2 + 0.5D);
            double d6 = d1 * d1 + d2 * d2 + d4 * d4;
            if (d6 > 36.0D)
                return;

            double d8 = this.e.q;
            this.e.q = this.e.ak;

            this.e.q = d8;
        }

        int i3 = paramic.d;
        int i4 = (int) hf.e(n - this.d.e.m);
        int i5 = (int) hf.e(i2 - this.d.e.o);
        if (i4 > i5)
            i5 = i4;
        if (paramic.e == 0) {
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
        } else if (paramic.e == 2) {
            // hMod: Stop digging
            Block block = etc.getServer().getBlockAt(n, i1, i2);
            block.setStatus(2); // Stopped digging
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});
            this.e.c.a();
        } else if (paramic.e == 1) {
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
        } else if (paramic.e == 3) {
            // hMod: Break block
            Block block = new Block(type, x, y, z);
            block.setStatus(3);
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});

            double d3 = this.e.p - (n + 0.5D);
            double d5 = this.e.q - (i1 + 0.5D);
            double d7 = this.e.r - (i2 + 0.5D);
            double d9 = d3 * d3 + d5 * d5 + d7 * d7;
            if (d9 < 256.0D) {
                this.e.a.b(new fl(n, i1, i2, this.d.e));
            }
        }
        this.d.e.B = false;
    }

    public void a(fz paramfz) {
        // hMod: Is admin?
        boolean bool = this.d.e.B = (this.d.f.g(getPlayer().getName()) || getPlayer().isAdmin());
        if (paramfz.e == 255) {
            hl localhl1 = paramfz.a >= 0 ? new hl(paramfz.a) : null;
            this.e.c.a(this.e, this.d.e, localhl1);
        } else {
            int m = paramfz.b;
            int n = paramfz.c;
            int i1 = paramfz.d;
            int i2 = paramfz.e;
            int i3 = (int) hf.e(m - this.d.e.m);
            int i4 = (int) hf.e(i1 - this.d.e.o);
            if (i3 > i4)
                i4 = i3;
            
            // hMod: Store block data to call BLOCK_CREATED
            Block blockClicked = new Block(etc.getServer().getBlockIdAt(m, n, i1), m, n, i1);
            blockClicked.setFaceClicked(Block.Face.fromId(paramfz.e));
            
            // hMod: call BLOCK_RIGHTCLICKED
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, new Object[]{((er)e).getPlayer(), blockClicked, new Item(new hl(paramfz.a))});
            
            // hMod: call original BLOCK_CREATED
            Block blockPlaced = new Block(paramfz.a, m, n, i1);
            if (paramfz.e == 0) {
                blockPlaced.setY(blockPlaced.getY() - 1);
            } else if (paramfz.e == 1) {
                blockPlaced.setY(blockPlaced.getY() + 1);
            } else if (paramfz.e == 2) {
                blockPlaced.setZ(blockPlaced.getZ() - 1);
            } else if (paramfz.e == 3) {
                blockPlaced.setZ(blockPlaced.getZ() + 1);
            } else if (paramfz.e == 4) {
                blockPlaced.setX(blockPlaced.getX() - 1);
            } else if (paramfz.e == 5) {
                blockPlaced.setX(blockPlaced.getX() + 1);
            } 
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_CREATED, new Object[]{e, blockPlaced, blockClicked, paramfz.a});
            
            // hMod: If we were building inside spawn, bail! (unless ops/admin)
            if ((i4 > etc.getInstance().getSpawnProtectionSize() || bool) && getPlayer().canBuild()) {
                hl localhl = paramfz.a >= 0 ? new hl(paramfz.a) : null;
                this.e.c.a(this.e, this.d.e, localhl, m, n, i1, i2);
            } else {
                // hMod: No point sending the client to update the blocks, you weren't allowed to place!
                this.d.e.B = false;
                return;
            }

            // hMod: these are the 'block changed' packets for the client.
            this.e.a.b(new fl(m, n, i1, this.d.e));

            if (i2 == 0)
                n--;
            if (i2 == 1)
                n++;
            if (i2 == 2)
                i1--;
            if (i2 == 3)
                i1++;
            if (i2 == 4)
                m--;
            if (i2 == 5)
                m++;

            this.e.a.b(new fl(m, n, i1, this.d.e));
        }
        this.d.e.B = false;
    }

    public void a(String paramString) {
        // hMod: disconnect!
        etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, new Object[]{e});
        a.info(this.e.as + " lost connection: " + paramString);
        this.d.f.a(new bh("§e" + this.e.as + " left the game."));
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(ir paramir) {
        a.warning(getClass() + " wasn't prepared to deal with a " + paramir.getClass());
        c("Protocol error, unexpected packet");
    }

    public void b(ir paramir) {
        this.b.a(paramir);
    }

    public void a(gr paramgr) {
        int m = paramgr.b;
        this.e.al.d = (this.e.al.a.length - 1);
        if (m == 0)
            this.k = null;
        else {
            this.k = new hl(m);
        }
        this.e.al.a[this.e.al.d] = this.k;
        this.d.k.a(this.e, new gr(this.e.g, m));
    }

    public void a(l paraml) {
        double d1 = paraml.b / 32.0D;
        double d2 = paraml.c / 32.0D;
        double d3 = paraml.d / 32.0D;
        // hMod: allow item drops
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_DROP, new Object[]{e, new Item(paraml.h, paraml.i)})) {
            gj localgj = new gj(this.d.e, d1, d2, d3, new hl(paraml.h, paraml.i));
            localgj.s = (paraml.e / 128.0D);
            localgj.t = (paraml.f / 128.0D);
            localgj.u = (paraml.g / 128.0D);
            localgj.c = 10;
            this.d.e.a(localgj);
        }
    }

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
        //getPlayer().command(paramString);
    }

    public void a(q paramq) {
        if (paramq.b == 1) {
            // hMod: Swing teh arm!
            etc.getLoader().callHook(PluginLoader.Hook.ARM_SWING, new Object[]{e});
            this.e.F();
        }
    }

    public void a(ju paramju) {
        this.b.a("Quitting");
    }

    public int b() {
        return this.b.d();
    }

    public void b(String paramString) {
        b(new bh("§7" + paramString));
    }

    public String c() {
        return this.e.as;
    }

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
            hl[] temp = this.e.al.a;
            this.e.al.a = paramu.b;
            this.e.al.a[this.e.al.d] = k;	// hMod: Preserve item in hand
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_CHANGE, new Object[]{e})) {
                this.e.al.a = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
        if (paramu.a == -2) {
            // hMod: Equipment
            hl[] temp = this.e.al.c;
            this.e.al.c = paramu.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EQUIPMENT_CHANGE, new Object[]{e})) {
                this.e.al.c = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
        if (paramu.a == -3) {
            // hMod: Craft inventory
            hl[] temp = this.e.al.b;
            this.e.al.b = paramu.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.CRAFTINVENTORY_CHANGE, new Object[]{e})) {
                this.e.al.b = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
    }

    public void d() {
        this.b.a(new u(-1, this.e.al.a));
        this.b.a(new u(-2, this.e.al.c));
        this.b.a(new u(-3, this.e.al.b));
    }

    public void a(jf paramjf) {
        if (paramjf.e.d("x") != paramjf.a)
            return;
        if (paramjf.e.d("y") != paramjf.b)
            return;
        if (paramjf.e.d("z") != paramjf.c)
            return;

        ay localay = this.d.e.k(paramjf.a, paramjf.b, paramjf.c);
        if (localay != null) {
            if (localay instanceof ia) {
                // hMod: Chest
                ia chest = (ia) localay;
                hl[] temp = chest.getContents();
                localay.a(paramjf.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Chest(chest)}) || !e.getPlayer().canBuild()) {
                    chest.setContents(temp);
                }
            } else if (localay instanceof dt) {
                // hMod: Furnace
                dt furnace = (dt) localay;
                hl[] temp = furnace.getContents();
                localay.a(paramjf.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Furnace(furnace)}) || !e.getPlayer().canBuild()) {
                    furnace.setContents(temp);
                }
            } else if (localay instanceof jl) {
                // hMod: Sign
                jl sign = (jl) localay;
                String[] temp = sign.e;
                localay.a(paramjf.e);
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
        dx localdx = this.d.e.a(parama.b);

        if ((localdx != null) && (this.e.i(localdx)))
            if (parama.c == 0)
                this.e.g(localdx);
            else if (parama.c == 1)
                this.e.h(localdx);
    }

    public void a(az paramaz) {
        if (this.e.aQ > 0)
            return;

        this.e = this.d.f.d(this.e);
    }
}
