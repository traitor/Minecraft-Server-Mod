import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import net.minecraft.server.MinecraftServer;

public class je extends ey implements eu {
	public static Logger a = Logger.getLogger("Minecraft");
	public bh b;
	public boolean c = false;
	private MinecraftServer d;
	private ep e;
	private int f = 0;
	private double g;
	private double h;
	private double i;
	private boolean j = true;

	private hj k = null;

	private List<String> onlyOneUseKits = new ArrayList<String>();
	private Pattern badChatPattern = Pattern.compile("[^ !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ\\[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~\u2302\u00C7\u00FC\u00E9\u00E2\u00E4\u00E0\u00E5\u00E7\u00EA\u00EB\u00E8\u00EF\u00EE\u00EC\u00C4\u00C5\u00C9\u00E6\u00C6\u00F4\u00F6\u00F2\u00FB\u00F9\u00FF\u00D6\u00DC\u00F8\u00A3\u00D8\u00D7\u0192\u00E1\u00ED\u00F3\u00FA\u00F1\u00D1\u00AA\u00BA\u00BF\u00AE\u00AC\u00BD\u00BC\u00A1\u00AB\u00BB]");

	public je(MinecraftServer paramMinecraftServer, bh parambh, ep paramep) {
		this.d = paramMinecraftServer;
		this.b = parambh;
		parambh.a(this);
		this.e = paramep;
		paramep.a = this;
	}
	
    /**
     * Returns the item in player's hand
     * @return
     */
    public int getItemInHand() {
        if (k != null) //Check to see if we are even holding anything
        {
            return k.c;
        }
        return -1;
    }
    public Player getPlayer() {
        return e.getPlayer();
    }
	public void a() {
		this.b.a();
		if (this.f++ % 20 == 0)
			this.b.a(new ke());
	}

	public void c(String paramString) {
		this.b.a(new jr(paramString));
		this.b.c();
		this.d.f.c(this.e);
		this.c = true;
	}

	public void a(gz paramgz) {
		if (!this.j) {
			double d1 = paramgz.b - this.h;
			if ((paramgz.a == this.g) && (d1 * d1 < 0.01D) && (paramgz.c == this.i)) {
				this.j = true;
			}
		}
		if ((int) Math.floor(g) != (int) Math.floor(getPlayer().getX())
                || (int) Math.floor(h) != (int) Math.floor(getPlayer().getY())
                || (int) Math.floor(i) != (int) Math.floor(getPlayer().getZ())) {
            Location from = new Location();
            from.x = (int) Math.floor(g);
            from.y = (int) Math.floor(h);
            from.z = (int) Math.floor(i);
            from.rotX = getPlayer().getRotation();
            from.rotY = getPlayer().getPitch();

            Location to = new Location();
            to.x = (int) Math.floor(e.p);
            to.y = (int) Math.floor(e.q);
            to.z = (int) Math.floor(e.r);
            to.rotX = getPlayer().getRotation();
            to.rotY = getPlayer().getPitch();

            etc.getLoader().callHook(PluginLoader.Hook.PLAYER_MOVE, new Object[]{e, from, to});
        }
		if (this.j) {
			if (this.e.k != null) {
				float f1 = this.e.v;
				float f2 = this.e.w;
				this.e.k.z();
				double d3 = this.e.p;
				double d4 = this.e.q;
				double d5 = this.e.r;
				double d6 = 0.0D;
				double d7 = 0.0D;
				if (paramgz.i) {
					f1 = paramgz.e;
					f2 = paramgz.f;
				}
				if ((paramgz.h) && (paramgz.b == -999.0D) && (paramgz.d == -999.0D)) {
					d6 = paramgz.a;
					d7 = paramgz.c;
				}

				this.e.k();
				this.e.b(d3, d4, d5, f1, f2);
				this.e.s = d6;
				this.e.u = d7;
				this.e.k.b_();

				return;
			}

			this.g = this.e.p;
			this.h = this.e.q;
			this.i = this.e.r;

			double d2 = this.e.p;
			double d3 = this.e.q;
			double d4 = this.e.r;

			float f3 = this.e.v;
			float f4 = this.e.w;

			if ((paramgz.h) && (paramgz.b == -999.0D) && (paramgz.d == -999.0D)) {
				paramgz.h = false;
			}

			if (paramgz.h) {
				d2 = paramgz.a;
				d3 = paramgz.b;
				d4 = paramgz.c;
				double d6 = paramgz.d - paramgz.b;
				if ((d6 > 1.65D) || (d6 < 0.1D)) {
					c("Illegal stance");
					a.warning(this.e.ar + " had an illegal stance: " + d6);
				}
				this.e.aj = paramgz.d;
			}
			if (paramgz.i) {
				f3 = paramgz.e;
				f4 = paramgz.f;
			}

			this.e.k();
			this.e.Q = 0.0F;
			this.e.b(this.g, this.h, this.i, f3, f4);

			double d6 = d2 - this.e.p;
			double d7 = d3 - this.e.q;
			double d8 = d4 - this.e.r;

			float f5 = 0.0625F;
			int m = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;

			this.e.c(d6, d7, d8);
			d6 = d2 - this.e.p;
			d7 = d3 - this.e.q;
			if ((d7 > -0.5D) || (d7 < 0.5D)) {
				d7 = 0.0D;
			}
			d8 = d4 - this.e.r;
			double d9 = d6 * d6 + d7 * d7 + d8 * d8;
			int n = 0;
			if (d9 > 0.0625D) {
				n = 1;
				a.warning(this.e.ar + " moved wrongly!");
			}
			this.e.b(d2, d3, d4, f3, f4);

			int i1 = this.d.e.a(this.e, this.e.z.b().e(f5, f5, f5)).size() == 0 ? 1 : 0;
			if ((m != 0) && ((n != 0) || (i1 == 0))) {
				a(this.g, this.h, this.i, f3, f4);
				return;
			}

			this.e.A = paramgz.g;
			this.d.f.b(this.e);
		}
	}

