
public interface Plugin {
    public void initialize();
    public boolean onLoginChecks(String user);
    public void onLogin(ea user);
    public void onChat(ea user, String message);
    public boolean onCommand(ea user, String[] commands);
    public void onBan(ea user, String reason);
    public void onIpBan(ea user, String reason);
    public void onKick(ea user, String reason);

    /*public void onBlockCreate(int id);
    public void onBlockDestroy();*/
}
