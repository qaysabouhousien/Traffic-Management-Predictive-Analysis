package com.R.Controller;

import com.R.Service.RunScriptService;
import com.R.Service.RConnectionService;
import org.math.R.Rsession;

public class TempController {


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