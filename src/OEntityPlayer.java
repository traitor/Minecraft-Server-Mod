import java.util.List;
import java.util.Random;

public abstract class OEntityPlayer extends OEntityLiving {

    public OInventoryPlayer     i      = new OInventoryPlayer(this);
    public OCraftingInventoryCB j;
    public OCraftingInventoryCB k;
    public byte                 l      = 0;
    public int                  m      = 0;
    public float                n;
    public float                o;
    public boolean              p      = false;
    public int                  q      = 0;
    public String               r;
    public int                  s;
    public double               t;
    public double               u;
    public double               v;
    public double               w;
    public double               x;
    public double               y;
    private boolean             a;
    private OChunkCoordinates   b;
    private int                 c;
    public float                z;
    public float                A;
    private int                 d      = 0;
    public OEntityFish          B      = null;

    // hMod start
    HumanEntity                 entity = new HumanEntity(this);

    // hMod end

    public OEntityPlayer(OWorld paramOWorld) {
        super(paramOWorld);

        j = new OCraftingInventoryPlayerCB(i, !paramOWorld.t);

        k = j;

        bb = 1.62F;
        OChunkCoordinates localOChunkCoordinates = paramOWorld.l();
        c(localOChunkCoordinates.a + 0.5D, localOChunkCoordinates.b + 1, localOChunkCoordinates.c + 0.5D, 0.0F, 0.0F);

        W = 20;
        P = "humanoid";
        O = 180.0F;
        bs = 20;

        M = "/mob/char.png";
    }

    protected void a() {
        super.a();

        bz.a(16, (byte) 0);
    }

    public void f_() {
        if (E()) {
            c += 1;
            if (c > 100) {
                c = 100;
            }
            if (!l()) {
                a(true, true);
            } else if ((!aF.t) && (aF.c())) {
                a(false, true);
            }
        } else if (c > 0) {
            c += 1;
            if (c >= 110) {
                c = 0;
            }
        }

        super.f_();

        if ((!aF.t) && (k != null) && (!k.b(this))) {
            t();
            k = j;
        }

        t = w;
        u = x;
        v = y;

        double d1 = aJ - w;
        double d2 = aK - x;
        double d3 = aL - y;

        double d4 = 10.0D;
        if (d1 > d4) {
            t = (this.w = aJ);
        }
        if (d3 > d4) {
            v = (this.y = aL);
        }
        if (d2 > d4) {
            u = (this.x = aK);
        }
        if (d1 < -d4) {
            t = (this.w = aJ);
        }
        if (d3 < -d4) {
            v = (this.y = aL);
        }
        if (d2 < -d4) {
            u = (this.x = aK);
        }

        w += d1 * 0.25D;
        y += d3 * 0.25D;
        x += d2 * 0.25D;
    }

    protected boolean w() {
        return (W <= 0) || (E());
    }

    protected void t() {
        k = j;
    }

    public void x() {
        super.x();
        n = o;
        o = 0.0F;
    }

    protected void c_() {
        if (p) {
            q += 1;
            if (q == 8) {
                q = 0;
                p = false;
            }
        } else {
            q = 0;
        }

        V = (q / 8.0F);
    }

    public void q() {
        // hMod: adjust 'healing over time' independent of
        // monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
        if ((aF.j == 0) && (autoHeal == PluginLoader.HookResult.DEFAULT_ACTION) || autoHeal == PluginLoader.HookResult.ALLOW_ACTION)
            if ((W < 20) && (br % 20 * 12 == 0))
                b(1);

        i.e();
        n = o;

        super.q();

        float f1 = OMathHelper.a(aM * aM + aO * aO);
        float f2 = (float) Math.atan(-aN * 0.2000000029802322D) * 15.0F;
        if (f1 > 0.1F) {
            f1 = 0.1F;
        }
        if ((!aU) || (W <= 0)) {
            f1 = 0.0F;
        }
        if ((aU) || (W <= 0)) {
            f2 = 0.0F;
        }
        o += (f1 - o) * 0.4F;
        ae += (f2 - ae) * 0.8F;

        if (W > 0) {
            List localList = aF.b(this, aT.b(1.0D, 0.0D, 1.0D));
            if (localList != null) {
                for (int i1 = 0; i1 < localList.size(); i1++) {
                    OEntity localOEntity = (OEntity) localList.get(i1);
                    if (!localOEntity.ba) {
                        i(localOEntity);
                    }
                }
            }
        }
    }

    private void i(OEntity paramOEntity) {
        paramOEntity.b(this);
    }

