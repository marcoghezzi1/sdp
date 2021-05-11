package REST.services;

import REST.beans.GlobalStat;
import REST.beans.GlobalStatsList;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("statistiche")
public class StatsService {

    @GET
    @Produces({"application/json", "application/xml"})
    public Response getAllStats() {
        return Response.ok(GlobalStatsList.getInstance()).build();
    }

    @Path("{lastN}")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getLastStats(@PathParam("lastN") int n) {
        return Response.ok(GlobalStatsList.getInstance().getLista(n)).build();
    }


    @Path("add")
    @POST
    @Consumes("application/json")
    public Response addStats(GlobalStat stats) {
        GlobalStatsList.getInstance().addStat(stats);
        return Response.ok().build();
    }

    @Path("avg/km")
    @GET
    @Consumes("application/json")
    @Produces("application/json")
    public Response avgKm(@QueryParam("t1") String t1, @QueryParam("t2") String t2) {
        List<GlobalStat> copy = GlobalStatsList.getInstance().getLista(t1, t2);
        double avgKm;
        int sum = 0;
        for (GlobalStat g: copy) {
            sum+= g.getAvgKm();
        }
        avgKm = (double) sum / copy.size();
        return Response.ok().entity("{\"Avg km:\" :" + avgKm + "}").build();
    }

    @Path("avg/delivery")
    @GET
    @Produces({"application/json", "application/xml"})
    public Response avgDelivery(@QueryParam("t1") String t1, @QueryParam("t2") String t2) {
        List<GlobalStat> copy = GlobalStatsList.getInstance().getLista(t1, t2);
        double avgDelivery;
        int sum = 0;
        for (GlobalStat g: copy) {
            sum+= g.getAvgKm();
        }
        avgDelivery = (double) sum / copy.size();
        return Response.ok().entity("{\"Avg km:\" :" + avgDelivery + "}").build();
    }



}
