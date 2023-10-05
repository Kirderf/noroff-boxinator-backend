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
    public User findById(Integer integer) {
        return userRepository.findById(integer).orElseThrow(() -> new UserNotFoundException(integer));
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
    public void delete(Integer integer) {
        userRepository.deleteById(integer);
    }

    @Override
    public boolean exists(Integer integer) {
        return userRepository.existsById(integer);
    }
}
