package com.example.spring311.dao;

import com.example.spring311.model.User;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void add(User user) {
        entityManager.persist(user);
    }

    @Override
    public void delete(Long id) {
       entityManager.createQuery("DELETE FROM User WHERE id = :id")
               .setParameter("id", id)
               .executeUpdate();
    }

    @Override
    public void update(User user) {
        entityManager.merge(user);
    }

    @Override
    public User findById(Long id) {
       return entityManager.find(User.class, id);
    }

    @Override
    public List<User> getAll() {
        List<User> result = entityManager.createQuery("SELECT user from User user").getResultList();
        return result;
    }
}
