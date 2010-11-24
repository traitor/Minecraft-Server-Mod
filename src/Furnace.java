
/**
 * Furnace.java - Interface for furnaces
 * 
 * @author James
 */
public class Furnace extends ItemArray implements ComplexBlock {

    private dt furnace;

    /**
     * Creates a furnace interface
     * 
     * @param furnace
     */
    public Furnace(dt furnace) {
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

    public hl[] getArray() {
        return furnace.getContents();
    }
    public int getContentSize() {
        return furnace.a();
    }
    public void setArray(hl[] contents){
        furnace.setContents(contents);
    }
    public Item[] getContents() {
        Item[] rt = new Item[getContentSize()];
        for(int i = 0; i < getContentSize();i++)
            rt[i] = new Item(getArray()[i]);

        return rt;
    }
    public void setContents(Item[] contents) {
        hl[] newcontents = new hl[getContentSize()];
        for(int i = 0; i < getContentSize();i++)
            newcontents[i] = new hl(contents[i].getItemId(),contents[i].getAmount());

        setArray(newcontents);
    }
}
