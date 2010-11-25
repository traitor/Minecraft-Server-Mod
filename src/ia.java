public class ia extends ay implements kc {
    private hl[] e = new hl[36];
    
    public hl[] getContents() {
        return e;
    }

    public void setContents(hl[] e) {
        this.e = e;
    }

    @Override
    public int a() {
        return 27;
    }

    @Override
    public hl a(int paramInt) {
        return this.e[paramInt];
    }

    public void a(int paramInt, hl paramhl) {
        this.e[paramInt] = paramhl;
        if ((paramhl != null) && (paramhl.a > d())) {
            paramhl.a = d();
        }
        c();
    }

    @Override
    public void a(v paramv) {
        super.a(paramv);
        eb localeb = paramv.k("Items");
        this.e = new hl[a()];
        for (int i = 0; i < localeb.b(); i++) {
            v localv = (v) localeb.a(i);
            int j = localv.b("Slot") & 0xFF;
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new hl(localv);
        }
    }

    @Override
    public void b(v paramv) {
        super.b(paramv);
        eb localeb = new eb();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) i);
                this.e[i].a(localv);
                localeb.a(localv);
            }
        }
        paramv.a("Items", localeb);
    }

    public int d() {
        return 64;
    }
}