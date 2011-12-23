package x33eja;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import x33eja.model.Course;
import x33eja.model.Student;
import x33eja.sb.SchoolCenterLocal;

@EJB(name = "SchoolCenter", beanInterface = SchoolCenterLocal.class)
public class SaveServlet extends HttpServlet {

    private SchoolCenterLocal getSchoolCenter() throws NamingException {
        return (SchoolCenterLocal) new InitialContext().lookup("java:comp/env/SchoolCenter");
    }
    private static final Logger LOG = Logger.getLogger("StudentServlet");

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
        
            String birthNumber = request.getParameter("birthNumber");
            String firstName = request.getParameter("firstName");
            String surname = request.getParameter("surname");
            String course = request.getParameter("course");
            System.out.println("Saving " + birthNumber + "/" + firstName + "/" + surname + "/" + course);
            
            int bN = Integer.parseInt(birthNumber);
            Student s = getSchoolCenter().getStudent(bN);
            if (s == null) {
                s = new Student();
                s.setBirthNumber(bN);
            }

            s.setFirstName(firstName);
            s.setSurname(surname);
            if (!"".equalsIgnoreCase(course)) {
                final List<Course> courses = s.getEnrolledIn();
                Course c = getSchoolCenter().getCourse(course);
                if (c != null) {
                    courses.add(c);
                    s.setEnrolledIn(courses);
                }
            }
            getSchoolCenter().updateStudent(s);
            getServletContext().getRequestDispatcher("/students").forward(request, response);

        } catch (NamingException ex) {
            Logger.getLogger(SaveServlet.class.getName()).log(Level.SEVERE, null, ex);
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
