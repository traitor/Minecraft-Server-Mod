import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import net.minecraft.server.MinecraftServer;

public class ONetServerHandler extends ONetHandler implements OICommandListener {

    public static Logger        a = Logger.getLogger("Minecraft");
    public ONetworkManager      b;
    public boolean              c = false;
    private MinecraftServer     d;
    private OEntityPlayerMP     e;
    private int                 f = 0;
    private double              g;
    private double              h;
    private double              i;
    private boolean             j = true;
    private Map<Integer, Short> k = new HashMap<Integer, Short>();

    public ONetServerHandler(MinecraftServer paramMinecraftServer, ONetworkManager paramONetworkManager, OEntityPlayerMP paramOEntityPlayerMP) {
        d = paramMinecraftServer;
        b = paramONetworkManager;
        paramONetworkManager.a(this);
        e = paramOEntityPlayerMP;
        paramOEntityPlayerMP.a = this;
    }

    /**
     * Returns the item in player's hand
     * 
     * @return
     */
    public int getItemInHand() {
        if (e.an.e() != null)
            return e.an.e().c;
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
        b(new OPacket3Chat(msg));
    }

    public void a() {
        b.a();
        if (f++ % 20 == 0)
            b.a(new OPacket0KeepAlive());
    }

    public void a(String paramString) {
        b.a(new OPacket255KickDisconnect(paramString));
        b.c();
        d.f.a(new OPacket3Chat("\u00A7e" + e.aw + " left the game."));
        d.f.c(e);
        c = true;
    }

