package f4dedDevelopment.Anime.Logic;

import f4dedDevelopment.Anime.Dal.Session;
import nl.xanderv2001.jwt.JWT;
import nl.xanderv2001.jwt.JWTException;
import org.jose4j.json.internal.json_simple.parser.ParseException;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.enterprise.context.RequestScoped;
import java.nio.charset.StandardCharsets;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.ZoneId;
import java.util.UUID;

@RequestScoped
public class JWTWrapper {

    private final SecretKey key = new SecretKeySpec("8vDQB3zB^EMUph@cqWq%HkXn7xHeU1ufC5k0ggx".getBytes(StandardCharsets.UTF_8), "HmacSHA256");

    public JWT getJWT(String jwtString) throws NoSuchAlgorithmException, InvalidKeyException, JWTException, ParseException {
        return JWT.parser().setKey(key).verify(jwtString);
    }

    public String GenerateJWT(Session session) throws NoSuchAlgorithmException, InvalidKeyException {
        return JWT.build(key)
                .setIssuedAt(session.getCreationDate().atZone(ZoneId.of("Europe/Amsterdam")).toInstant().toEpochMilli())
                .setIssuer("AniTracker")
                .setSessionId(session.getiD())
                .setSubject(session.getUser().getiD())
                .setJwtId(UUID.randomUUID().toString())
                .encode().getToken();
    }
}

