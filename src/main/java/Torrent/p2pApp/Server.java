package Torrent.p2pApp;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.security.NoSuchAlgorithmException;

import com.turn.ttorrent.tracker.TrackedTorrent;
import com.turn.ttorrent.tracker.Tracker;

public class Server {

	public static void main( String[] args ) throws IOException, NoSuchAlgorithmException
    {
        System.out.println( "Hello World!" );
        
     // First, instantiate a Tracker object with the port you want it to listen on.
    	// The default tracker port recommended by the BitTorrent protocol is 6969.
    	Tracker tracker = new Tracker(new InetSocketAddress(6969));

    	// Then, for each torrent you wish to announce on this tracker, simply created
    	// a TrackedTorrent object and pass it to the tracker.announce() method:
    	FilenameFilter filter = new FilenameFilter() {
    	  public boolean accept(File dir, String name) {
    	    return name.endsWith(".torrent");
    	  }
    	};

    	for (File f : new File("./Data/").listFiles(filter)) {
    	  tracker.announce(TrackedTorrent.load(f));
    	}

    	// Once done, you just have to start the tracker's main operation loop:
    	tracker.start();

    	// You can stop the tracker when you're done with:
    	//tracker.stop();
    }
	
}
