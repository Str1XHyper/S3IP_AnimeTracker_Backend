package f4dedDevelopment.Anime.Logic;

import com.google.gson.Gson;
import f4dedDevelopment.Anime.Dal.Anime;
import f4dedDevelopment.Anime.Dal.AnimeRepository;

import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.logging.Logger;

@Path("/Anime")
public class GetAnime {
    private static Logger logger = Logger.getLogger(GetAnime.class.getName());

    @Inject
    AnimeRepository animeRepository;

    @GET
    @Path("Get/Name={name}")
    @Produces(MediaType.APPLICATION_JSON)
    public String GetSingularAnime(@PathParam("name") String name){
        Anime anime = animeRepository.findByName(name);
        return new Gson().toJson(anime);
    }
}
