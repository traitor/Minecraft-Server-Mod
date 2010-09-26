
public interface Plugin {
    public void enable();
    public void disable();
    public String onLoginChecks(String user);
    public void onLogin(Player player);
    public boolean onChat(Player player, String message);
    public boolean onCommand(Player player, String[] split);
    public void onBan(Player player, String reason);
    public void onIpBan(Player player, String reason);
    public void onKick(Player player, String reason);
    public boolean onBlockCreate(Player player, Block block, int itemId);
    public boolean onBlockDestroy(Player player, Block block);
}
