/**
 * Contains methods common to all types of chat commands.
 * @author lightweight
 *
 */
public abstract class BaseCommand {
        public final String tooltip;
        public final String errorMessage;
        public final int minParam;
        public final int maxParam;
        
        public BaseCommand(String tooltip) {
            this(tooltip, "", 0);
        }
        
        public BaseCommand(String tooltip, String errorMessage, int minParam) {
           this(tooltip, errorMessage, minParam, 0);
        }
        
        public BaseCommand(String tooltip, String errorMessage, int minParam, int maxParam) {
            this.tooltip = tooltip;
            this.errorMessage = errorMessage;
            this.minParam = minParam;
            this.maxParam = maxParam;
        }
        
        public boolean parseCommand(Player player, String[] parameters) {
            if (parameters.length < minParam || (parameters.length > maxParam && maxParam != 0)) {
                onError(player, parameters);
                return false;
            }
            execute(player, parameters);
            return true;
        }
        
        public void onError(Player player, String[] parameters) {
            player.sendMessage(Colors.Rose + errorMessage);
        }
        
        /**
         * Executes a command. 
         * Note: should not be called directly. Use parseCommand() instead! 
         * @param player
         * @param parameters
         */
        abstract void execute(Player player, String[] parameters);
    }