    @Override
    public void a(OPacket10Flying paramOPacket10Flying) {
        double d1;
        if (!j) {
            d1 = paramOPacket10Flying.b - h;
            if ((paramOPacket10Flying.a == g) && (d1 * d1 < 0.01D) && (paramOPacket10Flying.c == i))
                j = true;
        }
        // hMod: Notice player movement
        if (etc.floor(g) != etc.floor(getPlayer().getX()) || etc.floor(h) != etc.floor(getPlayer().getY()) || etc.floor(i) != etc.floor(getPlayer().getZ())) {
            Location from = new Location();
            from.x = etc.floor(g);
            from.y = etc.floor(h);
            from.z = etc.floor(i);
            from.rotX = getPlayer().getRotation();
            from.rotY = getPlayer().getPitch();

            Location to = new Location();
            to.x = etc.floor(e.p);
            to.y = etc.floor(e.q);
            to.z = etc.floor(e.r);
            to.rotX = getPlayer().getRotation();
            to.rotY = getPlayer().getPitch();

            OEntity.manager.callHook(PluginLoader.Hook.PLAYER_MOVE, getPlayer(), from, to);
        }
        if (j) {
            if (e.k != null) {
                float f1 = e.v;
                float f2 = e.w;
                e.k.E();
                double d2 = e.p;
                double d3 = e.q;
                double d4 = e.r;
                double d5 = 0.0D;
                double d6 = 0.0D;
                if (paramOPacket10Flying.i) {
                    f1 = paramOPacket10Flying.e;
                    f2 = paramOPacket10Flying.f;
                }
                if ((paramOPacket10Flying.h) && (paramOPacket10Flying.b == -999.0D) && (paramOPacket10Flying.d == -999.0D)) {
                    d5 = paramOPacket10Flying.a;
                    d6 = paramOPacket10Flying.c;
                }

                // hMod: Make tmp copy of e.k as it sets k to null but has to
                // run first :/
                OEntity tmp = e.k;

                e.A = paramOPacket10Flying.g;

                e.n();
                e.c(d5, 0.0D, d6);
                e.b(d2, d3, d4, f1, f2);
                e.s = d5;
                e.u = d6;

                // hMod: set player as no longer in vehicle (that of tmp).
                d.e.b(tmp, true);

                if (e.k != null)
                    d.e.b(e.k, true);
                if (e.k != null)
                    e.k.E();
                d.f.b(e);
                g = e.p;
                h = e.q;
                i = e.r;
                d.e.f(e);

                return;
            }

            d1 = e.q;
            g = e.p;
            h = e.q;
            i = e.r;

            double d2 = e.p;
            double d3 = e.q;
            double d4 = e.r;

            float f3 = e.v;
            float f4 = e.w;

            if ((paramOPacket10Flying.h) && (paramOPacket10Flying.b == -999.0D) && (paramOPacket10Flying.d == -999.0D))
                paramOPacket10Flying.h = false;

            if (paramOPacket10Flying.h) {
                d2 = paramOPacket10Flying.a;
                d3 = paramOPacket10Flying.b;
                d4 = paramOPacket10Flying.c;
                double d6 = paramOPacket10Flying.d - paramOPacket10Flying.b;
                if ((d6 > 1.65D) || (d6 < 0.1D)) {
                    a("Illegal stance");
                    a.warning(e.aw + " had an illegal stance: " + d6);
                }
                e.al = paramOPacket10Flying.d;
            }
            if (paramOPacket10Flying.i) {
                f3 = paramOPacket10Flying.e;
                f4 = paramOPacket10Flying.f;
            }

            e.n();
            e.R = 0.0F;
            e.b(g, h, i, f3, f4);

            double d6 = d2 - e.p;
            double d7 = d3 - e.q;
            double d8 = d4 - e.r;

            float f5 = 0.0625F;
            int m = d.e.a(e, e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;

            e.c(d6, d7, d8);
            d6 = d2 - e.p;
            d7 = d3 - e.q;
            if ((d7 > -0.5D) || (d7 < 0.5D))
                d7 = 0.0D;
            d8 = d4 - e.r;
            double d9 = d6 * d6 + d7 * d7 + d8 * d8;
            int n = 0;
            if (d9 > 0.0625D) {
                n = 1;
                a.warning(e.aw + " moved wrongly!");
                System.out.println("Got position " + d2 + ", " + d3 + ", " + d4);
                System.out.println("Expected " + e.p + ", " + e.q + ", " + e.r);
            }
            e.b(d2, d3, d4, f3, f4);

            int i1 = d.e.a(e, e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;
            if ((m != 0) && ((n != 0) || (i1 == 0))) {
                a(g, h, i, f3, f4);
                return;
            }

            e.A = paramOPacket10Flying.g;
            d.f.b(e);
            e.b(e.q - d1, paramOPacket10Flying.g);
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
        if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.TELEPORT, player, player.getLocation(), from))
            return;

        j = false;
        g = paramDouble1;
        h = paramDouble2;
        i = paramDouble3;
        e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
        e.a.b(new OPacket13PlayerLookMove(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
    }

    // hMod: Store x/y/z
    int x, y, z, type;

    @Override
    public void a(OPacket14BlockDig paramOPacket14BlockDig) {
        if (paramOPacket14BlockDig.e == 4) {
            e.O();
            return;
        }
        // hMod: We allow admins and ops to dig!
        boolean bool = d.e.B = d.f.g(e.aw) || getPlayer().isAdmin();

        int m = 0;
        if (paramOPacket14BlockDig.e == 0)
            m = 1;
        if (paramOPacket14BlockDig.e == 1)
            m = 1;

        int n = paramOPacket14BlockDig.a;
        int i1 = paramOPacket14BlockDig.b;
        int i2 = paramOPacket14BlockDig.c;
        if (m != 0) {
            double d1 = e.p - (n + 0.5D);
            double d2 = e.q - (i1 + 0.5D);
            double d3 = e.r - (i2 + 0.5D);
            double d4 = d1 * d1 + d2 * d2 + d3 * d3;
            if (d4 > 36.0D)
                return;

            double d5 = e.q;
            e.q = e.al;

            e.q = d5;
        }

        int i3 = paramOPacket14BlockDig.d;
        int i4 = (int) OMathHelper.e(n - d.e.m);
        int i5 = (int) OMathHelper.e(i2 - d.e.o);
        if (i4 > i5)
            i5 = i4;
        // hMod: the player
        Player player = getPlayer();

        if (paramOPacket14BlockDig.e == 0) {
            // hMod: Start digging
            // No buildrights
            if (!getPlayer().canBuild())
                return;
            // hMod: Custom spawn prot size
            if ((i5 > etc.getInstance().getSpawnProtectionSize()) || (bool)) {
                // hMod: Dig hooks
                Block block = etc.getServer().getBlockAt(n, i1, i2);
                block.setStatus(0); // Started digging
                x = block.getX();
                y = block.getY();
                z = block.getZ();
                type = block.getType();
                if (!(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block))
                    e.c.a(n, i1, i2);
            }
        } else if (paramOPacket14BlockDig.e == 2) {
            // hMod: Stop digging
            Block block = etc.getServer().getBlockAt(n, i1, i2);
            block.setStatus(2); // Stopped digging
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

            e.c.a();
        } else if (paramOPacket14BlockDig.e == 1) {
            // hMod: Continue digging - don't forget spawn size
            if (!getPlayer().canBuild())
                return;
            if ((i5 > etc.getInstance().getSpawnProtectionSize()) || (bool)) {
                Block block = etc.getServer().getBlockAt(n, i1, i2);
                block.setStatus(1); // Digging
                if (!(Boolean) OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block))
                    e.c.a(n, i1, i2, i3);
            }
        } else if (paramOPacket14BlockDig.e == 3) {
            // hMod: Break block
            Block block = new Block(type, x, y, z);
            block.setStatus(3);
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_DESTROYED, player, block);

            double d6 = e.p - (n + 0.5D);
            double d7 = e.q - (i1 + 0.5D);
            double d8 = e.r - (i2 + 0.5D);
            double d9 = d6 * d6 + d7 * d7 + d8 * d8;
            if (d9 < 256.0D)
                e.a.b(new OPacket53BlockChange(n, i1, i2, d.e));
        }
        d.e.B = false;
    }

    // hMod: Store the blocks between blockPlaced packets
    Block lastRightClicked;

    @Override
    public void a(OPacket15Place paramOPacket15Place) {
        OItemStack localOItemStack = e.an.e();

        // hMod: We allow admins and ops to build!
        boolean bool = d.e.B = (d.f.g(getPlayer().getName()) || getPlayer().isAdmin());

        // hMod: Store block data to call hooks
        // hMod START
        Block blockClicked = null;
        Block blockPlaced = null;

        if (paramOPacket15Place.d == 255) {
            // ITEM_USE -- if we have a lastRightClicked then it could be a
            // usable location
            blockClicked = lastRightClicked;
            lastRightClicked = null;
        } else {
            // RIGHTCLICK or BLOCK_PLACE .. or nothing
            blockClicked = new Block(etc.getServer().getBlockIdAt(paramOPacket15Place.a, paramOPacket15Place.b, paramOPacket15Place.c), paramOPacket15Place.a, paramOPacket15Place.b, paramOPacket15Place.c);
            blockClicked.setFaceClicked(Block.Face.fromId(paramOPacket15Place.d));
            lastRightClicked = blockClicked;
        }

        // If we clicked on something then we also have a location to place the
        // block
        if (blockClicked != null && localOItemStack != null) {
            blockPlaced = new Block(localOItemStack.c, blockClicked.getX(), blockClicked.getY(), blockClicked.getZ());
            switch (paramOPacket15Place.d) {
                case 0:
                    blockPlaced.setY(blockPlaced.getY() - 1);
                    break;
                case 1:
                    blockPlaced.setY(blockPlaced.getY() + 1);
                    break;
                case 2:
                    blockPlaced.setZ(blockPlaced.getZ() - 1);
                    break;
                case 3:
                    blockPlaced.setZ(blockPlaced.getZ() + 1);
                    break;
                case 4:
                    blockPlaced.setX(blockPlaced.getX() - 1);
                    break;
                case 5:
                    blockPlaced.setX(blockPlaced.getX() + 1);
                    break;
            }
        }
        // hMod: END

        if (paramOPacket15Place.d == 255) {
            // hMod: call our version with extra blockClicked/blockPlaced
            if (blockPlaced != null)
                // Set the type of block to what it currently is
                blockPlaced.setType(etc.getServer().getBlockIdAt(blockPlaced.getX(), blockPlaced.getY(), blockPlaced.getZ()));
            if (localOItemStack == null)
                return;
            ((Digging) e.c).a(e, d.e, localOItemStack, blockPlaced, blockClicked);
            // e.c.a(e, d.e, localOItemStack);
        } else {
            int m = paramOPacket15Place.a;
            int n = paramOPacket15Place.b;
            int i1 = paramOPacket15Place.c;
            int i2 = paramOPacket15Place.d;
            // hMod : Fix stupid buggy spawn protection.
            int i3 = (int) OMathHelper.e((i2 == 4 ? (m - 1) : (i2 == 5 ? (m + 1) : m)) - d.e.m);
            // hMod : Fix stupid buggy spawn protection.
            int i4 = (int) OMathHelper.e((i2 == 2 ? (i1 - 1) : (i2 == 3 ? (i1 + 1) : i1)) - d.e.o);

            if (i3 > i4)
                i4 = i3;
            // hMod: call BLOCK_RIGHTCLICKED
            Item item = (localOItemStack != null) ? new Item(localOItemStack) : new Item(Item.Type.Air);
            Player player = getPlayer();
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_RIGHTCLICKED, player, blockClicked, item);

            // hMod: call original BLOCK_CREATED
            OEntity.manager.callHook(PluginLoader.Hook.BLOCK_CREATED, player, blockPlaced, blockClicked, item.getItemId());
            // hMod: If we were building inside spawn, bail! (unless ops/admin)
            if (((i4 > etc.getInstance().getSpawnProtectionSize() && !etc.getInstance().isOnItemBlacklist(item.getItemId())) || bool) && player.canBuild())
                e.c.a(e, d.e, localOItemStack, m, n, i1, i2);
            else {
                // hMod: No point sending the client to update the blocks, you
                // weren't allowed to place!
                d.e.B = false;
                return;
            }

            // hMod: these are the 'block changed' packets for the client.

            e.a.b(new OPacket53BlockChange(m, n, i1, d.e));

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

            e.a.b(new OPacket53BlockChange(m, n, i1, d.e));
        }
        if ((localOItemStack != null) && (localOItemStack.a == 0))
            e.an.a[e.an.c] = null;

        e.am = true;
        e.an.a[e.an.c] = OItemStack.b(e.an.a[e.an.c]);
        OSlot localOSlot = e.ap.a(e.an, e.an.c);
        e.ap.a();
        e.am = false;

        if (!OItemStack.a(e.an.e(), paramOPacket15Place.e))
            b(new OPacket103(e.ap.f, localOSlot.c, e.an.e()));

        d.e.B = false;
    }

