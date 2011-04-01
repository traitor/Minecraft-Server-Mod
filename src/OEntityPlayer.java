import java.util.List;

public abstract class OEntityPlayer extends OEntityLiving {
    public OInventoryPlayer     i = new OInventoryPlayer(this);
    public OCraftingInventoryCB j;
    public OCraftingInventoryCB k;
    public byte                 l = 0;
    public int                  m = 0;
    public float                n;
    public float                o;
    public boolean              p = false;
    public int                  q = 0;
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
    private OChunkCoordinates   d;
    private int                 e = 0;
    public OEntityFish          B = null;

 // hMod start
    HumanEntity                 entity = new HumanEntity(this);

    // hMod end

    
    public OEntityPlayer(OWorld paramOWorld) {
        super(paramOWorld);

        j = new OCraftingInventoryPlayerCB(i, !paramOWorld.t);

        k = j;

        bc = 1.62F;
        OChunkCoordinates localOChunkCoordinates = paramOWorld.m();
        c(localOChunkCoordinates.a + 0.5D, localOChunkCoordinates.b + 1, localOChunkCoordinates.c + 0.5D, 0.0F, 0.0F);

        W = 20;
        P = "humanoid";
        O = 180.0F;
        bs = 20;

        M = "/mob/char.png";
    }

    @Override
    protected void a() {
        super.a();

        bz.a(16, (byte) 0);
    }

    @Override
    public void f_() {
        if (F()) {
            c += 1;
            if (c > 100)
                c = 100;
            if (!m())
                a(true, true, false);
            else if ((!aG.t) && (aG.d()))
                a(false, true, true);
        } else if (c > 0) {
            c += 1;
            if (c >= 110)
                c = 0;
        }

        super.f_();

        if ((!aG.t) && (k != null) && (!k.b(this))) {
            u();
            k = j;
        }

        t = w;
        u = x;
        v = y;

        double d1 = aK - w;
        double d2 = aL - x;
        double d3 = aM - y;

        double d4 = 10.0D;
        if (d1 > d4)
            t = (w = aK);
        if (d3 > d4)
            v = (y = aM);
        if (d2 > d4)
            u = (x = aL);
        if (d1 < -d4)
            t = (w = aK);
        if (d3 < -d4)
            v = (y = aM);
        if (d2 < -d4)
            u = (x = aL);

        w += d1 * 0.25D;
        y += d3 * 0.25D;
        x += d2 * 0.25D;

        a(OStatList.j, 1);
    }

    @Override
    protected boolean p_() {
        return (W <= 0) || (F());
    }

    protected void u() {
        k = j;
    }

    @Override
    public void o_() {
        super.o_();
        n = o;
        o = 0.0F;
    }

    @Override
    protected void c_() {
        if (p) {
            q += 1;
            if (q == 8) {
                q = 0;
                p = false;
            }
        } else
            q = 0;

        V = (q / 8.0F);
    }

    @Override
    public void r() {
         // hMod: adjust 'healing over time' independent of
         // monster-spawn=true/false (nice notchup!)
         PluginLoader.HookResult autoHeal = etc.getInstance().autoHeal();
         if ((aG.j == 0) && (autoHeal == PluginLoader.HookResult.DEFAULT_ACTION) || autoHeal == PluginLoader.HookResult.ALLOW_ACTION)
             if ((W < 20) && (br % 20 * 12 == 0))
                 b(1);

        i.f();
        n = o;

        super.r();

        float f1 = OMathHelper.a(aN * aN + aP * aP);
        float f2 = (float) Math.atan(-aO * 0.2000000029802322D) * 15.0F;
        if (f1 > 0.1F)
            f1 = 0.1F;
        if ((!aV) || (W <= 0))
            f1 = 0.0F;
        if ((aV) || (W <= 0))
            f2 = 0.0F;
        o += (f1 - o) * 0.4F;
        ae += (f2 - ae) * 0.8F;

        if (W > 0) {
            List localList = aG.b(this, aU.b(1.0D, 0.0D, 1.0D));
            if (localList != null)
                for (int i1 = 0; i1 < localList.size(); i1++) {
                    OEntity localOEntity = (OEntity) localList.get(i1);
                    if (!localOEntity.bb)
                        i(localOEntity);
                }
        }
    }

    private void i(OEntity paramOEntity) {
        paramOEntity.b(this);
    }

    @Override
    public void a(OEntity paramOEntity) {
        super.a(paramOEntity);
        b(0.2F, 0.2F);
        a(aK, aL, aM);
        aO = 0.1000000014901161D;

        if (r.equals("Notch"))
            a(new OItemStack(OItem.h, 1), true);
        i.h();

        if (paramOEntity != null) {
            aN = (-OMathHelper.b((aa + aQ) * 3.141593F / 180.0F) * 0.1F);
            aP = (-OMathHelper.a((aa + aQ) * 3.141593F / 180.0F) * 0.1F);
        } else
            aN = (aP = 0.0D);
        bc = 0.1F;

        a(OStatList.u, 1);
    }

