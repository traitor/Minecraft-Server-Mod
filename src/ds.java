import java.io.*;
import java.net.URL;

class ds extends Thread {

    ds(fr fr1, ad ad1) {
        super();
        b = fr1;
        a = ad1;
    }

    public void run() {
        try {
            String serverID = fr.a(b);
            boolean res = (Boolean)etc.getLoader().callHook(PluginLoader.Hook.NAME_VERIFICATION, new Object[] { a.b, serverID, b.addr });
            if (res) {
                fr.a(b, a);
                return;
            }
            URL url = new URL("http://www.minecraft.net/game/checkserver.jsp?user=" + a.b + "&serverId=" + serverID);
            BufferedReader reader = new BufferedReader(new InputStreamReader(url.openStream()));
            String reply = reader.readLine();
            reader.close();
            System.out.println("THE REPLY IS " + reply);
            if (reply.equals("YES")) {
                fr.a(b, a);
            } else {
                b.b("Failed to verify username!");
            }
        }
        catch(Exception exception) {
            exception.printStackTrace();
        }
    }

    final ad a;
    final fr b;
}
