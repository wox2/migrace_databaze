package dvd;

import db.DVDItem;
import db.Database;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.model.SelectItem;

@ManagedBean(name = "app")
@ApplicationScoped
public class AppManagedBean {

    private List<SelectItem> genres = new ArrayList<SelectItem>();

    public AppManagedBean() {
        genres.add(new SelectItem("comedy"));
        genres.add(new SelectItem("crimi"));
        genres.add(new SelectItem("romantic"));
        genres.add(new SelectItem("sitcom"));
        genres.add(new SelectItem("thriller"));
    }

    public List<DVDItem> getCollection() {
        return Database.getDvds();
    }

    public List<SelectItem> getGenres() {
        return genres;
    }
}
