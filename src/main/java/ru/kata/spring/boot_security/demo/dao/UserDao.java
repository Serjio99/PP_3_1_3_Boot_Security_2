package ru.kata.spring.boot_security.demo.dao;


import ru.kata.spring.boot_security.demo.model.User;

import java.util.List;

public interface UserDao {
    User getUserByUsername(String username); //+

    List<User> getAllUser(); //+

    User getById(Long id);

    List<User> findUser(User user);

    void addUser(User user);

    void updateUser(Long id, User user);

    void deleteUser(Long id);

    void deleteUser(User user);
}