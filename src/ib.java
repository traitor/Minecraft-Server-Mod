public class ib extends ay implements kd {
    private hm[] e = new hm[36];

    /**
     * Get contents of the chest block
     * 
     * @return
     */
    public hm[] getContents() {
        return e;
    }

    /**
     * Set contents of the chest block
     * 
     * @return
     */
    public void setContents(hm[] e) {
        this.e = e;
    }

    @Override
    public int a() {
        return 27;
    }

    @Override
    public hm a(int paramInt) {
        return e[paramInt];
    }

    public void a(int paramInt, hm paramhm) {
        e[paramInt] = paramhm;
        if ((paramhm != null) && (paramhm.a > d())) {
            paramhm.a = d();
        }
        c();
    }

    @Override
    public void a(v paramv) {
        super.a(paramv);
        ec localec = paramv.k("Items");
        e = new hm[a()];
        for (int i = 0; i < localec.b(); i++) {
            v localv = (v) localec.a(i);
            int j = localv.b("Slot") & 0xFF;
            if ((j < 0) || (j >= e.length)) {
                continue;
            }
            e[j] = new hm(localv);
        }
    }

    @Override
    public void b(v paramv) {
        super.b(paramv);
        ec localec = new ec();

        for (int i = 0; i < e.length; i++) {
            if (e[i] != null) {
                v localv = new v();
                localv.a("Slot", (byte) i);
                e[i].a(localv);
                localec.a(localv);
            }
        }
        paramv.a("Items", localec);
    }

    public int d() {
        return 64;
    }
}