    @Override
    public void a(String paramString, Object[] paramArrayOfObject) {
        // hMod: disconnect!
        OEntity.manager.callHook(PluginLoader.Hook.DISCONNECT, getPlayer());
        a.info(e.aw + " lost connection: " + paramString);
        d.f.a(new OPacket3Chat("\u00A7e" + e.aw + " left the game."));
        d.f.c(e);
        c = true;
    }

    @Override
    public void a(OPacket paramOPacket) {
        a.warning(getClass() + " wasn't prepared to deal with a " + paramOPacket.getClass());
        a("Protocol error, unexpected packet");
    }

    public void b(OPacket paramOPacket) {
        b.a(paramOPacket);
    }

    @Override
    public void a(OPacket16BlockItemSwitch paramOPacket16BlockItemSwitch) {
        e.an.c = paramOPacket16BlockItemSwitch.a;
    }

    @Override
    public void a(OPacket3Chat paramOPacket3Chat) {
        String str = paramOPacket3Chat.a;
        // hMod: redirect chathandling to player class
        getPlayer().chat(str);
    }

    @Override
    public void a(OPacket18ArmAnimation paramOPacket18ArmAnimation) {
        if (paramOPacket18ArmAnimation.b == 1) {
            // hMod: Swing the arm!
            OEntity.manager.callHook(PluginLoader.Hook.ARM_SWING, getPlayer());
            e.K();
        }
    }

