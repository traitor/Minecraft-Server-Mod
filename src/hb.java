
public class hb extends as
        implements iw {

    private gp[] e = new gp[36];

    public gp[] getContents() {
        return e;
    }

    public void setContents(gp[] e) {
        this.e = e;
    }

    public int a() {
        return 27;
    }

    public gp a(int paramInt) {
        return this.e[paramInt];
    }

    public void a(int paramInt, gp paramgp) {
        this.e[paramInt] = paramgp;
        if ((paramgp != null) && (paramgp.a > d())) {
            paramgp.a = d();
        }
        c();
    }

    public void a(s params) {
        super.a(params);
        dm localdm = params.k("Items");
        this.e = new gp[a()];
        for (int i = 0; i < localdm.b(); i++) {
            s locals = (s) localdm.a(i);
            int j = locals.b("Slot") & 0xFF;
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new gp(locals);
        }
    }

    public void b(s params) {
        super.b(params);
        dm localdm = new dm();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                s locals = new s();
                locals.a("Slot", (byte) i);
                this.e[i].a(locals);
                localdm.a(locals);
            }
        }
        params.a("Items", localdm);
    }

    public int d() {
        return 64;
    }
}
