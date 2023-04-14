package spring.boot_security.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.boot_security.models.Role;
import spring.boot_security.models.User;
import spring.boot_security.repository.RoleRepository;
import spring.boot_security.repository.UserRepository;

import java.util.List;
import java.util.Optional;


@Service
@Transactional(readOnly = true)
public class UserServiceImpl implements UserService {
    private PasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;
    private RoleRepository roleRepository;

    public UserServiceImpl(PasswordEncoder bCryptPasswordEncoder, UserRepository userRepository, RoleRepository roleRepository) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
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
    @Transactional
    public void save(User user) {
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void update(long id, User updateUser) {
        updateUser.setId(id);
        userRepository.save(updateUser);
    }
    @Override
    @Transactional
    public void delete(long id) {
        userRepository.deleteById(id);
    }
}
