
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
     * @param hm
     */
    public Item(hm hm) {
        itemId = hm.c;
        amount = hm.a;
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
        if (itemId < fk.c.length) {
            return fk.c[itemId] != null;
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

    /**
     * Returns a String value representing this object
     * 
     * @return String representation of this object
     */
    @Override
    public String toString() {
        return String.format("Item[id=%d, amount=%d, slot=%d]", itemId, amount, slot);
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
        final Item other = (Item) obj;
        if (this.itemId != other.itemId) {
            return false;
        }
        if (this.amount != other.amount) {
            return false;
        }
        if (this.slot != other.slot) {
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
        hash = 97 * hash + this.itemId;
        hash = 97 * hash + this.amount;
        hash = 97 * hash + this.slot;
        return hash;
    }
}
