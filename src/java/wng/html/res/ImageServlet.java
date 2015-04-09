
package wng.html.res;

/**
 *
 * @author mrjustreborn
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import javax.ejb.EJB;
import javax.imageio.ImageIO;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import wng.html.HtmlController;

@WebServlet("/img/*")
public class ImageServlet extends HttpServlet {

        @EJB
        private HtmlController html;
        
        @Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            URL p = getClass().getProtectionDomain().getCodeSource().getLocation();
            String path = p.getPath().substring(0,p.getPath().indexOf("WEB-INF"));
            
            
            String filename = request.getPathInfo().substring(1);
            File file = new File(path+"Layout/"+html.template+"/img/", filename);
            
            if(!file.exists()){
                filename="troll_jubel.gif";
                file = new File(path+"Layout/"+html.template+"/img/", filename);
            }
            
            response.setHeader("Content-Type", getServletContext().getMimeType(filename));
            response.setHeader("Content-Length", String.valueOf(file.length()));
            response.setHeader("Content-Disposition", "inline; filename=\"" + filename + "\"");
            Files.copy(file.toPath(), response.getOutputStream());
	}
        
        
        private void genImg(String filename){
            try {
                int width = 200, height = 200;

                // TYPE_INT_ARGB specifies the image format: 8-bit RGBA packed
                // into integer pixels
                BufferedImage bi = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);

                Graphics2D ig2 = bi.createGraphics();


                Font font = new Font("TimesRoman", Font.BOLD, 20);
                ig2.setFont(font);
                String message = "www.java2s.com!";
                FontMetrics fontMetrics = ig2.getFontMetrics();
                int stringWidth = fontMetrics.stringWidth(message);
                int stringHeight = fontMetrics.getAscent();
                ig2.setPaint(Color.black);
                ig2.drawString(message, (width - stringWidth) / 2, height / 2 + stringHeight / 4);

                ImageIO.write(bi, "PNG", new File(filename));

              } catch (IOException ie) {
              }

        }
}