/**
 * Mob.java - Interface for mobs
 * @author James
 */
public class Mob {
    private String mob = "Chicken", mob2 = null;
    private Location location;

    /**
     * Creates a mob interface
     * @param mob name of mob
     * @param location location of mob
     */
    public Mob(String mob, Location location) {
        this.mob = mob;
        this.location = location;
    }

    /**
     * Creates a mob interface
     * @param mob name of mob
     * @param location location to spawn mob
     * @param rider rider will ride the mob
     */
    public Mob(String mob, Location location, String rider) {
        this.mob = mob;
        this.location = location;
        this.mob2 = rider;
    }

    /**
     * Spawns the mob
     */
    public void spawn() {
        if (!isValid(mob))
            return;

        dy localdy = etc.getMCServer().e;
        is localis = (is) gr.a(mob, localdy); // (is) gr.a(split[1], etc.getMCServer().e);// (is) c.getConstructor(new Class[]{dy.class}).newInstance(new Object[]{paramdy});

        localis.c(location.x + 0.5f, location.y, location.z + 0.5f, location.rotX, 0f);
        localdy.a(localis);

        if (mob2 != null) {
            if (!isValid(mob2))
                return;
            is localek = (is) gr.a(mob2, localdy);
            localek.c(location.x, location.y, location.z, localis.r, 0f);
            localdy.a(localek);
            localek.e(localis);
        }
    }

    /**
     * Checks to see if the mob is a valid mob
     * @param mob the mob to check
     * @return true of mob is valid
     */
    public static boolean isValid(String mob) {
        if (mob == null)
            return false;
        return gr.a(mob, etc.getMCServer().e) instanceof is;
    }
}
