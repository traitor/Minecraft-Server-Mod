/**
 * Inventory.java - Interface to player inventories
 * @author James
 */
public class Inventory {
    private Player player;
    private ea user;

    /**
     * Creates an interface for this player's inventory
     * @param player
     */
    public Inventory(Player player) {
        this.player = player;
        this.user = player.getUser();
    }

    /**
     * Gives this player an item. If the inventory is full
     * some will drop on the ground.
     * @param itemId
     * @param amount
     */
    public void giveItem(int itemId, int amount) {
        int temp = amount;
        do {
            int amountToAdd = temp >= 64 ? 64 : temp;
            
            if (hasItem(itemId, 1, 63)) { //Do we already have an item we can add to?
                Item i = getItemFromId(itemId, 63);
                if (i != null) {
                    if (amountToAdd == 64) {
                        int a = amountToAdd - i.getAmount();
                        i.setAmount(64);
                        temp -= a;
                    } else if (amountToAdd + i.getAmount() > 64) {
                        int a = amountToAdd + i.getAmount() - 64;
                        i.setAmount(64);
                        temp = a;
                    } else if (amountToAdd + i.getAmount() <= 64) {
                        i.setAmount(amountToAdd + i.getAmount());
                        temp = 0;
                    }
                    addItem(i);
                    continue;
                }
            }

            int emptySlot = getEmptySlot();
            if (emptySlot == -1) //No empty slots
                break;
            addItem(new Item(itemId, amountToAdd, emptySlot));
            temp -= 64;
        } while (temp > 0);
        
        if (temp > 0) { //If the inventory's full it'll drop the rest on the ground.
            do {
                if (temp - 64 >= 64) {
                    user.a(new gp(itemId, 64));
                } else {
                    user.a(new gp(itemId, temp));
                }
                temp -= 64;
            } while (temp > 0);
        }
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
                user.aj.a[slot] = null;
            else if(Item.isValidItem(item.getItemId()))
                user.aj.a[slot] = new gp(item.getItemId(), item.getAmount());
        } else if (slot == -1) {
            int newSlot = getEmptySlot();
            if (newSlot != -1) {
                user.aj.a[newSlot] = new gp(item.getItemId(), item.getAmount());
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
            if (user.aj.a[slot] != null)
                return new Item(user.aj.a[slot].c, user.aj.a[slot].a, slot);
        return null;
    }

    /**
     * Retrieves from the slot
     * @param id
     * @return item
     */
    public Item getItemFromId(int id) {
        for (int i = 0; user.aj.a.length > i; i++) {
            if (user.aj.a[i] == null)
                continue;
            if (user.aj.a[i].c == id)
                return new Item(user.aj.a[i].c, user.aj.a[i].a, i);
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
        for (int i = 0; user.aj.a.length > i; i++) {
            if (user.aj.a[i] == null)
                continue;
            if (user.aj.a[i].c == id && user.aj.a[i].a <= maxAmount)
                return new Item(user.aj.a[i].c, user.aj.a[i].a, i);
        }
        return null;
    }

    /**
     * Gets the nearest empty slot. -1 if there's no empty slots
     * @return nearest empty slot
     */
    public int getEmptySlot() {
        for (int i = 0; user.aj.a.length > i; i++) {
            if (user.aj.a[i] != null)
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
            user.aj.a[slot] = null;
    }

    /**
     * Removes the item. No slot needed, it will go through the
     * inventory until the amount specified is removed.
     * @param item item id and amount to remove
     */
    public void removeItem(Item item) {
        int amount = item.getAmount();
        int itemId = item.getItemId();
        for (int i = 0; user.aj.a.length > i; i++) {
            if (user.aj.a[i] == null)
                continue;
            if (user.aj.a[i].c == itemId) {
                int tempAmount = user.aj.a[i].a;
                tempAmount -= amount;
                if (tempAmount <= 0) {
                    amount -= user.aj.a[i].a;
                    user.aj.a[i] = null;
                } else {
                    user.aj.a[i].a = tempAmount;
                }
                if (amount <= 0)
                    return;
            }
        }
    }

    /**
     * Checks to see if this player has one slot that has the item id
     * and equal or more to the amount.
     * @param itemId
     * @param minimum
     * @param maximum
     * @return
     */
    public boolean hasItem(int itemId, int minimum, int maximum) {
        for (int i = 0; user.aj.a.length > i; i++)
            if (user.aj.a[i] != null)
                if (user.aj.a[i].c == itemId &&
                    user.aj.a[i].a >= minimum &&
                    user.aj.a[i].a <= maximum)
                    return true;
        return false;
    }

    /**
     * Sends the edited inventory to the client.
     */
    public void updateInventory() {
        user.a.d();
    }
}
