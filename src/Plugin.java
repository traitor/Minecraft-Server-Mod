
public abstract class Plugin {
    private String name = "";
    private boolean enabled = true;

    public abstract void enable();
    public abstract void disable();
    
    public boolean isEnabled() { return enabled; }
    public boolean toggleEnabled() { enabled = !enabled; return enabled; }

    public void setName(String name) { this.name = name; }
    public String getName() { return name; }

    public String onLoginChecks(String user) { return null; }
    public void onLogin(Player player) { }
    public void onDisconnect(Player player) { }
    public boolean onChat(Player player, String message) { return false; }
    public boolean onCommand(Player player, String[] split) { return false; }
    public void onBan(Player player, String reason) { }
    public void onIpBan(Player player, String reason) { }
    public void onKick(Player player, String reason) { }
    public boolean onBlockCreate(Player player, Block blockPlaced, Block blockClicked, int itemInHand) { return false; }
    public boolean onBlockDestroy(Player player, Block block) { return false; }
}
