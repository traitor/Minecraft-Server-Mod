import java.net.Socket;
import java.util.Random;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public class eu extends eh {
    public static Logger a = Logger.getLogger("Minecraft");
    private static Random d = new Random();
    public ba b;
    public boolean c = false;
    private MinecraftServer e;
    private int f = 0;
    private String g = null;
    private z h = null;
    private String i = "";

    public eu(MinecraftServer paramMinecraftServer, Socket paramSocket, String paramString) {
        this.e = paramMinecraftServer;
        this.b = new ba(paramSocket, paramString, this);
    }

    public void a() {
        if (this.h != null) {
            b(this.h);
            this.h = null;
        }
        if (this.f++ == 100) {
            b("Took too long to log in");
        } else {
            this.b.a();
        }
    }

    public void b(String paramString) {
        a.info("Disconnecting " + b() + ": " + paramString);
        this.b.a(new il(paramString));
        this.b.c();
        this.c = true;
    }

    public void a(e parame) {
        if (this.e.l) {
            this.i = Long.toHexString(d.nextLong());
            this.b.a(new e(this.i));
        } else {
            this.b.a(new e("-"));
        }
    }

    public void a(z paramz) {
        this.g = paramz.b;
        if (paramz.a != 1) {
            b("Outdated client!");
            return;
        }

        if (!this.e.l) {
            b(paramz);
        } else {
            new db(this, paramz).start();
        }
    }

    public void b(z paramz) {
        dy localdy = this.e.f.a(this, paramz.b, paramz.c);
        if (localdy != null) {
            a.info(b() + " logged in");
            ia localia = new ia(this.e, this.b, localdy);
            localia.b(new z("", "", 0));

            if (Scripting.getInstance(localdy).login()) {
                //This will send out 'x has joined game'
                this.e.f.a(localdy);
                localia.a(localdy.k, localdy.l, localdy.m, localdy.q, localdy.r);

                this.e.c.a(localia);
                localia.b(new fj(this.e.e.c));
            }
        }
        this.c = true;
    }

    public void a(String paramString) {
        a.info(b() + " lost connection");
        this.c = true;
    }

    public void a(hn paramhn) {
        b("Protocol error");
    }

    public String b() {
        if (this.g != null) {
            return this.g + " [" + this.b.b().toString() + "]";
        }
        return this.b.b().toString();
    }
}
