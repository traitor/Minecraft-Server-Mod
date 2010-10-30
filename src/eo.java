
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class eo extends ft {

    public jc a;
    public MinecraftServer b;
    public jo c;
    public double d;
    public double e;
    public List f = new LinkedList();
    public Set ai = new HashSet();
    public double aj;
    private Player player;

    public eo(MinecraftServer paramMinecraftServer, el paramel, String paramString, jo paramjo) {
        super(paramel);

        int i = paramel.m;
        int j = paramel.o;
        int k = paramel.n;

        if (!paramel.q.c) {
            i += this.V.nextInt(20) - 10;
            k = paramel.e(i, j);
            j += this.V.nextInt(20) - 10;
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);
        this.b = paramMinecraftServer;
        this.R = 0.0F;
        paramjo.a = this;
        this.ar = paramString;
        this.c = paramjo;
        this.G = 0.0F;

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
        player = etc.getDataSource().getPlayer(ar);
        player.setUser(this);
    }

    public void b_() {
    }

    public void f(dv paramdv) {
    }

    public boolean a(dv paramdv, int paramInt) {
        return false;
    }

    public void a(int paramInt) {
    }

    public void k() {
        super.b_();

        ka localObject1 = null;

        double d1 = 0.0D;
        Object localObject2;
        for (int i = 0; i < this.f.size(); i++) {
            localObject2 = (ka) this.f.get(i);
            double d2 = ((ka) localObject2).a(this);
            if ((i == 0) || (d2 < d1)) {
                localObject1 = (ka) localObject2;
                d1 = ((ka) localObject2).a(this);
            }
        }

        if (localObject1 != null) {
            int i = 0;

            if (d1 < 1024.0D) {
                i = 1;
            }
            if (this.a.b() < 2) {
                i = 1;
            }

            if (i != 0) {
                this.f.remove(localObject1);
                this.a.b(new dk(localObject1.a * 16, 0, localObject1.b * 16, 16, 128, 16, this.b.e));
                localObject2 = this.b.e.d(localObject1.a * 16, 0, localObject1.b * 16, localObject1.a * 16 + 16, 128, localObject1.b * 16 + 16);
                for (int j = 0; j < ((List) localObject2).size(); j++) {
                    av localav = (av) ((List) localObject2).get(j);
                    if (!player.canBuild() && (localav instanceof hv || localav instanceof dr))
                        continue;
                    ComplexBlock block = null;
                    if (localav instanceof hv)
                        block = new Chest((hv)localav);
                    else if (localav instanceof dr)
                        block = new Furnace((dr)localav);
                    else if (localav instanceof jg)
                        block = new Sign((jg)localav);
                    if (block != null) {
                        if (!(Boolean)etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, new Object[] { this, block }))
                            this.a.b(new ja(localav.b, localav.c, localav.d, localav));
                    } else
                        this.a.b(new ja(localav.b, localav.c, localav.d, localav));
                }
            }
        }
    }

    public void D() {
        this.s = (this.t = this.u = 0.0D);
        this.bj = false;
        super.D();
    }

    public void c(dv paramdv, int paramInt) {
        if ((!paramdv.F)
                && ((paramdv instanceof gf))) {
            this.a.b(new fc(((gf) paramdv).a, paramInt));
            this.b.k.a(paramdv, new cp(paramdv.g, this.g));
        }

        super.c(paramdv, paramInt);
    }

    public void E() {
        if (!this.ap) {
            this.aq = -1;
            this.ap = true;
            this.b.k.a(this, new p(this, 1));
        }
    }

    protected float s() {
        return 1.62F;
    }
}
