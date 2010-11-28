
/**
 * Real Base entity class to be able to handle all entity classes.
 *
 * @author tw1nk
 */
public class Minecart extends BaseEntity {

    jm cart;

    public Minecart(jm cart) {
        super(cart);
        this.cart = cart;
    }

    public double getMotionX() {
        return cart.s;
    }

    public double getMotionY() {
        return cart.t;
    }

    public double getMotionZ() {
        return cart.u;
    }

    public void setMotionX(double motion) {
        cart.s = motion;
    }

    public void setMotionY(double motion) {
        cart.t = motion;
    }

    public void setMotionZ(double motion) {
        cart.u = motion;
    }

    /**
     * Set minecart motion
     * @param motionX
     * @param motionY
     * @param motionZ
     */
    public void setMotion(double motionX, double motionY, double motionZ) {
        cart.s = motionX;
        cart.t = motionY;
        cart.u = motionZ;
    }

    /**
     * Set damage on Minecart
     * @param damage over 40 and you "kill" the minecart
     */
    public void setDamage(int damage) {
        cart.a = damage;
    }

    /**
     * Returns damage for minecart
     * @return returns current damage
     */
    public int getDamage() {
        return cart.a;
    }

    public void destroy() {
        cart.l();
    }

    public int getType() {
        return cart.d;
    }

    public boolean isEmpty() {
        if (cart.j == null) {
            return true;
        } else {
            return false;
        }
    }
}
