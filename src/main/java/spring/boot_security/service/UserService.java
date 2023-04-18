package spring.boot_security.service;

import spring.boot_security.models.Role;
import spring.boot_security.models.User;

import java.util.List;

public interface UserService {
    List<User> findAll();

    User findOne(long id);

    User findByName(String name);

    void save(User user);

    void update(long id, User userUpdate);

    void delete(long id);
    List<Role> getRole();

}
