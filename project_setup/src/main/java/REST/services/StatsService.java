package REST.services;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;

@Path("statistiche")
public class StatsService {

    @Path("avg")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response avgKm(@QueryParam("t1") String t1, @QueryParam("t2") String t2) {
        return Response.ok().build();
    }

}
