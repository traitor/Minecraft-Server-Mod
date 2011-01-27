/**
 * BanSystem.java - Handles all ban related things.
 * 
 * @author James
 */
public class BanSystem {

    /**
     * Files a regular name-only ban.
     * 
     * @param player
     *            player to ban
     */
    public void fileBan(Player player) {
    }

    /**
     * Files an IP address ban
     * 
     * @param player
     *            player to IP ban
     */
    public void fileIpBan(Player player) {
    }

    /**
     * Files a temporary ban (both name and IP bans)
     * 
     * @param player
     *            player to ban
     * @param minutes
     *            length in minutes
     * @param hours
     *            length in hours
     * @param days
     *            length in days
     */
    public void fileTempBan(Player player, int minutes, int hours, int days) {
        // This is sort of crappy but I'm lazy.
        int timestamp = ((int) (System.currentTimeMillis() / 1000L));
        timestamp += minutes * 60;
        timestamp += hours * 60 * 60;
        timestamp += days * 60 * 60 * 24;
    }
}
