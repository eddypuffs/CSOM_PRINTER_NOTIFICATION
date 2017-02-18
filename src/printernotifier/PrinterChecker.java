package printernotifier;
import java.util.HashMap;
import java.util.LinkedList;

class PrinterChecker
{
    private HashMap<String, String> printerURLs = new HashMap<>();
    public String address;
    
    public LinkedList<String> URLlist;
    public LinkedList<String> PrinterNameList;
    
    PrinterChecker(HashMap<String,String> printerURLs, String address)
    {
        this.printerURLs = printerURLs;
        this.address = address;
        
        this.URLlist = new LinkedList<String>();
        this.PrinterNameList = new LinkedList<String>();
        
        for (String URL : printerURLs.values())
        {
            URLlist.add(URL);
        }
        
        for (String name : printerURLs.keySet())
        {
            PrinterNameList.add(name);
        }
        
    }   
    

void check() throws Exception
    {
        Emailer mailer = new Emailer();
        
        GetPrinterData datagetter = new GetPrinterData();
        
        LinkedList<HashMap<String,String>> data = datagetter.datamine(URLlist);
        
        
        for (int i = 0; i < data.size(); i++)
        {
            //Checks cartridge levels
            
            int cartridgelevel = Integer.parseInt(data.get(i).get("Cartridge"));
            
            if(cartridgelevel == 0)
            {
                String subject = "Printer " + PrinterNameList.get(i) + " Cartridge Level is low";
                String body = URLlist.get(i);
                mailer.sendEmail(subject,body, address);
            }
            
            //Checks maint levels
            
            int maintlevel = Integer.parseInt(data.get(i).get("Maintenance"));
            
            if(maintlevel == 0)
            {
                String subject = "Printer " + PrinterNameList.get(i) + " Maintenance Level is low";
                String body = URLlist.get(i);
                mailer.sendEmail(subject, body, address);
            }
            
            //Checks tray2 levels
            
            String tray2level = data.get(i).get("Tray2");
            
            if(tray2level.equals("1 - 10") | tray2level.equals("0"))
            {
                String subject = "Printer " + PrinterNameList.get(i) + " Tray 2 Paper is low";
                String body = URLlist.get(i);
                mailer.sendEmail(subject, body, address);
            }
            
            //Checks tray3 levels
            
            String tray3level = data.get(i).get("Tray3");
            
            if(tray3level.equals("1 - 10") | tray3level.equals("0"))
            {
                String subject = "Printer " + PrinterNameList.get(i) + " Tray 3 Paper is low";
                String body = URLlist.get(i);
                mailer.sendEmail(subject, body, address);
            }
            
        }
        

    }
}
