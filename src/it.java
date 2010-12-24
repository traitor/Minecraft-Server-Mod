
public class it
        implements lg, Container<il> {

    private il[] a = new il[1];

    /**
     * Get contents of the chest block
     *
     * @return
     */
    public il[] getContents() {
        return a;
    }

    /**
     * Set contents of the chest block
     *
     * @return
     */
    public void setContents(il[] e) {
        this.a = e;
    }

    public int a() {
        return 1;
    }

    public il a(int paramInt) {
        return this.a[paramInt];
    }

    public String b() {
        return "Result";
    }

    public il a(int paramInt1, int paramInt2) {
        if (this.a[paramInt1] != null) {
            il localil = this.a[paramInt1];
            this.a[paramInt1] = null;
            return localil;
        }
        return null;
    }

    public void a(int paramInt, il paramil) {
        this.a[paramInt] = paramil;
    }

    public int c() {
        return 64;
    }

    public void d() {
    }

    public boolean a_(gq paramgq) {
        return true;
    }
}
