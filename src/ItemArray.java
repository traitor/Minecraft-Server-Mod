/**
 * ItemArray.java - Interface to jh[] so I don't have to copy+paste this a bunch
 * of times
 * 
 * @author James
 */
public abstract class ItemArray<C extends Container<hn>> {
    protected C container;
    private int arraySize = 0;
    
    public ItemArray(C c, int size) {
        this.container = c;
        this.arraySize = size;
    }
    
    public int getContentSize() {
        return arraySize;
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
        if (slot < _getArray().length && slot >= 0) {
            if (item.getAmount() <= 0) {
                _getArray()[slot] = null;
            } else if (Item.isValidItem(item.getItemId())) {
                _getArray()[slot] = new hn(item.getItemId(), item.getAmount());
            }
        } else if (slot == -1) {
            int newSlot = getEmptySlot();
            if (newSlot != -1) {
                _getArray()[newSlot] = new hn(item.getItemId(), item.getAmount());
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
        if (slot < _getArray().length && slot >= 0) {
            if (_getArray()[slot] != null) {
                return new Item(_getArray()[slot], slot);
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
        for (int i = 0; _getArray().length > i; i++) {
            if (_getArray()[i] != null) {
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
        if (slot < _getArray().length && slot >= 0) {
            _getArray()[slot] = null;
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
        if (slot < _getArray().length && slot >= 0) {
            _getArray()[slot] = new hn(itemId, (amount > 64 ? 64 : amount));
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
        hn[] newcontents = new hn[arraySize];
        for (int i = 0; i < arraySize; i++) {
            newcontents[i] = (contents[i] != null) ? new hn(contents[i].getItemId(), contents[i].getAmount()) : null;
        }
        _setArray(newcontents);
    }

    /**
     * Gets the raw items in this array
     * @return an array of raw items
     * @deprecated use getContents() instead
     */
    public hn[] getArray() {
        return _getArray();
    }

    private hn[] _getArray() {
        return container.getContents();
    }

    /**
     * Sets the raw items in this array
     * @param values items to set
     * @deprecated use setContents(Item[]) instead
     */
    public void setArray(hn[] values) {
        _setArray(values);
    }

    public void _setArray(hn[] values) {
        container.setContents(values);
    }
    
    public void clearContents() {
        for (int i = 0; _getArray().length > i; i++) {
            _getArray()[i] = null;
        }
    }
}
