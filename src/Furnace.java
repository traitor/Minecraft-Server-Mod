/**
 * Furnace.java - Interface for furnaces
 * @author James
 */
public class Furnace implements ComplexBlock {
    private df furnace;

    public Furnace(df furnace) {
        this.furnace = furnace;
    }

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
        if (slot < 37 && slot >= 0) {
            if (item.getAmount() <= 0)
                furnace.getContents()[slot] = null;
            else if(Item.isValidItem(item.getItemId()))
                furnace.getContents()[slot] = new gp(item.getItemId(), item.getAmount());
        } else if (slot == -1) {
            int newSlot = getEmptySlot();
            if (newSlot != -1) {
                furnace.getContents()[newSlot] = new gp(item.getItemId(), item.getAmount());
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
        if (slot < 37 && slot >= 0)
            if (furnace.getContents()[slot] != null)
                return new Item(furnace.getContents()[slot].c, furnace.getContents()[slot].a, slot);
        return null;
    }

    /**
     * Retrieves from the slot
     * @param id
     * @return item
     */
    public Item getItemFromId(int id) {
        for (int i = 0; furnace.getContents().length > i; i++) {
            if (furnace.getContents()[i] == null)
                continue;
            if (furnace.getContents()[i].c == id)
                return new Item(furnace.getContents()[i].c, furnace.getContents()[i].a, i);
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
        for (int i = 0; furnace.getContents().length > i; i++) {
            if (furnace.getContents()[i] == null)
                continue;
            if (furnace.getContents()[i].c == id && furnace.getContents()[i].a <= maxAmount)
                return new Item(furnace.getContents()[i].c, furnace.getContents()[i].a, i);
        }
        return null;
    }

    /**
     * Gets the nearest empty slot. -1 if there's no empty slots
     * @return nearest empty slot
     */
    public int getEmptySlot() {
        for (int i = 0; furnace.getContents().length > i; i++) {
            if (furnace.getContents()[i] != null)
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
        if (slot < 37 && slot >= 0)
            furnace.getContents()[slot] = null;
    }

    /**
     * Removes the item. No slot needed, it will go through the
     * inventory until the amount specified is removed.
     * @param item item id and amount to remove
     */
    public void removeItem(Item item) {
        int amount = item.getAmount();
        int itemId = item.getItemId();
        for (int i = 0; furnace.getContents().length > i; i++) {
            if (furnace.getContents()[i] == null)
                continue;
            if (furnace.getContents()[i].c == itemId) {
                int tempAmount = furnace.getContents()[i].a;
                tempAmount -= amount;
                if (tempAmount <= 0) {
                    amount -= furnace.getContents()[i].a;
                    furnace.getContents()[i] = null;
                } else {
                    furnace.getContents()[i].a = tempAmount;
                }
                if (amount <= 0)
                    return;
            }
        }
    }

    /**
     * Checks to see if this furnace has one slot that has the item id
     * and equal or more to the amount.
     * @param itemId
     * @param minimum
     * @param maximum
     * @return
     */
    public boolean hasItem(int itemId, int minimum, int maximum) {
        for (int i = 0; furnace.getContents().length > i; i++)
            if (furnace.getContents()[i] != null)
                if (furnace.getContents()[i].c == itemId &&
                    furnace.getContents()[i].a >= minimum &&
                    furnace.getContents()[i].a <= maximum)
                    return true;
        return false;
    }

    public void update() {
        furnace.c();
    }
}
