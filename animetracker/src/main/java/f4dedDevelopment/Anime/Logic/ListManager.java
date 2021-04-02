package f4dedDevelopment.Anime.Logic;

import f4dedDevelopment.Anime.Dal.*;
import f4dedDevelopment.Anime.Models.Lists.DeleteFromList;
import f4dedDevelopment.Anime.Models.Lists.AddToList;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@ApplicationScoped
public class ListManager {
    @Inject UserRepository userRepository;
    @Inject AnimeRepository animeRepository;
    @Inject ProgressRepository progressRepository;

    public boolean AddToPlanned(AddToList addToList){
        AnimeProgress progress = progressRepository.FindByUserAnime(addToList.getUserID(), addToList.getAnimeID());
        if(progress == null){
            User user = userRepository.FindById(addToList.getUserID());
            Anime anime = animeRepository.FindByID(addToList.getAnimeID());

            progress = createNewProgress(anime,user);
        }
        progress.setPlanned(true);
        progress.setCompleted(false);
        progress.setWatching(false);
        progressRepository.persist(progress);
        return true;
    }

    public boolean RemoveFromPlanned(DeleteFromList deleteFromList) {
        AnimeProgress progress = progressRepository.FindByUserAnime(deleteFromList.getUserID(), deleteFromList.getAnimeID());
        progress.setPlanned(false);
        progress.persist();
        return false;
    }

    public List<Anime> GetPlanned(String userID) {
        List<AnimeProgress> animeProgressList = progressRepository.getPlanned(userID);
        List<Anime> animes = new ArrayList<>();
        for (AnimeProgress animeProgress: animeProgressList) {
            animes.add(animeProgress.getAnime());
        }
        return animes;
    }

    public boolean AddToWatching(AddToList addToList){
        AnimeProgress progress = progressRepository.FindByUserAnime(addToList.getUserID(), addToList.getAnimeID());
        if(progress == null){
            User user = userRepository.FindById(addToList.getUserID());
            Anime anime = animeRepository.FindByID(addToList.getAnimeID());

            progress = createNewProgress(anime,user);
        }
        progress.setPlanned(false);
        progress.setCompleted(false);
        progress.setWatching(true);
        progressRepository.persist(progress);
        return true;
    }

    private AnimeProgress createNewProgress(Anime anime, User user){
        AnimeProgress progress = new AnimeProgress();
        progress.setId(UUID.randomUUID().toString());
        progress.setAnime(anime);
        progress.setUser(user);
        progress.setProgress(0);
        return progress;
    }

    public List<AnimeProgress> GetWatching(String userID) {
        return progressRepository.getWatching(userID);
    }
}
