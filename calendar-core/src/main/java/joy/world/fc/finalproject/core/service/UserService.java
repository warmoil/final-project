package joy.world.fc.finalproject.core.service;

import joy.world.fc.finalproject.core.dto.UserCreateRequest;
import joy.world.fc.finalproject.core.entity.User;
import joy.world.fc.finalproject.core.exception.CalendarException;
import joy.world.fc.finalproject.core.exception.ErrorCode;
import joy.world.fc.finalproject.core.repository.UserRepository;
import joy.world.fc.finalproject.core.util.Encryptor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final Encryptor encryptor;
    private final UserRepository userRepository;

    @Transactional
    public User create(UserCreateRequest userCreateRequest) {
        userRepository.findByEmail(userCreateRequest.getEmail())
                .ifPresent(user-> {
                    throw new CalendarException(ErrorCode.ALREADY_EXISTS_USER);
                });
        return userRepository.save(new User(
                userCreateRequest.getName(),
                userCreateRequest.getEmail(),
                encryptor.encrypt(userCreateRequest.getPassword()),
                userCreateRequest.getBirthday()
        ));
    }


    @Transactional
    public Optional<User> findPwMatchUser(String email, String password) {
        return userRepository.findByEmail(email)
                .map(user -> encryptor.isMatch(password, user.getPassword()) ? user : null);
    }

    public User findByUserId(Long id) {
        return userRepository.findById(id).orElseThrow(()->{
            throw new CalendarException(ErrorCode.USER_NOT_FOUND);
        });
    }
}
