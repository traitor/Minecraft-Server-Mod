
public class BanSystem {
    class Ban {
        public String Name, Ip, Reason;
        public int Timestamp = -1;
    }

    public BanSystem(DataSource source)
    {
        //Load bans.
        //source.loadBans(); etc.
    }

    public void fileBan(Player player) {

    }

    public void fileIpBan(Player player) {

    }

    public void fileTempBan(Player player, int minutes, int hours, int days) {
        //This is sort of crappy but I'm lazy.
        int timestamp = ((int) (System.currentTimeMillis() / 1000L));
        timestamp += minutes * 60;
        timestamp += hours * 60 * 60;
        timestamp += days * 60 * 60 * 24;
    }

    public boolean isBanned(String player, String ip) {
        return false;
    }

    public String getBanReason(String player, String ip) {
        return "";
    }
}
