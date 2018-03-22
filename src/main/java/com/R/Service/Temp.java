package com.R.Service;

import org.rosuda.REngine.REXPMismatchException;
import org.rosuda.REngine.Rserve.RConnection;
import org.rosuda.REngine.Rserve.RserveException;

import java.io.File;

public class Temp {

    public static void main(String a[]) {
        RConnection connection = null;

        try {
            /* Create a connection to Rserve instance running on default port
             * 6311
             */
            connection = new RConnection();
            String scriptName = "JavaRFirstIntegration.R";
            String scriptPath = GetScriptPath(scriptName);
            System.out.println("Script Path : "+ scriptPath);
            String command = "source('"+scriptPath+"')";

            connection.eval(command);
            int num1=10;
            int num2=20;
            int sum=connection.eval("myAdd("+num1+","+num2+")").asInteger();
            System.out.println("The sum is=" + sum);
        } catch (RserveException e) {
            e.printStackTrace();
        } catch (REXPMismatchException e) {
            e.printStackTrace();
        }finally{
            connection.close();
        }
    }

    private static String GetScriptPath(String scriptName) {
        File f = new File("src/main/resources/R Resources/"+scriptName);
        String absPath =f.getAbsolutePath();
        absPath = absPath.replace("\\","/");

        return absPath;
    }
}