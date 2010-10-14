/**
 * ItemArray.java - Interface to gp[] so I don't have to copy+paste
 * this a bunch of times
 * @author James
 */
public abstract class ItemArray {
    /**
     * Adds the specified item. If the item doesn't have a slot,
     * it will get the nearest available slot. If amount is equal
     * to 0, it will delete the item if a slot is specified.
     * @param item item to add
     */
    public void addItem(Item item) {
        if (item == null)
            return;

        int slot = item.getSlot();
        if (slot < getArray().length && slot >= 0) {
            if (item.getAmount() <= 0)
                getArray()[slot] = null;
            else if(Item.isValidItem(item.getItemId()))
                getArray()[slot] = new gp(item.getItemId(), item.getAmount());
        } else if (slot == -1) {
            int newSlot = getEmptySlot();
            if (newSlot != -1) {
                getArray()[newSlot] = new gp(item.getItemId(), item.getAmount());
                item.setSlot(newSlot);
            }
        }
    }

    /**
     * Retrieves from the slot
     * @param slot slot to get item from
     * @return item
     */
    public Item getItemFromSlot(int slot) {
        if (slot < getArray().length && slot >= 0)
            if (getArray()[slot] != null)
                return new Item(getArray()[slot].c, getArray()[slot].a, slot);
        return null;
    }

    /**
     * Retrieves from the slot
     * @param id
     * @return item
     */
    public Item getItemFromId(int id) {
        for (int i = 0; getArray().length > i; i++) {
            if (getArray()[i] == null)
                continue;
            if (getArray()[i].c == id)
                return new Item(getArray()[i].c, getArray()[i].a, i);
        }
        return null;
    }

    /**
     * Retrieves from the slot
     * @param id
     * @param maxAmount
     * @return item
     */
    public Item getItemFromId(int id, int maxAmount) {
        for (int i = 0; getArray().length > i; i++) {
            if (getArray()[i] == null)
                continue;
            if (getArray()[i].c == id && getArray()[i].a <= maxAmount)
                return new Item(getArray()[i].c, getArray()[i].a, i);
        }
        return null;
    }

    /**
     * Gets the nearest empty slot. -1 if there's no empty slots
     * @return nearest empty slot
     */
    public int getEmptySlot() {
        for (int i = 0; getArray().length > i; i++) {
            if (getArray()[i] != null)
                continue;
            return i;
        }
        return -1;
    }

    /**
     * Removes the item from the slot
     * @param slot slot to remove item from
     */
    public void removeItem(int slot) {
        if (slot < getArray().length && slot >= 0)
            getArray()[slot] = null;
    }

    /**
     * Removes the item. No slot needed, it will go through the
     * inventory until the amount specified is removed.
     * @param item item id and amount to remove
     */
    public void removeItem(Item item) {
        int amount = item.getAmount();
        int itemId = item.getItemId();
        for (int i = 0; getArray().length > i; i++) {
            if (getArray()[i] == null)
                continue;
            if (getArray()[i].c == itemId) {
                int tempAmount = getArray()[i].a;
                tempAmount -= amount;
                amount -= getArray()[i].a;
                if (tempAmount <= 0) {
                    getArray()[i] = null;
                } else {
                    getArray()[i].a = tempAmount;
                }
                if (amount <= 0)
                    return;
            }
        }
    }

    /**
     * Checks to see if this getArray() has one slot that has the item id
     * and equal or more to the amount.
     * @param itemId
     * @param minimum
     * @param maximum
     * @return
     */
    public boolean hasItem(int itemId, int minimum, int maximum) {
        for (int i = 0; getArray().length > i; i++)
            if (getArray()[i] != null)
                if (getArray()[i].c == itemId &&
                    getArray()[i].a >= minimum &&
                    getArray()[i].a <= maximum)
                    return true;
        return false;
    }

    /**
     * Clears the contents
     */
    public void clearContents() {
        for (int i = 0; getArray().length > i; i++) {
            getArray()[i] = null;
        }
    }

    /**
     * Gets the actual item array
     * @return item array
     */
    public abstract gp[] getArray();
}
