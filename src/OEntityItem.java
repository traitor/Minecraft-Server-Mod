public class OEntityItem extends OEntity {
    public OItemStack a;
    private int       e;
    public int        b = 0;
    public int        c;
    private int       f = 5;

    public float      d = (float) (Math.random() * 3.141592653589793D * 2.0D);

    public OEntityItem(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3, OItemStack paramOItemStack) {
        super(paramOWorld);
        b(0.25F, 0.25F);
        bc = (be / 2.0F);
        a(paramDouble1, paramDouble2, paramDouble3);
        a = paramOItemStack;

        aQ = (float) (Math.random() * 360.0D);

        aN = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
        aO = 0.2000000029802322D;
        aP = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
    }

    @Override
    protected boolean l() {
        return false;
    }

    public OEntityItem(OWorld paramOWorld) {
        super(paramOWorld);
        b(0.25F, 0.25F);
        bc = (be / 2.0F);
    }

    @Override
    protected void a() {
    }

    private long lastcall = System.currentTimeMillis();
    private static int floor(double d) { int rt = (int) d; return rt > d ? rt-1 : rt; }
    @Override
    public void f_() {
        super.f_();
        if (c > 0) {
            c -= floor((lastcall - System.currentTimeMillis())/50);
            lastcall = System.currentTimeMillis();
        }
        aH = aK;
        aI = aL;
        aJ = aM;

        aO -= 0.03999999910593033D;
        if (aG.c(OMathHelper.b(aK), OMathHelper.b(aL), OMathHelper.b(aM)) == OMaterial.g) {
            aO = 0.2000000029802322D;
            aN = ((bq.nextFloat() - bq.nextFloat()) * 0.2F);
            aP = ((bq.nextFloat() - bq.nextFloat()) * 0.2F);
            aG.a(this, "random.fizz", 0.4F, 2.0F + bq.nextFloat() * 0.4F);
        }
        g(aK, aL, aM);
        c(aN, aO, aP);

        float f1 = 0.98F;
        if (aV) {
            f1 = 0.5880001F;
            int i = aG.a(OMathHelper.b(aK), OMathHelper.b(aU.b) - 1, OMathHelper.b(aM));
            if (i > 0)
                f1 = OBlock.m[i].bx * 0.98F;
        }

        aN *= f1;
        aO *= 0.9800000190734863D;
        aP *= f1;

        if (aV)
            aO *= -0.5D;

        e += 1;
        b += 1;
        if (b >= 6000)
            D();
    }

    @Override
    public boolean g_() {
        return aG.a(aU, OMaterial.f, this);
    }

    private boolean g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = OMathHelper.b(paramDouble1);
        int j = OMathHelper.b(paramDouble2);
        int k = OMathHelper.b(paramDouble3);

        double d1 = paramDouble1 - i;
        double d2 = paramDouble2 - j;
        double d3 = paramDouble3 - k;

        if (OBlock.o[aG.a(i, j, k)] != false) {
            int m = OBlock.o[aG.a(i - 1, j, k)] == false ? 1 : 0;
            int n = OBlock.o[aG.a(i + 1, j, k)] == false ? 1 : 0;
            int i1 = OBlock.o[aG.a(i, j - 1, k)] == false ? 1 : 0;
            int i2 = OBlock.o[aG.a(i, j + 1, k)] == false ? 1 : 0;
            int i3 = OBlock.o[aG.a(i, j, k - 1)] == false ? 1 : 0;
            int i4 = OBlock.o[aG.a(i, j, k + 1)] == false ? 1 : 0;

            int i5 = -1;
            double d4 = 9999.0D;
            if ((m != 0) && (d1 < d4)) {
                d4 = d1;
                i5 = 0;
            }
            if ((n != 0) && (1.0D - d1 < d4)) {
                d4 = 1.0D - d1;
                i5 = 1;
            }
            if ((i1 != 0) && (d2 < d4)) {
                d4 = d2;
                i5 = 2;
            }
            if ((i2 != 0) && (1.0D - d2 < d4)) {
                d4 = 1.0D - d2;
                i5 = 3;
            }
            if ((i3 != 0) && (d3 < d4)) {
                d4 = d3;
                i5 = 4;
            }
            if ((i4 != 0) && (1.0D - d3 < d4)) {
                d4 = 1.0D - d3;
                i5 = 5;
            }

            float f1 = bq.nextFloat() * 0.2F + 0.1F;
            if (i5 == 0)
                aN = (-f1);
            if (i5 == 1)
                aN = f1;
            if (i5 == 2)
                aO = (-f1);
            if (i5 == 3)
                aO = f1;
            if (i5 == 4)
                aP = (-f1);
            if (i5 == 5)
                aP = f1;
        }

        return false;
    }

    @Override
    protected void a(int paramInt) {
        a((OEntity)null, paramInt);
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        W();
        f -= paramInt;
        if (f <= 0)
            D();
        return false;
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Health", (short) (byte) f);
        paramONBTTagCompound.a("Age", (short) b);
        paramONBTTagCompound.a("Item", a.a(new ONBTTagCompound()));
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        f = (paramONBTTagCompound.d("Health") & 0xFF);
        b = paramONBTTagCompound.d("Age");
        ONBTTagCompound localONBTTagCompound = paramONBTTagCompound.k("Item");
        a = new OItemStack(localONBTTagCompound);
    }

    @Override
    public void b(OEntityPlayer paramOEntityPlayer) {
        if (aG.t)
            return;

        int i = a.a;
        if ((c == 0) && (paramOEntityPlayer.i.a(a))) {
         // hMod: allow item pickups
            Item item = new Item(a.c, i);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), item)) {
                aG.a(this, "random.pop", 0.2F, ((bq.nextFloat() - bq.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                paramOEntityPlayer.b(this, i);
                D();
            }
        }
    }
}