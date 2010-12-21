/**
 * Inventory.java - Interface to player inventories
 * 
 * @author James
 */
public class Inventory extends ItemArray<ih> {
    /**
     * The type of inventory to use
     */
    public enum Type {

        /**
         * Regular inventory
         */
        Inventory,
        /**
         * The small, 2x2 crafting table
         */
        CraftingTable,
        /**
         * The player's equipment
         */
        Equipment
    }
    private fi user;
    private Inventory.Type type = Inventory.Type.Inventory;

    /**
     * Creates an interface for this player's inventory
     * 
     * @param player
     * @param type
     */
    public Inventory(Player player, Type type) {
        super(player.getUser().an, 37);
        this.user = player.getUser();
        this.type = type;
    }
    
    /**
     * Return an empty slot.
     * Overridden to ensure the last slot remains hidden, as use of it is forbidden.
     */
    @Override
    public int getEmptySlot() {
        Item[] contents = getContents();
        int free = -1;

        // Fill backwards so that it starts from lower left.
        for(int i = contents.length-2; i >= 0; i--)
            if (contents[i] == null) 
                free = i;

        return free;
    }

    /**
     * Gives this player an item. If the inventory is full some will drop on the
     * ground.
     * 
     * @param itemId
     * @param amount
     */
    public void giveItem(int itemId, int amount) {

        if (amount == -1) {
            int emptySlot = getEmptySlot();
            if (emptySlot == -1) {
                user.getPlayer().giveItemDrop(itemId, -1);
            } else {
                addItem(new Item(itemId, 255, emptySlot));
            }
            return;
        }

        int temp = amount;
        do {
            int amountToAdd = temp >= 64 ? 64 : temp;

            if (hasItem(itemId, 1, 63)) { // Do we already have an item we can
                // add to?
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
            if (emptySlot == -1) // No empty slots
            {
                break;
            }
            addItem(new Item(itemId, amountToAdd, emptySlot));
            temp -= 64;
        } while (temp > 0);

        if (temp > 0) { // If the inventory's full it'll drop the rest on the
            // ground.
            user.getPlayer().giveItemDrop(itemId, temp);
        }
    }

    /**
     * Sends the edited inventory to the client.
     */
    public void updateInventory() {
        user.k();
    }

    /**
     * Returns a String value representing this Block
     * 
     * @return String representation of this block
     */
    @Override
    public String toString() {
        return String.format("Inventory[user=%s, type=%s]", user.getPlayer(), type);
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
        final Inventory other = (Inventory) obj;
        if (!this.user.getPlayer().equals(other.user.getPlayer())) {
            return false;
        }
        if (type != other.type) {
            return false;
        }
        return true;
    }

    /**
     * Returns a semi-unique hashcode for this block
     * 
     * @return hashcode
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.user.hashCode();
        hash = 97 * hash + this.type.ordinal();
        return hash;
    }
    
    /** 
     * Overriding getArray as variable we're interested in depends on the type of inventory...
     */
    @Override 
    public ik[] getArray() {
        switch (type) {
        case Inventory:
            return container.a;
        /*case CraftingTable:
            return container.c;*/ //Removed?
        case Equipment:
            return container.b;
        }
        
        return null;
    }
    
    /** 
     * Overriding setArray as the target array varies depending on type.
     */
    @Override
    public void setArray(ik[] values) {
        switch (type) {
        case Inventory:
            container.a = values;
            break;
        /*case CraftingTable:
            container.c = values;
            break;*/ //Removed?
        case Equipment:
            container.b = values;
            break;
        }
    }
    
}
