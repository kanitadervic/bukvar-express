package ba.unsa.etf.ri.ppis.bukvarexpress.controller;

import ba.unsa.etf.ri.ppis.bukvarexpress.model.User;
import ba.unsa.etf.ri.ppis.bukvarexpress.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
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
}
