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
    protected Map<String, Location> homes;
    protected Map<String, Location> warps;
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

    abstract public void addHome(String name, Location location);

    abstract public void changeHome(String name, Location location);

    public Location getHome(String name) {
        synchronized (homeLock) {
            if (homes.containsKey(name)) {
                return homes.get(name);
            }
        }
        return null;
    }

    abstract public void addWarp(String name, Location location);

    abstract public void changeWarp(String name, Location location);

    public Location getWarp(String name) {
        synchronized (warpLock) {
            if (warps.containsKey(name)) {
                return warps.get(name);
            }
        }
        return null;
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
        synchronized (whiteListLock) {
            return !whiteList.isEmpty();
        }
    }

    public boolean isUserOnReserveList(String user) {
        synchronized (reserveList) {
            for (String name : whiteList) {
                if (name.equalsIgnoreCase(user)) {
                    return true;
                }
            }
        }
        return false;
    }
}
