
public class DoubleChest extends ItemArray<av> implements ComplexBlock, Inventory {
    private final Block block;

    public DoubleChest(av chest) {
        super(chest);
        block = chest.getChestBlock();
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