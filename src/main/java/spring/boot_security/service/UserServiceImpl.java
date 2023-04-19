package spring.boot_security.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot_security.models.Role;
import spring.boot_security.models.User;
import spring.boot_security.repository.RoleRepository;
import spring.boot_security.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User findOne(long id) {
        Optional<User> foundUser = userRepository.findById(id);
        return foundUser.orElse(null);
    }

    @Override
    public User findByName(String name) {
        return userRepository.findByName(name);
    }

    @Override

    public void save(User user) {
        user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        userRepository.save(user);
    }

    @Override
    public void update(long id, User updateUser) {
        updateUser.setId(id);
        updateUser.setPassword(bCryptPasswordEncoder.encode(updateUser.getPassword()));
        userRepository.saveAndFlush(updateUser);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public List<Role> getRole() {
        return roleRepository.findAll();
    }
}
