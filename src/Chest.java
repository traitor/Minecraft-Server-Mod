/**
 * Chest.java - Interface to chests.
 * @author James
 */
public class Chest extends BaseContainerBlock<kc> implements ComplexBlock {
    
    public Chest(kc chest) {
        super(chest, "Chest");
    }

    public DoubleChest findAttachedChest() {
        Block block = getBlock();

        DoubleChest result;

        result = tryAttachedChest(block, Block.Face.Front);
        if (result != null) return result;

        result = tryAttachedChest(block, Block.Face.Back);
        if (result != null) return result;

        result = tryAttachedChest(block, Block.Face.Left);
        if (result != null) return result;
        
        result = tryAttachedChest(block, Block.Face.Right);
        if (result != null) return result;

        return null;
    }

    private DoubleChest tryAttachedChest(Block origin, Block.Face face) {
        Block block = origin.getFace(face);
        
        if (block.blockType == Block.Type.Chest) {
            ComplexBlock cblock = etc.getServer().getOnlyComplexBlock(block);
            if ((cblock != null) && (cblock instanceof Chest)) {
                Chest chest = (Chest)cblock;
                return new DoubleChest(new ay(getName(), this.container, chest.container));
            }
        }

        return null;
    }

}