package com.agtech.user.resource;

import com.agtech.user.model.User;
import com.agtech.user.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;
    private final String USER_ID = "id";

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        List<User> list = userService.findAll();

        return ResponseEntity.ok().body(list);
    }

    @GetMapping(USER_ID)
    public ResponseEntity<User> findById(@PathVariable Integer id) {
        User user = userService.findById(id);

        return ResponseEntity.ok().body(user);
    }

    @PostMapping
    public ResponseEntity<User> save(@RequestBody User user) {
        User created = userService.save(user);

        return ResponseEntity.ok().body(created);
    }

    @DeleteMapping(USER_ID)
    public ResponseEntity<Void> delete(@PathVariable Integer id) {
        userService.delete(id);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping(USER_ID)
    public ResponseEntity<User> update(
            @PathVariable Integer id,
            @RequestBody User userUpdate) {
        User update = userService.update(userUpdate);

        return ResponseEntity.ok().body(update);
    }

}