    @Override
    public void c(OEntity paramOEntity, int paramInt) {
        m += paramInt;

        if ((paramOEntity instanceof OEntityPlayer))
            a(OStatList.w, 1);
        else
            a(OStatList.v, 1);
    }

    public void z() {
        a(i.a(i.c, 1), false);
    }

    public void b(OItemStack paramOItemStack) {
        a(paramOItemStack, false);
    }

    public void a(OItemStack paramOItemStack, boolean paramBoolean) {
        if (paramOItemStack == null)
            return;

        OEntityItem localOEntityItem = new OEntityItem(aG, aK, aL - 0.300000011920929D + q(), aM, paramOItemStack);
        localOEntityItem.c = 40;

        float f1 = 0.1F;
        float f2;
        if (paramBoolean) {
            f2 = bq.nextFloat() * 0.5F;
            float f3 = bq.nextFloat() * 3.141593F * 2.0F;
            localOEntityItem.aN = (-OMathHelper.a(f3) * f2);
            localOEntityItem.aP = (OMathHelper.b(f3) * f2);
            localOEntityItem.aO = 0.2000000029802322D;
        } else {
            f1 = 0.3F;
            localOEntityItem.aN = (-OMathHelper.a(aQ / 180.0F * 3.141593F) * OMathHelper.b(aR / 180.0F * 3.141593F) * f1);
            localOEntityItem.aP = (OMathHelper.b(aQ / 180.0F * 3.141593F) * OMathHelper.b(aR / 180.0F * 3.141593F) * f1);
            localOEntityItem.aO = (-OMathHelper.a(aR / 180.0F * 3.141593F) * f1 + 0.1F);
            f1 = 0.02F;

            f2 = bq.nextFloat() * 3.141593F * 2.0F;
            f1 *= bq.nextFloat();
            localOEntityItem.aN += Math.cos(f2) * f1;
            localOEntityItem.aO += (bq.nextFloat() - bq.nextFloat()) * 0.1F;
            localOEntityItem.aP += Math.sin(f2) * f1;
        }

        if (!(Boolean) manager.callHook(PluginLoader.Hook.ITEM_DROP, ((OEntityPlayerMP) this).getPlayer(), new Item(paramOItemStack))){
            a(localOEntityItem);
            a(OStatList.r, 1);
        // return the item to the inventory.
        }else
            i.a(paramOItemStack);

    }

    protected void a(OEntityItem paramOEntityItem) {
        aG.a(paramOEntityItem);
    }

    public float a(OBlock paramOBlock) {
        float f = i.a(paramOBlock);
        if (a(OMaterial.f))
            f /= 5.0F;
        if (!aV)
            f /= 5.0F;

        return f;
    }

    public boolean b(OBlock paramOBlock) {
        return i.b(paramOBlock);
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        ONBTTagList localONBTTagList = paramONBTTagCompound.l("Inventory");
        i.b(localONBTTagList);
        s = paramONBTTagCompound.e("Dimension");
        a = paramONBTTagCompound.m("Sleeping");
        c = paramONBTTagCompound.d("SleepTimer");

        if (a) {
            b = new OChunkCoordinates(OMathHelper.b(aK), OMathHelper.b(aL), OMathHelper.b(aM));
            a(true, true, false);
        }

        if ((paramONBTTagCompound.b("SpawnX")) && (paramONBTTagCompound.b("SpawnY")) && (paramONBTTagCompound.b("SpawnZ")))
            d = new OChunkCoordinates(paramONBTTagCompound.e("SpawnX"), paramONBTTagCompound.e("SpawnY"), paramONBTTagCompound.e("SpawnZ"));
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        paramONBTTagCompound.a("Inventory", i.a(new ONBTTagList()));
        paramONBTTagCompound.a("Dimension", s);
        paramONBTTagCompound.a("Sleeping", a);
        paramONBTTagCompound.a("SleepTimer", (short) c);

        if (d != null) {
            paramONBTTagCompound.a("SpawnX", d.a);
            paramONBTTagCompound.a("SpawnY", d.b);
            paramONBTTagCompound.a("SpawnZ", d.c);
        }
    }

    public void a(OIInventory paramOIInventory) {
    }

    public void b(int paramInt1, int paramInt2, int paramInt3) {
    }

    public void b(OEntity paramOEntity, int paramInt) {
    }

    @Override
    public float q() {
        return 0.12F;
    }

