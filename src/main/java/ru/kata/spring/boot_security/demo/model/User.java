package ru.kata.spring.boot_security.demo.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Setter
@Getter
@Entity
//@Table(name = "users")
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name"})})
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "secondName")
    private String secondName;
    @Column(name = "email")
    private String email;
    @Column(name = "password")
    private String password;

    // изменение ролей
    @Getter
    @ManyToMany(fetch = FetchType.LAZY, cascade = {CascadeType.MERGE})
    @Fetch(FetchMode.JOIN)
    @JoinTable(name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<Role> roles;

    public User(Set<Role> roles) {
        roles.add(new Role(1L, "ROLE_ADMIN"));
        roles.add(new Role(2L, "ROLE_USER"));
        this.roles = roles;
    }

    public User() {
    }

    public User(String name, String secondName, String email, String password, Set<Role> roles) {
        this.name = name;
        this.secondName = secondName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (id != user.id) return false;
        if (!name.equals(user.name)) return false;
        if (!secondName.equals(user.secondName)) return false;
        if (!email.equals(user.email)) return false;
        if (!password.equals(user.password)) return false;
        return roles.equals(user.roles);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, secondName, email, password, roles);
    }
    public String getUsername() {
        return name;
    }

}
