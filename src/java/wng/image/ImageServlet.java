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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ImageServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {

		response.setContentType("image/png");
                String str[] = request.getRequestURI().split("/");

                URL p = getClass().getProtectionDomain().getCodeSource().getLocation();
                String path = p.getPath().substring(0,p.getPath().indexOf("WEB-INF"));
		File f = new File(path+"Layout/design12/img/"+str[str.length-1]);
		BufferedImage bi = ImageIO.read(f);
		OutputStream out = response.getOutputStream();
		ImageIO.write(bi, "png", out);
		out.close();

	}

}
