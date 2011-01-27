public class OEntityItem extends OEntity {

    public OItemStack a;
    private int       e;
    public int        b = 0;
    public int        c;
    private int       f = 5;
    public float      d = (float) (Math.random() * 3.141592653589793D * 2.0D);

    public OEntityItem(OWorld paramOWorld, double paramDouble1, double paramDouble2, double paramDouble3, OItemStack paramOItemStack) {
        super(paramOWorld);
        a(0.25F, 0.25F);
        H = (J / 2.0F);
        a(paramDouble1, paramDouble2, paramDouble3);
        a = paramOItemStack;

        v = (float) (Math.random() * 360.0D);

        s = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
        t = 0.2000000029802322D;
        u = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
        M = false;
    }

    public OEntityItem(OWorld paramOWorld) {
        super(paramOWorld);
        a(0.25F, 0.25F);
        H = (J / 2.0F);
    }

    @Override
    protected void a() {
    }

    @Override
    public void b_() {
        super.b_();
        if (c > 0)
            c -= 1;
        m = p;
        n = q;
        o = r;

        t -= 0.03999999910593033D;
        if (l.c(OMathHelper.b(p), OMathHelper.b(q), OMathHelper.b(r)) == OMaterial.g) {
            t = 0.2000000029802322D;
            s = ((W.nextFloat() - W.nextFloat()) * 0.2F);
            u = ((W.nextFloat() - W.nextFloat()) * 0.2F);
            l.a(this, "random.fizz", 0.4F, 2.0F + W.nextFloat() * 0.4F);
        }
        g(p, q, r);
        c(s, t, u);

        float f1 = 0.98F;
        if (A) {
            f1 = 0.5880001F;
            int i = l.a(OMathHelper.b(p), OMathHelper.b(z.b) - 1, OMathHelper.b(r));
            if (i > 0)
                f1 = OBlock.m[i].bu * 0.98F;
        }

        s *= f1;
        t *= 0.9800000190734863D;
        u *= f1;

        if (A)
            t *= -0.5D;

        e += 1;
        b += 1;
        if (b >= 6000)
            q();
    }

    @Override
    public boolean v() {
        return l.a(z, OMaterial.f, this);
    }

    private boolean g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = OMathHelper.b(paramDouble1);
        int j = OMathHelper.b(paramDouble2);
        int k = OMathHelper.b(paramDouble3);

        double d1 = paramDouble1 - i;
        double d2 = paramDouble2 - j;
        double d3 = paramDouble3 - k;

        if (OBlock.o[l.a(i, j, k)] != false) {
            int m = OBlock.o[l.a(i - 1, j, k)] == false ? 1 : 0;
            int n = OBlock.o[l.a(i + 1, j, k)] == false ? 1 : 0;
            int i1 = OBlock.o[l.a(i, j - 1, k)] == false ? 1 : 0;
            int i2 = OBlock.o[l.a(i, j + 1, k)] == false ? 1 : 0;
            int i3 = OBlock.o[l.a(i, j, k - 1)] == false ? 1 : 0;
            int i4 = OBlock.o[l.a(i, j, k + 1)] == false ? 1 : 0;

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

            float f1 = W.nextFloat() * 0.2F + 0.1F;
            if (i5 == 0)
                s = (-f1);
            if (i5 == 1)
                s = f1;
            if (i5 == 2)
                t = (-f1);
            if (i5 == 3)
                t = f1;
            if (i5 == 4)
                u = (-f1);
            if (i5 == 5)
                u = f1;
        }

        return false;
    }

    @Override
    protected void b(int paramInt) {
        // hMod check this.
        a((OEntity) null, paramInt);
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        y();
        f -= paramInt;
        if (f <= 0)
            q();
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
        f = (paramONBTTagCompound.c("Health") & 0xFF);
        b = paramONBTTagCompound.c("Age");
        ONBTTagCompound localONBTTagCompound = paramONBTTagCompound.j("Item");
        a = new OItemStack(localONBTTagCompound);
    }

    @Override
    public void b(OEntityPlayer paramOEntityPlayer) {
        if (l.z)
            return;

        int i = a.a;
        if ((c == 0) && (paramOEntityPlayer.an.a(a))) {
            // hMod: allow item pickups
            Item item = new Item(a.c, i);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), item)) {
                l.a(this, "random.pop", 0.2F, ((W.nextFloat() - W.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                paramOEntityPlayer.c(this, i);
                q();
            }
        }
    }
}
