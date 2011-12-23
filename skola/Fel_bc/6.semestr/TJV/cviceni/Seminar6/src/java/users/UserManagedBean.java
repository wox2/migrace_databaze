package users;

import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean(name = "userBean")
@RequestScoped
public class UserManagedBean {

    @NotNull
    private Long id;
    @Size(min = 1, max = 30)
    private String name;
    @Pattern(regexp = ".*@.*")
    private String email;
    private int version;
    @EJB
    private ManagerSessionBean manager;

    public UserManagedBean() {
    }

    public String find() {
        User u = manager.findUser(id);
        if (u == null) {
            FacesMessage msg = new FacesMessage("invalid id");
            FacesContext.getCurrentInstance().addMessage("form1:id", msg);
            return "find";
        }
        name = u.getName();
        email = u.getEmail();
        version = u.getVersion();
        return "update";
    }

    public String perform() {
        try {
            manager.updateUser(id, name, email, version);
            return "find";
        } catch (UpdateException e) {
            FacesMessage msg = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage("form2:update", msg);
        }
        return null;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }
}
