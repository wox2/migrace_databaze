package x33eja;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.transaction.Status;
import javax.transaction.SystemException;
import javax.transaction.UserTransaction;
import x33eja.model.Course;
import x33eja.model.Student;

public class SaveServlet extends HttpServlet {

    private static final Logger LOG = Logger.getLogger("StudentServlet");
    // na tomto miste si injektujeme EntityManagerFactory misto EntityManageru kvuli problemu se soubehem - instance
    // servletu je jedina v cele aplikaci a EntityManager neni thread-safe, takze vice pozadavku
    // na tento servlet by mohlo zpusobit soubeh na teto instanci EntityManager.
    @PersistenceUnit(unitName="jpa-example-clientPU")
    private EntityManagerFactory emf;
    // Tato instance slouzi k transakcnimu pristupu k databazi.
    @Resource
    private UserTransaction utx;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        System.out.println("=== RQ START ===");
        EntityManager em = emf.createEntityManager();
        System.out.println("Entity manager created.");

        String birthNumber = request.getParameter("birthNumber");
        String firstName = request.getParameter("firstName");
        String surname = request.getParameter("surname");
        String course = request.getParameter("course");

        System.out.println("Saving " + birthNumber + "/" + firstName + "/" + surname + "/" + course);
        int bN = Integer.parseInt(birthNumber);
        try {
            utx.begin();

            Student s = em.find(Student.class, bN);

            if (s == null) {
                s = new Student();
                s.setBirthNumber(Integer.parseInt(birthNumber));
                em.persist(s);
            }

            s.setFirstName(firstName);
            s.setSurname(surname);

            if (!"".equalsIgnoreCase(course) ) {
            final List<Course> courses = s.getEnrolledIn();


            courses.add(em.find(Course.class, course));
            s.setEnrolledIn(courses);
            }


            em.flush();
            utx.commit();
        } catch (Exception ex) {
            try {
                if (utx.getStatus() == Status.STATUS_ACTIVE ||
                        utx.getStatus() == Status.STATUS_MARKED_ROLLBACK) {
                    utx.rollback();
                    System.out.println("Transaction rolled back due to an exception: " + ex.getMessage());
                }
            } catch (IllegalStateException ex1) {
                LOG.log(Level.SEVERE, null, ex1);
            } catch (SecurityException ex1) {
                LOG.log(Level.SEVERE, null, ex1);
            } catch (SystemException ex1) {
                LOG.log(Level.SEVERE, null, ex1);
            }
        }
        System.out.println("Closing entity manager.");
        em.close();
        System.out.println("=== RQ END ===");

        getServletContext().getRequestDispatcher("/students").forward(request, response);
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
