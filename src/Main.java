import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.channels.Channels;
import java.nio.channels.ReadableByteChannel;

public class Main {

    public static void main(String[] args) throws IOException {

        checkFileForUpdates("minecraft_server.jar", 
                "http://minecraft.net/download/minecraft_server.jar",
                etc.getInstance().isAutoUpdateMCServer());

        //My mod doesn't work with gui.
        System.out.println("Starting server");
        try {
            net.minecraft.server.MinecraftServer.main(args);
        } catch (Throwable t) {
            t.printStackTrace();
        }
    }

    /** 
     * Download a given file. If the file already exists, download the new version.
     * 
     * Will automatically apply the update if <code>autoUpdate</code> is set to true.
     * @param name The name of the file on the local filesystem to download to.
     * @param location The URL of the file you wish to download.
     * @param autoUpdate If true, any updates will be automatically applied
     */
    private static void checkFileForUpdates(String filename, String location, boolean autoUpdate){

        try {
            File oldfile = new File(filename);
            
            URL url = new URL(location);
            ReadableByteChannel rbc = Channels.newChannel(url.openStream());
            
            if (!oldfile.exists()) {
                // old file does not exist, download it
                System.out.println(filename+" not found, downloading...");
            
                FileOutputStream fos = new FileOutputStream(filename);
                fos.getChannel().transferFrom(rbc, 0, 1 << 24);
            
                System.out.println("Finished downloading "+filename);
            } else {
                boolean same = false;
                
                BufferedInputStream bis1 = new BufferedInputStream(new FileInputStream(oldfile));
                BufferedInputStream bis2 = new BufferedInputStream(url.openStream());
                
                File newfile = new File(filename+"_new");
                FileOutputStream newfos = new FileOutputStream(newfile);
            
                //TODO maybe try some general checking before checking byte-wise
                System.out.println("Checking for updates to "+filename);
                boolean finished = false;
                while(!finished){
                    int a = bis1.read();
                    int b = bis2.read();
                    newfos.write(b); //write out whilst checking
                    if(a != b){
                        same = false;
                        finished = true;
                        // download the rest of the new file
                        while(b != -1){
                            b = bis2.read();
                            newfos.write(b);
                        }
                    }
                    if(a == -1){
                        // both streams have reached the end, so identical
                        same = true;
                        finished = true;
                    }
                }
                // have to close the streams before moving/deleting files
                bis1.close();
                bis2.close();
                newfos.close(); 
                if(!same){
                    System.out.println(filename+" needs updating.");
                    if(autoUpdate){
                        System.out.println("Updating now...");
                        oldfile.renameTo(new File(filename+"_old"));
                        newfile.renameTo(new File(filename));
                    }
                } else {
                    System.out.println(filename + " is up-to-date.");
                    newfile.delete();
                }
            
                // if all went well, finish the updating.
                return;
            }
        } catch (MalformedURLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return;
    }

}
