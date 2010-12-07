import java.io.PrintStream;
import java.util.List;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class ji extends fc implements ey {
    public static Logger a = Logger.getLogger("Minecraft");
    public bi b;
    public boolean c = false;
    private MinecraftServer d;
    private et e;
    private int f = 0;
    private double g;
    private double h;
    private double i;
    private boolean j = true;

    private hn k = null;

    public ji(MinecraftServer paramMinecraftServer, bi parambi, et paramet) {
        this.d = paramMinecraftServer;
        this.b = parambi;
        parambi.a(this);
        this.e = paramet;
        paramet.a = this;
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
        if (this.f++ % 20 == 0) {
            this.b.a(new kj());
        }
    }

    public void c(String paramString) {
        // hMod: Should we add this too here?
        // etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, ((es)e).getPlayer());
        this.b.a(new jw(paramString));
        this.b.c();
        this.d.f.a(new bh("§e" + this.e.at + " left the game."));
        this.d.f.c(this.e);
        this.c = true;
    }

    @Override
    public void a(hd paramhd) {
        if (!this.j) {
            double d1 = paramhd.b - this.h;
            if ((paramhd.a == this.g) && (d1 * d1 < 0.01D) && (paramhd.c == this.i)) {
                this.j = true;
            }
        }
        // hMod: Notice player movement
        if ((int) Math.floor(this.g) != (int) Math.floor(getPlayer().getX()) || (int) Math.floor(this.h) != (int) Math.floor(getPlayer().getY()) || (int) Math.floor(this.i) != (int) Math.floor(getPlayer().getZ())) {
            Location from = new Location();
            from.x = (int) Math.floor(this.g);
            from.y = (int) Math.floor(this.h);
            from.z = (int) Math.floor(this.i);
            from.rotX = getPlayer().getRotation();
            from.rotY = getPlayer().getPitch();

            Location to = new Location();
            to.x = (int) Math.floor(this.e.p);
            to.y = (int) Math.floor(this.e.q);
            to.z = (int) Math.floor(this.e.r);
            to.rotX = getPlayer().getRotation();
            to.rotY = getPlayer().getPitch();

            etc.getLoader().callHook(PluginLoader.Hook.PLAYER_MOVE, ((et)this.e).getPlayer(), from, to);
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
                if (paramhd.i) {
                    f1 = paramhd.e;
                    f2 = paramhd.f;
                }
                if ((paramhd.h) && (paramhd.b == -999.0D) && (paramhd.d == -999.0D)) {
                    d6 = paramhd.a;
                    d7 = paramhd.c;
                }

                this.e.A = paramhd.g;

                // hMod: Make tmp copy of e.k as it sets k to null but has to run first :/
                ea tmp = this.e.k;
                this.e.k();
                this.e.c(d6, 0.0D, d7);
                this.e.b(d3, d4, d5, f1, f2);
                this.e.s = d6;
                this.e.u = d7;

                // hMod: set player as no longer in vehicle (that of tmp).
                this.d.e.b(tmp, true);

                // hMod: prevent this null pointer exception...
                if(this.e.k != null && this.e.k.j != null) {
                    this.e.k.A();
                }
                this.d.f.b(this.e);
                this.g = this.e.p;
                this.h = this.e.q;
                this.i = this.e.r;
                this.d.e.f(this.e);

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

            if ((paramhd.h) && (paramhd.b == -999.0D) && (paramhd.d == -999.0D)) {
                paramhd.h = false;
            }

            if (paramhd.h) {
                d3 = paramhd.a;
                d4 = paramhd.b;
                d5 = paramhd.c;
                double d7 = paramhd.d - paramhd.b;
                if ((d7 > 1.65D) || (d7 < 0.1D)) {
                    c("Illegal stance");
                    a.warning(this.e.at + " had an illegal stance: " + d7);
                }
                this.e.ak = paramhd.d;
            }
            if (paramhd.i) {
                f3 = paramhd.e;
                f4 = paramhd.f;
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
                a.warning(this.e.at + " moved wrongly!");
                System.out.println("Got position " + d3 + ", " + d4 + ", " + d5);
                System.out.println("Expected " + this.e.p + ", " + this.e.q + ", " + this.e.r);
            }
            this.e.b(d3, d4, d5, f3, f4);

            int i1 = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;
            if ((m != 0) && ((n != 0) || (i1 == 0))) {
                a(this.g, this.h, this.i, f3, f4);
                return;
            }

            this.e.A = paramhd.g;
            this.d.f.b(this.e);
            this.e.b(this.e.q - d2, paramhd.g);
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
        Player player = ((et)e).getPlayer();
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.TELEPORT, player, player.getLocation(), from)) {
            return;
        }

        this.j = false;
        this.g = paramDouble1;
        this.h = paramDouble2;
        this.i = paramDouble3;
        this.e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        this.e.a.b(new eh(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }

    // hMod: Store x/y/z
    int x, y, z, type;

    // Destroy function
    @Override
    public void a(ie paramie) {
        this.e.am.a[this.e.am.d] = this.k;
        // hMod: We allow admins and ops to dig!
        boolean bool = this.d.e.B = this.d.f.g(this.e.at) || getPlayer().isAdmin();
        int m = 0;
        if (paramie.e == 0) {
            m = 1;
        }
        if (paramie.e == 1) {
            m = 1;
        }

        int n = paramie.a;
        int i1 = paramie.b;
        int i2 = paramie.c;
        if (m != 0) {
            double d1 = this.e.p - (n + 0.5D);
            double d2 = this.e.q - (i1 + 0.5D);
            double d4 = this.e.r - (i2 + 0.5D);
            double d6 = d1 * d1 + d2 * d2 + d4 * d4;
            if (d6 > 36.0D) {
                return;
            }

            double d8 = this.e.q;
            this.e.q = this.e.ak;

            this.e.q = d8;
        }

        int i3 = paramie.d;
        int i4 = (int) hh.e(n - this.d.e.m);
        int i5 = (int) hh.e(i2 - this.d.e.o);
        if (i4 > i5) {
            i5 = i4;
        }

        // hMod: the player
        Player player = ((et)e).getPlayer();

        if (paramie.e == 0) {
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
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block)) {
                    this.e.c.a(n, i1, i2);
                }
            }
        } else if (paramie.e == 2) {
            // hMod: Stop digging
            Block block = etc.getServer().getBlockAt(n, i1, i2);
            block.setStatus(2); // Stopped digging
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);
            this.e.c.a();
        } else if (paramie.e == 1) {
            // hMod: Continue digging
            if (!getPlayer().canBuild()) {
                return;
            }

            if (i5 > etc.getInstance().getSpawnProtectionSize() || (bool)) {
                Block block = etc.getServer().getBlockAt(n, i1, i2);
                block.setStatus(1); // Digging
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block)) {
                    this.e.c.a(n, i1, i2, i3);
                }
            }
        } else if (paramie.e == 3) {
            // hMod: Break block
            Block block = new Block(type, x, y, z);
            block.setStatus(3);
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

            double d3 = this.e.p - (n + 0.5D);
            double d5 = this.e.q - (i1 + 0.5D);
            double d7 = this.e.r - (i2 + 0.5D);
            double d9 = d3 * d3 + d5 * d5 + d7 * d7;
            if (d9 < 256.0D) {
                this.e.a.b(new fn(n, i1, i2, this.d.e));
            }
        }
        this.d.e.B = false;
    }

    // hMod: Store the blocks between blockPlaced packets
    Block lastRightClicked;
    
    @Override
    public void a(gb paramgb) {
        //System.out.println(String.format("BlockPlacePacket: %d @ [%d,%d,%d] dir %s", paramgb.a, paramgb.b, paramgb.c, paramgb.d, paramgb.e ));
        // hMod: We allow admins and ops to build!
        boolean bool = d.e.B = (d.f.g(getPlayer().getName()) || getPlayer().isAdmin());

        // hMod: Store block data to call hooks
        Block blockClicked = null;
        Block blockPlaced = null;

        if (paramgb.c == 255) {
            // ITEM_USE -- if we have a lastRightClicked then it could be a usable location
            blockClicked = lastRightClicked;
            lastRightClicked = null;
        } else {
            // RIGHTCLICK or BLOCK_PLACE .. or nothing
            blockClicked = new Block(etc.getServer().getBlockIdAt(paramgb.b, paramgb.c, paramgb.d), paramgb.b, paramgb.c, paramgb.d);
            blockClicked.setFaceClicked(Block.Face.fromId(paramgb.e));
            lastRightClicked = blockClicked;
        }

        // If we clicked on something then we also have a location to place the block
        if (blockClicked != null) {
            blockPlaced = new Block( paramgb.a, blockClicked.getX(), blockClicked.getY(), blockClicked.getZ());
            if (paramgb.e == 0) {
                blockPlaced.setY(blockPlaced.getY() - 1);
            } else if (paramgb.e == 1) {
                blockPlaced.setY(blockPlaced.getY() + 1);
            } else if (paramgb.e == 2) {
                blockPlaced.setZ(blockPlaced.getZ() - 1);
            } else if (paramgb.e == 3) {
                blockPlaced.setZ(blockPlaced.getZ() + 1);
            } else if (paramgb.e == 4) {
                blockPlaced.setX(blockPlaced.getX() - 1);
            } else if (paramgb.e == 5) {
                blockPlaced.setX(blockPlaced.getX() + 1);
            }
        }

        if (paramgb.e == 255) {
            hn localhn1 = paramgb.a >= 0 ? new hn(paramgb.a) : null;
            // hMod: call our version with extra blockClicked/blockPlaced
            if (blockPlaced != null) {
                // Set the type of block to what it currently is
                blockPlaced.setType(etc.getServer().getBlockIdAt(blockPlaced.getX(), blockPlaced.getY(), blockPlaced.getZ()));
            }
            ((Digging)this.e.c).a(this.e, this.d.e, localhn1, blockPlaced, blockClicked);
        } else {
            int m = paramgb.b;
            int n = paramgb.c;
            int i1 = paramgb.d;
            int i2 = paramgb.e;
            int i3 = (int) hh.e(m - this.d.e.m);
            int i4 = (int) hh.e(i1 - this.d.e.o);
            if (i3 > i4) {
                i4 = i3;
            }
            // hMod: call BLOCK_RIGHTCLICKED
            Player player = ((et)this.e).getPlayer();
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, player, blockClicked, new Item(new hn(paramgb.a)));

            // hMod: call original BLOCK_CREATED
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_CREATED, player, blockPlaced, blockClicked, paramgb.a);

            // hMod: If we were building inside spawn, bail! (unless ops/admin)
            if (((i4 > etc.getInstance().getSpawnProtectionSize() && !etc.getInstance().isOnItemBlacklist(paramgb.a)) || bool) && player.canBuild()) {
                hn localhn = paramgb.a >= 0 ? new hn(paramgb.a) : null;
                this.e.c.a(this.e, this.d.e, localhn, m, n, i1, i2);
            } else {
                // hMod: No point sending the client to update the blocks, you weren't allowed to place!
                this.d.e.B = false;
                return;
            }

            // hMod: these are the 'block changed' packets for the client.
            this.e.a.b(new fn(m, n, i1, this.d.e));

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

            this.e.a.b(new fn(m, n, i1, this.d.e));
        }
        this.d.e.B = false;
    }

    @Override
    public void a(String paramString) {
        // hMod: disconnect!
        etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, ((et)e).getPlayer());
        a.info(this.e.at + " lost connection: " + paramString);
        this.d.f.a(new bh("§e" + this.e.at + " left the game."));
        this.d.f.c(this.e);
        this.c = true;
    }

    @Override
    public void a(it paramit) {
        a.warning(getClass() + " wasn't prepared to deal with a " + paramit.getClass());
        c("Protocol error, unexpected packet");
    }

    public void b(it paramit) {
        this.b.a(paramit);
    }

    @Override
    public void a(gt paramgt) {
        int m = paramgt.b;
        this.e.am.d = (this.e.am.a.length - 1);
        if (m == 0) {
            this.k = null;
        } else {
            this.k = new hn(m);
        }
        this.e.am.a[this.e.am.d] = this.k;
        this.d.k.a(this.e, new gt(this.e.g, m));
    }

    @Override
    public void a(l paraml) {
        double d1 = paraml.b / 32.0D;
        double d2 = paraml.c / 32.0D;
        double d3 = paraml.d / 32.0D;

        // hMod: allow item drops
        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_DROP, ((et)e).getPlayer(), new Item(paraml.h, paraml.i))) {
            gl localgl = new gl(this.d.e, d1, d2, d3, new hn(paraml.h, paraml.i));
            localgl.s = (paraml.e / 128.0D);
            localgl.t = (paraml.f / 128.0D);
            localgl.u = (paraml.g / 128.0D);
            localgl.c = 10;
            this.d.e.a(localgl);
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
            etc.getLoader().callHook(PluginLoader.Hook.ARM_SWING, ((et)e).getPlayer());
            this.e.F();
        } else if (paramq.b == 104) {
            this.e.al = true;
        } else if (paramq.b == 105) {
            this.e.al = false;
        }
    }

    @Override
    public void a(jw paramjw) {
        this.b.a("Quitting");
    }

    public int b() {
        return this.b.d();
    }

    @Override
    public void b(String paramString) {
        b(new bh("§7" + paramString));
    }

    @Override
    public String c() {
        return this.e.at;
    }

    @Override
    public void a(u paramu) {
        // hMod: Inventory handling; no inventory if you don't have buildrights
        Player player = ((et)this.e).getPlayer();
        if (!getPlayer().canBuild()) {
            getPlayer().getInventory().clearContents();
            getPlayer().getCraftingTable().clearContents();
            getPlayer().getEquipment().clearContents();
            getPlayer().getInventory().updateInventory();
            return;
        }
        if (paramu.a == -1) {
            // hMod: Inventory
            hn[] temp = this.e.am.a;
            this.e.am.a = paramu.b;
            this.e.am.a[this.e.am.d] = k; // hMod: Preserve item in hand
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_CHANGE, player)) {
                this.e.am.a = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
        if (paramu.a == -2) {
            // hMod: Equipment
            hn[] temp = this.e.am.c;
            this.e.am.c = paramu.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EQUIPMENT_CHANGE, player)) {
                this.e.am.c = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
        if (paramu.a == -3) {
            // hMod: Craft inventory
            hn[] temp = this.e.am.b;
            this.e.am.b = paramu.b;
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.CRAFTINVENTORY_CHANGE, player)) {
                this.e.am.b = temp;
                getPlayer().getInventory().updateInventory();
            }
        }
    }

    public void d() {
        this.b.a(new u(-1, this.e.am.a));
        this.b.a(new u(-2, this.e.am.c));
        this.b.a(new u(-3, this.e.am.b));
    }

    @Override
    public void a(jh paramjh) {
        if (paramjh.e.d("x") != paramjh.a) {
            return;
        }
        if (paramjh.e.d("y") != paramjh.b) {
            return;
        }
        if (paramjh.e.d("z") != paramjh.c) {
            return;
        }

        // hMod: Player
        Player player = ((et)e).getPlayer();

        ay localay = this.d.e.k(paramjh.a, paramjh.b, paramjh.c);
        if (localay != null) {
            if (localay instanceof ic) {
                // hMod: Chest
                ic chest = (ic) localay;
                hn[] temp = chest.getContents();
                localay.a(paramjh.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, player, new Chest(chest)) || !player.canBuild()) {
                    chest.setContents(temp);
                }
            } else if (localay instanceof dv) {
                // hMod: Furnace
                dv furnace = (dv) localay;
                hn[] temp = furnace.getContents();
                localay.a(paramjh.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, player, new Furnace(furnace)) || !player.canBuild()) {
                    furnace.setContents(temp);
                }
            } else if (localay instanceof jn) {
                // hMod: Sign
                jn sign = (jn) localay;
                String[] temp = sign.e;
                localay.a(paramjh.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, player, new Sign(sign)) || !player.canBuild()) {
                    sign.e = temp;
                }
            }
            // try {
            // localay.a(paramjh.e);
            // } catch (Exception localException) {
            // }
            localay.c();
        }
    }

    @Override
    public void a(a parama) {
        ea localea = this.d.e.a(parama.b);
        this.e.am.a[this.e.am.d] = this.k;

        if ((localea != null) && (this.e.i(localea))) {
            if (parama.c == 0) {
                this.e.g(localea);
            } else if (parama.c == 1) {
                this.e.h(localea);
            }
        }
    }

    @Override
    public void a(az paramaz) {
        if (this.e.aR > 0) {
            return;
        }

        this.e = this.d.f.d(this.e);
    }
}
