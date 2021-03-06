
package wng.html.res;

/**
 *
 * @author mrjustreborn
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import javax.ejb.EJB;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wng.html.HtmlController;

@WebServlet("/css/*")
public class CSSServlet extends HttpServlet {

	@EJB
        private HtmlController html;

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            URL p = getClass().getProtectionDomain().getCodeSource().getLocation();
            String path = p.getPath().substring(0,p.getPath().indexOf("WEB-INF"));
            
            
            String filename = request.getPathInfo().substring(1);
            File file = new File(path+"Layout/"+html.template+"/css/", filename);
            response.setHeader("Content-Type", getServletContext().getMimeType(filename));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            Files.copy(file.toPath(), response.getOutputStream());
	}
}