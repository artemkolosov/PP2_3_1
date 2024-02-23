package web.dao;

import web.model.User;
import java.util.List;

public interface UserDao {
    void createUser(User user);
    List<User> readAllUsers();
    User readUser(Long id);

    void updateUser(User user, Long id );

    void deleteUser(Long id);
}
