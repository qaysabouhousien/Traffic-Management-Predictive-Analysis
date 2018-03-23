package com.R.Service;

import org.math.R.Rsession;

public class Temp {


    public static void main(String[] args){


        StartRService rServe = StartRService.getInstance();
        Rsession session = rServe.getRSession();
        if(session.isAvailable())
            try {
                String scriptName = "JavaRFirstIntegration.R";
                String scriptPath = StartRService.GetScriptPath(scriptName);
                System.out.println("Script Path : "+ scriptPath);
                String command = "source('"+scriptPath+"')";
                session.eval(command);
                int num1=10;
                int num2=20;
                double sum =(Double) session.eval("myAdd("+num1+","+num2+")");
                System.out.println("The sum is=" + sum);

            } catch (Rsession.RException e) {
                e.printStackTrace();
            }
        boolean destroyed = StartRService.destroySession();
        System.out.println(destroyed);

    }


}