/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Jay
 */
public class Minecart /*extends ItemArray*/ extends BaseEntity {
    private jm cart;

    public Minecart(jm cart) {
        entity = cart; // obscure :(
        this.cart = cart;
    }

    /**
     * @return Type of Minecart where \n
     * 2 = Powered \n
     * 1 = Chest \n
     * 0 = Regular(?)
     */
    public int getType() {
        return cart.d;
    }

    public int getFuel() {
        return cart.e;
    }

    // Here just for show right now, until ItemArray is reworked so we can touch it :(
    public hl[] getArray() {
        return cart.ak;
    }
}