    protected void l_() {
        bc = 1.62F;
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        at = 0;
        if (W <= 0)
            return false;

        if (F())
            a(true, true, false);

        if (((paramOEntity instanceof OEntityMobs)) || ((paramOEntity instanceof OEntityArrow))) {
            if (aG.j == 0)
                paramInt = 0;
            if (aG.j == 1)
                paramInt = paramInt / 3 + 1;
            if (aG.j == 3)
                paramInt = paramInt * 3 / 2;
        }

        if (paramInt == 0)
            return false;

        Object localObject = paramOEntity;
        if (((localObject instanceof OEntityArrow)) && (((OEntityArrow) localObject).b != null))
            localObject = ((OEntityArrow) localObject).b;

        if ((localObject instanceof OEntityLiving))
            a((OEntityLiving) localObject, false);

        a(OStatList.t, paramInt);

        return super.a(paramOEntity, paramInt);
    }

    protected void a(OEntityLiving paramOEntityLiving, boolean paramBoolean) {
        if (((paramOEntityLiving instanceof OEntityCreeper)) || ((paramOEntityLiving instanceof OEntityGhast)))
            return;

        if ((paramOEntityLiving instanceof OEntityWolf)) {
            OEntityWolf localObject = (OEntityWolf) paramOEntityLiving;
            if ((((OEntityWolf) localObject).y()) && (r.equals(((OEntityWolf) localObject).v())))
                return;

        }

        Object localObject = aG.a(OEntityWolf.class, OAxisAlignedBB.b(aK, aL, aM, aK + 1.0D, aL + 1.0D, aM + 1.0D).b(16.0D, 4.0D, 16.0D));
        for (OEntity localOEntity : (List<OEntity>) localObject) {
            OEntityWolf localOEntityWolf = (OEntityWolf) localOEntity;
            if ((localOEntityWolf.y()) && (localOEntityWolf.A() == null) && (r.equals(localOEntityWolf.v())) && ((!paramBoolean) || (!localOEntityWolf.w()))) {
                localOEntityWolf.b(false);
                localOEntityWolf.c(paramOEntityLiving);
            }
        }
    }

    @Override
    protected void c(int paramInt) {
        int i1 = 25 - i.g();
        int i2 = paramInt * i1 + e;
        i.c(paramInt);
        paramInt = i2 / 25;
        e = (i2 % 25);
        super.c(paramInt);
    }

    public void a(OTileEntityFurnace paramOTileEntityFurnace) {
    }

    public void a(OTileEntityDispenser paramOTileEntityDispenser) {
    }

    public void a(OTileEntitySign paramOTileEntitySign) {
    }

