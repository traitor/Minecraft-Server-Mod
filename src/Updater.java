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


/** Regularly check (every hour) for updates for registered files.
 * @author Cogito
 *
 */
/**
 * @author Andrew
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
    
    /**
     * Allows for files to be checked for updates at regular intervals.
     * 
     * @param initialDelay how long to wait before the first check.
     * @param period how often each check should occur.
     * @param timeUnits the unit of time that initialDelay and period are measured in.
     */
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
     * @param location Where to look for the update, as a URL string. 
     * @param autoUpdate If set to true, the updater will try to apply updates automatically as they are found.
     */
    public void addFileToUpdate(final String filename, final String location, final boolean autoUpdate) {
        
        ScheduledFuture<?> handle;
        final Runnable updateFile = new Runnable(){
            @Override
			public void run() { updateFile(filename, location, autoUpdate);}
        };
        handle = scheduler.scheduleAtFixedRate(updateFile, initialDelay, period, timeUnits);
        handles.put(filename, handle);
    }


    /**
     * Checks for a new version of the given file.
     * If the local file does not exist, it will be created. If the file needs updating, the
     * the update is downloaded, and applied if autoUpdate is true.
     * 
     * @param filename The name of the local file to check for updates.
     * @param location A url where to look for the update. 
     * @param autoUpdate If set to true, the updater will try to apply updates automatically as they are found.
     */
    private void updateFile(String filename, String location, boolean autoUpdate) {
        try {
            File oldfile = new File(filename);
            File newfile = new File(filename+".new");
            URL url = new URL(location);
            if(!oldfile.exists()) {
                // The file doesn't exist, download it.
                System.out.println(filename+" not found, downloading...");
                this.downloadFile(oldfile, url);
                log.info("Finished downloading "+filename+" from "+location);        
            } else {
                // Check for updated file.
                log.info("Checking for new version of "+filename+" at "+location);
                if(!this.lastModified.containsKey(url)){
                	// assume the last modified field of the file can be used
                	this.lastModified.put(url, oldfile.lastModified());
                }
                if(this.lastModified.get(url) < this.getLastModified(url)){
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
                } else {
                	log.info(filename + " is up-to-date.");
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

	private void downloadFile(File file, URL url) throws IOException {
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
    
    
    /**
     * Find the last modified header for the given URL
     * 
     * @param url the URL to check when last modified
     * @return the precise time the file at the given URL was modified, given in milliseconds since the epoch
     */
    public long getLastModified(URL url){
    	long value;
		try {
			URLConnection conn = url.openConnection();
			value = conn.getLastModified();
		} catch (IOException e) {
			log.info("Could not open connection to "+url.toString()+" while checking for new version.");
			value = -1;
		}
		return value;
    }

}
