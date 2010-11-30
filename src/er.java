
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class er extends fx {

    public jg a;
    public MinecraftServer b;
    public jt c;
    public double d;
    public double e;
    public List f = new LinkedList();
    public Set aj = new HashSet();
    public double ak;
    private int bu = -1;
    private Player player;

    public er(MinecraftServer paramMinecraftServer, eo parameo, String paramString, jt paramjt) {
        super(parameo);

        int i = parameo.m;
        int j = parameo.o;
        int k = parameo.n;

        if (!parameo.q.e) {
            i += this.W.nextInt(20) - 10;
            k = parameo.e(i, j);
            j += this.W.nextInt(20) - 10;
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        this.b = paramMinecraftServer;
        this.S = 0.0F;
        paramjt.a = this;
        this.as = paramString;
        this.c = new Digging(parameo, this);
        this.H = 0.0F;

        player = etc.getDataSource().getPlayer(paramString);
        player.setUser(this);
    }

    /**
     * Returns the player
     * 
     * @return player
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Reloads the player
     */
    public void reloadPlayer() {
        player = etc.getDataSource().getPlayer(as);
        player.setUser(this);
    }

    public void b_() {
    }

    public void f(dx paramdx) {
        // hMod: drops inventory on death.
        if (etc.getInstance().isHealthEnabled())
            this.al.f();
    }

    public boolean a(dx paramdx, int paramInt) {
        if (!this.b.n) {
            if ((paramdx instanceof fx))
                return false;
            if ((paramdx instanceof dw)) {
                dw localdw = (dw) paramdx;
                if ((localdw.b instanceof fx)) {
                    return false;
                }
            }
        }
        return super.a(paramdx, paramInt);
    }

    /**
    Heal health by paramInt
    2 = 1 heart.
    20 = max health.

    Code for decrease health ( from fx.class )
     *  Decrease damage because of armor ( al = inventory )
    int i = 25 - al.e();
     * paramInt is the ammount of health to decrease ?
     * add left over damage ?
    int j = paramInt * i + a;
     * decrease durability of armor ?
    al.c(paramInt);
     * the actual damage experienced.
    paramInt = j / 25;
     * store left over damage ?
    a = (j % 25);
    super.c(paramInt);
     *
     *
     */
    public void a(int paramInt) {
        super.a(paramInt);
    }

    public void k() {
        super.b_();

        kf localObject1 = null;

        double d1 = 0.0D;
        Object localObject2;
        for (int i = 0; i < this.f.size(); i++) {
            localObject2 = (kf) this.f.get(i);
            double d2 = ((kf) localObject2).a(this);
            if ((i == 0) || (d2 < d1)) {
                localObject1 = (kf) localObject2;
                d1 = ((kf) localObject2).a(this);
            }
        }

        if (localObject1 != null) {
            int i = 0;

            if (d1 < 1024.0D)
                i = 1;
            if (this.a.b() < 2)
                i = 1;

            if (i != 0) {
                this.f.remove(localObject1);
                this.a.b(new dm(localObject1.a * 16, 0, localObject1.b * 16, 16, 128, 16, this.b.e));
                localObject2 = this.b.e.d(localObject1.a * 16, 0, localObject1.b * 16, localObject1.a * 16 + 16, 128,
                        localObject1.b * 16 + 16);
                for (int j = 0; j < ((List) localObject2).size(); j++) {
                    ay localay = (ay) ((List) localObject2).get(j);

                    // hMod: Don't let people interact with Chests/Furnaces unless they can guild
                    if (!player.canBuild() && (localay instanceof ia || localay instanceof dt)) {
                        continue;
                    }
                    ComplexBlock block = null;
                    if (localay instanceof ia) {
                        block = new Chest((ia) localay);
                    } else if (localay instanceof dt) {
                        block = new Furnace((dt) localay);
                    } else if (localay instanceof jl) {
                        block = new Sign((jl) localay);
                    } else if (localay instanceof cf) {
                        block = new MobSpawner((cf) localay);
                    }
                    if (block != null) {
                        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, new Object[]{this, block})) {
                            this.a.b(new jf(localay.b, localay.c, localay.d, localay));
                        }
                    } else {
                        this.a.b(new jf(localay.b, localay.c, localay.d, localay));
                    }
                }
            }
        }

        // hMod: Update the health
        if (this.aQ != this.bu) {
            //updates your health when it is changed.
            if(!etc.getInstance().isHealthEnabled()) {
                this.aQ = 20;
                this.aZ = false;
            // Only send health updates when health is turned on.
            } else {
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.HEALTH_CHANGE, new Object[]{getPlayer(), this.bu, this.aQ})){
                    this.aQ = this.bu;
                } else {
                    this.a.b(new ed(this.aQ));
                }
            }
            this.bu = this.aQ;
        }
    }

    public void E() {
        this.s = (this.t = this.u = 0.0D);
        this.br = false;
        super.E();
    }

    public void c(dx paramdx, int paramInt) {
        if ((!paramdx.G) && ((paramdx instanceof gj))) {
            this.a.b(new ff(((gj) paramdx).a, paramInt));
            this.b.k.a(paramdx, new cs(paramdx.g, this.g));
        }

        super.c(paramdx, paramInt);
    }

    public void F() {
        if (!this.aq) {
            this.ar = -1;
            this.aq = true;
            this.b.k.a(this, new q(this, 1));
        }
    }

    public float s() {
        return 1.62F;
    }

    public void e(dx paramdx) {
        super.e(paramdx);
        this.a.b(new s(this, this.k));
        this.a.a(this.p, this.q, this.r, this.v, this.w);
    }

    protected void a(double paramDouble, boolean paramBoolean) {
    }

    public void b(double paramDouble, boolean paramBoolean) {
        super.a(paramDouble, paramBoolean);
    }
}
