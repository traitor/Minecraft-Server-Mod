import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class et extends fz {
    public ji a;
    public MinecraftServer b;
    public jv c;
    public double d;
    public double e;
    public List f = new LinkedList();

    public Set aj = new HashSet();
    public double ak;
    public boolean al = false;

    private int bv = -99999999;
    private int bw = 60;

    // hMod: Player storage
    private Player player;

    public et(MinecraftServer paramMinecraftServer, eq parameq, String paramString, jv paramjv) {
        super(parameq);

        int i = parameq.m;
        int j = parameq.o;
        int k = parameq.n;

        if (!parameq.q.e) {
            i += this.W.nextInt(20) - 10;
            k = parameq.e(i, j);
            j += this.W.nextInt(20) - 10;
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        this.b = paramMinecraftServer;
        this.S = 0.0F;
        paramjv.a = this;
        this.at = paramString;
        // hMod: So we don't conflict with runecraft
        this.c = new Digging(parameq, this);
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
        this.bw -= 1;
    }

    @Override
    public void f(ea paramea) {
        // hMod: drops inventory on death.
        // TODO: HOOK MEH
        if (etc.getInstance().isHealthEnabled()) {
            this.am.f();
        }
    }

    @Override
    public boolean a(ea paramea, int paramInt) {
        if (this.bw > 0) {
            return false;
        }

        if (!this.b.n) {
            if ((paramea instanceof fz)) {
                return false;
            }
            if ((paramea instanceof dy)) {
                dy localdy = (dy) paramea;
                if ((localdy.b instanceof fz)) {
                    return false;
                }
            }
        }
        return super.a(paramea, paramInt);
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

        kh localObject1 = null;

        double d1 = 0.0D;
        Object localObject2;
        for (int i = 0; i < this.f.size(); i++) {
            localObject2 = this.f.get(i);
            double d2 = ((kh) localObject2).a(this);
            if ((i == 0) || (d2 < d1)) {
                localObject1 = (kh) localObject2;
                d1 = ((kh) localObject2).a(this);
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
                
                //hMod: create 'do' object using reflection...
                Class doClass;
                try {
                    doClass = Class.forName("do");
                    Class params[] = {Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, Integer.TYPE, eq.class};
                    Constructor ct = doClass.getConstructor(params);
                    Object arglist[] = { localObject1.a * 16, 0, localObject1.b * 16, 16, 128, 16, this.b.e };
                    this.a.b((it)ct.newInstance(arglist));
                } catch (Exception e1) {
                    e1.printStackTrace();
                }
                //this.a.b(new do(localObject1.a * 16, 0, localObject1.b * 16, 16, 128, 16, this.b.e));
                localObject2 = this.b.e.d(localObject1.a * 16, 0, localObject1.b * 16, localObject1.a * 16 + 16, 128, localObject1.b * 16 + 16);
                for (int j = 0; j < ((List) localObject2).size(); j++) {
                    ay localay = (ay) ((List) localObject2).get(j);

                    // hMod: Don't let people interact with Chests/Furnaces unless they can guild
                    if (!player.canBuild() && (localay instanceof ic || localay instanceof dv)) {
                        continue;
                    }
                    ComplexBlock block = null;
                    if (localay instanceof ic) {
                        block = new Chest((ic) localay);
                    } else if (localay instanceof dv) {
                        block = new Furnace((dv) localay);
                    } else if (localay instanceof jn) {
                        block = new Sign((jn) localay);
                    } else if (localay instanceof cf) {
                        block = new MobSpawner((cf) localay);
                    }
                    if (block != null) {
                        if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_SEND, this.getPlayer(), block)) {
                            this.a.b(new jh(localay.b, localay.c, localay.d, localay));
                        }
                    } else {
                        this.a.b(new jh(localay.b, localay.c, localay.d, localay));
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
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), this.bv, this.aR)){
                    this.aR = this.bv;
                } else {
                    this.a.b(new ef(this.aR));
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
    public void c(ea paramea, int paramInt) {
        if (!paramea.G) {
            if ((paramea instanceof gl)) {
                this.a.b(new fh(((gl) paramea).a, paramInt));
                this.b.k.a(paramea, new cs(paramea.g, this.g));
            }
            if ((paramea instanceof dy)) {
                this.a.b(new fh(new hn(fw.j), 1));
                this.b.k.a(paramea, new cs(paramea.g, this.g));
            }
        }
        super.c(paramea, paramInt);
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
    public void e(ea paramea) {
        super.e(paramea);
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
