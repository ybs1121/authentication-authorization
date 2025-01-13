package hello.authenticationauthorization.init;

import hello.authenticationauthorization.User;
import hello.authenticationauthorization.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
public class Init {

    private final UserRepository userRepository;

    @PostConstruct
    public void init() {
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("admin");
        user1.setPassword("admin");
        user1.setRole("GRADE_1");

        userRepository.save(user1);

        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user");
        user2.setPassword("user");
        user2.setRole("GRADE_2");

        userRepository.save(user1);


        log.info("init data!!");
    }
}
