
/**
 * Chest.java - Interface to chests.
 * 
 * @author James
 */
public class Chest extends ItemArray implements ComplexBlock {

    private hx chest;

    /**
     * Creates a chest interface
     * 
     * @param chest
     */
    public Chest(hx chest) {
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

    public hj[] getArray() {
        return chest.getContents();
    }
    public int getContentSize() {
        return 36;
    }
    public void setArray(hj[] contents){
        chest.setContents(contents);
    }
    public Item[] getContents() {
        Item[] rt = new Item[getContentSize()];
        for(int i = 0; i < getContentSize();i++)
            rt[i] = new Item(getArray()[i]);

        return rt;
    }
    public void setContents(Item[] contents) {
        hj[] newcontents = new hj[getContentSize()];
        for(int i = 0; i < getContentSize();i++)
            newcontents[i] = new hj(contents[i].getItemId(),contents[i].getAmount());
        setArray(newcontents);
    }
}
