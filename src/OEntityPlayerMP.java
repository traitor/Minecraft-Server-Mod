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

    public Set                 g  = new HashSet();

    private int                bE = -99999999;
    private int                bF = 60;

    private OItemStack[]       bG = { null, null, null, null, null };

    private int                bH = 0;
    public boolean             h;

    public OEntityPlayerMP(MinecraftServer paramMinecraftServer, OWorld paramOWorld, String paramString, OItemInWorldManager paramOItemInWorldManager) {
        super(paramOWorld);

        OChunkCoordinates localOChunkCoordinates = paramOWorld.m();
        int i = localOChunkCoordinates.a;
        int j = localOChunkCoordinates.c;
        int k = localOChunkCoordinates.b;

        if (!paramOWorld.m.e) {
            i += bq.nextInt(20) - 10;
            k = paramOWorld.e(i, j);
            j += bq.nextInt(20) - 10;
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        b = paramMinecraftServer;
        bm = 0.0F;
        paramOItemInWorldManager.a = this;
        r = paramString;
        c = paramOItemInWorldManager;
        bc = 0.0F;
    }

    public void m() {
        k.a(this);
    }

    @Override
    public OItemStack[] k_() {
        return bG;
    }

    @Override
    protected void l_() {
        bc = 0.0F;
    }

    @Override
    public float q() {
        return 1.62F;
    }

    @Override
    public void f_() {
        c.a();

        bF -= 1;
        k.a();

        for (int i = 0; i < 5; i++) {
            OItemStack localOItemStack = b_(i);
            if (localOItemStack != bG[i]) {
                b.k.a(this, new OPacket5PlayerInventory(aB, i, localOItemStack));
                bG[i] = localOItemStack;
            }
        }
    }

    public OItemStack b_(int paramInt) {
        if (paramInt == 0)
            return i.b();
        return i.b[(paramInt - 1)];
    }

    @Override
    public void a(OEntity paramOEntity) {
        i.h();
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        if (bF > 0)
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
    public void b(int paramInt) {
        super.b(paramInt);
    }

    public void a(boolean paramBoolean) {
        super.f_();

        if ((paramBoolean) && (!f.isEmpty())) {
            OChunkCoordIntPair localOChunkCoordIntPair = (OChunkCoordIntPair) f.get(0);

            if (localOChunkCoordIntPair != null) {
                int i = 0;

                if (a.b() < 2)
                    i = 1;

                if (i != 0) {
                    f.remove(localOChunkCoordIntPair);
                    a.b(new OPacket51MapChunk(localOChunkCoordIntPair.a * 16, 0, localOChunkCoordIntPair.b * 16, 16, 128, 16, b.e));
                    List localList = b.e.d(localOChunkCoordIntPair.a * 16, 0, localOChunkCoordIntPair.b * 16, localOChunkCoordIntPair.a * 16 + 16, 128, localOChunkCoordIntPair.b * 16 + 16);
                    for (int j = 0; j < localList.size(); j++)
                        a((OTileEntity) localList.get(j));
                }
            }
        }

        if (W != bE) {
            a.b(new OPacket8(W));
            bE = W;
        }
    }

    private void a(OTileEntity paramOTileEntity) {
        if (paramOTileEntity != null) {
            OPacket localOPacket = paramOTileEntity.e();
            if (localOPacket != null)
                a.b(localOPacket);
        }
    }

    @Override
    public void r() {
        super.r();
    }

    @Override
    public void b(OEntity paramOEntity, int paramInt) {
        if (!paramOEntity.bb) {
            if ((paramOEntity instanceof OEntityItem))
                b.k.a(paramOEntity, new OPacket22Collect(paramOEntity.aB, aB));
            if ((paramOEntity instanceof OEntityArrow))
                b.k.a(paramOEntity, new OPacket22Collect(paramOEntity.aB, aB));
        }
        super.b(paramOEntity, paramInt);
        k.a();
    }

    @Override
    public void m_() {
        if (!p) {
            q = -1;
            p = true;
            b.k.a(this, new OPacket18ArmAnimation(this, 1));
        }
    }

    public void t() {
    }

    @Override
    public NEW9 a(int paramInt1, int paramInt2, int paramInt3) {
        NEW9 localNEW9 = super.a(paramInt1, paramInt2, paramInt3);
        if (localNEW9 == NEW9.a)
            b.k.a(this, new OPacket17Sleep(this, 0, paramInt1, paramInt2, paramInt3));
        return localNEW9;
    }

    @Override
    public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
        if (F())
            b.k.b(this, new OPacket18ArmAnimation(this, 3));
        super.a(paramBoolean1, paramBoolean2, paramBoolean3);
        a.a(aK, aL, aM, aQ, aR);
    }

    @Override
    public void b(OEntity paramOEntity) {
        super.b(paramOEntity);
        a.b(new OPacket39(this, aF));
        a.a(aK, aL, aM, aQ, aR);
    }

    @Override
    protected void a(double paramDouble, boolean paramBoolean) {
    }

    public void b(double paramDouble, boolean paramBoolean) {
        super.a(paramDouble, paramBoolean);
    }

    private void aa() {
        bH = (bH % 100 + 1);
    }

    @Override
    public void b(int paramInt1, int paramInt2, int paramInt3) {
        aa();
        a.b(new OPacket100(bH, 1, "Crafting", 9));
        k = new OCraftingInventoryWorkbenchCB(i, aG, paramInt1, paramInt2, paramInt3);
        k.f = bH;
        k.a(this);
    }

    @Override
    public void a(OIInventory paramOIInventory) {
        aa();
        a.b(new OPacket100(bH, 0, paramOIInventory.c(), paramOIInventory.q_()));
        k = new OCraftingInventoryChestCB(i, paramOIInventory);
        k.f = bH;
        k.a(this);
    }

    @Override
    public void a(OTileEntityFurnace paramOTileEntityFurnace) {
        aa();
        a.b(new OPacket100(bH, 2, paramOTileEntityFurnace.c(), paramOTileEntityFurnace.q_()));
        k = new OCraftingInventoryFurnaceCB(i, paramOTileEntityFurnace);
        k.f = bH;
        k.a(this);
    }

    @Override
    public void a(OTileEntityDispenser paramOTileEntityDispenser) {
        aa();
        a.b(new OPacket100(bH, 3, paramOTileEntityDispenser.c(), paramOTileEntityDispenser.q_()));
        k = new OCraftingInventoryDispenserCB(i, paramOTileEntityDispenser);
        k.f = bH;
        k.a(this);
    }

    public void a(OCraftingInventoryCB paramOCraftingInventoryCB, int paramInt, OItemStack paramOItemStack) {
        if ((paramOCraftingInventoryCB.a(paramInt) instanceof OSlotCrafting))
            return;

        if (h)
            return;

        a.b(new OPacket103(paramOCraftingInventoryCB.f, paramInt, paramOItemStack));
    }

    public void a(OCraftingInventoryCB paramOCraftingInventoryCB, List paramList) {
        a.b(new OPacket104(paramOCraftingInventoryCB.f, paramList));
        a.b(new OPacket103(-1, -1, i.j()));
    }

    public void a(OCraftingInventoryCB paramOCraftingInventoryCB, int paramInt1, int paramInt2) {
        a.b(new OPacket105(paramOCraftingInventoryCB.f, paramInt1, paramInt2));
    }

    @Override
    public void a(OItemStack paramOItemStack) {
    }

    @Override
    public void u() {
        a.b(new OPacket101(k.f));
        w();
    }

    public void v() {
        if (h)
            return;
        a.b(new OPacket103(-1, -1, i.j()));
    }

    public void w() {
        k.a(this);
        k = j;
    }

    public void a(float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, float paramFloat3, float paramFloat4) {
        au = paramFloat1;
        av = paramFloat2;
        ax = paramBoolean1;
        e(paramBoolean2);
        aR = paramFloat3;
        aQ = paramFloat4;
    }
}