import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class es extends fy {
    public jh a;
    public MinecraftServer b;
    public ju c;
    public double d;
    public double e;
    public List f = new LinkedList();

    public Set aj = new HashSet();
    public double ak;
    public boolean al = false;
    private int bv = -99999999;

    // hMod: Player storage
    private Player player;

    public es(MinecraftServer paramMinecraftServer, ep paramep, String paramString, ju paramju) {
        super(paramep);

        int i = paramep.m;
        int j = paramep.o;
        int k = paramep.n;

        if (!paramep.q.e) {
            i += this.W.nextInt(20) - 10;
            k = paramep.e(i, j);
            j += this.W.nextInt(20) - 10;
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        this.b = paramMinecraftServer;
        this.S = 0.0F;
        paramju.a = this;
        this.at = paramString;
        this.c = paramju;
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
        player = etc.getDataSource().getPlayer(at);
        player.setUser(this);
    }

    @Override
    public void b_() {
    }

    @Override
    public void f(dy paramdy) {
        // hMod: drops inventory on death.
        // TODO: HOOK MEH
        if (etc.getInstance().isHealthEnabled()) {
            this.am.f();
        }
    }

    @Override
    public boolean a(dy paramdy, int paramInt) {
        if (!this.b.n) {
            if ((paramdy instanceof fy)) {
                return false;
            }
            if ((paramdy instanceof dx)) {
                dx localdx = (dx) paramdy;
                if ((localdx.b instanceof fy)) {
                    return false;
                }
            }
        }
        return super.a(paramdy, paramInt);
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
    @Override
    public void a(int paramInt) {
        super.a(paramInt);
    }

    public void k() {
        super.b_();

        kg localObject1 = null;

        double d1 = 0.0D;
        Object localObject2;
        for (int i = 0; i < this.f.size(); i++) {
            localObject2 = this.f.get(i);
            double d2 = ((kg) localObject2).a(this);
            if ((i == 0) || (d2 < d1)) {
                localObject1 = (kg) localObject2;
                d1 = ((kg) localObject2).a(this);
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
                this.a.b(new dn(localObject1.a * 16, 0, localObject1.b * 16, 16, 128, 16, this.b.e));
                localObject2 = this.b.e.d(localObject1.a * 16, 0, localObject1.b * 16, localObject1.a * 16 + 16, 128, localObject1.b * 16 + 16);
                for (int j = 0; j < ((List) localObject2).size(); j++) {
                    ay localay = (ay) ((List) localObject2).get(j);

                    // hMod: Don't let people interact with Chests/Furnaces unless they can guild
                    if (!player.canBuild() && (localay instanceof ib || localay instanceof du)) {
                        continue;
                    }
                    ComplexBlock block = null;
                    if (localay instanceof ib) {
                        block = new Chest((ib) localay);
                    } else if (localay instanceof du) {
                        block = new Furnace((du) localay);
                    } else if (localay instanceof jm) {
                        block = new Sign((jm) localay);
                    } else if (localay instanceof cf) {
                        block = new MobSpawner((cf) localay);
                    }
                    if (block != null) {
                        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, new Object[]{this, block})) {
                            this.a.b(new jg(localay.b, localay.c, localay.d, localay));
                        }
                    } else {
                        this.a.b(new jg(localay.b, localay.c, localay.d, localay));
                    }
                }
            }
        }

        // hMod: Update the health
        if (this.aR != this.bv) {
            //updates your health when it is changed.
            if(!etc.getInstance().isHealthEnabled()) {
                this.aR = 20;
                this.ba = false;
            // Only send health updates when health is turned on.
            } else {
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.HEALTH_CHANGE, new Object[]{getPlayer(), this.bv, this.aR})){
                    this.aR = this.bv;
                } else {
                    this.a.b(new ee(this.aR));
                }
            }
            this.bv = this.aR;
        }
    }

    @Override
    public void E() {
        this.s = (this.t = this.u = 0.0D);
        this.bs = false;
        super.E();
    }

    @Override
    public void c(dy paramdy, int paramInt) {
        if (!paramdy.G) {
            if ((paramdy instanceof gk)) {
                this.a.b(new fg(((gk) paramdy).a, paramInt));
                this.b.k.a(paramdy, new cs(paramdy.g, this.g));
            }
            if ((paramdy instanceof dx)) {
                this.a.b(new fg(new hm(fv.j), 1));
                this.b.k.a(paramdy, new cs(paramdy.g, this.g));
            }
        }
        super.c(paramdy, paramInt);
    }

    @Override
    public void F() {
        if (!this.ar) {
            this.as = -1;
            this.ar = true;
            this.b.k.a(this, new q(this, 1));
        }
    }

    @Override
    public float s() {
        return 1.62F;
    }

    @Override
    public void e(dy paramdy) {
        super.e(paramdy);
        this.a.b(new s(this, this.k));
        this.a.a(this.p, this.q, this.r, this.v, this.w);
    }

    @Override
    protected void a(double paramDouble, boolean paramBoolean) {
    }

    public void b(double paramDouble, boolean paramBoolean) {
        super.a(paramDouble, paramBoolean);
    }

    @Override
    public boolean p() {
        return this.al;
    }
}
