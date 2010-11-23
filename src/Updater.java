import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;


/** Registers and regularly checks (every hour) for updates.
 * @author Cogito
 *
 */
public class Updater {
    
    private static final Logger log = Logger.getLogger("Minecraft");
    
    private final ScheduledExecutorService scheduler;
    private Map<String,ScheduledFuture<?>> handles;
    private long initialDelay;
    private long period;
    private TimeUnit timeUnits;


	private Map<URL, Long> lastModified;

    public Updater() {
        this(0, 1, TimeUnit.HOURS);
    }
    
    public Updater(long initialDelay, long period, TimeUnit timeUnits) {
        super();
        scheduler = Executors.newScheduledThreadPool(1);
        handles = new HashMap<String, ScheduledFuture<?>>();
        lastModified = new HashMap<URL, Long>();
        this.initialDelay = initialDelay;
        this.period = period;
        this.timeUnits = timeUnits;
    }



    /**
     * Checks the given file for updates regularly. Will notify when a new
     * update has been downloaded.
     * 
     * @param filename The name of the local file to check for updates.
     * @param location A url where to look for the update. 
     * @param autoUpdate If set to true, the updater will try to apply updates automatically as they are found.
     */
    /**
     * @param filename
     * @param location
     * @param autoUpdate
     */
    public void addFileToUpdate(final String filename, final String location, final boolean autoUpdate) {
        
        ScheduledFuture<?> handle;
        final Runnable updateFile = new Runnable(){
            public void run() { updateFile(filename, location, autoUpdate);}
        };
        handle = scheduler.scheduleAtFixedRate(updateFile, initialDelay, period, timeUnits);
        handles.put(filename, handle);
    }


    /**
     * Checks for a new version of the given file.
     * If the local file does not exist, it will be created. Otherwise, the
     * the update is downloaded, and applied if autoUpdate is true.
     * 
     * @param filename The name of the local file to check for updates.
     * @param location A url where to look for the update. 
     * @param autoUpdate If set to true, the updater will try to apply updates automatically as they are found.
     */
    protected void updateFile(String filename, String location, boolean autoUpdate) {
        try {
            File oldfile = new File(filename);
            File newfile = new File(filename+".new");
            URL url = new URL(location);
            if(!oldfile.exists()) {
                // The file doesn't exist, download it.
                System.out.println(filename+" not found, downloading...");
                this.downloadFile(oldfile, url);
                lastModified.put(url, getLastModified(url)); // store the last modified time
                log.info("Finished downloading "+filename+" from "+location);        
            } else {
                // Check for updated file.
                log.info("Checking for new version of "+filename+" at "+location);
                if(this.lastModified.containsKey(url) && 
                		(this.lastModified.get(url) < this.getLastModified(url))){
                	// if there is a new version, download it.
                	downloadFile(newfile, url);
        		    if(autoUpdate){
        		        // if autoUpdate is set, rename the old file and replace it
        		        oldfile.renameTo(new File(filename+".old"));
        		        newfile.renameTo(new File(filename));
        		        log.log(Level.WARNING, filename+" has been automatically updated");  
        		    } else {
        		        // otherwise, print a message
        		        log.log(Level.WARNING, filename+" has been downloaded and is ready to be updated.");
        		    }
                }
                
                
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

	protected void downloadFile(File file, URL url) throws IOException {
		ReadableByteChannel rbc = Channels.newChannel(url.openStream());
        FileOutputStream fos = new FileOutputStream(file);
        fos.getChannel().transferFrom(rbc, 0, 1 << 24);
        this.lastModified.put(url, this.getLastModified(url)); // keep track of when last modified
	}

    public long getInitialDelay() {
        return initialDelay;
    }
    public void setInitialDelay(long initialDelay) {
        this.initialDelay = initialDelay;
    }


    public long getFrequency() {
        return period;
    }
    public void setFrequency(long period) {
        this.period = period;
    }


    public TimeUnit getTimeUnits() {
        return timeUnits;
    }
    public void setTimeUnits(TimeUnit timeUnits) {
        this.timeUnits = timeUnits;
    }

    public long getLastModified(URL url){
          try {
			URLConnection conn = url.openConnection();
			long value = conn.getLastModified();
			return value;
		} catch (IOException e) {
			log.info("Could not open connection to "+url.toString()+" while checking for new version.");
		}
		return -1;
    }

}
