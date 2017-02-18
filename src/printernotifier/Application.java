package printernotifier;

import java.util.Date;
import java.util.HashMap;  
import java.util.Timer;  
import java.util.TimerTask;  
import java.util.logging.Level;
import java.util.logging.Logger;
  
public class Application extends TimerTask {  
      
    public void run() {  
        
        try
        {
            // Instantiate a Date object
            Date date = new Date();
            
            // display time and date using toString()
            System.out.println(date.toString());
            
            
            String address = "uqxtufm05lmo@nmamail.net";
            
            HashMap<String,String> availablePrinters = new HashMap<>();
            
            availablePrinters.put("L112-2", "http://192.168.75.10/hp/device/this.LCDispatcher");
            availablePrinters.put("L113-1", "http://192.168.75.8/hp/device/this.LCDispatcher");
            availablePrinters.put("L113-2", "http://192.168.75.9/hp/device/this.LCDispatcher");
            
            //Code needs to handle user selection to shrink down HashMap to selected
            //For now, code scans all printers
            
            HashMap<String,String> selectedPrinters = availablePrinters;
            
            PrinterChecker checker = new PrinterChecker(selectedPrinters, address);
            
            checker.check();
        } catch (Exception ex)
        {
            Logger.getLogger(Application.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  
      
    public static void main(String[] args) { 
        int minutes = 10;
        
        System.out.println("Checking Printers every " + minutes + " minutes...");
        System.out.println("");
        System.out.println("");
        System.out.println("");
        
        // Create an instance of our task/job for execution  
        Application task = new Application();  
          
        // We use a class java.util.Timer to   
        // schedule our task/job for execution  
        Timer timer = new Timer();  
          
        // Let's schedule our task/job to be executed every 1 second  
        timer.scheduleAtFixedRate(task, 0, 60000*minutes);  
        // First parameter: task - the job logic we   
        // created in run() method above.  
        // Second parameter: 0 - means that the task is   
        // executed in 0 millisecond after the program runs.  
        // Third parameter: 1000 - means that the task is   
        // repeated every 1000 milliseconds
    }  
  
}  
