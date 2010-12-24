/**
 * Inventory.java - Interface to player inventories
 * 
 * @author James
 */
public interface Inventory {
    /**
     * Updates this inventory, sending the new information to clients
     */
    public void update();

    /**
     * Clears this inventory
     */
    public void clearContents();

    public void addItem(Item item);
    public Item getItemFromSlot(int slot);
    public Item getItemFromId(Item.Type type);
    public Item getItemFromId(int id);
    public Item getItemFromId(Item.Type type, int maxAmount);
    public Item getItemFromId(int id, int maxAmount);
    public int getEmptySlot();
    public void removeItem(int slot);
    public void setSlot(Item item, int slot);
    public void setSlot(Item.Type type, int amount, int slot);
    public void setSlot(int itemId, int amount, int slot);
    public void setSlot(int itemId, int amount, int damage, int slot);
    public void removeItem(Item item);
    public void removeItem(Item.Type type, int amount);
    public void removeItem(int id, int amount);
    public boolean hasItem(Item.Type type);
    public boolean hasItem(int itemId);
    public boolean hasItem(Item.Type type, int minimum);
    public boolean hasItem(int itemId, int minimum);
    public boolean hasItem(int itemId, int minimum, int maximum);
    public Item[] getContents();
    public void setContents(Item[] contents);
    public int getContentsSize();
    public String getName();
    public void setName(String value);
}
