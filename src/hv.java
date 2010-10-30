
public class hv extends av
        implements jx {

    private hh[] e = new hh[36];

    public hh[] getContents() {
        return e;
    }

    public void setContents(hh[] e) {
        this.e = e;
    }

    public int a() {
        return 27;
    }

    public hh a(int paramInt) {
        return this.e[paramInt];
    }

    public void a(int paramInt, hh paramhh) {
        this.e[paramInt] = paramhh;
        if ((paramhh != null) && (paramhh.a > d())) {
            paramhh.a = d();
        }
        c();
    }

    public void a(t paramt) {
        super.a(paramt);
        dy localdy = paramt.k("Items");
        this.e = new hh[a()];
        for (int i = 0; i < localdy.b(); i++) {
            t localt = (t) localdy.a(i);
            int j = localt.b("Slot") & 0xFF;
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new hh(localt);
        }
    }

    public void b(t paramt) {
        super.b(paramt);
        dy localdy = new dy();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                t localt = new t();
                localt.a("Slot", (byte) i);
                this.e[i].a(localt);
                localdy.a(localt);
            }
        }
        paramt.a("Items", localdy);
    }

    public int d() {
        return 64;
    }
}
