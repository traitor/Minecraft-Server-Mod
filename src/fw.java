
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import net.minecraft.server.MinecraftServer;

public class fw {

    private Set a = new HashSet();
    private hf b = new hf();
    private MinecraftServer c;
    private int d;

    public fw(MinecraftServer paramMinecraftServer) {
        this.c = paramMinecraftServer;
        this.d = paramMinecraftServer.f.a();
    }

    public void a(dj paramdj) {
        ea localea;
        if ((paramdj instanceof ea)) {
            a(paramdj, 512, 2);
            localea = (ea) paramdj;
            for (Object obj : this.a) {
                fy localfy = (fy)obj;
                if (localfy.a != localea) {
                    localfy.a(localea);
                }
            }
        } else if ((paramdj instanceof fn)) {
            a(paramdj, 64, 20);
        } else if ((paramdj instanceof ih)) {
            a(paramdj, 160, 4);
        } else if ((paramdj instanceof ab)) {
            a(paramdj, 160, 2);
        }
    }

    public void a(dj paramdj, int paramInt1, int paramInt2) {
        if (paramInt1 > this.d) {
            paramInt1 = this.d;
        }
        if (this.b.b(paramdj.c)) {
            throw new IllegalStateException("Entity is already tracked!");
        }
        fy localfy = new fy(paramdj, paramInt1, paramInt2);
        this.a.add(localfy);
        this.b.a(paramdj.c, localfy);
        localfy.b(this.c.e.k);
    }

    public void b(dj paramdj) {
        fy localfy = (fy) this.b.d(paramdj.c);
        if (localfy != null) {
            this.a.remove(localfy);
            localfy.a();
        }
    }

    public void a() {
        ArrayList localArrayList = new ArrayList();
        for (Iterator localIterator1 = this.a.iterator(); localIterator1.hasNext();) {
            fy localObject = (fy) localIterator1.next();
            ((fy) localObject).a(this.c.e.k);
            if ((((fy) localObject).j) && ((((fy) localObject).a instanceof ea))) {
                localArrayList.add((ea) ((fy) localObject).a);
            }
        }
        Object localObject;
        for (int i = 0; i < localArrayList.size(); i++) {
            localObject = (ea) localArrayList.get(i);
            for (Object obj : this.a) {
                fy localfy = (fy)obj;
                if (localfy.a != localObject) {
                    localfy.a((ea) localObject);
                }
            }
        }
    }

    public void a(dj paramdj, hp paramhp) {
        fy localfy = (fy) this.b.a(paramdj.c);
        if (localfy != null) {
            localfy.a(paramhp);
        }
    }
}
