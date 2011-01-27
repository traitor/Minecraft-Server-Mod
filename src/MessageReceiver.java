public interface MessageReceiver {
    String getName();

    void notify(String message);
}
