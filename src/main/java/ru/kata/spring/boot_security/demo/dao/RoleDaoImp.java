package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import ru.kata.spring.boot_security.demo.model.Role;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.Set;

@Repository
public class RoleDaoImp implements RoleDao {


    private final EntityManager entityManager;

    public RoleDaoImp(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public List<Role> getAllRoles() {
        return entityManager
                .createQuery("Select role From Role role")
                .getResultList();
    }

    @Override
    public void addRole(Role role) {
        entityManager.persist(role);
    }
}
