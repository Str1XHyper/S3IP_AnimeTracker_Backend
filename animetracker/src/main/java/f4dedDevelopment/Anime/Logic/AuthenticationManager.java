package f4dedDevelopment.Anime.Logic;

import f4dedDevelopment.Anime.Dal.SessionRepository;
import f4dedDevelopment.Anime.Dal.User;
import f4dedDevelopment.Anime.Dal.UserRepository;
import f4dedDevelopment.Anime.Models.Login;
import f4dedDevelopment.Anime.Models.LoginResponse;
import f4dedDevelopment.Anime.Models.Register;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.InternalServerErrorException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

@ApplicationScoped
public class AuthenticationManager {
    @Inject UserRepository userRepository;
    @Inject SessionRepository sessionRepository;
    @Inject Hasher hasher;
    @Inject JWTWrapper jwt;



    public boolean AddUser(Register register){
        User user = new User();
        user.setiD(UUID.randomUUID().toString());
        user.setUsername(register.getUsername());
        user.setEmail(register.getEmail());
        user.setPassword(hasher.EncryptPassword(register.getPassword()));
        userRepository.persist(user);
        return true;
    }

    public LoginResponse VerifyLogin(Login login) throws Exception{
        User user = userRepository.FindByName(login.getUsername());
        boolean canLogin = hasher.ValidatePassword(login.getPassword(), user.getPassword());

        if(canLogin){
            LoginResponse response = new LoginResponse();
            response.setUser(user);
            try {
                response.setJwt(jwt.GenerateJWT(sessionRepository.CreateSession(user)));
            } catch (NoSuchAlgorithmException e) {
                throw new InternalServerErrorException("The given algorithm can't be used.");
            } catch (InvalidKeyException e) {
                throw new InternalServerErrorException("The given key isn't correct.");
            }
            return response;
        }
        throw new Exception("Password combination incorrect!");

    }
}
