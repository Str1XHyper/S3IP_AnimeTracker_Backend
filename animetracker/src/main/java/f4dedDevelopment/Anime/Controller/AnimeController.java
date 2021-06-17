package f4dedDevelopment.Anime.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import f4dedDevelopment.Anime.Dal.Anime;
import f4dedDevelopment.Anime.Logic.AnimeManager;
import f4dedDevelopment.Anime.Models.Anime.AddAnime;
import f4dedDevelopment.Anime.Models.Anime.EditAnime;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/Anime")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AnimeController {
    @Inject AnimeManager animeManager;

    @POST
    @Path("AddAnime")
    public Response AddAnime(AddAnime addAnime){
        Anime anime = animeManager.CreateAnime(addAnime);
        if(anime == null){
            return Response.status(Response.Status.CONFLICT).build();
        } else {
            return Response.ok(anime).build();
        }
    }

    @GET
    @Path("{Name}")
    public Anime GetAnime(@PathParam("Name") String name){
        return animeManager.GetAnimeByName(name);
    }

    @GET
    @Path("/All")
    public List<Anime> GetAllAnimes(){
        return animeManager.GetAllAnimes();
    }

    @GET
    @Path("/Newest")
    public List<Anime>  GetNewestAnimes(){
        return animeManager.GetNewest();
    }

    @GET
    @Path("/Recommended/{UserID}")
    public List<Anime>  GetRecommendedAnimes(@PathParam("UserID") String UserID){
        return animeManager.GetRecommended(UserID);
    }


    @PUT
    @Path("EditAnime")
    public boolean EditAnime(EditAnime anime){
        System.out.println(anime.getJapaneseName());
        return animeManager.EditAnime(anime);
    }

    @DELETE
    @Path("RemoveAnime")
    public boolean RemoveAnime(String data){
        JsonObject obj = new Gson().fromJson(data, JsonObject.class);

        return animeManager.DeleteAnime(obj);
    }
}