    @Override
    public void a(OPacket19 paramOPacket19) {
        if (paramOPacket19.b == 1)
            e.b(true);
        else if (paramOPacket19.b == 2)
            e.b(false);
    }

    @Override
    public void a(OPacket255KickDisconnect paramOPacket255KickDisconnect) {
        b.a("disconnect.quitting", new Object[0]);
    }

    public int b() {
        return b.d();
    }

    public void b(String paramString) {
        b(new OPacket3Chat("\u00A77" + paramString));
    }

    public String c() {
        return e.aw;
    }

    @Override
    public void a(OPacket7 paramOPacket7) {
        OEntity localOEntity = d.e.a(paramOPacket7.b);

        if ((localOEntity != null) && (e.i(localOEntity)))
            if (paramOPacket7.c == 0)
                e.g(localOEntity);
            else if (paramOPacket7.c == 1)
                e.h(localOEntity);
    }

    @Override
    public void a(OPacket9 paramOPacket9) {
        if (e.aZ > 0)
            return;

        e = d.f.d(e);
    }

    @Override
    public void a(OPacket101 paramOPacket101) {
        e.N();
    }

    @Override
    public void a(OPacket102 paramOPacket102) {
        if ((e.ap.f == paramOPacket102.a) && (e.ap.c(e))) {
            OItemStack localOItemStack = e.ap.a(paramOPacket102.b, paramOPacket102.c, e);

            if (OItemStack.a(paramOPacket102.e, localOItemStack)) {
                e.a.b(new OPacket106(paramOPacket102.a, paramOPacket102.d, true));
                e.am = true;
                e.ap.a();
                e.M();
                e.am = false;
            } else {
                k.put(Integer.valueOf(e.ap.f), Short.valueOf(paramOPacket102.d));
                e.a.b(new OPacket106(paramOPacket102.a, paramOPacket102.d, false));
                e.ap.a(e, false);

                ArrayList<OItemStack> localArrayList = new ArrayList<OItemStack>();
                for (int m = 0; m < e.ap.e.size(); m++)
                    localArrayList.add(((OSlot) e.ap.e.get(m)).c());
                e.a(e.ap, localArrayList);
            }
        }
    }

