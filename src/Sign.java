/**
 * Sign.java - Interface to signs
 * @author James
 */
public class Sign implements ComplexBlock {
    private ig sign;

    public Sign(ig sign) {
        this.sign = sign;
    }

    public void setText(int index, String text) {
        if (index >= 0 && sign.e.length > index)
            sign.e[index] = text;
    }

    public String getText(int index) {
        if (index >= 0 && sign.e.length > index)
            return sign.e[index];
        return "";
    }

    public void update() {
        sign.c();
    }
}
