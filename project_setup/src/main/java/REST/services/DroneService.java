package REST.services;


import REST.beans.Drone;
import REST.beans.StormoDroni;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("drone")
public class DroneService {

    @GET
    @Produces({"application/json", "application/xml"})
    public Response getAllDrones() {
        return Response.ok(StormoDroni.getInstance()).build();
    }


    /* aggiunta di un drone alla flotta
    se l'aggiunta va a buon fine, devo restituire al chiamante la posizione all'interno della smart city e
    la lista dei droni
     */
    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml", "text/plain"})
    public Response addDrone(Drone d) {
        int posizione = StormoDroni.getInstance().checkDrone(d);
        boolean check = posizione == -1;
        if (check) {
            //List<Drone> copy = StormoDroni.getInstance().getStormo();
            StormoDroni.getInstance().add(d);
            //d.setPosizione();
            return Response.ok().build();
        }
        return Response.status(Response.Status.NOT_FOUND).build();
    }

    // rimozione di un drone
    @Path("delete/{id}")
    @DELETE
    public Response removeDrone(@PathParam("id") int id) {
        StormoDroni.getInstance().deleteDrone(id);
        return Response.ok().build();
    }
}
