package com.backbase.learning.groovybasedtests.api;

import com.backbase.learning.groovybasedtests.domain.User;
import com.backbase.learning.groovybasedtests.service.IUserServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {


    private IUserServices userServices;

    public UserRestController(IUserServices userServices) {
        this.userServices = userServices;
    }

    @GetMapping()
    public ResponseEntity<?> getAllUsers() {
        return ResponseEntity.ok(userServices.getAllUsers());
    }

    @PostMapping()
    public ResponseEntity<?> createUser(@RequestBody User user) {
        return ResponseEntity.ok(userServices.createUser(user));
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getUser(@PathVariable("id") Long id) {
        return ResponseEntity.ok(userServices.getUserById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        return ResponseEntity.ok(userServices.updateUser(id, user));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable("id") Long id) {
        userServices.deleteUser(id);
        return ResponseEntity.ok().build();
    }

}
