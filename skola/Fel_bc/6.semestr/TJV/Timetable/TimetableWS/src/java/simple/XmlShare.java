package simple;

import java.net.URI;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "share")
public class XmlShare {

    private String passanger;
    private int reduction;
    private URI uri;

    public XmlShare() {
    }

    public XmlShare(String passanger, int reduction, URI uri) {
        this.passanger = passanger;
        this.reduction = reduction;
        this.uri = uri;
    }

    @XmlElement
    public String getPassanger() {
        return passanger;
    }

    public void setPassanger(String passanger) {
        this.passanger = passanger;
    }

    @XmlElement
    public int getReduction() {
        return reduction;
    }

    public void setReduction(int reduction) {
        this.reduction = reduction;
    }

    @XmlAttribute
    public URI getUri() {
        return uri;
    }

    public void setUri(URI uri) {
        this.uri = uri;
    }
}
