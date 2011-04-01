public class OTileEntityMobSpawner extends OTileEntity {

    public int    a = -1;
    // hMod: make public to allow reading
    public String h;
    public double b;
    public double c = 0.0D;

    public OTileEntityMobSpawner() {
        h = "Pig";
        a = 20;
    }

    public void a(String paramString) {
        h = paramString;
    }

    public boolean a() {
        return d.a(e + 0.5D, f + 0.5D, g + 0.5D, 16.0D) != null;
    }

    @Override
    public void i_() {
        c = b;

        if (!a())
            return;

        double d1 = e + d.k.nextFloat();
        double d2 = f + d.k.nextFloat();
        double d3 = g + d.k.nextFloat();
        d.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
        d.a("flame", d1, d2, d3, 0.0D, 0.0D, 0.0D);

        b += 1000.0F / (a + 200.0F);
        while (b > 360.0D) {
            b -= 360.0D;
            c -= 360.0D;
        }

        if (a == -1)
            c();

        if (a > 0) {
            a -= 1;
            return;
        }

        int i = 4;
        for (int j = 0; j < i; j++) {
            OEntityLiving localOEntityLiving = (OEntityLiving) OEntityList.a(h, d);
            if (localOEntityLiving == null)
                return;

            int k = d.a(localOEntityLiving.getClass(), OAxisAlignedBB.b(e, f, g, e + 1, f + 1, g + 1).b(8.0D, 4.0D, 8.0D)).size();
            if (k >= 6) {
                c();
                return;
            }

            if (localOEntityLiving != null) {
                double d4 = e + (d.k.nextDouble() - d.k.nextDouble()) * 4.0D;
                double d5 = f + d.k.nextInt(3) - 1;
                double d6 = g + (d.k.nextDouble() - d.k.nextDouble()) * 4.0D;

                localOEntityLiving.c(d4, d5, d6, d.k.nextFloat() * 360.0F, 0.0F);

                if (localOEntityLiving.b()) {
                    ((OWorldServer) d).a(localOEntityLiving);

                    for (int m = 0; m < 20; m++) {
                        d1 = e + 0.5D + (d.k.nextFloat() - 0.5D) * 2.0D;
                        d2 = f + 0.5D + (d.k.nextFloat() - 0.5D) * 2.0D;
                        d3 = g + 0.5D + (d.k.nextFloat() - 0.5D) * 2.0D;

                        d.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        d.a("flame", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                    }

                    localOEntityLiving.M();
                    c();
                }
            }
        }

        super.i_();
    }

    private void c() {
        a = (200 + d.k.nextInt(600));
    }

    @Override
    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        h = paramONBTTagCompound.i("EntityId");
        a = paramONBTTagCompound.d("Delay");
    }

    @Override
    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        paramONBTTagCompound.a("EntityId", h);
        paramONBTTagCompound.a("Delay", (short) a);
    }
}
