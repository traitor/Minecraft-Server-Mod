public class ic extends ay implements ke, Container<hn> {
    private hn[] e = new hn[36];

    /**
     * Get contents of the chest block
     * 
     * @return
     */
    public hn[] getContents() {
        return e;
    }

    /**
     * Set contents of the chest block
     * 
     * @return
     */
    public void setContents(hn[] e) {
        this.e = e;
    }

    @Override
    public int a() {
        return 27;
    }

    @Override
    public hn a(int paramInt) {
        return this.e[paramInt];
    }

    public void a(int paramInt, hn paramhn) {
        this.e[paramInt] = paramhn;
        if ((paramhn != null) && (paramhn.a > d())) {
            paramhn.a = d();
        }
        c();
    }

    @Override
    public void a(v paramv) {
        super.a(paramv);
        ed localed = paramv.k("Items");
        this.e = new hn[a()];
        for (int i = 0; i < localed.b(); i++) {
            v localv = (v) localed.a(i);
            int j = localv.b("Slot") & 0xFF;
            if ((j < 0) || (j >= this.e.length)) {
                continue;
            }
            this.e[j] = new hn(localv);
        }
    }

    @Override
    public void b(v paramv) {
        super.b(paramv);
        ed localed = new ed();

        for (int i = 0; i < this.e.length; i++) {
            if (this.e[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) i);
                this.e[i].a(localv);
                localed.a(localv);
            }
        }
        paramv.a("Items", localed);
    }

    public int d() {
        return 64;
    }
}
