package practice3;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.Transaction;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class JDOServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	RequestDispatcher rd = req.getRequestDispatcher("/guestbook.jsp");
        
        String content = req.getParameter("message");
        
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
   
        PersistenceManager pm = PMF.get().getPersistenceManager();
        
        List<Message> messages = null;
        try {
        	if(content != null && !content.isEmpty()) {
        		Message message = new Message();
        		message.setUser(user);
        		message.setMessage(content);
        		message.setDate(new Date());
        		pm.makePersistent(message);
        	}
            
        	Query query = pm.newQuery(Message.class);
        	query.setOrdering("date desc");
        
        
            messages = (List<Message>) query.execute();
            
            req.setAttribute("user", user);
            if(user == null) {
            	req.setAttribute("url", userService.createLoginURL("/"));
            }
            else {
            	req.setAttribute("url", userService.createLogoutURL("/"));
            }
            req.setAttribute("messages", messages);
            rd.forward(req, resp);
        } finally {
            pm.close();
        }
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
}