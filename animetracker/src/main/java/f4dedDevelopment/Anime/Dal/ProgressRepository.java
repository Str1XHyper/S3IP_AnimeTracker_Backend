package f4dedDevelopment.Anime.Dal;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
@ApplicationScoped
public class ProgressRepository implements PanacheRepository<AnimeProgress> {
    public AnimeProgress FindByUserAnime(String UserID, String AnimeID){
        Map<String, Object> params = new HashMap<>();
        params.put("User", UserID);
        params.put("Anime", AnimeID);
        return find("user_ID = :User and anime_ID = :Anime", params).firstResult();
    }

    public List<AnimeProgress> getPlanned(String userID) {
        Map<String, Object> params = new HashMap<>();
        params.put("User", userID);
        params.put("Planned", true);
        return list("user_ID = :User And isPlanned = :Planned", params);
    }

    public List<AnimeProgress> getWatching(String userID) {
        Map<String, Object> params = new HashMap<>();
        params.put("User", userID);
        params.put("Watching", true);
        return list("user_ID = :User And isWatching = :Watching", params);
    }
}
