
import java.util.List;
import java.util.Random;

public class OTileEntityMobSpawner extends OTileEntity {

    public int e = -1;
    public String h;
    public double f;
    public double g = 0.0D;

    public OTileEntityMobSpawner() {
        h = "Pig";
        e = 20;
    }

    public void a(String paramString) {
        h = paramString;
    }

    //check if there are no nearby players.
    public boolean a() {
        return a.a((double)b + 0.5D, (double)c + 0.5D, (double)d + 0.5D, 16.0D) != null;
    }

    @Override
    public void f() {
        g = f;

        //do nothing if there are no players nearby
        if (!a()) {
            return;
        }

        double d1 = (float)b + a.l.nextFloat();
        double d2 = (float)c + a.l.nextFloat();
        double d3 = (float)d + a.l.nextFloat();
        a.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
        a.a("flame", d1, d2, d3, 0.0D, 0.0D, 0.0D);

        f += 1000.0F / ((float)e + 200.0F);
        while (f > 360.0D) {
            f -= 360.0D;
            g -= 360.0D;
        }

        if (e == -1) {
            b();
        }

        if (e > 0) {
            e -= 1;
            return;
        }

        int i = 4;
        for (int j = 0; j < i; j++) {
            OEntityLiving localOEntityLiving = (OEntityLiving) OEntityList.a(h, a);
            if (localOEntityLiving == null) {
                return;
            }

            //get the light level inside the mob cage.
            int k = a.a(localOEntityLiving.getClass(), OAxisAlignedBB.b(b, c, d, b + 1, c + 1, d + 1).b(8.0D, 4.0D, 8.0D)).size();
            if (k >= 6) {
                b();
                return;
            }

            if (localOEntityLiving != null) {
                //generate a random location inside the mob room.
                double d4 = (double)b + (a.l.nextDouble() - a.l.nextDouble()) * 4.0D;
                double d5 = c + a.l.nextInt(3) - 1;
                double d6 = (double)d + (a.l.nextDouble() - a.l.nextDouble()) * 4.0D;

                //set the location of the mob.
                localOEntityLiving.c(d4, d5, d6, a.l.nextFloat() * 360.0F, 0.0F);

                if (localOEntityLiving.b()) {
                    // hMod: allow entities to spawn
                    if ((Boolean) (etc.getLoader().callHook(PluginLoader.Hook.MOB_SPAWN, new Mob(localOEntityLiving)))) {
                        b();
                        return;
                    }
                    //Spawn the mob.
                    a.a(localOEntityLiving);

                    //"create" the smoke around the mobcage.
                    for (int m = 0; m < 20; m++) {
                        d1 = (double)b + 0.5D + ((double)a.l.nextFloat() - 0.5D) * 2.0D;
                        d2 = (double)c + 0.5D + ((double)a.l.nextFloat() - 0.5D) * 2.0D;
                        d3 = (double)d + 0.5D + ((double)a.l.nextFloat() - 0.5D) * 2.0D;

                        a.a("smoke", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                        a.a("flame", d1, d2, d3, 0.0D, 0.0D, 0.0D);
                    }
                    //random effects around the spawning mob.
                    localOEntityLiving.R();
                    //reinitialize the timer.
                    b();
                }
            }
        }

        super.f();
    }

    private void b() {
        e = (200 + a.l.nextInt(600));
    }

    public void a(ONBTTagCompound paramONBTTagCompound) {
        super.a(paramONBTTagCompound);
        h = paramONBTTagCompound.h("EntityId");
        e = paramONBTTagCompound.c("Delay");
    }

    public void b(ONBTTagCompound paramONBTTagCompound) {
        super.b(paramONBTTagCompound);
        paramONBTTagCompound.a("EntityId", h);
        paramONBTTagCompound.a("Delay", (short) e);
    }
}