    public void a(OEntity paramOEntity) {
        super.a(paramOEntity);
        a(0.2F, 0.2F);
        a(aJ, aK, aL);
        aN = 0.1000000014901161D;

        if (r.equals("Notch")) {
            a(new OItemStack(OItem.h, 1), true);
        }
        i.g();

        if (paramOEntity != null) {
            aM = (-OMathHelper.b((aa + aP) * 3.141593F / 180.0F) * 0.1F);
            aO = (-OMathHelper.a((aa + aP) * 3.141593F / 180.0F) * 0.1F);
        } else {
            aM = (this.aO = 0.0D);
        }
        bb = 0.1F;
    }

    public void c(OEntity paramOEntity, int paramInt) {
        m += paramInt;
    }

    public void y() {
        a(i.a(i.c, 1), false);
    }

    public void b(OItemStack paramOItemStack) {
        a(paramOItemStack, false);
    }

    public void a(OItemStack paramOItemStack, boolean paramBoolean) {
        if (paramOItemStack == null) {
            return;
        }

        OEntityItem localOEntityItem = new OEntityItem(aF, aJ, aK - 0.300000011920929D + p(), aL, paramOItemStack);
        localOEntityItem.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = bq.nextFloat() * 0.5F;
            float f3 = bq.nextFloat() * 3.141593F * 2.0F;
            localOEntityItem.aM = (-OMathHelper.a(f3) * f2);
            localOEntityItem.aO = (OMathHelper.b(f3) * f2);
            localOEntityItem.aN = 0.2000000029802322D;
        } else {
            f1 = 0.3F;
            localOEntityItem.aM = (-OMathHelper.a(aP / 180.0F * 3.141593F) * OMathHelper.b(aQ / 180.0F * 3.141593F) * f1);
            localOEntityItem.aO = (OMathHelper.b(aP / 180.0F * 3.141593F) * OMathHelper.b(aQ / 180.0F * 3.141593F) * f1);
            localOEntityItem.aN = (-OMathHelper.a(aQ / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = bq.nextFloat() * 3.141593F * 2.0F;
            f1 *= bq.nextFloat();
            localOEntityItem.aM += Math.cos(f2) * f1;
            localOEntityItem.aN += (bq.nextFloat() - bq.nextFloat()) * 0.1F;
            localOEntityItem.aO += Math.sin(f2) * f1;
        }

        if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), new Item(paramOItemStack)))
            a(localOEntityItem);
        // return the item to the inventory.
        else
            i.a(paramOItemStack);
    }

    protected void a(OEntityItem paramOEntityItem) {
        aF.a(paramOEntityItem);
    }

    public float a(OBlock paramOBlock) {
        float f = i.a(paramOBlock);
        if (a(OMaterial.f)) {
            f /= 5.0F;
        }
        if (!aU) {
            f /= 5.0F;
        }

        return f;
    }

    public boolean b(OBlock paramOBlock) {
        return i.b(paramOBlock);
    }

    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.l("Inventory");
        i.b(localONBTTagList);
        s = paramONBTTagCompound.e("Dimension");
        a = paramONBTTagCompound.m("Sleeping");
        c = paramONBTTagCompound.d("SleepTimer");

        if (a) {
            b = new OChunkCoordinates(OMathHelper.b(aJ), OMathHelper.b(aK), OMathHelper.b(aL));
            a(true, true);
        }
    }

    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        paramONBTTagCompound.a("Inventory", i.a(new ONBTTagList()));
        paramONBTTagCompound.a("Dimension", s);
        paramONBTTagCompound.a("Sleeping", a);
        paramONBTTagCompound.a("SleepTimer", (short) c);
    }

    public void a(OIInventory paramOIInventory) {
    }

    public void b(int paramInt1, int paramInt2, int paramInt3) {
    }

    public void b(OEntity paramOEntity, int paramInt) {
    }

    public float p() {
        return 0.12F;
    }

    protected void l_() {
        bb = 1.62F;
    }

    public boolean a(OEntity paramOEntity, int paramInt) {
        at = 0;
        if (W <= 0) {
            return false;
        }

        if (E()) {
            a(true, true);
        }

        if (((paramOEntity instanceof OEntityMobs)) || ((paramOEntity instanceof OEntityArrow))) {
            if (aF.j == 0) {
                paramInt = 0;
            }
            if (aF.j == 1) {
                paramInt = paramInt / 3 + 1;
            }
            if (aF.j == 3) {
                paramInt = paramInt * 3 / 2;
            }
        }

        if (paramInt == 0) {
            return false;
        }

        return super.a(paramOEntity, paramInt);
    }

    protected void c(int paramInt) {
        int i1 = 25 - i.f();
        int i2 = paramInt * i1 + d;
        i.c(paramInt);
        paramInt = i2 / 25;
        d = (i2 % 25);
        super.c(paramInt);
    }

    public void a(OTileEntityFurnace paramOTileEntityFurnace) {
    }

    public void a(OTileEntityDispenser paramOTileEntityDispenser) {
    }

    public void a(OTileEntitySign paramOTileEntitySign) {
    }

    public void c(OEntity paramOEntity) {
        if (paramOEntity.a(this)) {
            return;
        }
        OItemStack localOItemStack = z();
        if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
            localOItemStack.b((OEntityLiving) paramOEntity);
            if (localOItemStack.a <= 0) {
                localOItemStack.a(this);
                A();
            }
        }
    }

    public OItemStack z() {
        return i.b();
    }

    public void A() {
        i.a(i.c, null);
    }

    public double B() {
        return bb - 0.5F;
    }

    public void r() {
        q = -1;
        p = true;
    }

    public void d(OEntity paramOEntity) {
        int i1 = i.a(paramOEntity);
        if (i1 > 0) {
            paramOEntity.a(this, i1);
            OItemStack localOItemStack = z();
            if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
                localOItemStack.a((OEntityLiving) paramOEntity);
                if (localOItemStack.a <= 0) {
                    localOItemStack.a(this);
                    A();
                }
            }
        }
    }

    public void a(OItemStack paramOItemStack) {
    }

    public void C() {
        super.C();
        j.a(this);
        if (k != null) {
            k.a(this);
        }
    }

    public boolean D() {
        return (!a) && (super.D());
    }

    public boolean a(int paramInt1, int paramInt2, int paramInt3) {
        if ((E()) || (!J())) {
            return false;
        }

        if (aF.m.c) {
            return false;
        }
        if (aF.c()) {
            return false;
        }
        if ((Math.abs(aJ - paramInt1) > 3.0D) || (Math.abs(aK - paramInt2) > 2.0D) || (Math.abs(aL - paramInt3) > 3.0D)) {
            return false;
        }

        a(0.2F, 0.2F);
        bb = 0.2F;
        if (aF.f(paramInt1, paramInt2, paramInt3)) {
            int i1 = aF.b(paramInt1, paramInt2, paramInt3);
            int i2 = OBlockBed.c(i1);
            float f1 = 0.5F;
            float f2 = 0.5F;

            switch (i2) {
                case 0:
                    f2 = 0.9F;
                    break;
                case 2:
                    f2 = 0.1F;
                    break;
                case 1:
                    f1 = 0.1F;
                    break;
                case 3:
                    f1 = 0.9F;
            }

            e(i2);
            a(paramInt1 + f1, paramInt2 + 0.9375F, paramInt3 + f2);
        } else {
            a(paramInt1 + 0.5F, paramInt2 + 0.9375F, paramInt3 + 0.5F);
        }
        a = true;
        c = 0;
        b = new OChunkCoordinates(paramInt1, paramInt2, paramInt3);
        aM = (this.aO = this.aN = 0.0D);

        if (!aF.t) {
            aF.o();
        }

        return true;
    }

    private void e(int paramInt) {
        z = 0.0F;
        A = 0.0F;

        switch (paramInt) {
            case 0:
                A = -1.8F;
                break;
            case 2:
                A = 1.8F;
                break;
            case 1:
                z = 1.8F;
                break;
            case 3:
                z = -1.8F;
        }
    }

    public void a(boolean paramBoolean1, boolean paramBoolean2) {
        a(0.6F, 1.8F);
        l_();

        OChunkCoordinates localOChunkCoordinates1 = b;
        if ((localOChunkCoordinates1 != null) && (aF.a(localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c) == OBlock.S.bk)) {
            OBlockBed.a(aF, localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c, false);

            OChunkCoordinates localOChunkCoordinates2 = OBlockBed.g(aF, localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c, 0);
            a(localOChunkCoordinates2.a + 0.5F, localOChunkCoordinates2.b + bb + 0.1F, localOChunkCoordinates2.c + 0.5F);
        }

        a = false;
        if ((!aF.t) && (paramBoolean2)) {
            aF.o();
        }
        if (paramBoolean1) {
            c = 0;
        } else {
            c = 100;
        }
    }

    private boolean l() {
        return aF.a(b.a, b.b, b.c) == OBlock.S.bk;
    }

    public boolean E() {
        return a;
    }

    public boolean F() {
        return (a) && (c >= 100);
    }

    public void a(String paramString) {
    }
}
