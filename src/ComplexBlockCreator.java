/**
 * Static utility class for creation of specialized ComplexBlocks. 
 * 
 * @author lightweight
 *
 */
public final class ComplexBlockCreator {
    private ComplexBlockCreator() {
        // No need to instantiate this class.
    }
    
    /**
     * Return a ComplexBlock wrapper for the given block. Null if invalid or non-existent block.
     */
    public static ComplexBlock createFrom(ay target) {
        if (target != null) {
            if (target instanceof ic) {
                return new Chest((ic) target);
            } else if (target instanceof jn) {
                return new Sign((jn) target);
            } else if (target instanceof dv) {
                return new Furnace((dv) target);
            } else if (target instanceof cf) {
                return new MobSpawner((cf) target);
            }
        }
        return null;
    }
}
