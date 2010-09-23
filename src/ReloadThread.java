/* Reloads settings every x minutes. Reloading settings won't reload sleep time however */

public class ReloadThread implements Runnable {
    private boolean running = false;
    private long sleepTime = 30000L;

    public ReloadThread(long sleepTime) {
        this.sleepTime = sleepTime;
    }

    public void run() {
        while (this.running) {
            try {
                Thread.sleep(sleepTime);
            } catch (InterruptedException localInterruptedException) {
            }
            etc.getInstance().load();
            etc.getInstance().loadData();
        }
    }

    public void start() {
        this.running = true;
        new Thread(this).start();
    }

    public void stop() {
        this.running = false;
    }
}
