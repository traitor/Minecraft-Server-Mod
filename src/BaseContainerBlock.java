/**
 * Generic superclass for Chests and Furnaces, as they are really similar.
 * 
 * @author lightweight
 * 
 * @param <C>
 *            The type of container we wish to wrap.
 */
public abstract class BaseContainerBlock<C extends OTileEntity & OIInventory & Container<OItemStack>> extends ItemArray<C> implements Inventory {
    private final String name;

    /**
     * Create a BaseContainerBlock to act as a wrapper for a given container.
     * 
     * @param block
     *            The in-world block to 'envelop'.
     * @param reference
     *            Shows in toString().
     */
    public BaseContainerBlock(C block, String reference) {
        super(block);
        this.name = reference;
    }

    public int getX() {
        return container.e;
    }

    public int getY() {
        return container.f;
    }

    public int getZ() {
        return container.g;
    }

    public Block getBlock() {
        return etc.getServer().getBlockAt(getX(), getY(), getZ());
    }

    public void update() {
        container.c();
    }

    public String getName() {
        return container.getName();
    }

    public void setName(String value) {
        container.setName(value);
    }

    /**
     * Tests the given object to see if it equals this object
     * 
     * @param obj
     *            the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        // Supress warning since we've already returned if class is wrong.
        @SuppressWarnings("unchecked")
        final BaseContainerBlock<C> other = (BaseContainerBlock<C>) obj;
        if (this.getX() != other.getX())
            return false;
        if (this.getY() != other.getY())
            return false;
        if (this.getZ() != other.getZ())
            return false;
        return true;
    }

    /**
     * Returns a semi-unique hashcode for this object
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.getX();
        hash = 97 * hash + this.getY();
        hash = 97 * hash + this.getZ();
        return hash;
    }

    @Override
    public String toString() {
        return String.format(name + " [x=%d, y=%d, z=%d]", getX(), getY(), getZ());
    }
}
