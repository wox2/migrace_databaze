package x33eja;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import x33eja.model.Course;
import x33eja.model.Student;

public class StudentServlet extends HttpServlet {

    // na tomto miste si injektujeme EntityManagerFactory misto EntityManageru kvuli problemu se soubehem - instance
    // servletu je jedina v cele aplikaci a EntityManager neni thread-safe, takze vice pozadavku
    // na tento servlet by mohlo zpusobit soubeh na teto instanci EntityManager.
    @PersistenceUnit(unitName="jpa-example-clientPU")
    private EntityManagerFactory emf;

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        final EntityManager em = emf.createEntityManager();

        String birthNumber = request.getParameter("birthNumber");

        Query courseQuery;

        if (birthNumber != null) {
            Integer bn =  Integer.parseInt(birthNumber);

            Student s = em.find(Student.class, bn);
            if (s != null) {
                request.setAttribute("birthNumber", s.getBirthNumber());
                request.setAttribute("firstName", s.getFirstName());
                request.setAttribute("surname", s.getSurname());
            }

            courseQuery = em.createQuery("SELECT a FROM Course a WHERE a.courseId NOT IN (SELECT c.courseId FROM Student p JOIN p.mEnrolledIn c WHERE p.birthNumber=:bn)").setParameter("bn", bn);
        } else {
            // Priklad preddefinovane query, ktera je ulozena u entity
            courseQuery = em.createNamedQuery(Course.Q_GET_ALL_COURSES);
        }
        response.setContentType("text/html;charset=UTF-8");
        final Map<Student,Long> map = new LinkedHashMap<Student, Long>();
        final List<Object[]> result = (List<Object[]>) em.createQuery("SELECT p, count(e) FROM Student p LEFT JOIN p.mEnrolledIn e GROUP BY p ORDER BY p.surname, p.firstName").getResultList();

        for(final Object[] o : result) {
            map.put((Student) o[0], (Long) o[1]);
        }


        request.setAttribute("courses", (List<Course>) courseQuery.getResultList() );
        request.setAttribute("students", map );
        getServletContext().getRequestDispatcher("/WEB-INF/students.jsp").forward(request, response);

        em.close();
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
