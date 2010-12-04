
public abstract class ItemContainerBlock<C extends ay & ke & Container<hn>> extends ItemArray implements ComplexBlock  {
    private C container;

    /**
     * Creates a chest interface
     * @param localay
     */
    public ItemContainerBlock(C container) {
        this.container = container;
    }

    public int getX() {
        return container.b;
    }

    public int getY() {
        return container.c;
    }

    public int getZ() {
        return container.d;
    }

    public void update() {
        container.c();
    }

    /**
     * Get the underlying array of this container.
     */
    protected hn[] getArray() {
        return container.getContents();
    }
    
    /**
     * Set the contents of this container.
     * @param contents contents to set
     */
    protected void setArray(hn[] contents) {
        container.setContents(contents);
    }
    
    /**
     * Returns the size of this container.
     * @return
     */
    public abstract int getContentSize();



    /**
     * Returns the contents of this container.
     * @return contents
     */
    public Item[] getContents() {
        Item[] rt = new Item[getContentSize()];
        for (int i = 0; i < getContentSize(); i++) {
            rt[i] = (getArray()[i] != null) ? new Item(getArray()[i]):null;
        }

        return rt;
    }

    /**
     * Sets the contents
     * @param contents contents to set
     */
    public void setContents(Item[] contents) {
        hn[] newcontents = new hn[getContentSize()];
        for (int i = 0; i < getContentSize(); i++) {
            newcontents[i] = (getArray()[i] != null) ? new hn(contents[i].getItemId(), contents[i].getAmount()):null;
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
        return String.format("Chest[x=%d, y=%d, z=%d]", getX(), getY(), getZ());
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
        final Chest other = (Chest) obj;
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
