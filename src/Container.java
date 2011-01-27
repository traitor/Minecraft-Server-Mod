/*
 * Generic interface for containers of different types.
 */
public interface Container<T> {
    public T[] getContents();

    public void setContents(T[] values);

    public T getContentsAt(int index);

    public void setContentsAt(int index, T value);

    public int getContentsSize();

    public String getName();

    public void setName(String value);
}