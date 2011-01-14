
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class fy extends hl
        implements ep {

    public lp a;
    public MinecraftServer b;
    public md c;
    public double d;
    public double e;
    public List f = new LinkedList();
    public Set ak = new HashSet();
    public double al;
    private int bD = -99999999;
    private int bE = 60;
    private jl[] bF = {null, null, null, null, null};
    private int bG = 0;
    public boolean am;
    // hMod: Player storage
    private Player player;
    // hMod: for the inventory move hook
    private Inventory lastOpenedInventory;

    public fy(MinecraftServer paramMinecraftServer, fv paramfv, String paramString, md parammd) {
        super(paramfv);

        int i = paramfv.m;
        int j = paramfv.o;
        int k = paramfv.n;

        if (!paramfv.q.e) {
            i += W.nextInt(20) - 10;
            k = paramfv.e(i, j);
            j += W.nextInt(20) - 10;
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        b = paramMinecraftServer;
        S = 0.0F;
        parammd.a = this;
        aw = paramString;
        c = parammd;
        H = 0.0F;
        // hMod: So we don't conflict with runecraft
        this.c = new Digging(paramfv, this);

        // hMod: Store player
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
        player = etc.getDataSource().getPlayer(aw);
        player.setUser(this);
    }

    public void l() {
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.
        ap.a((ep) this);
    }

    public jl[] I() {
        return bF;
    }

    public void b_() {
        bE -= 1;
        ap.a();

        for (int i = 0; i < 5; i++) {
            jl localjl = a(i);
            if (localjl != bF[i]) {
                b.k.a(this, new v(g, i, localjl));
                bF[i] = localjl;
            }
        }
    }

    public jl a(int paramInt) {
        if (paramInt == 0) {
            return an.e();
        }
        return an.b[(paramInt - 1)];
    }

    public void f(fe paramfe) {
        // hMod: drops inventory on death.
        if (etc.getInstance().isHealthEnabled()) {
            an.h();
        }
    }

    public boolean a(fe paramfe, int paramInt) {
        if (bE > 0) {
            return false;
        }

        if (!b.n) {
            if ((paramfe instanceof hl)) {
                return false;
            }
            if ((paramfe instanceof fc)) {
                fc localfc = (fc) paramfe;
                if ((localfc.b instanceof hl)) {
                    return false;
                }
            }
        }
        return super.a(paramfe, paramInt);
    }

    public void d(int paramInt) {
        super.d(paramInt);
    }

    public void n() {
        super.b_();

        mr localObject1 = null;

        double d1 = 0.0D;
        mr localObject2;
        for (int i = 0; i < f.size(); i++) {
            localObject2 = (mr) f.get(i);
            double d2 = ((mr) localObject2).a(this);
            if ((i == 0) || (d2 < d1)) {
                localObject1 = localObject2;
                d1 = ((mr) localObject2).a(this);
            }
        }

        if (localObject1 != null) {
            int i = 0;

            if (d1 < 1024.0D) {
                i = 1;
            }
            if (a.b() < 2) {
                i = 1;
            }

            if (i != 0) {
                f.remove(localObject1);
                a.b(new er(localObject1.a * 16, 0, localObject1.b * 16, 16, 128, 16, b.e));
                List list = b.e.d(localObject1.a * 16, 0, localObject1.b * 16, localObject1.a * 16 + 16, 128, localObject1.b * 16 + 16);
                for (int j = 0; j < ((List) list).size(); j++) {
                    a((bm) ((List) list).get(j));// TODO : Complex block stuff. Move to a(bg)?
                }
            }
        }

        // hMod: Update the health
        if (this.ba != this.bE) {
            //updates your health when it is changed.
            if (!etc.getInstance().isHealthEnabled()) {
                this.aZ = 20;
                this.bi = false;
            } else {
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), bD, aZ)) {
                    aZ = bD;
                } else {
                    a.b(new fj(aZ));
                }
            }
            bD = aZ;
        }
    }

    private void a(bm parambm) {
        if (parambm != null) {
            // hMod: Let plugins know we're showing a sign
            if (parambm instanceof lv) {
                Sign sign = new Sign((lv) parambm);
                etc.getLoader().callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
            }
            kx localkx = parambm.g();
            if (localkx != null) {
                a.b(localkx);
            }
        }
    }

    public void o() {
        s = (this.t = this.u = 0.0D);
        bA = false;
        super.o();
    }

    public void c(fe paramfe, int paramInt) {
        if (!paramfe.G) {
            if ((paramfe instanceof ic)) {
                b.k.a(paramfe, new dq(paramfe.g, g));
            }
            if ((paramfe instanceof fc)) {
                b.k.a(paramfe, new dq(paramfe.g, g));
            }
        }
        super.c(paramfe, paramInt);
        ap.a();
    }

    public void K() {
        if (!au) {
            av = -1;
            au = true;
            b.k.a(this, new z(this, 1));
        }
    }

    public float w() {
        return 1.62F;
    }

    public void e(fe paramfe) {
        super.e(paramfe);
        a.b(new ab(this, k));
        a.a(p, q, r, v, w);
    }

    protected void a(double paramDouble, boolean paramBoolean) {
    }

    public void b(double paramDouble, boolean paramBoolean) {
        super.a(paramDouble, paramBoolean);
    }

    private void U() {
        bG = (bG % 100 + 1);
    }
    // TODO : fix workbenches
    public void a(int paramInt1, int paramInt2, int paramInt3) {
        U();
        a.b(new jh(bG, 1, "Crafting", 9));
        ap = new jg(an, l, paramInt1, paramInt2, paramInt3);
        ap.f = bG;
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.
        ap.a((ep) this);
    }

    public void a(mn parammn) {
        // hMod: Check if we can open this
        Inventory inv = null;
        String name = parammn.b();
        if (parammn instanceof kc) {
            inv = new Chest((kc) parammn);
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv)) {
                return;
            }
        } else if (parammn instanceof ay) {
            inv = new DoubleChest((ay) parammn);
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv)) {
                return;
            }
        }

        if (inv != null) {
            name = inv.getName();
        }
        this.lastOpenedInventory = inv;
        U();
        a.b(new jh(bG, 0, name, parammn.h_()));
        ap = new cf(an, parammn);
        ap.f = bG;
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.
        ap.a((ep) this);
    }

    public void a(ez paramez) {
        // hMod: Check if we can open this
        Inventory inv = new Furnace(paramez);
        String name = paramez.b();
        if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv)) {
            return;
        }

        if (inv != null) {
            name = inv.getName();
        }
        this.lastOpenedInventory = inv;
        U();
        a.b(new jh(bG, 2, name, paramez.h_()));
        ap = new bq(an, paramez);
        ap.f = bG;
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.
        ap.a((ep) this);
    }
    /*
     * new type of inventory ?
     * TODO: fix dispensers
     */

    public void a(bf parambf) {
        U();
        a.b(new jh(bG, 3, parambf.b(), parambf.h_()));
        ap = new ew(an, parambf);
        ap.f = bG;
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.
        ap.a((ep) this);
    }

    public void a(ei paramei, int paramInt, jl paramjl) {
        if ((paramei.a(paramInt) instanceof mp)) {
            return;
        }

        if (am) {
            return;
        }

        a.b(new in(paramei.f, paramInt, paramjl));
    }

    public void a(ei paramei, List paramList) {
        a.b(new kn(paramei.f, paramList));
        a.b(new in(-1, -1, an.i()));
    }

    public void a(ei paramei, int paramInt1, int paramInt2) {
        a.b(new p(paramei.f, paramInt1, paramInt2));
    }

    public void a(jl paramjl) {
    }

    public void L() {
        a.b(new h(ap.f));
        N();
    }

    public void M() {
        if (am) {
            return;
        }
        a.b(new in(-1, -1, an.i()));
    }

    public void N() {
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.
        ap.a((ep) this);
        ap = ao;
    }
    // hMod: for inventory move hook

    public Inventory getLastOpenedInventory() {
        return this.lastOpenedInventory;
    }
}
