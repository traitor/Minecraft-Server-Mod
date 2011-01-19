
import java.util.Random;

public class bf extends bm
        implements mn, Container<jl> {

    private jl[] e = new jl[9];
    private Random f = new Random();
    public String name = "Trap";

    @Override
    public jl[] getContents() {
        int size = getContentsSize();
        jl[] result = new jl[size];

        for (int i = 0; i < size; i++) {
            result[i] = getContentsAt(i);
        }

        return result;
    }

    public void setContents(jl[] values) {
        int size = getContentsSize();

        for (int i = 0; i < size; i++) {
            setContentsAt(i, values[i]);
        }
    }

    public jl getContentsAt(int index) {
        return a(index);
    }

    public void setContentsAt(int index, jl value) {
        a(index, value);
    }

    @Override
    public int getContentsSize() {
        return h_();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String value) {
        name = value;
    }

    public int h_() {
        return 9;
    }

    public jl a(int paramInt) {
        return e[paramInt];
    }

    public jl b(int paramInt1, int paramInt2) {
        if (e[paramInt1] != null) {
            if (e[paramInt1].a <= paramInt2) {
                jl localjl = e[paramInt1];
                e[paramInt1] = null;
                d();
                return localjl;
            }
            jl localjl = e[paramInt1].a(paramInt2);
            if (e[paramInt1].a == 0) {
                e[paramInt1] = null;
            }
            d();
            return localjl;
        }

        return null;
    }

    public jl e() {
        int i = -1;
        int j = 1;
        for (int k = 0; k < e.length; k++) {
            if ((e[k] != null) && (f.nextInt(j) == 0)) {
                i = k;

                j++;
            }
        }

        if (i >= 0) {
            return b(i, 1);
        }
        return null;
    }

    public void a(int paramInt, jl paramjl) {
        e[paramInt] = paramjl;
        if ((paramjl != null) && (paramjl.a > c())) {
            paramjl.a = c();
        }
        d();
    }

    public String b() {
        return name;
    }

    public void a(ah paramah) {
        super.a(paramah);
        fh localfh = paramah.k("Items");
        e = new jl[h_()];
        for (int i = 0; i < localfh.b(); i++) {
            ah localah = (ah) localfh.a(i);
            int j = localah.b("Slot") & 0xFF;
            if ((j < 0) || (j >= e.length)) {
                continue;
            }
            e[j] = new jl(localah);
        }
    }

    public void b(ah paramah) {
        super.b(paramah);
        fh localfh = new fh();

        for (int i = 0; i < e.length; i++) {
            if (e[i] != null) {
                ah localah = new ah();
                localah.a("Slot", (byte) i);
                e[i].a(localah);
                localfh.a(localah);
            }
        }
        paramah.a("Items", localfh);
    }

    public int c() {
        return 64;
    }

    public boolean a_(hl paramhl) {
        if (a.m(b, c, d) != this) {
            return false;
        }
        return paramhl.d(b + 0.5D, c + 0.5D, d + 0.5D) <= 64.0D;
    }
}
