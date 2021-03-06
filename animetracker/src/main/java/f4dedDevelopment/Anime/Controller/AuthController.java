package f4dedDevelopment.Anime.Controller;

import f4dedDevelopment.Anime.Dal.User;
import f4dedDevelopment.Anime.Logic.AuthenticationManager;
import f4dedDevelopment.Anime.Models.Auth.Login;
import f4dedDevelopment.Anime.Models.Auth.LoginResponse;
import f4dedDevelopment.Anime.Models.Auth.Register;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/Auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class AuthController {

    @Inject AuthenticationManager authenticationManager;

    @POST
    @Path("/Register")
    public User Register(Register register){
        return authenticationManager.AddUser(register);
    }

    @POST
    @Path("/Login")
    public LoginResponse Login(Login login){
        try{
            return authenticationManager.VerifyLogin(login);
        } catch (Exception ex){
            return null;
        }
    }
}
