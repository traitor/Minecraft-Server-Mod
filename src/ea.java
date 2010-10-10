
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class ea extends fc {

    public id a;
    public MinecraftServer b;
    public in ad;
    public double ae;
    public double af;
    public List ag = new LinkedList();
    public Set ah = new HashSet();
    public double ai;
    private Player player;

    public ea(MinecraftServer paramMinecraftServer, dy paramdy, String paramString, in paramin) {
        super(paramdy);

        int i = paramdy.n + this.R.nextInt(20) - 10;
        int j = paramdy.p + this.R.nextInt(20) - 10;
        int k = paramdy.d(i, j);
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);
        this.b = paramMinecraftServer;
        this.N = 0.0F;
        paramin.a = this;
        this.aq = paramString;
        this.ad = paramin;
        this.C = 0.0F;

        player = etc.getDataSource().getPlayer(paramString);
        player.setUser(this);
    }

    /**
     * Returns the player
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Reloads the player
     */
    public void reloadPlayer() {
        player = etc.getDataSource().getPlayer(aq);
        player.setUser(this);
    }

    public void b_() {
    }

    public void f(dj paramdj) {
    }

    public boolean a(dj paramdj, int paramInt) {
        return false;
    }

    public void a(int paramInt) {
    }

    public void i() {
        super.b_();

        Object localObject1 = null;

        double d1 = 0.0D;
        Object localObject2;
        for (int i = 0; i < this.ag.size(); i++) {
            localObject2 = (iy) this.ag.get(i);
            double d2 = ((iy) localObject2).a(this);
            if ((i == 0) || (d2 < d1)) {
                localObject1 = localObject2;
                d1 = ((iy) localObject2).a(this);
            }
        }

        if (localObject1 != null) {
            i = 0;

            if (d1 < 1024.0D) {
                i = 1;
            }
            if (this.a.b() < 2) {
                i = 1;
            }

            if (i != 0) {
                iy localiy = (iy) localObject1;
                this.ag.remove(localiy);
                this.a.b(new cz(localiy.a * 16, 0, localiy.b * 16, 16, 128, 16, this.b.e));
                localObject2 = this.b.e.d(localiy.a * 16, 0, localiy.b * 16, localiy.a * 16 + 16, 128, localiy.b * 16 + 16);
                for (int j = 0; j < ((List) localObject2).size(); j++) {
                    as localas = (as) ((List) localObject2).get(j);
                    if (!player.canBuild() && (localas instanceof hb || localas instanceof df))
                        continue;
                    ComplexBlock block = null;
                    if (localas instanceof hb)
                        block = new Chest((hb)localas);
                    else if (localas instanceof df)
                        block = new Furnace((df)localas);
                    else if (localas instanceof ig)
                        block = new Sign((ig)localas);
                    if (block != null) {
                        if (!(Boolean)etc.getInstance().getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, new Object[] { this, block }))
                            this.a.b(new ib(localas.b, localas.c, localas.d, localas));
                    } else
                        this.a.b(new ib(localas.b, localas.c, localas.d, localas));
                }
            }
        }
    }

    public void y() {
        this.o = (this.p = this.q = 0.0D);
        this.bg = false;
        super.y();
    }

    public void c(dj paramdj, int paramInt) {
        if ((!paramdj.B)
                && ((paramdj instanceof fn))) {
            this.a.b(new en(((fn) paramdj).a, paramInt));
            this.b.k.a(paramdj, new ce(paramdj.c, this.c));
        }

        super.c(paramdj, paramInt);
    }

    public void z() {
        if (!this.ao) {
            this.ap = -1;
            this.ao = true;
            this.b.k.a(this, new o(this, 1));
        }
    }

    protected float p() {
        return 1.62F;
    }
}
