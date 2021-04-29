package f4dedDevelopment.Anime.Logic;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;

import com.google.gson.JsonObject;
import f4dedDevelopment.Anime.Dal.Anime;
import f4dedDevelopment.Anime.Dal.AnimeRepository;
import f4dedDevelopment.Anime.Dal.Genre;
import f4dedDevelopment.Anime.Models.Anime.AddAnime;
import f4dedDevelopment.Anime.Models.Anime.EditAnime;

import java.time.LocalDate;
import java.util.*;

@ApplicationScoped
public class AnimeManager {

    @Inject
    AnimeRepository animeRepository;

    public Anime GetAnimeByName(String Name) {
        return animeRepository.FindByName(Name);
    }

    public List<Anime> GetNewest(){
        return animeRepository.FindNewest();
    }

    public boolean CreateAnime(AddAnime addAnime){
        Anime anime = new Anime();
        anime.setId(UUID.randomUUID().toString());
        anime.setDescription(addAnime.getDescription());
        anime.setName(addAnime.getName());
        anime.setJapaneseName(addAnime.getJapaneseName());
        anime.setImgSrc(addAnime.getImgSrc());

        try {
            animeRepository.persist(anime);
            return true;
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            return false;
        }
    }


    public boolean EditAnime(EditAnime animeModel) {
        Anime anime = animeRepository.FindByID(animeModel.getiD());
        anime.setDescription(animeModel.getDescription());
        anime.setName(animeModel.getName());
        anime.setImgSrc(animeModel.getImgSrc());
        anime.setJapaneseName(animeModel.getJapaneseName());
        Set<Genre> genres = new HashSet<Genre>();
        genres.add(Genre.Action);
        genres.add(Genre.Adventure);
        genres.add(Genre.Fantasy);
        anime.setGenres(genres);
        anime.setReleaseDate(LocalDate.now());
        anime.Save();
        return true;
    }

    public List<Anime> GetAllAnimes() {
        return animeRepository.findAll().list();
    }

    public boolean DeleteAnime(JsonObject obj) {
        if (!obj.has("ID")) {
            return false;
        }
        animeRepository.DeleteAnime(obj.get("ID").getAsString());
        return true;
    }

    public List<Anime> GetRecommended(String userID) {
        return null;
    }
}
