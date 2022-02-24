package web.dao;

import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import web.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import java.util.List;

@Component
@Transactional(readOnly = true)
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<User> getListUsers() {
        return entityManager.createQuery("select u from User u", User.class)
                .getResultList();
    }

    @Override
    public User getUserById(long id) {
        TypedQuery<User> q = entityManager.createQuery(
                "select u from User u where u.id = :id", User.class);
        q.setParameter("id", id);
        return q.getResultList().stream().findAny().orElse(null);
    }

    @Override
    @Transactional
    public void add(User user) {
        entityManager.persist(user);
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
        entityManager.createQuery("delete from User where  id=: id")
                .setParameter("id", id)
                .executeUpdate();
    }
}
