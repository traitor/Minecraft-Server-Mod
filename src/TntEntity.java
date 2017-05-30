/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author callegustafsson
 */
public class TntEntity extends BaseEntity {

    public TntEntity() {
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
     * Returns the entity we're wrapping.
     * @return
     */
    public cj getEntity() {
        return (cj) entity;
    }

    /**
     * Set fuse time, starts counting down immideatly, 
     * default = 80;
     * @param time
     */
    public void setFuse(int time) {
        ((cj) entity).a = time;
    }

    /**
     * Makes it explode
     */
    public void explode() {
        entity.l();
        ((cj) entity).c();
    }
}
