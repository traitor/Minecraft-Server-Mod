import java.io.*;
import java.net.URL;
import java.lang.reflect.*;

public class dq extends Thread {
    private ad a;
    private fo b;

    dq(fo fo1, ad ad1) {
        super();
        b = fo1;
        a = ad1;
    }

    public void run() {
        try {
            String serverID = fo.a(b);
            boolean res = (Boolean)etc.getLoader().callHook(PluginLoader.Hook.NAME_VERIFICATION, new Object[] { a.b, serverID, b.addr });
            if (res) {
                fo.a(b, a);
                return;
            }
            URL url = new URL("http://www.minecraft.net/game/checkserver.jsp?user=" + a.b + "&serverId=" + serverID);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String reply = reader.readLine();
            reader.close();
            System.out.println("THE REPLY IS " + reply);
            if (reply.equals("YES")) {
                fo.a(b, a);
            } else {
                b.b("Failed to verify username!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static String access1(fo obj) {
        try {
            Field f1 = obj.getClass().getDeclaredField("i");
            f1.setAccessible(true);
            return (String)f1.get(obj);
        } catch (Throwable t) {
            t.printStackTrace();
            return "";
        }
    }
}
