package com.backbase.learning.groovybasedtests.repository;

import com.backbase.learning.groovybasedtests.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.swing.text.html.Option;
import java.util.List;
import java.util.Optional;

@Repository
public interface InMemoryUserRepository extends JpaRepository<User, Long> {


    List<User> findAll();

    Optional<User> findUserById(Long id);

    void deleteUsersById(Long id);

    Optional<User> findUserByEmail(String email);
}
