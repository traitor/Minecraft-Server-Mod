
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class kk extends fs
        implements fn {

    public static Logger a = Logger.getLogger("Minecraft");
    public bs b;
    public boolean c = false;
    private MinecraftServer d;
    private fi e;
    private int f = 0;
    private double g;
    private double h;
    private double i;
    private boolean j = true;
    private Map<Integer,Short> k = new HashMap<Integer,Short>();

    public kk(MinecraftServer paramMinecraftServer, bs parambs, fi paramfi) {
        this.d = paramMinecraftServer;
        this.b = parambs;
        parambs.a(this);
        this.e = paramfi;
        paramfi.a = this;
    }

    /**
     * Returns the item in player's hand
     * 
     * @return
     */
    public int getItemInHand() {
        if (this.e.an.e() != null) {
            return this.e.an.e().c;
        }
        return -1;
    }

    
    public Player getPlayer() {
        return e.getPlayer();
    }

    /**
     * Sends a message to the player
     *
     * @param msg
     */
    public void msg(String msg) {
        b(new br(msg));
    }

    public void a() {
        this.b.a();
        if (this.f++ % 20 == 0) {
            this.b.a(new lm());
        }
    }

    public void a(String paramString) {
        this.b.a(new ky(paramString));
        this.b.c();
        this.d.f.a(new br("\u00A7e" + this.e.aw + " left the game."));
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(hy paramhx) {
        if (!this.j) {
            double d1 = paramhx.b - this.h;
            if ((paramhx.a == this.g) && (d1 * d1 < 0.01D) && (paramhx.c == this.i)) {
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

            etc.getLoader().callHook(PluginLoader.Hook.PLAYER_MOVE, ((fi)this.e).getPlayer(), from, to);
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
                if (paramhx.i) {
                    f1 = paramhx.e;
                    f2 = paramhx.f;
                }
                if ((paramhx.h) && (paramhx.b == -999.0D) && (paramhx.d == -999.0D)) {
                    d6 = paramhx.a;
                    d7 = paramhx.c;
                }

                this.e.A = paramhx.g;

                // hMod: Make tmp copy of e.k as it sets k to null but has to run first :/
                ep tmp = this.e.k;
                this.e.F();
                this.e.c(d6, 0.0D, d7);
                this.e.b(d3, d4, d5, f1, f2);
                this.e.s = d6;
                this.e.u = d7;

                // hMod: set player as no longer in vehicle (that of tmp).
                this.d.e.b(tmp, true);

                if (this.e.k != null) {
                    this.d.e.b(this.e.k, true);
                }
                
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

            if ((paramhx.h) && (paramhx.b == -999.0D) && (paramhx.d == -999.0D)) {
                paramhx.h = false;
            }

            if (paramhx.h) {
                d3 = paramhx.a;
                d4 = paramhx.b;
                d5 = paramhx.c;
                double d7 = paramhx.d - paramhx.b;
                if ((d7 > 1.65D) || (d7 < 0.1D)) {
                    a("Illegal stance");
                    a.warning(this.e.aw + " had an illegal stance: " + d7);
                }
                this.e.ak = paramhx.d;
            }
            if (paramhx.i) {
                f3 = paramhx.e;
                f4 = paramhx.f;
            }

            this.e.F();
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
                a.warning(this.e.aw + " moved wrongly!");
                System.out.println("Got position " + d3 + ", " + d4 + ", " + d5);
                System.out.println("Expected " + this.e.p + ", " + this.e.q + ", " + this.e.r);
            }
            this.e.b(d3, d4, d5, f3, f4);

            int i1 = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;
            if ((m != 0) && ((n != 0) || (i1 == 0))) {
                a(this.g, this.h, this.i, f3, f4);
                return;
            }

            this.e.A = paramhx.g;
            this.d.f.b(this.e);
            this.e.b(this.e.q - d2, paramhx.g);
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
        Player player = getPlayer();
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.TELEPORT, player, player.getLocation(), from)) {
            return;
        }
        
        this.j = false;
        this.g = paramDouble1;
        this.h = paramDouble2;
        this.i = paramDouble3;
        this.e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        this.e.a.b(new ew(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }

    // hMod: Store x/y/z
    int x, y, z, type;

    public void a(jd paramjc) {
        if (paramjc.e == 4) {
            // hMod: item_drop hook
            il rawil = e.an.a[e.an.c];
            Item toDrop = rawil != null ? new Item(rawil) : null;
            if (toDrop != null && toDrop.getAmount() > 0) {
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_DROP, new Object[]{this.getPlayer(), toDrop})) {
                    getPlayer().getInventory().update();
                    this.e.J();
                    return;
                }
            }
            // end hMod
            this.e.L();
            return;
        }
        // hMod: We allow admins and ops to dig!
        boolean bool = this.d.e.B = this.d.f.g(this.e.aw) || getPlayer().isAdmin();
        int m = 0;
        if (paramjc.e == 0) {
            m = 1;
        }
        if (paramjc.e == 1) {
            m = 1;
        }

        int n = paramjc.a;
        int i1 = paramjc.b;
        int i2 = paramjc.c;
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

        int i3 = paramjc.d;
        int i4 = (int) ic.e(n - this.d.e.m);
        int i5 = (int) ic.e(i2 - this.d.e.o);
        if (i4 > i5) {
            i5 = i4;
        }

        // hMod: the player
        Player player = getPlayer();

        if (paramjc.e == 0) {
            // hMod: Start digging
            // No buildrights
            if (!getPlayer().canBuild()) {
                return;
            }
            // hMod: Custom spawn prot size
            if ((i5 > etc.getInstance().getSpawnProtectionSize()) || (bool)) {
                // hMod: Dig hooks
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
        } else if (paramjc.e == 2) {
            // hMod: Stop digging
            Block block = etc.getServer().getBlockAt(n, i1, i2);
            block.setStatus(2); // Stopped digging
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);
            this.e.c.a();
        } else if (paramjc.e == 1) {
            // hMod: Continue digging - don't forget spawn size
            if (!getPlayer().canBuild()) {
                return;
            }
            if ((i5 > etc.getInstance().getSpawnProtectionSize()) || (bool)) {
                Block block = etc.getServer().getBlockAt(n, i1, i2);
                block.setStatus(1); // Digging
                if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block)) {
                    this.e.c.a(n, i1, i2, i3);
                }
            }
        } else if (paramjc.e == 3) {
            // hMod: Break block
            Block block = new Block(type, x, y, z);
            block.setStatus(3);
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

            double d3 = this.e.p - (n + 0.5D);
            double d5 = this.e.q - (i1 + 0.5D);
            double d7 = this.e.r - (i2 + 0.5D);
            double d9 = d3 * d3 + d5 * d5 + d7 * d7;
            if (d9 < 256.0D) {
                this.e.a.b(new gd(n, i1, i2, this.d.e));
            }
        }
        this.d.e.B = false;
    }

    // hMod: Store the blocks between blockPlaced packets
    Block lastRightClicked;
    
    public void a(gt paramgs) {
        il localik = this.e.an.e();

        // hMod: We allow admins and ops to build!
        boolean bool = d.e.B = (d.f.g(getPlayer().getName()) || getPlayer().isAdmin());

        // hMod: Store block data to call hooks
        Block blockClicked = null;
        Block blockPlaced = null;

        if (paramgs.d == 255) {
            // ITEM_USE -- if we have a lastRightClicked then it could be a usable location
            blockClicked = lastRightClicked;
            lastRightClicked = null;
        } else {
            // RIGHTCLICK or BLOCK_PLACE .. or nothing
            blockClicked = new Block(etc.getServer().getBlockIdAt(paramgs.a, paramgs.b, paramgs.c), paramgs.a, paramgs.b, paramgs.c);
            blockClicked.setFaceClicked(Block.Face.fromId(paramgs.d));
            lastRightClicked = blockClicked;
        }

        // If we clicked on something then we also have a location to place the block
        if (blockClicked != null && localik != null) {
            blockPlaced = new Block( localik.c, blockClicked.getX(), blockClicked.getY(), blockClicked.getZ());
            if (paramgs.d == 0) {
                blockPlaced.setY(blockPlaced.getY() - 1);
            } else if (paramgs.d == 1) {
                blockPlaced.setY(blockPlaced.getY() + 1);
            } else if (paramgs.d == 2) {
                blockPlaced.setZ(blockPlaced.getZ() - 1);
            } else if (paramgs.d == 3) {
                blockPlaced.setZ(blockPlaced.getZ() + 1);
            } else if (paramgs.d == 4) {
                blockPlaced.setX(blockPlaced.getX() - 1);
            } else if (paramgs.d == 5) {
                blockPlaced.setX(blockPlaced.getX() + 1);
            }
        }        
        if (paramgs.d == 255) {
            // hMod: call our version with extra blockClicked/blockPlaced
            if (blockPlaced != null) {
                // Set the type of block to what it currently is
                blockPlaced.setType(etc.getServer().getBlockIdAt(blockPlaced.getX(), blockPlaced.getY(), blockPlaced.getZ()));
            }
            if (localik == null) return;
            ((Digging)this.e.c).a(this.e, this.d.e, localik, blockPlaced, blockClicked);
            
        } else {
            int m = paramgs.a;
            int n = paramgs.b;
            int i1 = paramgs.c;
            int i2 = paramgs.d;
            int i3 = (int) ic.e(m - this.d.e.m);
            int i4 = (int) ic.e(i1 - this.d.e.o);
            if (i3 > i4) {
                i4 = i3;
            }
            // hMod: call BLOCK_RIGHTCLICKED
            Item item = (localik != null) ? new Item(localik) : new Item(Item.Type.Air);
            Player player = ((fi)this.e).getPlayer();
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, player, blockClicked, item);

            // hMod: call original BLOCK_CREATED
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_CREATED, player, blockPlaced, blockClicked, item.getItemId());

            // hMod: If we were building inside spawn, bail! (unless ops/admin)
            if (((i4 > etc.getInstance().getSpawnProtectionSize() && !etc.getInstance().isOnItemBlacklist(item.getItemId())) || bool) && player.canBuild()) {
                this.e.c.a(this.e, this.d.e, localik, m, n, i1, i2);
            } else {
                // hMod: No point sending the client to update the blocks, you weren't allowed to place!
                this.d.e.B = false;
                return;
            }
            
            // hMod: these are the 'block changed' packets for the client.
            this.e.a.b(new gd(m, n, i1, this.d.e));

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

            this.e.a.b(new gd(m, n, i1, this.d.e));
        }
        if ((localik != null) && (localik.a == 0)) {
            this.e.an.a[this.e.an.c] = null;
        }

        this.e.am = true;
        this.e.an.a[this.e.an.c] = il.a(this.e.an.a[this.e.an.c]);
        gp localgo = this.e.ap.a(this.e.an, this.e.an.c);
        this.e.ap.a();
        this.e.am = false;

        if (!il.a(this.e.an.e(), paramgs.e)) {
            b(new hq(this.e.ap.f, localgo.c, this.e.an.e()));
        }

        this.d.e.B = false;
    }

    public void a(String paramString, Object[] paramArrayOfObject) {
        // hMod: disconnect!
        etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, ((fi)e).getPlayer());
        a.info(this.e.aw + " lost connection: " + paramString);
        this.d.f.a(new br("\u00A7e" + this.e.aw + " left the game."));
        this.d.f.c(this.e);
        this.c = true;
    }

    public void a(jv paramju) {
        a.warning(getClass() + " wasn't prepared to deal with a " + paramju.getClass());
        a("Protocol error, unexpected packet");
    }

    public void b(jv paramju) {
        this.b.a(paramju);
    }

    public void a(hn paramhm) {
        this.e.an.c = paramhm.a;
    }

    public void a(br parambr) {
        // hMod: redirect chathandling to player class
        String str = parambr.a;
        getPlayer().chat(str);
    }

    private void c(String paramString) {
        if (paramString.toLowerCase().startsWith("/me ")) {
            paramString = "* " + this.e.aw + " " + paramString.substring(paramString.indexOf(" ")).trim();
            a.info(paramString);
            this.d.f.a(new br(paramString));
        } else if (paramString.toLowerCase().startsWith("/kill")) {
            this.e.a(null, 1000);
        } else {
            String[] localObject;
            if (paramString.toLowerCase().startsWith("/tell ")) {
                localObject = paramString.split(" ");
                if (localObject.length >= 3) {
                    paramString = paramString.substring(paramString.indexOf(" ")).trim();
                    paramString = paramString.substring(paramString.indexOf(" ")).trim();

                    paramString = "\u00A77" + this.e.aw + " whispers " + paramString;
                    a.info(paramString + " to " + localObject[1]);
                    if (!this.d.f.a(localObject[1], new br(paramString))) {
                        b(new br("\u00A7cThere's no player by that name online."));
                    }
                }
            } else if (this.d.f.g(this.e.aw)) {
                String localObject2 = paramString.substring(1);
                a.info(this.e.aw + " issued server command: " + (String) localObject2);
                this.d.a((String) localObject2, this);
            } else {
                String localObject2 = paramString.substring(1);
                a.info(this.e.aw + " tried command: " + (String) localObject2);
            }
        }
    }

    public void a(v paramv) {
        if (paramv.b == 1) {
            // hMod: Swing teh arm!
            etc.getLoader().callHook(PluginLoader.Hook.ARM_SWING, ((fi)e).getPlayer());
            this.e.H();
        } else if (paramv.b == 104) {
            this.e.al = true;
        } else if (paramv.b == 105) {
            this.e.al = false;
        }
    }

    public void a(ky paramkx) {
        this.b.a("disconnect.quitting", new Object[0]);
    }

    public int b() {
        return this.b.d();
    }

    public void b(String paramString) {
        b(new br("\u00A77" + paramString));
    }

    public String c() {
        return this.e.aw;
    }

    public void a(a parama) {
        ep localep = this.d.e.a(parama.b);

        if ((localep != null) && (this.e.i(localep))) {
            if (parama.c == 0) {
                this.e.g(localep);
            } else if (parama.c == 1) {
                this.e.h(localep);
            }
        }
    }

    public void a(bh parambh) {
        if (this.e.ba > 0) {
            return;
        }

        this.e = this.d.f.d(this.e);
    }

    public void a(f paramf) {
        this.e.K();
    }

    public void a(cs paramcs) {
        if ((this.e.ap.f == paramcs.a) && (this.e.ap.c(this.e))) {
            // hMod: item drop & inventory move hooks
            int clickedSlot = paramcs.b;
            boolean leftClick = paramcs.c == 0;
            boolean haveExtraInv = paramcs.a != 0;
            Player p = getPlayer();
            Inventory stage = !haveExtraInv ? getPlayer().getInventory() : e.getLastOpenedInventory();
            // are we accessing an pinv slot while in a window?
            if ((stage instanceof Furnace && clickedSlot >= 3) || (stage instanceof Workbench && clickedSlot >= 10)
                    || (stage instanceof Chest && clickedSlot >= 27) || (stage instanceof DoubleChest && clickedSlot >= 54)) {
                // yes. we must transform the slot id accordingly...
                if (stage instanceof Furnace) {
                    clickedSlot -= 3;
                } else if (stage instanceof Workbench) {
                    clickedSlot -= 10;
                } else if (stage instanceof Chest) {
                    clickedSlot -= 27;
                } else if (stage instanceof DoubleChest) {
                    clickedSlot -= 54;
                }
                if (clickedSlot >= 0 && clickedSlot < 27) {
                    clickedSlot += 9;
                } else if (clickedSlot >= 27 && clickedSlot < 36) {
                    clickedSlot -= 27;
                }
                // and then set the stage to the inventory.
                stage = getPlayer().getInventory();
                haveExtraInv = false;
            } else if (!haveExtraInv) // yes.. let's transform packet slot id > server slot id
            {
                if (clickedSlot >= 36 && clickedSlot <= 44) {
                    clickedSlot -= 36;
                } else {
                    switch (clickedSlot) {
                        case 5:
                            clickedSlot = 39;
                            break;
                        case 6:
                            clickedSlot = 38;
                            break;
                        case 7:
                            clickedSlot = 37;
                            break;
                        case 8:
                            clickedSlot = 36;
                            break;
                    }
                }
            }
            // we don't have a wrapper for crafting inventory yet... so i'm ignoring it for now...
            if (((clickedSlot >= 5 || clickedSlot == -999) && !haveExtraInv) || haveExtraInv) {
                if (clickedSlot == -999) {
                    // we're dropping item!
                    il rawil = this.e.an.i();
                    if (rawil != null) {
                        Item toDrop = new Item(rawil);
                        if (!leftClick) {
                            toDrop.setAmount(1);
                        }
                        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_DROP, new Object[]{this.getPlayer(), toDrop})) {
                            p.getInventory().update();
                            e.J();
                            return;
                        }
                    }
                } else {
                    /*
                     * there are 3 operations here..
                     * 1. pick up item (when slot is 1 and cursor is 0)
                     * 2. put item (when slot is 0 and cursor is 1
                     * 3. swap item (when slot and cursor is 1)
                     */
                    boolean reject = false;
                    il rawil = e.an.i();
                    Item cursorItem = rawil != null ? new Item(rawil) : null;
                    Item slotItem = stage.getItemFromSlot(clickedSlot);
                    boolean validSlot = true;
                    if (!haveExtraInv && cursorItem != null) // if we are accessing player inventory, and cursorItem isn't null
                    {
                        switch (clickedSlot) { // and we are accessing the damn equip slots
                            // make sure we don't fire an event where nothing would have been done (can't put non-equips into equip slots)
                            case 39: // Helmets
                                validSlot = cursorItem.getItemId() == 298 || cursorItem.getItemId() == 302 || cursorItem.getItemId() == 306 || cursorItem.getItemId() == 310 || cursorItem.getItemId() == 314;
                                break;
                            case 38: // Chestplates
                                validSlot = cursorItem.getItemId() == 299 || cursorItem.getItemId() == 303 || cursorItem.getItemId() == 307 || cursorItem.getItemId() == 311 || cursorItem.getItemId() == 315;
                                break;
                            case 37: // Leggings
                                validSlot = cursorItem.getItemId() == 300 || cursorItem.getItemId() == 304 || cursorItem.getItemId() == 308 || cursorItem.getItemId() == 312 || cursorItem.getItemId() == 316;
                                break;
                            case 36: // Boots
                                validSlot = cursorItem.getItemId() == 301 || cursorItem.getItemId() == 305 || cursorItem.getItemId() == 309 || cursorItem.getItemId() == 313 || cursorItem.getItemId() == 317;
                                break;
                        }
                    }
                    if (validSlot) {
                        if (cursorItem != null && slotItem != null && slotItem.getItemId() != cursorItem.getItemId()) {
                            reject = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_SWAP, new Object[]{this.getPlayer(), stage, clickedSlot, slotItem, cursorItem});
                        } else if (((cursorItem != null && slotItem == null) || (cursorItem != null && slotItem != null && slotItem.getItemId() == cursorItem.getItemId()))) {
                            if (!leftClick) {
                                cursorItem.setAmount(1);
                            }
                            if (slotItem != null) {
                                int maxInSlot = gm.c[cursorItem.getItemId()].b();
                                if (slotItem.getAmount() + cursorItem.getAmount() > maxInSlot) {
                                    cursorItem.setAmount(maxInSlot - slotItem.getAmount());
                                }
                            }

                            // place item
                            if (cursorItem.getAmount() > 0) {
                                reject = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_PLACE, new Object[]{this.getPlayer(), stage, cursorItem, clickedSlot});
                            } else if (cursorItem.getAmount() < 0) {
                                cursorItem.setAmount(Math.abs(slotItem.getAmount()));
                                reject = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_TAKE, new Object[]{this.getPlayer(), stage, cursorItem, clickedSlot});
                            }
                        } else if (cursorItem == null && slotItem != null) {
                            if (!leftClick) {
                                slotItem.setAmount((slotItem.getAmount() + 1) / 2);
                            }
                            // take shit
                            reject = (Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_TAKE, new Object[]{this.getPlayer(), stage, slotItem, clickedSlot});
                        }
                    }
                    if (reject) {
                        getPlayer().getInventory().update();
                        this.e.J();
                        return;
                    }
                }
            }

            // end hMod

            il localik = this.e.ap.a(paramcs.b, paramcs.c, this.e);

            if (il.a(paramcs.e, localik)) {
                this.e.a.b(new ay(paramcs.a, paramcs.d, true));
                this.e.am = true;
                this.e.ap.a();
                this.e.J();
                this.e.am = false;
            } else {
                this.k.put(Integer.valueOf(this.e.ap.f), Short.valueOf(paramcs.d));
                this.e.a.b(new ay(paramcs.a, paramcs.d, false));
                this.e.ap.a(this.e, false);

                ArrayList<il> localArrayList = new ArrayList<il>();
                for (int m = 0; m < this.e.ap.e.size(); m++) {
                    localArrayList.add(((gp) this.e.ap.e.get(m)).c());
                }
                this.e.a(this.e.ap, localArrayList);
            }
        }
    }

    public void a(ay paramay) {
        Short localShort = (Short) this.k.get(Integer.valueOf(this.e.ap.f));
        if ((localShort != null) && (paramay.b == localShort.shortValue()) && (this.e.ap.f == paramay.a) && (!this.e.ap.c(this.e))) {
            this.e.ap.a(this.e, true);
        }
    }

    public void a(gn paramgm) {
        if (this.d.e.f(paramgm.a, paramgm.b, paramgm.c)) {
            bg localbg = this.d.e.l(paramgm.a, paramgm.b, paramgm.c);
            int n;
            int i1;
            for (int m = 0; m < 4; m++) {
                n = 1;
                // hMod: Remove the char limit, for plugins.
                //if (paramgm.d[m].length() > 15) {
                for (i1 = 0; i1 < paramgm.d[m].length(); i1++) {
                    if (fq.a.indexOf(paramgm.d[m].charAt(i1)) < 0) {
                        n = 0;
                    }
                }
                if (n == 0) {
                    paramgm.d[m] = "!?";
                }
            }
            if ((localbg instanceof kp)) {
                int m = paramgm.a;
                n = paramgm.b;
                i1 = paramgm.c;
                kp localko = (kp) localbg;

                // hMod: Copy the old line text
                String[] old = Arrays.copyOf(localko.e, localko.e.length);

                for (int i2 = 0; i2 < 4; i2++) {
                    localko.e[i2] = paramgm.d[i2];
                }

                // hMod: Check if we can change it
                Sign sign = new Sign(localko);
                if ((Boolean)etc.getLoader().callHook(PluginLoader.Hook.SIGN_CHANGE, getPlayer(), sign)) {
                    localko.e = Arrays.copyOf(old, old.length);
                }

                localko.d();
                this.d.e.g(m, n, i1);
            }
        }
    }
}
