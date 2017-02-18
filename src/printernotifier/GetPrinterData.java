package printernotifier;

import java.util.HashMap;
import java.util.LinkedList;

public class GetPrinterData
{

    LinkedList<HashMap<String, String>> datamine(LinkedList<String> URLlist) throws Exception
    {
        WebToText converter = new WebToText();
        
        LinkedList<HashMap<String, String>> ListOfMaps = new LinkedList<>();
        
        for (int i = 0; i < URLlist.size(); i++)
        {
            HashMap<String, String> DataMap = new HashMap<>();
                    
            String webtext = converter.convert(URLlist.get(i));
            
            //Cartridge
            
            int cartridgeindex= webtext.indexOf("Black Print Cartridge  ") + 23;
            
            String cartridgevalue = "";
            
            while(true)
            {
                if(webtext.charAt(cartridgeindex)!="%".charAt(0))
                {
                    cartridgevalue += webtext.charAt(cartridgeindex);
                    cartridgeindex++;
                }
                else
                {
                   break;
                }
                
            }
            
            //Maintenance levels
                        
            int maintindex = webtext.indexOf("Maintenance Kit  ") + 17;
                        
            String maintvalue = "";
            
            while(true)
            {
                if(webtext.charAt(maintindex)!="%".charAt(0))
                {
                    maintvalue += webtext.charAt(maintindex);
                    maintindex++;
                }
                else
                {
                   break;
                }
            }
            
            //Tray 2 paper
            
            int tray2index = webtext.indexOf("Tray 2") + 9;
                                    
            String tray2value = "";
            
            boolean getTray2Value=false;            
            
            if(webtext.substring(tray2index+4, tray2index+9).equals("Empty")
                    | webtext.substring(tray2index, tray2index+7).equals("Unknown"))
            {
                tray2value= "0";
            }
            else
            {
                getTray2Value=true;
            }
          
            
            while(getTray2Value)
            {
            if(webtext.charAt(tray2index)!="%".charAt(0))
                {
                    tray2value += webtext.charAt(tray2index);
                    tray2index++;
                }
                else
                {
                   break;
                }
            }
            
            //Tray 3 Paper Levels
            
            int tray3index = webtext.indexOf("Tray 3") + 9;
                                    
            String tray3value = "";
            
            boolean getTray3Value=false;            
                        
            if(webtext.substring(tray3index+4, tray3index+9).equals("Empty")
                    | webtext.substring(tray3index, tray3index+7).equals("Unknown"))
            {
                tray3value= "0";
            }
            else
            {
                getTray3Value=true;
            }
          
            
            while(getTray3Value)
            {
                if(webtext.charAt(tray3index)!="%".charAt(0))
                {
                    tray3value += webtext.charAt(tray3index);
                    tray3index++;
                }
                else
                {
                   break;
                }
            }
            
            System.out.println("");
            System.out.println("PRINTER " + (i+1));
            System.out.println("Cartridge: " + cartridgevalue + "%");
            System.out.println("Maintenance Kit: " + maintvalue + "%");
            System.out.println("Tray2: " + tray2value + "%");
            System.out.println("Tray3: " + tray3value + "%");
            
            //Adds values to HashMap
            
            DataMap.put("Cartridge", cartridgevalue);
            DataMap.put("Maintenance", maintvalue);
            DataMap.put("Tray2", tray2value);
            DataMap.put("Tray3", tray3value);
            
            ListOfMaps.add(DataMap);
            
        }
        System.out.println("");
        
        return ListOfMaps;
    }
    
}
