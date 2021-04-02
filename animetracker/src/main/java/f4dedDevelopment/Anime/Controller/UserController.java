package f4dedDevelopment.Anime.Controller;

import f4dedDevelopment.Anime.Dal.User;
import f4dedDevelopment.Anime.Logic.UserManager;
import f4dedDevelopment.Anime.Models.User.DeleteUser;
import f4dedDevelopment.Anime.Models.User.EditUser;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/User")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@Transactional
public class UserController {

    @Inject UserManager userManager;

    @GET
    public List<User> GetUsers () {
        return userManager.GetAllUsers();
    }

    @PUT
    @Path("/Edit")
    public User EditUser(EditUser editUser){
        return userManager.EditUser(editUser);
    }

    @DELETE
    @Path("/Delete")
    public boolean DeleteUser(DeleteUser deleteUser){
        return userManager.DeleteUser(deleteUser);
    }
}
