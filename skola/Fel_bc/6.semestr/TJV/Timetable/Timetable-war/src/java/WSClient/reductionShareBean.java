package WSClient;

import java.net.HttpURLConnection;
import java.net.URL;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;

@ManagedBean(name = "shareBean")
@RequestScoped
public class reductionShareBean {

    private static final String shareURL = "http://localhost:11367/TimetableWS/share/";
    @ManagedProperty("#{param['passanger']}")
    private String passanger;
    private String reduction;

    public reductionShareBean() {
    }

    private void callWS() {
        try {
            URL url = new URL(shareURL + passanger);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
            JAXBContext ctx = JAXBContext.newInstance(XmlShare.class);
            Unmarshaller um = ctx.createUnmarshaller();
            XmlShare s = (XmlShare) um.unmarshal(conn.getInputStream());
            reduction = s.getReduction();
        } catch (Exception e) {
            FacesMessage msg = new FacesMessage(e.getMessage());
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
    }

    public String getPassanger() {
        return passanger;
    }

    public void setPassanger(String passanger) {
        this.passanger = passanger;
    }

    public String getReduction() {
        if (reduction == null) {
            callWS();
        }
        return reduction;
    }

    public void setReduction(String reduction) {
        this.reduction = reduction;
    }
}
