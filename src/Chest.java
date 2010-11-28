
/**
 * Chest.java - Interface to chests.
 * @author James
 */
public class Chest extends ItemArray implements ComplexBlock {

    private ia chest;

    /**
     * Creates a chest interface
     * @param chest
     */
    public Chest(ia chest) {
        this.chest = chest;
    }

    public int getX() {
        return chest.b;
    }

    public int getY() {
        return chest.c;
    }

    public int getZ() {
        return chest.d;
    }

    public void update() {
        chest.c();
    }

    public hl[] getArray() {
        return chest.getContents();
    }

    /**
     * Returns the content size of this chest
     * @return
     */
    public int getContentSize() {
        return 36;
    }

    /**
     * Sets the contents
     * @param contents contents to set
     */
    public void setArray(hl[] contents) {
        chest.setContents(contents);
    }

    /**
     * Returns the contents of this chest
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
     * Sets the contents
     * @param contents contents to set
     */
    public void setContents(Item[] contents) {
        hl[] newcontents = new hl[getContentSize()];
        for (int i = 0; i < getContentSize(); i++) {
            newcontents[i] = new hl(contents[i].getItemId(), contents[i].getAmount());
        }
        setArray(newcontents);
    }
}
