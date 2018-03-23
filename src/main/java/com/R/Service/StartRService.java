package com.R.Service;
import org.math.R.RenjinSession;
import org.math.R.Rsession;
import java.io.File;



// Singleton Class In Order to maintain Only one RSession.
public class StartRService {


//  Singleton Instance
    private static StartRService startRService;


    private Rsession rSession;

    private StartRService(){
        /* Create a connection to Rserve instance running on default port
         * 6311
         */
        rSession = new RenjinSession(System.out,null);
    }

    public static StartRService getInstance() {
        if(startRService == null )
            startRService = new StartRService();
        return startRService;
    }

    public Rsession getRSession() {
        return this.rSession;
    }

    public static String GetScriptPath(String scriptName) {
        File f = new File("src/main/resources/R Resources/"+scriptName);
        String absPath =f.getAbsolutePath();
        absPath = absPath.replace("\\","/");

        return absPath;
    }

    public static boolean destroySession(){
        if (startRService.rSession.isAvailable()){
            startRService.rSession.end();
            return true;
        }
        return false;
    }

}
