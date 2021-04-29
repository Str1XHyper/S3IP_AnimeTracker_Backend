package f4dedDevelopment.Anime.Models.Auth;

import f4dedDevelopment.Anime.Dal.User;

public class LoginResponse {
    private User user;
    private String jwt;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getJwt() {
        return jwt;
    }

    public void setJwt(String jwt) {
        this.jwt = jwt;
    }
}
