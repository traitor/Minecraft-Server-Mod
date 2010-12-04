
/**
 * Gather stuff relating to Complex Blocks here, instead of performing the same 'instanceof' checks in multiple places.
 * @author lightweight
 *
 */
public class ComplexBlockCreator {

    private ComplexBlockCreator() {
        // No state to save.
    }

    /** 
     * Creates a new ComplexBlock based on the class given.
     * @param a Class for which a wrapper is requested.
     */
    public static ComplexBlock newComplexBlock(ay a) {
        switch(detectClassId(a)) {
        case 1:
            return new Chest((ic)a);
        case 2:
            return new Furnace((dv)a);
        case 3:
            return new Sign((jn)a);
        case 4: 
            return new MobSpawner((cf)a);
        }
        return null;
    }

    /** 
     * Creates a new instance of the given class.
     * @param a Class for which a new instance is requested.
     */
    public static ay newBaseBlock(ay a) {
        switch(detectClassId(a)) {
        case 1:
            return new ic();
        case 2:
            return new dv();
        case 3:
            return new jn();
        case 4: 
            return new cf();
        }
        return null;
    }
    
    /**
     * Returns an internal ID for the given class. 
     * The less reliance on names that change, the better. =)
     */
    private static int detectClassId(ay obj) {
        int id = -1;
        
        if (obj instanceof ic) {
            id = 1;
        } else if (obj instanceof dv) {
            id = 2;
        } else if (obj instanceof jn) {
            id = 3;
        } else if (obj instanceof cf) {
            id = 4;
        }
        
        return id;
    }
}
