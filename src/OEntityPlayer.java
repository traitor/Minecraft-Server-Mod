import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving {

    public OInventoryPlayer     an     = new OInventoryPlayer(this);
    public OCraftingInventoryCB ao;
    public OCraftingInventoryCB ap;
    public byte                 aq     = 0;
    public int                  ar     = 0;
    public float                as;
    public float                at;
    public boolean              au     = false;
    public int                  av     = 0;
    public String               aw;
    public int                  ax;
    public double               ay;
    public double               az;
    public double               aA;
    public double               aB;
    public double               aC;
    public double               aD;
    private int                 a      = 0;
    public OEntityFish          aE     = null;

    // hMod start
    HumanEntity                 entity = new HumanEntity(this);

    // hMod end

    public OEntityPlayer(OWorld paramOWorld) {
        super(paramOWorld);

        ao = new OCraftingInventoryPlayerCB(an, !paramOWorld.z);

        ap = ao;

        H = 1.62F;
        c(paramOWorld.m + 0.5D, paramOWorld.n + 1, paramOWorld.o + 0.5D, 0.0F, 0.0F);

        aZ = 20;
        aS = "humanoid";
        aR = 180.0F;
        Y = 20;

        aP = "/mob/char.png";
    }

    @Override
    public void b_() {
        super.b_();

        if ((!l.z) && (ap != null) && (!ap.b(this))) {
            L();
            ap = ao;
        }

        ay = aB;
        az = aC;
        aA = aD;

        double d1 = p - aB;
        double d2 = q - aC;
        double d3 = r - aD;

        double d4 = 10.0D;
        if (d1 > d4)
            ay = (aB = p);
        if (d3 > d4)
            aA = (aD = r);
        if (d2 > d4)
            az = (aC = q);
        if (d1 < -d4)
            ay = (aB = p);
        if (d3 < -d4)
            aA = (aD = r);
        if (d2 < -d4)
            az = (aC = q);

        aB += d1 * 0.25D;
        aD += d3 * 0.25D;
        aC += d2 * 0.25D;
    }

    protected void L() {
        ap = ao;
    }

    @Override
    public void D() {
        super.D();
        as = at;
        at = 0.0F;
    }

    @Override
    protected void d() {
        if (au) {
            av += 1;
            if (av == 8) {
                av = 0;
                au = false;
            }
        } else
            av = 0;

        aY = (av / 8.0F);
    }

    @Override
    public void o() {
        // hMod: adjust 'healing over time' independent of
        // monster-spawn=true/false (nice notchup!)
        PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
        if ((l.k == 0) && (autoHeal == PluginLoader.HookResult.DEFAULT_ACTION) || autoHeal == PluginLoader.HookResult.ALLOW_ACTION)
            if ((aZ < 20) && (X % 20 * 12 == 0))
                d(1);

        an.f();
        as = at;

        super.o();

        float f1 = OMathHelper.a(s * s + u * u);
        float f2 = (float) Math.atan(-t * 0.2000000029802322D) * 15.0F;
        if (f1 > 0.1F)
            f1 = 0.1F;
        if ((!A) || (aZ <= 0))
            f1 = 0.0F;
        if ((A) || (aZ <= 0))
            f2 = 0.0F;
        at += (f1 - at) * 0.4F;
        bh += (f2 - bh) * 0.8F;

        if (aZ > 0) {
            List localList = l.b(this, z.b(1.0D, 0.0D, 1.0D));
            if (localList != null)
                for (int i = 0; i < localList.size(); i++) {
                    OEntity localOEntity = (OEntity) localList.get(i);
                    if (!localOEntity.G)
                        j(localOEntity);
                }
        }
    }

    private void j(OEntity paramOEntity) {
        paramOEntity.b(this);
    }

    @Override
    public void f(OEntity paramOEntity) {
        super.f(paramOEntity);
        a(0.2F, 0.2F);
        a(p, q, r);
        t = 0.1000000014901161D;

        // gives player with name "Notch" an free apple; god may know why.
        if (aw.equals("Notch"))
            a(new OItemStack(OItem.h, 1), true);
        an.h();

        if (paramOEntity != null) {
            s = (-OMathHelper.b((bd + v) * 3.141593F / 180.0F) * 0.1F);
            u = (-OMathHelper.a((bd + v) * 3.141593F / 180.0F) * 0.1F);
        } else
            s = (u = 0.0D);
        H = 0.1F;
    }

    @Override
    public void b(OEntity paramOEntity, int paramInt) {
        ar += paramInt;
    }

    public void O() {
        a(an.b(an.c, 1), false);
    }

    public void b(OItemStack paramOItemStack) {
        a(paramOItemStack, false);
    }

    public void a(OItemStack paramOItemStack, boolean paramBoolean) {
        if (paramOItemStack == null)
            return;

        OEntityItem localOEntityItem = new OEntityItem(l, p, q - 0.300000011920929D + w(), r, paramOItemStack);
        localOEntityItem.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = W.nextFloat() * 0.5F;
            float f3 = W.nextFloat() * 3.141593F * 2.0F;
            localOEntityItem.s = (-OMathHelper.a(f3) * f2);
            localOEntityItem.u = (OMathHelper.b(f3) * f2);
            localOEntityItem.t = 0.2000000029802322D;
        } else {
            f1 = 0.3F;
            localOEntityItem.s = (-OMathHelper.a(v / 180.0F * 3.141593F) * OMathHelper.b(w / 180.0F * 3.141593F) * f1);
            localOEntityItem.u = (OMathHelper.b(v / 180.0F * 3.141593F) * OMathHelper.b(w / 180.0F * 3.141593F) * f1);
            localOEntityItem.t = (-OMathHelper.a(w / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = W.nextFloat() * 3.141593F * 2.0F;
            f1 *= W.nextFloat();
            localOEntityItem.s += Math.cos(f2) * f1;
            localOEntityItem.t += (W.nextFloat() - W.nextFloat()) * 0.1F;
            localOEntityItem.u += Math.sin(f2) * f1;
        }

        if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), new Item(paramOItemStack)))
            a(localOEntityItem);
        // return the item to the inventory.
        else
            an.a(paramOItemStack);
    }

    protected void a(OEntityItem paramOEntityItem) {
        l.a(paramOEntityItem);
    }

    public float a(OBlock paramOBlock) {
        float f = an.a(paramOBlock);
        if (a(OMaterial.f))
            f /= 5.0F;
        if (!A)
            f /= 5.0F;

        return f;
    }

    public boolean b(OBlock paramOBlock) {
        return an.b(paramOBlock);
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.k("Inventory");
        an.b(localONBTTagList);
        ax = paramONBTTagCompound.d("Dimension");
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        paramONBTTagCompound.a("Inventory", an.a(new ONBTTagList()));
        paramONBTTagCompound.a("Dimension", ax);
    }

    public void a(OIInventory paramOIInventory) {
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
    }

    public void c(OEntity paramOEntity, int paramInt) {
    }

    @Override
    public float w() {
        return 0.12F;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        bw = 0;
        if (aZ <= 0)
            return false;

        if (((paramOEntity instanceof OEntityMobs)) || ((paramOEntity instanceof OEntityArrow))) {
            if (l.k == 0)
                paramInt = 0;
            if (l.k == 1)
                paramInt = paramInt / 3 + 1;
            if (l.k == 3)
                paramInt = paramInt * 3 / 2;
        }

        if (paramInt == 0)
            return false;

        return super.a(paramOEntity, paramInt);
    }

    @Override
    protected void e(int paramInt) {
        int i = 25 - an.g();
        int j = paramInt * i + a;
        an.c(paramInt);
        paramInt = j / 25;
        a = (j % 25);
        super.e(paramInt);
    }

    public void a(OTileEntityFurnace paramOTileEntityFurnace) {
    }

    public void a(OTileEntityDispenser paramOTileEntityDispenser) {
    }

    public void a(OTileEntitySign paramOTileEntitySign) {
    }

    public void g(OEntity paramOEntity) {
        if (paramOEntity.a(this))
            return;
        OItemStack localOItemStack = P();
        if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
            localOItemStack.b((OEntityLiving) paramOEntity);
            if (localOItemStack.a <= 0) {
                localOItemStack.a(this);
                Q();
            }
        }
    }

    public OItemStack P() {
        return an.e();
    }

    public void Q() {
        an.a(an.c, null);
    }

    @Override
    public double F() {
        return H - 0.5F;
    }

    public void K() {
        av = -1;
        au = true;
    }

    public void h(OEntity paramOEntity) {
        int i = an.a(paramOEntity);
        if (i > 0) {
            paramOEntity.a(this, i);
            OItemStack localOItemStack = P();
            if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
                localOItemStack.a((OEntityLiving) paramOEntity);
                if (localOItemStack.a <= 0) {
                    localOItemStack.a(this);
                    Q();
                }
            }
        }
    }

    public void a(OItemStack paramOItemStack) {
    }

    @Override
    public void q() {
        super.q();
        ao.a(this);
        if (ap != null)
            ap.a(this);
    }
}
