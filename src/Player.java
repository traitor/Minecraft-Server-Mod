//Interface for ea - so mods don't have to update often.

/**
 *
 * @author James
 */
public class Player {

    private ea user;

    /**
     * Creates a player interface
     * @param user
     */
    public Player(ea user) {
        this.user = user;
    }

    /**
     * Kicks player with the specified reason
     * @param reason
     */
    public void kick(String reason) {
        user.a.c(reason);
    }

    /**
     * Sends a message to the player
     * @param message
     */
    public void sendMessage(String message) {
        user.a.msg(message);
    }

    /**
     * Gives an item to the player
     * @param item
     */
    public void giveItem(Item item) {
        giveItem(item.getItemId(), item.getAmount());
    }

    /**
     * Gives an item to the player
     * @param itemId
     * @param amount
     */
    public void giveItem(int itemId, int amount) {
        int temp = amount;

        do {
            if (temp - 64 >= 64) {
                user.a(new gp(itemId, 64));
            } else {
                user.a(new gp(itemId, temp));
            }
            temp -= 64;
        } while (temp >= 64);
    }

    /**
     * Teleports to the other player
     * @param player
     */
    public void teleportTo(Player player) {
        user.a.a(player.getX(), player.getY(), player.getZ(), player.getRotation(), player.getPitch());
    }

    /**
     * Teleports to the provided location
     * @param location
     */
    public void teleportTo(Location location) {
        user.a.a(location.x, location.y, location.z, location.rotX, location.rotY);
    }

    /**
     * Returns the actual player
     * @return
     */
    public ea getMCUser() {
        return user;
    }

    /**
     * Returns the user
     * @return
     */
    public User getUser() {
        return etc.getInstance().getUser(user.aq);
    }

    /**
     * Returns the player's name
     * @return
     */
    public String getName() {
        return user.aq;
    }

    /**
     * Return's the player's current location
     * @return
     */
    public Location getLocation() {
        Location loc = new Location();
        loc.x = getX();
        loc.y = getY();
        loc.z = getZ();
        loc.rotX = getRotation();
        loc.rotY = getPitch();
        return loc;
    }

    /**
     * Returns the player's X
     * @return
     */
    public double getX() {
        return user.l;
    }

    /**
     * Sets the player's X
     * @param x
     */
    public void setX(double x) {
        user.a.a(x, getY(), getZ(), getRotation(), getPitch());
    }

    /**
     * Returns the player's Y
     * @return
     */
    public double getY() {
        return user.m;
    }

    /**
     * Sets the player's Y
     * @param y
     */
    public void setY(double y) {
        user.a.a(getX(), y, getZ(), getRotation(), getPitch());
    }

    /**
     * Returns the player's Z
     * @return
     */
    public double getZ() {
        return user.n;
    }

    /**
     * Sets the player's Z
     * @param z
     */
    public void setZ(double z) {
        user.a.a(getX(), getY(), z, getRotation(), getPitch());
    }

    /**
     * Returns the player's pitch
     * @return
     */
    public float getPitch() {
        return user.s;
    }

    /**
     * Sets the player's pitch
     * @param pitch
     */
    public void setPitch(float pitch) {
        user.a.a(getX(), getY(), getZ(), getRotation(), pitch);
    }

    /**
     * Returns the player's rotation
     * @return
     */
    public float getRotation() {
        return user.r;
    }

    /**
     * Sets the player's rotation
     * @param rotation
     */
    public void setRotation(float rotation) {
        user.a.a(getX(), getY(), getZ(), rotation, getPitch());
    }
}
