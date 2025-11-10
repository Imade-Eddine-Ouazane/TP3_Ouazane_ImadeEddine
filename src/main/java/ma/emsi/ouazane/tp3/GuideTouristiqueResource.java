package ma.emsi.ouazane.tp3;

import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import ma.emsi.ouazane.tp3.llm.GuideTouristiqueClient;

@Path("/guide")
public class GuideTouristiqueResource {
    @Inject
    private GuideTouristiqueClient client;
    @GET
    @Path("/lieu/{ville_ou_pays}/{nombre}")
    @Produces(MediaType.APPLICATION_JSON + ";charset=UTF-8")
    public String getGuide(@PathParam("ville_ou_pays") String ville_ou_pays,@PathParam("nombre") int nombre) {
        return client.ask(ville_ou_pays,nombre);
    }

}