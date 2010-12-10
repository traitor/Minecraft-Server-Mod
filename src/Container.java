
/*
 * Generic interface for containers of different types.
 */
public interface Container<T> {
    public T[] getContents();
    public void setContents(T[] values);
}