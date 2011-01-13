/**
 * ItemArray.java - Interface to jh[] so I don't have to copy+paste this a bunch
 * of times
 * 
 * @author James
 */
public abstract class ItemArray<C extends Container<jl>> {
    protected C container;
    
    public ItemArray(C c) {
        this.container = c;
    }

    public int getContentsSize() {
        return container.getContentsSize();
    }
    
    /**
     * Adds the specified item. If the item doesn't have a slot, it will get the
     * nearest available slot. If amount is equal to 0, it will delete the item
     * if a slot is specified.
     * 
     * @param item item to add
     */
    public void addItem(Item item) {
        if (item == null) {
            return;
        }

        int slot = item.getSlot();
        int size = getContentsSize();

        if (slot < size && slot >= 0) {
            if (item.getAmount() <= 0) {
                setSlot(null, slot);
            } else if (Item.isValidItem(item.getItemId())) {
                setSlot(item, slot);
            }
        } else if (slot == -1) {
            int newSlot = getEmptySlot();
            if (newSlot != -1) {
                setSlot(item, newSlot);
                item.setSlot(newSlot);
            }
        }
    }

    /**
     * Retrieves from the slot
     * 
     * @param slot slot to get item from
     * @return item
     */
    public Item getItemFromSlot(int slot) {
        int size = getContentsSize();

        if (slot < size && slot >= 0) {
            jl result = container.getContentsAt(slot);
            if (result != null) {
                return new Item(result, slot);
            }
        }

        return null;
    }

    /**
     * Retrieves from the slot
     * 
     * @param type
     * @return item
     */
    public Item getItemFromId(Item.Type type) {
        return getItemFromId(type.getId());
    }

