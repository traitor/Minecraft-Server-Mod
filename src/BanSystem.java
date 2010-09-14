
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

    public void fileBan(String player) {

    }

    public void fileIpBan(String ip) {

    }

    public void fileTempBan(String player, int minutes, int hours, int months) {
        //This is sort of crappy but I'm lazy.
        int timestamp = ((int) (System.currentTimeMillis() / 1000L));
        timestamp += minutes * 60;
        timestamp += hours * 60 * 60;
        timestamp += months * 60 * 60 * 24 * 30;
    }

    public boolean isBanned(String player, String ip) {
        return false;
    }

    public String getBanReason(String player, String ip) {
        return "";
    }
}
