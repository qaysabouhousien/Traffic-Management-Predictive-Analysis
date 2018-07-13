package com.R.Controller;

import com.R.Service.RunScriptService;
import com.R.Service.RConnectionService;
import org.math.R.Rsession;



/**
 * This controller Tests R Integration With A simple Script that Adds two numbers.
 * @author - Qays
 */
public class TempController {

    /**
     * Reads an R script and calls the function inside it
     * @return script result
     */
    public static double StartR(){
        try{
            String scriptName = "JavaRFirstIntegration.R";
            RunScriptService.runScript(scriptName);

            Rsession session = RConnectionService.getInstance().getRSession();
            int num1=10;
            int num2=20;
            double sum =(Double) session.eval("myAdd("+num1+","+num2+")");
            System.out.println("The sum is=" + sum);
            return sum;
        } catch (Rsession.RException e) {
            e.printStackTrace();
        }
        boolean destroyed = RConnectionService.destroySession();
        System.out.println(destroyed);

        return 0;
    }


}