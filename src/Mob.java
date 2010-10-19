/**
 * Mob.java - Interface for mobs
 * @author James
 */
public class Mob extends BaseEntity {
    private gh mob = null;

    /**
     * Creates a mob interface
     * @param mob name of mob
     */
    public Mob(String mob) {
        this.mob = (gh) gr.a(mob, etc.getMCServer().e);
        this.entity = this.mob;
    }

    /**
     * Creates a mob interface
     * @param mob name of mob
     * @param location location of mob
     */
    public Mob(String mob, Location location) {
        this.mob = (gh) gr.a(mob, etc.getMCServer().e);
        this.entity = this.mob;
        teleportTo(location);
    }

    /**
     * Creates a mob interface
     * @param mob name of mob
     */
    public Mob(gh mob) {
        this.mob = mob;
        this.entity = this.mob;
    }

    /**
     * Spawns this mob
     */
    public void spawn() {
        spawn(null);
    }

    /**
     * Spawns this mob with a rider
     * @param rider
     */
    public void spawn(Mob rider) {
        dy localdy = etc.getMCServer().e;

        mob.c(getX() + 0.5f, getY(), getZ() + 0.5f, getRotation(), 0f);
        localdy.a(mob);

        if (rider != null) {
            gh mob2 = rider.getMob();
            mob2.c(getX(), getY(), getZ(), getRotation(), 0f);
            localdy.a(mob2);
            mob2.e(mob);
        }
    }

    /**
     * Returns this mob's name
     * @return name
     */
    public String getName() {
        return gr.b(mob);
    }

    /**
     * Drops this mob's loot. Automatically called if health is set to 0.
     */
    public void dropLoot() {
        mob.f(null);
    }

    public void setHealth(int health) {
        super.setHealth(health);
        if (health <= 0)
            dropLoot();
    }

    /**
     * Returns the actual mob
     * @return
     */
    public gh getMob() {
        return mob;
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
