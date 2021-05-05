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
        int pos = Dictionary.getInstance().checkWord(w);
        boolean wordNotFound = pos == -1;
        if (wordNotFound) {
            Dictionary.getInstance().add(w);
            return Response.ok().entity("{\"message\": \"Parola inserita\"}").build();
        }
        else
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\": \"Parola già inserita\"}").build();
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

    @Path("update/{word}")
    @PUT
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response updateWord(@PathParam("word") String word, String definition){
        Word w = new Word(word, definition);
        if (Dictionary.getInstance().checkWord(w)==-1)
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("{\"message\": \"Parola inesistente\"}").build();
        Dictionary.getInstance().updateDefinition(w);
        return Response.ok().build();

    }

    @Path("delete/{word}")
    @DELETE
    @Consumes({"application/json"})
    @Produces({"application/json"})
    public Response deleteWord(@PathParam("word") String word) {
        Dictionary.getInstance().deleteWord(word);
        return Response.ok().build();
    }

}
