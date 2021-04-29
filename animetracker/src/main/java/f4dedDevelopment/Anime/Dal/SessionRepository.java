package f4dedDevelopment.Anime.Dal;

import io.quarkus.hibernate.orm.panache.PanacheRepository;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.UUID;

@ApplicationScoped
public class SessionRepository implements PanacheRepository<Session> {
    public Session CreateSession(User user){
        LocalDateTime currentDateTime = LocalDateTime.from(ZonedDateTime.now(ZoneId.of("Europe/Amsterdam")));
        Session session = new Session();
        session.setiD(UUID.randomUUID().toString());
        session.setUser(user);
        session.setExpiryDate(currentDateTime.plusDays(30));
        session.setCreationDate(currentDateTime);
        session.setAlive(true);
        persist(session);
        return session;
    }
}
