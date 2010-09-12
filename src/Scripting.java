
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

public class Scripting {
    private static final Logger log = Logger.getLogger("Minecraft");
    private static Map<dy, Scripting> instances = new HashMap<dy, Scripting>();
    private Map<String, Script> scripts = new HashMap<String, Script>();
    private ScriptEngineManager manager;
    private dy e;
    private Player player;

    public static Scripting getInstance(dy e) {
        if (!instances.containsKey(e)) {
            instances.put(e, new Scripting(e));
        }
        return instances.get(e);
    }

    public Scripting(dy e) {
        this.e = e;
        player = new Player(e);

        initialize();
    }

    private void initialize() {
        manager = new ScriptEngineManager();
    }

    private Script getScript(String name) {
        if (!scripts.containsKey(name)) {
            ScriptEngine engine = manager.getEngineByName("js");
            try {
                FileReader fr = new FileReader(name);
                engine.eval(fr);
                fr.close();
            } catch (Exception ex) {
                log.log(Level.WARNING, "Caught exception reading file " + name, ex);
            }
            Invocable iv = (Invocable) engine;
            engine.put("player", player);
            engine.put("log", log);
            scripts.put(name, iv.getInterface(Script.class));
        }
        return scripts.get(name);
    }

    public boolean login() {
        return getScript("everything.js").onLogin();
    }

    public boolean chat(String msg, String[] split) {
        return getScript("everything.js").onChat(msg, split);
    }
}
