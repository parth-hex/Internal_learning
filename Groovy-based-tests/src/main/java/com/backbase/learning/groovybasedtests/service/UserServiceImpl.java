package com.backbase.learning.groovybasedtests.service;

import com.backbase.learning.groovybasedtests.domain.User;
import com.backbase.learning.groovybasedtests.repository.InMemoryUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserServices{

    InMemoryUserRepository userRepository;

    public UserServiceImpl(InMemoryUserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findUserById(id).get();
    }

    @Override
    public User updateUser(Long id, User user) {
        User userFromDB = getUserById(id);
        userFromDB.setEmail(user.getEmail());
        userFromDB.setFName(user.getFName());
        userFromDB.setLName(user.getLName());

        userRepository.save(userFromDB);

        return userFromDB;
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Long createUser(User user) {
        return userRepository.save(user).getId();
    }
}
