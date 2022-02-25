package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.dao.UserDao;
import web.model.User;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService{


    private final UserDao userDao;

    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public List<User> getListUsers() {
        return userDao.getListUsers();
    }

    @Override
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public void add(User user) {
        userDao.add(user);
    }

    @Override
    @Transactional
    public void userUpdate(long id, User userUpdate) {
        User userUpd = getUserById(id);
        userUpd.setFirstName(userUpdate.getFirstName());
        userUpd.setLastName(userUpdate.getLastName());
        userUpd.setEmail(userUpdate.getEmail());
    }

    @Override
    @Transactional
    public void deleteUsers(long id) {
        userDao.deleteUsers(id);
    }
}
