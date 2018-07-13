package com.R.Service;
import org.math.R.RenjinSession;
import org.math.R.Rsession;
import java.io.File;





/**
 * Singleton Class In Order to maintain Only one RSession.
 */
public class RConnectionService {


//  Singleton Instance
    private static RConnectionService RConnectionService;


    private Rsession rSession;

    /**
     * Create a connection to Rserve instance running on default port 6311
     *
     *
     */
    private RConnectionService(){

        rSession = new RenjinSession(System.out,null);
    }

    /**
     * checks if an Instance is available if yes it gets returned, if not it create a new one.
     * @return RConnectionService Instance
     */
    public static RConnectionService getInstance() {
        if(RConnectionService == null )
            RConnectionService = new RConnectionService();
        return RConnectionService;
    }

    /**
     * Gets the only RSession
     * @return Rsession
     */
    public Rsession getRSession() {
        return this.rSession;
    }

    /**
     * getting the script absolute path in the file system, So that R could reach it.
     * @param scriptName Script Name
     * @return script Path
     */
    public static String GetScriptPath(String scriptName) {
        File f = new File("src/main/resources/R Resources/"+scriptName);
        String absPath =f.getAbsolutePath();
        absPath = absPath.replace("\\","/");

        return absPath;
    }

    /**
     * destroys/Closes the session
     * @return true if closes, false otherwise.
     */
    public static boolean destroySession(){
        if (RConnectionService.rSession.isAvailable()){
            RConnectionService.rSession.end();
            return true;
        }
        return false;
    }

}
