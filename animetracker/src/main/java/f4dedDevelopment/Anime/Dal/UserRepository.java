package f4dedDevelopment.Anime.Dal;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.sql.SQLException;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public User FindByName(String name){
        return find("username", name).firstResult();
    }
    public User FindById(String id) {
        return find("id", id).firstResult();
    }
}
