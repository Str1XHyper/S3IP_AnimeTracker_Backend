package f4dedDevelopment.Anime.Logic;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import java.util.logging.Logger;

public class Authentication {

    private static Logger logger = Logger.getLogger(Authentication.class.getName());
    @Path("/Authentication")
    public static class Register{
        @POST
        @Path("Register/{name}&{password}")
        public String name(@PathParam("name") String name,@PathParam("password") String pw) {
            logger.info(name);
            logger.info(pw);
            return "Test";
        }
    }
}
