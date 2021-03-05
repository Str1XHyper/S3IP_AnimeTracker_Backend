package f4dedDevelopment.Anime.Dal;

import io.quarkus.hibernate.orm.panache.PanacheQuery;
import io.quarkus.hibernate.orm.panache.PanacheRepository;
import org.hibernate.sql.Insert;

import javax.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class AnimeRepository implements PanacheRepository<Anime> {
    public Anime findByName(String name){
        return find("Name", name).firstResult();
    }

    public Anime first() {
        return findAll().firstResult();
    }
}
