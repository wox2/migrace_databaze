package dvd;

import db.Database;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@ManagedBean(name = "addDVD")
@RequestScoped
public class AddDVD {

    @Size(min = 1, max = 30)
    private String title;
    @Pattern(regexp = "\\d\\d\\d\\d")
    private String year;
    private String genre;

    public AddDVD() {
    }

    public String perform() {
        Database.addDVD(title, year, genre);
        return "DVDCollection";
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
