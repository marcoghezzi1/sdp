package REST.services;

import REST.beans.Dictionary;
import REST.beans.Word;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

@Path("dictionary")
public class DictionaryService {
    @GET
    @Produces({"application/json", "application/xml"})
    public Response getAllWords() {
        return Response.ok(Dictionary.getInstance()).build();
    }

    @Path("add")
    @POST
    @Consumes({"application/json", "application/xml", "text/plain"})
    public Response addWord(Word w) {
        boolean check = Dictionary.getInstance().checkWord(w);
        if (!check) {
            Dictionary.getInstance().add(w);
            return Response.ok().build();
        }
        else
            return Response.status(Response.Status.NOT_FOUND).entity("{\"message\": \"Parola già inserita\"}").build();
    }

    @Path("get/{word}")
    @GET
    @Produces({"text/plain"})
    public Response getWordDefinition(@PathParam("word") String word){
        Word w = Dictionary.getInstance().getDefinition(word);
        if(w!=null)
            return Response.ok(w.getDefinition()).build();
        else
            return Response.status(Response.Status.NOT_FOUND).build();
    }
}
