/**
 * Inventory.java - Interface to player inventories
 * @author James
 */
public class Inventory extends ItemArray {
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
    private ea user;
    private Type type;

    /**
     * Creates an interface for this player's inventory
     * @param player
     * @param type
     */
    public Inventory(Player player, Type type) {
        this.user = player.getUser();
        this.type = type;
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
     * Sends the edited inventory to the client.
     */
    public void updateInventory() {
        user.a.d();
    }

    public gp[] getArray() {
        switch (type) {
            case Inventory: return user.aj.a;
            case CraftingTable: return user.aj.c;
            case Equipment: return user.aj.b;
        }
        return new gp[0];
    }
}