    /**
     * Retrieves from the slot
     * 
     * @param id
     * @return item
     */
    public Item getItemFromId(int id) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == id)) {
                return item;
            }
        }

        return null;
    }

    /**
     * Retrieves from the slot
     * 
     * @param type
     * @param maxAmount
     * @return item
     */
    public Item getItemFromId(Item.Type type, int maxAmount) {
        return getItemFromId(type.getId());
    }

    /**
     * Retrieves from the slot
     * 
     * @param id
     * @param maxAmount
     * @return item
     */
    public Item getItemFromId(int id, int maxAmount) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == id) && (item.getAmount() <= maxAmount)) {
                return item;
            }
        }

        return null;
    }

    /**
     * Gets the nearest empty slot. -1 if there's no empty slots
     * 
     * @return nearest empty slot
     */
    public int getEmptySlot() {
        int size = getContentsSize();

        for (int i = 0; size > i; i++) {
            if (container.getContentsAt(i) != null) {
                continue;
            }
            return i;
        }

        return -1;
    }

    /**
     * Removes the item from the slot
     * 
     * @param slot slot to remove item from
     */
    public void removeItem(int slot) {
        int size = getContentsSize();

        if (slot < size && slot >= 0) {
            container.setContentsAt(slot, null);
        }
    }

    /**
     * Sets the specified slot with item
     * 
     * @param item item to set
     * @param slot slot to use
     */
    public void setSlot(Item item, int slot) {
        setSlot(item.getItemId(), item.getAmount(), slot);
    }

    /**
     * Replaces the slot with the specified item.
     *
     * @param type type of the item to put into the slot.
     * @param amount amount of the item to put into the slot.
     * @param slot the id of the slot.
     */
    public void setSlot(Item.Type type, int amount, int slot) {
        setSlot(type.getId(), amount, slot);
    }

    /**
     * Replaces the slot with the specified item.
     *
     * @param itemId item id of the item to put into the slot.
     * @param amount amount of the item to put into the slot.
     * @param slot the id of the slot.
     */
    public void setSlot(int itemId, int amount, int slot) {
        setSlot(itemId, amount, 0, slot);
    }

    /**
     * Replaces the slot with the specified item.
     *
     * @param itemId item id of the item to put into the slot.
     * @param amount amount of the item to put into the slot.
     * @param damage remaining damage of the item to put into the slot.
     * @param slot the id of the slot.
     */
    public void setSlot(int itemId, int amount, int damage, int slot) {
        int size = getContentsSize();

        if (slot < size && slot >= 0) {
            container.setContentsAt(slot, new jl(itemId, (amount > 64 ? (amount == 255 ? -1 : 64) : amount), damage));
        }
    }

    /**
     * Removes the item. No slot needed, it will go through the inventory until
     * the amount specified is removed.
     * 
     * @param item item id and amount to remove
     */
    public void removeItem(Item item) {
        removeItem(item.getItemId(), item.getAmount());
    }

    /**
     * Removes the item. No slot needed, it will go through the inventory until
     * the amount specified is removed.
     * 
     * @param type item to remove
     * @param amount amount to remove
     */
    public void removeItem(Item.Type type, int amount) {
        removeItem(type.getId(), amount);
    }

    /**
     * Removes the item. No slot needed, it will go through the inventory until
     * the amount specified is removed.
     * 
     * @param id item to remove
     * @param amount amount to remove
     */
    public void removeItem(int id, int amount) {
        Item[] items = getContents();
        int remaining = amount;

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == id)) {
                if (item.getAmount() == remaining) {
                    removeItem(item.getSlot());
                    return;
                } else if (item.getAmount() > remaining) {
                    setSlot(id, item.getAmount() - remaining, item.getSlot());
                    return;
                } else {
                    removeItem(item.getSlot());
                    remaining -= item.getAmount();
                }
            }
        }
    }

    /**
     * Checks to see if this getArray() has one slot that has the given item type
     * 
     * @param type
     * @return
     */
    public boolean hasItem(Item.Type type) {
        return hasItem(type, 1);
    }

    /**
     * Checks to see if this getArray() has one slot that has the given item id
     * 
     * @param itemId
     * @return
     */
    public boolean hasItem(int itemId) {
        return hasItem(itemId, 1);
    }

    /**
     * Checks to see if this getArray() has one slot that has the item id and
     * equal or more to the amount.
     *
     * @param type item to look for
     * @param minimum amount of items that must be in the stack
     * @return
     */
    public boolean hasItem(Item.Type type, int minimum) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getType() == type) && (item.getAmount() >= minimum)) {
                return true;
            }
        }

        return false;
    }

    /**
     * Checks to see if this getArray() has one slot that has the item id and
     * equal or more to the amount.
     * 
     * @param itemId item to look for
     * @param minimum amount of items that must be in the stack
     * @return
     */
    public boolean hasItem(int itemId, int minimum) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == itemId) && (item.getAmount() >= minimum)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Checks to see if this getArray() has one slot that has the item id and
     * equal or more to the amount.
     * 
     * @param itemId
     * @param minimum
     * @param maximum
     * @return
     */
    public boolean hasItem(int itemId, int minimum, int maximum) {
        Item[] items = getContents();

        for (Item item : items) {
            if ((item != null) && (item.getItemId() == itemId) && (item.getAmount() >= minimum) && (item.getAmount() <= maximum)) {
                return true;
            }
        }
        
        return false;
    }

    /**
     * Returns the contents of this chest
     * @return contents
     */
    public Item[] getContents() {
        int arraySize = getContentsSize();
        Item[] rt = new Item[arraySize];
        
        for (int i = 0; i < arraySize; i++) {
            rt[i] = getItemFromSlot(i);
        }

        return rt;
    }

    /**
     * Sets the contents
     * @param contents contents to set
     */
    public void setContents(Item[] contents) {
        int arraySize = getContentsSize();

        for (int i = 0; i < arraySize; i++) {
            setSlot(contents[i], i);
        }
    }
    
    public void clearContents() {
        int size = getContentsSize();

        for (int i = 0; size > i; i++) {
            container.setContentsAt(i, null);
        }
    }
}
