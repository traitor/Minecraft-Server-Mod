/**
 * Adds the ability to handle TntEntities
 * @author callegustafsson
 */
public class TntEntity extends BaseEntity {

    /**
     * Creates an TntEntity from an existing entity
     * @param c entity
     */
    public TntEntity(cj c) {
       super(c);
    }

    /**
     * Creates a fused TNT, that will explode.
     * @param x
     * @param y
     * @param z
     */
    public TntEntity(double x, double y, double z) {
        eq localeq = etc.getMCServer().e;
        this.entity = (ea) new cj(etc.getMCServer().e, x, y, z);
        localeq.a(entity);
    }

    /**
     * Creates a fused TNT with set fuse, that will explode in defined ticks
     * @param x
     * @param y
     * @param z
     * @param ticks ticks until explosion
     */
    public TntEntity(double x, double y, double z, int ticks) {
        this(x,y,z);
        this.setFuseTicks(ticks);
    }


    /**
     * Returns the entity we're wrapping.
     * @return
     */
    public cj getEntity() {
        return (cj) entity;
    }

    /**
     * Set fuse time, starts counting down immideatly, 
     * default = 80;
     * @param ticks
     */
    public void setFuseTicks(int ticks) {
        getEntity().a = ticks;
    }

    /**
     * Makes it explode
     */
    public void explode() {
        entity.l();
        getEntity().c();
    }
}
