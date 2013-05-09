package practice3;

import java.io.IOException;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.FetchOptions;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.datastore.Transaction;
import com.google.appengine.api.datastore.TransactionOptions;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class DatastoreServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	RequestDispatcher rd = req.getRequestDispatcher("/guestbook.jsp");
        
        DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
        UserService userService = UserServiceFactory.getUserService();
        User user = userService.getCurrentUser();
        
        String content = req.getParameter("message");
        if(content != null && !content.isEmpty()) {
            Entity greeting = new Entity("Message");
            greeting.setProperty("user", user);
            greeting.setProperty("date", new Date());
            greeting.setProperty("message", content);
            datastore.put(greeting);
        }
        
        Query query = new Query("Message").addSort("date", Query.SortDirection.DESCENDING);
        List<Entity> result = datastore.prepare(query).asList(FetchOptions.Builder.withDefaults());
        
        List<Message> messages = new LinkedList<Message>();
        for(Entity e : result) {
        	Message m = new Message();
        	m.setUser((User) e.getProperty("user"));
        	m.setDate((Date) e.getProperty("date"));
        	m.setMessage((String) e.getProperty("message"));
        	messages.add(m);
        }
        
        req.setAttribute("user", user);
        if(user == null) {
        	req.setAttribute("url", userService.createLoginURL("/"));
        }
        else {
        	req.setAttribute("url", userService.createLogoutURL("/"));
        }
        req.setAttribute("messages", messages);
        rd.forward(req, resp);
    }
    
    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	doGet(req, resp);
    }
}