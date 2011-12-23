/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package cz.cvut.eja.servletdemo;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author marek
 */
@WebServlet(name="GetImage", urlPatterns={"/GetImage"})
public class GetImage extends HttpServlet {

    private static final Logger LOG = Logger.getLogger(GetImage.class.getName());

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("image/png");

        OutputStream out = response.getOutputStream();

        String border = request.getParameter("imageBorder"); // retrieve image border from GET parameter
        String text = (String) request.getSession().getAttribute("imageText"); // retrieve image text from session

        try {
            BufferedImage img;
            try {
                img = ImageIO.read(new URL("http://cyber.felk.cvut.cz/i4c/images/gllogo.jpg"));
            } catch (Exception ex) {
                LOG.warning("Failed to load an image: "+ex);
                img = new BufferedImage(200, 100, BufferedImage.TYPE_INT_ARGB);
            }
            Graphics2D g = img.createGraphics();

            if (border != null) {
                g.setStroke(new BasicStroke(10));
                g.setColor(new Color(Color.decode(border).getRGB() | 0x80000000, true));
                g.drawRoundRect(0, 0, img.getWidth()-1, img.getHeight()-1, 20, 20);

                if (text != null) {
                    g.setFont(new Font(Font.SERIF, Font.BOLD, 24));
                    g.drawString(text, 10, img.getHeight()-20);
                }
            }

            //out.write - napriklad byte array ziskane v databazi, nebo:
            ImageIO.write(img, "png", out);
            g.dispose();
            img.flush();
            out.flush();
        } catch (Exception ex) {
            LOG.severe("Failed to render an image: "+ex);
        } finally {
            out.close();
        }
    } 

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    } 

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
