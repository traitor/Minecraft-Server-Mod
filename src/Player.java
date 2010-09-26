//Interface for ea - so mods don't have to update often.

public class Player {

    private ea user;

    public Player(ea user) {
        this.user = user;
    }

    public void kick(String reason) {
        user.a.c(reason);
    }

    public void sendMessage(String message) {
        user.a.msg(message);
    }

    public void giveItem(Item item) {
        giveItem(item.getItemId(), item.getAmount());
    }

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

    public void teleportTo(Player player) {
        user.a.a(player.getX(), player.getY(), player.getZ(), player.getRotation(), player.getPitch());
    }

    public void teleportTo(Location location) {
        user.a.a(location.x, location.y, location.z, location.rotX, location.rotY);
    }

    public ea getMCUser() {
        return user;
    }

    public User getUser() {
        return etc.getInstance().getUser(user.aq);
    }

    public String getName() {
        return user.aq;
    }

    public Location getLocation() {
        Location loc = new Location();
        loc.x = getX();
        loc.y = getY();
        loc.z = getZ();
        loc.rotX = getRotation();
        loc.rotY = getPitch();
        return loc;
    }

    public double getX() {
        return user.l;
    }

    public void setX(double x) {
        user.a.a(x, getY(), getZ(), getRotation(), getPitch());
    }

    public double getY() {
        return user.m;
    }

    public void setY(double y) {
        user.a.a(getX(), y, getZ(), getRotation(), getPitch());
    }

    public double getZ() {
        return user.n;
    }

    public void setZ(double z) {
        user.a.a(getX(), getY(), z, getRotation(), getPitch());
    }

    public float getPitch() {
        return user.s;
    }

    public void setPitch(float pitch) {
        user.a.a(getX(), getY(), getZ(), getRotation(), pitch);
    }

    public float getRotation() {
        return user.r;
    }

    public void setRotation(float rotation) {
        user.a.a(getX(), getY(), getZ(), rotation, getPitch());
    }
}
