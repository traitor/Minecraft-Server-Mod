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
}
