package com.backbase.learning.groovybasedtests.service;

import com.backbase.learning.groovybasedtests.domain.User;

import java.util.List;


public interface IUserServices {

    public List<User> getAllUsers();

    public User getUserById(Long id);

    public User updateUser(Long id,User user);

    public void deleteUser(Long id);

    Long createUser(User user);

    User getUserByMailAddress(String mailAddress);
}
