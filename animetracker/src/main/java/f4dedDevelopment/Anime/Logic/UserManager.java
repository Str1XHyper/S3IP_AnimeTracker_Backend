package f4dedDevelopment.Anime.Logic;

import f4dedDevelopment.Anime.Dal.User;
import f4dedDevelopment.Anime.Dal.UserRepository;
import f4dedDevelopment.Anime.Models.User.DeleteUser;
import f4dedDevelopment.Anime.Models.User.EditUser;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.util.List;

@ApplicationScoped
public class UserManager {
    @Inject UserRepository userRepository;

    public List<User> GetAllUsers(){
        return userRepository.findAll().list();
    };

    public User EditUser(EditUser editUser) {
        User user = userRepository.FindById(editUser.getId());
        user.setRole(editUser.getRole());
        user.setUsername(editUser.getUsername());
        user.setEmail(editUser.getEmail());
        user.Save();
        return user;
    }

    public boolean DeleteUser(DeleteUser deleteUser) {
        User user = userRepository.FindById(deleteUser.getId());
        userRepository.delete(user);
        return true;
    }
}
