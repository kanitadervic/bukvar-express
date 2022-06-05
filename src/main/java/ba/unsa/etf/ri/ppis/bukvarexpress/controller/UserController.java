package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.UserEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.User;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@CrossOrigin
@RequestMapping("/api/user")
public class UserController {
    private final UserService userService;

    @GetMapping
    public ResponseEntity<User> getUser(@RequestParam Long userId) {
        User user = userService.getUserById(userId);

        return ResponseEntity.ok(user);
    }

    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody User newUser) {
        User user = userService.createUser(newUser);

        return ResponseEntity.ok(user);
    }

    @PutMapping
    public ResponseEntity<User> updateUser(@RequestBody User newUser) {
        if (newUser.getId() == null) {
            return null;
        }
        User user = userService.getUserById(newUser.getId());
        if (user == null) {
            return null;
        }

        User updatedUser = userService.updateUser(newUser);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteUser(@RequestParam Long userId) {
        if (userId == null) {
            return null;
        }
        User user = userService.getUserById(userId);
        if (user == null) {
            return null;
        }
        userService.deleteUserById(userId);
        return ResponseEntity.ok("Successfully deleted a user!");
    }

    @GetMapping("/userRole")
    public ResponseEntity<UserEntity> getRoleForUser(@RequestParam String username, @RequestParam String password){
        return ResponseEntity.ok(userService.getRoleForUser(username, password));
    }
}
