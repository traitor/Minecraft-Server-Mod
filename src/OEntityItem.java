import java.util.Random;

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
        bb = (bd / 2.0F);
        a(paramDouble1, paramDouble2, paramDouble3);
        a = paramOItemStack;

        aP = (float) (Math.random() * 360.0D);

        aM = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
        aN = 0.2000000029802322D;
        aO = (float) (Math.random() * 0.2000000029802322D - 0.1000000014901161D);
        bg = false;
    }

    public OEntityItem(OWorld paramOWorld) {
        super(paramOWorld);
        a(0.25F, 0.25F);
        bb = (bd / 2.0F);
    }

    protected void a() {
    }

    @Override
    public void f_() {
        super.f_();
        if (c > 0) {
            c -= 1;
        }
        aG = aJ;
        aH = aK;
        aI = aL;

        aN -= 0.03999999910593033D;
        if (aF.c(OMathHelper.b(aJ), OMathHelper.b(aK), OMathHelper.b(aL)) == OMaterial.g) {
            aN = 0.2000000029802322D;
            aM = ((bq.nextFloat() - bq.nextFloat()) * 0.2F);
            aO = ((bq.nextFloat() - bq.nextFloat()) * 0.2F);
            aF.a(this, "random.fizz", 0.4F, 2.0F + bq.nextFloat() * 0.4F);
        }
        g(aJ, aK, aL);
        c(aM, aN, aO);

        float f1 = 0.98F;
        if (aU) {
            f1 = 0.5880001F;
            int i = aF.a(OMathHelper.b(aJ), OMathHelper.b(aT.b) - 1, OMathHelper.b(aL));
            if (i > 0) {
                f1 = OBlock.m[i].bw * 0.98F;
            }
        }

        aM *= f1;
        aN *= 0.9800000190734863D;
        aO *= f1;

        if (aU) {
            aN *= -0.5D;
        }

        e += 1;
        b += 1;
        if (b >= 6000) {
            C();
        }
    }

    @Override
    public boolean g_() {
        return aF.a(aT, OMaterial.f, this);
    }

    private boolean g(double paramDouble1, double paramDouble2, double paramDouble3) {
        int i = OMathHelper.b(paramDouble1);
        int j = OMathHelper.b(paramDouble2);
        int k = OMathHelper.b(paramDouble3);

        double d1 = paramDouble1 - i;
        double d2 = paramDouble2 - j;
        double d3 = paramDouble3 - k;

        if (OBlock.o[aF.a(i, j, k)] != false) {
            int m = OBlock.o[aF.a(i - 1, j, k)] == false ? 1 : 0;
            int n = OBlock.o[aF.a(i + 1, j, k)] == false ? 1 : 0;
            int i1 = OBlock.o[aF.a(i, j - 1, k)] == false ? 1 : 0;
            int i2 = OBlock.o[aF.a(i, j + 1, k)] == false ? 1 : 0;
            int i3 = OBlock.o[aF.a(i, j, k - 1)] == false ? 1 : 0;
            int i4 = OBlock.o[aF.a(i, j, k + 1)] == false ? 1 : 0;

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
            if (i5 == 0) {
                aM = (-f1);
            }
            if (i5 == 1) {
                aM = f1;
            }
            if (i5 == 2) {
                aN = (-f1);
            }
            if (i5 == 3) {
                aN = f1;
            }
            if (i5 == 4) {
                aO = (-f1);
            }
            if (i5 == 5) {
                aO = f1;
            }
        }

        return false;
    }

    @Override
    protected void a(int paramInt) {
        a((OEntity) null, paramInt);
    }

    @Override
    public boolean a(OEntity paramOEntity, int paramInt) {
        R();
        f -= paramInt;
        if (f <= 0) {
            C();
        }
        return false;
    }

    public void a(ONBTTagCompound paramONBTTagCompound) {
        paramONBTTagCompound.a("Health", (short) (byte) f);
        paramONBTTagCompound.a("Age", (short) b);
        paramONBTTagCompound.a("Item", a.a(new ONBTTagCompound()));
    }

    public void b(ONBTTagCompound paramONBTTagCompound) {
        f = (paramONBTTagCompound.d("Health") & 0xFF);
        b = paramONBTTagCompound.d("Age");
        ONBTTagCompound localONBTTagCompound = paramONBTTagCompound.k("Item");
        a = new OItemStack(localONBTTagCompound);
    }

    @Override
    public void b(OEntityPlayer paramOEntityPlayer) {
        if (aF.t) {
            return;
        }

        int i = a.a;
        if ((c == 0) && (paramOEntityPlayer.i.a(a))) {
            // hMod: allow item pickups
            Item item = new Item(a.c, i);
            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_PICK_UP, ((OEntityPlayerMP) paramOEntityPlayer).getPlayer(), item)) {
                aF.a(this, "random.pop", 0.2F, ((bq.nextFloat() - bq.nextFloat()) * 0.7F + 1.0F) * 2.0F);
                paramOEntityPlayer.b(this, i);
                C();
            }
        }
    }
}
