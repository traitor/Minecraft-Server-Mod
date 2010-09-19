/* Interface so we can either use MySQL or flat files */

import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import net.minecraft.server.MinecraftServer;

public abstract class DataSource {

    protected static final Logger log = Logger.getLogger("Minecraft");
    protected List<User> users;
    protected List<String> reserveList;
    protected List<String> whiteList;
    protected List<Group> groups;
    protected List<Kit> kits;
    protected List<Warp> homes;
    protected List<Warp> warps;
    protected Map<String, Integer> items;
    protected MinecraftServer server;
    protected final Object userLock = new Object(), groupLock = new Object(), kitLock = new Object();
    protected final Object homeLock = new Object(), warpLock = new Object(), itemLock = new Object();
    protected final Object whiteListLock = new Object(), reserveListLock = new Object();

    abstract public void initialize();

    abstract public void loadUsers();

    abstract public void loadGroups();

    abstract public void loadKits();

    abstract public void loadHomes();

    abstract public void loadWarps();

    abstract public void loadItems();

    abstract public void loadWhitelist();

    abstract public void loadReserveList();

    //abstract public void loadBanList();

    abstract public void addUser(User user);

    abstract public void modifyUser(User user);

    public User getUser(String name) {
        synchronized (userLock) {
            for (User user : users) {
                if (user.Name.equalsIgnoreCase(name)) {
                    return user;
                }
            }
        }
        return null;
    }

    abstract public void addGroup(Group group);

    abstract public void modifyGroup(Group group);

    public Group getGroup(String name) {
        synchronized (groupLock) {
            for (Group group : groups) {
                if (group.Name.equalsIgnoreCase(name)) {
                    return group;
                }
            }
        }

        if (!name.equals("")) {
            log.log(Level.INFO, "Unable to find group '" + name + "'. Are you sure you have that group?");
        }

        return null;
    }

    public Group getDefaultGroup() {
        synchronized (groupLock) {
            for (Group group : groups) {
                if (group.DefaultGroup) {
                    return group;
                }
            }
        }
        return null;
    }

    abstract public void addKit(Kit kit);

    abstract public void modifyKit(Kit kit);

    public Kit getKit(String name) {
        synchronized (kitLock) {
            for (Kit kit : kits) {
                if (kit.Name.equalsIgnoreCase(name)) {
                    return kit;
                }
            }
        }
        return null;
    }

    public boolean hasKits() {
        synchronized (kitLock) {
            return kits.size() > 0;
        }
    }

    public String getKitNames(String player) {
        StringBuilder builder = new StringBuilder();
        builder.append(""); //incaseofnull

        synchronized (kitLock) {
            for (Kit kit : kits) {
                if (etc.getInstance().isUserInGroup(player, kit.Group) || kit.Group.equals("")) {
                    builder.append(kit.Name).append(" ");
                }
            }
        }

        return builder.toString();
    }

    abstract public void addHome(Warp home);

    abstract public void changeHome(Warp home);

    public Warp getHome(String name) {
        synchronized (homeLock) {
            for (Warp home : homes) {
                if (home.Name.equalsIgnoreCase(name)) {
                    return home;
                }
            }
        }
        return null;
    }

    abstract public void addWarp(Warp warp);

    abstract public void changeWarp(Warp warp);

    abstract public void removeWarp(Warp warp);

    public Warp getWarp(String name) {
        synchronized (warpLock) {
            for (Warp warp : warps) {
                if (warp.Name.equalsIgnoreCase(name)) {
                    return warp;
                }
            }
        }
        return null;
    }

    public boolean hasWarps() {
        synchronized (warpLock) {
            return warps.size() > 0;
        }
    }

    public String getWarpNames(String player) {
        StringBuilder builder = new StringBuilder();
        builder.append(""); //incaseofnull

        synchronized (warpLock) {
            for (Warp warp : warps) {
                if (etc.getInstance().isUserInGroup(player, warp.Group) || warp.Group.equals("")) {
                    builder.append(warp.Name).append(" ");
                }
            }
        }

        return builder.toString();
    }

    public int getItem(String name) {
        synchronized (itemLock) {
            if (items.containsKey(name)) {
                return items.get(name);
            }
        }
        return 0;
    }

    abstract public void addToWhitelist(String name);

    abstract public void removeFromWhitelist(String name);

    public boolean hasWhitelist() {
        synchronized (whiteListLock) {
            return !whiteList.isEmpty();
        }
    }

    public boolean isUserOnWhitelist(String user) {
        synchronized (whiteListLock) {
            for (String name : whiteList) {
                if (name.equalsIgnoreCase(user)) {
                    return true;
                }
            }
        }
        return false;
    }

    abstract public void addToReserveList(String name);

    abstract public void removeFromReserveList(String name);

    public boolean hasReserveList() {
        synchronized (reserveList) {
            return !reserveList.isEmpty();
        }
    }

    public boolean isUserOnReserveList(String user) {
        synchronized (reserveList) {
            for (String name : reserveList) {
                if (name.equalsIgnoreCase(user)) {
                    return true;
                }
            }
        }
        return false;
    }
}
