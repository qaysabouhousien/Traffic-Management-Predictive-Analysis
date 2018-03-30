package com.R.Service;


import org.math.R.Rsession;

public class RunScriptService {


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
