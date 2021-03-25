package f4dedDevelopment.Anime.Dal;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UserRepository implements PanacheRepository<User> {
    public User FindByName(String name){
        return find("username", name).firstResult();
    }

}
