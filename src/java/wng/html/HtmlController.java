
package wng.html;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import javax.ejb.Singleton;

/**
 *
 * @author mrjustreborn
 */
@Singleton
public class HtmlController {
    private BufferedReader main, content, test;
    private String html_main, html_content;
    public String title="[WNG] Wiggles nextGen";
    private final String path;
    private final String path_to_index,path_to_content,path_to_test;
    
    private String html_test;

    public HtmlController() throws FileNotFoundException, IOException {
        URL p = getClass().getProtectionDomain().getCodeSource().getLocation();
        this.path = p.getPath().substring(0,p.getPath().indexOf("WEB-INF"));
        
        this.path_to_index = this.path+"Layout/index.html";
        this.path_to_content = this.path+"Layout/content.htm";
        this.path_to_test = this.path+"Layout/design12/index.html";
        
        this.main = new BufferedReader(new FileReader(this.path_to_index));
        this.content = new BufferedReader(new FileReader(this.path_to_content));
        this.test = new BufferedReader(new FileReader(this.path_to_test));
        this.cache();
    }
    
    public String gethtmltest(){
        return this.html_test;
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
        this.main = new BufferedReader(new FileReader(this.path_to_index));
        this.content = new BufferedReader(new FileReader(this.path_to_content));
        this.test = new BufferedReader(new FileReader(this.path_to_test));
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