    public void c(OEntity paramOEntity) {
        if (paramOEntity.a(this))
            return;
        OItemStack localOItemStack = A();
        if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
            localOItemStack.a((OEntityLiving) paramOEntity);
            if (localOItemStack.a <= 0) {
                localOItemStack.a(this);
                B();
            }
        }
    }

    public OItemStack A() {
        return i.b();
    }

    public void B() {
        i.a(i.c, null);
    }

    @Override
    public double C() {
        return bc - 0.5F;
    }

    public void m_() {
        q = -1;
        p = true;
    }

    public void d(OEntity paramOEntity) {
        int i1 = i.a(paramOEntity);
        if (i1 > 0) {
            paramOEntity.a(this, i1);
            OItemStack localOItemStack = A();
            if ((localOItemStack != null) && ((paramOEntity instanceof OEntityLiving))) {
                localOItemStack.a((OEntityLiving) paramOEntity);
                if (localOItemStack.a <= 0) {
                    localOItemStack.a(this);
                    B();
                }
            }
            if ((paramOEntity instanceof OEntityLiving)) {
                if (paramOEntity.N())
                    a((OEntityLiving) paramOEntity, true);
                a(OStatList.s, i1);
            }
        }
    }

    public void a(OItemStack paramOItemStack) {
    }

    @Override
    public void D() {
        super.D();
        j.a(this);
        if (k != null)
            k.a(this);
    }

    @Override
    public boolean E() {
        return (!a) && (super.E());
    }

    public OEnumStatus a(int paramInt1, int paramInt2, int paramInt3) {
        if ((F()) || (!N()))
            return OEnumStatus.e;

        if (aG.m.c)
            return OEnumStatus.b;
        if (aG.d())
            return OEnumStatus.c;
        if ((Math.abs(aK - paramInt1) > 3.0D) || (Math.abs(aL - paramInt2) > 2.0D) || (Math.abs(aM - paramInt3) > 3.0D))
            return OEnumStatus.d;

        b(0.2F, 0.2F);
        bc = 0.2F;
        if (aG.f(paramInt1, paramInt2, paramInt3)) {
            int i1 = aG.b(paramInt1, paramInt2, paramInt3);
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
        } else
            a(paramInt1 + 0.5F, paramInt2 + 0.9375F, paramInt3 + 0.5F);
        a = true;
        c = 0;
        b = new OChunkCoordinates(paramInt1, paramInt2, paramInt3);
        aN = (aP = aO = 0.0D);

        if (!aG.t)
            aG.q();

        return OEnumStatus.a;
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

    public void a(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3) {
        b(0.6F, 1.8F);
        l_();

        OChunkCoordinates localOChunkCoordinates1 = b;
        OChunkCoordinates localOChunkCoordinates2 = b;
        if ((localOChunkCoordinates1 != null) && (aG.a(localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c) == OBlock.S.bl)) {
            OBlockBed.a(aG, localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c, false);

            localOChunkCoordinates2 = OBlockBed.f(aG, localOChunkCoordinates1.a, localOChunkCoordinates1.b, localOChunkCoordinates1.c, 0);
            if (localOChunkCoordinates2 == null)
                localOChunkCoordinates2 = new OChunkCoordinates(localOChunkCoordinates1.a, localOChunkCoordinates1.b + 1, localOChunkCoordinates1.c);
            a(localOChunkCoordinates2.a + 0.5F, localOChunkCoordinates2.b + bc + 0.1F, localOChunkCoordinates2.c + 0.5F);
        }

        a = false;
        if ((!aG.t) && (paramBoolean2))
            aG.q();
        if (paramBoolean1)
            c = 0;
        else
            c = 100;
        if (paramBoolean3)
            a(b);
    }

    private boolean m() {
        return aG.a(b.a, b.b, b.c) == OBlock.S.bl;
    }

    public static OChunkCoordinates a(OWorld paramOWorld, OChunkCoordinates paramOChunkCoordinates) {
        OIChunkProvider localOIChunkProvider = paramOWorld.n();
        localOIChunkProvider.c(paramOChunkCoordinates.a - 3 >> 4, paramOChunkCoordinates.c - 3 >> 4);
        localOIChunkProvider.c(paramOChunkCoordinates.a + 3 >> 4, paramOChunkCoordinates.c - 3 >> 4);
        localOIChunkProvider.c(paramOChunkCoordinates.a - 3 >> 4, paramOChunkCoordinates.c + 3 >> 4);
        localOIChunkProvider.c(paramOChunkCoordinates.a + 3 >> 4, paramOChunkCoordinates.c + 3 >> 4);

        if (paramOWorld.a(paramOChunkCoordinates.a, paramOChunkCoordinates.b, paramOChunkCoordinates.c) != OBlock.S.bl)
            return null;

        OChunkCoordinates localOChunkCoordinates = OBlockBed.f(paramOWorld, paramOChunkCoordinates.a, paramOChunkCoordinates.b, paramOChunkCoordinates.c, 0);
        return localOChunkCoordinates;
    }

    @Override
    public boolean F() {
        return a;
    }

    public boolean G() {
        return (a) && (c >= 100);
    }

    public void a(String paramString) {
    }

    public OChunkCoordinates H() {
        return d;
    }

    public void a(OChunkCoordinates paramOChunkCoordinates) {
        if (paramOChunkCoordinates != null)
            d = new OChunkCoordinates(paramOChunkCoordinates);
        else
            d = null;
    }

    public void a(OStatBasic paramOStatBasic, int paramInt) {
    }

    @Override
    protected void I() {
        super.I();
        a(OStatList.q, 1);
    }

    @Override
    public void a(float paramFloat1, float paramFloat2) {
        double d1 = aK;
        double d2 = aL;
        double d3 = aM;

        super.a(paramFloat1, paramFloat2);

        g(aK - d1, aL - d2, aM - d3);
    }

    private void g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i1;
        if (a(OMaterial.f)) {
            i1 = Math.round(OMathHelper.a(paramDouble1 * paramDouble1 + paramDouble2 * paramDouble2 + paramDouble3 * paramDouble3) * 100.0F);
            if (i1 > 0)
                a(OStatList.p, i1);
        } else if (g_()) {
            i1 = Math.round(OMathHelper.a(paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) * 100.0F);
            if (i1 > 0)
                a(OStatList.l, i1);
        } else if (n()) {
            if (paramDouble2 > 0.0D)
                a(OStatList.n, (int) Math.round(paramDouble2 * 100.0D));
        } else if (aV) {
            i1 = Math.round(OMathHelper.a(paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) * 100.0F);
            if (i1 > 0)
                a(OStatList.k, i1);
        } else {
            i1 = Math.round(OMathHelper.a(paramDouble1 * paramDouble1 + paramDouble3 * paramDouble3) * 100.0F);
            if (i1 > 25)
                a(OStatList.o, i1);
        }
    }

    @Override
    protected void a(float paramFloat) {
        if (paramFloat >= 2.0F)
            a(OStatList.m, (int) Math.round(paramFloat * 100.0D));
        super.a(paramFloat);
    }

    public void J() {
    }
}