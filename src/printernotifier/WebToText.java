package printernotifier;

import java.net.URL;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

class WebToText
{
    String convert(String webPage) throws Exception
    {
        URL url = new URL(webPage);
        Document doc = Jsoup.parse(url, 3*1000);

        return doc.body().text();
    }
}
