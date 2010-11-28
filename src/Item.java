
/**
 * Item.java - Item stuff.
 * @author James
 */
public class Item {

    private int itemId = 1, amount = 1, slot = -1;

    /**
     * Create an item with an id of 1 and amount of 1
     */
    public Item() {
    }

    /**
     * Creates an item with specified id and amount
     * @param itemId
     * @param amount
     */
    public Item(int itemId, int amount) {
        this.itemId = itemId;
        this.amount = amount;
    }

    /**
     * Creates an item with specified id, amount and slot
     * @param itemId
     * @param amount
     * @param slot
     */
    public Item(int itemId, int amount, int slot) {
        this.itemId = itemId;
        this.amount = amount;
        this.slot = slot;
    }

    /**
     * Creates an item from the actual item class
     * @param hl
     */
    public Item(hl hl) {
        itemId = hl.c;
        amount = hl.a;
    }

    /**
     * Returns the item id
     * @return item id
     */
    public int getItemId() {
        return itemId;
    }

    /**
     * Sets item id to specified id
     * @param itemId
     */
    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    /**
     * Returns the amount
     * @return amount
     */
    public int getAmount() {
        return amount;
    }

    /**
     * Sets the amount
     * @param amount
     */
    public void setAmount(int amount) {
        this.amount = amount;
    }

    /**
     * Returns true if specified item id is a valid item id.
     * @param itemId
     * @return
     */
    public static boolean isValidItem(int itemId) {
        if (itemId < fj.c.length) {
            return fj.c[itemId] != null;
        }
        return false;
    }

    /**
     * Returns this item's current slot. -1 if no slot is specified
     * @return slot
     */
    public int getSlot() {
        return slot;
    }

    /**
     * Sets this item's slot
     * @param slot
     */
    public void setSlot(int slot) {
        this.slot = slot;
    }
}
