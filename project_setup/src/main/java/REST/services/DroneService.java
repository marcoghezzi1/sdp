package REST.services;


import REST.beans.Drone;
import REST.beans.StormoDroni;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("drone")
public class DroneService {

    @GET
    @Produces({"application/json", "application/xml"})
    public Response getAllWords() {
        return Response.ok(StormoDroni.getInstance()).build();
    }

    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml", "text/plain"})
    public Response addDrone(Drone d) {

        return Response.ok().build();
    }
}
