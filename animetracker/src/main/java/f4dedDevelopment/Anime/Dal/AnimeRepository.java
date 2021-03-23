package f4dedDevelopment.Anime.Dal;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

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

}
