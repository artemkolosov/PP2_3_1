package web.service;

import web.model.User;


import java.util.List;


public interface UserService {

    void createUser(User user);
    List<User> readAllUsers();
    User readUser(Long id);

    void updateUser(User user);

    void deleteUser(Long id);
}
