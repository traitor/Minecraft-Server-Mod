/**
 * Sign.java - Interface to signs
 * @author James
 */
public class Sign implements ComplexBlock {
    private jg sign;

    /**
     * Creates a sign interface
     * @param sign
     */
    public Sign(jg sign) {
        this.sign = sign;
    }

    /**
     * Sets the line of text at specified index
     * @param index line
     * @param text text
     */
    public void setText(int index, String text) {
        if (index >= 0 && sign.e.length > index)
            sign.e[index] = text;
    }

    /**
     * Returns the line of text
     * @param index line of text
     * @return text
     */
    public String getText(int index) {
        if (index >= 0 && sign.e.length > index)
            return sign.e[index];
        return "";
    }

    public int getX() {
        return sign.b;
    }

    public int getY() {
        return sign.c;
    }

    public int getZ() {
        return sign.d;
    }

    public void update() {
        sign.c();
    }
}
