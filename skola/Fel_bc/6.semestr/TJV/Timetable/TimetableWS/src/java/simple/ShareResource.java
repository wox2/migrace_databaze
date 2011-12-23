package simple;

import java.math.BigDecimal;
import java.util.Random;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.GET;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response.Status;

@Path("share/{passanger}")
public class ShareResource {

    @Context
    private UriInfo uriInfo;

    public ShareResource() {
    }
    
    @GET
    @Produces("application/xml")
    public XmlShare getXml(@PathParam("passanger") String passanger) {
        int reduction;
        if (passanger.equals("student")) {
            reduction = 20;
        } else if (passanger.equals("duchodce")) {
            reduction = 100;
        } else if (passanger.equals("dospeli")) {
            reduction = 0;
        } else {
            throw new WebApplicationException(Status.NOT_FOUND);
        }
        return new XmlShare(passanger, reduction, uriInfo.getAbsolutePath());
    }
}
