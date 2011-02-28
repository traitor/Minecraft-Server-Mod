/**
 * Mob.java - Interface for mobs
 * 
 * @author James
 */
public class Mob extends LivingEntity {
    /**
     * Creates a mob interface
     * 
     * @param locallb
     *            name of mob
     */
    public Mob(OEntityLiving locallb) {
        super(locallb);
    }

    /**
     * Creates a mob interface
     * 
     * @param mob
     *            name of mob
     */
    public Mob(String mob) {
        this((OEntityLiving) OEntityList.a(mob, etc.getMCServer().e));
    }

    /**
     * Creates a mob interface
     * 
     * @param mobName
     *            name of mob
     * @param location
     *            location of mob
     */
    public Mob(String mobName, Location location) {
        this(mobName);
        teleportTo(location);
    }

    /**
     * Spawns this mob
     */
    public void spawn() {
        spawn(null);
    }

    /**
     * Spawns this mob with a rider
     * 
     * @param rider
     */
    public void spawn(Mob rider) {
        OWorld localff = etc.getMCServer().e;

        entity.c(getX() + 0.5f, getY(), getZ() + 0.5f, getRotation(), 0f);
        localff.a(entity);

        if (rider != null) {
            OEntityLiving mob2 = rider.getMob();
            mob2.c(getX(), getY(), getZ(), getRotation(), 0f);
            localff.a(mob2);
            mob2.e(entity);
        }
    }

    /**
     * Returns this mob's name
     * 
     * @return name
     */
    public String getName() {
        return OEntityList.b(entity);
    }

    /**
     * Drops this mob's loot. Automatically called if health is set to 0.
     */
    public void dropLoot() {
        // Forced cast to get to the intended method
        getEntity().a((OEntity) null);
    }

    @Override
    public void setHealth(int health) {
        super.setHealth(health);
        if (health <= 0)
            dropLoot();
    }

    /**
     * Returns the actual mob
     * 
     * @return
     */
    public OEntityLiving getMob() {
        return getEntity();
    }

    /**
     * Checks to see if the mob is a valid mob
     * 
     * @param mob
     *            the mob to check
     * @return true of mob is valid
     */
    public static boolean isValid(String mob) {
        if (mob == null)
            return false;
        OEntity c = OEntityList.a(mob, etc.getMCServer().e);
        return c instanceof OIMobs || c instanceof OIAnimals;
    }
}
