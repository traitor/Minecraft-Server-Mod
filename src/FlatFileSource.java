import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.logging.Level;

/**
 * FlatFileSource.java - Accessing users, groups and such from flat files.
 * 
 * @author James
 */
public class FlatFileSource extends DataSource {

    public void initialize() {
        loadGroups();
        loadKits();
        loadHomes();
        loadWarps();
        loadItems();
        // loadBanList();

        String location = etc.getInstance().getUsersLocation();
        if (!new File(location).exists()) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(location);
                writer.write("#Add your users here (When adding your entry DO NOT include #!)\r\n");
                writer.write("#The format is:\r\n");
                writer.write("#NAME:GROUPS:ADMIN/UNRESTRICTED:COLOR:COMMANDS:IPs\r\n");
                writer.write("#For administrative powers set admin/unrestricted to 2.\r\n");
                writer.write("#For no restrictions and ability to give out items set it to 1.\r\n");
                writer.write("#If you don't want the person to be able to build set it to -1.\r\n");
                writer.write("#Admin/unrestricted, color and commands are optional.\r\n");
                writer.write("#Examples:\r\n");
                writer.write("#Adminfoo:admins\r\n");
                writer.write("#Moderator39:mods:1:0:/unban\r\n");
                writer.write("#BobTheBuilder:vip:0:d\r\n");
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while creating " + location, e);
            } finally {
                try {
                    if (writer != null)
                        writer.close();
                } catch (IOException e) {
                    log.log(Level.SEVERE, "Exception while closing writer for " + location, e);
                }
            }
        }
        location = etc.getInstance().getWhitelistLocation();
        if (!new File(location).exists()) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(location);
                writer.write("#Whitelist. Add your users here\r\n");
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while creating " + location, e);
            } finally {
                try {
                    if (writer != null)
                        writer.close();
                } catch (IOException e) {
                    log.log(Level.SEVERE, "Exception while closing writer for " + location, e);
                }
            }
        }
    }

    public void loadGroups() {
        String location = etc.getInstance().getGroupLocation();

        if (!new File(location).exists()) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(location);
                writer.write("#Add your groups here (When adding your entry DO NOT include #!)\r\n");
                writer.write("#The format is:\r\n");
                writer.write("#NAME:COLOR:COMMANDS:INHERITEDGROUPS:ADMIN/UNRESTRICTED\r\n");
                writer.write("#For administrative powers set admin/unrestricted to 2.\r\n");
                writer.write("#For no restrictions and ability to give out items set it to 1.\r\n");
                writer.write("#If you don't want the group to be able to build set it to -1.\r\n");
                writer.write("#Inherited groups and admin/unrestricted are optional.\r\n");
                writer.write("#Examples:\r\n");
                writer.write("admins:c:*:mods:2\r\n");
                writer.write("mods:b:/ban,/kick,/item,/tp,/tphere,/s,/i,/give:vip:1\r\n");
                writer.write("vip:a::default\r\n");
                writer.write("default:f:/help,/sethome,/home,/spawn,/me,/msg,/kit,/playerlist,/warp,/motd,/compass,/tell,/m,/who:default\r\n");
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while creating " + location, e);
            } finally {
                try {
                    if (writer != null)
                        writer.close();
                } catch (IOException e) {
                    log.log(Level.SEVERE, "Exception while closing writer for " + location, e);
                }
            }
        }

        synchronized (groupLock) {
            groups = new ArrayList<Group>();
            try {
                Scanner scanner = new Scanner(new File(location));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("#") || line.equals("") || line.startsWith("﻿"))
                        continue;

                    String[] split = line.split(":");
                    Group group = new Group();
                    group.Name = split[0];
                    group.Prefix = split[1];
                    group.Commands = split[2].split(",");
                    if (split.length >= 4)
                        group.InheritedGroups = split[3].split(",");
                    if (split.length >= 5)
                        if (split[4].equals("1"))
                            group.IgnoreRestrictions = true;
                        else if (split[4].equals("2"))
                            group.Administrator = true;
                        else if (split[4].equals("-1"))
                            group.CanModifyWorld = false;

                    // kind of a shitty way, but whatever.
                    if (group.InheritedGroups != null)
                        if (group.InheritedGroups[0].equalsIgnoreCase(group.Name)) {
                            group.InheritedGroups = new String[] { "" };
                            group.DefaultGroup = true;
                        }

                    groups.add(group);
                }
                scanner.close();
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while reading " + location + " (Are you sure you formatted it correctly?)", e);
            }
        }
    }

    public void loadKits() {
        String location = etc.getInstance().getKitsLocation();

        if (!new File(location).exists()) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(location);
                writer.write("#Add your kits here. Example entry below (When adding your entry DO NOT include #!)\r\n");
                writer.write("#miningbasics:1,2,3,4:6000\r\n");
                writer.write("#The formats are (Find out more about groups in " + etc.getInstance().getUsersLocation() + ":\r\n");
                writer.write("#NAME:IDs:DELAY\r\n");
                writer.write("#NAME:IDs:DELAY:GROUP\r\n");
                writer.write("#6000 for delay is roughly 5 minutes.\r\n");
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while creating " + location, e);
            } finally {
                try {
                    if (writer != null)
                        writer.close();
                } catch (IOException e) {
                }
            }
        }

        synchronized (kitLock) {
            kits = new ArrayList<Kit>();
            try {
                Scanner scanner = new Scanner(new File(location));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("#") || line.equals(""))
                        continue;
                    String[] split = line.split(":");
                    String name = split[0];
                    String[] ids = split[1].split(",");
                    int delay = Integer.parseInt(split[2]);
                    String group = "";
                    if (split.length == 4)
                        group = split[3];
                    Kit kit = new Kit();
                    kit.Name = name;
                    kit.IDs = new HashMap<String, Integer>();
                    for (String str : ids) {
                        String id = "";
                        int amount = 1;
                        if (str.contains(" ")) {
                            id = str.split(" ")[0];
                            amount = Integer.parseInt(str.split(" ")[1]);
                        } else
                            id = str;
                        kit.IDs.put(id, amount);
                    }
                    kit.Delay = delay;
                    kit.Group = group;
                    kits.add(kit);
                }
                scanner.close();
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while reading " + location, e);
            }
        }
    }

    public void loadHomes() {
        synchronized (homeLock) {
            homes = new ArrayList<Warp>();
            if (!etc.getInstance().canSaveHomes())
                return;

            String location = etc.getInstance().getHomeLocation();
            if (new File(location).exists())
                try {
                    Scanner scanner = new Scanner(new File(location));
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.startsWith("#") || line.equals(""))
                            continue;
                        String[] split = line.split(":");
                        if (split.length < 4)
                            continue;
                        Location loc = new Location();
                        loc.x = Double.parseDouble(split[1]);
                        loc.y = Double.parseDouble(split[2]);
                        loc.z = Double.parseDouble(split[3]);
                        if (split.length >= 6) {
                            loc.rotX = Float.parseFloat(split[4]);
                            loc.rotY = Float.parseFloat(split[5]);
                        }
                        Warp home = new Warp();
                        home.Name = split[0];
                        home.Location = loc;
                        if (split.length >= 7)
                            home.Group = split[6];
                        else
                            home.Group = "";
                        homes.add(home);
                    }
                    scanner.close();
                } catch (Exception e) {
                    log.log(Level.SEVERE, "Exception while reading " + location, e);
                }
        }
    }

    public void loadWarps() {
        synchronized (warpLock) {
            warps = new ArrayList<Warp>();
            String location = etc.getInstance().getWarpLocation();

            if (new File(location).exists())
                try {
                    Scanner scanner = new Scanner(new File(location));
                    while (scanner.hasNextLine()) {
                        String line = scanner.nextLine();
                        if (line.startsWith("#") || line.equals(""))
                            continue;
                        String[] split = line.split(":");
                        if (split.length < 4)
                            continue;

                        Location loc = new Location();
                        loc.x = Double.parseDouble(split[1]);
                        loc.y = Double.parseDouble(split[2]);
                        loc.z = Double.parseDouble(split[3]);
                        if (split.length == 6) {
                            loc.rotX = Float.parseFloat(split[4]);
                            loc.rotY = Float.parseFloat(split[5]);
                        }
                        Warp warp = new Warp();
                        warp.Name = split[0];
                        warp.Location = loc;
                        if (split.length >= 7)
                            warp.Group = split[6];
                        else
                            warp.Group = "";
                        warps.add(warp);
                    }
                    scanner.close();
                } catch (Exception e) {
                    log.log(Level.SEVERE, "Exception while reading " + location, e);
                }
        }
    }

    public void loadItems() {
        String location = etc.getInstance().getItemLocation();

        if (!(new File(location).exists())) {
            FileWriter writer = null;
            try {
                writer = new FileWriter(location);
                writer.write("#Add your items in here (When adding your entry DO NOT include #!)\r\n");
                writer.write("#The format is:\r\n");
                writer.write("#NAME:ID\r\n");
                writer.write("#Default Items:\r\n");
                writer.write("air:0\r\n");
                writer.write("rock:1\r\n");
                writer.write("stone:1\r\n");
                writer.write("grass:2\r\n");
                writer.write("dirt:3\r\n");
                writer.write("cobblestone:4\r\n");
                writer.write("cobble:4\r\n");
                writer.write("wood:5\r\n");
                writer.write("sapling:6\r\n");
                writer.write("adminium:7\r\n");
                writer.write("bedrock:7\r\n");
                writer.write("water:8\r\n");
                writer.write("stillwater:9\r\n");
                writer.write("swater:9\r\n");
                writer.write("lava:10\r\n");
                writer.write("stilllava:11\r\n");
                writer.write("slava:11\r\n");
                writer.write("sand:12\r\n");
                writer.write("gravel:13\r\n");
                writer.write("goldore:14\r\n");
                writer.write("ironore:15\r\n");
                writer.write("coalore:16\r\n");
                writer.write("tree:17\r\n");
                writer.write("log:17\r\n");
                writer.write("leaves:18\r\n");
                writer.write("sponge:19\r\n");
                writer.write("glass:20\r\n");
                writer.write("cloth:35\r\n");
                writer.write("flower:37\r\n");
                writer.write("rose:38\r\n");
                writer.write("brownmushroom:39\r\n");
                writer.write("redmushroom:40\r\n");
                writer.write("gold:41\r\n");
                writer.write("goldblock:41\r\n");
                writer.write("iron:42\r\n");
                writer.write("ironblock:42\r\n");
                writer.write("doublestair:43\r\n");
                writer.write("stair:44\r\n");
                writer.write("step:44\r\n");
                writer.write("brickblock:45\r\n");
                writer.write("brickwall:45\r\n");
                writer.write("tnt:46\r\n");
                writer.write("bookshelf:47\r\n");
                writer.write("bookcase:47\r\n");
                writer.write("mossycobblestone:48\r\n");
                writer.write("mossy:48\r\n");
                writer.write("obsidian:49\r\n");
                writer.write("torch:50\r\n");
                writer.write("fire:51\r\n");
                writer.write("mobspawner:52\r\n");
                writer.write("woodstairs:53\r\n");
                writer.write("chest:54\r\n");
                writer.write("redstonedust:55\r\n");
                writer.write("redstonewire:55\r\n");
                writer.write("diamondore:56\r\n");
                writer.write("diamondblock:57\r\n");
                writer.write("workbench:58\r\n");
                writer.write("crop:59\r\n");
                writer.write("crops:59\r\n");
                writer.write("soil:60\r\n");
                writer.write("furnace:61\r\n");
                writer.write("litfurnace:62\r\n");
                writer.write("signblock:63\r\n");
                writer.write("wooddoorblock:64\r\n");
                writer.write("ladder:65\r\n");
                writer.write("rails:66\r\n");
                writer.write("rail:66\r\n");
                writer.write("track:66\r\n");
                writer.write("tracks:66\r\n");
                writer.write("cobblestonestairs:67\r\n");
                writer.write("stairs:67\r\n");
                writer.write("signblocktop:68\r\n");
                writer.write("wallsign:68\r\n");
                writer.write("lever:69\r\n");
                writer.write("rockplate:70\r\n");
                writer.write("stoneplate:70\r\n");
                writer.write("irondoorblock:71\r\n");
                writer.write("woodplate:72\r\n");
                writer.write("redstoneore:73\r\n");
                writer.write("redstoneorealt:74\r\n");
                writer.write("redstonetorchoff:75\r\n");
                writer.write("redstonetorchon:76\r\n");
                writer.write("button:77\r\n");
                writer.write("snow:78\r\n");
                writer.write("ice:79\r\n");
                writer.write("snowblock:80\r\n");
                writer.write("cactus:81\r\n");
                writer.write("clayblock:82\r\n");
                writer.write("reedblock:83\r\n");
                writer.write("jukebox:84\r\n");
                writer.write("fence:85\r\n");
                writer.write("pumpkin:86\r\n");
                writer.write("netherstone:87\r\n");
                writer.write("slowsand:88\r\n");
                writer.write("lightstone:89\r\n");
                writer.write("portal:90\r\n");
                writer.write("jackolantern:91\r\n");
                writer.write("jacko:91\r\n");
                writer.write("ironshovel:256\r\n");
                writer.write("ironspade:256\r\n");
                writer.write("ironpickaxe:257\r\n");
                writer.write("ironpick:257\r\n");
                writer.write("ironaxe:258\r\n");
                writer.write("flintandsteel:259\r\n");
                writer.write("lighter:259\r\n");
                writer.write("apple:260\r\n");
                writer.write("bow:261\r\n");
                writer.write("arrow:262\r\n");
                writer.write("coal:263\r\n");
                writer.write("diamond:264\r\n");
                writer.write("ironbar:265\r\n");
                writer.write("goldbar:266\r\n");
                writer.write("ironsword:267\r\n");
                writer.write("woodsword:268\r\n");
                writer.write("woodshovel:269\r\n");
                writer.write("woodspade:269\r\n");
                writer.write("woodpickaxe:270\r\n");
                writer.write("woodpick:270\r\n");
                writer.write("woodaxe:271\r\n");
                writer.write("stonesword:272\r\n");
                writer.write("stoneshovel:273\r\n");
                writer.write("stonespade:273\r\n");
                writer.write("stonepickaxe:274\r\n");
                writer.write("stonepick:274\r\n");
                writer.write("stoneaxe:275\r\n");
                writer.write("diamondsword:276\r\n");
                writer.write("diamondshovel:277\r\n");
                writer.write("diamondspade:277\r\n");
                writer.write("diamondpickaxe:278\r\n");
                writer.write("diamondpick:278\r\n");
                writer.write("diamondaxe:279\r\n");
                writer.write("stick:280\r\n");
                writer.write("bowl:281\r\n");
                writer.write("bowlwithsoup:282\r\n");
                writer.write("soupbowl:282\r\n");
                writer.write("soup:282\r\n");
                writer.write("goldsword:283\r\n");
                writer.write("goldshovel:284\r\n");
                writer.write("goldspade:284\r\n");
                writer.write("goldpickaxe:285\r\n");
                writer.write("goldpick:285\r\n");
                writer.write("goldaxe:286\r\n");
                writer.write("string:287\r\n");
                writer.write("feather:288\r\n");
                writer.write("gunpowder:289\r\n");
                writer.write("woodhoe:290\r\n");
                writer.write("stonehoe:291\r\n");
                writer.write("ironhoe:292\r\n");
                writer.write("diamondhoe:293\r\n");
                writer.write("goldhoe:294\r\n");
                writer.write("seeds:295\r\n");
                writer.write("wheat:296\r\n");
                writer.write("bread:297\r\n");
                writer.write("leatherhelmet:298\r\n");
                writer.write("leatherchestplate:299\r\n");
                writer.write("leatherpants:300\r\n");
                writer.write("leatherboots:301\r\n");
                writer.write("chainmailhelmet:302\r\n");
                writer.write("chainmailchestplate:303\r\n");
                writer.write("chainmailpants:304\r\n");
                writer.write("chainmailboots:305\r\n");
                writer.write("ironhelmet:306\r\n");
                writer.write("ironchestplate:307\r\n");
                writer.write("ironpants:308\r\n");
                writer.write("ironboots:309\r\n");
                writer.write("diamondhelmet:310\r\n");
                writer.write("diamondchestplate:311\r\n");
                writer.write("diamondpants:312\r\n");
                writer.write("diamondboots:313\r\n");
                writer.write("goldhelmet:314\r\n");
                writer.write("goldchestplate:315\r\n");
                writer.write("goldpants:316\r\n");
                writer.write("goldboots:317\r\n");
                writer.write("flint:318\r\n");
                writer.write("meat:319\r\n");
                writer.write("pork:319\r\n");
                writer.write("cookedmeat:320\r\n");
                writer.write("cookedpork:320\r\n");
                writer.write("painting:321\r\n");
                writer.write("paintings:321\r\n");
                writer.write("goldenapple:322\r\n");
                writer.write("sign:323\r\n");
                writer.write("wooddoor:324\r\n");
                writer.write("bucket:325\r\n");
                writer.write("waterbucket:326\r\n");
                writer.write("lavabucket:327\r\n");
                writer.write("minecart:328\r\n");
                writer.write("saddle:329\r\n");
                writer.write("irondoor:330\r\n");
                writer.write("redstonedust:331\r\n");
                writer.write("snowball:332\r\n");
                writer.write("boat:333\r\n");
                writer.write("leather:334\r\n");
                writer.write("milkbucket:335\r\n");
                writer.write("brick:336\r\n");
                writer.write("clay:337\r\n");
                writer.write("reed:338\r\n");
                writer.write("paper:339\r\n");
                writer.write("book:340\r\n");
                writer.write("slimeorb:341\r\n");
                writer.write("storageminecart:342\r\n");
                writer.write("poweredminecart:343\r\n");
                writer.write("egg:344\r\n");
                writer.write("compass:345\r\n");
                writer.write("fishingrod:346\r\n");
                writer.write("watch:347\r\n");
                writer.write("lightstonedust:348\r\n");
                writer.write("lightdust:348\r\n");
                writer.write("rawfish:349\r\n");
                writer.write("fish:349\r\n");
                writer.write("cookedfish:350\r\n");
                writer.write("goldrecord:2256\r\n");
                writer.write("greenrecord:2257\r\n");
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while creating " + location, e);
            } finally {
                if (writer != null)
                    try {
                        writer.close();
                    } catch (IOException e) {
                        log.log(Level.SEVERE, "Exception while closing writer for " + location, e);
                    }
            }
        }

        // This, for sure, now exists.
        synchronized (itemLock) {
            items = new HashMap<String, Integer>();
            try {
                Scanner scanner = new Scanner(new File(location));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("#"))
                        continue;
                    if (line.equals(""))
                        continue;
                    String[] split = line.split(":");
                    String name = split[0];

                    items.put(name, Integer.parseInt(split[1]));
                }
                scanner.close();
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while reading " + location + " (Are you sure you formatted it correctly?)", e);
            }
        }
    }

    public void loadBanList() {
        synchronized (banLock) {
            bans = new ArrayList<Ban>();

            try {
                Scanner scanner = new Scanner(new File("banned-players.txt"));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("#") || line.equals(""))
                        continue;
                    String[] split = line.split(":");
                    Ban ban = new Ban();
                    if (split.length >= 1)
                        ban.setName(split[0]);
                    if (split.length == 4) {
                        ban.setIp(split[1]);
                        ban.setReason(split[2]);
                        ban.setTimestamp(Integer.parseInt(split[3]));
                    }
                    bans.add(ban);
                }
                scanner.close();
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while reading banned-players.txt", e);
            }

            try {
                Scanner scanner = new Scanner(new File("banned-ips.txt"));
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    if (line.startsWith("#") || line.equals(""))
                        continue;
                    String[] split = line.split(":");

                    Ban ban = new Ban();
                    if (split.length >= 1)
                        ban.setIp(split[0]);
                    if (split.length == 4) {
                        ban.setName(split[1]);
                        ban.setReason(split[2]);
                        ban.setTimestamp(Integer.parseInt(split[3]));
                    }
                    bans.add(ban);
                }
                scanner.close();
            } catch (Exception e) {
                log.log(Level.SEVERE, "Exception while reading banned-ips.txt", e);
            }
        }
    }

    // Users
    public void addPlayer(Player player) {
        String loc = etc.getInstance().getUsersLocation();

        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(loc, true));
            StringBuilder builder = new StringBuilder();
            // #NAME:GROUPS:ADMIN/UNRESTRICTED:COLOR:COMMANDS
            builder.append(player.getName());
            builder.append(":");
            builder.append(etc.combineSplit(0, player.getGroups(), ","));
            builder.append(":");
            if (player.getAdmin())
                builder.append("2");
            else if (player.ignoreRestrictions())
                builder.append("1");
            else if (!player.canModifyWorld())
                builder.append("-1");
            else
                builder.append("0");
            builder.append(":");
            builder.append(player.getPrefix());
            builder.append(":");
            builder.append(etc.combineSplit(0, player.getCommands(), ","));
            bw.append(builder.toString());
            bw.newLine();
            bw.close();
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Exception while writing new user to " + loc, ex);
        }
    }

    public void modifyPlayer(Player player) {
        String loc = etc.getInstance().getUsersLocation();

        try {
            // Now to save...
            BufferedReader reader = new BufferedReader(new FileReader(new File(loc)));
            StringBuilder toWrite = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null)
                if (!line.split(":")[0].equalsIgnoreCase(player.getName()))
                    toWrite.append(line).append("\r\n");
                else {
                    StringBuilder builder = new StringBuilder();
                    builder.append(player.getName());
                    builder.append(":");
                    builder.append(etc.combineSplit(0, player.getGroups(), ","));
                    builder.append(":");
                    if (player.getAdmin())
                        builder.append("2");
                    else if (player.ignoreRestrictions())
                        builder.append("1");
                    else if (!player.canModifyWorld())
                        builder.append("-1");
                    else
                        builder.append("0");
                    builder.append(":");
                    builder.append(player.getPrefix());
                    builder.append(":");
                    builder.append(etc.combineSplit(0, player.getCommands(), ","));
                    toWrite.append(builder.toString()).append("\r\n");
                }
            reader.close();

            FileWriter writer = new FileWriter(loc);
            writer.write(toWrite.toString());
            writer.close();
        } catch (Exception ex) {
            log.log(Level.SEVERE, "Exception while editing user in " + loc, ex);
        }
    }

    public boolean doesPlayerExist(String player) {
        String location = etc.getInstance().getUsersLocation();
        try {
            Scanner scanner = new Scanner(new File(location));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#") || line.equals("") || line.startsWith("﻿"))
                    continue;
                String[] split = line.split(":");
                if (!split[0].equalsIgnoreCase(player))
                    continue;
                return true;
            }
            scanner.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception while reading " + location + " (Are you sure you formatted it correctly?)", e);
        }
        return false;
    }

    public Player getPlayer(String name) {
        Player player = new Player();
        String location = etc.getInstance().getUsersLocation();

        try {
            Scanner scanner = new Scanner(new File(location));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#") || line.equals("") || line.startsWith("﻿"))
                    continue;
                String[] split = line.split(":");
                if (!split[0].equalsIgnoreCase(name))
                    continue;

                player.setGroups(split[1].split(","));

                if (split.length >= 3)
                    if (split[2].equals("1"))
                        player.setIgnoreRestrictions(true);
                    else if (split[2].equals("2"))
                        player.setAdmin(true);
                    else if (split[2].equals("-1"))
                        player.setCanModifyWorld(false);

                if (split.length >= 4)
                    player.setPrefix(split[3]);
                if (split.length >= 5)
                    player.setCommands(split[4].split(","));
                if (split.length >= 6)
                    player.setIps(split[5].split(","));
            }
            scanner.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception while reading " + location + " (Are you sure you formatted it correctly?)", e);
        }
        return player;
    }

    // Groups
    public void addGroup(Group group) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void modifyGroup(Group group) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Kits
    public void addKit(Kit kit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void modifyKit(Kit kit) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Homes
    public void addHome(Warp home) {
        String homeLoc = etc.getInstance().getHomeLocation();
        try {
            if (etc.getInstance().canSaveHomes()) {
                BufferedWriter bw = new BufferedWriter(new FileWriter(homeLoc, true));
                StringBuilder builder = new StringBuilder();
                builder.append(home.Name);
                builder.append(":");
                builder.append(home.Location.x);
                builder.append(":");
                builder.append(home.Location.y);
                builder.append(":");
                builder.append(home.Location.z);
                builder.append(":");
                builder.append(home.Location.rotX);
                builder.append(":");
                builder.append(home.Location.rotY);
                builder.append(":");
                builder.append(home.Group);
                bw.append(builder.toString());
                bw.newLine();
                bw.close();
            }
            synchronized (homeLock) {
                homes.add(home);
            }
        } catch (Exception e2) {
            log.log(Level.SEVERE, "Exception while writing new user home to " + homeLoc, e2);
        }
    }

    public void changeHome(Warp home) {
        synchronized (homeLock) {
            Warp toRem = null;
            for (Warp h : homes)
                if (h.Name.equalsIgnoreCase(home.Name))
                    toRem = h;
            if (toRem != null)
                homes.remove(toRem);
            homes.add(home);
        }
        FileWriter writer = null;
        String homeLoc = etc.getInstance().getHomeLocation();
        try {
            // Now to save...
            if (etc.getInstance().canSaveHomes()) {
                BufferedReader reader = new BufferedReader(new FileReader(new File(homeLoc)));
                StringBuilder toWrite = new StringBuilder();
                String line = "";
                while ((line = reader.readLine()) != null)
                    if (!line.split(":")[0].equalsIgnoreCase(home.Name))
                        toWrite.append(line).append("\r\n");
                    else {
                        StringBuilder builder = new StringBuilder();
                        builder.append(home.Name);
                        builder.append(":");
                        builder.append(home.Location.x);
                        builder.append(":");
                        builder.append(home.Location.y);
                        builder.append(":");
                        builder.append(home.Location.z);
                        builder.append(":");
                        builder.append(home.Location.rotX);
                        builder.append(":");
                        builder.append(home.Location.rotY);
                        builder.append(":");
                        builder.append(home.Group);
                        toWrite.append(builder.toString()).append("\r\n");
                    }
                reader.close();

                writer = new FileWriter(homeLoc);
                writer.write(toWrite.toString());
                writer.close();
            }
        } catch (Exception e1) {
            log.log(Level.SEVERE, "Exception while editing user home in " + homeLoc, e1);
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {
            }
        }
    }

    // Warps
    public void addWarp(Warp warp) {
        String warpLoc = etc.getInstance().getWarpLocation();
        try {
            BufferedWriter bw = new BufferedWriter(new FileWriter(warpLoc, true));
            StringBuilder builder = new StringBuilder();
            builder.append(warp.Name);
            builder.append(":");
            builder.append(warp.Location.x);
            builder.append(":");
            builder.append(warp.Location.y);
            builder.append(":");
            builder.append(warp.Location.z);
            builder.append(":");
            builder.append(warp.Location.rotX);
            builder.append(":");
            builder.append(warp.Location.rotY);
            builder.append(":");
            builder.append(warp.Group);
            bw.append(builder.toString());
            bw.newLine();
            bw.close();
            synchronized (warpLock) {
                warps.add(warp);
            }
        } catch (Exception e2) {
            log.log(Level.SEVERE, "Exception while writing new warp to " + warpLoc, e2);
        }
    }

    public void changeWarp(Warp warp) {
        synchronized (warpLock) {
            Warp toRem = null;
            for (Warp h : warps)
                if (h.Name.equalsIgnoreCase(warp.Name))
                    toRem = h;
            if (toRem != null)
                warps.remove(toRem);
            warps.add(warp);
        }
        FileWriter writer = null;
        String warpLoc = etc.getInstance().getWarpLocation();
        try {
            // Now to save...
            BufferedReader reader = new BufferedReader(new FileReader(new File(warpLoc)));
            StringBuilder toWrite = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null)
                if (!line.split(":")[0].equalsIgnoreCase(warp.Name))
                    toWrite.append(line).append("\r\n");
                else {
                    StringBuilder builder = new StringBuilder();
                    builder.append(warp.Name);
                    builder.append(":");
                    builder.append(warp.Location.x);
                    builder.append(":");
                    builder.append(warp.Location.y);
                    builder.append(":");
                    builder.append(warp.Location.z);
                    builder.append(":");
                    builder.append(warp.Location.rotX);
                    builder.append(":");
                    builder.append(warp.Location.rotY);
                    builder.append(":");
                    builder.append(warp.Group);
                    toWrite.append(builder.toString()).append("\r\n");
                }
            reader.close();

            writer = new FileWriter(warpLoc);
            writer.write(toWrite.toString());
            writer.close();
        } catch (Exception e1) {
            log.log(Level.SEVERE, "Exception while editing warp in " + warpLoc, e1);
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {
            }
        }
    }

    public void removeWarp(Warp warp) {
        FileWriter writer = null;
        String warpLoc = etc.getInstance().getWarpLocation();
        try {
            // Now to save...
            BufferedReader reader = new BufferedReader(new FileReader(new File(warpLoc)));
            StringBuilder toWrite = new StringBuilder();
            String line = "";
            while ((line = reader.readLine()) != null)
                if (!line.split(":")[0].equalsIgnoreCase(warp.Name))
                    toWrite.append(line).append("\r\n");
            reader.close();

            writer = new FileWriter(warpLoc);
            writer.write(toWrite.toString());
            writer.close();
        } catch (Exception e1) {
            log.log(Level.SEVERE, "Exception while delete warp from " + warpLoc, e1);
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {
            }
        }

        synchronized (warpLock) {
            warps.remove(warp);
        }
    }

    // Whitelist
    public void addToWhitelist(String name) {
        if (isUserOnWhitelist(name))
            return;

        BufferedWriter bw = null;
        String location = etc.getInstance().getWhitelistLocation();
        try {
            bw = new BufferedWriter(new FileWriter(location, true));
            bw.newLine();
            bw.append(name);
        } catch (Exception e2) {
            log.log(Level.SEVERE, "Exception while writing new user to " + location, e2);
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException ex) {
            }
        }
    }

    public void removeFromWhitelist(String name) {
        if (!isUserOnWhitelist(name))
            return;

        FileWriter writer = null;
        String location = etc.getInstance().getWhitelistLocation();

        try {
            // Now to save...
            BufferedReader reader = new BufferedReader(new FileReader(new File(location)));
            String line = "";
            StringBuilder toSave = new StringBuilder();

            while ((line = reader.readLine()) != null)
                if (!line.equalsIgnoreCase(name.toLowerCase()))
                    toSave.append(line).append("\r\n");
            reader.close();

            writer = new FileWriter(location);
            writer.write(toSave.toString());
        } catch (Exception e1) {
            log.log(Level.SEVERE, "Exception while removing player '" + name + "' from " + location, e1);
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {
            }
        }
    }

    public boolean isUserOnWhitelist(String user) {
        String location = etc.getInstance().getWhitelistLocation();
        Player player = getPlayer(user);
        try {
            Scanner scanner = new Scanner(new File(location));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#") || line.equals("") || line.startsWith("﻿"))
                    continue;
                if (line.startsWith("@") && player.isInGroup(line.substring(1)))
                    return true;
                if (line.equalsIgnoreCase(user))
                    return true;
            }
            scanner.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception while reading " + location, e);
        }
        return false;
    }

    public void modifyBan(Ban ban) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    // Reservelist
    public boolean isUserOnReserveList(String user) {
        String location = etc.getInstance().getReservelistLocation();
        Player player = getPlayer(user);
        try {
            Scanner scanner = new Scanner(new File(location));
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                if (line.startsWith("#") || line.equals("") || line.startsWith("﻿"))
                    continue;
                if (line.startsWith("@") && player.isInGroup(line.substring(1)))
                    return true;
                if (line.equalsIgnoreCase(user))
                    return true;
            }
            scanner.close();
        } catch (Exception e) {
            log.log(Level.SEVERE, "Exception while reading " + location, e);
        }
        return false;
    }

    public void addToReserveList(String name) {
        if (isUserOnReserveList(name))
            return;
        BufferedWriter bw = null;
        String location = etc.getInstance().getReservelistLocation();
        try {
            bw = new BufferedWriter(new FileWriter(location, true));
            bw.newLine();
            bw.append(name);
        } catch (Exception e2) {
            log.log(Level.SEVERE, "Exception while writing new user to " + location, e2);
        } finally {
            try {
                if (bw != null)
                    bw.close();
            } catch (IOException ex) {
            }
        }
    }

    public void removeFromReserveList(String name) {
        if (!isUserOnReserveList(name))
            return;

        FileWriter writer = null;
        String location = etc.getInstance().getReservelistLocation();

        try {
            // Now to save...
            BufferedReader reader = new BufferedReader(new FileReader(new File(location)));
            String line = "";
            StringBuilder toSave = new StringBuilder();

            while ((line = reader.readLine()) != null)
                if (!line.equalsIgnoreCase(name.toLowerCase()))
                    toSave.append(line).append("\r\n");
            reader.close();

            writer = new FileWriter(location);
            writer.write(toSave.toString());
        } catch (Exception e1) {
            log.log(Level.SEVERE, "Exception while removing player '" + name + "' from " + location, e1);
        } finally {
            try {
                if (writer != null)
                    writer.close();
            } catch (IOException ex) {
            }
        }
    }
}
