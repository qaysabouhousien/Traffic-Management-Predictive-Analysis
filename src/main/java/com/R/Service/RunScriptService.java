package com.R.Service;


import org.math.R.Rsession;

/**
 * Service for running the R script
 * @author - Qays
 */
public class RunScriptService {

    /**
     * runs the specified script.
     * @param scriptName script name
     */
    public static void runScript(String scriptName){
        RConnectionService rService = RConnectionService.getInstance();
        Rsession session =rService.getRSession();
        String scriptPath = RConnectionService.GetScriptPath(scriptName);
        String command = "source('"+scriptPath+"')";
        try {
            session.eval(command);
        } catch (Rsession.RException e) {
            e.printStackTrace();
        }


    }

}