    @Override
    public void a(OPacket106 paramOPacket106) {
        Short localShort = k.get(Integer.valueOf(e.ap.f));
        if ((localShort != null) && (paramOPacket106.b == localShort.shortValue()) && (e.ap.f == paramOPacket106.a) && (!e.ap.c(e)))
            e.ap.a(e, true);
    }

    @Override
    public void a(OPacket130 paramOPacket130) {
        if (d.e.f(paramOPacket130.a, paramOPacket130.b, paramOPacket130.c)) {
            OTileEntity localOTileEntity = d.e.m(paramOPacket130.a, paramOPacket130.b, paramOPacket130.c);
            int n, m;
            int i1;
            for (m = 0; m < 4; m++) {
                n = 1;
                // hMod: Remove the char limit, for plugins.
                // if (paramhh.d[m].length() > 15)
                // n = 0;
                // else {
                for (i1 = 0; i1 < paramOPacket130.d[m].length(); i1++)
                    if (OFontAllowedCharacters.a.indexOf(paramOPacket130.d[m].charAt(i1)) < 0)
                        n = 0;
                // }
                if (n == 0)
                    paramOPacket130.d[m] = "!?";
            }
            if ((localOTileEntity instanceof OTileEntitySign)) {
                m = paramOPacket130.a;
                n = paramOPacket130.b;
                i1 = paramOPacket130.c;
                OTileEntitySign localOTileEntitySign = (OTileEntitySign) localOTileEntity;
                // hMod: Copy the old line text
                String[] old = Arrays.copyOf(localOTileEntitySign.e, localOTileEntitySign.e.length);
                for (int i2 = 0; i2 < 4; i2++)
                    localOTileEntitySign.e[i2] = paramOPacket130.d[i2];
                // hMod: Check if we can change it
                Sign sign = new Sign(localOTileEntitySign);
                if ((Boolean) OEntity.manager.callHook(PluginLoader.Hook.SIGN_CHANGE, getPlayer(), sign))
                    localOTileEntitySign.e = Arrays.copyOf(old, old.length);

                localOTileEntitySign.d();
                d.e.g(m, n, i1);
            }
        }
    }
}
