
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class fi extends gq
        implements ec {

    public kk a;
    public MinecraftServer b;
    public kw c;
    public double d;
    public double e;
    public List f = new LinkedList();
    public Set aj = new HashSet();
    public double ak;
    public boolean al = false;
    private int bE = -99999999;
    private int bF = 60;
    private int[] bG = {-1, -1, -1, -1, -1};
    private int bH = 0;
    public boolean am;

    // hMod: Player storage
    private Player player;
	// hMod: for the inventory move hook
	private Inventory lastOpenedInventory;

    public fi(MinecraftServer paramMinecraftServer, ff paramff, String paramString, kw paramkv) {
        super(paramff);

        int i = paramff.m;
        int j = paramff.o;
        int k = paramff.n;

        if (!paramff.q.e) {
            i += this.W.nextInt(20) - 10;
            k = paramff.e(i, j);
            j += this.W.nextInt(20) - 10;
        }
        c(i + 0.5D, k, j + 0.5D, 0.0F, 0.0F);

        this.b = paramMinecraftServer;
        this.S = 0.0F;
        paramkv.a = this;
        this.aw = paramString;

        // hMod: So we don't conflict with runecraft
        this.c = new Digging(paramff, this);

        this.H = 0.0F;

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

    public void k() {
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.
        this.ap.a((ec)this);
    }

    public int[] E() {
        return this.bG;
    }

    public void b_() {
        this.bF -= 1;
        this.ap.a();

        for (int i = 0; i < 5; i++) {
            int j = a(i);
            if (j != this.bG[i]) {
                this.b.k.a(this, new r(this.g, i, j));
                this.bG[i] = j;
            }
        }
    }

    public int a(int paramInt) {
        if (paramInt == 0) {
            return c(this.an.e());
        }
        return c(this.an.b[(paramInt - 1)]);
    }

    private int c(il paramik) {
        if (paramik == null) {
            return -1;
        }
        return paramik.c;
    }

    public void f(ep paramep) {
        // hMod: drops inventory on death.
        if (etc.getInstance().isHealthEnabled()) {
            this.an.h();
        }
    }

    public boolean a(ep paramep, int paramInt) {
        if (this.bF > 0) {
            return false;
        }

        if (!this.b.n) {
            if ((paramep instanceof gq)) {
                return false;
            }
            if ((paramep instanceof en)) {
                en localen = (en) paramep;
                if ((localen.b instanceof gq)) {
                    return false;
                }
            }
        }
        return super.a(paramep, paramInt);
    }

    public void c(int paramInt) {
        super.c(paramInt);
    }

    public void F() {
        super.b_();

        lk localObject1 = null;

        double d1 = 0.0D;
        lk localObject2;
        for (int i = 0; i < this.f.size(); i++) {
            localObject2 = (lk) this.f.get(i);
            double d2 = ((lk) localObject2).a(this);
            if ((i == 0) || (d2 < d1)) {
                localObject1 = localObject2;
                d1 = ((lk) localObject2).a(this);
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
                this.a.b(new ed(localObject1.a * 16, 0, localObject1.b * 16, 16, 128, 16, this.b.e));
                List list = this.b.e.d(localObject1.a * 16, 0, localObject1.b * 16, localObject1.a * 16 + 16, 128, localObject1.b * 16 + 16);
                for (int j = 0; j < list.size(); j++) {
                    a((bg) list.get(j)); // TODO : Complex block stuff. Move to a(bg)?
                }
            }
        }

         // hMod: Update the health
        if (this.ba != this.bE) {
            //updates your health when it is changed.
            if(!etc.getInstance().isHealthEnabled()) {
                this.ba = 20;
                this.bj = false;
            } else {
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.HEALTH_CHANGE, getPlayer(), this.bE, this.ba)) {
                    this.ba = this.bE;
                } else {
                    this.a.b(new eu(this.ba));
                }
            }
            this.bE = this.ba;
        }
    }

    private void a(bg parambg) {
        if (parambg != null) {
            // hMod: Let plugins know we're showing a sign
            if (parambg instanceof kp) {
                Sign sign = new Sign((kp)parambg);
                etc.getLoader().callHook(PluginLoader.Hook.SIGN_SHOW, getPlayer(), sign);
            }

            jv localju = parambg.f();
            if (localju != null) {
                this.a.b(localju);
            }
        }
    }

    public void G() {
        this.s = (this.t = this.u = 0.0D);
        this.bB = false;
        super.G();
    }

    public void c(ep paramep, int paramInt) {
        if (!paramep.G) {
            if ((paramep instanceof hf)) {
                this.b.k.a(paramep, new de(paramep.g, this.g));
            }
            if ((paramep instanceof en)) {
                this.b.k.a(paramep, new de(paramep.g, this.g));
            }
        }
        super.c(paramep, paramInt);
        this.ap.a();
    }

    public void H() {
        if (!this.au) {
            this.av = -1;
            this.au = true;
            this.b.k.a(this, new v(this, 1));
        }
    }

    public float s() {
        return 1.62F;
    }

    public void e(ep paramep) {
        super.e(paramep);
        this.a.b(new x(this, this.k));
        this.a.a(this.p, this.q, this.r, this.v, this.w);
    }

    protected void a(double paramDouble, boolean paramBoolean) {
    }

    public void b(double paramDouble, boolean paramBoolean) {
        super.a(paramDouble, paramBoolean);
    }

    public boolean p() {
        return this.al;
    }

    private void R() {
        this.bH = (this.bH % 100 + 1);
    }

    public void a(int paramInt1, int paramInt2, int paramInt3) {
        // hMod: Check if we can open this. Will have to move a line from below to here.
        ig temp = new ig(this.an, this.l, paramInt1, paramInt2, paramInt3);
        Workbench bench = new Workbench(temp);
        if ((Boolean)etc.getLoader().callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), bench)) {
            return;
        }
		this.lastOpenedInventory = bench;
        R();
        this.a.b(new ih(this.bH, 1, bench.getName(), 9));
        this.ap = temp;
        this.ap.f = this.bH;
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.
        this.ap.a((ec)this);
      }

    public void a(lg paramlf) {
        // hMod: Check if we can open this
        Inventory inv = null;
        String name = paramlf.b();
        if (paramlf instanceof jb) {
            inv = new Chest((jb)paramlf);
            if ((Boolean)etc.getLoader().callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv)) {
                return;
            }
        } else if (paramlf instanceof av) {
            inv = new DoubleChest((av)paramlf);
            if ((Boolean)etc.getLoader().callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv)) {
                return;
            }
        }

        if (inv != null) {
            name = inv.getName();
        }
		this.lastOpenedInventory = inv;
        R();
        this.a.b(new ih(this.bH, 0, name, paramlf.a()));
        this.ap = new bx(this.an, paramlf);
        this.ap.f = this.bH;
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.
        this.ap.a((ec)this);
    }

    public void a(ek paramek) {
        // hMod: Check if we can open this
        Inventory inv = new Furnace(paramek);
        String name = paramek.b();
        if ((Boolean)etc.getLoader().callHook(PluginLoader.Hook.OPEN_INVENTORY, getPlayer(), inv)) {
            return;
        }

        if (inv != null) {
            name = inv.getName();
        }
		this.lastOpenedInventory = inv;
        R();
        this.a.b(new ih(this.bH, 2, name, paramek.a()));
        this.ap = new bj(this.an, paramek);
        this.ap.f = this.bH;
        // hMod: Make sure this gets cast correctly, or mutant puppies will spawn and eat your items.

        this.ap.a((ec)this);
    }

    public void a(dw paramdw, int paramInt, il paramik) {
        if ((paramdw.a(paramInt) instanceof li)) {
            return;
        }

        if (this.am) {
            return;
        }

        this.a.b(new hq(paramdw.f, paramInt, paramik));
    }

    public void a(dw paramdw, List paramList) {
        this.a.b(new jl(paramdw.f, paramList));
        this.a.b(new hq(-1, -1, this.an.i()));
    }

    public void a(dw paramdw, int paramInt1, int paramInt2) {
        this.a.b(new m(paramdw.f, paramInt1, paramInt2));
    }

    public void a(il paramik) {
    }

    public void I() {
        this.a.b(new f(this.ap.f));
        K();
    }

    public void J() {
        if (this.am) {
            return;
        }
        this.a.b(new hq(-1, -1, this.an.i()));
    }

    public void K() {
        this.ap.a((gq)this);
        this.ap = this.ao;
    }

	// hMod: for inventory move hook
	public Inventory getLastOpenedInventory()
	{
		return this.lastOpenedInventory;
	}
}
