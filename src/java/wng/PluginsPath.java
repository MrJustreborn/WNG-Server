
package wng;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.PathParam;

/**
 *
 * @author mrjustreborn
 */

@Stateless
@Path("/plugins")
public class PluginsPath {

    @EJB
    private NameStorageBean nameStorage;

    @GET
    @Produces("text/html")
    public String getPlugins() {
        return "<html><body><h1>Hello "+nameStorage.getName()+"!</h1></body></html>";
    }
    
    @GET @Path("{id}")
    @Produces("text/html")
    public String getWidget(@PathParam("id") String id){
        return "<html><body><h1>Page: "+id+"!</h1></body></html>";
    }
    
    @GET @Path("{id}/{id2}")
    @Produces("text/html")
    public String getWidget2(@PathParam("id") String id, @PathParam("id2") String id2){
        return "<html><body><h1>Page: "+id+"/"+id2+"!</h1></body></html>";
    }
    
    @GET
    @Produces("application/json")
    public String getPluginsJSON() {
        return "{"
                + "\"Version\":\"0.0.1\","
                + "\"plugins\":"
                + "["
                + "{"
                + "\"name\":\"Awesom-Plugin\","
                + "\"maintainer\":\"xyz\","
                + "\"desc\":\"beschreibung...\","
                + "\"icon\":\"http://icon\","
                + "\"link\":\"plugins/xyz/awesome-plugin\""
                + "},"
                + "{"
                + "\"name\":\"Awesom-Plugin 2\","
                + "\"maintainer\":\"xyz\","
                + "\"desc\":\"beschreibung...2\","
                + "\"icon\":\"http://icon\","
                + "\"link\":\"plugins/xyz/awesome-plugin_2\""
                + "}"
                + "]"
                + "}";
    }

    /**
     * PUT method for updating an instance of HelloWorldResource
     * @param content representation for the resource
     * @return an HTTP response with content of the updated or created resource.
     */
    @PUT
    @Consumes("text/plain")
    @Produces("text/plain")
    public String setName(String content) {
        nameStorage.setName(content);
        return content;
    }
}
