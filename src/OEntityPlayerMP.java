import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

    public ONetServerHandler   a;
    public MinecraftServer     b;
    public OItemInWorldManager c;
    public double              d;
    public double              e;
    public List                f  = new LinkedList();
    public Set                 g  = new HashSet();
    private int                bE = -99999999;
    private int                bF = 60;
    private OItemStack[]       bG = { null, null, null, null, null };
    private int                bH = 0;
    public boolean             h;
    // hMod: Player storage
    private Player             player;

    public OEntityPlayerMP(MinecraftServer paramMinecraftServer, OWorld paramOWorld, String paramString, OItemInWorldManager paramOItemInWorldManager) {
        super(paramOWorld);

        OChunkCoordinates localOChunkCoordinates = paramOWorld.l();
        int i = localOChunkCoordinates.a;
        int j = localOChunkCoordinates.c;
        int k = localOChunkCoordinates.b;

        if (!paramOWorld.m.e) {
            i += bq.nextInt(etc.getInstance().getSpawnProtectionSize() * 2 + 1) - etc.getInstance().getSpawnProtectionSize();
            k = paramOWorld.e(i, j);
            j += bq.nextInt(etc.getInstance().getSpawnProtectionSize() * 2 + 1) - etc.getInstance().getSpawnProtectionSize();
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        b = paramMinecraftServer;
        bm = 0.0F;
        paramOItemInWorldManager.a = this;
        r = paramString;
        c = paramOItemInWorldManager;
        bb = 0.0F;

        // hMod: So we don't conflict with runecraft
        c = new Digging(paramOWorld, this);

        // hMod: Store player
        player = etc.getDataSource().getPlayer(paramString);
        player.setUser(this);
    }

    /**
     * Returns the player
     * 
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Reloads the player
     */
    public void reloadPlayer() {
        player = etc.getDataSource().getPlayer(r);
        player.setUser(this);
    }

    public void l() {
        // hMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);
    }

    public OItemStack[] k_() {
        return bG;
    }

    protected void l_() {
        bb = 0.0F;
    }

    public float p() {
        return 1.62F;
    }

    public void f_() {
        c.a();

        bF -= 1;
        k.a();

        for (int i = 0; i < 5; i++) {
            OItemStack localOItemStack = b_(i);
            if (localOItemStack != bG[i]) {
                b.k.a(this, new OPacket5PlayerInventory(aA, i, localOItemStack));
                bG[i] = localOItemStack;
            }
        }
    }

    public OItemStack b_(int paramInt) {
        if (paramInt == 0) {
            return i.b();
        }
        return i.b[(paramInt - 1)];
    }

    public void a(OEntity paramOEntity) {
        // hMod: drops inventory on death.
        if (etc.getInstance().isHealthEnabled())
            i.g();
    }

    public boolean a(OEntity paramOEntity, int paramInt) {
        if (bF > 0) {
            return false;
        }

        if (!b.n) {
            if ((paramOEntity instanceof OEntityPlayer)) {
                return false;
            }
            if ((paramOEntity instanceof OEntityArrow)) {
                OEntityArrow localOEntityArrow = (OEntityArrow) paramOEntity;
                if ((localOEntityArrow.b instanceof OEntityPlayer)) {
                    return false;
                }
            }
        }
        return super.a(paramOEntity, paramInt);
    }

    public void b(int paramInt) {
        super.b(paramInt);
    }

    public void a(boolean paramBoolean) {
        super.f_();

        if ((paramBoolean) && (!f.isEmpty())) {
            OChunkCoordIntPair localOChunkCoordIntPair = (OChunkCoordIntPair) f.get(0);

            if (localOChunkCoordIntPair != null) {
                int i = 0;

                if (a.b() < 2) {
                    i = 1;
                }

                if (i != 0) {
                    f.remove(localOChunkCoordIntPair);
                    a.b(new OPacket51MapChunk(localOChunkCoordIntPair.a * 16, 0, localOChunkCoordIntPair.b * 16, 16, 128, 16, b.e));
                    List localList = b.e.d(localOChunkCoordIntPair.a * 16, 0, localOChunkCoordIntPair.b * 16, localOChunkCoordIntPair.a * 16 + 16, 128, localOChunkCoordIntPair.b * 16 + 16);
                    for (int j = 0; j < localList.size(); j++) {
                        a((OTileEntity) localList.get(j));
                    }
                }
            }
        }

        // hMod: Update the health
        if (X != bF) {
            // updates your health when it is changed.
            if (!etc.getInstance().isHealthEnabled()) {
                W = 20;
                af = false;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), bE, W))
                W = bE;
            else
                a.b(new OPacket8(W));
            bE = W;
        }
    }

    private void a(OTileEntity paramOTileEntity) {
        if (paramOTileEntity != null) {
            // hMod: Let plugins know we're showing a sign
            if (paramOTileEntity instanceof OTileEntitySign) {
                Sign sign = new Sign((OTileEntitySign) paramOTileEntity);
                manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
            }

            OPacket localOPacket = paramOTileEntity.e();
            if (localOPacket != null) {
                a.b(localOPacket);
            }
        }
    }

    public void q() {
        super.q();
    }

    public void b(OEntity paramOEntity, int paramInt) {
        if (!paramOEntity.ba) {
            if ((paramOEntity instanceof OEntityItem)) {
                b.k.a(paramOEntity, new OPacket22Collect(paramOEntity.aA, aA));
            }
            if ((paramOEntity instanceof OEntityArrow)) {
                b.k.a(paramOEntity, new OPacket22Collect(paramOEntity.aA, aA));
            }
        }
        super.b(paramOEntity, paramInt);
        k.a();
    }

    public void r() {
        if (!p) {
            q = -1;
            p = true;
            b.k.a(this, new OPacket18ArmAnimation(this, 1));
        }
    }

    public void s() {
    }

    public boolean a(int paramInt1, int paramInt2, int paramInt3) {
        if (super.a(paramInt1, paramInt2, paramInt3)) {
            b.k.a(this, new OPacket17Sleep(this, 0, paramInt1, paramInt2, paramInt3));

            return true;
        }
        return false;
    }

    public void a(boolean paramBoolean1, boolean paramBoolean2) {
        if (E()) {
            b.k.b(this, new OPacket18ArmAnimation(this, 3));
        }
        super.a(paramBoolean1, paramBoolean2);
        a.a(aJ, aK, aL, aP, aQ);
    }

    public void b(OEntity paramOEntity) {
        super.b(paramOEntity);
        a.b(new OPacket39(this, aE));
        a.a(aJ, aK, aL, aP, aQ);
    }

    protected void a(double paramDouble, boolean paramBoolean) {
    }

    public void b(double paramDouble, boolean paramBoolean) {
        super.a(paramDouble, paramBoolean);
    }

    private void V() {
        bH = (bH % 100 + 1);
    }

    public void b(int paramInt1, int paramInt2, int paramInt3) {
        V();
        a.b(new OPacket100(bH, 1, "Crafting", 9));
        k = new OCraftingInventoryWorkbenchCB(i, aF, paramInt1, paramInt2, paramInt3);
        k.f = bH;
        // hMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);
    }

    public void a(OIInventory paramOIInventory) {
        // hMod: Check if we can open this
        Inventory inv = null;
        String name = paramOIInventory.c();
        if (paramOIInventory instanceof OTileEntityChest) {
            inv = new Chest((OTileEntityChest) paramOIInventory);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
                return;
        } else if (paramOIInventory instanceof OInventoryLargeChest) {
            inv = new DoubleChest((OInventoryLargeChest) paramOIInventory);
            if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
                return;
        }

        if (inv != null)
            name = inv.getName();
        V();
        a.b(new OPacket100(bH, 0, name, paramOIInventory.m_()));
        k = new OCraftingInventoryChestCB(i, paramOIInventory);
        k.f = bH;
        // hMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);
    }

    public void a(OTileEntityFurnace paramOTileEntityFurnace) {
        // hMod: Check if we can open this
        Inventory inv = new Furnace(paramOTileEntityFurnace);
        String name = paramOTileEntityFurnace.c();
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
            return;

        if (inv != null)
            name = inv.getName();

        V();
        a.b(new OPacket100(bH, 2, name, paramOTileEntityFurnace.m_()));
        k = new OCraftingInventoryFurnaceCB(i, paramOTileEntityFurnace);
        k.f = bH;
        // hMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);
    }

    public void a(OTileEntityDispenser paramOTileEntityDispenser) {
        Dispenser dis = new Dispenser(paramOTileEntityDispenser);
        String name = paramOTileEntityDispenser.c();
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), dis))
            return;

        if (dis != null)
            name = dis.getName();

        V();
        a.b(new OPacket100(bH, 3, name, paramOTileEntityDispenser.m_()));
        k = new OCraftingInventoryDispenserCB(i, paramOTileEntityDispenser);
        k.f = bH;
        // hMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        k.a((OICrafting) this);
    }

    public void a(OCraftingInventoryCB paramOCraftingInventoryCB, int paramInt, OItemStack paramOItemStack) {
        if ((paramOCraftingInventoryCB.a(paramInt) instanceof OSlotCrafting)) {
            return;
        }

        if (h) {
            return;
        }

        a.b(new OPacket103(paramOCraftingInventoryCB.f, paramInt, paramOItemStack));
    }

    public void a(OCraftingInventoryCB paramOCraftingInventoryCB, List paramList) {
        a.b(new OPacket104(paramOCraftingInventoryCB.f, paramList));
        a.b(new OPacket103(-1, -1, i.i()));
    }

    public void a(OCraftingInventoryCB paramOCraftingInventoryCB, int paramInt1, int paramInt2) {
        a.b(new OPacket105(paramOCraftingInventoryCB.f, paramInt1, paramInt2));
    }

    public void a(OItemStack paramOItemStack) {
    }

    public void t() {
        a.b(new OPacket101(k.f));
        v();
    }

    public void u() {
        if (h) {
            return;
        }
        a.b(new OPacket103(-1, -1, i.i()));
    }

    public void v() {
        k.a((OEntityPlayer) this);
        k = j;
    }

    public void a(float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, float paramFloat3, float paramFloat4) {
        au = paramFloat1;
        av = paramFloat2;
        ax = paramBoolean1;
        b(paramBoolean2);
        aQ = paramFloat3;
        aP = paramFloat4;
    }
}
