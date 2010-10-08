
/**
 * Player.java - Interface for ea so mods don't have to update often.
 * @author James
 */
public class Player {

    private ea user;
    private int id = -1;
    private String name = "";
    private String prefix = "";
    private String[] commands = new String[] { "" };
    private String[] groups = new String[] { "" };
    private String[] ips = new String[] { "" };
    private boolean ignoreRestrictions = false;
    private boolean admin = false;
    private boolean canModifyWorld = false;
    private boolean muted = false;

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
     * Returns true if this player can use the specified command
     * @param command
     * @return
     */
    public boolean canUseCommand(String command) {
        for (String str : commands) {
            if (str.equalsIgnoreCase(command)) {
                return true;
            }
        }

        for (String str : groups) {
            Group g = etc.getDataSource().getGroup(str);
            if (g != null) {
                if (recursiveUseCommand(g, command)) {
                    return true;
                }
            }
        }

        if (hasNoGroups()) {
            Group def = etc.getInstance().getDefaultGroup();
            if (def != null) {
                if (recursiveUseCommand(def, command)) {
                    return true;
                }
            }
        }

        return false;
    }

    private boolean recursiveUseCommand(Group g, String command) {
        for (String str : g.Commands) {
            if (str.equalsIgnoreCase(command) || str.equals("*")) {
                return true;
            }
        }

        if (g.InheritedGroups != null) {
            for (String str : g.InheritedGroups) {
                Group g2 = etc.getDataSource().getGroup(str);
                if (g2 != null) {
                    if (recursiveUseCommand(g2, command)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Checks to see if this player is in the specified group
     * @param group
     * @return
     */
    public boolean isInGroup(String group) {
        if (group != null) {
            if (etc.getInstance().getDefaultGroup() != null) {
                if (group.equalsIgnoreCase(etc.getInstance().getDefaultGroup().Name)) {
                    return true;
                }
            }
        }
        for (String str : groups) {
            if (recursiveUserInGroup(etc.getDataSource().getGroup(str), group)) {
                return true;
            }
        }
        return false;
    }

    private boolean recursiveUserInGroup(Group g, String group) {
        if (g == null || group == null) {
            return false;
        }

        if (g.Name.equalsIgnoreCase(group)) {
            return true;
        }

        if (g.InheritedGroups != null) {
            for (String str : g.InheritedGroups) {
                if (g.Name.equalsIgnoreCase(str)) {
                    return true;
                }

                Group g2 = etc.getDataSource().getGroup(str);
                if (g2 != null) {
                    if (recursiveUserInGroup(g2, group)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    /**
     * Returns true if this player has control over the other player
     * @param player
     * @return true if player has control
     */
    public boolean hasControlOver(Player player) {
        boolean isInGroup = false;

        if (player.hasNoGroups())
            return true;
        for (String str : player.getGroups()) {
            if (isInGroup(str))
                isInGroup = true;
            else
                continue;
            break;
        }

        return isInGroup;
    }

    /**
     * Returns the player's name
     * @return
     */
    public String getName() {
        return user.aq;
    }

    /**
     * Returns the player's current location
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
     * Returns the IP of this player
     * @return
     */
    public String getIP() {
        return user.a.b.b().toString().split(":")[0].substring(1);
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

    /**
     * Returns true if this player is an admin.
     * @return
     */
    public boolean isAdmin() {
        if (admin) {
            return true;
        }

        for (String str : groups) {
            Group group = etc.getDataSource().getGroup(str);
            if (group != null) {
                if (group.Administrator) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Don't use this! Use isAdmin
     * @return
     */
    public boolean getAdmin() {
        return admin;
    }

    /**
     * Sets whether or not this player is an administrator
     * @param admin
     */
    public void setAdmin(boolean admin) {
        this.admin = admin;
    }

    /**
     * Returns false if this player can not modify terrain, edit chests, etc.
     * @return
     */
    public boolean canBuild() {
        if (canModifyWorld) {
            return true;
        }

        for (String str : groups) {
            Group group = etc.getDataSource().getGroup(str);
            if (group != null) {
                if (group.CanModifyWorld) {
                    return true;
                }
            }
        }

        if (hasNoGroups())
            if (etc.getInstance().getDefaultGroup().CanModifyWorld)
                return true;

        return false;
    }

    /**
     * Don't use this, use canBuild()
     * @return
     */
    public boolean canModifyWorld() {
        return canModifyWorld;
    }

    /**
     * Sets whether or not this player can modify the world terrain
     * @param canModifyWorld
     */
    public void setCanModifyWorld(boolean canModifyWorld) {
        this.canModifyWorld = canModifyWorld;
    }

    /**
     * Set allowed commands
     * @return
     */
    public String[] getCommands() {
        return commands;
    }

    /**
     * Sets this player's allowed commands
     * @param commands
     */
    public void setCommands(String[] commands) {
        this.commands = commands;
    }

    /**
     * Returns this player's groups
     * @return
     */
    public String[] getGroups() {
        return groups;
    }

    /**
     * Sets this player's groups
     * @param groups
     */
    public void setGroups(String[] groups) {
        this.groups = groups;
    }

    /**
     * Returns the sql ID.
     * @return
     */
    public int getSqlId() {
        return id;
    }

    /**
     * Sets the sql ID. Don't touch this.
     * @param id
     */
    public void setSqlId(int id) {
        this.id = id;
    }

    /**
     * If the user can ignore restrictions this will return true. Things like
     * item amounts and such are unlimited, etc.
     * @return
     */
    public boolean canIgnoreRestrictions() {
        if (admin || ignoreRestrictions) {
            return true;
        }

        for (String str : groups) {
            Group group = etc.getDataSource().getGroup(str);
            if (group != null) {
                if (group.Administrator || group.IgnoreRestrictions) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Don't use. Use canIgnoreRestrictions()
     * @return
     */
    public boolean ignoreRestrictions() {
        return ignoreRestrictions;
    }

    /**
     * Sets ignore restrictions
     * @param ignoreRestrictions
     */
    public void setIgnoreRestrictions(boolean ignoreRestrictions) {
        this.ignoreRestrictions = ignoreRestrictions;
    }

    /**
     * Returns allowed IPs
     * @return
     */
    public String[] getIps() {
        return ips;
    }

    /**
     * Sets allowed IPs
     * @param ips
     */
    public void setIps(String[] ips) {
        this.ips = ips;
    }

    /**
     * Returns the correct color/prefix for this player
     * @return
     */
    public String getColor() {
        if (prefix != null) {
            if (!prefix.equals("")) {
                return "§" + prefix;
            }
        }
        if(groups.length > 0)
        {
            Group group = etc.getDataSource().getGroup(groups[0]);
            if (group != null) {
                return "§" + group.Prefix;
            }
        }
        Group def = etc.getInstance().getDefaultGroup();
        return def != null ? "§" + def.Prefix : "";
    }

    /**
     * Returns the prefix. NOTE: Don't use this, use getColor() instead.
     * @return
     */
    public String getPrefix() {
        return prefix;
    }

    /**
     * Sets the prefix
     * @param prefix
     */
    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    /**
     * Gets the actual user class.
     * @return
     */
    public ea getUser() {
        return user;
    }

    /**
     * Sets the user. Don't use this.
     * @param user
     */
    public void setUser(ea user) {
        this.user = user;
    }

    /**
     * Returns true if the player is muted
     * @return
     */
    public boolean isMuted() {
        return muted;
    }

    /**
     * Toggles mute
     * @return
     */
    public boolean toggleMute() {
        muted = !muted;
        return muted;
    }

    public boolean hasNoGroups() {
        if (groups.length == 0)
            return true;
        if (groups.length == 1)
            return groups[0].equals("");
        return false;
    }
}
