package f4dedDevelopment.Anime.Controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import f4dedDevelopment.Anime.Logic.AnimeManager;
import f4dedDevelopment.Anime.Models.Anime.AddAnime;
import f4dedDevelopment.Anime.Models.Anime.EditAnime;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;

@Path("/Anime")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AnimeController {
    @Inject AnimeManager animeManager;

    @POST
    @Path("AddAnime")
    public boolean AddAnime(AddAnime anime){
        return animeManager.CreateAnime(anime);
    }

    @GET
    @Path("{Name}")
    public String GetAnime(@PathParam("Name") String name){
        return new Gson().toJson(animeManager.GetAnimeByName(name));
    }

    @GET
    public String GetAnimes(){
        return new Gson().toJson(animeManager.GetAllAnimes());
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
