
package wng;

import wng.html.HtmlController;
import java.io.IOException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import wng.html.Content;

/**
 *
 * @author mrjustreborn
 */
@Stateless
@Path("/")
public class RootPath {

    @EJB
    private HtmlController html;
    
    private Content[] c = new Content[10];
    
    public RootPath(){
        for(int i=0;i<c.length;++i)
        {
            c[i] = new Content();
            c[i].title = "Awesome Plugin "+i;
            c[i].content = "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet. Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet.";
        }
    }
    
    @GET
    @Produces("text/html")
    public String getHome() {
        return html.gethtml(html.title);
        //return "<html><body><h1>Hello World!</h1>"+"<img src=\"http://mjrb.dyndns.org/wng/progress/\">"+"<iframe src=\"http://tylerlh.github.com/github-latest-commits-widget/?username=MrJustreborn&repo=WNG-Tests&limit=LIMIT\"\n" +
        //                    "  allowtransparency=\"true\" frameborder=\"0\" scrolling=\"no\" width=\"502px\" height=\"252px\"></iframe>"+new Date().toString()+"</body></html>";
    }
    
    @GET @Path("{id}")
    @Produces("text/html")
    public String getHome(@PathParam("id") String id) throws IOException {
        html.reCache();
        return html.gethtml(html.title+" - "+id.toUpperCase(),c);
    }
}
