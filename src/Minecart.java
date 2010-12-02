/**
 * Minecart - Used for manipulating minecarts
 *
 * @author tw1nk
 */
public class Minecart extends BaseVehicle {
    /**
     * Type of minecart
     */
    public enum Type {
        /**
         * Base minecart.
         */
        Minecart,
        /**
         * Powered minecart. Has storage for fuel.
         */
        PoweredMinecart,
        /**
         * Storage minecart. Has storage for items.
         */
        StorageCart,
    }

    /**
     * Creates an interface for minecart.
     * @param cart
     */
    public Minecart(jn cart) {
        super(cart);
    }
    
    /**
     * Returns the entity we're wrapping.
     * @return
     */ 
    public jn getEntity() {
        return (jn) entity;
    }

    /**
     * Set damage on Mineentity
     * @param damage over 40 and you "kill" the mineentity
     */
    public void setDamage(int damage) {
        getEntity().a = damage;
    }

    /**
     * Returns damage for mineentity
     * @return returns current damage
     */
    public int getDamage() {
        return getEntity().a;
    }

    /**
     * Returns the type of this minecart.
     * @return type
     */
    public Type getType() {
        switch (getEntity().d) {
            case 1: return Type.Minecart;
            case 2: return Type.StorageCart;
            case 3: return Type.PoweredMinecart;
            default: return Type.Minecart;
        }
    }

    /**
     * Returns the storage for this minecart. Returns null if minecart is not a storage or powered
     * minecart.
     * @return storage
     */
    public StorageMinecart getStorage() {
        if (getType() == Type.StorageCart || getType() == Type.PoweredMinecart)
            return new StorageMinecart(getEntity());
        return null;
    }
}
