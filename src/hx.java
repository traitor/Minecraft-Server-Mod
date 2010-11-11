
public class hx extends ay implements jz {

    private hj[] e = new hj[36];

    public hj[] getContents() {
        return e;
    }

    public void setContents(hj[] e) {
        this.e = e;
    }

    public int a() {
        return 27;
    }

    public hj a(int paramInt) {
        return this.e[paramInt];
    }

    public void a(int paramInt, hj paramhj) {
        this.e[paramInt] = paramhj;
        if ((paramhj != null) && (paramhj.a > d())) {
            paramhj.a = d();
        }
        c();
    }

    public void a(v paramv) {
        super.a(paramv);
        ea localea = paramv.k("Items");
        this.e = new hj[a()];
        for (int i = 0; i < localea.b(); i++) {
            v localv = (v) localea.a(i);
            int j = localv.b("Slot") & 0xFF;
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new hj(localv);
        }
    }

    public void b(v paramv) {
        super.b(paramv);
        ea localea = new ea();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) i);
                this.e[i].a(localv);
                localea.a(localv);
            }
        }
        paramv.a("Items", localea);
    }

    public int d() {
        return 64;
    }
}
