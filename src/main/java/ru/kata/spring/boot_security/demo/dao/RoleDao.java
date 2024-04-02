package ru.kata.spring.boot_security.demo.dao;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.model.Role;

import java.util.List;
import java.util.Set;


@Repository
public interface RoleDao {

    List<Role> getAllRoles();
    @Transactional
    void addRole(Role role);
}