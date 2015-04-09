/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package wng.image;

/**
 *
 * @author mrjustreborn
 */
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/img/*")
public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            URL p = getClass().getProtectionDomain().getCodeSource().getLocation();
            String path = p.getPath().substring(0,p.getPath().indexOf("WEB-INF"));
            
            
            String filename = request.getPathInfo().substring(1);
            File file = new File(path+"Layout/design12/img/", filename);
            response.setHeader("Content-Type", getServletContext().getMimeType(filename));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            Files.copy(file.toPath(), response.getOutputStream());
	}

}
