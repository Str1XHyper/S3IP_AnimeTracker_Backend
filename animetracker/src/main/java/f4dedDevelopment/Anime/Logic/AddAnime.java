package f4dedDevelopment.Anime.Logic;

import f4dedDevelopment.Anime.Dal.Anime;
import f4dedDevelopment.Anime.Dal.AnimeRepository;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.logging.Logger;

@Path("/Anime")
public class AddAnime {
    private static Logger logger = Logger.getLogger(AddAnime.class.getName());
    @Inject
    AnimeRepository animeRepository;

    @POST
    @Path("AddAnime/{Name}&{Desc}")
    @Transactional
    public boolean AddAnime(@PathParam("Name") String Name, @PathParam("Desc") String Description){
        Anime anime = new Anime();
        anime.setDescription(Description);
        anime.setName(Name);

        if(animeRepository.isPersistent(anime)){
            return false;
        }

        try{

            animeRepository.persist(anime);
            return true;
        } catch (Exception ex){
            logger.warning(ex.getMessage());
            return false;
        }

    }
}
