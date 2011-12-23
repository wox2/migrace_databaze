package x33eja;



import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import x33eja.sb.StudentSessionLocal;

@EJB(name = "StudentSessionBean", beanInterface = StudentSessionLocal.class)
public class StudentServlet2 extends HttpServlet {

    private StudentSessionLocal setup(HttpServletRequest request) throws NamingException {
        StudentSessionLocal ssl = (StudentSessionLocal) new InitialContext().lookup("java:comp/env/StudentSession");
        request.getSession().setAttribute("studentSession", ssl);
        return ssl;
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected synchronized void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {


        try {
            StudentSessionLocal ssl = (StudentSessionLocal) request.getSession().getAttribute("studentSession");

            if (ssl == null) {
                ssl = setup(request);
            }

            if (request.getParameter("save") != null) {
                ssl.save(request.getParameter("birthNumber"), request.getParameter("firstName"), request.getParameter("surname"));
            } else if (request.getParameter("add") != null) {
                ssl.addCourse(request.getParameter("course"));
            } else {
                if (request.getParameter("cancel") != null) {
                    ssl.discard();
                    request.getSession().removeAttribute("studentSession");
                    ssl = setup(request);
                    ssl.init(null);
                } else {
                    ssl.init(request.getParameter("birthNumber"));
                }
            }

            request.setAttribute("current", ssl.getCurrent());
            request.setAttribute("students", ssl.getStudentsWithCourseCount());
            request.setAttribute("courses", ssl.getNotEnrolledCoursesForCurrent());
            response.setContentType("text/html;charset=UTF-8");
            getServletContext().getRequestDispatcher("/WEB-INF/students2.jsp").forward(request, response);
        } catch (NamingException ex) {
            Logger.getLogger(StudentServlet2.class.getName()).log(Level.SEVERE, null, ex);
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
