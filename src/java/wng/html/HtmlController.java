
package wng.html;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.ejb.Singleton;

/**
 *
 * @author mrjustreborn
 */
@Singleton
public class HtmlController {
    private BufferedReader main, content;
    private String html_main, html_content;
    public String title="[WNG] Wiggles nextGen";

    public HtmlController() throws FileNotFoundException, IOException {
        this.main = new BufferedReader(new FileReader("/var/www/wng/index.html"));
        this.content = new BufferedReader(new FileReader("/var/www/wng/content.htm"));
        this.cache();
    }
    
    public String gethtml(){
        return html_main;
    }
    
    public String gethtml(String title){
        return html_main.replace("{TITLE}", title);
    }
    
    public String gethtml(String title, Content[] content){
        String str="";
        str = html_main.replace("{TITLE}", title);
        str = str.replace("{CONTENT}", this.getContent(content));
        return str;
    }
    private String getContent(Content[] content){
        String str="";
        String cache="";
        for (Content c:content){
            cache=html_content.replace("{TITLE}", "<a href=/WNG/plugins/"+c.title.replace(" ", "")+">"+c.title+"</a>");
            cache=cache.replace("{CONTENT}", c.content);
            str+=cache;
        }
        return str;
    }
    
    public void reCache() throws FileNotFoundException, IOException {
        this.main = new BufferedReader(new FileReader("/var/www/wng/index.html"));
        this.content = new BufferedReader(new FileReader("/var/www/wng/content.htm"));
        this.cache();
    }
    
    private void cache() throws IOException{
        String str="";
        String curLine;
        while((curLine = main.readLine()) != null)
            str+=curLine;
        this.html_main=str;
        
        str="";
        curLine="";
        while((curLine = content.readLine()) != null)
            str+=curLine;
        this.html_content=str;
    }
}
