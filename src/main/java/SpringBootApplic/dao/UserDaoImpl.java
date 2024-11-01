package SpringBootApplic.dao;


import SpringBootApplic.model.User;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional
    public void addUser(User user) {
        entityManager.persist(user);
    }

    @Override
    @Transactional(readOnly = true)
    public List<User> getAllUsers() {
        return entityManager.createQuery("select u from User u", User.class).getResultList();
    }

    @Override
    @Transactional
    public void updateUser(User updatedUser) {
        entityManager.merge(updatedUser);
    }

    @Override
    @Transactional
    public void deleteUser(int id) {
        User userToBeDeleted = entityManager.find(User.class, id);
        entityManager.remove(userToBeDeleted);
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(int id) {
        return entityManager.find(User.class, id);
    }
}