	public void a(double paramDouble1, double paramDouble2, double paramDouble3, float paramFloat1, float paramFloat2) {
		Location from = new Location();
		from.x = paramDouble1;
		from.y = paramDouble2;
		from.z = paramDouble3;
		from.rotX = paramFloat1;
		from.rotY = paramFloat2;
		if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.TELEPORT, new Object[]{e, e.getPlayer().getLocation(), from})) {
			return;
		}
		this.j = false;
		this.g = paramDouble1;
		this.h = paramDouble2;
		this.i = paramDouble3;
		this.e.b(paramDouble1, paramDouble2, paramDouble3, paramFloat1, paramFloat2);
		this.e.a.b(new ed(paramDouble1, paramDouble2 + 1.620000004768372D, paramDouble2, paramDouble3, paramFloat1, paramFloat2, false));
	}

	 int x, y, z, type;
	 //Destroy function
	 
	public void a(hz paramhz) {
		this.e.ak.a[this.e.ak.d] = this.k;
		boolean bool = this.d.e.B = this.d.f.g(this.e.ar) || getPlayer().getAdmin();
		int m = 0;
		if (paramhz.e == 0)
			m = 1;
		if (paramhz.e == 1)
			m = 1;

		if (m != 0) {
			double d1 = this.e.q;
			this.e.q = this.e.aj;
			gm localgm = this.e.a(4.0D, 1.0F);
			this.e.q = d1;
			if (localgm == null)
				return;
			if ((localgm.b != paramhz.a) || (localgm.c != paramhz.b) || (localgm.d != paramhz.c) || (localgm.e != paramhz.d)) {
				return;
			}
		}
		int n = paramhz.a;
		int i1 = paramhz.b;
		int i2 = paramhz.c;
		int i3 = paramhz.d;
		int i4 = (int) hd.e(n - this.d.e.m);
		int i5 = (int) hd.e(i2 - this.d.e.o);
		if (i4 > i5)
			i5 = i4;
		if (paramhz.e == 0) {
			
			if (!getPlayer().canBuild())
                return;
            
			
			if ((i5 > etc.getInstance().getSpawnProtectionSize()) || (bool)){
				 Block block = etc.getServer().getBlockAt(n, i1, i2);
	            block.setStatus(0); //Started digging
	            x = block.getX();
	            y = block.getY();
	            z = block.getZ();
	            type = block.getType();
	            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block})) {
	                this.e.c.a(n, i1, i2);
	            }
			}
				
			
			
		} else if (paramhz.e == 2) {
			
			Block block = etc.getServer().getBlockAt(n, i1, i2);
            block.setStatus(2); //Stopped digging
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});
            
			this.e.c.a();
		} else if (paramhz.e == 1) {
			
			if (!getPlayer().canBuild())
                return;
			
			if (i5 > etc.getInstance().getSpawnProtectionSize() || (bool)){
				Block block = etc.getServer().getBlockAt(n, i1, i2);
				block.setStatus(1); //Digging
				if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block})) {
					this.e.c.a(n, i1, i2, i3);
				}
			}			
		} else if (paramhz.e == 3) {
			
			Block block = new Block(type, x, y, z);
            block.setStatus(3);
            etc.getLoader().callHook(PluginLoader.Hook.BLOCK_DESTROYED, new Object[]{e, block});
            
			double d2 = this.e.p - (n + 0.5D);
			double d3 = this.e.q - (i1 + 0.5D);
			double d4 = this.e.r - (i2 + 0.5D);
			double d5 = d2 * d2 + d3 * d3 + d4 * d4;
			if (d5 < 256.0D) {
				this.e.a.b(new fj(n, i1, i2, this.d.e));
			}
		}
		this.d.e.B = false;
	}

	public void a(fx paramfx) {
		boolean bool = this.d.e.B = this.d.f.g(getPlayer().getName()) || getPlayer().isAdmin();
		if (paramfx.e == 255) {
			hj localhj1 = paramfx.a >= 0 ? new hj(paramfx.a) : null;
			this.e.c.a(this.e, this.d.e, localhj1);
		} else {
			int m = paramfx.b;
			int n = paramfx.c;
			int i1 = paramfx.d;
			int i2 = paramfx.e;
			int i3 = (int) hd.e(m - this.d.e.m);
			int i4 = (int) hd.e(i1 - this.d.e.o);
			if (i3 > i4)
				i4 = i3;
			if ((i4 > etc.getInstance().getSpawnProtectionSize()) || (bool)) {
				hj localhj = paramfx.a >= 0 ? new hj(paramfx.a) : null;
				
				Block blockPlaced = new Block(localhj != null ? localhj.c : paramfx.a, m, n, i1);
	            if (paramfx.e == 0) {
	                blockPlaced.setY(blockPlaced.getY() - 1);
	            } else if (paramfx.e == 1) {
	                blockPlaced.setY(blockPlaced.getY() + 1);
	            } else if (paramfx.e == 2) {
	                blockPlaced.setZ(blockPlaced.getZ() - 1);
	            } else if (paramfx.e == 3) {
	                blockPlaced.setZ(blockPlaced.getZ() + 1);
	            } else if (paramfx.e == 4) {
	                blockPlaced.setX(blockPlaced.getX() - 1);
	            } else if (paramfx.e == 5) {
	                blockPlaced.setX(blockPlaced.getX() + 1);
	            }
	            Block blockClicked = new Block(etc.getServer().getBlockIdAt(m, n, i1), m, n, i1);
	            blockClicked.setFaceClicked(Block.Face.fromId(paramfx.e));
	            
	            if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.BLOCK_CREATED, new Object[]{e, blockPlaced, blockClicked, paramfx.a}) || getPlayer().getAdmin()) {
	            	if(localhj != null){
	            		if (!etc.getInstance().isOnItemBlacklist(localhj.c) || bool) 
	            			this.e.c.a(this.e, this.d.e, localhj, m, n, i1, i2);
	            	}
	            	// is this right ?
	            	else
	            		this.e.c.a(this.e, this.d.e, localhj, m, n, i1, i2);
	            }
			}

			this.e.a.b(new fj(m, n, i1, this.d.e));

			if (i2 == 0)
				n--;
			if (i2 == 1)
				n++;
			if (i2 == 2)
				i1--;
			if (i2 == 3)
				i1++;
			if (i2 == 4)
				m--;
			if (i2 == 5)
				m++;

			this.e.a.b(new fj(m, n, i1, this.d.e));
		}
		this.d.e.B = false;
	}

	public void a(String paramString) {
		etc.getLoader().callHook(PluginLoader.Hook.DISCONNECT, new Object[]{e});
		a.info(this.e.ar + " lost connection: " + paramString);
		this.d.f.c(this.e);
		this.c = true;
	}

	public void a(io paramio) {
		a.warning(getClass() + " wasn't prepared to deal with a " + paramio.getClass());
		c("Protocol error, unexpected packet");
	}

	public void b(io paramio) {
		this.b.a(paramio);
	}

	public void a(gp paramgp) {
		int m = paramgp.b;
		this.e.ak.d = (this.e.ak.a.length - 1);
		if (m == 0)
			this.k = null;
		else {
			this.k = new hj(m);
		}
		this.e.ak.a[this.e.ak.d] = this.k;
		this.d.k.a(this.e, new gp(this.e.g, m));
	}

	public void a(l paraml) {
		double d1 = paraml.b / 32.0D;
		double d2 = paraml.c / 32.0D;
		double d3 = paraml.d / 32.0D;
		if (!(Boolean) etc.getLoader().callHook(PluginLoader.Hook.ITEM_DROP, new Object[]{e, new Item(paraml.h, paraml.i)})) {
			gh localgh = new gh(this.d.e, d1, d2, d3, new hj(paraml.h, paraml.i));
			localgh.s = (paraml.e / 128.0D);
			localgh.t = (paraml.f / 128.0D);
			localgh.u = (paraml.g / 128.0D);
			localgh.c = 10;
			this.d.e.a(localgh);
		}
	}

	public void a(bg parambg) {
		String str = parambg.a;
		if (str.length() > 100) {
			c("Chat message too long");
			return;
		}
		str = str.trim();
		Matcher m = badChatPattern.matcher(str);
        if (m.find()) {
            c("Illegal characters '" + m.group() + "' hex: " + Integer.toHexString(str.charAt(m.start())) + " in chat");
            return;
        }
		/*
		for (int m = 0; m < str.length(); m++) {
			if (" !\"#$%&'()*+,-./0123456789:;<=>?@ABCDEFGHIJKLMNOPQRSTUVWXYZ[\\]^_'abcdefghijklmnopqrstuvwxyz{|}~⌂ÇüéâäàåçêëèïîìÄÅÉæÆôöòûùÿÖÜø£Ø×ƒáíóúñÑªº¿®¬½¼¡«»"
					.indexOf(str.charAt(m)) < 0) {
				c("Illegal characters in chat");
				return;
			}
		}
		 */
		if (str.startsWith("/")) {
			d(str);
		} else {
			if (getPlayer().isMuted()) {
                msg(Colors.Rose + "You are currently muted.");
                return;
            }
			if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.CHAT, new Object[]{e, str})) {
				return;
			}
			 
			//str = "<" + this.e.ar + "> " + str;
			String message = "<" + getPlayer().getColor() + getPlayer().getName() + Colors.White + "> " + str;
			a.log(Level.INFO, "<" + getPlayer().getName() + "> " + str);
			//a.info(str);
			this.d.f.a(new bg(message));
		}
	}
	
    /**
     * Sends a message to the player
     * @param msg
     */
    public void msg(String msg) {
        b(new bg(msg));
    }

	private void d(String paramString) {
		try {
            if (etc.getInstance().isLogging()) {
                a.info("Command used by " + getPlayer().getName() + " " + paramString);
            }
            String[] split = paramString.split(" ");
            if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMMAND, new Object[]{e, split})) {
                return; //No need to go on, commands were parsed.
            }
            if (!getPlayer().canUseCommand(split[0]) && !split[0].startsWith("/#")) {
                if (etc.getInstance().showUnknownCommand()) {
                    msg(Colors.Rose + "Unknown command.");
                }
                return;
            }
            if (split[0].equalsIgnoreCase("/help")) {
                //Meh, not the greatest way, but not the worst either.
                List<String> availableCommands = new ArrayList<String>();
                for (Entry<String, String> entry : etc.getInstance().getCommands().entrySet()) {
                    if (getPlayer().canUseCommand(entry.getKey())) {
                        if (entry.getKey().equals("/kit") && !etc.getDataSource().hasKits()) {
                            continue;
                        }
                        if (entry.getKey().equals("/listwarps") && !etc.getDataSource().hasWarps()) {
                            continue;
                        }

                        availableCommands.add(entry.getKey() + " " + entry.getValue());
                    }
                }

                msg(Colors.Blue + "Available commands (Page " + (split.length == 2 ? split[1] : "1") + " of " + (int) Math.ceil((double) availableCommands.size() / (double) 7) + ") [] = required <> = optional:");
                if (split.length == 2) {
                    try {
                        int amount = Integer.parseInt(split[1]);

                        if (amount > 0) {
                            amount = (amount - 1) * 7;
                        } else {
                            amount = 0;
                        }

                        for (int i = amount; i < amount + 7; i++) {
                            if (availableCommands.size() > i) {
                                msg(Colors.Rose + availableCommands.get(i));
                            }
                        }
                    } catch (NumberFormatException ex) {
                        msg(Colors.Rose + "Not a valid page number.");
                    }
                } else {
                    for (int i = 0; i < 7; i++) {
                        if (availableCommands.size() > i) {
                            msg(Colors.Rose + availableCommands.get(i));
                        }
                    }
                }
            } else if (split[0].equalsIgnoreCase("/reload")) {
                etc.getInstance().load();
                etc.getInstance().loadData();
                for (Player player : etc.getServer().getPlayerList()) {
                    player.getUser().reloadPlayer();
                }
                a.info("Reloaded config");
                msg("Successfuly reloaded config");
            } else if ((split[0].equalsIgnoreCase("/modify") || split[0].equalsIgnoreCase("/mp"))) {
                if (split.length > 2 && split[2].contains(":")) {
                    for (int i = 3; i < split.length; i++) {
                        if (!split[i].contains(":")) {
                            msg(Colors.Rose + "Usage is: /modify [player] [key] [value]");
                            msg(Colors.Rose + "Keys:");
                            msg(Colors.Rose + "prefix: only the letter the color represents");
                            msg(Colors.Rose + "commands: list seperated by comma");
                            msg(Colors.Rose + "groups: list seperated by comma");
                            msg(Colors.Rose + "ignoresrestrictions: true or false");
                            msg(Colors.Rose + "admin: true or false");
                            msg(Colors.Rose + "modworld: true or false");
                            return;
                        }
                    }

                    Player player = etc.getServer().matchPlayer(split[1]);

                    if (player == null) {
                        msg(Colors.Rose + "Player does not exist.");
                        return;
                    }

                    for (int i = 2; i < split.length; i++) {
                        if (split[i].split(":").length != 2) {
                            msg("This key:value pair is deformed... " + split[i]);
                            return;
                        }
                        String key = split[i].split(":")[0];
                        String value = split[i].split(":")[1];
                        boolean newUser = false;

                        if (!etc.getDataSource().doesPlayerExist(player.getName())) {
                            if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                                msg(Colors.Rose + "When adding a new user, set their group(s) first.");
                                return;
                            }
                            msg(Colors.Rose + "Adding new user.");
                            newUser = true;
                            player.setCanModifyWorld(true);
                        }

                        if (key.equalsIgnoreCase("prefix") || key.equalsIgnoreCase("p")) {
                            player.setPrefix(value);
                        } else if (key.equalsIgnoreCase("commands") || key.equalsIgnoreCase("c")) {
                            player.setCommands(value.split(","));
                        } else if (key.equalsIgnoreCase("groups") || key.equalsIgnoreCase("g")) {
                            player.setGroups(value.split(","));
                        } else if (key.equalsIgnoreCase("ignoresrestrictions") || key.equalsIgnoreCase("ir")) {
                            player.setIgnoreRestrictions(value.equalsIgnoreCase("true") || value.equals("1"));
                        } else if (key.equalsIgnoreCase("admin") || key.equalsIgnoreCase("a")) {
                            player.setAdmin(value.equalsIgnoreCase("true") || value.equals("1"));
                        } else if (key.equalsIgnoreCase("modworld") || key.equalsIgnoreCase("mw")) {
                            player.setCanModifyWorld(value.equalsIgnoreCase("true") || value.equals("1"));
                        }

                        if (newUser) {
                            etc.getDataSource().addPlayer(player);
                        } else {
                            etc.getDataSource().modifyPlayer(player);
                        }
                        a.info("Modifed user " + split[1] + ". " + key + " => " + value + " by " + getPlayer().getName());
                    }
                    msg(Colors.Rose + "Modified user.");
                } else {
                    if (split.length < 4) {
                        msg(Colors.Rose + "Usage is: /modify [player] [key] [value]");
                        msg(Colors.Rose + "Keys:");
                        msg(Colors.Rose + "prefix: only the letter the color represents");
                        msg(Colors.Rose + "commands: list seperated by comma");
                        msg(Colors.Rose + "groups: list seperated by comma");
                        msg(Colors.Rose + "ignoresrestrictions: true or false");
                        msg(Colors.Rose + "admin: true or false");
                        msg(Colors.Rose + "modworld: true or false");
                        return;
                    }

                    Player player = etc.getServer().matchPlayer(split[1]);

                    if (player == null) {
                        msg(Colors.Rose + "Player does not exist.");
                        return;
                    }

                    String key = split[2];
                    String value = split[3];
                    boolean newUser = false;

                    if (!etc.getDataSource().doesPlayerExist(player.getName())) {
                        if (!key.equalsIgnoreCase("groups") && !key.equalsIgnoreCase("g")) {
                            msg(Colors.Rose + "When adding a new user, set their group(s) first.");
                            return;
                        }
                        msg(Colors.Rose + "Adding new user.");
                        newUser = true;
                    }

                    if (key.equalsIgnoreCase("prefix") || key.equalsIgnoreCase("p")) {
                        player.setPrefix(value);
                    } else if (key.equalsIgnoreCase("commands") || key.equalsIgnoreCase("c")) {
                        player.setCommands(value.split(","));
                    } else if (key.equalsIgnoreCase("groups") || key.equalsIgnoreCase("g")) {
                        player.setGroups(value.split(","));
                    } else if (key.equalsIgnoreCase("ignoresrestrictions") || key.equalsIgnoreCase("ir")) {
                        player.setIgnoreRestrictions(value.equalsIgnoreCase("true") || value.equals("1"));
                    } else if (key.equalsIgnoreCase("admin") || key.equalsIgnoreCase("a")) {
                        player.setAdmin(value.equalsIgnoreCase("true") || value.equals("1"));
                    } else if (key.equalsIgnoreCase("modworld") || key.equalsIgnoreCase("mw")) {
                        player.setCanModifyWorld(value.equalsIgnoreCase("true") || value.equals("1"));
                    }

                    if (newUser) {
                        etc.getDataSource().addPlayer(player);
                    } else {
                        etc.getDataSource().modifyPlayer(player);
                    }
                    msg(Colors.Rose + "Modified user.");
                    a.info("Modifed user " + split[1] + ". " + key + " => " + value + " by " + getPlayer().getName());
                }
            } else if (split[0].equalsIgnoreCase("/whitelist")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "whitelist [operation (toggle, add or remove)] <player>");
                    return;
                }

                if (split[1].equalsIgnoreCase("toggle")) {
                    msg(Colors.Rose + (etc.getInstance().toggleWhitelist() ? "Whitelist enabled" : "Whitelist disabled"));
                } else if (split.length == 3) {
                    if (split[1].equalsIgnoreCase("add")) {
                        etc.getDataSource().addToWhitelist(split[2]);
                        msg(Colors.Rose + split[2] + " added to whitelist");
                    } else if (split[1].equalsIgnoreCase("remove")) {
                        etc.getDataSource().removeFromWhitelist(split[2]);
                        msg(Colors.Rose + split[2] + " removed from whitelist");
                    } else {
                        msg(Colors.Rose + "Invalid operation.");
                    }
                } else {
                    msg(Colors.Rose + "Invalid operation.");
                }
            } else if (split[0].equalsIgnoreCase("/reservelist")) {
                if (split.length != 3) {
                    msg(Colors.Rose + "reservelist [operation (add or remove)] [player]");
                    return;
                }

                if (split[1].equalsIgnoreCase("add")) {
                    etc.getDataSource().addToReserveList(split[2]);
                    msg(Colors.Rose + split[2] + " added to reservelist");
                } else if (split[1].equalsIgnoreCase("remove")) {
                    etc.getDataSource().removeFromReserveList(split[2]);
                    msg(Colors.Rose + split[2] + " removed from reservelist");
                } else {
                    msg(Colors.Rose + "Invalid operation.");
                }
            } else if (split[0].equalsIgnoreCase("/mute")) {
                if (split.length != 2) {
                    msg(Colors.Rose + "Correct usage is: /mute [player]");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (player.toggleMute()) {
                        msg(Colors.Rose + "player was muted");
                    } else {
                        msg(Colors.Rose + "player was unmuted");
                    }
                } else {
                    msg(Colors.Rose + "Can't find player " + split[1]);
                }
            } else if ((split[0].equalsIgnoreCase("/msg") || split[0].equalsIgnoreCase("/tell")) || split[0].equalsIgnoreCase("/m")) {
                if (split.length < 3) {
                    msg(Colors.Rose + "Correct usage is: /msg [player] [message]");
                    return;
                }
                if (getPlayer().isMuted()) {
                    msg(Colors.Rose + "You are currently muted.");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (player.getName().equals(getPlayer().getName())) {
                        msg(Colors.Rose + "You can't message yourself!");
                        return;
                    }
                    String prefix = getPlayer().getColor();

                    player.sendMessage("(MSG) " + prefix + "<" + getPlayer().getName() + "> " + Colors.White + etc.combineSplit(2, split, " "));
                    msg("(MSG) " + prefix + "<" + getPlayer().getName() + "> " + Colors.White + etc.combineSplit(2, split, " "));
                } else {
                    msg(Colors.Rose + "Couldn't find player " + split[1]);
                }
            } else if (split[0].equalsIgnoreCase("/kit") && etc.getDataSource().hasKits()) {
                if (split.length != 2 && split.length != 3) {
                    msg(Colors.Rose + "Available kits" + Colors.White + ": " + etc.getDataSource().getKitNames(getPlayer()));
                    return;
                }

                Player toGive = getPlayer();
                if (split.length > 2 && getPlayer().canIgnoreRestrictions()) {
                    toGive = etc.getServer().matchPlayer(split[1]);
                }

                Kit kit = etc.getDataSource().getKit(split[1]);
                if (toGive != null) {
                    if (kit != null) {
                        if (!getPlayer().isInGroup(kit.Group) && !kit.Group.equals("")) {
                            msg(Colors.Rose + "That kit does not exist.");
                        } else if (onlyOneUseKits.contains(kit.Name)) {
                            msg(Colors.Rose + "You can only get this kit once per login.");
                        } else if (MinecraftServer.b.containsKey(getPlayer().getName() + " " + kit.Name)) {
                            msg(Colors.Rose + "You can't get this kit again for a while.");
                        } else {
                            if (!getPlayer().canIgnoreRestrictions()) {
                                if (kit.Delay >= 0) {
                                    MinecraftServer.b.put(getPlayer().getName() + " " + kit.Name, kit.Delay);
                                } else {
                                    onlyOneUseKits.add(kit.Name);
                                }
                            }

                            a.info(getPlayer().getName() + " got a kit!");
                            toGive.sendMessage(Colors.Rose + "Enjoy this kit!");
                            for (Map.Entry<String, Integer> entry : kit.IDs.entrySet()) {
                                try {
                                    int itemId = 0;
                                    try {
                                        itemId = Integer.parseInt(entry.getKey());
                                    } catch (NumberFormatException n) {
                                        itemId = etc.getDataSource().getItem(entry.getKey());
                                    }

                                    toGive.giveItem(itemId, kit.IDs.get(entry.getKey()));
                                } catch (Exception e1) {
                                    a.info("Got an exception while giving out a kit (Kit name \"" + kit.Name + "\"). Are you sure all the Ids are numbers?");
                                    msg(Colors.Rose + "The server encountered a problem while giving the kit :(");
                                }
                            }
                        }
                    } else {
                        msg(Colors.Rose + "That kit does not exist.");
                    }
                } else {
                    msg(Colors.Rose + "That user does not exist.");
                }
            } else if (split[0].equalsIgnoreCase("/tp")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /tp [player]");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (getPlayer().getName().equalsIgnoreCase(player.getName())) {
                        msg(Colors.Rose + "You're already here!");
                        return;
                    }

                    a.info(getPlayer().getName() + " teleported to " + player.getName());
                    getPlayer().teleportTo(player);
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if ((split[0].equalsIgnoreCase("/tphere") || split[0].equalsIgnoreCase("/s"))) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /tphere [player]");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (getPlayer().getName().equalsIgnoreCase(player.getName())) {
                        msg(Colors.Rose + "Wow look at that! You teleported yourself to yourself!");
                        return;
                    }

                    a.info(getPlayer().getName() + " teleported " + player.getName() + " to their self.");
                    player.teleportTo(getPlayer());
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/playerlist") || split[0].equalsIgnoreCase("/who")) {
                msg(Colors.Rose + "Player list (" + d.f.b.size() + "/" + etc.getInstance().getPlayerLimit() + "): " + Colors.White + d.f.c());
            } else if (split[0].equalsIgnoreCase("/item") || split[0].equalsIgnoreCase("/i") || split[0].equalsIgnoreCase("/give")) {
                if (split.length < 2) {
                    if (getPlayer().canIgnoreRestrictions()) {
                        msg(Colors.Rose + "Correct usage is: /item [itemid] <amount> <player> (optional)");
                    } else {
                        msg(Colors.Rose + "Correct usage is: /item [itemid] <amount>");
                    }
                    return;
                }

                Player toGive = getPlayer();
                if (split.length == 4 && getPlayer().canIgnoreRestrictions()) {
                    toGive = etc.getServer().matchPlayer(split[3]);
                }

                if (toGive != null) {
                    try {
                        int itemId = 0;
                        try {
                            itemId = Integer.parseInt(split[1]);
                        } catch (NumberFormatException n) {
                            itemId = etc.getDataSource().getItem(split[1]);
                        }
                        int amount = 1;
                        if (split.length > 2) {
                            amount = Integer.parseInt(split[2]);
                        }

                        String itemIdstr = Integer.toString(itemId);
                        if (amount <= 0 && !getPlayer().isAdmin()) {
                            amount = 1;
                        }

                        if (amount > 64 && !getPlayer().canIgnoreRestrictions()) {
                            amount = 64;
                        }
                        if (amount > 1024) {
                            amount = 1024; //16 stacks worth. More than enough.
                        }
                        boolean allowedItem = false;
                        if (!etc.getInstance().getAllowedItems()[0].equals("") && (!getPlayer().canIgnoreRestrictions())) {
                            for (String str : etc.getInstance().getAllowedItems()) {
                                if (itemIdstr.equals(str)) {
                                    allowedItem = true;
                                }
                            }
                        } else {
                            allowedItem = true;
                        }
                        if (!etc.getInstance().getDisallowedItems()[0].equals("") && !getPlayer().canIgnoreRestrictions()) {
                            for (String str : etc.getInstance().getDisallowedItems()) {
                                if (itemIdstr.equals(str)) {
                                    allowedItem = false;
                                }
                            }
                        }
                        if (Item.isValidItem(itemId)) {
                            if (allowedItem || getPlayer().canIgnoreRestrictions()) {
                                a.log(Level.INFO, "Giving " + toGive.getName() + " some " + itemId);
                                toGive.giveItem(itemId, amount);

                                if (toGive.getName().equalsIgnoreCase(getPlayer().getName())) {
                                    msg(Colors.Rose + "There you go c:");
                                } else {
                                    msg(Colors.Rose + "Gift given! :D");
                                    toGive.sendMessage(Colors.Rose + "Enjoy your gift! :D");
                                }
                            } else if (!allowedItem && !getPlayer().canIgnoreRestrictions()) {
                                msg(Colors.Rose + "You are not allowed to spawn that item.");
                            }
                        } else {
                            msg(Colors.Rose + "No item with ID " + split[1]);
                        }
                    } catch (NumberFormatException localNumberFormatException) {
                        msg(Colors.Rose + "Improper ID and/or amount.");
                    }
                } else {
                    msg(Colors.Rose + "Can't find user " + split[3]);
                }
            } else if (split[0].equalsIgnoreCase("/tempban")) {
                // /tempban MINUTES HOURS DAYS
                if (split.length == 1) {
                    //TODO;
                    return;
                }
                int minutes = 0, hours = 0, days = 0;
                if (split.length >= 2) {
                    minutes = Integer.parseInt(split[1]);
                }
                if (split.length >= 3) {
                    hours = Integer.parseInt(split[2]);
                }
                if (split.length >= 4) {
                    days = Integer.parseInt(split[3]);
                }
                Date date = new Date();
                //date.
            } else if (split[0].equalsIgnoreCase("/banlist")) {
                byte type = 0;
                if (split.length == 2) {
                    if (split[1].equalsIgnoreCase("ips")) {
                        type = 1;
                    }
                }
                if (type == 0) { //Regular user bans
                    msg(Colors.Blue + "Ban list:" + Colors.White + " " + d.f.getBans());
                } else { //IP bans
                    msg(Colors.Blue + "IP Ban list:" + Colors.White + " " + d.f.getIpBans());
                }
            } else if (split[0].equalsIgnoreCase("/banip")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /banip [player] <reason> (optional) NOTE: this permabans IPs.");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (!getPlayer().hasControlOver(player)) {
                        msg(Colors.Rose + "You can't ban that user.");
                        return;
                    }

                    // adds player to ban list
                    this.d.f.c(player.getIP());

                    etc.getLoader().callHook(PluginLoader.Hook.IPBAN, new Object[]{getPlayer().getUser(), player.getUser(), split.length >= 3 ? etc.combineSplit(2, split, " ") : ""});

                    a.log(Level.INFO, "IP Banning " + player.getName() + " (IP: " + player.getIP() + ")");
                    msg(Colors.Rose + "IP Banning " + player.getName() + " (IP: " + player.getIP() + ")");

                    if (split.length > 2) {
                        player.kick("IP Banned by " + getPlayer().getName() + ": " + etc.combineSplit(2, split, " "));
                    } else {
                        player.kick("IP Banned by " + getPlayer().getName() + ".");
                    }
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/ban")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /ban [player] <reason> (optional)");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (!getPlayer().hasControlOver(player)) {
                        msg(Colors.Rose + "You can't ban that user.");
                        return;
                    }

                    // adds player to ban list
                    this.d.f.a(player.getName());

                    etc.getLoader().callHook(PluginLoader.Hook.BAN, new Object[]{getPlayer().getUser(), player.getUser(), split.length >= 3 ? etc.combineSplit(2, split, " ") : ""});

                    if (split.length > 2) {
                        player.kick("Banned by " + getPlayer().getName() + ": " + etc.combineSplit(2, split, " "));
                    } else {
                        player.kick("Banned by " + getPlayer().getName() + ".");
                    }
                    a.log(Level.INFO, "Banning " + player.getName());
                    msg(Colors.Rose + "Banning " + player.getName());
                } else {
                    //msg(Colors.Rose + "Can't find user " + split[1] + ".");
                    this.d.f.a(split[1]);
                    a.log(Level.INFO, "Banning " + split[1]);
                    msg(Colors.Rose + "Banning " + split[1]);
                }
            } else if (split[0].equalsIgnoreCase("/unban")) {
                if (split.length != 2) {
                    msg(Colors.Rose + "Correct usage is: /unban [player]");
                    return;
                }
                this.d.f.b(split[1]);
                msg(Colors.Rose + "Unbanned " + split[1]);
            } else if (split[0].equalsIgnoreCase("/unbanip")) {
                if (split.length != 2) {
                    msg(Colors.Rose + "Correct usage is: /unbanip [ip]");
                    return;
                }
                this.d.f.d(split[1]);
                msg(Colors.Rose + "Unbanned " + split[1]);
            } else if (split[0].equalsIgnoreCase("/kick")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /kick [player] <reason> (optional)");
                    return;
                }

                Player player = etc.getServer().matchPlayer(split[1]);

                if (player != null) {
                    if (!getPlayer().hasControlOver(player)) {
                        msg(Colors.Rose + "You can't kick that user.");
                        return;
                    }

                    etc.getLoader().callHook(PluginLoader.Hook.KICK, new Object[]{getPlayer().getUser(), player.getUser(), split.length >= 3 ? etc.combineSplit(2, split, " ") : ""});

                    if (split.length > 2) {
                        player.kick("Kicked by " + getPlayer().getName() + ": " + etc.combineSplit(2, split, " "));
                    } else {
                        player.kick("Kicked by " + getPlayer().getName() + ".");
                    }
                    a.log(Level.INFO, "Kicking " + player.getName());
                    msg(Colors.Rose + "Kicking " + player.getName());
                } else {
                    msg(Colors.Rose + "Can't find user " + split[1] + ".");
                }
            } else if (split[0].equalsIgnoreCase("/me")) {
                if (getPlayer().isMuted()) {
                    msg(Colors.Rose + "You are currently muted.");
                    return;
                }
                if (split.length == 1) {
                    return;
                }
                String prefix = getPlayer().getColor();
                String paramString2 = "* " + prefix + getPlayer().getName() + Colors.White + " " + paramString.substring(paramString.indexOf(" ")).trim();
                a.info("* " + getPlayer().getName() + " " + paramString.substring(paramString.indexOf(" ")).trim());
                this.d.f.a(new bg(paramString2));
            } else if (split[0].equalsIgnoreCase("/sethome")) {
                // player.k, player.l, player.m
                // x, y, z
                Warp home = new Warp();
                home.Location = getPlayer().getLocation();
                home.Group = ""; //no group neccessary, lol.
                home.Name = getPlayer().getName();
                etc.getInstance().changeHome(home);
                msg(Colors.Rose + "Your home has been set.");
            } else if (split[0].equalsIgnoreCase("/spawn")) {
                getPlayer().teleportTo(etc.getServer().getSpawnLocation());
            } else if (split[0].equalsIgnoreCase("/setspawn")) {
                this.d.e.m = (int) Math.ceil(getPlayer().getX());
                //Too lazy to actually update this considering it's not even used anyways.
                //this.d.e.n = (int) Math.ceil(e.m); //Not that the Y axis really matters since it tries to get the highest point iirc.
                this.d.e.o = (int) Math.ceil(getPlayer().getZ());
                a.info("Spawn position changed.");
                msg(Colors.Rose + "You have set the spawn to your current position.");
            } else if (split[0].equalsIgnoreCase("/home")) {
                Warp home = null;
                if (split.length > 1 && getPlayer().isAdmin()) {
                    home = etc.getDataSource().getHome(split[1]);
                } else {
                    home = etc.getDataSource().getHome(getPlayer().getName());
                }

                if (home != null) {
                    getPlayer().teleportTo(home.Location);
                } else if (split.length > 1 && getPlayer().isAdmin()) {
                    msg(Colors.Rose + "That player home does not exist");
                } else {
                    getPlayer().teleportTo(etc.getServer().getSpawnLocation());
                }
            } else if (split[0].equalsIgnoreCase("/warp")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /warp [warpname]");
                    return;
                }
                Player toWarp = getPlayer();
                Warp warp = null;
                if (split.length == 3 && getPlayer().canIgnoreRestrictions()) {
                    warp = etc.getDataSource().getWarp(split[1]);
                    toWarp = etc.getServer().matchPlayer(split[2]);
                } else {
                    warp = etc.getDataSource().getWarp(split[1]);
                }
                if (toWarp != null) {
                    if (warp != null) {
                        if (!getPlayer().isInGroup(warp.Group) && !warp.Group.equals("")) {
                            msg(Colors.Rose + "Warp not found.");
                        } else {
                            toWarp.teleportTo(warp.Location);
                            toWarp.sendMessage(Colors.Rose + "Woosh!");
                        }
                    } else {
                        msg(Colors.Rose + "Warp not found");
                    }
                } else {
                    msg(Colors.Rose + "Player not found.");
                }
            } else if (split[0].equalsIgnoreCase("/listwarps") && etc.getDataSource().hasWarps()) {
                if (split.length != 2 && split.length != 3) {
                    msg(Colors.Rose + "Available warps: " + Colors.White + etc.getDataSource().getWarpNames(getPlayer()));
                    return;
                }
            } else if (split[0].equalsIgnoreCase("/setwarp")) {
                if (split.length < 2) {
                    if (getPlayer().canIgnoreRestrictions()) {
                        msg(Colors.Rose + "Correct usage is: /setwarp [warpname] [group]");
                    } else {
                        msg(Colors.Rose + "Correct usage is: /setwarp [warpname]");
                    }
                    return;
                }
                if (split[1].contains(":")) {
                    msg("You can't set a warp with \":\" in its name");
                    return;
                }
                Warp warp = new Warp();
                warp.Name = split[1];
                warp.Location = getPlayer().getLocation();
                if (split.length == 3) {
                    warp.Group = split[2];
                } else {
                    warp.Group = "";
                }
                etc.getInstance().setWarp(warp);
                msg(Colors.Rose + "Created warp point " + split[1] + ".");
            } else if (split[0].equalsIgnoreCase("/removewarp")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /removewarp [warpname]");
                    return;
                }
                Warp warp = etc.getDataSource().getWarp(split[1]);
                if (warp != null) {
                    etc.getDataSource().removeWarp(warp);
                    msg(Colors.Blue + "Warp removed.");
                } else {
                    msg(Colors.Rose + "That warp does not exist");
                }
            } else if (split[0].equalsIgnoreCase("/lighter")) {
                if (MinecraftServer.b.containsKey(getPlayer().getName() + " lighter")) {
                    a.info(getPlayer().getName() + " failed to iron!");
                    msg(Colors.Rose + "You can't create another lighter again so soon");
                } else {
                    if (!getPlayer().canIgnoreRestrictions()) {
                        MinecraftServer.b.put(getPlayer().getName() + " lighter", Integer.valueOf(6000));
                    }
                    a.info(getPlayer().getName() + " created a lighter!");
                    getPlayer().giveItem(259, 1);
                }
            } else if ((paramString.startsWith("/#")) && (this.d.f.g(getPlayer().getName()))) {
                String str = paramString.substring(2);
                a.info(getPlayer().getName() + " issued server command: " + str);
                this.d.a(str, this);
            } else if (split[0].equalsIgnoreCase("/time")) {
                if (split.length == 2) {
                    if (split[1].equalsIgnoreCase("day")) {
                        etc.getServer().setRelativeTime(0);
                    } else if (split[1].equalsIgnoreCase("night")) {
                        etc.getServer().setRelativeTime(13000);
                    } else if (split[1].equalsIgnoreCase("check")) {
                        msg(Colors.Rose + "The time is " + etc.getServer().getRelativeTime() + "! (RAW: " + etc.getServer().getTime() + ")");
                    } else {
                        try {
                            etc.getServer().setRelativeTime(Long.parseLong(split[1]));
                        } catch (NumberFormatException ex) {
                            msg(Colors.Rose + "Please enter numbers, not letters.");
                        }
                    }
                } else if (split.length == 3) {
                    if (split[1].equalsIgnoreCase("raw")) {
                        try {
                            etc.getServer().setTime(Long.parseLong(split[2]));
                        } catch (NumberFormatException ex) {
                            msg(Colors.Rose + "Please enter numbers, not letters.");
                        }
                    }
                } else {
                    msg(Colors.Rose + "Correct usage is: /time [time|'day|night|check|raw'] (rawtime)");
                    return;
                }
            } else if (split[0].equalsIgnoreCase("/getpos")) {
                Player p = getPlayer();
                msg("Pos X: " + p.getX() + " Y: " + p.getY() + " Z: " + p.getZ());
                msg("Rotation: " + p.getRotation() + " Pitch: " + p.getPitch());

                double degreeRotation = ((p.getRotation() - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }
                msg("Compass: " + etc.getCompassPointForDirection(degreeRotation) + " (" + (Math.round(degreeRotation * 10) / 10.0) + ")");
            } else if (split[0].equalsIgnoreCase("/listplugins")) {
                msg(Colors.Rose + "Plugins" + Colors.White + ": " + etc.getLoader().getPluginList());
            } else if (split[0].equalsIgnoreCase("/reloadplugin")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /reloadplugin [plugin]");
                    return;
                }

                etc.getLoader().reloadPlugin(split[1]);
                msg(Colors.Rose + "Plugin reloaded.");
            } else if (split[0].equalsIgnoreCase("/enableplugin")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /enableplugin [plugin]");
                    return;
                }

                etc.getLoader().enablePlugin(split[1]);
                msg(Colors.Rose + "Plugin enabled.");
            } else if (split[0].equalsIgnoreCase("/disableplugin")) {
                if (split.length < 2) {
                    msg(Colors.Rose + "Correct usage is: /disableplugin [plugin]");
                    return;
                }

                etc.getLoader().disablePlugin(split[1]);
                msg(Colors.Rose + "Plugin disabled.");
            } else if (split[0].equalsIgnoreCase("/compass")) {
                double degreeRotation = ((getPlayer().getRotation() - 90) % 360);
                if (degreeRotation < 0) {
                    degreeRotation += 360.0;
                }

                msg(Colors.Rose + "Compass: " + etc.getCompassPointForDirection(degreeRotation));
            } else if (split[0].equalsIgnoreCase("/motd")) {
                for (String str : etc.getInstance().getMotd()) {
                    msg(str);
                }
            } else if (split[0].equalsIgnoreCase("/spawnmob")) {
                if (split.length == 1) {
                    msg(Colors.Rose + "Correct usage is: /spawnmob [name] <amount>");
                    return;
                }
                if (!Mob.isValid(split[1])) {
                    msg(Colors.Rose + "Invalid mob. Name has to start with a capital like so: Pig");
                    return;
                }

                if (split.length == 2) {
                    Mob mob = new Mob(split[1], getPlayer().getLocation());
                    mob.spawn();
                } else if (split.length == 3) {
                    try {
                        int mobnumber = Integer.parseInt(split[2]);
                        for (int i = 0; i < mobnumber; i++) {
                            Mob mob = new Mob(split[1], getPlayer().getLocation());
                            mob.spawn();
                        }
                    } catch (NumberFormatException nfe) {
                        if (!Mob.isValid(split[2])) {
                            msg(Colors.Rose + "Invalid mob name or number of mobs.");
                            msg(Colors.Rose + "Mob names have to start with a capital like so: Pig");
                        } else {
                            Mob mob = new Mob(split[1], getPlayer().getLocation());
                            mob.spawn(new Mob(split[2]));
                        }
                    }
                } else if (split.length == 4) {
                    try {
                        int mobnumber = Integer.parseInt(split[3]);
                        if (!Mob.isValid(split[2])) {
                            msg(Colors.Rose + "Invalid rider. Name has to start with a capital like so: Pig");
                        } else {
                            for (int i = 0; i < mobnumber; i++) {
                                Mob mob = new Mob(split[1], getPlayer().getLocation());
                                mob.spawn(new Mob(split[2]));
                            }
                        }
                    } catch (NumberFormatException nfe) {
                        msg(Colors.Rose + "Invalid number of mobs.");
                    }
                }
            } else if (split[0].equalsIgnoreCase("/clearinventory")) {
                Player target = getPlayer();
                if (split.length >= 2 && getPlayer().isAdmin()) {
                    target = etc.getServer().matchPlayer(split[1]);
                }
                if (target != null) {
                    Inventory inv = target.getInventory();
                    inv.clearContents();
                    inv = target.getCraftingTable();
                    inv.clearContents();
                    inv = target.getEquipment();
                    inv.clearContents();
                    inv.updateInventory();
                    if (!target.getName().equals(getPlayer().getName())) {
                        msg(Colors.Rose + "Cleared " + target.getName() + "'s inventory.");
                    }
                } else {
                    msg(Colors.Rose + "Target not found");
                }
            } else if (split[0].equalsIgnoreCase("/version")) {
                msg(Colors.Gold + "Hey0 Server Mod Build " + etc.getInstance().getVersion());
            } else {
                a.info(getPlayer().getName() + " tried command " + paramString);
                if (etc.getInstance().showUnknownCommand()) {
                    msg(Colors.Rose + "Unknown command");
                }
            }
        } catch (Throwable ex) { //Might as well try and catch big exceptions before the server crashes from a stack overflow or something
            a.log(Level.SEVERE, "Exception in command handler (Report this to hey0 unless you did something dumb like enter letters as numbers):", ex);
            if (getPlayer().isAdmin()) {
                msg(Colors.Rose + "Exception occured. Check the server for more info.");
            }
        }
		/*
		if (paramString.toLowerCase().startsWith("/me ")) {
			paramString = "* " + this.e.ar + " " + paramString.substring(paramString.indexOf(" ")).trim();
			a.info(paramString);
			this.d.f.a(new bg(paramString));
		} else if (paramString.toLowerCase().startsWith("/tell ")) {
			String[] arrayOfString = paramString.split(" ");
			if (arrayOfString.length >= 3) {
				paramString = paramString.substring(paramString.indexOf(" ")).trim();
				paramString = paramString.substring(paramString.indexOf(" ")).trim();

				paramString = "§7" + this.e.ar + " whispers " + paramString;
				a.info(paramString + " to " + arrayOfString[1]);
				if (!this.d.f.a(arrayOfString[1], new bg(paramString))) {
					b(new bg("§cThere's no player by that name online."));
				}
			}
		} else if (paramString.toLowerCase().equalsIgnoreCase("/home")) {
			a.info(this.e.ar + " returned home");
			int m = this.d.e.e(this.d.e.m, this.d.e.o);
			a(this.d.e.m + 0.5D, m + 1.5D, this.d.e.o + 0.5D, 0.0F, 0.0F);
		} else {
			String str;
			if (this.d.f.g(this.e.ar)) {
				str = paramString.substring(1);
				a.info(this.e.ar + " issued server command: " + str);
				this.d.a(str, this);
			} else {
				str = paramString.substring(1);
				a.info(this.e.ar + " tried command: " + str);
			}
		}
		*/
	}

	public void a(q paramq) {
		if (paramq.b == 1){
			etc.getLoader().callHook(PluginLoader.Hook.ARM_SWING, new Object[]{e});
			this.e.E();
		}
	}

	public void a(jr paramjr) {
		this.b.a("Quitting");
	}

	public int b() {
		return this.b.d();
	}

	public void b(String paramString) {
		b(new bg("§7" + paramString));
	}

	public String c() {
		return this.e.ar;
	}

	public void a(u paramu) {
		if (!getPlayer().canBuild()) {
            getPlayer().getInventory().clearContents();
            getPlayer().getCraftingTable().clearContents();
            getPlayer().getEquipment().clearContents();
            getPlayer().getInventory().updateInventory();
            return;
        }
		
		if (paramu.a == -1){
			hj[] temp = this.e.ak.a;
			this.e.ak.a = paramu.b;
			if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.INVENTORY_CHANGE, new Object[]{e})) {
				this.e.ak.a = temp;
				getPlayer().getInventory().updateInventory();
			}
		}
		if (paramu.a == -2){
			hj[] temp = this.e.ak.c;
			this.e.ak.c = paramu.b;
			if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.EQUIPMENT_CHANGE, new Object[]{e})) {
				this.e.ak.c = temp;
				getPlayer().getInventory().updateInventory();
			}
		}
		if (paramu.a == -3){
			hj[] temp = this.e.ak.b;
			this.e.ak.b = paramu.b;
			 if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.CRAFTINVENTORY_CHANGE, new Object[]{e})) {
				 this.e.ak.b = temp;
	                getPlayer().getInventory().updateInventory();
			 }
		}
	}

	public void d() {
		this.b.a(new u(-1, this.e.ak.a));
		this.b.a(new u(-2, this.e.ak.c));
		this.b.a(new u(-3, this.e.ak.b));
	}

	public void a(jc paramjc) {
		if (paramjc.e.d("x") != paramjc.a)return;
		if (paramjc.e.d("y") != paramjc.b)return;
		if (paramjc.e.d("z") != paramjc.c)return;

		ay localay = this.d.e.k(paramjc.a, paramjc.b, paramjc.c);
		if (localay != null) {
			if (localay instanceof hx) { //Chest
                hx chest = (hx) localay;
                hj[] temp = chest.getContents();
                localay.a(paramjc.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Chest(chest)})) {
                    chest.setContents(temp);
                }
            } else if (localay instanceof ds) { //Furnace
                ds furnace = (ds) localay;
                hj[] temp = furnace.getContents();
                localay.a(paramjc.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Furnace(furnace)})) {
                    furnace.setContents(temp);
                }
            } else if (localay instanceof ji) { //Sign
                ji sign = (ji) localay;
                String[] temp = sign.e;
                localay.a(paramjc.e);
                if ((Boolean) etc.getLoader().callHook(PluginLoader.Hook.COMPLEX_BLOCK_CHANGE, new Object[]{e, new Sign(sign)})) {
                    sign.e = temp;
                }
            }
		/*	try {
				localay.a(paramjc.e);
			} catch (Exception localException) {
			}*/
			localay.c();
		}
	}

	public void a(a parama) {
		dw localdw = this.d.e.a(parama.b);
		if ((localdw != null) && (this.e.g(localdw)))
			localdw.a(this.e);
	}
}