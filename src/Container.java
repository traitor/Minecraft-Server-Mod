
/*
 * Generic interface for containers of all types.
 */
interface Container<T> {
    public T[] getContents();
    public void setContents(T[] values);
}