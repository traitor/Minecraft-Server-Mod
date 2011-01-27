public class DoubleChest extends ItemArray<OInventoryLargeChest> implements ComplexBlock, Inventory {
    private final Block block;
    private String      name = "Large Chest";

    public DoubleChest(OInventoryLargeChest chest) {
        super(chest);
        block = chest.getChestBlock();
    }

    public String getName() {
        return name;
    }

    public void setName(String value) {
        name = value;
    }

    public int getX() {
        return block.getX();
    }

    public int getY() {
        return block.getY();
    }

    public int getZ() {
        return block.getZ();
    }

    public void update() {
        container.d();
    }

    public Block getBlock() {
        return block;
    }

}