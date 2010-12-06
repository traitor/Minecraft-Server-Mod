import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.server.MinecraftServer;

/**
 * Singleton class for dealing with the chat.
 * @author lightweight
 *
 */
public final class ChatParser {    
    private static Pattern badChatPattern = Pattern.compile("[^ !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ\\[\\\\\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u2302\u00C7\u00FC\u00E9\u00E2\u00E4\u00E0\u00E5\u00E7\u00EA\u00EB\u00E8\u00EF\u00EE\u00EC\u00C4\u00C5\u00C9\u00E6\u00C6\u00F4\u00F6\u00F2\u00FB\u00F9\u00FF\u00D6\u00DC\u00F8\u00A3\u00D8\u00D7\u0192\u00E1\u00ED\u00F3\u00FA\u00F1\u00D1\u00AA\u00BA\u00BF\u00AE\u00AC\u00BD\u00BC\u00A1\u00AB\u00BB]");

    private ChatParser() {}
    
    public static Matcher scanForBadCharacters(String message) {
        Matcher m = badChatPattern.matcher(message);
        if (m.find()) { 
            return m;
        }
        
        return null;
    }
    
    public static boolean isCommand(String message) {
        return (message.startsWith("/"));
    }
    
    public static boolean isMessageTooLong(String message) {
        return (message.length() > 100);
    }

}
