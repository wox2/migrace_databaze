package cz.cvut.fel.agents.aos.mashup.beans;


import java.util.Collection;
import java.util.LinkedList;
import javax.faces.bean.ManagedBean;

@ManagedBean(name="usersBean")
public class UsersManagedBean {
    
    private Collection<MashupUser> users;
    private String username;
    private int goals;

    ///////////////////////////////////////////////
    //Backup getters/setters for the managed bean.
    public Collection<MashupUser> getUsers() {
        if(users == null){
            //TODO: get from users service
            this.users = new LinkedList<MashupUser>();
            users.add(new MashupUser("Fake Adahot", 3));
            users.add(new MashupUser("Pepe Guardiola", 1));
        }
        return users;
    }

    public void setUsers(Collection<MashupUser> users) {
        this.users = users;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }
    ///////////////////////////////////////////////////
    
    /**
     * Action method called by action button.
     */
    public void add(){
        //TODO: call rest service to add user
        System.out.println(username + " " + goals);
    }
    
    
    
}
