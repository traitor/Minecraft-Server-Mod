// hMod: This class must extend Container<obfuscated_item> and implement the various methods within. They will not all be marked individually.

public class av
        implements lg, Container<il> {

    private String a;
    private lg b;
    private lg c;

    public il[] getContents() {
        int size = getContentsSize();
        il[] result = new il[size];

        for (int i = 0; i < size; i++) {
            result[i] = getContentsAt(i);
        }

        return result;
    }

    public void setContents(il[] values) {
        int size = getContentsSize();

        for (int i = 0; i < size; i++) {
            setContentsAt(i, values[i]);
        }
    }

    public il getContentsAt(int index) {
        return a(index);
    }

    public void setContentsAt(int index, il value) {
        a(index, value);
    }

    public int getContentsSize() {
        return a();
    }

    public Block getChestBlock() {
        if (this.b instanceof jb) {
            jb block = (jb)b;
            return etc.getServer().getBlockAt(block.b, block.c, block.d);
        }
        if (this.c instanceof jb) {
            jb block = (jb)c;
            return etc.getServer().getBlockAt(block.b, block.c, block.d);
        }
        return null;
    }

    public String getName() {
        return a;
    }

    public void setName(String value) {
        a = value;
    }

    public av(String paramString, lg paramlg1, lg paramlg2) {
        this.a = paramString;
        this.b = paramlg1;
        this.c = paramlg2;
    }

    public int a() {
        return this.b.a() + this.c.a();
    }

    public String b() {
        return getName(); //hMod: override with getName()
    }

    public il a(int paramInt) {
        if (paramInt >= this.b.a()) {
            return this.c.a(paramInt - this.b.a());
        }
        return this.b.a(paramInt);
    }

    public il a(int paramInt1, int paramInt2) {
        if (paramInt1 >= this.b.a()) {
            return this.c.a(paramInt1 - this.b.a(), paramInt2);
        }
        return this.b.a(paramInt1, paramInt2);
    }

    public void a(int paramInt, il paramil) {
        if (paramInt >= this.b.a()) {
            this.c.a(paramInt - this.b.a(), paramil);
        } else {
            this.b.a(paramInt, paramil);
        }
    }

    public int c() {
        return this.b.c();
    }

    public void d() {
        this.b.d();
        this.c.d();
    }

    public boolean a_(gq paramgq) {
        return (this.b.a_(paramgq)) && (this.c.a_(paramgq));
    }
}
