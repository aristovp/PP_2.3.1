package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {

    List<User> getListUsers();

    void add(User user);

    void userUpdate(long id, User userUpdate);

    User getUserById(long id);

    void deleteUsers(long id);
}
