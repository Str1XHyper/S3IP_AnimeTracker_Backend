package f4dedDevelopment.Anime.Controller;

import f4dedDevelopment.Anime.Dal.Anime;
import f4dedDevelopment.Anime.Dal.AnimeProgress;
import f4dedDevelopment.Anime.Logic.ListManager;
import f4dedDevelopment.Anime.Models.Lists.DeleteFromList;
import f4dedDevelopment.Anime.Models.Lists.AddToList;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/List")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class ListController {
    @Inject
    ListManager listManager;

    @POST
    @Path("/Planned/Add")
    public boolean AddToPlanned(AddToList addToList){
        return listManager.AddToPlanned(addToList);
    }

    @DELETE
    @Path("/Planned/Remove")
    public boolean DeleteFromPlanned(DeleteFromList deleteFromList){
        return listManager.RemoveFromPlanned(deleteFromList);
    }

    @GET
    @Path("/Planned/Get/{UserID}")
    public List<Anime> getPlanned(@PathParam("UserID") String UserID){
        return listManager.GetPlanned(UserID);
    }

    @POST
    @Path("/Watching/Add")
    public boolean AddToWatching(AddToList addToList){
        return listManager.AddToWatching(addToList);
    }

    @GET
    @Path("/Watching/Get/{UserID}")
    public List<AnimeProgress> getWatching(@PathParam("UserID") String UserID){
        return listManager.GetWatching(UserID);
    }
}
