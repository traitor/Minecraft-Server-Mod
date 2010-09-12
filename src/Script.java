
public interface Script {
    public void initialize(Properties props);

    public boolean onLogin();

    public boolean onChat(String msg, String[] split);

    public void onKick(String reason);

    public void onBan(String reason);

    public void onQuit(String reason); //Quits DO have a reason!

    public boolean onBuild(Block block);

    public boolean onDestroy();
}
