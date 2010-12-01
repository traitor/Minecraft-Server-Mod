/**
 * Furnace.java - Interface for furnaces
 * @author James
 */
public class Furnace extends ItemArray implements ComplexBlock {

    private du furnace;

    /**
     * Creates a furnace interface
     * @param furnace
     */
    public Furnace(du furnace) {
        this.furnace = furnace;
    }

    public int getX() {
        return furnace.b;
    }

    public int getY() {
        return furnace.c;
    }

    public int getZ() {
        return furnace.d;
    }

    public void update() {
        furnace.c();
    }

    public hm[] getArray() {
        return furnace.getContents();
    }

    /**
     * Returns the size of contents
     * @return size
     */
    public int getContentSize() {
        return furnace.a();
    }

    /**
     * Sets the contents of this furnace
     * @param contents contents of this furnace
     */
    public void setArray(hm[] contents) {
        furnace.setContents(contents);
    }

    /**
     * Returns the contents of this furnace
     * @return contents
     */
    public Item[] getContents() {
        Item[] rt = new Item[getContentSize()];
        for (int i = 0; i < getContentSize(); i++) {
            rt[i] = new Item(getArray()[i]);
        }

        return rt;
    }

    /**
     * Sets the contents of this furnace
     * @param contents contents to set
     */
    public void setContents(Item[] contents) {
        hm[] newcontents = new hm[getContentSize()];
        for (int i = 0; i < getContentSize(); i++) {
            newcontents[i] = new hm(contents[i].getItemId(), contents[i].getAmount());
        }

        setArray(newcontents);
    }

    /**
     * Returns a String value representing this object
     * 
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return String.format("Furnace[x=%d, y=%d, z=%d]", getX(), getY(), getZ());
    }

    /**
     * Tests the given object to see if it equals this object
     * 
     * @param obj the object to test
     * @return true if the two objects match
     */
    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Furnace other = (Furnace) obj;
        if (this.getX() != other.getX()) {
            return false;
        }
        if (this.getY() != other.getY()) {
            return false;
        }
        if (this.getZ() != other.getZ()) {
            return false;
        }
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
}
