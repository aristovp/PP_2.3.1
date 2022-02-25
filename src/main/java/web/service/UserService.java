package web.service;

import web.model.User;

import java.util.List;

public interface UserService {
    List<User> getListUsers();

    void add(User user);

    void userUpdate(long id, User userUpdate);

    User getUserById(long id);

    void deleteUsers(long id);
}
