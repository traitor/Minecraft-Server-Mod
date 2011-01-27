import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import net.minecraft.server.MinecraftServer;

public class OEntityPlayerMP extends OEntityPlayer implements OICrafting {

    public ONetServerHandler   a;
    public MinecraftServer     b;
    public OItemInWorldManager c;
    public double              d;
    public double              e;
    public List                f  = new LinkedList();
    public Set                 ak = new HashSet();
    public double              al;
    private int                bD = -99999999;
    private int                bE = 60;
    private OItemStack[]       bF = { null, null, null, null, null };
    private int                bG = 0;
    public boolean             am;
    // hMod: Player storage
    private Player             player;

    public OEntityPlayerMP(MinecraftServer paramMinecraftServer, OWorld paramOWorld, String paramString, OItemInWorldManager paramOItemInWorldManager) {
        super(paramOWorld);

        int i = paramOWorld.m;
        int j = paramOWorld.o;
        int k = paramOWorld.n;

        if (!paramOWorld.q.e) {
            i += W.nextInt(20) - 10;
            k = paramOWorld.e(i, j);
            j += W.nextInt(20) - 10;
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        b = paramMinecraftServer;
        S = 0.0F;
        paramOItemInWorldManager.a = this;
        aw = paramString;
        c = paramOItemInWorldManager;
        H = 0.0F;

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
        player = etc.getDataSource().getPlayer(aw);
        player.setUser(this);
    }

    public void l() {
        // hMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        ap.a((OICrafting) this);
    }

    @Override
    public OItemStack[] I() {
        return bF;
    }

    @Override
    public void b_() {
        bE -= 1;
        ap.a();

        for (int i = 0; i < 5; i++) {
            OItemStack localOItemStack = a(i);
            if (localOItemStack != bF[i]) {
                b.k.a(this, new OPacket5PlayerInventory(g, i, localOItemStack));
                bF[i] = localOItemStack;
            }
        }
    }

    public OItemStack a(int paramInt) {
        if (paramInt == 0)
            return an.e();
        return an.b[(paramInt - 1)];
    }

    @Override
    public void f(OEntity paramOEntity) {
        // hMod: drops inventory on death.
        if (etc.getInstance().isHealthEnabled())
            an.h();
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        if (bE > 0)
            return false;

        if (!b.n) {
            if ((paramOEntity instanceof OEntityPlayer))
                return false;
            if ((paramOEntity instanceof OEntityArrow)) {
                OEntityArrow localOEntityArrow = (OEntityArrow) paramOEntity;
                if ((localOEntityArrow.b instanceof OEntityPlayer))
                    return false;
            }
        }
        return super.a(paramOEntity, paramInt);
    }

    @Override
    public void d(int paramInt) {
        super.d(paramInt);
    }

    public void n() {
        super.b_();

        OChunkCoordIntPair localObject1 = null;

        double d1 = 0.0D;
        OChunkCoordIntPair localObject2;
        for (int i = 0; i < f.size(); i++) {
            localObject2 = (OChunkCoordIntPair) f.get(i);
            double d2 = (localObject2).a(this);
            if ((i == 0) || (d2 < d1)) {
                localObject1 = localObject2;
                d1 = (localObject2).a(this);
            }
        }

        if (localObject1 != null) {
            int i = 0;

            if (d1 < 1024.0D)
                i = 1;
            if (a.b() < 2)
                i = 1;

            if (i != 0) {
                f.remove(localObject1);
                a.b(new OPacket51MapChunk(localObject1.a * 16, 0, localObject1.b * 16, 16, 128, 16, b.e));
                List list = b.e.d(localObject1.a * 16, 0, localObject1.b * 16, localObject1.a * 16 + 16, 128, localObject1.b * 16 + 16);
                for (int j = 0; j < (list).size(); j++)
                    a((OTileEntity) (list).get(j));
            }
        }

        // hMod: Update the health
        if (ba != bE) {
            // updates your health when it is changed.
            if (!etc.getInstance().isHealthEnabled()) {
                aZ = 20;
                bi = false;
            } else if ((Boolean) manager.callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), bD, aZ))
                aZ = bD;
            else
                a.b(new OPacket8(aZ));
            bD = aZ;
        }
    }

    private void a(OTileEntity paramOTileEntity) {
        if (paramOTileEntity != null) {
            // hMod: Let plugins know we're showing a sign
            if (paramOTileEntity instanceof OTileEntitySign) {
                Sign sign = new Sign((OTileEntitySign) paramOTileEntity);
                manager.callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
            }

            OPacket localOPacket = paramOTileEntity.g();
            if (localOPacket != null)
                a.b(localOPacket);
        }
    }

    @Override
    public void o() {
        s = (t = u = 0.0D);
        bA = false;
        super.o();
    }

    @Override
    public void c(OEntity paramOEntity, int paramInt) {
        if (!paramOEntity.G) {
            if ((paramOEntity instanceof OEntityItem))
                b.k.a(paramOEntity, new OPacket22Collect(paramOEntity.g, g));
            if ((paramOEntity instanceof OEntityArrow))
                b.k.a(paramOEntity, new OPacket22Collect(paramOEntity.g, g));
        }
        super.c(paramOEntity, paramInt);
        ap.a();
    }

    @Override
    public void K() {
        if (!au) {
            av = -1;
            au = true;
            b.k.a(this, new OPacket18ArmAnimation(this, 1));
        }
    }

    @Override
    public float w() {
        return 1.62F;
    }

    @Override
    public void e(OEntity paramOEntity) {
        super.e(paramOEntity);
        a.b(new OPacket39(this, k));
        a.a(p, q, r, v, w);
    }

    @Override
    protected void a(double paramDouble, boolean paramBoolean) {
    }

    public void b(double paramDouble, boolean paramBoolean) {
        super.a(paramDouble, paramBoolean);
    }

    private void U() {
        bG = (bG % 100 + 1);
    }

    @Override
    public void a(int paramInt1, int paramInt2, int paramInt3) {
        OCraftingInventoryWorkbenchCB temp = new OCraftingInventoryWorkbenchCB(an, l, paramInt1, paramInt2, paramInt3);
        Workbench bench = new Workbench(temp);
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), bench))
            return;

        U();
        a.b(new OPacket100(bG, 1, "Crafting", 9));
        ap = temp;
        ap.f = bG;
        ap.a((OICrafting) this);
    }

    @Override
    public void a(OIInventory paramOIInventory) {
        // hMod: Check if we can open this
        Inventory inv = null;
        String name = paramOIInventory.b();
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

        U();
        a.b(new OPacket100(bG, 0, name, paramOIInventory.h_()));
        ap = new OCraftingInventoryChestCB(an, paramOIInventory);
        ap.f = bG;
        // hMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        ap.a((OICrafting) this);
    }

    @Override
    public void a(OTileEntityFurnace paramOTileEntityFurnace) {
        // hMod: Check if we can open this
        Inventory inv = new Furnace(paramOTileEntityFurnace);
        String name = paramOTileEntityFurnace.b();
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv))
            return;

        if (inv != null)
            name = inv.getName();

        U();
        a.b(new OPacket100(bG, 2, name, paramOTileEntityFurnace.h_()));
        ap = new OCraftingInventoryFurnaceCB(an, paramOTileEntityFurnace);
        ap.f = bG;
        // hMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        ap.a((OICrafting) this);
    }

    @Override
    public void a(OTileEntityDispenser paramOTileEntityDispenser) {
        Dispenser dis = new Dispenser(paramOTileEntityDispenser);
        String name = paramOTileEntityDispenser.b();
        if ((Boolean) manager.callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), dis))
            return;

        if (dis != null)
            name = dis.getName();
        U();
        a.b(new OPacket100(bG, 3, name, paramOTileEntityDispenser.h_()));
        ap = new OCraftingInventoryDispenserCB(an, paramOTileEntityDispenser);
        ap.f = bG;
        // hMod: Make sure this gets cast correctly, or mutant puppies will
        // spawn and eat your items.
        ap.a((OICrafting) this);
    }

    public void a(OCraftingInventoryCB paramOCraftingInventoryCB, int paramInt, OItemStack paramOItemStack) {
        if ((paramOCraftingInventoryCB.a(paramInt) instanceof OSlotCrafting))
            return;

        if (am)
            return;

        a.b(new OPacket103(paramOCraftingInventoryCB.f, paramInt, paramOItemStack));
    }

    public void a(OCraftingInventoryCB paramOCraftingInventoryCB, List paramList) {
        a.b(new OPacket104(paramOCraftingInventoryCB.f, paramList));
        a.b(new OPacket103(-1, -1, an.i()));
    }

    public void a(OCraftingInventoryCB paramOCraftingInventoryCB, int paramInt1, int paramInt2) {
        a.b(new OPacket105(paramOCraftingInventoryCB.f, paramInt1, paramInt2));
    }

    @Override
    public void a(OItemStack paramOItemStack) {
    }

    @Override
    public void L() {
        a.b(new OPacket101(ap.f));
        N();
    }

    public void M() {
        if (am)
            return;
        a.b(new OPacket103(-1, -1, an.i()));
    }

    public void N() {
        ap.a((OEntityPlayer) this);
        ap = ao;
    }
}
