import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Meaglin
 */
public class Cloth {
    public enum Color {
        WHITE("white", 0), ORANGE("Orange", 1), MAGENTA("Magenta", 2), LIGHT_BLUE("Light Blue", 3), YELLOW("Yellow", 4), LIGHT_GREEN("Light Green", 5), PINK("Pink", 6), GRAY("Gray", 7), LIGHT_GRAY("Light Gray", 8), CYAN("Cyan", 9), PURPLE("Purple", 10), BLUE("Blue", 11), BROWN("Brown", 12), DARK_GREEN("Dark Green", 13), RED("Red", 14), BLACK("Black", 15);

        private String                    name;
        private int                       data;
        private static Color[]            colors;
        private static Map<String, Color> colorMap;

        private Color(String name, int data) {
            this.name = name;
            this.data = data;
            add(data, this);

        }

        private static void add(int data, Color color) {
            if (colors == null)
                colors = new Color[16];

            if (colorMap == null)
                colorMap = new HashMap<String, Color>();

            colors[data] = color;
            colorMap.put(color.getName().toLowerCase(), color);
        }

        public static Color getColor(int data) {
            if (data < 0 || data > 15)
                return null;
            else
                return colors[data];
        }

        public static Color getColor(String name) {
            return colorMap.get(name);
        }

        public int getData() {
            return data;
        }

        public String getName() {
            return name;
        }

        public Block getBlock() {
            return new Block(Block.Type.Cloth, getData());
        }

        public Item getItem() {
            return new Item(Item.Type.Cloth, 1, -1, getData());
        }
    }

}
