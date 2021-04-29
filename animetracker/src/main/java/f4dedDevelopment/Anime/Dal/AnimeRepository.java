package f4dedDevelopment.Anime.Dal;

import io.quarkus.hibernate.orm.panache.PanacheRepository;
import io.quarkus.panache.common.Sort;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AnimeRepository implements PanacheRepository<Anime> {

    public Anime FindByName(String name)
    {
        return find("Name", name).firstResult();
    };

    public Anime FindByID(String id) {
        return find("id", id).firstResult();
    }

    public void DeleteAnime(String id) {
        delete("id", id);
    }

    public List<Anime> FindNewest(){
        return listAll(Sort.descending("releaseDate"));
    }

}
