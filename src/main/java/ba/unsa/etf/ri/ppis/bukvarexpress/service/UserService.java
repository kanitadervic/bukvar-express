package ba.unsa.etf.ri.ppis.bukvarexpress.service;

import ba.unsa.etf.ri.ppis.bukvarexpress.entity.UserEntity;
import ba.unsa.etf.ri.ppis.bukvarexpress.model.User;
import ba.unsa.etf.ri.ppis.bukvarexpress.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public User getUserById(Long userId) {
        return toModel(userRepository.getById(userId));
    }

    public User createUser(User user) {
        UserEntity userEntity = toEntity(user);
        return toModel(userRepository.save(userEntity));
    }

    public User updateUser(User user) {
        UserEntity userEntity = toEntity(user);

        return toModel(userRepository.save(userEntity));
    }

    public void deleteUserById(Long userId) {
        userRepository.deleteById(userId);
    }

    private User toModel(UserEntity user) {
        return User
                .builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .gender(user.getGender())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }

    private UserEntity toEntity(User user) {
        return UserEntity
                .builder()
                .id(user.getId())
                .name(user.getName())
                .phone(user.getPhone())
                .gender(user.getGender())
                .email(user.getEmail())
                .username(user.getUsername())
                .password(user.getPassword())
                .role(user.getRole())
                .build();
    }
}
