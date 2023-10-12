package com.experis.no.boxinator.services.user;
import com.experis.no.boxinator.exceptions.UserNotFoundException;
import com.experis.no.boxinator.models.User;
import com.experis.no.boxinator.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Collection;
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findById(String uid) {
        return userRepository.findById(uid).orElseThrow(() -> new UserNotFoundException(uid));
    }

    @Override
    public Collection<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User add(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User update(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public void delete(String id) {
        userRepository.deleteById(id);
    }

    @Override
    public boolean exists(String id) {
        return userRepository.existsById(id);
    }
}
